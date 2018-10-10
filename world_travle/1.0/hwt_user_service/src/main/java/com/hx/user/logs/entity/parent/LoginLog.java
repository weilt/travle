package com.hx.user.logs.entity.parent;

/**
 * 用户
 * @author Administrator
 *
 */
public class LoginLog extends Logs{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 操作人id
	 */
	private Long user_id;
	
	/**
	 * 当前操作机器的ip地址和名称
	 */
	private String clientIpAndName;
	
	/**
	 * 1-登录  2-登出
	 */
	private int state;
	
	/**
	 * 登录方式  1-手机加验证码  2-手机加密码 3-账号加密码
	 */
	private int loginState;
	
	/**
	 * 经度
	 */
	private String longitude;
	
	/**
	 * 维度
	 */
	private String latitude;


	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getClientIpAndName() {
		return clientIpAndName;
	}

	public void setClientIpAndName(String clientIpAndName) {
		this.clientIpAndName = clientIpAndName;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public int getLoginState() {
		return loginState;
	}

	public void setLoginState(int loginState) {
		this.loginState = loginState;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
}
