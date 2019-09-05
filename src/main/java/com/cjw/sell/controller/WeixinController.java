package com.cjw.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 15:24 2019-09-03
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @GetMapping("auth")
    public void auth(@RequestParam("code") String code) {
        log.info("进入auth方法 code={}", code);

        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";
        RestTemplate restTemplate = new RestTemplate();
        String res = restTemplate.getForObject(url, String.class);
        log.info("forObject=>", res);

    }
}