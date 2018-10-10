package com.common.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/21 17:20
 * @Description:
 */
@Data
@Component
@ConfigurationProperties(prefix = AliSmsConfig.SMS_PREFIX)
public class AliSmsConfig {
    public static final String SMS_PREFIX = "ali.sms";
    private String accessKeyId;
    private String accessKeySecret;
    private String signName;
    private String templateCode;
}
