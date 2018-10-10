package com.hwt.domain.entity.user.video.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="小视频喜欢列表")
public class HxUserVideoLikeListVo  extends HxUserVideoVo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="判断我是否关注   为空不关注")
	private Long video_follow_user_id;
	
	@ApiModelProperty(value="喜欢时间")
	private Long like_time;

	public Long getVideo_follow_user_id() {
		return video_follow_user_id;
	}

	public void setVideo_follow_user_id(Long video_follow_user_id) {
		this.video_follow_user_id = video_follow_user_id;
	}

	public Long getLike_time() {
		return like_time;
	}

	public void setLike_time(Long like_time) {
		this.like_time = like_time;
	}
	
}
