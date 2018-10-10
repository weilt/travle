package com.hx.api.handler;


import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;

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
    }
	
}
