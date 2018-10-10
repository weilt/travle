package com.hx.core.mongo.utils;

import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryValue;
import com.mongodb.client.model.Filters;
import org.bson.*;
import org.bson.conversions.Bson;

import java.util.*;
import java.util.regex.Pattern;

public class MongoConditionUtils {

	/**
	 * 单自定义条件
	 * @param qv
	 * @return
	 */
	public static Bson one(MongoQueryValue qv) {
		return getBsonToValue(qv);
	}
	
	/**
	 * 多个自定义条件的and组合
	 * @param qvs
	 * @return
	 */
	public static Bson manyAnd(List<MongoQueryValue> qvs) {
		return Filters.and(getBsonToValue(qvs));
	}
	
	/**
	 * 多个自定义条件的or组合
	 * @param qvs
	 * @return
	 */
	public static Bson manyOr(List<MongoQueryValue> qvs) {
		return Filters.or(getBsonToValue(qvs));
	}
	
	/**
	 * 创建and链接全等于的条件
	 * @param queryMap
	 * @return
	 */
	public static Bson andEq(Map<String,Object> queryMap) {
		Set<String> key = queryMap.keySet();
		if(key.size() > 0) {
			List<Bson> bs = new ArrayList<>();
			for (String k : key) {
				
				bs.add(Filters.eq(k, queryMap.get(k)));
			}
			return Filters.and(bs);
		}
		return null;
	}
	
	/**
	 * 创建or链接全等于的条件
	 * @param queryMap
	 * @return
	 */
	public static Bson orEq(Map<String,Object> queryMap) {
		Set<String> key = queryMap.keySet();
		List<Bson> bs = new ArrayList<>();
		for (String k : key) {
			bs.add(Filters.eq(k, queryMap.get(k)));
		}
		return Filters.or(bs);
	}
	
	/**
	 * 复杂条件 多关键字(and/or)交叉链接的组装
	 * @param condition
	 * @return
	 */
	public static Bson createCondition(MongoQueryCondition condition) {
		if(condition.getLk().equals(MongoQueryCondition.LinkKey.and)) {
			if(condition.getCondtion() != null && condition.getCondtion().length > 0) {
				return Filters.and(getBsonToCondition(condition.getCondtion()));
			} 
			else if(condition.getQvs() != null && condition.getQvs().size() > 0){
				return Filters.and(getBsonToValue(condition.getQvs()));
			}
			else {
				return null;
			}
		} else {
			if(condition.getCondtion() != null && condition.getCondtion().length > 0) {
				return Filters.or(getBsonToCondition(condition.getCondtion()));
			} 
			else if(condition.getQvs() != null && condition.getQvs().size() > 0){
				return Filters.or(getBsonToValue(condition.getQvs()));
			} 
			else {
				return null;
			}
		}
	}

	private static List<Bson> getBsonToCondition(MongoQueryCondition[] conditions) {
		List<Bson> bs = null;
		if (conditions != null && conditions.length > 0) {
			bs = new ArrayList<>();
			for (MongoQueryCondition mqc : conditions) {
				Bson b = null;
				if(mqc.getCondtion() != null && mqc.getCondtion().length > 0) {
					if(mqc.getLk().equals(MongoQueryCondition.LinkKey.and))
						b = Filters.and(getBsonToCondition(mqc.getCondtion()));
					else
						b = Filters.or(getBsonToCondition(mqc.getCondtion()));
					bs.add(b);
				} 
				else if(mqc.getQvs() != null && mqc.getQvs().size() > 0){
					if(mqc.getLk().equals(MongoQueryCondition.LinkKey.and))
						b = Filters.and(getBsonToValue(mqc.getQvs()));
					else
						b = Filters.or(getBsonToValue(mqc.getQvs()));
					bs.add(b);
				}
			}
		}
		return bs;
	}
	
	private static List<Bson> getBsonToValue(List<MongoQueryValue> mqv) {
		List<Bson> bs = new ArrayList<>();
		for (MongoQueryValue qv : mqv) {
			Bson b = getBsonToValue(qv);
			bs.add(b);
		}
		return bs;
	}
	
	private static Bson getBsonToValue(MongoQueryValue qv) {
		MongoOperator opt = qv.getKey();
		Bson b = null;
		switch (opt) {
		case eq:
			b = Filters.eq(qv.getFiled(), qv.getValue());
			break;
		case gte:
			b = Filters.gte(qv.getFiled(), qv.getValue());
			break;
		case gt:
			b = Filters.gt(qv.getFiled(), qv.getValue());
			break;
		case in:
			if(qv.getValue() instanceof ArrayList)
				b = Filters.in(qv.getFiled(), (ArrayList)qv.getValue());
			else if(qv.getValue() instanceof Object[])
				b = Filters.in(qv.getFiled(), (Object[]) qv.getValue());
			break;
		case lt:
			b = Filters.lt(qv.getFiled(), qv.getValue());
			break;
		case lte:
			b = Filters.lte(qv.getFiled(), qv.getValue());
			break;
		case ne:
			b = Filters.ne(qv.getFiled(), qv.getValue());
			break;
		case nin:
			b = Filters.nin(qv.getFiled(), qv.getValue());
			break;
		case regex:
			Pattern p = Pattern.compile("^.*" + qv.getValue()+ ".*$"); // as SQL:  like " '%" + personName + "%' ";
			b = Filters.regex(qv.getFiled(), p);
			break;
		case notregex:
			Bson c = null; 
			Pattern p1 = Pattern.compile("^.*" + qv.getValue()+ ".*$"); // as SQL: not	 like " '%" + personName + "%' ";
			c = Filters.regex(qv.getFiled(), p1);
			b = Filters.not(c);
			break;
		default:
			break;
		}
		return b;
	}

	/**
	 * Java对象转BsonValue对象
	 * @param obj
	 * @return
	 */
	private static BsonValue objectToBsonValue(Object obj){

		if (obj instanceof Integer){
			return new BsonInt32((Integer) obj);
		}

		if (obj instanceof String){
			return new BsonString((String) obj);
		}

		if (obj instanceof Long){
			return new BsonInt64((Long) obj);
		}

		if (obj instanceof Date){
			return new BsonDateTime(((Date) obj).getTime());
		}
		return new BsonNull();
	}

	/**
	 * 集合装换成MONGO的查询数据类型
	 * 一般用于查询条件为多个并列值
	 * 如: $in : {"",""}
	 * @param obs
	 * @return
	 */
	public static BsonArray convertBsonArray(List obs) {
		BsonArray bsonList = null;
		if(obs != null && obs.size() > 0) {
			bsonList  = new BsonArray();
			for (Object o : obs) {
				BsonValue bv = objectToBsonValue(o);
				bsonList.add(bv);
			}
		}
		System.out.println("===== ======================="+bsonList);
		return bsonList;
	}

	/**
	 * 数组装换成MONGO的查询数据类型
	 * 一般用于查询条件为多个并列值
	 * 如: $in : {"",""}
	 * @param obs
	 * @return
	 */
	public static BsonArray convertBsonArray(Object[] obs) {
		BsonArray bsonList = null;
		if(obs != null && obs.length > 0) {
			bsonList  = new BsonArray();
			for (Object o : obs) {
				BsonValue bv = objectToBsonValue(o);
				bsonList.add(bv);
			}
		}
		return bsonList;
	}
}
