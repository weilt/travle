package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 15:38
 * @Description:
 */
@ApiModel(value="首页参数")
public class IndexParam implements Serializable {
    @ApiModelProperty(value = "类型 1：天天洗车 2：划痕无忧")
    private Integer type;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
