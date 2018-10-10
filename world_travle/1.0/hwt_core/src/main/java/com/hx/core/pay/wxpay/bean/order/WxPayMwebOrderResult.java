package com.hx.core.pay.wxpay.bean.order;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * <pre>
 * 微信H5支付统一下单后发起支付拼接所需参数实现类.
 * Created by2018-4-21.
 * </pre>
 *
 *
 */
@Data
@AllArgsConstructor
public class WxPayMwebOrderResult {
  @XStreamAlias("mwebUrl")
  private String mwebUrl;
}
