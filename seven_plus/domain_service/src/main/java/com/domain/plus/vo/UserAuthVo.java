package com.domain.plus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 15:03
 * @Description:
 */
@ApiModel(value="用户权限信息")
@Data
public class UserAuthVo implements Serializable {

    @ApiModelProperty(value = "该用户id")
    private Long id;
    @ApiModelProperty(value = "是否办理洗车业务")
    private Boolean isWash;
    @ApiModelProperty(value = "是否办理划痕业务")
    private Boolean isNick;
    @ApiModelProperty(value = "VIP类型 默认0 无 1：黄金会员 2：铂金会员")
    private Integer vipType;
    @ApiModelProperty(value = "电话号码")
    private String phone;
    @ApiModelProperty(value = "会员天数（时间毫秒）")
    private Long remainDay;
    @ApiModelProperty(value = "佣金(分)")
    private Long brokerage = 0L;
    @ApiModelProperty(value = "推荐人Id（该属性大于零不具备推荐功能）")
    private Long parentId;
}
