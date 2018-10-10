package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 17:25
 * @Description:
 */
@Data
@ApiModel(value="评价网点")
public class AddCommentParam implements Serializable {
    @ApiModelProperty(value = "网点Id")
    private Long storeId;
    @ApiModelProperty(value = "评价内容")
    private String comment;
    @ApiModelProperty(value = "类型 默认0：好评 1：差评 2： TODO")
    private Integer type;
    @ApiModelProperty(value = "使用记录Id")
    private Long recordId;
}
