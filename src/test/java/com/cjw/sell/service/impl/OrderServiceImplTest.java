package com.cjw.sell.service.impl;

import com.cjw.sell.dto.OrderDto;
import com.cjw.sell.entity.OrderDetail;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.annotation.Transient;
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
    }

    @Test
    public void findList() {
    }

    @Test
    public void cancel() {
    }

    @Test
    public void finish() {
    }

    @Test
    public void paid() {
    }
}