package com.hx.core.page;

public class Global {
	//公用部分
	public static final String BASE_LOCALHOST_IP = "127.0.0.1";
	public static final String FILTER_ESCAPE_ON = "/admin/";//过滤器中不需要验证的关键字标识
	
	/* 超级管理员标识 */
	public static final int IS_SUPER_ADMIN = 1;
	// ajax
	public static final String AJAX_ACTION_FORWARD = "forward";// AJAX返回动作代码跳转
	public static final String AJAX_ACTION_REFRESH = "refresh";// AJAX返回动作代码刷新
	public static final String AJAX_ACTION_NoREFRESHANDFORWARD = "noForwardAndRefresh";// AJAX返回动作代码无跳转刷新

	// 分页
	public static final int CURRENT_PAGE = 1;// 默认当前页
	public static final int PAGE_SIZE_Five = 15;// 默认每页显示数据量
	public static final int PAGE_SIZE_Ten = 10;// 默认每页显示数据量
	public static final int PAGE_SIZE_Twelve = 12;// 默认每页显示数据量
	public static final int PAGE_SIZE_Fourteen = 14;// 默认每页显示数据量
	public static final int PAGE_SIZE_Sixteen = 16;// 默认每页显示数据量16

	// 验证码
	public static final int RANDOM_IMAGE_WIDTH = 60;// 验证码图片宽度
	public static final int RANDOM_IMAGE_HEIGHT = 20;// 验证码图片高度
	public static final int RANDOM_LENGTH = 4;// 验证码字符位数
	
	//登陆的时候设置Cookie值
	public static final String setGetCookie = "user_liuDaye_loginArrange";
	public static final String setGetCookieUserName = "setGetCookieUserName_liugang";
	public static final String setGetCookieUserPassWord = "setGetCookieUserPassWord_liugang";
}
