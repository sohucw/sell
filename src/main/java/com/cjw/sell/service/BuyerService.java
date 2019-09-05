package com.cjw.sell.service;

import com.cjw.sell.dto.OrderDto;

/**
 * @param <>买家
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 14:03 2019-09-03
 */
public interface BuyerService {
    // 查询一个订单
    OrderDto findOrderOne(String openid, String orderId);

    // 取消订单
    OrderDto cancelOrder(String openid, String orderId);

}
