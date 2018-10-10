package com.common.wechat.wxpay.bean.result;

import com.common.util.ToStringUtils;
import com.common.wechat.json.WxGsonBuilder;
import lombok.Data;

import java.io.Serializable;

@Data
public class WxMediaUploadResult implements Serializable {
  private static final long serialVersionUID = 330834334738622341L;

  private String type;
  private String mediaId;
  private String thumbMediaId;
  private long createdAt;

  public static WxMediaUploadResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMediaUploadResult.class);
  }

  @Override
  public String toString() {
    return ToStringUtils.toSimpleString(this);
  }

}
