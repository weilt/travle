package com.domain.plus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 14:28
 * @Description:
 */
@ApiModel(value="订单使用")
@Data
public class OrderRecordApiVo implements Serializable {
    @ApiModelProperty(value = "id")
    private Long id;
    @ApiModelProperty(value = "车牌号")
    private String carNo;
    @ApiModelProperty(value = "提交时间")
    private Long createTime;
    @ApiModelProperty(value = "订单类型 1：天天洗车 2：划痕无忧")
    private Integer orderType;
    @ApiModelProperty(value = "状态 默认0 未审核 1：审核通过 2：审核失败")
    private Integer state;
    @ApiModelProperty(value = "上传图片")
    private String imgUrl;
    @ApiModelProperty(value = "网点名称")
    private String storeName;
    @ApiModelProperty(value = "网点电话")
    private String storePhone;
    @ApiModelProperty(value = "网点Id")
    private Long storeId;
    @ApiModelProperty(value = "网点经度")
    private String lat;
    @ApiModelProperty(value = "网点纬度")
    private String lng;
    @ApiModelProperty(value = "网点地址")
    private String address;
}
