package com.hwt.domain.entity.user;

import java.io.Serializable;

/**
 * Created by RO on 2018/3/28.
 * test用户
 */
public class TestUser implements Serializable {
    
    private Long userId;
    private String uname;

    public TestUser(Long userId, String uname) {
        this.userId = userId;
        this.uname = uname;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }
}
