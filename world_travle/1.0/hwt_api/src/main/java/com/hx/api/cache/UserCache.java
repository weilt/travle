package com.hx.api.cache;

import java.io.Serializable;

/**
 * Created by RO on 2018/4/3.
 * 用户信息缓存
 */
public class UserCache implements Serializable{

    private static final long serialVersionUID = -157501126290479084L;
    private Long userId;        //用户ID
    private String token;       //用户token
    private String nickname;    //昵称
    private String account;     //帐号
    private String imToken;     //通信token

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getImToken() {
        return imToken;
    }

    public void setImToken(String imToken) {
        this.imToken = imToken;
    }
}
