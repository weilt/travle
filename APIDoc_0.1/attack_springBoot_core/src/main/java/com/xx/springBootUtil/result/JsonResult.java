package com.xx.springBootUtil.result;

import java.io.Serializable;

import com.xx.springBootUtil.util.GsonUtil;

/**
 * 返回的数据信息
 * @author LiuGang
 */
public class JsonResult implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private int state; // 返回的状态码
	private String msg; // 返回的提示消息
	private Object data; // 返回的数据信息
	
	/**
	 * 默认正确值
	 */
	public JsonResult() {
		 this.msg = ResultCode.SUCCESS.msg;
         this.state = ResultCode.SUCCESS.code;
	}
	
	
	/**
	 * 自定义返回正确信息
	 * @param state
	 * @param msg
	 */
	public JsonResult(Object data) {
		this.msg = ResultCode.SUCCESS.msg;
        this.state = ResultCode.SUCCESS.code;
		this.data = data;
	}
	/**
	 * 自定义返回数据信息
	 * @param state
	 * @param msg
	 */
	public JsonResult(int state,String msg) {
		this.state = state;
		this.msg = msg;
	}
	
	/**
	 * 自定义返回数据信息
	 * @param state
	 * @param msg
	 * @param data
	 */
	public JsonResult(int state, String msg, Object data) {
		this.state = state;
		this.msg = msg;
		this.data = data;
	}
	
	public JsonResult(ResultCode resultCode) {
        this.msg = resultCode.msg;
        this.state = resultCode.code;
        this.data = null;
    }

    public JsonResult(Object data,ResultCode resultCode) {
        this.msg = resultCode.msg;
        this.state = resultCode.code;
        this.data = data;
    }

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
	
	public String toString(){
		return GsonUtil.toJson(this);
	}

}
