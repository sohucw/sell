package com.cjw.sell.service.impl;

import com.cjw.sell.dto.OrderDto;
import com.cjw.sell.enums.ResultEnum;
import com.cjw.sell.exception.SellException;
import com.cjw.sell.service.BuyerService;
import com.cjw.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 14:06 2019-09-03
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Autowired
    private OrderService orderService;

    @Override
    public OrderDto findOrderOne(String openid, String orderId) {

        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDto cancelOrder(String openid, String orderId) {
        OrderDto orderDto = checkOrderOwner(openid, orderId);
        if (orderDto == null) {

            log.error("取消订单 查询不到改订单 orderId={}", orderId);
            throw  new SellException(ResultEnum.order_not_exist);
        }
        return orderService.cancel(orderDto);
    }

    private OrderDto checkOrderOwner(String openid, String orderId) {
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null) {
            return null;
        }
        // 判断是否是自己的订单
        if(!orderDto.getBuyerOpenid().equals(openid)) {
            log.error("查询订单 订单的openid不一致openid={}", openid);
            throw  new SellException(ResultEnum.order_owner_error);
        }
        return orderDto;
    }
}
