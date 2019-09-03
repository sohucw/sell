package com.cjw.sell.vo;

import lombok.Data;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 19:46 2019-08-29
 */
@Data
public class ResultVo<T> {
    // 错误码
    private Integer code;
    // 提示信息
    private String msg;
    // 返回的具体内容
    private T data;

}
