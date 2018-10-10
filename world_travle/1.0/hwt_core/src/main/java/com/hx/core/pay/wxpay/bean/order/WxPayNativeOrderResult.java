package com.hx.core.pay.wxpay.bean.order;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <pre>
 * 微信扫码支付统一下单后发起支付拼接所需参数实现类
 * Created by2017-9-1.
 * </pre>
 *
 *
 */
@Data
@AllArgsConstructor
public class WxPayNativeOrderResult {
  private String codeUrl;
}
