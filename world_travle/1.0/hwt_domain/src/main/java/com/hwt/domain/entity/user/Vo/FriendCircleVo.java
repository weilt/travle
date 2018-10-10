package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 朋友圈表
 * @author Administrator
 *
 */
@ApiModel(value="朋友圈表")
public class FriendCircleVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@ApiModelProperty(value = "朋友圈id",position=1)
	private Long friend_circle_id;

	/**
	 * 用户id
	 */
	@ApiModelProperty(value = "用户id",position=2)
	private Long user_id;
	
	/**
	 * 类型 1-文本 2-图片 3-视频 4-链接 5-其他
	 */
	@ApiModelProperty(value = "类型 1-文本 2-图片 3-视频 4-链接 5-其他",position=3)
	private Integer type;
	
	/**
	 * 内容
	 */
	@ApiModelProperty(value = "内容",position=4)
	private String content;
	
	/**
	 * 描述
	 */
	@ApiModelProperty(value = "描述",position=5)
	private String description;
	
	/**
	 * 哪些人可以看1-所有人 能看 0-只能自己看  y1yy2yy3y----1,2,3能看   n1nn2nn3n----1,2,3不能看  只能选一种情况  
	 */
	@ApiModelProperty(value = "哪些人可以看1-所有人 能看 0-只能自己看  y1yy2yy3y----1,2,3能看   n1nn2nn3n----1,2,3不能看  只能选一种情况 ",position=6)
	private String can_see_user_id;
	
	/**
	 * 提醒谁看    1,2,3  提醒1,2,3看
	 */
	@ApiModelProperty(value = "提醒谁看    1,2,3  提醒1,2,3看",position=7)
	private String remind_user_id;
	
	/**
	 * 发朋友圈经度
	 */
	@ApiModelProperty(value = " 发朋友圈经度",position=8)
	private String longitude;
	
	/**
	 * 发朋友圈纬度
	 */
	@ApiModelProperty(value = " 发朋友圈纬度",position=9)
	private String latitude;
	
	/**
	 * 发表地址
	 */
	@ApiModelProperty(value = "发表地址",position=9)
	private String publish_address;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "发布时间",position=10)
	private Long create_time;
	
	/**
	 * 该朋友圈的所有评论
	 */
	@ApiModelProperty(value = "该朋友圈的所有评论",position=11)
	private List<FriendCircleCommentVo> friendCircleCommentVos = new ArrayList<>();

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

	public List<FriendCircleCommentVo> getFriendCircleCommentVos() {
		return friendCircleCommentVos;
	}

	public void setFriendCircleCommentVos(List<FriendCircleCommentVo> friendCircleCommentVos) {
		this.friendCircleCommentVos = friendCircleCommentVos;
	}

	public String getPublish_address() {
		return publish_address;
	}

	public void setPublish_address(String publish_address) {
		this.publish_address = publish_address;
	}
	
	
	
	
}
