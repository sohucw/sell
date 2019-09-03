package com.cjw.sell.enums;

import lombok.Getter;

/**
 * @param <> 商品状态
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 17:21 2019-08-29
 */
@Getter
public enum ProductStatusEnum {
    UP(0, "在架"),
    DOWN(1, "下架"),
    ;
    private Integer code;
    private String message;
    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
