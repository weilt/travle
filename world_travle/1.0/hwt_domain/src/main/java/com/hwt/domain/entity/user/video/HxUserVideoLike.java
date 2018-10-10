package com.hwt.domain.entity.user.video;

import java.io.Serializable;

/**
 * @author 
 */
public class HxUserVideoLike implements Serializable {
	
	 /**
     * 用户id
     */
    private Long user_id;

    /**
     * 小视频id
     */
    private Long video_id;
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

	public Long getVideo_id() {
		return video_id;
	}

	public void setVideo_id(Long video_id) {
		this.video_id = video_id;
	}
    
    
}