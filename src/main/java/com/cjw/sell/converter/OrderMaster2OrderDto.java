package com.cjw.sell.converter;

import com.cjw.sell.dto.OrderDto;
import com.cjw.sell.entity.OrderMaster;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 09:46 2019-09-03
 */
public class OrderMaster2OrderDto {
    public static OrderDto convert(OrderMaster orderMaster) {
        OrderDto orderDto = new OrderDto();
        BeanUtils.copyProperties(orderMaster, orderDto);
        return orderDto;
    }

    public static List<OrderDto> convert(List<OrderMaster> orderMasters) {
        return  orderMasters.stream().map(e -> convert(e)).collect(Collectors.toList());
    }
}
