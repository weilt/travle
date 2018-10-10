package com.xx.util.result;

import java.io.Serializable;

public class ActionAndUrl implements Serializable{

	/**
	 * 这个类是 做为后台返回数据判断的类， 方便统一跳转和跳转的类型数据信息   == 刘钢
	 */
	private static final long serialVersionUID = 1L;
	
	public ActionAndUrl(){
	}
	public ActionAndUrl(String action,String url){
		this.action = action;
		this.url = url;
	}
	private String action; //请求类型 - 详见 Global.java
	private String url; //地址
	
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
