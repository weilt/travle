package com.hwt.domain.entity.user;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class HxUserBlackList implements Serializable {
	
	/**
     * 用户ID
     */
    private Long user_id;

    /**
     * 被拉黑用户ID
     */
    private Long blacklist_id;
    
    /**
     * 好友备注
     */
    private String friend_remark;
    
    /**
     * 拉黑日期
     */
    private Date create_time;

    private static final long serialVersionUID = 1L;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getBlacklist_id() {
		return blacklist_id;
	}

	public void setBlacklist_id(Long blacklist_id) {
		this.blacklist_id = blacklist_id;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	public String getFriend_remark() {
		return friend_remark;
	}

	public void setFriend_remark(String friend_remark) {
		this.friend_remark = friend_remark;
	}
    
    
    
}