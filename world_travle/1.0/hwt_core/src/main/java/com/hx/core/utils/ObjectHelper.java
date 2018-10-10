package com.hx.core.utils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import com.hx.core.page.Global;

public class ObjectHelper {
	/**
	 * 判断这个Object是否为Null或长度为0
	 * @param obj
	 * @return
	 */
	public static boolean isEmpty(Object obj) {
		if (obj == null)
			return true;
		if (obj instanceof Collection) {
			return ((Collection<?>) obj).isEmpty();
		}
		if (obj instanceof String) {
			return ((String) obj).equalsIgnoreCase("null")
					| ((String) obj).trim().toString().equals("");
		}
		if (obj instanceof StringBuffer) {
			return ((StringBuffer) obj).length() == 0;
		}
		if (obj.getClass().isArray()) {
			try {
				Object[] a = (Object[]) obj;

				boolean b = true;
				for (Object o : a) {
					b = b & isEmpty(o);
					if (!b)
						break;
				}
				return b;
			} catch (ClassCastException e) {
			}
		}
		return false;
	}

	/**
	 * 判断这个Object是否不为Null或长度不为0
	 * @param obj
	 * @return
	 */
	public static boolean isNotEmpty(Object obj) {
		return !isEmpty(obj);
	}
	
	
	//定义移动端请求的所有可能类型
	private final static String[] agent = { "Android", "iPhone", "iPod","iPad", "Windows Phone", "MQQBrowser" }; 
	/**
	* 判断User-Agent 是不是来自于手机
	* @param ua
	* @return true 移动端     false-PC端
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
		
	/**
	 * 判断请求是post还是get
	 * get - true;post - false
	 * @param request
	 * @return
	 */
	public static boolean getOrPost(HttpServletRequest request){
		String method = request.getMethod();
		boolean flag = false;
		if (method != null && method.equals("GET")) {
			return true;
		}
		return flag;
	}
	
	/**
	 * 判断请求是AJAX还是同步请求
	 * AJAX - true;同步请求 - false
	 * @param request
	 * @return
	 */
	public static boolean x_requested_with(HttpServletRequest request){
		if(request.getHeader("x-requested-with") != null 
				&& request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")){
			return true;
		}else{
			return false;
		}
	}
	
	
	/** 
     * 大陆手机号码11位数，匹配格式：前三位固定格式+后8位任意数 
     * 此方法中前三位格式有： 
     * 13+任意数 
     * 15+除4的任意数 
     * 18+除1和4的任意数 
     * 17+除9的任意数 
     * 147 
     * @return true 正确     false-错误
     */  
    public static boolean isPhoneLegal(String str) {  
    	//String regExp = "^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(147))\\d{8}$";
    	String regExp = "0?(13|14|15|18|17|19)[0-9]{9}";
        Pattern p = Pattern.compile(regExp);  
        Matcher m = p.matcher(str);  
        return m.matches();  
    }
    
    /**
     * 验证邮箱
     * @param email
     * @return true 正确     false-错误
     */
    public static boolean checkEmail(String email) {
        boolean flag = false;
        try {
            String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
            Pattern regex = Pattern.compile(check);
            Matcher matcher = regex.matcher(email);
            flag = matcher.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    
    /**
     * 验证身份证号码
     * @param idCard 居民身份证号码15位或18位，最后一位可能是数字或字母
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkIdCard(String idCard) { 
        String regex = "[1-9]\\d{13,16}[a-zA-Z0-9]{1}"; 
        return Pattern.matches(regex,idCard); 
    } 
    
    /**
     * 验证整数（正整数和负整数）
     * @param digit 一位或多位0-9之间的整数
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkDigit(String digit) { 
		String regex = "\\-?[1-9]\\d+"; 
		return Pattern.matches(regex,digit); 
    } 
    
    /**
     * 验证整数和浮点数（正负整数和正负浮点数）
     * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
     * @return 验证成功返回true，验证失败返回false
     */ 
    public static boolean checkDecimals(String decimals) { 
        String regex = "\\-?[1-9]\\d+(\\.\\d+)?"; 
        return Pattern.matches(regex,decimals); 
    } 
    
    /**
	 * 密码加密 - 生成的密码 DES加密
	 * @param pass - 密码
	 * @param key - MD5 加密后的字符串 取出（8-24）
	 * @return 
	 */
	public static String passWord(String pass,String key){
		String passWored = null;
		try {
			String keyTo = MessageUtil.MD5(key, 32);
	    	byte[] bs = Des.encrypt(pass, keyTo.substring(0, 8));
	    	String dierbu2=Des.toHexString(bs).toLowerCase();
	    	passWored = MessageUtil.MD5(dierbu2, 32).toLowerCase(); //密码MD5加密
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return passWored;
	}
	
	/**
	 * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址,
	 * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
	 * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串。
	 * 如：X-Forwarded-For：192.168.1.110, 192.168.1.120, 192.168.1.130,
	 * 192.168.1.100
	 * 用户真实IP为： 192.168.1.110
	 * @param request
	 * @return
	 */
	public static String getIpAddress(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (ip.trim().equals("0:0:0:0:0:0:0:1")) {
			ip = Global.BASE_LOCALHOST_IP;
		}
		return ip;
	}
	
	/**
	 * 获取当前 - 请求的路径
	 * @return
	 */
	public static String servletPath(HttpServletRequest request){
		String url = request.getServletPath();
		return url;
	}
	
	
	public static Map<String, Object> obj2Map(Object obj) {  
		  
        Map<String, Object> map = new HashMap<String, Object>();  
        // System.out.println(obj.getClass());  
        // 获取f对象对应类中的所有属性域  
        Field[] fields = obj.getClass().getDeclaredFields();  
        for (int i = 0, len = fields.length; i < len; i++) {  
            String varName = fields[i].getName();  
            //varName = varName.toLowerCase();//将key置为小写，默认为对象的属性  
            try {  
                // 获取原来的访问控制权限  
                boolean accessFlag = fields[i].isAccessible();  
                // 修改访问控制权限  
                fields[i].setAccessible(true);  
                // 获取在对象f中属性fields[i]对应的对象中的变量  
                Object o = fields[i].get(obj);  
                
                    map.put(varName, isEmpty(o)?"":o);  
                // System.out.println("传入的对象中包含一个如下的变量：" + varName + " = " + o);  
                // 恢复访问控制权限  
                fields[i].setAccessible(accessFlag);  
            } catch (IllegalArgumentException ex) {  
                ex.printStackTrace();  
            } catch (IllegalAccessException ex) {  
                ex.printStackTrace();  
            }  
        }  
        return map;  
    }

	/**
	 * map 转 Object
	 * @param map
	 * @param clz
	 * @return
	 * @throws Exception
	 */
	public static <T>T map2Obj(Map<String,Object> map,Class<T> clz) {
		T obj = null;
		try {
			obj = clz.newInstance();
			Field[] declaredFields = obj.getClass().getDeclaredFields();
			for(Field field:declaredFields){
				//获取类的属性的修饰符 public: 1	private: 2	protected: 4 static: 8	final: 16 synchronized: 32 volatile: 64
				//transient: 128 native: 256 interface: 512	abstract: 1024	strict: 2048
				int mod = field.getModifiers();
				//排除static和final
				if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
					continue;
				}
				//设置访问权限 方便后面赋值（当为private时也可以对该属性赋值）
				field.setAccessible(true);
				field.set(obj, map.get(field.getName()));
			}
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

		return obj;
	}


	/**
	 * 判断集合是否为空
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection collection){
		if (null == collection || collection.isEmpty()){
			return true;
		}
		return false;
	}
}
