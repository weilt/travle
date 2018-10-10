package com.common.vo;

import java.io.Serializable;
import java.util.Date;

public class LoginVo implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;// 主键
	
	private String phone;// 手机号
	
	private String token;// 通讯用到的 token
	
	private String referrerId;// 推荐人账号
	
	private int userShopType;//用户类型 - 0-普通用户,1-店长
	
	private String userShopManagerId;//店铺ID,只有店长 才有这个值
	
	private Date loginTime;//登录时间
	
	private String loginIp;//登录IP
	
	private String verToken; //当前用户登录token，验证token一致性 - 真实token
	
	public LoginVo() {}
	
	public LoginVo(String id,String phone,String token,String referrerId,int userShopType,Date loginTime,String loginIp,String verToken) {
		this.id = id;
		this.phone = phone;
		this.token = token;
		this.referrerId = referrerId;
		this.userShopType = userShopType;
		this.loginTime = loginTime;
		this.loginIp = loginIp;
		this.verToken = verToken;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getReferrerId() {
		return referrerId;
	}

	public void setReferrerId(String referrerId) {
		this.referrerId = referrerId;
	}

	public Date getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Date loginTime) {
		this.loginTime = loginTime;
	}
	
	public int getUserShopType() {
		return userShopType;
	}

	public void setUserShopType(int userShopType) {
		this.userShopType = userShopType;
	}

	public String getLoginIp() {
		return loginIp;
	}

	public void setLoginIp(String loginIp) {
		this.loginIp = loginIp;
	}

	public String getVerToken() {
		return verToken;
	}

	public void setVerToken(String verToken) {
		this.verToken = verToken;
	}

	public String getUserShopManagerId() {
		return userShopManagerId;
	}

	public void setUserShopManagerId(String userShopManagerId) {
		this.userShopManagerId = userShopManagerId;
	}
}
