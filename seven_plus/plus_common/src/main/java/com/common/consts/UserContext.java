package com.common.consts;

import com.common.jwt.provider.Payload;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 14:51
 * @Description:
 */
@Data
public class UserContext implements Payload,Serializable {
    public final static String CONTEXT_NAME = "userContext";

    private Long id;

    private String openId;

    @Override
    public String key() {
        return CONTEXT_NAME;
    }
}
