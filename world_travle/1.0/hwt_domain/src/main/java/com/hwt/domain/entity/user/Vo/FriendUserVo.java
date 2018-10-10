package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hwt.domain.entity.user.HxUserInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Created by RO on 2018/3/12.
 * 用户__好友关系 信息
 */
@ApiModel(value="用户__好友关系 信息")
public class FriendUserVo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * 好友备注
     */
	@ApiModelProperty(value=" 好友备注")
    private String friend_remark;
	
	/**
     * 用户昵称
     */
    @ApiModelProperty(value="用户昵称 ")
    private String nickname;
    
    /**
	 * 云信id
	 */
	@ApiModelProperty(value = "云信id")
	private String im_id;
	
	
	  /**
     * 用户头像地址
     */
    @ApiModelProperty(value="用户头像地址")
    private String user_icon;
    
	 /**
	  * 好友id
	 * 
	 */
	@ApiModelProperty(value="好友id")
	private Long friend_id;
    /**
     * 好友在我里的  关系状态 1-正常 2-拉黑 3-删除',
     */
	@ApiModelProperty(value="好友在我里的  关系状态 1-正常 2-拉黑 3-删除")
    private Integer friend_state;
    
    /**
     * 我在好友里的  关系状态 1-正常 2-被拉黑 3-被删除',
     */
	@ApiModelProperty(value="我在好友里的  关系状态 1-正常 2-被拉黑 3-被删除")
    private Integer friend_to_me_state;
    
 

	public String getFriend_remark() {
		return friend_remark;
	}

	public void setFriend_remark(String friend_remark) {
		this.friend_remark = friend_remark;
	}

	public Integer getFriend_state() {
		return friend_state;
	}

	public void setFriend_state(Integer friend_state) {
		this.friend_state = friend_state;
	}

	public Integer getFriend_to_me_state() {
		return friend_to_me_state;
	}

	public void setFriend_to_me_state(Integer friend_to_me_state) {
		this.friend_to_me_state = friend_to_me_state;
	}

	
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

	public String getIm_id() {
		return im_id;
	}

	public void setIm_id(String im_id) {
		this.im_id = im_id;
	}

	public String getUser_icon() {
		return user_icon;
	}

	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}

	
}
