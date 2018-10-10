package com.common.config;

import com.common.jwt.JwtConfig;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 16:16
 * @Description:
 */
@Component
@ConfigurationProperties(prefix=WechatJwtConfig.PREFIX)
public class WechatJwtConfig extends JwtConfig {
    public static final String PREFIX = "jwt";
}
