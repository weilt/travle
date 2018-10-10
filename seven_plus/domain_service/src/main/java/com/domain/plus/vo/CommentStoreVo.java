package com.domain.plus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 17:05
 * @Description:
 */
@ApiModel(value = "网点评价")
@Data
public class CommentStoreVo implements Serializable {
    @ApiModelProperty(value = "用户电话")
    private String phone;
    @ApiModelProperty(value = "评价内容")
    private String comment;
    @ApiModelProperty(value = "时间")
    private Long createTime;
}
