package com.hwt.domain.entity.user.video.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="小视频他人基本数量展示")
public class HxUserVideoOtherBasicNumShow {
	
	/**
	 * 是否关注 0-否 1-是
	 */
	@ApiModelProperty(value="是否关注 0-否 1-是")
	private Integer is_follow;
	
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

	public Integer getIs_follow() {
		return is_follow;
	}

	public void setIs_follow(Integer is_follow) {
		this.is_follow = is_follow;
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
