package com.hwt.domain.entity.cicerone.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 导游信息详情
 * @author Administrator
 *
 */
@ApiModel(value="导游信息详情")
public class HxCiceroneInfoVoInfo extends HxCiceroneInfoBaseVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 导游类型
	 */
	@ApiModelProperty(value="导游类型")
	private List<HxCiceroneTypeVo> ciceroneTypeVos = new ArrayList<>();
	
	public List<HxCiceroneTypeVo> getCiceroneTypeVos() {
		return ciceroneTypeVos;
	}

	public void setCiceroneTypeVos(List<HxCiceroneTypeVo> ciceroneTypeVos) {
		this.ciceroneTypeVos = ciceroneTypeVos;
	}
	
	
	
}
