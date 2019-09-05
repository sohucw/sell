package com.cjw.sell.controller;

import com.cjw.sell.dto.OrderDto;
import com.cjw.sell.enums.ResultEnum;
import com.cjw.sell.exception.SellException;
import com.cjw.sell.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 17:18 2019-09-03
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private OrderService orderService;
    public void create(@RequestParam("orderId") String orderId,
                       @RequestParam("returnUrl") String returnUrl) {
        OrderDto orderDto = orderService.findOne(orderId);
        if(orderDto == null) {
            throw  new SellException(ResultEnum.order_not_exist);
        }

    }

}
