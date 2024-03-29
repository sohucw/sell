package com.cjw.sell.enums;

import lombok.Getter;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 20:41 2019-08-29
 */
@Getter
public enum OrderStatusEnum {

    NEW(0, "新订单"),
    FINISHED(1, "完结"),
    CANCEL(2, "已取消"),
    ;

    private Integer code;
    private String message;
    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
