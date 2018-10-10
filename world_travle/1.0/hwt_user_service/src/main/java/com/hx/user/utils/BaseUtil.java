package com.hx.user.utils;

import org.apache.commons.lang3.StringUtils;

import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.core.redis.RedisCache;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.Md5Utils;

/**
 * Created by RO on 2018/4/3.
 * API接口项目常用工具类
 */
public final class BaseUtil {

    //密码加密密钥 不可更改
//    private static final String pwdSecretKey = "XwTLeEjRbkQ6eELR";

    /**
     * 用户密码加密
     * @param userSalt
     * @param pwd
     * @return
     */
    public final static String passwordEncryption(String userSalt, String pwd) {
        return Md5Utils.encodeMD5(userSalt + pwd);
    }
    
    /**
     * 根据token返回登录信息
     * @param token
     * @return
     */
    public static LoginVo getLoginVo(String token){
    	String stringLoginVo = RedisCache.get(token);
		if (StringUtils.isBlank(stringLoginVo)){
			throw new RuntimeException("SESSION_OUT");
		}
		LoginVo loginVo = GsonUtil.fromJson(stringLoginVo, LoginVo.class);
		return loginVo;
    }
    
}
