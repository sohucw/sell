package com.cjw.sell.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 09:20 2019-08-30
 */
@Data
@AllArgsConstructor
public class CartDto {
    private  String productId;
    private Integer productQuantity;

}
