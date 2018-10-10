package com.hx.admin.service.cache;

import org.apache.commons.collections.map.HashedMap;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Created by Ro on 2018/4/23.
 * 自定义session管理
 */
public class MySessionContext {

    /**
     * 用户的sessionId存储 用来管理用户的session
     * 当权限发生改变时可以清除用户session来强制用户重新登陆刷新权限
     * 只有在使用session保存用户信息
     */
    private static Map<String,HttpSession> sessionMap = new HashedMap();

    public static synchronized void AddSession(HttpSession session) {
        if (session != null) {
            sessionMap.put(session.getId(), session);
        }
    }

    public static synchronized void DelSession(HttpSession session) {
        if (session != null) {
            sessionMap.remove(session.getId());
        }
    }

    public static synchronized HttpSession getSession(String session_id) {
        if (session_id == null)
            return null;
        return sessionMap.get(session_id);
    }
}
