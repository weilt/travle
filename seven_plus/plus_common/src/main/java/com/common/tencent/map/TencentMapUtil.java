package com.common.tencent.map;

import com.alibaba.fastjson.JSONObject;
import com.common.util.HttpClientUtil;
import com.common.util.MD5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/14 09:53
 * @Description:
 */
public class TencentMapUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(TencentMapUtil.class);

    /**
     * 腾讯地址地址
     */
     private static final String TENCENT_MAP_ADDRESS_URL = "https://apis.map.qq.com/ws/geocoder/v1?address={address}&key={key}";



     private static final String TENCENT_MAP_LOCATION_URL = "https://apis.map.qq.com/ws/geocoder/v1/?location={lat},{lng}&key={key}";


    /**
     *  通过地址获取经纬度
     * @param address 地址
     * @param key key
     * @return map {province,city,district,street,lng,lat}  street有可能为空
     */
     public static Map<String, String> getLocation(String address, String key){
         Map<String,String> result = new HashMap<>();
         String url = TENCENT_MAP_ADDRESS_URL.replace("{address}",address).replace("{key}",key);
         LOGGER.debug(url);
         JSONObject jsonObject = HttpClientUtil.httpGet(url);
         if (null == jsonObject){
             return null;
         }
         int code = jsonObject.getInteger("status");
         switch (code){
             case 0:
                 JSONObject resultJson = (JSONObject) jsonObject.get("result");
                 JSONObject locationJson = (JSONObject) resultJson.get("location");
                 JSONObject addressJson = (JSONObject) resultJson.get("address_components");
                 result.put("province",addressJson.getString("province"));
                 result.put("city",addressJson.getString("city"));
                 result.put("district",addressJson.getString("district"));
                 result.put("street",addressJson.getString("street"));
                 result.put("lng",locationJson.getString("lng"));
                 result.put("lat",locationJson.getString("lat"));
                 break;
             case 310:
                 LOGGER.error("请求腾讯地图请求参数信息有误 url:{}",url);
                 break;
             case 311:
                 LOGGER.error("请求腾讯地图Key格式错误 url:{}",url);
                 break;
             case 306:
                 LOGGER.error("请求腾讯地图请求有护持信息请检查字符串 url:{}",url);
                 break;
             case 110:
                 LOGGER.error("请求腾讯地图请求来源未被授权 url:{}",url);
                 break;
             case 111:
                 LOGGER.error("请求腾讯地图签名验证失败 url:{}",url);
                 break;
             default:
                 LOGGER.error("请求腾讯地图其他错误 url:{}",url);
         }
         return result;
     }


    /**
     *  通过经纬度获取省市区
     * @param lat 经度
     * @param lng 纬度
     * @param key key
     * @return
     */
     public static Map<String,String> getAddress(String lat,String lng,String key) {
         Map<String,String> result = new HashMap<>();
         String url = TENCENT_MAP_LOCATION_URL.replace("{lat}",lat).replace("{lng}",lng).replace("{key}",key);
         LOGGER.debug(url);
         JSONObject jsonObject = HttpClientUtil.httpGet(url);
         if (null == jsonObject){
             return null;
         }
         int code = jsonObject.getInteger("status");
         switch (code){
             case 0:
                 JSONObject resultJson = (JSONObject) jsonObject.get("result");
                 JSONObject addressJson = (JSONObject) resultJson.get("address_component");
                 result.put("province",addressJson.getString("province"));
                 result.put("city",addressJson.getString("city"));
                 result.put("district",addressJson.getString("district"));
                 result.put("street",addressJson.getString("street"));
                 break;
             case 310:
                 LOGGER.error("请求腾讯地图请求参数信息有误 url:{}",url);
                 break;
             case 311:
                 LOGGER.error("请求腾讯地图Key格式错误 url:{}",url);
                 break;
             case 306:
                 LOGGER.error("请求腾讯地图请求有护持信息请检查字符串 url:{}",url);
                 break;
             case 110:
                 LOGGER.error("请求腾讯地图请求来源未被授权 url:{}",url);
                 break;
             case 111:
                 LOGGER.error("请求腾讯地图签名验证失败 url:{}",url);
                 break;
             default:
                 LOGGER.error("请求腾讯地图其他错误 url:{}",url);
         }
         return result;
     }


    public static void main(String[] args) {
        System.out.println(getLocation("重庆市垫江县","GTYBZ-DAMW4-AM4UI-DV65U-BZ7QQ-6EFEZ"));
    }
}
