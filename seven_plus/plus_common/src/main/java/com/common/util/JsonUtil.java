package com.common.util;

import com.common.consts.Global;
import com.common.result.ActionAndUrl;
import com.common.result.JsonResult;
import com.common.result.ResultCode;
import com.common.result.ResultEntity;

/**
 * 后台返回的数据信息
 * @author LiuGang
 */
public class JsonUtil {
	/**
	 * (int state, String msg, String action, String url)
	 * 方法说明：创建json返回数据 正确状态 自定义 200
	 * @param msg - 显示的信息
	 * @param action - 进行的操作 "forward"、"refresh"
	 * @param url - 要跳转到的url地址
	 * @return
	 */
	public synchronized static JsonResult jsonSuccess(String msg, String action, String url) {
		return new JsonResult(ResultCode.SUCCESS.code,msg, new ActionAndUrl(action,url));
	}
	
	/**
	 * 方法说明：创建json返回数据 正确状态 不跳转页面也不刷新当前页面 但关闭当前页面200
	 * @param msg 显示的信息 - action - noForwardAndRefresh
	 * @return
	 */
	public synchronized static JsonResult jsonSuccess(String msg) {
		return new JsonResult(ResultCode.SUCCESS.code,msg, new ActionAndUrl(Global.AJAX_ACTION_NoREFRESHANDFORWARD, null));
	}
	
	/**
	 * 方法说明：创建json返回数据 正确状态 跳转页面200
	 * @param url - 要跳转到的url地址
	 * @return
	 */
	public synchronized static JsonResult jsonForward(String url) {
		return new JsonResult(ResultCode.SUCCESS.code,null, new ActionAndUrl(Global.AJAX_ACTION_FORWARD, url));
	}
	
	/**
	 * 方法说明：创建json返回数据 正确状态 跳转页面200
	 * @param msg - 要提示的消息
	 * @param url - 要跳转到的url地址
	 * @return
	 */
	public synchronized static JsonResult jsonForward(String msg,String url) {
		return new JsonResult(ResultCode.SUCCESS.code,msg, new ActionAndUrl(Global.AJAX_ACTION_FORWARD, url));
	}
	
	/**
	 * 方法说明：创建json返回数据 正确状态 刷新页面 无显示信息200
	 * @return
	 */
	public synchronized static JsonResult jsonRefresh() {
		JsonResult jsonResult = new JsonResult(ResultCode.SUCCESS.code, null,new ActionAndUrl(Global.AJAX_ACTION_REFRESH, null));
		return(jsonResult);
	}
	
	/**
	 * 方法说明：创建json返回数据 正确状态 刷新页面 有信息200
	 * @return
	 */
	public synchronized static JsonResult jsonRefresh(String msg) {
		JsonResult jsonResult = new JsonResult(ResultCode.SUCCESS.code, msg,new ActionAndUrl(Global.AJAX_ACTION_REFRESH, null));
		return(jsonResult);
	}


	/**
	 * 
	 * 方法说明：创建json返回数据 错误状态 不跳转不刷新300
	 * @param msg - 显示的信息
	 * @return
	 */
	public synchronized static JsonResult jsonError(String msg) {
		return new JsonResult(ResultCode.ERROR.code,msg);
	}
	
	
	
	
	/* ----------这里的返回主要是前端自定义的返回信息------JsonResult 不懂的查看这个类------- */
	/**
	 * 方法说明：创建json返回数据 正确状态 自定义200
	 * @param msg - 显示的信息
	 * @param obj - data要显示的数据信息
	 * @return
	 */
	public synchronized static JsonResult jsonSuccess(String msg,Object obj) {
		return new JsonResult(ResultCode.SUCCESS.code,msg, obj);
	}
	
	/**
	 * 方法说明：创建json返回数据 正确状态 自定义200
	 * @param obj - data要显示的数据信息
	 * @return
	 */
	public synchronized static JsonResult jsonSuccess(Object obj) {
		return new JsonResult(obj);
	}
	/**
	 * 
	 * 方法说明：创建json返回数据 错误状态 300
	 * @param msg - 显示的信息
	 * @param obj - data - 显示错误标识，-自己自定义
	 * @return
	 */
	public synchronized static JsonResult jsonError(String msg,Object obj) {
		return new JsonResult(ResultCode.ERROR.code,msg,obj);
	}
	
	/**
	 * 方法说明：创建json返回数据 - 自定义返回的数据信息 - 
	 * @param state - 状态 - INT
	 * @param msg - 显示的信息
	 * @param data - data - 显示错误标识，-自己自定义
	 * @return
	 */
	public synchronized static JsonResult jsonResult(int state, String msg, Object data) {
		return new JsonResult(state,msg,data);
	}
}
