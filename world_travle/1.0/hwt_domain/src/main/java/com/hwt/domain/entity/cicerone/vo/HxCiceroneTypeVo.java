package com.hwt.domain.entity.cicerone.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/16 15:28
 * @Description:
 */

@ApiModel(value="导游类型")
public class HxCiceroneTypeVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value=" 类型ID")
    private Long id;
    @ApiModelProperty(value=" 类型名称")
    private String typeValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeValue() {
        return typeValue;
    }

    public void setTypeValue(String typeValue) {
        this.typeValue = typeValue;
    }
}
