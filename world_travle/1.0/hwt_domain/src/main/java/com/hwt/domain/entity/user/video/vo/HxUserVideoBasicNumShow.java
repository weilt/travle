package com.hwt.domain.entity.user.video.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="小视频基本数量展示")
public class HxUserVideoBasicNumShow {
	
	/**
	 * 总的喜欢
	 */
	@ApiModelProperty(value="总的喜欢")
    private Long total_good;
	
	/**
	 * 总的关注
	 */
	@ApiModelProperty(value="总的关注")
	private Long total_follow;
	
	/**
	 * 总的自己的视频
	 */
	@ApiModelProperty(value="总的自己的视频")
	private Long total_my;
	
	/**
	 * 总的喜欢
	 */
	@ApiModelProperty(value="总的喜欢")
    private Long get_total_good;
	
	/**
	 * 总的关注
	 */
	@ApiModelProperty(value="总的关注")
	private Long get_total_follow;
	
	/**
	 * 总的评论
	 */
	@ApiModelProperty(value="总的评论")
	private Long total_comment;

	public Long getTotal_good() {
		return total_good;
	}

	public void setTotal_good(Long total_good) {
		this.total_good = total_good;
	}

	public Long getTotal_follow() {
		return total_follow;
	}

	public void setTotal_follow(Long total_follow) {
		this.total_follow = total_follow;
	}

	public Long getTotal_my() {
		return total_my;
	}

	public void setTotal_my(Long total_my) {
		this.total_my = total_my;
	}

	public Long getGet_total_good() {
		return get_total_good;
	}

	public void setGet_total_good(Long get_total_good) {
		this.get_total_good = get_total_good;
	}

	public Long getGet_total_follow() {
		return get_total_follow;
	}

	public void setGet_total_follow(Long get_total_follow) {
		this.get_total_follow = get_total_follow;
	}

	public Long getTotal_comment() {
		return total_comment;
	}

	public void setTotal_comment(Long total_comment) {
		this.total_comment = total_comment;
	}
	
	
}
