package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/21 11:33
 * @Description:
 */
@ApiModel(value="汽车品牌")
@Data
public class CarBrandParam implements Serializable {
    @ApiModelProperty(value="车辆品牌")
    private String brand;
    @ApiModelProperty(value="当前页")
    private Integer pageNo;
    @ApiModelProperty(value="查询条数")
    private Integer pageSize;
}
