package com.hwt.domain.entity.user.video.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="关注用户列表")
public class VideoFollowVo extends VideoUserVo implements Serializable {
	
    /**
     * 创建时间
     */
	@ApiModelProperty(value="关注时间")
    private Long follow_time;

    private static final long serialVersionUID = 1L;

	public Long getFollow_time() {
		return follow_time;
	}

	public void setFollow_time(Long follow_time) {
		this.follow_time = follow_time;
	}
}