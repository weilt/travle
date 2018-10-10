package com.hx.user.logs.entity.parent;

/**
 * 短信日志
 * @author Administrator
 *
 */
public class SMSLog extends Logs{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	/**
	 * 发送手机号
	 */
	private String  phone;
	
	
	/**
	 * 内容
	 */
	private String content;
	
	
	/**
	 * 短信类型
	 */
	private String type;


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
}
