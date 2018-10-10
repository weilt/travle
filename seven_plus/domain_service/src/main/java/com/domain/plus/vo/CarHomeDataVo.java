package com.domain.plus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/21 11:38
 * @Description:
 */
@ApiModel(value="车辆信息")
@Data
public class CarHomeDataVo implements Serializable {
    /**
     * 主键
     *
     * isNullAble:0
     */
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "型号")
    private String model;
    @ApiModelProperty(value = "子型号")
    private String subModel;
}
