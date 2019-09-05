package com.cjw.sell.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 15:46 2019-09-03
 */
@Component
@Data
@ConfigurationProperties(prefix = "wechat")
public class WechatAccountConfig {
    private String mpAppId;
    private String mpAppSecret;
    private String mchId; // 商户号
    private String mchKey; // 商户密钥
    private String keyPath; // 商户证书路径


}
