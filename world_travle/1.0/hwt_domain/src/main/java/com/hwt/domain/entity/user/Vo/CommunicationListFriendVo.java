package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 通讯录好友信息列表
 * @author Administrator
 *
 */
 @ApiModel(value="通讯录好友信息列表")
public class CommunicationListFriendVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     *用户id
     */
	@ApiModelProperty(value = "用户id")
    private Long user_id;
	
	 /**
     * 手机登录号码
     */
	@ApiModelProperty(value = "手机登录号码")
    private String account_phone;
    
	/**
     * 账号
     */
	@ApiModelProperty(value = "账号")
    private String account;

    /**
     * 云信id
     */
	@ApiModelProperty(value = "云信id")
    private String im_id;
	
    /**
     * 用户昵称
     */
	@ApiModelProperty(value = "用户昵称")
    private String nickname;
	
	
	 /**
     * 用户头像地址
     */
    @ApiModelProperty(value = " 用户头像地址")
    private String user_icon;


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getAccount_phone() {
		return account_phone;
	}


	public void setAccount_phone(String account_phone) {
		this.account_phone = account_phone;
	}


	public String getAccount() {
		return account;
	}


	public void setAccount(String account) {
		this.account = account;
	}


	public String getIm_id() {
		return im_id;
	}


	public void setIm_id(String im_id) {
		this.im_id = im_id;
	}


	public String getNickname() {
		return nickname;
	}


	public void setNickname(String nickname) {
		this.nickname = nickname;
	}


	public String getUser_icon() {
		return user_icon;
	}


	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}
    
    

}
