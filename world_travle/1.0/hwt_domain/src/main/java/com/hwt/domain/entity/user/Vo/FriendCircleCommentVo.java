package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="朋友圈评论")
public class FriendCircleCommentVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	@ApiModelProperty(value = "评论id",position=1)
	private Long comment_id;
	
	/**
	 * 朋友圈id
	 */
	@ApiModelProperty(value = "朋友圈id",position=2)
	private Long friend_circle_id;
	
	/**
	 * 评论人id
	 */
	@ApiModelProperty(value = "评论人id",position=3)
	private Long user_id;
	
	/**
	 * 类型  1-文本  2-点赞
	 */
	@ApiModelProperty(value = "类型  1-文本  2-点赞",position=4)
	private Integer type;
	
	/**
	 * 内容
	 */
	@ApiModelProperty(value = "内容",position=5)
	private String content;
	
	/**
	 *  父id  0-顶级
	 */
	@ApiModelProperty(value = " 父id  0-顶级",position=6)
	private Long parent_id;
	
	/**
	 *	回复谁？ 0-不是回复   
	 */
	@ApiModelProperty(value = "回复谁？ 0-不是回复   ",position=7)
	private Long parent_user_id;
	
	/**
	 * 关联 ，共同好友才能看  如r1rr2rr3r  1,2,3才能看
	 */
	@ApiModelProperty(value = "关联 ，共同好友才能看  如r1rr2rr3r  1,2,3才能看",position=8)
	private String relation;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "评论时间",position=9)
	private Long create_time;

	

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

	public Long getFriend_circle_id() {
		return friend_circle_id;
	}

	public void setFriend_circle_id(Long friend_circle_id) {
		this.friend_circle_id = friend_circle_id;
	}

	public Long getComment_id() {
		return comment_id;
	}

	public void setComment_id(Long comment_id) {
		this.comment_id = comment_id;
	}

	public Long getParent_id() {
		return parent_id;
	}

	public void setParent_id(Long parent_id) {
		this.parent_id = parent_id;
	}

	public Long getParent_user_id() {
		return parent_user_id;
	}

	public void setParent_user_id(Long parent_user_id) {
		this.parent_user_id = parent_user_id;
	}

	public String getRelation() {
		return relation;
	}

	public void setRelation(String relation) {
		this.relation = relation;
	}
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	
}
