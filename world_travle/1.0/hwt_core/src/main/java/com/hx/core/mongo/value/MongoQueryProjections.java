package com.hx.core.mongo.value;

import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ro on 2017/4/7.
 * 创建查询指定的字段
 * 需要的字段为1 不显示的为0
 */
public class MongoQueryProjections{

    /**
     * 显示
     */
    public static final Integer display = 1;
    /**
     * 不显示
     */
    public static final Integer none = 0;

    private Map<String,Object> projectionsMap;

    public MongoQueryProjections() {
        this.projectionsMap = new HashMap();
        projectionsMap.put("_id",0);
    }

    public MongoQueryProjections(String key, Object value) {
        this.projectionsMap = new HashMap();
        projectionsMap.put("_id",0);
        projectionsMap.put("key",value);
    }

//    public static MongoQueryProjections getInstance() {
//        return new MongoQueryProjections();
//    }

    public void put(String key, Object value) {
        projectionsMap.put(key,value);
    }

    public Document getProjections() {
        return new Document(projectionsMap);
    }

    public Map xx(){
        return projectionsMap;
    }
}
