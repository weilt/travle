package com.hx.core.mongo.service;

import com.hx.core.mongo.provider.MongoProvider;
import com.hx.core.mongo.utils.JsonUtils;
import com.hx.core.mongo.utils.MongoConditionUtils;
import com.hx.core.mongo.value.*;
import com.mongodb.client.MongoCollection;
import org.apache.commons.lang3.StringUtils;
import org.bson.conversions.Bson;

import java.lang.reflect.InvocationTargetException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * Created by Ro on 2017/4/7.
 * 备注：wj11
 */

public class MongoService {

    private static MongoProvider mp = MongoProvider.getInstance();
//    static {
//        mp = MongoProvider.getInstance();
//    }

    /**
     * 添加
     * @param dbName        库名
     * @param collectionName 表名
     * @param insertMap
     */
    public static void insert(String dbName, String collectionName, Map<String, Object> insertMap) {
        MongoCollection collection = mp.getCollection(dbName,collectionName);
        if (insertMap != null) {
            mp.insert(collection,insertMap);
        }
    }

    /**
     * 添加
     * @param dbName        库名
     * @param collectionName 表名
     * @param t
     */
    public static <T> void insert(String dbName, String collectionName, T t) {
        MongoCollection collection = mp.getCollection(dbName,collectionName);
        if (t != null) {
            mp.insert(collection,t);
        }
    }

