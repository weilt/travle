package com.common.wechat.json;

import com.common.error.WxError;
import com.common.wechat.wxpay.bean.WxAccessToken;
import com.common.wechat.wxpay.bean.menu.WxMenu;
import com.common.wechat.wxpay.bean.result.WxMediaUploadResult;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class WxGsonBuilder {

  public static final GsonBuilder INSTANCE = new GsonBuilder();

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxAccessToken.class, new WxAccessTokenAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxMenu.class, new WxMenuGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMediaUploadResult.class, new WxMediaUploadResultAdapter());
  }

  public static Gson create() {
    return INSTANCE.create();
  }

}
