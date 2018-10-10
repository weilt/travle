package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;

import com.hwt.domain.entity.user.HxUserInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 黑名单列表信息
 * @author Administrator
 */
@ApiModel(value="黑名单列表")
public class BlackFriendUserVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	
	/**
	 * 被拉黑用户id
	 */
	@ApiModelProperty(value="被拉黑用户id")
	private Long friend_id;
	
	
	/**
	 * 用户昵称
	 */
	@ApiModelProperty(value="用户昵称")
	private String nickname;
	
	/**
	 * 用户头像
	 */
	@ApiModelProperty(value="用户头像")
	private String user_icon;
	
	/**
	 * 好友备注
	 */
	@ApiModelProperty(value="好友备注")
	private String friend_remark;
	
	/**
	 * 云信id
	 */
//	@ApiModelProperty(value = "云信id")
//	private String im_id;
	
	
	


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

	public String getFriend_remark() {
		return friend_remark;
	}

	public void setFriend_remark(String friend_remark) {
		this.friend_remark = friend_remark;
	}

//	public String getIm_id() {
//		return im_id;
//	}
//
//	public void setIm_id(String im_id) {
//		this.im_id = im_id;
//	}

	public Long getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(Long friend_id) {
		this.friend_id = friend_id;
	}

	
	
}
