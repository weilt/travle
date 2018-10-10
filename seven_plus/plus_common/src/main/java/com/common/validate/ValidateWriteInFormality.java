package com.common.validate;

import com.common.util.ObjectHelper;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * 这个主要是拦截 - 参数的性质
 * @author LiuGang
 */
public class ValidateWriteInFormality extends HandlerInterceptorAdapter{

	@Resource
	private ValidateService validateService;

	//在请求处理之前进行调用（Controller方法调用之前）
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//判断请求是否有效
		//获取当前地址
		String URLName = request.getRequestURI(); //    返回"/error" 表示没有找到该请求
		if(ObjectHelper.isNotEmpty(URLName) && "/error".equals(URLName)){
			try {
				request.getRequestDispatcher("/500/500.html").forward(request,response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			return false;
		}

		if (!handler.getClass().isAssignableFrom(HandlerMethod.class)) {
            return true;
        }
        final HandlerMethod handlerMethod = (HandlerMethod) handler;
        final Method method = handlerMethod.getMethod();
        final Class<?> clazz = method.getDeclaringClass();

        //@ValidateWriteIn
        // 请求参数拦截 - post/get都拦截
        if (clazz.isAnnotationPresent(ValidateWriteIn.class) || method.isAnnotationPresent(ValidateWriteIn.class)) {
        	ValidateWriteIn vp = handlerMethod.getMethodAnnotation(ValidateWriteIn.class);
            if(vp != null) {
                validateService.validateService(request, vp);
            }
            return true;
        }

        //@ValidateWriteInPost
        //post请求拦截
        if (clazz.isAnnotationPresent(ValidateWriteInPost.class) || method.isAnnotationPresent(ValidateWriteInPost.class)) {
        	ValidateWriteInPost vp = handlerMethod.getMethodAnnotation(ValidateWriteInPost.class);
        	if(!ObjectHelper.getOrPost(request)){
	        	if(vp != null) {
	                validateService.validateService(request, vp);
	            }
        	}
        }
		return true;
	}

	//请求处理之后进行调用，但是在视图被渲染之前（Controller方法调用之后）
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	//在整个请求结束之后被调用，也就是在DispatcherServlet 渲染了对应的视图之后执行（主要是用于进行资源清理工作）
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
}
