package com.cjw.sell.service;

import com.cjw.sell.dto.OrderDto;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 17:21 2019-09-03
 */
public interface PayService {
    void create(OrderDto orderDto);
}
