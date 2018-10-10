package com.hwt.domain.entity.mg.scenic.vo;

import com.hwt.domain.entity.mg.travel.line.vo.HwTravelLineBase;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 查询景点详情
 * @author Administrator
 *
 */
@ApiModel(value="查询景点详情")
public class QueryScenicSpotDetailsVo {
	
	@ApiModelProperty(value = "景点详情")
	private ScenicSpotVO scenicSpotVO;
	
	@ApiModelProperty(value = "线路基本信息")
	private HwTravelLineBase hwTravelLineBase;
	
	@ApiModelProperty(value = "是否收藏  0-否  1-是")
	private Integer is_collection;
	
	@ApiModelProperty(value = "收藏id")
	private Long collect_id;

	public ScenicSpotVO getScenicSpotVO() {
		return scenicSpotVO;
	}

	public void setScenicSpotVO(ScenicSpotVO scenicSpotVO) {
		this.scenicSpotVO = scenicSpotVO;
	}

	public HwTravelLineBase getHwTravelLineBase() {
		return hwTravelLineBase;
	}

	public void setHwTravelLineBase(HwTravelLineBase hwTravelLineBase) {
		this.hwTravelLineBase = hwTravelLineBase;
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
