package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 10:50
 * @Description:
 */
@Data
@ApiModel(value="订单查询")
public class OrderParam implements Serializable {
    @ApiModelProperty(value = "类型 1：天天洗车 2：划痕无忧")
    private Integer orderType;
    @ApiModelProperty(value = "当前页")
    private Integer pageNo;
    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;
}
