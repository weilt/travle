package com.hx.admin.config;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hwt.domain.entity.admin.AdminModule;
import com.hx.admin.base.ResultCode;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.service.cache.AdminUserCache;
import com.hx.admin.service.cache.AdminUserCacheTools;
import com.hx.admin.service.utils.AdminConfigKey;
import com.hx.core.exception.BaseException;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;



/**
 * 后台管理系统session监听
 * @author JIAO_LIU_BABA
 */
@WebFilter(urlPatterns = {"/admin/*","/adminHx/*"})
public class AdminFilter implements Filter{
	private static final Logger log = LoggerFactory.getLogger(AdminFilter.class);
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        
        res.setHeader("Content-type", "text/html;charset=UTF-8");  
		//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
        response.setCharacterEncoding("UTF-8");  
        
        String url = req.getRequestURI();
        log.info(" doFilter uri : " + url);
        
        boolean boo = boo(url); //包含的数据直接过滤
		if(boo){
			chain.doFilter(request, response);
			return;
		}
        
		AdminUserCache adminUserCache = AdminUserCacheTools.getSession(req);
    	if(ObjectHelper.isEmpty(adminUserCache)) {
    		if(ObjectHelper.x_requested_with(req)){
    			//异步请求
    			//res.getWriter().write("<html><script>window.open('/back','_top')</script></html>");
    			res.getWriter().write(JsonUtils.Bean2Json(new ResultEntity(ResultCode.SESSION_OUT)));
    		}else{
    			//同步请求
    			res.getWriter().write("<html><script>window.open('/back','_top')</script></html>");
    		}
    		return;
        }
    	//验证权限 - 角色为1的是超级管理员  - 直接滤过权限
        if(adminUserCache.getRoleId().equals(AdminConfigKey.IS_SUPER_ADMIN)){
        	chain.doFilter(request, response);
         	return;
        }
    	
        List<AdminModule> listModule = AdminUserCacheTools.getSessionForModule(req);
    	
		if(ObjectHelper.isEmpty(listModule)){
			if(ObjectHelper.x_requested_with(req)){
				String all = "无操作权限";
    			//异步请求 - 无操作权限
				if (ObjectHelper.getOrPost(req)){
					res.sendRedirect("/powerExpire");//无权限页面
				}else{
					response.getWriter().write(GsonUtil.toJson(new ResultEntity(ResultCode.ERROR.code,all)));
				}
    		}else{
    			res.sendRedirect("/powerExpire");
    			
    		}
			return;
		}else{
			boolean b = booSession(url); //强制过滤信息
			if(b){
				chain.doFilter(request, response);
				return;
			}
			for (AdminModule adminModule : listModule) {
				if(url.equals(("/"+adminModule.getUrl()))){
					b = true;
					break;
				}
			}
			
			if(b){
				chain.doFilter(request, response);
				return;
			}
			if(ObjectHelper.x_requested_with(req)){
				String all = "无操作权限";
    			//异步请求 - 无操作权限
				if (ObjectHelper.getOrPost(req)){
					res.sendRedirect("/powerExpire");//无权限页面
				}else{
					response.getWriter().write(GsonUtil.toJson(new ResultEntity(ResultCode.ERROR.code,all)));
				}
				
	            
    		}else{
    			//同步请求
    			res.sendRedirect("/powerExpire");//无权限页面
    		}
		}
	}

	@Override
	public void destroy() {
		
	}
	
	//过滤的请求地址 - session值的数据
	private String[] filter = {"/admin/user/index","/admin/user/left"};
	//过滤的带后缀的地址
	private String[] filter_to = {".html",".js",".png",".PNG",".jsp",".htm",".jpg",".gif",".text"};
	//请求过滤信息
	public boolean booSession(String url){
		for(int i = 0;i<filter.length;i++){
			if(url.equals(filter[i])){
				return true;
			}
		}
		return false;
	}
	//请求过滤信息
	public boolean boo(String url){
		for(int i = 0;i < filter_to.length;i++){
			if(url.contains(filter_to[i])){
				return true;
			}
		}
		return false;
	}
}
