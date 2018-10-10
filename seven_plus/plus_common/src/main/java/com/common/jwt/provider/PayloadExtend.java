package com.common.jwt.provider;

/***
 *
 *
 * @date 2018/8/8.
 */
public interface PayloadExtend extends Payload {

    void setRemoteIp(String remoteIp);


    void setUserAgent(String userAgent);
}
