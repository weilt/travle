package com.domain.plus.param;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 15:25
 * @Description:
 */
@Data
@ApiModel(value="获取价格参数")
public class CreatePriceParam implements Serializable {

    @ApiModelProperty(value="办理业务 1：天天洗车 2：划痕无忧")
    private Integer orderType;

    @ApiModelProperty(value="车辆ID")
    private Long carId;

    @ApiModelProperty(value="订单Id")
    private Long orderId;

}
