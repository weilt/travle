package com.hwt.domain.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class HxUserFriend implements Serializable {
	
	
	 /**
     * 好友用户ID
     */
    private Long friend_id;

    /**
     * 用户ID
     */
    private Long user_id;
    
    /**
     * 是否屏蔽该好友 0 否 1 是屏蔽
     */
    private Integer is_shield;

    /**
     * 好友备注
     */
    private String friend_remark;

    /**
     * 好友关系状态 1-正常 2-拉黑 3-删除
     */
    private Byte friend_state;

    /**
     * 我在好友里面的状态 1-正常 2-被拉黑 3-被删除'
     */
    private Byte friend_to_me_state;

    /**
     * 是否为星级朋友 0-不是 , 1-是  默认为不是
     */
    private Byte is_star;

    /**
     * 是否让朋友看我的朋友圈吧 0-否  1-是 默认是1
     */
    private Byte friend_see_me_space;

    /**
     * 我是否愿意看朋友的朋友圈  0-否  1-能 默认能
     */
    private Byte me_see_friend_space;

    /**
     * 朋友 是否让我 他看朋友圈吧 0-否  1-是 默认是1
     */
    private Byte friend_friend_see_me_space;

    /**
     * 朋友 朋友是否愿意看我的朋友圈 0-否  1-是 默认是1
     */
    private Byte friend_me_see_friend_space;

    /**
     * 电话号码, 可以是多个用，隔开
     */
    private String phone;

    /**
     * 好友描述
     */
    private String description;

    /**
     * 名片或者图片
     */
    private String card_image;

    /**
     * 好友来源
     */
    private String source;

    /**
     * 添加时间
     */
    private Date create_time;

    private static final long serialVersionUID = 1L;

	public Long getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(Long friend_id) {
		this.friend_id = friend_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Integer getIs_shield() {
		return is_shield;
	}

	public void setIs_shield(Integer is_shield) {
		this.is_shield = is_shield;
	}

	public String getFriend_remark() {
		return friend_remark;
	}

	public void setFriend_remark(String friend_remark) {
		this.friend_remark = friend_remark;
	}

	public Byte getFriend_state() {
		return friend_state;
	}

	public void setFriend_state(Byte friend_state) {
		this.friend_state = friend_state;
	}

	public Byte getIs_star() {
		return is_star;
	}

	public void setIs_star(Byte is_star) {
		this.is_star = is_star;
	}

	public Byte getFriend_see_me_space() {
		return friend_see_me_space;
	}

	public void setFriend_see_me_space(Byte friend_see_me_space) {
		this.friend_see_me_space = friend_see_me_space;
	}

	public Byte getMe_see_friend_space() {
		return me_see_friend_space;
	}

	public void setMe_see_friend_space(Byte me_see_friend_space) {
		this.me_see_friend_space = me_see_friend_space;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCard_image() {
		return card_image;
	}

	public void setCard_image(String card_image) {
		this.card_image = card_image;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public Byte getFriend_to_me_state() {
		return friend_to_me_state;
	}

	public void setFriend_to_me_state(Byte friend_to_me_state) {
		this.friend_to_me_state = friend_to_me_state;
	}

	public Byte getFriend_friend_see_me_space() {
		return friend_friend_see_me_space;
	}

	public void setFriend_friend_see_me_space(Byte friend_friend_see_me_space) {
		this.friend_friend_see_me_space = friend_friend_see_me_space;
	}

	public Byte getFriend_me_see_friend_space() {
		return friend_me_see_friend_space;
	}

	public void setFriend_me_see_friend_space(Byte friend_me_see_friend_space) {
		this.friend_me_see_friend_space = friend_me_see_friend_space;
	}
    
}	