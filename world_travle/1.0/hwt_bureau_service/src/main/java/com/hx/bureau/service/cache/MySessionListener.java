package com.hx.bureau.service.cache;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Ro on 2018/4/23.
 * Session监听器
 */
@WebListener
public class MySessionListener implements HttpSessionListener {

    /**
     * 创建session
     * @param httpSessionEvent
     */
    @Override
    public void sessionCreated(HttpSessionEvent httpSessionEvent) {
        System.out.printf(httpSessionEvent.getSession().getId() + "---------------session add");
        MySessionContext.AddSession(httpSessionEvent.getSession());
    }

    /**
     * 销毁session
     * @param httpSessionEvent
     */
    @Override
    public void sessionDestroyed(HttpSessionEvent httpSessionEvent) {
        System.out.printf(httpSessionEvent.getSession().getId() + "---------------session sessionDestroyed");
        HttpSession session = httpSessionEvent.getSession();
        MySessionContext.DelSession(session);
    }
}
