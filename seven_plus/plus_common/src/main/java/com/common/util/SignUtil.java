package com.common.util;

import com.google.common.collect.Lists;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Created by zengyh on 2017/10/18.
 * 签名工具
 */
public class SignUtil {

    /**
     * 验证签名
     * @param obj
     * @param key
     * @param <T>
     * @return
     */
    public static <T> Boolean checkSign(T obj, String key, String sign){
        Field[] fields = obj.getClass().getDeclaredFields();
        for(Field field:fields){
            if(field.getName().equals("sign")){
                field.setAccessible(Boolean.TRUE);
                try {
                    field.set(obj,null);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
        return createSign(obj, key).toUpperCase().equals(sign.toUpperCase());
    }

    /**
     * 生成签名
     * @param obj
     * @param key
     * @param <T>
     * @return
     */
    public static <T> String createSign(T obj, String key){
        String signData = createSignData(obj, "&");
        String attachKey = signData + "&key=" + key;
        return DigestUtil.md5(attachKey, DigestUtil.Charset.UFT_8);
    }

    /**
     * Map生成签名
     * @param map
     * @param key
     * @return
     */
    public static String createSign(Map map, String key){
        String signData = createSignData(map, "&");
        String attachKey = signData + "&key=" + key;
        return DigestUtil.md5(attachKey, DigestUtil.Charset.UFT_8);
    }


    /**
     * 拼接对象属性 KEY=VALUE & KEY=VALUE & ...
     * @param obj
     * @param delimiter
     * @param <T>
     * @return
     */
    public static <T> String createSignData(T obj, CharSequence delimiter) {
        Field[] fields = obj.getClass().getDeclaredFields();
        //拼接列表
        List<String> properties = Lists.newArrayList();
        for(Field field:fields){
            field.setAccessible(Boolean.TRUE);
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            //字段为空不参与加密
            if(value != null) {
                //拼接 key=value
                properties.add(field.getName() + "=" + value);
            }
        }

        //按字段名称ASCII码从小到大排序
        Collections.sort(properties, String::compareTo);
        //key=value按分隔符拼接
        return properties.stream().collect(Collectors.joining(delimiter));
    }




    /**
     * 拼接签名数据 KEY=VALUE & KEY=VALUE & ...
     * @param params
     * @param delimiter
     * @return
     */
    public static String createSignData(Map<String,Object> params, CharSequence delimiter){
        return createSignData(params,"=",delimiter);
    }
    /**
     * 拼接签名数据 KEY=VALUE & KEY=VALUE & ...
     * @param params
     * @param delimiter
     * @return
     */
    public static String createSignData(Map<String,Object> params,CharSequence kvdelimiter, CharSequence delimiter){

        String kvdelimiterStr=kvdelimiter==null?"":kvdelimiter.toString();

        TreeMap<String,Object> sortedParams = new TreeMap<String,Object>(params);

        String signData = sortedParams.entrySet().stream().map((o)->o.getKey()+ kvdelimiterStr + o.getValue()).collect(Collectors.joining(delimiter));
        return signData;
    }



}
