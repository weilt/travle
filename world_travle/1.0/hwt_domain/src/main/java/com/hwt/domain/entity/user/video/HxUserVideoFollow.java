package com.hwt.domain.entity.user.video;

import java.io.Serializable;

/**
 * @author 
 */
public class HxUserVideoFollow implements Serializable {
	
	 /**
     * 用户id
     */
    private Long user_id;

    /**
     * 被关注人的id
     */
    private Long follow_user_id;
    
    /**
     * 创建时间
     */
    private Long create_time;

    private static final long serialVersionUID = 1L;

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getFollow_user_id() {
		return follow_user_id;
	}

	public void setFollow_user_id(Long follow_user_id) {
		this.follow_user_id = follow_user_id;
	}
    
    
}