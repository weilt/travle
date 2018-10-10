package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;

import com.hwt.domain.entity.user.HxUserInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


/**
 * 好友详情
 * @author Administrator
 *
 */
@ApiModel(value="好友详情")
public class FriendInfoVo extends UserInfoVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 朋友id
	 */
	@ApiModelProperty(value="朋友id")
	private Long friend_id;
	
	/**
	 * 是否被申请 1-是  2-其他
	 */
//	@ApiModelProperty(value="是否被申请 1-被申请 2-其他")
//	private Integer apply_state;
//	
//	/**
//	 * 是否拉黑对方 1-是 2-其他
//	 */
//	@ApiModelProperty(value="是否拉黑对方 1-是 2-其他")
//	private Integer black_state;
//	
//	/**
//	 * 是否被拉黑 1-是 2-其他
//	 */
//	@ApiModelProperty(value="是否被拉黑 1-是 2-其他")
//	private Integer bei_black_state;
//	
//	/**
//	 * 是否删除对方 1-是 2-其他
//	 */
//	@ApiModelProperty(value="是否删除对方 1-是 2-其他")
//	private Integer delete_state;
//	/**
//	 * 是否被删除1-是 2-其他
//	 */
//	@ApiModelProperty(value="是否被删除 1-是 2-其他")
//	private Integer bei_delete_state;
	
	/**
	 * 我对朋友的现有操作
	 */
	@ApiModelProperty(value="我对朋友的现有操作 ：1-正常关系，2-拉黑，3-删除， 4-申请")
	private Integer apply_type;
	
	
	/**
	 * 朋友对我的现有操作
	 */
	@ApiModelProperty(value="朋友对我的现有操作 ：1-正常关系，2-拉黑，3-删除，4-申请")
	private Integer friend_apply_type;
	
	/**
     * 好友备注
     */
	@ApiModelProperty(value="好友备注")
    private String friend_remark;
    
   
    
    /**
     * '好友关系状态 1-正常 2-拉黑 3-删除',
     */
	@ApiModelProperty(value="好友关系状态 1-正常 2-拉黑 3-删除")
    private Byte friend_state;

	
	 /**
     * 是否让朋友看我的朋友圈吧 0-否  1-是 默认是1
     */
	@ApiModelProperty(value="是否让朋友看我的朋友圈吧 0-否  1-是 默认是1")
    private Byte friend_see_me_space;

    /**
     * 我是否能看朋友的朋友圈  0-否  1-能 默认能
     */
	@ApiModelProperty(value="我是否能看朋友的朋友圈  0-否  1-能 默认能")
    private Byte me_see_friend_space;
	
	public Integer getApply_type() {
		return apply_type;
	}



	public void setApply_type(Integer apply_type) {
		this.apply_type = apply_type;
	}



	public Integer getFriend_apply_type() {
		return friend_apply_type;
	}



	public void setFriend_apply_type(Integer friend_apply_type) {
		this.friend_apply_type = friend_apply_type;
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



	public Long getFriend_id() {
		return friend_id;
	}



	public void setFriend_id(Long friend_id) {
		this.friend_id = friend_id;
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



	
	
}
