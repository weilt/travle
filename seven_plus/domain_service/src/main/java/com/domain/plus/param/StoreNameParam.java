package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/23 16:04
 * @Description:
 */
@Data
@ApiModel(value="网点查询")
public class StoreNameParam extends StoreParam {
    @ApiModelProperty(value = "网点名称")
    private String storeName;
}
