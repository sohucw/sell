package com.cjw.sell.config;

import me.chanjar.weixin.mp.api.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @param <>
 * @Author: chenjianwei
 * @Description:
 * @Date: Created in 15:41 2019-09-03
 */
@Component
public class WechatMpConfig {

    @Autowired
    private WechatAccountConfig wechatAccountConfig;
    @Bean
    public WxMpService wxMpService() {
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxMpConfigStorage());
        return wxMpService;
    }
    @Bean
    public WxMpConfigStorage wxMpConfigStorage() {
        WxMpInMemoryConfigStorage wxMpConfigStorage = new WxMpInMemoryConfigStorage();
        wxMpConfigStorage.setAppId(wechatAccountConfig.getMpAppId());
        wxMpConfigStorage.setSecret(wechatAccountConfig.getMpAppSecret());
        return wxMpConfigStorage;
    }
}
