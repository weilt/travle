package com.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 14:22
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = WechatConfig.WECHAT_PREFIX)
public class WechatConfig {
    public static final String WECHAT_PREFIX = "wechat";

    private String appId;
    private String secret;
    private String mchId;
    private String mchKey;
    private String notifyUrl;
}
