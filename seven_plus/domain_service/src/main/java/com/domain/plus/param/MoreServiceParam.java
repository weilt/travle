package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/27 10:05
 * @Description:
 */
@ApiModel(value="更多服务")
@Data
public class MoreServiceParam implements Serializable {
    @ApiModelProperty(value = "文章分类 默认0 1： 划痕无忧  2：天天洗车 3：会员协议 4：车友群介绍 5:道路抢险 6：代办车贷")
    private Integer type;
}
