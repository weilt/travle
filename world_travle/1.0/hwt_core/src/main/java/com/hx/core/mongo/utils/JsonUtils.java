package com.hx.core.mongo.utils;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * json的工具类
 */
public final class JsonUtils {


    private final static ObjectMapper objectMapper = new ObjectMapper();

    private static Gson gson = new Gson();
    public static String Bean2Json(Object object) {
        return gson.toJson(object);
    }

    public static <T> T json2Bean(String jsonString, Class<T> c) {
       return gson.fromJson(jsonString,c);
    }



    /**
     * json转换成对应的list的javabean
     * @param <T>
     * @return
     */
    public static <T> List<T> json2List(String json,Class<T> c){
        List<T> list = null;
        try {
            /**
             * json的转换的特殊情况的操作constructParametrizedType采用这个方法来实现,
             * 1.个参数是:转换的接口类型，
             * 2.需要转成实现的类型
             * 3.需要转换成javabean的类型
             */
            JavaType javaType = objectMapper.getTypeFactory().constructParametrizedType(List.class, ArrayList.class,c);
            list = objectMapper.readValue(json,javaType);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    /**
     * json转换成map
     * @param json
     * @return
     */
    public static Map<String,Object> json2Map(String json){

        Map<String,Object>  map = null;
        try {
            map = objectMapper.readValue(json, HashMap.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return map;
    }
}
