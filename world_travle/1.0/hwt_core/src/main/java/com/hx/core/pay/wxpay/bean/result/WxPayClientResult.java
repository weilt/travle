package com.hx.core.pay.wxpay.bean.result;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/31 15:21
 * @Description: 前端参数
 */
@Data
@XStreamAlias("xml")
public class WxPayClientResult implements Serializable {
    private String appId;
    private String nonceStr;
    @XStreamAlias("package")
    private String packageData;
    private String signType;
    private String paySign;
    private String timeStamp;
}
