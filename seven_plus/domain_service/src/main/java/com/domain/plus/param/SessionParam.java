package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 15:43
 * @Description:
 */
@ApiModel(value="获取TOKEN参数")
@Data
public class SessionParam implements Serializable {

    @ApiModelProperty(value = "JS_CODE")
    private String code;

    @ApiModelProperty(value = "经度")
    private String lat;
    @ApiModelProperty(value = "纬度")
    private String lng;
}
