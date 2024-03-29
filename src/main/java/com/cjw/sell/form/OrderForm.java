package com.cjw.sell.form;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 10:34 2019-09-03
 */
@Data
public class OrderForm {
    @NotEmpty(message = "买家姓名必填")
    private String name;
    @NotEmpty(message = "手机号必填")
    private String phone;
    @NotEmpty(message = "地址必填")
    private String address;
    @NotEmpty(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购物车不能为空")
    private String items;


}
