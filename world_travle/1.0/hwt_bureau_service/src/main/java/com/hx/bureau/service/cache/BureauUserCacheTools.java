package com.hx.bureau.service.cache;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.hwt.domain.entity.admin.AdminModule;
import com.hwt.domain.entity.bureau.HxBureauModule;
import com.hx.core.exception.BaseException;
import com.hx.core.redis.RedisCache;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.Md5Utils;
/**
 * Created by Ro on 2018/4/23.
 * 用户缓存工具类
 */
public class BureauUserCacheTools {

    //后台用户缓存对象KEY
    private final static String bureauCacheKey = "bureau_CACHE";
    
    
    //后台用户模块缓存对象KEY
    private final static String bureauModuleCacheKey = "bureau_MODULE_CACHE";
   
    /**
     * 添加用户到session
     * @param userCache
     */
    public static void addSession(BureauUserCache userCache, List<HxBureauModule> listModule, HttpServletRequest request) {
        if(userCache == null) {
            throw new BaseException("用户缓存对象为空!");
        }
        //是之前的匿名session失效
        request.getSession().invalidate();
        request.getSession().setAttribute(bureauCacheKey,userCache);
        request.getSession().setAttribute(bureauModuleCacheKey,listModule);
        MySessionContext.AddSession(request.getSession());
    }
    /**
     * 获取用户session对象
     * @param request
     * @return
     */
    public static BureauUserCache getSession(HttpServletRequest request) {
        return (BureauUserCache)request.getSession().getAttribute(bureauCacheKey);
    }
    
    /**
     * 获取用户权限信息
     * @param request
     * @return
     */
    public static List<HxBureauModule> getSessionForModule(HttpServletRequest request) {
    	return (List<HxBureauModule>)request.getSession().getAttribute(bureauModuleCacheKey);
    }

    /**
     * 清除session
     * @param request
     */
    public static void removeSession(HttpServletRequest request) {
        request.getSession().invalidate();
    }
    
    
    /**
     * 添加用户缓存到Redis
     * @param userCache 登陆用户信息
     */
    public static void addRedisCache(BureauUserCache userCache) {
        if(userCache == null) {
            throw new BaseException("用户缓存对象为空!");
        }
        RedisCache.set(bureauCacheKey, JsonUtils.Bean2Json(userCache));
    }

    
    /**
     * 添加用户缓存到Redis
     * @param userCache 登陆用户信息
     * @param experice  失效时间(秒)
     */
    public static void addRedisCache(BureauUserCache bureauUserCache, int experice) {
        if(bureauUserCache == null) {
            throw new BaseException("用户缓存对象为空!");
        }
        RedisCache.set(generateRedisCacheUserKey(bureauUserCache.getBureau_id()), JsonUtils.Bean2Json(bureauUserCache),experice);
    }

    /**
     * 添加用户缓存到Redis
     * @param bureauUserId 后台用户ID
     */
    public static BureauUserCache getRedisCache(long bureauUserId) {
        String value = RedisCache.get(generateRedisCacheUserKey(bureauUserId));
        if(StringUtils.isNotBlank(value))
            return JsonUtils.json2Bean(value, BureauUserCache.class);
        return null;
    }

    /**
     * 生成用户Redis缓存key
     * @param bureauUserId
     * @return
     */
    private static String generateRedisCacheUserKey(long bureauUserId) {
        return Md5Utils.encodeMD5(bureauCacheKey + bureauUserId);
    }
}
