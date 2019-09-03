package com.cjw.sell.utils;

import java.util.Random;

/**
 * @param <T>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 21:26 2019-08-29
 */
public class KeyUtil {
    /**
     * 生产主键
     *
     * @return
     */
    public static synchronized String genUniqueKey() {
        Random random = new Random();

        Integer result = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(result);

    }
}
