package com.hwt.domain.entity.user.video.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="小视频界面数据")
public class HxVideoVo extends HxUserVideoVo {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="判断我是否喜欢   为空不喜欢")
	private Long video_like_user_id;
	
	@ApiModelProperty(value="判断我是否关注   为空不关注")
	private Long video_follow_user_id;
	
	
	
	public Long getVideo_like_user_id() {
		return video_like_user_id;
	}

	public void setVideo_like_user_id(Long video_like_user_id) {
		this.video_like_user_id = video_like_user_id;
	}

	public Long getVideo_follow_user_id() {
		return video_follow_user_id;
	}

	public void setVideo_follow_user_id(Long video_follow_user_id) {
		this.video_follow_user_id = video_follow_user_id;
	}
	
	
}
