package com.cjw.sell.enums;

import lombok.Getter;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 20:43 2019-08-29
 */
@Getter
public enum PayStatusEnum {
    WAIT(0, "等待支付"),
    SUCCESS(1, "支付成功"),
    ;
    private Integer code;
    private String message;
    PayStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
