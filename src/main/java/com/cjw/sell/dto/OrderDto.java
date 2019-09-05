package com.cjw.sell.dto;

import com.cjw.sell.entity.OrderDetail;
import com.cjw.sell.enums.OrderStatusEnum;
import com.cjw.sell.enums.PayStatusEnum;
import com.cjw.sell.serializer.Date2LongSerializer;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 21:05 2019-08-29
 */
@Data
// @JsonInclude(JsonInclude.Include.NON_NULL)  // 为null的不返回给前端
public class OrderDto {

    private String orderId;

    /**
     * 买家名字.
     */
    private String buyerName;

    /**
     * 买家手机号.
     */
    private String buyerPhone;

    /**
     * 买家地址.
     */
    private String buyerAddress;

    /**
     * 买家微信Openid.
     */
    private String buyerOpenid;

    /**
     * 订单总金额.
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态, 默认为0新下单.
     */
    private Integer orderStatus;

    /**
     * 支付状态, 默认为0未支付.
     */
    private Integer payStatus;

    /**
     * 创建时间.
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间.
     */
    @JsonSerialize(using = Date2LongSerializer.class)
    private Date updateTime;


    private List<OrderDetail> orderDetailList;
}
