package com.common.wechat.authorize;

import com.alibaba.fastjson.JSONObject;
import com.common.util.HttpClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/7 10:03
 * @Description: 小程序授权类
 */
public class MicroAuthorize {

    private static final Logger LOGGER = LoggerFactory.getLogger(MicroAuthorize.class);

    /**
     * 微信小程序获取 https://developers.weixin.qq.com/miniprogram/dev/api/api-login.html#wxloginobject
     * 'https://api.weixin.qq.com/sns/jscode2session?appid='.APPID.'&secret='.SECRET.'&js_code='.$this->js_code.'&grant_type='.GRANTTYPE
     */
    private static final String WECHAT_LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session?appid={appid}&secret={secret}&js_code={js_code}&grant_type=authorization_code";

    /**
     * 微信小程序授权
     * @param appid 小程序唯一标识
     * @param secret 小程序的 app secret
     * @param js_code 	登录时获取的 code
     * @return {"openid": "OPENID",	"session_key": "SESSIONKEY" }  {"errcode": 40029,"errmsg": "invalid code" }
     */
    public static Map<String, Object> authorize(String appid , String secret, String js_code){
        String url = WECHAT_LOGIN_URL.replace("{appid}",appid).replace("{secret}",secret).replace("{js_code}",js_code);
        JSONObject jsonObject = HttpClientUtil.httpGet(url);
        LOGGER.info(" 获取数据:{} ",jsonObject);
        if (jsonObject.containsKey("errcode")){
            LOGGER.info("获取openid失败 JS_CODE:{}",js_code);
            return null;
        } else {
            return jsonObject;
        }
    }

}
