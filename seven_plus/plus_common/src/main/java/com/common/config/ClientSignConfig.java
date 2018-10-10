package com.common.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * 移动APP接口签名参数对象
 * @author
 * @date 2018/4/23
 */
@Component
@ConfigurationProperties(prefix = "mobile.client")
public class ClientSignConfig implements Serializable {

    //请求key
    private String appKey;
    //请求密钥
    private String appSecret;

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }
}
