package com.hx.core.pay.wxpay.bean.entpay;

import com.hx.core.annotation.Required;
import com.hx.core.pay.wxpay.util.ToStringUtils;
import com.hx.core.pay.wxpay.bean.request.BaseWxPayRequest;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

/**
 * <pre>
 * 企业付款请求对象.
 * Created by2016/10/19.
 * </pre>
 *
 *
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class EntPayQueryRequest extends BaseWxPayRequest {
  private static final long serialVersionUID = 1972288742207813985L;

  /**
   * <pre>
   * 字段名：商户订单号.
   * 变量名：partner_trade_no
   * 是否必填：是
   * 示例值：10000098201411111234567890
   * 类型：String
   * 描述商户订单号
   * </pre>
   */
  @Required
  @XStreamAlias("partner_trade_no")
  private String partnerTradeNo;

  @Override
  protected void checkConstraints() {
    //do nothing
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

  @Override
  protected boolean ignoreSignType() {
    return true;
  }
}
