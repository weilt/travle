package com.hwt.domain.entity.cicerone.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 导游详情返回
 * @author Administrator
 *
 */
@ApiModel(value="导游详情返回")
public class HxCiceroneDetails {
	
	@ApiModelProperty(value = "是否收藏  0-否  1-是")
	private Integer is_collection;
	
	@ApiModelProperty(value = "导游信息详情")
	private HxCiceroneInfoVoInfo hxCiceroneInfoVoInfo;


	@ApiModelProperty(value = "收藏id")
	private Long collect_id;
	
	public Integer getIs_collection() {
		return is_collection;
	}

	public void setIs_collection(Integer is_collection) {
		this.is_collection = is_collection;
	}

	public HxCiceroneInfoVoInfo getHxCiceroneInfoVoInfo() {
		return hxCiceroneInfoVoInfo;
	}

	public void setHxCiceroneInfoVoInfo(HxCiceroneInfoVoInfo hxCiceroneInfoVoInfo) {
		this.hxCiceroneInfoVoInfo = hxCiceroneInfoVoInfo;
	}

	public Long getCollect_id() {
		return collect_id;
	}

	public void setCollect_id(Long collect_id) {
		this.collect_id = collect_id;
	}
	
}
