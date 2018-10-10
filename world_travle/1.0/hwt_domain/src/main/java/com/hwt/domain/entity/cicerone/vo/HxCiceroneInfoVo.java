package com.hwt.domain.entity.cicerone.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/16 16:03
 * @Description:
 */
@ApiModel(value="导游列表")
public class HxCiceroneInfoVo extends HxCiceroneInfoBaseVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value=" 头像")
    private String user_icon;

	public String getUser_icon() {
		return user_icon;
	}
	
	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}
}
