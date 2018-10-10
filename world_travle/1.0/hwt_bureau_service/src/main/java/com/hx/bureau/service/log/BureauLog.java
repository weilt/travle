package com.hx.bureau.service.log;

import com.hx.core.logs.annotation.InsertIncId;
import com.hx.core.logs.entity.parent.Logs;

public class BureauLog extends Logs {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@InsertIncId
	private Long logId;
	
	/**
	 * 1-登录  2-操作
	 */
	private Integer type;
	
	/**
	 * 旅行社iD
	 */
	private Long bureau_id;
	
	/**
	 * 旅行社用户
	 */
	private Long bureau_user_id;
	
	/**
	 * 真实姓名
	 */
	private String real_name;
	
	/**
	 * ip地址
	 */
	private String ip_address;

	/**
	 * 描述
	 */
	private String dec;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Long getBureau_id() {
		return bureau_id;
	}

	public void setBureau_id(Long bureau_id) {
		this.bureau_id = bureau_id;
	}

	public Long getBureau_user_id() {
		return bureau_user_id;
	}

	public void setBureau_user_id(Long bureau_user_id) {
		this.bureau_user_id = bureau_user_id;
	}

	public String getDec() {
		return dec;
	}

	public void setDec(String dec) {
		this.dec = dec;
	}

	public Long getLogId() {
		return logId;
	}

	public void setLogId(Long logId) {
		this.logId = logId;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	
	
}
