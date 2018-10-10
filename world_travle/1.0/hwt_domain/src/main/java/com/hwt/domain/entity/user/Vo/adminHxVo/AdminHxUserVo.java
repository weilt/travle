package com.hwt.domain.entity.user.Vo.adminHxVo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * hx用户列表返回数据
 * @author Administrator
 *
 */

public class AdminHxUserVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * 主键ID
     */
    private Long user_id;
    
    /**
     * 用户登录账号
     */
    private String account;
    
    /**
     * 手机登录号码
     */
    private String account_phone;
    
    /**
     * 登录状态 1-正常状态,2-冻结状态 ，3-永久冻结
     */
    private Integer login_state;
    
    /**
     * 最近登录时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:dd:ss")
   	@DateTimeFormat( pattern = "yyyy-MM-dd HH:dd:ss")
    private Date recently_login_time;
    
    /**
     * 最近登录IP
     */
    private String recently_login_ip;
    
    /**
     * 用户昵称
     */
    private String nickname;
    
    /**
     * 用户类型 - （0-普通用户，1-VIP用户，2-vip2用户...）
     * 
     */
    private Integer userType;
    
    /**
     * 用户性别 0-人妖 1-男 2-女
     */
    private Integer user_sex;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAccount_phone() {
		return account_phone;
	}

	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}

	public Integer getLogin_state() {
		return login_state;
	}

	public void setLogin_state(Integer login_state) {
		this.login_state = login_state;
	}

	public Date getRecently_login_time() {
		return recently_login_time;
	}

	public void setRecently_login_time(Date recently_login_time) {
		this.recently_login_time = recently_login_time;
	}

	public String getRecently_login_ip() {
		return recently_login_ip;
	}

	public void setRecently_login_ip(String recently_login_ip) {
		this.recently_login_ip = recently_login_ip;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}
    
    
	
}
