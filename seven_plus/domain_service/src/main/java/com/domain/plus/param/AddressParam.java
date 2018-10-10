package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/21 19:30
 * @Description:
 */
@ApiModel(value="地址")
@Data
public class AddressParam implements Serializable {
    @ApiModelProperty(value="id(删除修改时填写)")
    private Long id;
    @ApiModelProperty(value="地址")
    private String address;
}
