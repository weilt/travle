package com.hx.core.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Locale;

/**
 *
 */
public class WebUtils {
	private static final Logger log = LoggerFactory.getLogger(WebUtils.class);

	private static final String DEFAULT_CHARACTER_ENCODING = "ISO-8859-1";

	/**
	 * 应用请求路径，去掉项目名称和和 ?的query参数 myproject/xxx?mmm=3 return /xxx
	 *
	 * @param request
	 *            current HTTP request
	 * @return the path within the web application
	 */
	public static String getPathWithinApplication(HttpServletRequest request) {
		String contextPath = getContextPath(request);
		String requestUri = getRequestURI(request);
		if (StringUtils.startsWithIgnoreCase(requestUri, contextPath)) {
			// Normal case: URI contains context path.
			String path = requestUri.substring(contextPath.length());
			return (StringUtils.isNotEmpty(path) ? path : "/");
		} else {
			// Special case: rather unusual.
			return requestUri;
		}
	}

	/**
	 * /myproject/xxx?iii=3 return /myproject/xxx 去掉?的query参数
	 *
	 * @param request
	 *            current HTTP request
	 * @return the request URI
	 */
	public static String getRequestURI(HttpServletRequest request) {
		String uri = request.getRequestURI();
		return normalize(decodeAndCleanUriString(request, uri));
	}

	/**
	 * 标准化 normalize Normalize operations were was happily taken from
	 * org.apache.catalina.util.RequestUtil in Tomcat trunk, r939305
	 *
	 * @param path
	 *            Relative path to be normalized
	 * @return normalized path
	 */
	public static String normalize(String path) {
		return normalize(path, true);
	}

	/**
	 * Normalize operations were was happily taken from
	 * org.apache.catalina.util.RequestUtil in Tomcat trunk, r939305
	 *
	 * @param path
	 *            Relative path to be normalized
	 * @param replaceBackSlash
	 *            Should '\\' be replaced with '/'
	 * @return normalized path
	 */
	private static String normalize(String path, boolean replaceBackSlash) {

		if (path == null)
			return null;

		// Create a place for the normalized path
		String normalized = path;

		if (replaceBackSlash && normalized.indexOf('\\') >= 0)
			normalized = normalized.replace('\\', '/');

		if (normalized.equals("/."))
			return "/";

		// Add a leading "/" if necessary
		if (!normalized.startsWith("/"))
			normalized = "/" + normalized;

		// Resolve occurrences of "//" in the normalized path
		while (true) {
			int index = normalized.indexOf("//");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 1);
		}

		// Resolve occurrences of "/./" in the normalized path
		while (true) {
			int index = normalized.indexOf("/./");
			if (index < 0)
				break;
			normalized = normalized.substring(0, index) + normalized.substring(index + 2);
		}

		// Resolve occurrences of "/../" in the normalized path
		while (true) {
			int index = normalized.indexOf("/../");
			if (index < 0)
				break;
			if (index == 0)
				return (null); // Trying to go outside our context
			int index2 = normalized.lastIndexOf('/', index - 1);
			normalized = normalized.substring(0, index2) + normalized.substring(index + 3);
		}

