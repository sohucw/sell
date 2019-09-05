package com.cjw.sell.exception;

import com.cjw.sell.enums.ResultEnum;
import lombok.Data;
import org.aspectj.lang.annotation.DeclareAnnotation;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 21:14 2019-08-29
 */
@Data
public class SellException extends RuntimeException {

    private Integer code;

    public SellException(ResultEnum resultEnum) {
        super(resultEnum.getMessage());
        this.code = resultEnum.getCode();

    }

    public SellException(Integer code, String defaultMessage) {
        super(defaultMessage);
        this.code = code;

    }
}
