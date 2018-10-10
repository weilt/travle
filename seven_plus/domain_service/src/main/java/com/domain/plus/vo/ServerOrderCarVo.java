package com.domain.plus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/9/5 11:31
 * @Description:
 */
@ApiModel(value = "车辆信息")
@Data
public class ServerOrderCarVo implements Serializable {
    @ApiModelProperty(value = "订单Id")
    private Long orderId;
    @ApiModelProperty(value = "车牌号")
    private String carNo;
}
