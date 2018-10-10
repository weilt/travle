package com.hx.core.mongo.value;

import org.bson.Document;

import java.io.Serializable;

/**
 * Created by Ro on 2017/4/10.
 * mongo聚合函数的值对象
 * 用于构建聚合的条件 配合MongoAggregate 一起使用
 */
public class MongoAggregateValue extends Document implements Serializable {
    private static final long serialVersionUID = 8923026422171492447L;

    /**
     * 聚合函数的值对象构造
     * @param key   表示聚合的操作符字符串或者需要显示的自定义字段名
     * @param value 聚合函数的值对象（MongoAggregateValue） 或者 需要操作的表字段名称
     *              （注：字段名称需要带'$'符号）
     */
    public MongoAggregateValue(String key,Object value) {
        super(key,value);
    }

    /**
     * 聚合函数的值对象构造
     * 示例：
     *      { $group : { _id : {"cc":"$aa"},count:{$sum:1} } }
     *       MongoAggregateValue  mav = new MongoAggregateValue("_id",new MongoAggregateValue("cc","$aa"));
     *       mav.put("count",new MongoAggregateValue(AggregateOperator.sum.code,1));
     *
     * @param key   表示聚合的操作符字符串或者需要显示的自定义字段名
     * @param value
     * @return
     */
    public MongoAggregateValue put(String key, MongoAggregateValue value) {
        return (MongoAggregateValue)super.put(key, value);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
