package com.hx.core.pay.wxpay.json;

import com.hx.core.pay.wxpay.bean.menu.WxMenu;
import com.hx.core.pay.wxpay.bean.result.WxMediaUploadResult;
import com.hx.core.pay.wxpay.error.WxError;
import com.hx.core.pay.wxpay.bean.WxAccessToken;
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
