//package com.hx.api.config;
//import java.io.IOException;
//import java.util.Iterator;
//import java.util.List;
//
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import com.hwt.domain.entity.admin.AdminModule;
//import com.hwt.domain.entity.version.HxVersion;
//import com.hwt.domain.mapper.version.HxVersionMapper;
//import com.hx.core.exception.BaseException;
//import com.hx.core.redis.RedisCache;
//import com.hx.core.systemconfig.HXSystemConfig;
//import com.hx.core.utils.GsonUtil;
//import com.hx.core.utils.JsonUtils;
//import com.hx.core.utils.ObjectHelper;
//
//
//
///**
// * 后台管理系统session监听
// * @author JIAO_LIU_BABA
// */
//@WebFilter(urlPatterns = {"/*"})
//public class HXFilter implements Filter{
//	
//	@Autowired
//	private HxVersionMapper hxVersionMapper;
//	
//	private static final Logger log = LoggerFactory.getLogger(HXFilter.class);
//	@Override
//	public void init(FilterConfig filterConfig) throws ServletException {
//	}
//
//	@SuppressWarnings("unchecked")
//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		
//		HttpServletRequest req = (HttpServletRequest) request;
//        HttpServletResponse res = (HttpServletResponse) response;
//        
//        res.setHeader("Content-type", "text/html;charset=UTF-8");  
//		//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
//        response.setCharacterEncoding("UTF-8");  
//        
//        String url = req.getRequestURI();
//        log.info(" doFilter uri : " + url);
//        
//        boolean boo = boo(url); //包含的数据直接过滤
//		if(boo){
//			chain.doFilter(request, response);
//			return;
//		}
//        
//		//判断版本号
//		String HX_VSERSIONs = RedisCache.get(HXSystemConfig.HX_VSERSION, RedisCache.db15);
//		HxVersion json2Bean = JsonUtils.json2Bean(HX_VSERSIONs, HxVersion.class);
//		
//	}
//
//	@Override
//	public void destroy() {
//		
//	}
//	
//	//过滤的请求地址 - session值的数据
//	private String[] filter = {"/admin/user/index","/admin/user/left"};
//	//过滤的带后缀的地址
//	private String[] filter_to = {".html",".js",".png",".PNG",".jsp",".htm",".jpg",".gif",".text"};
//	//请求过滤信息
//	public boolean booSession(String url){
//		for(int i = 0;i<filter.length;i++){
//			if(url.equals(filter[i])){
//				return true;
//			}
//		}
//		return false;
//	}
//	//请求过滤信息
//	public boolean boo(String url){
//		for(int i = 0;i < filter_to.length;i++){
//			if(url.contains(filter_to[i])){
//				return true;
//			}
//		}
//		return false;
//	}
//}
