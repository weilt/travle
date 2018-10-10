package com.hwt.domain.entity.mg.travel.line.vo;

import java.util.List;

import com.hwt.domain.entity.order.vo.HwOrderCommentVo;
import com.hwt.domain.entity.system.HwAttribute;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 线路详情数据返回
 * @author Administrator
 *
 */
@ApiModel(value=" 线路详情数据返回")
public class HwTravelLineDetailsVo {

	@ApiModelProperty(value = "旅行社线路")
	private HwTravelLineVo hwTravelLineVo;
	
	@ApiModelProperty(value = "类型")
	private List<HwAttribute> line_types;
	
	@ApiModelProperty(value = "服务")
	private List<HwAttribute> line_services;
	
	@ApiModelProperty(value = "是否收藏  0-否  1-是")
	private Integer is_collection;
	
	@ApiModelProperty(value = "收藏id")
	private Long collect_id;
	
	@ApiModelProperty(value = "最近的一次好评")
	private HwOrderCommentVo hwOrderCommentVo;
	
	public HwTravelLineVo getHwTravelLineVo() {
		return hwTravelLineVo;
	}

	public void setHwTravelLineVo(HwTravelLineVo hwTravelLineVo) {
		this.hwTravelLineVo = hwTravelLineVo;
	}

	public List<HwAttribute> getLine_types() {
		return line_types;
	}

	public void setLine_types(List<HwAttribute> line_types) {
		this.line_types = line_types;
	}

	public List<HwAttribute> getLine_services() {
		return line_services;
	}

	public void setLine_services(List<HwAttribute> line_services) {
		this.line_services = line_services;
	}

	public Integer getIs_collection() {
		return is_collection;
	}

	public void setIs_collection(Integer is_collection) {
		this.is_collection = is_collection;
	}

	public HwOrderCommentVo getHwOrderCommentVo() {
		return hwOrderCommentVo;
	}

	public void setHwOrderCommentVo(HwOrderCommentVo hwOrderCommentVo) {
		this.hwOrderCommentVo = hwOrderCommentVo;
	}
	
	
}
