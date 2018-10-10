package com.hwt.domain.entity.mg.user;

import java.io.Serializable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;



/**
 * 朋友圈评论
 * @author Administrator
 *
 */
public class FriendCircleComment implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * id
	 */
	private Long comment_id;
	
	/**
	 * 朋友圈id
	 */
	private Long friend_circle_id;
	
	/**
	 * 评论人id
	 */
	private Long user_id;
	
	/**
	 * 类型  1-文本  2-点赞
	 */
	private Integer type;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 *  父id  0-顶级
	 */
	private Long parent_id;
	
	/**
	 *	回复谁？ 0-不是回复   
	 */
	private Long parent_user_id;
	
	/**
	 * 关联 ，共同好友才能看  如1,2,3
	 */
	private String relation;
	
	/**
	 * 创建时间
	 */
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

	public static void main(String[] args) {
		String str = "bbbabcd";
		String pattern = "^((?!acb).)*$/is";

		Pattern r = Pattern.compile(pattern);
		Matcher m = r.matcher(str);
		System.out.println(m.matches());
	}
}
