package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/21 16:56
 * @Description:
 */
@ApiModel(value="短信参数")
@Data
public class SmsParam implements Serializable {
    @ApiModelProperty(value="电话号码")
    private String phone;
    @ApiModelProperty(value="验证码")
    private String code;
    @ApiModelProperty(value="推荐者id")
    private Long id;
}
