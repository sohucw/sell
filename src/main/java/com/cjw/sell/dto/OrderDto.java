package com.cjw.sell.dto;

import com.cjw.sell.entity.OrderDetail;
import com.cjw.sell.enums.OrderStatusEnum;
import com.cjw.sell.enums.PayStatusEnum;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 21:05 2019-08-29
 */
@Data
public class OrderDto {
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
    private Date createTime;

    /**
     * 更新时间.
     */
    private Date updateTime;
    private List<OrderDetail> orderDetailList;
}
