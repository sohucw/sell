package com.cjw.sell.enums;

import lombok.Getter;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 21:15 2019-08-29
 */
@Getter
public enum ResultEnum {

    PRAM_ERROR(1, "参数不正确"),

    PRODUCT_NOT_EXIST(10, "商品不存在"),
    PRODUCT_STOCK_ERROR(11, "库存不足"),
    order_not_exist(12, "订单不存在"),
    order_detail_not_exist(13, "订单详情不存在"),
    order_status_error(14, "订单状态不正确"),
    order_update_error(15, "订单更新失败"),
    order_detail_empty(16, "订单详细为null"),
    order_pay_error(17, "订单支付状态不正确"),

    CART_EMPTY(18, "购物车为hull"),
    order_owner_error(19, "该订单不属于当前user"),
    wx_xp_error(20, "微信公众账号错误"),
    ;
    private Integer code;
    private String message;
    ResultEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
