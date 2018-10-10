package com.hwt.domain.entity.mg.user;

import java.io.Serializable;
import java.util.Date;

/**
 * 朋友圈
 * @author Administrator
 *
 */
public class FriendCircle implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Long friend_circle_id;

	/**
	 * 用户id
	 */
	private Long user_id;
	
	/**
	 * 类型 1-文本 2-图片 3-视频 4-链接 5-其他
	 */
	private Integer type;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 描述
	 */
	private String description;
	
	/**
	 * 哪些人可以看1-所有人 能看 0-只能自己看  y1yy2yy3y----1,2,3能看   n1nn2nn3n----1,2,3不能看  只能选一种情况  
	 */
	private String can_see_user_id;
	
	/**
	 * 提醒谁看    1,2,3  提醒1,2,3看
	 */
	private String remind_user_id;
	
	/**
	 * 发朋友圈经度
	 */
	private String longitude;
	
	/**
	 * 发朋友圈纬度
	 */
	private String latitude;
	
	/**
	 * 创建时间
	 */
	private Long create_time;
	
	/**
	 * 发表地址
	 */
	private String publish_address;
	
	public Long getFriend_circle_id() {
		return friend_circle_id;
	}

	public void setFriend_circle_id(Long friend_circle_id) {
		this.friend_circle_id = friend_circle_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCan_see_user_id() {
		return can_see_user_id;
	}

	public void setCan_see_user_id(String can_see_user_id) {
		this.can_see_user_id = can_see_user_id;
	}

	public String getRemind_user_id() {
		return remind_user_id;
	}

	public void setRemind_user_id(String remind_user_id) {
		this.remind_user_id = remind_user_id;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	public String getPublish_address() {
		return publish_address;
	}

	public void setPublish_address(String publish_address) {
		this.publish_address = publish_address;
	}

	
	
	
}
