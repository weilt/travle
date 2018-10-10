package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/23 15:08
 * @Description:
 */
@Data
@ApiModel(value="使用订单")
public class OrderRecordParam implements Serializable {

    @ApiModelProperty(value="划痕申请Id")
    private Long id;

    @ApiModelProperty(value="订单Id")
    private Long orderId;

    @ApiModelProperty(value="网点id (划痕不传)")
    private Long storeId;

    @ApiModelProperty(value="洗车图片/划痕图片")
    private String imgUrl;

}
