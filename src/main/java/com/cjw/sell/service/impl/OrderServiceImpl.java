package com.cjw.sell.service.impl;

import com.cjw.sell.converter.OrderMaster2OrderDto;
import com.cjw.sell.dao.OrderDetailDao;
import com.cjw.sell.dao.OrderMasterDao;
import com.cjw.sell.dto.CartDto;
import com.cjw.sell.dto.OrderDto;
import com.cjw.sell.entity.OrderDetail;
import com.cjw.sell.entity.OrderMaster;
import com.cjw.sell.entity.ProductInfo;
import com.cjw.sell.enums.OrderStatusEnum;
import com.cjw.sell.enums.PayStatusEnum;
import com.cjw.sell.enums.ResultEnum;
import com.cjw.sell.exception.SellException;
import com.cjw.sell.service.OrderService;
import com.cjw.sell.service.ProductService;
import com.cjw.sell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @param
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 21:09 2019-08-29
 */
@Service
@Slf4j
public class OrderServiceImpl implements OrderService {


    @Autowired
    private ProductService productService;
    @Autowired
    private OrderDetailDao orderDetailDao;

    @Autowired
    private OrderMasterDao orderMasterDao;

    @Override
    @Transactional
    public OrderDto createOrder(OrderDto orderDto) {
        String orderId = KeyUtil.genUniqueKey(); // 创建的时候生成订单
        BigDecimal orderAmount = new BigDecimal(BigInteger.ONE);
        // 创建订单
        // 1 查询商品 数量 价格
        for(OrderDetail orderDetail: orderDto.getOrderDetailList()) {
            ProductInfo one = productService.getOne(orderDetail.getProductId());
            if(one == null) {
                throw  new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            // 2 计算总价 订单总价
            orderAmount = (BigDecimal) one.getProductPrice()
                    .multiply(new BigDecimal(orderDetail.getProductQuantity()))
                    .add(orderAmount);

            // 3 写入订单数据库
            BeanUtils.copyProperties(one, orderDetail);
            orderDetail.setDetailId(KeyUtil.genUniqueKey()); // 主键
            orderDetail.setOrderId(orderId); // 订单id
            orderDetailDao.save(orderDetail);



        };
        // 3 写入订单数据库
        OrderMaster orderMaster = new OrderMaster();
        orderDto.setOrderId(orderId);
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderStatus(OrderStatusEnum.NEW.getCode());
        orderMaster.setPayStatus(PayStatusEnum.WAIT.getCode());
        orderMaster.setOrderAmount(orderAmount);
        orderMasterDao.save(orderMaster);

        // 4 扣库存
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream().map(
                orderDetail -> new CartDto(orderDetail.getProductId(), orderDetail.getProductQuantity()))
                .collect(Collectors.toList());


        productService.decreaseStock(cartDtoList);





        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto findOne(String orderId) {
        OrderMaster orderMaster = orderMasterDao.getOne(orderId);
        if(orderMaster == null) {
            throw  new SellException(ResultEnum.order_not_exist);
        }
        List<OrderDetail> orderDetailList = orderDetailDao.findByOrderId(orderId);
        if(CollectionUtils.isEmpty(orderDetailList)) {
            throw  new SellException(ResultEnum.order_detail_not_exist);
        }
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        Page<OrderMaster> orderMasterPage = orderMasterDao.findByBuyerOpenid(buyerOpenId, pageable);
        List<OrderDto> orderDtos = OrderMaster2OrderDto.convert(orderMasterPage.getContent());
        Page<OrderDto> orderDtoPage = new PageImpl<OrderDto>(orderDtos, pageable, orderMasterPage.getTotalElements());
        return orderDtoPage;
    }

    @Override
    @Transactional
    public OrderDto cancel(OrderDto orderDto) {
        OrderMaster orderMaster = new OrderMaster();

        // 判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("取消订单 订单状态不正确 orderId={}, orderState={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.order_status_error);
        }
        // 修改订单状态
        orderDto.setOrderStatus(OrderStatusEnum.CANCEL.getCode());
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateReulst = orderMasterDao.save(orderMaster);
        if(updateReulst == null) {
            log.error("更新失败 orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.order_update_error);
        }
        // 返还库存
        if(CollectionUtils.isEmpty(orderDto.getOrderDetailList())) {
            log.error("【取消订单】订单中无商品信息， orderDto={}", orderDto);
            throw  new SellException(ResultEnum.order_detail_empty);
        }
        List<CartDto> cartDtoList = orderDto.getOrderDetailList().stream()
                .map(e -> new CartDto(e.getProductId(), e.getProductQuantity()))
                .collect(Collectors.toList());
        productService.increaseStock(cartDtoList);
        // 退款

        if(orderDto.getOrderStatus().equals(PayStatusEnum.SUCCESS.getCode())) {
            // TODO:  发起退款流程
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto finish(OrderDto orderDto) {
        // 判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("完结订单 订单状态不正确 orderId={}, orderState={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.order_status_error);
        }
        // 修改状态
        orderDto.setOrderStatus(OrderStatusEnum.FINISHED.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateMaster = orderMasterDao.save(orderMaster);
        if(updateMaster == null) {
            log.error("更新失败 orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.order_update_error);
        }
        return orderDto;
    }

    @Override
    @Transactional
    public OrderDto paid(OrderDto orderDto) {
        // 判断订单状态
        if(!orderDto.getOrderStatus().equals(OrderStatusEnum.NEW.getCode())){
            log.error("支付订单 订单状态不正确 orderId={}, orderState={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.order_status_error);
        }
        // 判断支付状态
        if(!orderDto.getPayStatus().equals(PayStatusEnum.WAIT)) {
            log.error("支付订单 订单支付状态不正确 orderId={}, orderState={}", orderDto.getOrderId(), orderDto.getOrderStatus());
            throw new SellException(ResultEnum.order_pay_error);
        }
        // 修改支付状态
        orderDto.setPayStatus(PayStatusEnum.SUCCESS.getCode());
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(orderDto, orderMaster);
        OrderMaster updateMaster = orderMasterDao.save(orderMaster);
        if(updateMaster == null) {
            log.error("更新失败 orderMaster={}", orderMaster);
            throw new SellException(ResultEnum.order_update_error);
        }
        return orderDto;
    }
}
