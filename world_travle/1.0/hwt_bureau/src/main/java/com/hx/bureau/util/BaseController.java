package com.hx.bureau.util;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.hx.bureau.service.cache.BureauUserCache;
import com.hx.bureau.service.cache.BureauUserCacheTools;

/**
 * 公共Controller类
 * @author JIAO_LIU_BABA
 */
public abstract class BaseController {
	public static final Logger log = LoggerFactory.getLogger(BaseController.class);
	@Autowired
	protected HttpServletRequest request;
	@Autowired
	protected HttpServletResponse response;
	@Autowired
	protected HttpSession session;
	
	protected Map<String, Object> map = new HashMap<>();
    /**
	 * 返回json字符串
	 * @param json
	 */
//	protected void responseJson(Object o) {
//		response.setContentType("text/plain;charset=UTF-8");
//		PrintWriter pw = null;
//		try {
//			pw = response.getWriter();
//			pw.write(GsonUtil.toJson(o, Object.class));
//		} catch (IOException e) {
//			System.out.println("发送响应信息错误  ：" + e.getMessage());
//			e.printStackTrace();
//		}
//	}
	
	/**
	 * 返回后台登陆的用户
	 * @return
	 */
	protected BureauUserCache sessionUser(){
		BureauUserCache bureauUserCache = BureauUserCacheTools.getSession(request);
		return bureauUserCache;
	}
	
	
	
	/**
	 * Stores an attribute in this request
	 * @param name a String specifying the name of the attribute
	 * @param value the Object to be stored
	 */
	protected void setAttr(String name, Object value) {
		request.setAttribute(name, value);
	}
	
	/**
	 * Stores attributes in this request, key of the map as attribute name and value of the map as attribute value
	 * @param attrMap key and value as attribute of the map to be stored
	 */
	protected void setAttrs(Map<String, Object> attrMap) {
		for (Map.Entry<String, Object> entry : attrMap.entrySet())
			request.setAttribute(entry.getKey(), entry.getValue());
	}
	
	/**
	 * Returns the value of a request parameter as a String, or null if the parameter does not exist.
	 * <p>
	 * You should only use this method when you are sure the parameter has only one value. If the 
	 * parameter might have more than one value, use getParaValues(java.lang.String). 
	 * <p>
	 * If you use this method with a multivalued parameter, the value returned is equal to the first 
	 * value in the array returned by getParameterValues.
	 * @param name a String specifying the name of the parameter
	 * @return a String representing the single value of the parameter
	 */
	protected String getPara(String name) {
		return request.getParameter(name);
	}
	
	/**
	 * Returns the value of a request parameter as a String, or default value if the parameter does not exist.
	 * @param name a String specifying the name of the parameter
	 * @param defaultValue a String value be returned when the value of parameter is null
	 * @return a String representing the single value of the parameter
	 */
	protected String getPara(String name, String defaultValue) {
		String result = request.getParameter(name);
		return result != null && !"".equals(result) ? result : defaultValue;
	}
	
	/**
	 * Returns an array of String objects containing all of the values the given request 
	 * parameter has, or null if the parameter does not exist. If the parameter has a 
	 * single value, the array has a length of 1.
	 * @param name a String containing the name of the parameter whose value is requested
	 * @return an array of String objects containing the parameter's values
	 */
	protected String[] getParaValues(String name) {
		return request.getParameterValues(name);
	}
	
	
	
	
	/**
	 * 获取session
	 * @return
	 */
	protected HttpSession getSession(){
		return request.getSession();
	}
	
	/**
	 * Set Cookie.
	 * @param name cookie name
	 * @param value cookie value
	 * @param maxAgeInSeconds -1: clear cookie when close browser. 0: clear cookie immediately.  n>0 : max age in n seconds.
	 */
	protected void setCookie(String name, String value, int maxAgeInSeconds) {
		 doSetCookie(name, value, maxAgeInSeconds, null, null, null);
	}
	
	/**
	 * Get cookie value by cookie name.
	 */
	public String getCookie(String name) {
		Cookie cookie = getCookieObject(name);
		return cookie != null ? cookie.getValue() : null;
	}
	
	/**
	 * Get cookie object by cookie name.
	 */
	public Cookie getCookieObject(String name) {
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
			for (Cookie cookie : cookies)
				if (cookie.getName().equals(name))
					return cookie;
		return null;
	}
	
	/**
	 * Remove Cookie.
	 */
	protected void removeCookie(String name) {
		 doSetCookie(name, null, 0, null, null, null);
	}
	/**
	 * 设置cookie
	 * @param name
	 * @param value
	 * @param maxAgeInSeconds
	 * @param path
	 * @param domain
	 * @param isHttpOnly
	 */
	private void doSetCookie(String name, String value, int maxAgeInSeconds, String path, String domain, Boolean isHttpOnly) {
		Cookie cookie = new Cookie(name.trim(), value);
		cookie.setMaxAge(maxAgeInSeconds);
//		// set the default path value to "/"
		if (path == null) {
			path = "/";
		}
		cookie.setPath(path);
		if (domain != null) {
			cookie.setDomain(domain);
		}
		if (isHttpOnly != null) {
			cookie.setHttpOnly(isHttpOnly);
		}
		response.addCookie(cookie);
	}
}
