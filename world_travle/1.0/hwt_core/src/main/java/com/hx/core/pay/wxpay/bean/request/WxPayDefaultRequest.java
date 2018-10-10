package com.hx.core.pay.wxpay.bean.request;

import com.thoughtworks.xstream.annotations.XStreamAlias;

/**
 * <pre>
 *  支付请求默认对象类
 *  Created by BinaryWang on 2017/6/18.
 * </pre>
 *
 *
 */
@XStreamAlias("xml")
public class WxPayDefaultRequest extends BaseWxPayRequest {
  @Override
  protected void checkConstraints() {
    //do nothing
  }
}
