package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 16:12
 * @Description:
 */
@Data
@ApiModel(value="网点查询")
public class StoreParam implements Serializable {
    @ApiModelProperty(value = "1：天天洗车 2：划痕无忧")
    private Integer storeType;
    @ApiModelProperty(value = "当前页")
    private Integer pageNo;
    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;
}
