package com.hx.core.pay.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * <pre>
 * 撤销订单响应结果类
 * Created by2017-3-23.
 * </pre>
 *
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@XStreamAlias("xml")
public class WxPayOrderReverseResult extends BaseWxPayResult {

  /**
   * <pre>
   * 是否重调
   * recall
   * 是
   * String(1)
   * Y
   * 是否需要继续调用撤销，Y-需要，N-不需要
   * </pre>
   **/
  @XStreamAlias("recall")
  private String isRecall;

}
