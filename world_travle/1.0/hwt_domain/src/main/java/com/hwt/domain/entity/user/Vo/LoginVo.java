package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 登录成功保存用户资料信息
 * @author JIAO_LIU_BABA
 */
@ApiModel(value="登录成功保存用户资料信息")
public class LoginVo implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户id")
	private Long user_id;// 主键
	
	@ApiModelProperty(value="账号")
	private String account;// 账号
	
	@ApiModelProperty(value="绑定的手机号")
	private String account_phone;// 手机号
	
	@ApiModelProperty(value="绑定的手机号云信id")
	private String im_id; 	// 云信id
	
	@ApiModelProperty(value="通讯用到的token")
	private String im_token;// 通讯用到的token
	
	@ApiModelProperty(value=" 推荐人账号")
	private String referrer_account;// 推荐人账号

	@ApiModelProperty(value="登录时间")
	private Date recently_login_time;//登录时间
	
	@ApiModelProperty(value="登录IP")
	private String recently_login_ip;//登录IP
	
	@ApiModelProperty(value="当前用户登录token，验证token一致性 - 真实token")
	private String token; //当前用户登录token，验证token一致性 - 真实token
	
	@ApiModelProperty(value="用户昵称")
	private String nickname;
	
	@ApiModelProperty(value="用户头像")
	private String user_icon;
	
	@ApiModelProperty(value="朋友圈封面")
	private String friend_circle_cover;
	
	public LoginVo(){
		
	}


	public LoginVo(Long user_id, String account, String account_phone, String im_id, String im_token,
			String referrer_account, Date recently_login_time, String recently_login_ip, String token,String user_icon,String friend_circle_cover) {
		super();
		this.user_id = user_id;
		this.account = account;
		this.account_phone = account_phone;
		this.im_id = im_id;
		this.im_token = im_token;
		this.referrer_account = referrer_account;
		this.recently_login_time = recently_login_time;
		this.recently_login_ip = recently_login_ip;
		this.token = token;
		this.user_icon = user_icon;
		this.friend_circle_cover = friend_circle_cover;
	}
	
	
	

	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


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


	public String getIm_id() {
		return im_id;
	}


	public void setIm_id(String im_id) {
		this.im_id = im_id;
	}


	public String getIm_token() {
		return im_token;
	}


	public void setIm_token(String im_token) {
		this.im_token = im_token;
	}


	public String getReferrer_account() {
		return referrer_account;
	}


	public void setReferrer_account(String referrer_account) {
		this.referrer_account = referrer_account;
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


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getUser_icon() {
		return user_icon;
	}


	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}


	public String getFriend_circle_cover() {
		return friend_circle_cover;
	}


	public void setFriend_circle_cover(String friend_circle_cover) {
		this.friend_circle_cover = friend_circle_cover;
	}
	
	
	
}
