package com.cjw.sell.converter;

import com.cjw.sell.dto.OrderDto;
import com.cjw.sell.entity.OrderDetail;
import com.cjw.sell.enums.ResultEnum;
import com.cjw.sell.exception.SellException;
import com.cjw.sell.form.OrderForm;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 10:45 2019-09-03
 */
@Slf4j
public class OrderForm2OrderDto {
    public static OrderDto convert(OrderForm orderForm) {
        Gson gson = new Gson();
        OrderDto orderDto = new OrderDto();

        orderDto.setBuyerName(orderForm.getName());
        orderDto.setBuyerPhone(orderForm.getPhone());
        orderDto.setBuyerAddress(orderForm.getAddress());
        List<OrderDetail> orderDetailList = new ArrayList<>();
        try {
            gson.fromJson(orderForm.getItems(), new TypeToken<List<OrderDetail>>(){}.getType());
        } catch (Exception e) {
            log.error("对象转换错误 string={}", orderForm.getItems());
            throw  new SellException(ResultEnum.PRAM_ERROR);
        }
        orderDto.setOrderDetailList(orderDetailList);
        return orderDto;
    }
}
