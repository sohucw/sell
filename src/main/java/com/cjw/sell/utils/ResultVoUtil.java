package com.cjw.sell.utils;

import com.cjw.sell.vo.ResultVo;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 20:14 2019-08-29
 */
public class ResultVoUtil {
    public static ResultVo success(Object o) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(o);
        resultVo.setCode(0);
        resultVo.setMsg("成功");
        return  resultVo;
    }

    public static ResultVo success() {
        return  success(null);
    }

    public static ResultVo error(Integer code, String msg) {
        ResultVo resultVo = new ResultVo();
        resultVo.setData(null);
        resultVo.setCode(code);
        resultVo.setMsg(msg);
        return  resultVo;
    }
}
