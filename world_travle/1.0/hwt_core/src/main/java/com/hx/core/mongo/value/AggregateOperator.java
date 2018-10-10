package com.hx.core.mongo.value;

/**
 * Created by Ro on 2017/4/8.
 * mongo聚合操作符
 */
public enum AggregateOperator {

    /**
     * 按照给定表达式组合结果
     */
    group("$group"),
    /**
     * 查询，需要同find()一样的参数
     */
    match("$match"),
    /**
     * 求和
     */
    sum("$sum"),
    /**
     * 分割嵌入数组到自己顶层文件
     */
    unwind("$unwind");

    public String code;

    AggregateOperator(String code) {
        this.code = code;
    }
}
