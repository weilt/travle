package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/9/3 10:03
 * @Description:
 */
@ApiModel(value="车牌号")
@Data
public class CarNoParam implements Serializable {
    @ApiModelProperty(value="车牌号")
    private String carNo;
}
