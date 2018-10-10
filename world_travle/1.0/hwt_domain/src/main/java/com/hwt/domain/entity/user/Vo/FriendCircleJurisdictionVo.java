package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;

/**
 * 朋友圈权限
 * @author Administrator
 *
 */
public class FriendCircleJurisdictionVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 朋友id
	 */
	private Long friend_id;

	/**
     * 允许朋友查看朋友圈的范围 	0-全部 	1-3天 	2-半年
     */
    private Byte friend_circle_friend_see_day;
    
    /**
     * 好友在我这里的状态
     */
    private Byte friend_state;
    /**
     * 我在好友里的状态
     */
    private Byte friend_to_me_state;
    /**
     * 我能看好友朋友圈
     */
    private Byte me_see_friend_space;
    /**
     * 好友让我看朋友圈
     */
    private Byte friend_let_me_see_space;

	public Long getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(Long friend_id) {
		this.friend_id = friend_id;
	}

	public Byte getFriend_circle_friend_see_day() {
		return friend_circle_friend_see_day;
	}

	public void setFriend_circle_friend_see_day(Byte friend_circle_friend_see_day) {
		this.friend_circle_friend_see_day = friend_circle_friend_see_day;
	}

	public Byte getFriend_state() {
		return friend_state;
	}

	public void setFriend_state(Byte friend_state) {
		this.friend_state = friend_state;
	}

	public Byte getFriend_to_me_state() {
		return friend_to_me_state;
	}

	public void setFriend_to_me_state(Byte friend_to_me_state) {
		this.friend_to_me_state = friend_to_me_state;
	}

	public Byte getMe_see_friend_space() {
		return me_see_friend_space;
	}

	public void setMe_see_friend_space(Byte me_see_friend_space) {
		this.me_see_friend_space = me_see_friend_space;
	}

	public Byte getFriend_let_me_see_space() {
		return friend_let_me_see_space;
	}

	public void setFriend_let_me_see_space(Byte friend_let_me_see_space) {
		this.friend_let_me_see_space = friend_let_me_see_space;
	}

	@Override
	public String toString() {
		return "FriendCircleJurisdictionVo [friend_id=" + friend_id + ", friend_circle_friend_see_day="
				+ friend_circle_friend_see_day + ", friend_state=" + friend_state + ", friend_to_me_state="
				+ friend_to_me_state + ", me_see_friend_space=" + me_see_friend_space + ", friend_let_me_see_space="
				+ friend_let_me_see_space + "]";
	}
	
	
}
