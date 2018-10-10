package com.hwt.domain.entity.mg.opinion;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 意见反馈
 * @author Administrator
 *
 */
@ApiModel(value="意见反馈")
public class HxOpinion {
	
	/**
	 * id
	 */
	@ApiModelProperty(value="id",hidden=true)
	private Long opinion_id;
	
	/**
	 * 用户id
	 */
	@ApiModelProperty(value="用户id",hidden=true)
	private Long user_id;
	
	/**
	 * 内容
	 */
	@ApiModelProperty(value="内容")
	private String content;
	
	/**
	 * 内容
	 */
	@ApiModelProperty(value="联系方式")
	private String phone_or_email;
	
	/**
	 * 图片 不得超过5张 用,隔开
	 */
	@ApiModelProperty(value="图片 不得超过5张 用,隔开")
	private String images;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="图片 不得超过5张 用,隔开",hidden=true)
	private Long create_time;
	
	/**
	 * 是否处理  0-否  1-是
	 */
	@ApiModelProperty(value="是否处理  0-否  1-是",hidden=true)
	private Integer is_handle;

	public Long getOpinion_id() {
		return opinion_id;
	}

	public void setOpinion_id(Long opinion_id) {
		this.opinion_id = opinion_id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

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

	public Integer getIs_handle() {
		return is_handle;
	}

	public void setIs_handle(Integer is_handle) {
		this.is_handle = is_handle;
	}

	public String getPhone_or_email() {
		return phone_or_email;
	}

	public void setPhone_or_email(String phone_or_email) {
		this.phone_or_email = phone_or_email;
	}
	
}