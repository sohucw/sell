package com.cjw.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 22:38 2019-08-28
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1() {
        String name = "chjenjianwei";
        log.info("name: {}", name);

    }
}
