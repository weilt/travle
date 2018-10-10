package com.hwt.domain.entity.cicerone.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 导游在淮信的基本信息
 * @author Administrator
 *
 */
@ApiModel(value=" 导游在淮信的基本信息")
public class HxCiceroneInfoUserInfo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="云信im_id")
	private String im_id;

	public String getIm_id() {
		return im_id;
	}

	public void setIm_id(String im_id) {
		this.im_id = im_id;
	}
	
	
}