    /**
     * 添加有自增列的数据
     * @param dbName        库名
     * @param collectionName 表名
     * @param t
     * @param idName    自增列名称
     */
    public static synchronized <T> T insertIncId(String dbName, String collectionName, T t, String idName)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        MongoCollection collection = mp.getCollection(dbName,collectionName);
        if (t != null && !StringUtils.isBlank(idName)) {
            // 获取自增列ID
            String incIdName = "set"+idName.substring(0,1).toUpperCase() + idName.substring(1);
            Long incId = mp.getIncId(collection,idName);
            t.getClass().getMethod(incIdName, Long.class).invoke(t,incId);
            mp.insert(collection,t);
        }
        return t;
    }

    /**
     * 删除所有数据
     * @param dbName        库名
     * @param collectionName 表名
     */
    public static void deleteAll(String dbName, String collectionName) {
        MongoCollection collection = mp.getCollection(dbName,collectionName);
        mp.deleteAll(collection);
    }

    /**
     * 条件删除
     * 全等 AND 链接的条件
     * @param dbName        库名
     * @param collectionName 表名
     * @param filterMap
     * @return
     */
    public static boolean delete(String dbName, String collectionName, Map<String,Object> filterMap) {
        MongoCollection collection = mp.getCollection(dbName,collectionName);
        return mp.delete(collection, MongoConditionUtils.andEq(filterMap));
    }

    /**
     * 指定单条件删除
     * @param dbName        库名
     * @param collectionName 表名
     * @param filterKey     条件的字段
     * @param filterValue   条件的值
     * @param opt           条件运算符类型 如（ =，>,<等)
     * @return
     */
    public static boolean delete(String dbName, String collectionName,String filterKey,
                          Object filterValue,MongoOperator opt) {
        MongoCollection collection = mp.getCollection(dbName,collectionName);
        Bson b = MongoConditionUtils.one(new MongoQueryValue(opt,filterKey,filterValue));
        return mp.delete(collection,b);
    }

    /**
     * 复杂条件删除
     * 复杂多条件 and/or 交叉的条件
     * @param dbName            库名
     * @param collectionName    表名
     * @param condition         封装的条件对象
     * @return
     */
    public static boolean delete(String dbName, String collectionName, MongoQueryCondition condition) {
        MongoCollection collection = mp.getCollection(dbName,collectionName);
        return mp.delete(collection, MongoConditionUtils.createCondition(condition));
    }

    /**
     * 多条件 更新一条数据
     * 全等 and 链接的条件
     * @param dbName           库名
     * @param collectionName   表名
     * @param filterMap			条件map
     * @param updateMap			更新map
     * @return
     */
    public static boolean updateOne(String dbName, String collectionName, Map<String,Object> filterMap, Map<String, Object> updateMap) {
        MongoCollection collection = mp.getCollection(dbName,collectionName);
        return mp.updateOne(collection, MongoConditionUtils.andEq(filterMap),updateMap);
    }

    /**
     * 更新信息
     * 只需要一个条件
     * @param dbname        库名
     * @param collctionName 表名
     * @param filterKey     条件的字段
     * @param filterValue   条件的值
     * @param opt           条件运算符类型 如（ =，>,<等)
     * @return
     */
    public static boolean updateOne(String dbname, String collctionName,Map<String,Object> updateMap, String filterKey,
                             Object filterValue,MongoOperator opt) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        Bson b = MongoConditionUtils.one(new MongoQueryValue(opt,filterKey,filterValue));
        return mp.updateOne(collection,b,updateMap);
    }

    /**
     * 复杂更新
     * 复杂多条件 and/or 交叉的条件
     * @param dbname        库名
     * @param collctionName 表名
     * @param condition
     * @param updateMap
     * @return
     */
    public static boolean updateOne(String dbname, String collctionName,MongoQueryCondition condition, Map<String, Object> updateMap) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        return mp.updateOne(collection, MongoConditionUtils.createCondition(condition),updateMap);
    }

    /**
     * 查询是否存在
     * @param dbname
     * @param collectionName
     * @param filed     查询条件的字段名
     * @param value     查询条件的值
     * @return
     */
    public static boolean isExits(String dbname, String collectionName, String filed, Object value) {
        MongoCollection collection = mp.getCollection(dbname,collectionName);
        return mp.isExits(collection,MongoConditionUtils.one(new MongoQueryValue(filed,value)));
    }

    /**
     * 查询是否存在
     * @param dbname
     * @param collectionName
     * @param condition     查询条件对象
     * @return
     */
    public static boolean isExits(String dbname, String collectionName, MongoQueryCondition condition) {
        MongoCollection collection = mp.getCollection(dbname,collectionName);
        return mp.isExits(collection,MongoConditionUtils.createCondition(condition));
    }

    /**
     * 查询一条记录
     * 指定的单一查询条件
     * @param dbname        库名
     * @param collctionName 表名
     * @param queryValue    查询条件
     * @param projections   指定查询的字段集合
     * @return
     */
    public static Map<String,Object> findOne(String dbname, String collctionName
            , MongoQueryValue queryValue, MongoQueryProjections projections) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        if(queryValue != null) {
            return mp.findOne(collection,MongoConditionUtils.one(queryValue)
                    ,projections != null ? projections.getProjections() : new MongoQueryProjections().getProjections(),null);
        }
        return null;
    }

    /**
     * 查询一条记录
     * 指定的单一查询条件
     * @param dbname        库名
     * @param collctionName 表名
     * @param queryValue    查询条件
     * @param projections   指定查询的字段集合
     * @param tClass        要返回的类型
     * @return
     */
    public static <T> T findOne(String dbname, String collctionName
            , MongoQueryValue queryValue, MongoQueryProjections projections,Class<T> tClass) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        if(queryValue != null) {
            Map<String,Object> result = mp.findOne(collection,MongoConditionUtils.one(queryValue)
                    ,projections != null ? projections.getProjections() : new MongoQueryProjections().getProjections(),null);
            if(result != null) {
                return JsonUtils.json2Bean(JsonUtils.Bean2Json(result),tClass);
            }
        }
        return null;
    }

    /**
     * 查询一条记录
     * and链接的多条件查询
     * @param dbname        库名
     * @param collctionName 表名
     * @param queryValue    查询条件
     * @param projections   指定查询的字段集合
     * @return
     */
    public static Map<String,Object> findOne(String dbname, String collctionName, List<MongoQueryValue> queryValue, MongoQueryProjections projections) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        if(queryValue != null) {
            return mp.findOne(collection,MongoConditionUtils.manyAnd(queryValue)
                    ,projections != null ? projections.getProjections() : new MongoQueryProjections().getProjections(),null);
        }
        return null;
    }

    /**
     * 获取自增列ID
     * @param dbname
     * @param collctionName
     * @param idName
     */
    public static Long getIncId(String dbname, String collctionName,String idName) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        return mp.getIncId(collection,idName);
    }

    /**
     * 复杂查询一条记录
     * and/or 交叉的查询条件
     * @param dbname        库名
     * @param collctionName 表名
     * @param condition     组装的多条查询条件
     * @param projections   指定查询的字段集合
     * @return
     */
    public static Map<String,Object> findOne(String dbname, String collctionName, MongoQueryCondition condition, MongoQueryProjections projections) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        if(condition != null) {
            return mp.findOne(collection,MongoConditionUtils.createCondition(condition)
                    ,projections != null ? projections.getProjections() : new MongoQueryProjections().getProjections(),null);
        }
        return null;
    }

    /**
     * 条件查询
     * 指定的唯一条件查询
     * @param dbname        库名
     * @param collctionName 表名
     * @param queryValue	查询条件
     * @param sort	    排序
     * @param projection	指定查询的字段集合
     * @return
     */
    public static List<Map<String,Object>> findAnd(String dbname, String collctionName, MongoQueryValue queryValue, Sort sort, MongoQueryProjections projection) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        return mp.findAnd(collection,MongoConditionUtils.one(queryValue),sort,projection != null ? projection.getProjections() : new MongoQueryProjections().getProjections());
    }

    /**
     * 自定义复杂条件查询
     * and/or 的复杂条件查询
     * @param dbname        库名
     * @param collctionName 表名
     * @param condition	    查询条件
     * @param sort	    排序
     * @param projection	指定查询的字段集合
     * @return
     */
    public static List<Map<String,Object>> findCustom(String dbname, String collctionName,
                                               MongoQueryCondition condition, Sort sort, MongoQueryProjections projection) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        Bson n = MongoConditionUtils.createCondition(condition);
        List<Map<String,Object>> a = mp.findAnd(collection,MongoConditionUtils.createCondition(condition),sort,projection != null ? projection.getProjections() : new MongoQueryProjections().getProjections());
        return a;
    }
    
    public List<Map<String, Object>> findAndSorts(MongoCollection collection, Bson createCondition, Sort[] sorts,
			Object object) {
		// TODO Auto-generated method stub
		return null;
	}

    /**
     * 自定义复杂条件查询 多排序
     * and/or 的复杂条件查询
     * @param dbname        库名
     * @param collctionName 表名
     * @param condition	    查询条件
     * @param sorts	    排序
     * @param projection	指定查询的字段集合
     * @return
     */
    public static List<Map<String, Object>> findCustomSorts(String dbname,
			String collctionName, MongoQueryCondition condition, Sort[] sorts, MongoQueryProjections projection) {
    	MongoCollection collection = mp.getCollection(dbname,collctionName);
        Bson n = MongoConditionUtils.createCondition(condition);
        List<Map<String,Object>> a = mp.findAndSorts(collection,MongoConditionUtils.createCondition(condition),sorts,projection != null ? projection.getProjections() : new MongoQueryProjections().getProjections());
        return a;
	}
    /**
     * 分组查询
     * @param dbname
     * @param collctionName
     * @param aggregate     聚合函数的集合对象
     * @param projection
     * @param sort
     * @return
     */
    public static List<Map<String,Object>> findGroup(String dbname, String collctionName
            ,MongoAggregate aggregate,MongoQueryProjections projection,Sort sort) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
       return mp.findGroup(collection,aggregate.getGourps(),projection != null ? projection.getProjections() : null,sort);
    }

    /**
     * 查询所有
     * @param dbname
     * @param collctionName
     * @param sort
     * @param projection
     * @return
     */
    public static List<Map<String,Object>> findAll(String dbname, String collctionName,Sort sort, MongoQueryProjections projection ) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        return mp.findAll(collection,sort,projection != null ? projection.getProjections() : null);
    }
    
    /**
     * 查询所有      --多排序
     * @param dbname
     * @param collctionName
     * @param
     * @param projection
     * @return
     */
    public static List<Map<String, Object>> findALLSorts(String dbname, String collctionName,Sort[] sorts, MongoQueryProjections projection ) {
    	 MongoCollection collection = mp.getCollection(dbname,collctionName);
         return mp.findALLSorts(collection,sorts,projection != null ? projection.getProjections() : null);
	}

    /**
     * 复杂条件的分页查询
     * 用MongoQueryCondition自由组装的查询条件
     * @param dbname           库名
     * @param collctionName    表名
     * @param projection       指定查询或者屏蔽的字段集合
     * @param condition 	    条件
     * @param sort             排序
     * @param pageIndex     	页码 从1开始
     * @param pageSize 			每页条数
     * @return
     */
    public static List<Map<String,Object>> findByPage(String dbname, String collctionName,MongoQueryProjections projection,
                                               MongoQueryCondition condition,Sort sort, int pageIndex, int pageSize) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        return mp.findByPage(collection,MongoConditionUtils.createCondition(condition),sort,pageIndex,pageSize
                ,projection != null ? projection.getProjections() : new MongoQueryProjections().getProjections());
    }
    /**
     * 复杂条件的分页查询  ---多排序
     * 用MongoQueryCondition自由组装的查询条件
     * @param dbname           库名
     * @param collctionName    表名
     * @param projection       指定查询或者屏蔽的字段集合
     * @param condition 	    条件
     * @param sorts             排序
     * @param pageIndex     	页码 从1开始
     * @param pageSize 			每页条数
     * @return
     */
    public static List<Map<String,Object>> findByPageSorts(String dbname, String collctionName,MongoQueryProjections projection,
    		MongoQueryCondition condition,Sort[] sorts, int pageIndex, int pageSize) {
    	MongoCollection collection = mp.getCollection(dbname,collctionName);
    	return mp.findByPageSorts(collection,MongoConditionUtils.createCondition(condition),sorts,pageIndex,pageSize
    			,projection != null ? projection.getProjections() : new MongoQueryProjections().getProjections());
    }

    /**
     * 分页查询
     * 全等 全And关键字链接的查询条件 : where a = 1 and b = 1 and c = 1
     * @param dbname           库名
     * @param collctionName    表名
     * @param projection       指定查询或者屏蔽的字段集合
     * @param paramMap 	        条件
     * @param sort             排序
     * @param pageIndex     	页码 从1开始
     * @param pageSize 			每页条数
     * @return
     */
    public static List<Map<String,Object>> findByPageToOr(String dbname, String collctionName,MongoQueryProjections projection,
                                                      Map<String,Object> paramMap,Sort sort, int pageIndex, int pageSize) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        return mp.findByPage(collection,MongoConditionUtils.orEq(paramMap),sort,pageIndex,pageSize
                ,projection != null ? projection.getProjections() : new MongoQueryProjections().getProjections());
    }

    /**
     * 分页查询
     * 全等 全ro关键字链接的查询条件 : where a = 1 or b = 1 or c = 1
     * @param dbname           库名
     * @param collctionName    表名
     * @param projection       指定查询或者屏蔽的字段集合
     * @param paramMap 	        条件
     * @param sort             排序
     * @param pageIndex     	页码 从1开始
     * @param pageSize 			每页条数
     * @return
     */
    public static List<Map<String,Object>> findByPageToAnd(String dbname, String collctionName,MongoQueryProjections projection,
                                                           Map<String,Object> paramMap,Sort sort, int pageIndex, int pageSize) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        return mp.findByPage(collection,paramMap != null ? MongoConditionUtils.andEq(paramMap) : null,sort,pageIndex,pageSize
                ,projection != null ? projection.getProjections() : new MongoQueryProjections().getProjections());
    }

    /**
     * 条件查询总数
     * @param dbname           库名
     * @param collctionName    表名
     * @param condition			查询条件
     * @return
     */
    public static long findPageCount(String dbname, String collctionName, MongoQueryCondition condition) {
        MongoCollection collection = mp.getCollection(dbname,collctionName);
        return mp.findPageCount(collection,MongoConditionUtils.createCondition(condition));
    }

    /**
     * 获取最后一个指定表名
     * @param dbName
     * @param tableName
     * @return
     */
    public static String getMaxTableName(String dbName, String tableName) {
        List<String> tables = mp.getContainsCollections(dbName,tableName);
        if(tables != null && tables.size() > 0) {
            Collections.sort(tables);
            return tables.get(tables.size() - 1);
        }
        return null;
    }

    /**
     * 获取最后一个分表的数量
     * @param dbName
     * @param tableName
     * @return
     */
    public static long getLastTableCount(String dbName,String tableName) {
        if(StringUtils.isNotBlank(tableName)) {
           return mp.getCount(dbName,tableName);
        }
        return 0;
    }

    /**
     * 关闭链接
     */
    public void close() {
       mp.close();
    }
}
