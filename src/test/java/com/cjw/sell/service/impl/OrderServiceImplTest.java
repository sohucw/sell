package com.cjw.sell.service.impl;

import com.cjw.sell.dto.OrderDto;
import com.cjw.sell.entity.OrderDetail;
import com.cjw.sell.enums.PayStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 09:39 2019-08-30
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class OrderServiceImplTest {

    @Autowired
    private OrderServiceImpl orderService;
    private final String buyerOpenId = "110110";

    @Test
    // @Transactional
    public void createOrder() {
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerName("jack chen");
        orderDto.setBuyerAddress("北京 china");
        orderDto.setBuyerPhone("18701664166");
        orderDto.setBuyerOpenid(buyerOpenId);

        // 购物车
        List<OrderDetail> orderDetailList = new ArrayList<>();
        OrderDetail o1 = new OrderDetail();
        o1.setProductId("111111");
        o1.setProductQuantity(1);
        orderDetailList.add(o1);

        OrderDetail o2 = new OrderDetail();
        o2.setProductId("33333");
        o2.setProductQuantity(2);
        orderDetailList.add(o2);


        orderDto.setOrderDetailList(orderDetailList);

        OrderDto orderDto1 = orderService.createOrder(orderDto);
        log.info("创建订单{}", orderDto1);
    }


    @Test
    public void findOne() {
        OrderDto one = orderService.findOne("1567132346283643041");
        log.info("查询单个订单 result={}", one);
        // System.out.println(one.getBuyerName());
    }

    @Test
    public void findList() {
        Page<OrderDto> buyerOpenId = orderService.findList("110110", PageRequest.of(0, 2));
        log.info("查询list buyerOpenId {}",buyerOpenId.getTotalElements());
    }

    @Test
    public void cancel() {
        OrderDto one = orderService.findOne("1567132346283643041");

        OrderDto cancel = orderService.cancel(one);
        log.info("取消订单 orderStatue={}", cancel.getOrderStatus());
    }

    @Test
    public void finish() {
        // 判断订单状态
        OrderDto one = orderService.findOne("1567132346283643041");
        OrderDto cancel = orderService.finish(one);
        log.info("完结订单 orderStatue={}", cancel.getOrderStatus());
        // 修改状态
        //
    }

    @Test
    public void paid() {
        // 判断订单状态
        OrderDto one = orderService.findOne("1567132346283643041");
        OrderDto cancel = orderService.finish(one);
        log.info("支付订单 orderStatue={}", cancel.getOrderStatus());
        Assert.assertEquals(PayStatusEnum.SUCCESS, cancel.getOrderStatus());
    }
}