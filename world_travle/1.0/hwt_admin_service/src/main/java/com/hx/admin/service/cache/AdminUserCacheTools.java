package com.hx.admin.service.cache;

import com.hwt.domain.entity.admin.AdminModule;
import com.hx.core.exception.BaseException;
import com.hx.core.redis.RedisCache;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.Md5Utils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
/**
 * Created by Ro on 2018/4/23.
 * 用户缓存工具类
 */
public class AdminUserCacheTools {

    //后台用户缓存对象KEY
    private final static String adminUserCacheKey = "ADMIN_USER_CACHE";
    
    //后台用户模块缓存对象KEY
    private final static String adminModuleCacheKey = "ADMIN_MODULE_CACHE";

    /**
     * 添加用户到session
     * @param userCache
     */
    public static void addSession(AdminUserCache userCache, List<AdminModule> listModule,HttpServletRequest request) {
        if(userCache == null) {
            throw new BaseException("用户缓存对象为空!");
        }
        //是之前的匿名session失效
        request.getSession().invalidate();
        request.getSession().setAttribute(adminUserCacheKey,userCache);
        request.getSession().setAttribute(adminModuleCacheKey,listModule);
        MySessionContext.AddSession(request.getSession());
    }

    /**
     * 获取用户session对象
     * @param request
     * @return
     */
    public static AdminUserCache getSession(HttpServletRequest request) {
        return (AdminUserCache)request.getSession().getAttribute(adminUserCacheKey);
    }
    /**
     * 获取用户权限信息
     * @param request
     * @return
     */
    public static List<AdminModule> getSessionForModule(HttpServletRequest request) {
    	return (List<AdminModule>)request.getSession().getAttribute(adminModuleCacheKey);
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
    public static void addRedisCache(AdminUserCache userCache) {
        if(userCache == null) {
            throw new BaseException("用户缓存对象为空!");
        }
        RedisCache.set(adminUserCacheKey, JsonUtils.Bean2Json(userCache));
    }

    /**
     * 添加用户缓存到Redis
     * @param userCache 登陆用户信息
     * @param experice  失效时间(秒)
     */
    public static void addRedisCache(AdminUserCache userCache, int experice) {
        if(userCache == null) {
            throw new BaseException("用户缓存对象为空!");
        }
        RedisCache.set(generateRedisCacheUserKey(userCache.getAdminUserId()), JsonUtils.Bean2Json(userCache),experice);
    }

    /**
     * 添加用户缓存到Redis
     * @param adminUserId 后台用户ID
     */
    public static AdminUserCache getRedisCache(long adminUserId) {
        String value = RedisCache.get(generateRedisCacheUserKey(adminUserId));
        if(StringUtils.isNotBlank(value))
            return JsonUtils.json2Bean(value, AdminUserCache.class);
        return null;
    }

    /**
     * 生成用户Redis缓存key
     * @param adminUserId
     * @return
     */
    private static String generateRedisCacheUserKey(long adminUserId) {
        return Md5Utils.encodeMD5(adminUserCacheKey + adminUserId);
    }
}
