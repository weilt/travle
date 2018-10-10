package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 16:54
 * @Description:
 */
@Data
@ApiModel(value="网点评价")
public class CommentParam implements Serializable {

    @ApiModelProperty(value = "网点Id")
    private Long storeId;
    @ApiModelProperty(value = "订单使用记录id")
    private Long recordId;
    @ApiModelProperty(value = "类型 默认0：好评 1：差评 2： TODO")
    private Integer type;
    @ApiModelProperty(value = "当前页")
    private Integer pageNo;
    @ApiModelProperty(value = "页面大小")
    private Integer pageSize;

}
