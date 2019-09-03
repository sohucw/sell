package com.cjw.sell.service.impl;

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
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 21:09 2019-08-29
 */
@Service
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
        BeanUtils.copyProperties(orderDto, orderMaster);
        orderMaster.setOrderId(orderId);
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
    public OrderDto findOne(String orderId) {
        return null;
    }

    @Override
    public Page<OrderDto> findList(String buyerOpenId, Pageable pageable) {
        return null;
    }

    @Override
    public OrderDto cancel(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto finish(OrderDto orderDto) {
        return null;
    }

    @Override
    public OrderDto paid(OrderDto orderDto) {
        return null;
    }
}
