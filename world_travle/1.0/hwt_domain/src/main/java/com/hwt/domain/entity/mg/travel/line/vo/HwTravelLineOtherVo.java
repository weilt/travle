package com.hwt.domain.entity.mg.travel.line.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 线路详情页面其他信息返回
 * @author Administrator
 *
 */
@ApiModel(value="线路详情页面其他信息返回")
public class HwTravelLineOtherVo {
	
	/**
	 * 线路id
	 */
	@ApiModelProperty(value = "线路id")
	private Long line_id;
	
	@ApiModelProperty(value = "线路id")
	private Long bureau_id;
	
	@ApiModelProperty(value = "客户电话")
	private String phone;
	
	@ApiModelProperty(value = "是否收藏  0-否  1-是")
	private Integer is_collection;
	
	@ApiModelProperty(value = "收藏id")
	private Long collect_id;

	public Long getLine_id() {
		return line_id;
	}

	public void setLine_id(Long line_id) {
		this.line_id = line_id;
	}

	public Long getBureau_id() {
		return bureau_id;
	}

	public void setBureau_id(Long bureau_id) {
		this.bureau_id = bureau_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getIs_collection() {
		return is_collection;
	}

	public void setIs_collection(Integer is_collection) {
		this.is_collection = is_collection;
	}

	public Long getCollect_id() {
		return collect_id;
	}

	public void setCollect_id(Long collect_id) {
		this.collect_id = collect_id;
	}
	
	
}
