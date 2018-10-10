package com.hx.api.cache;

import com.hx.core.redis.RedisCache;
import com.hx.core.utils.JsonUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by RO on 2018/3/12.
 * 验证码缓存
 */
public class CacheUtils {

    private static Logger logger = LoggerFactory.getLogger(CacheUtils.class);

    //验证码过期时间 单位秒
    private static final int vcode_overtime = 300;

    /**
     * 获取验证码
     * @param phone
     * @return
     */
    public static String getVcode(String phone) {
        return RedisCache.get(phone,RedisCache.db0);
    }

    /**
     * 添加验证码缓存
     * @param phone 手机号
     * @param vcode 验证码
     */
    public static void addVcode(String phone, String vcode) {
        RedisCache.setpar(phone,vcode,vcode_overtime,RedisCache.db0);
    }

    /**
     * 添加用户缓存
     * @param token     用户验证token
     * @param userCache 用户缓存对象
     * @return
     */
    public static void addUserCache(String token, UserCache userCache) {
        RedisCache.setpar(token, JsonUtils.Bean2Json(userCache),RedisCache.db1);
    }

    /**
     * 获取缓存
     * @param token
     * @return
     */
    public static UserCache getUserCache(String token) {
        String cache = RedisCache.get(token,RedisCache.db1);
        if(StringUtils.isNotBlank(cache))  {
            try {
                return JsonUtils.json2Bean(cache,UserCache.class);
            }catch (Exception e) {
                logger.error("用户缓存信息解析错误! error is position :: {}, method :: getUserCache",CacheUtils.class.getCanonicalName());
            }
        }
        return null;
    }
}
