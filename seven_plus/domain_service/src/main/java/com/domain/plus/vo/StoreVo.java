package com.domain.plus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 15:51
 * @Description:
 */
@Data
@ApiModel(value = "网点信息")
public class StoreVo implements Serializable {
    @ApiModelProperty(value = "网点Id")
    private Long id;
    @ApiModelProperty(value = "网点名称")
    private String storeName;
    @ApiModelProperty(value = "网点地址")
    private String storeAddress;
    @ApiModelProperty(value = "网点经度")
    private String storeLat;
    @ApiModelProperty(value = "网点纬度")
    private String storeLon;
    @ApiModelProperty(value = "网点图片")
    private String storeImg;
    @ApiModelProperty(value = "网点订单数")
    private Integer orderCount;
}
