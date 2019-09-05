package com.cjw.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 15:46 2019-09-03
 */
@Component
@Data
public class WechatPayConfig {
    @Autowired
    private  WechatAccountConfig wechatAccountConfig;

    @Bean
    public BestPayServiceImpl bestPayService() {
        // WxPayH5Config wxPayH5Config = new WxPayH5Config();
        // wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());
        // wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        // wxPayH5Config.setMchId(wechatAccountConfig.getMchId());
        // wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());
        // wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();
        bestPayService.setWxPayH5Config(wxPayH5Config());
        return bestPayService;
    }
    @Bean
    public WxPayH5Config wxPayH5Config() {
        WxPayH5Config wxPayH5Config = new WxPayH5Config();
        wxPayH5Config.setAppId(wechatAccountConfig.getMpAppId());
        wxPayH5Config.setAppSecret(wechatAccountConfig.getMpAppSecret());
        wxPayH5Config.setMchId(wechatAccountConfig.getMchId());
        wxPayH5Config.setMchKey(wechatAccountConfig.getMchKey());
        wxPayH5Config.setKeyPath(wechatAccountConfig.getKeyPath());
        return wxPayH5Config;
    }
}
