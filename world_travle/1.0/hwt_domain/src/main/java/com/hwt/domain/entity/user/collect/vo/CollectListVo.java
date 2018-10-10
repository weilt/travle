package com.hwt.domain.entity.user.collect.vo;

import java.util.List;

import com.hwt.domain.entity.es.ESQuery;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="收藏夹列表返回")
public class CollectListVo {
	
	/**
	 * 
	 */
	@ApiModelProperty(value = "收藏夹    用于排序")
	private List<HwUserCollectVo> hwUserCollectVos;
	/**
	 * 
	 */
	@ApiModelProperty(value = "收藏夹    收藏的实际类容")
	private List<ESQuery> dataList;
	public List<HwUserCollectVo> getHwUserCollectVos() {
		return hwUserCollectVos;
	}
	public void setHwUserCollectVos(List<HwUserCollectVo> hwUserCollectVos) {
		this.hwUserCollectVos = hwUserCollectVos;
	}
	public List<ESQuery> getDataList() {
		return dataList;
	}
	public void setDataList(List<ESQuery> dataList) {
		this.dataList = dataList;
	}
	
	
}
