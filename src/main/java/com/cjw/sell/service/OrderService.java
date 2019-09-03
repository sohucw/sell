package com.cjw.sell.service;

import com.cjw.sell.dto.OrderDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 21:02 2019-08-29
 */
public interface OrderService {
    // 创建订单
    OrderDto createOrder(OrderDto orderDto);
    // 查询单个订单
    OrderDto findOne(String orderId);
    // 查询订单list
    Page<OrderDto> findList(String buyerOpenId, Pageable pageable);
    // 取消订单
    OrderDto cancel(OrderDto orderDto);
    // 完结订单
    OrderDto finish(OrderDto orderDto);
    // 支付订单
    OrderDto paid(OrderDto orderDto);
}
