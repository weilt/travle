package com.hwt.domain.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class HxUserApplyFriend implements Serializable {
	
	 /**
     * 申请人ID
     */
    private Long apply_user_id;

    /**
     * 被申请人ID
     */
    private Long apply_friend_id;
    
    /**
     * 好友备注
     */
    private String friend_remark;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 是否让朋友看我的朋友圈吧 0-否  1-是 默认是1
     */
    private Byte friend_see_me_space;

    /**
     * 申请状态  0 - 拒绝; 1 - 申请 2 - 同意
     */
    private Integer apply_state;

    /**
     * 最近操作时间
     */
    private Date recent_time;

    private static final long serialVersionUID = 1L;

	public Long getApply_user_id() {
		return apply_user_id;
	}

	public void setApply_user_id(Long apply_user_id) {
		this.apply_user_id = apply_user_id;
	}

	public Long getApply_friend_id() {
		return apply_friend_id;
	}

	public void setApply_friend_id(Long apply_friend_id) {
		this.apply_friend_id = apply_friend_id;
	}

	public String getFriend_remark() {
		return friend_remark;
	}

	public void setFriend_remark(String friend_remark) {
		this.friend_remark = friend_remark;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Integer getApply_state() {
		return apply_state;
	}

	public void setApply_state(Integer apply_state) {
		this.apply_state = apply_state;
	}

	public Date getRecent_time() {
		return recent_time;
	}

	public void setRecent_time(Date recent_time) {
		this.recent_time = recent_time;
	}

	public Byte getFriend_see_me_space() {
		return friend_see_me_space;
	}

	public void setFriend_see_me_space(Byte friend_see_me_space) {
		this.friend_see_me_space = friend_see_me_space;
	}
    
    
}