package com.common.config;

import com.common.wechat.wxpay.config.WxPayConfig;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/23 10:49
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = WechatConfig.WECHAT_PREFIX)
public class WechatPayConfig extends WxPayConfig {
}
