package com.hx.core.mongo.value;

import org.bson.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ro on 2017/4/8.
 * 组装mongo聚合基础对象
 */
public class MongoAggregate implements Serializable {

    private static final long serialVersionUID = -3230281975315030401L;

    private List<Document> gourps;

    private MongoAggregate() {
        gourps = new ArrayList<>();
    }

    public static MongoAggregate getInstance() {
        return new MongoAggregate();
    }

    public void put(AggregateOperator key, MongoAggregateValue value) {
        Document d = new Document(key.code,value);
        gourps.add(d);
    }

    public List<Document> getGourps() {
        return gourps;
    }
}
