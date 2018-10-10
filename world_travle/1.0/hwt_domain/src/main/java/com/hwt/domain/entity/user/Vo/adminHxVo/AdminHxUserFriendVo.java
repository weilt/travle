package com.hwt.domain.entity.user.Vo.adminHxVo;

import java.io.Serializable;

/**
 * 用户好友列表
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public class AdminHxUserFriendVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 好友id
	 */
	private Integer friend_id;
	
	/**
	 * 好友账号
	 */
	private String account;
	
	/**
	 * 好友昵称
	 */
	private String nickname;
	
	/**
	 * 好友备注
	 */
	private String friend_remark;
	
	
	/**
	 * 用户性别 0-人妖 1-男 2-女
	 */
	private Integer user_sex;
	
	/**
	 * 好友关系状态 1-正常 2-拉黑 3-删除
	 */
	private Integer friend_state;
	
	/**
	 * 我在好友里面的状态 1-正常 2-被拉黑 3-被删除
	 */
	private Integer friend_to_me_state;
	

	public Integer getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(Integer friend_id) {
		this.friend_id = friend_id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getFriend_remark() {
		return friend_remark;
	}

	public void setFriend_remark(String friend_remark) {
		this.friend_remark = friend_remark;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
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
	
}
