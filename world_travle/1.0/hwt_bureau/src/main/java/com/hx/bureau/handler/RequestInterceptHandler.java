package com.hx.bureau.handler;


import java.lang.reflect.Method;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.hx.bureau.service.cache.BureauUserCache;
import com.hx.bureau.service.cache.BureauUserCacheTools;
import com.hx.bureau.service.log.BureauLog;
import com.hx.bureau.validate.ValidateException;
import com.hx.bureau.validate.ValidateParam;
import com.hx.bureau.validate.ValidateService;
import com.hx.bureau.validate.ValidateUtils;
import com.hx.core.logs.annotation.Log;
import com.hx.core.logs.annotation.Log.LogType;
import com.hx.core.logs.service.LogsService;
import com.hx.core.redis.RedisCache;
import com.hx.core.utils.ObjectHelper;

/**
 * Request请求拦截
 */

public class RequestInterceptHandler extends HandlerInterceptorAdapter {

    private final static Logger log = LoggerFactory.getLogger(RequestInterceptHandler.class);
	
	@Resource
    private ValidateService validateService;
	
	
	
    /**
     * 方法前拦截
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
             return true;
         }
         final HandlerMethod handlerMethod = (HandlerMethod) handler;
         final Method method = handlerMethod.getMethod();
         final Class<?> clazz = method.getDeclaringClass();
         
         //session
         System.out.println(request.getRequestURI());
//         if(!"/back/login".equals(request.getRequestURI())) {
//            BureauUserCache bureauUserCache = BureauUserCacheTools.getSession(request);
//             if(bureauUserCache == null) {
//                 response.sendRedirect("/login.html");
//                 return false;
//             }
//         }
         // 请求参数拦截
         if (clazz.isAnnotationPresent(ValidateParam.class) || method.isAnnotationPresent(ValidateParam.class)) {
             ValidateParam vp = handlerMethod.getMethodAnnotation(ValidateParam.class);
             if(vp != null) {
                 validateService.validateService(request, vp);
             }
         }
         return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
//        if(WebUtils.isWebAJAX(request)) {
//            System.out.println("aaaaaaaaaaaaaaaaa");
//        }
    	
    	  if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
              return ;
          }
          final HandlerMethod handlerMethod = (HandlerMethod) handler;
          final Method method = handlerMethod.getMethod();
          final Class<?> clazz = method.getDeclaringClass();
          
          //session
          System.out.println(request.getRequestURI());
//          if(!"/back/login".equals(request.getRequestURI())) {
//             BureauUserCache bureauUserCache = BureauUserCacheTools.getSession(request);
//              if(bureauUserCache == null) {
//                  response.sendRedirect("/login.html");
//                  return false;
//              }
//          }
          // 请求参数拦截
          if (clazz.isAnnotationPresent(Log.class) || method.isAnnotationPresent(Log.class)) {
        	  Log log = handlerMethod.getMethodAnnotation(Log.class);
              if(log != null) {
                  String dec = log.dec();
                  Integer type = null;
                  LogType logType = log.logType();
                  switch (logType) {
	      			case LOGIN:
	      				type = 1;
	      				break;
	      			case OPERATION:
	      				type = 2;
	      				break;
	      			
	      			default:
	      				return ;
	      		 }
                 BureauUserCache bureauUserCache = BureauUserCacheTools.getSession(request);
                 BureauLog bureauLog = new BureauLog();
                 bureauLog.setBureau_id(bureauUserCache.getBureau_id());
                 bureauLog.setBureau_user_id(bureauUserCache.getBureau_user_id());
                 bureauLog.setReal_name(bureauUserCache.getReal_name());
                 bureauLog.setDec(dec);
                 bureauLog.setCreat_time(System.currentTimeMillis());
                 bureauLog.setIp_address(ObjectHelper.getIpAddress(request));
                 bureauLog.setType(type);
                 LogsService logsService = new LogsService();
                 logsService.addInsertIncId(bureauLog, "bureau_log");
              }
          }
    }
	
}
