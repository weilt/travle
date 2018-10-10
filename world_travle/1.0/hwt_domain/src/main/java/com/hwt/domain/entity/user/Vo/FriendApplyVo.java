package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 好友申请
 * @author Administrator
 *
 */
@ApiModel(value="好友申请列表")
public class FriendApplyVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 好友id
	 */
	@ApiModelProperty(value = "好友id")
	private Long friend_id;
	
	
	/**
	 * 用户昵称
	 */
	@ApiModelProperty(value = "用户昵称")
	private String nickname;
	/**
	 * 用户头像
	 */
	@ApiModelProperty(value = "用户头像")
	private String user_icon;
	
	
	/**
	 * 申请备注信息
	 */
	@ApiModelProperty(value = "申请备注信息")
	private String remarks;
	
	/**
	 * 申请状态  0 - 拒绝; 1 - 申请
	 */
	@ApiModelProperty(value = " 申请状态  0 - 拒绝; 1 - 申请")
	private Integer apply_state;
	
	/**
	 * 云信id
	 */
	@ApiModelProperty(value = "云信id")
	private String im_id;

	
	public Long getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(Long friend_id) {
		this.friend_id = friend_id;
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
	
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Integer getApply_state() {
		return apply_state;
	}
	public void setApply_state(Integer apply_state) {
		this.apply_state = apply_state;
	}
	public String getIm_id() {
		return im_id;
	}
	public void setIm_id(String im_id) {
		this.im_id = im_id;
	}
	
	
}
