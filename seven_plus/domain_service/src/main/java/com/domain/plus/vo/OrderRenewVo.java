package com.domain.plus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 11:24
 * @Description:
 */
@ApiModel(value="订单支付")
@Data
public class OrderRenewVo implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "车牌号")
    private String carNo;
    @ApiModelProperty(value = "支付金额（分）")
    private Long money;
    @ApiModelProperty(value = "下单时间")
    private Long createTime;
    @ApiModelProperty(value = "订单类型 1：天天洗车 2：划痕无忧")
    private Integer orderType;
    @ApiModelProperty(value = "到期时间")
    private Long updateTime;
    @ApiModelProperty(value = "支付状态 0：支付中，1：支付成功，2：支付失败")
    private Integer renewState;
    @ApiModelProperty(value = "车辆Id")
    private Long carId;
}
