package com.hx.bureau.service.cache;

import java.io.Serializable;

/**
 * Created by Ro on 2018/4/23.
 * 旅行社缓存对象
 */
public class BureauUserCache implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * id
     */
    private Long bureau_user_id;

    /**
     * 账号
     */
    private String bureau_user_account;

    
    /**
     * 旅行社id
     */
    private Long bureau_id;


    /**
     * 是否是法人  0-不是 1-是
     */
    private Integer is_legal;


    /**
     * 真实姓名
     */
    private String real_name;
    
    /**
     * 上次登录时间
     */
    private Long last_login_time;

    
	

	public BureauUserCache(Long bureau_user_id, String bureau_user_account, Long bureau_id, Integer is_legal,
			String real_name, Long last_login_time) {
		super();
		this.bureau_user_id = bureau_user_id;
		this.bureau_user_account = bureau_user_account;
		this.bureau_id = bureau_id;
		this.is_legal = is_legal;
		this.real_name = real_name;
		this.last_login_time = last_login_time;
	}


	public Long getBureau_user_id() {
		return bureau_user_id;
	}


	public void setBureau_user_id(Long bureau_user_id) {
		this.bureau_user_id = bureau_user_id;
	}


	public String getBureau_user_account() {
		return bureau_user_account;
	}


	public void setBureau_user_account(String bureau_user_account) {
		this.bureau_user_account = bureau_user_account;
	}


	public Long getBureau_id() {
		return bureau_id;
	}


	public void setBureau_id(Long bureau_id) {
		this.bureau_id = bureau_id;
	}


	public Integer getIs_legal() {
		return is_legal;
	}


	public void setIs_legal(Integer is_legal) {
		this.is_legal = is_legal;
	}


	public String getReal_name() {
		return real_name;
	}


	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}


	public Long getLast_login_time() {
		return last_login_time;
	}


	public void setLast_login_time(Long last_login_time) {
		this.last_login_time = last_login_time;
	}
	
}
