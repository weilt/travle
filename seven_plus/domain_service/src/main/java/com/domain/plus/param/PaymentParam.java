package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/23 09:24
 * @Description:
 */
@Data
@ApiModel(value="统一下单参数")
public class PaymentParam extends CreatePriceParam {

    @ApiModelProperty(value="支付类型 0新开通，1续费")
    private Integer paymentType;

    @ApiModelProperty(value="订单Id（续费时需要传）")
    private Long orderId;
}