		// Return the normalized path that we have completed
		return (normalized);

	}

	/**
	 * Decode the supplied URI string and strips any extraneous portion after a
	 * ';'.
	 *
	 * @param request
	 *            the incoming HttpServletRequest
	 * @param uri
	 *            the application's URI string
	 * @return the supplied URI string stripped of any extraneous portion after
	 *         a ';'.
	 */
	private static String decodeAndCleanUriString(HttpServletRequest request, String uri) {
		uri = decodeRequestString(request, uri);
		int semicolonIndex = uri.indexOf(';');
		return (semicolonIndex != -1 ? uri.substring(0, semicolonIndex) : uri);
	}

	/**
	 * /myproject/xxx?iii=2 return /myproject
	 *
	 * @param request
	 *            current HTTP request
	 * @return the context path
	 */
	public static String getContextPath(HttpServletRequest request) {
		String contextPath = request.getContextPath();
		if ("/".equals(contextPath)) {
			// Invalid case, but happens for includes on Jetty: silently adapt
			// it.
			contextPath = "";
		}
		return decodeRequestString(request, contextPath);
	}

	/**
	 * @param request
	 *            current HTTP request
	 * @param source
	 *            the String to decode
	 * @return the decoded String
	 * @see #DEFAULT_CHARACTER_ENCODING
	 * @see URLDecoder#decode(String, String)
	 * @see URLDecoder#decode(String)
	 */
	@SuppressWarnings({ "deprecation" })
	public static String decodeRequestString(HttpServletRequest request, String source) {
		String enc = determineEncoding(request);
		try {
			return URLDecoder.decode(source, enc);
		} catch (UnsupportedEncodingException ex) {
			if (log.isWarnEnabled()) {
				log.warn("Could not decode request string [" + source + "] with encoding '" + enc
						+ "': falling back to platform default encoding; exception message: " + ex.getMessage());
			}
			return URLDecoder.decode(source);
		}
	}

	/**
	 * 确定编码
	 *
	 * @param request
	 * @return
	 */
	protected static String determineEncoding(HttpServletRequest request) {
		String enc = request.getCharacterEncoding();
		if (enc == null) {
			enc = DEFAULT_CHARACTER_ENCODING;
		}
		return enc;
	}

	public static boolean isWeb() {
		return true;
	}

	public static boolean isHttp() {
		return true;
	}

	/**
	 *
	 */
	public static HttpServletRequest toHttp(ServletRequest request) {
		return (HttpServletRequest) request;
	}

	/**
	 *
	 */
	public static HttpServletResponse toHttp(ServletResponse response) {
		return (HttpServletResponse) response;
	}

	/**
	 * 设定下列为true true t 1 enabled y yes n
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static boolean isTrue(ServletRequest request, String paramName) {
		String value = getCleanParam(request, paramName);
		return value != null && (value.equalsIgnoreCase("true") || value.equalsIgnoreCase("t")
				|| value.equalsIgnoreCase("1") || value.equalsIgnoreCase("enabled") || value.equalsIgnoreCase("y")
				|| value.equalsIgnoreCase("yes") || value.equalsIgnoreCase("on"));
	}

	/**
	 * 获取参数值 , 去掉空格，null 转换成 "" StringUtils.trimToEmpty(null) = ""
	 * StringUtils.trimToEmpty("") = "" StringUtils.trimToEmpty("     ") = ""
	 * StringUtils.trimToEmpty("abc") = "abc" StringUtils.trimToEmpty(
	 * "    abc    ") = "abc"
	 *
	 * @param request
	 * @param paramName
	 * @return
	 */
	public static String getCleanParam(ServletRequest request, String paramName) {
		return StringUtils.trimToEmpty(request.getParameter(paramName));
	}

	/**
	 * 改请求是否来自web的ajax请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isWebAJAX(HttpServletRequest request) {
		String referer = request.getHeader("referer");

		log.info("pathWithinApplication {}", WebUtils.getPathWithinApplication(request));

		log.debug("referer:" + referer);
		boolean isWebAJAX = false;
		try {
			isWebAJAX = (referer != null && !referer.equals("") && referer.indexOf("json") > 1);

			if (!isWebAJAX) {
				String x_requested_with = request.getHeader("x-requested-with");
				log.info("x_requested_with {}", request.getHeader("x-requested-with"));
				if ("XMLHttpRequest".equalsIgnoreCase(x_requested_with)) {
					isWebAJAX = true;
				}
			}
			//
			// x-requested-with---XMLHttpRequest
			// 是ajax

		} catch (Exception e) {
		}
		log.info("is ajax {}", isWebAJAX);
		return isWebAJAX;
	}

	/**
	 * 改请求是否来自移动端的请求
	 * 
	 * @param request
	 * @return
	 */
	public static boolean isMobile(HttpServletRequest request) {
		boolean isMobile = false;
		log.info("pathWithinApplication {} ", WebUtils.getPathWithinApplication(request));
		String agent = request.getHeader(user_agent);
		log.debug("agent:" + agent);
		/*if (StringUtils.isNoneBlank(agent)) {
			for (int i = 0; i < mobiles.length; i++) {
				String u = mobiles[i];
				if (agent.startsWith(u)) {
					isMobile = true;
				}
			}
		}

		log.info("is Mobile {} :", isMobile);*/
		
		return isMobile;
	}

	// 定义移动端类型
	private final static String[] agent = { "Android", "iPhone", "iPod", "iPad", "Windows Phone", "MQQBrowser","okhttp" };

	/**
	 * 
	 * 判断User-Agent 是不是来自于手机
	 * 
	 * @param request
	 * 
	 * @return
	 * 
	 */

	public static boolean checkAgentIsMobile(HttpServletRequest request) {
		
		String ua = request.getHeader("User-Agent");

		boolean flag = false;

		if (!ua.contains("Windows NT") || (ua.contains("Windows NT") && ua.contains("compatible; MSIE 9.0;"))) {

			// 排除 苹果桌面系统

			if (!ua.contains("Windows NT") && !ua.contains("Macintosh")) {

				for (String item : agent) {

					if (ua.contains(item)) {

						flag = true;

						break;

					}

				}

			}

		}

		return flag;

	}

	private final static String user_agent = "user-agent";
	private final static String[] mobiles = new String[] { "Apache-HttpClient" };

	/**
	 * 获取head值 , 去掉空格，null 转换成 ""
	 *
	 * StringUtils.trimToEmpty(null) = "" StringUtils.trimToEmpty("") = ""
	 * StringUtils.trimToEmpty("     ") = "" StringUtils.trimToEmpty("abc") =
	 * "abc" StringUtils.trimToEmpty("    abc    ") = "abc"
	 *
	 * @param request
	 * @param headName
	 * @return
	 */
	public static String getCleanHead(HttpServletRequest request, String headName) {
		return StringUtils.trimToEmpty(request.getHeader(headName));
	}

	public static final String DEFAULT_METHOD_PARAM = "_method";

	/**
	 * 获得定制的
	 * 
	 * @param request
	 * @return
	 */
	public static String getCustomHttpMethod(HttpServletRequest request, String customMethodParameter) {

		String method = request.getMethod(); // http传递的method

		String customHttpMethod = method; // 传递回去解析过后的

		if (StringUtils.isBlank(customMethodParameter)) {
			customMethodParameter = DEFAULT_METHOD_PARAM;
		}

		String customMethod = request.getParameter(customMethodParameter); // 自定义的

		if (StringUtils.isNoneEmpty(customMethod)) {

			String _method = customMethod.toUpperCase(Locale.ENGLISH);
			if ("POST".equals(method) && "PUT".equals(_method)) {
				customHttpMethod = _method;
			} else if ("GET".equals(method) && "DELETE".equals(_method)) {
				customHttpMethod = _method;
			}
		}
		return customHttpMethod;

	}

	/**
	 * 获取登录的IP地址
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/***
	 * 根据平台来源反回对应的数据 目前只分辨出是否是:Android,IOS,Windows,Other表示其他不定的类型
	 * 
	 * @param request
	 * @return
	 */
	public static String getRequestSource(HttpServletRequest request) {
		// 获取访问的类型
		String user_Type = request.getHeader("User-Agent");

		if (user_Type.contains("Windows"))
			return "pc";
		else if (user_Type.contains("Android") || user_Type.contains("okhttp"))
			return "Android";
		else if (user_Type.contains("Mac"))
			return "IOS";
		else
			return "Other";
	}

	public final static String PC = "3";
	public final static String ANDROID = "1";
	public final static String IOS = "2";
	public final static String OTHER = "0";


	/***
	 * 根据平台来源反回对应的数据 目前只分辨出是否是:Android,IOS,Windows,Other表示其他不定的类型
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestSourceState(HttpServletRequest request) {
		// 获取访问的类型
		String user_Type = request.getHeader("User-Agent");

		if (user_Type.contains("Windows"))
			return WebUtils.PC;
		else if (user_Type.contains("Android") || user_Type.contains("okhttp"))
			return WebUtils.ANDROID;
		else if (user_Type.contains("iPhone") || user_Type.contains("iPad") || user_Type.contains("iPod"))
			return WebUtils.IOS;
		else
			return WebUtils.OTHER;
	}

	
    /** 
     * 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址; 
     *  
     * @param request 
     * @return 
     * @throws IOException 
     */  
    public final static String getIpAddress(HttpServletRequest request){
        // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  
  
        String ip = request.getHeader("X-Forwarded-For");  
        if (log.isInfoEnabled()) {  
            log.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);  
        }  
  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
                if (log.isInfoEnabled()) {  
                    log.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
                if (log.isInfoEnabled()) {  
                    log.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
                if (log.isInfoEnabled()) {  
                    log.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
                if (log.isInfoEnabled()) {  
                    log.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
                if (log.isInfoEnabled()) {  
                    log.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);  
                }  
            }  
        } else if (ip.length() > 15) {  
            String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
        }  
        return ip;  
    }

    /**
     * 发起http请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return
     */
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr){
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            httpUrlConn.connect();

            // 当有数据需要提交时
            if (StringUtils.isNotBlank(outputStr)) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();

            log.info("HttpConnection result info : {}",buffer.toString());
        } catch (ConnectException ce) {
            log.error("HttpConnection server connection timed out.");
        } catch (Exception e) {
            log.error("https request error:{}", e);
        } finally {

		}
		return buffer.toString();
    }

}


