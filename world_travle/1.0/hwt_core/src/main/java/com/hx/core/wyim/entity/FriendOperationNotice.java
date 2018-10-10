package com.hx.core.wyim.entity;

import java.io.Serializable;

/**
 * 操作通知
 * @author Administrator
 *
 */
public class FriendOperationNotice implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 操作人云信id
	 */
	private String im_id;
	
	/**
	 * 操作人的用户信息
	 */
	private String userInfo;
	
	/**
	 * 类型   1 申请 2 拉黑 3 删除 4 恢复好友 5 同意好友 6 恢复好友关系   7 点赞和评论 8 取消点赞和评论 9 小圈圈 10 朋友圈删除 11导游申请回复 10000系统消息
	 * 
	 * 
	 */
	private Integer type;
	
	/**
	 * 内容
	 */
	private String content;
	
	public FriendOperationNotice(String im_id, Integer type, String userInfo) {
		super();
		this.im_id = im_id;
		this.type = type;
		this.userInfo = userInfo;
	}
	
	
	public FriendOperationNotice(String im_id, String userInfo, Integer type, String content) {
		super();
		this.im_id = im_id;
		this.userInfo = userInfo;
		this.type = type;
		this.content = content;
	}


	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getIm_id() {
		return im_id;
	}

	public void setIm_id(String im_id) {
		this.im_id = im_id;
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
}
