package com.hx.core.mongo.value;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * mongo条件组装对象
 * 如：where (a = 1 or a = 2) and (b = 1 or b = 2)
 * new MongoQueryCondition(MongoQueryCondition.LinkKey.and
 * 		,new MongoQueryCondition(MongoQueryCondition.LinkKey.or,new MongoQueryValue(MongoOperator.eq,"a",1),new MongoQueryValue(MongoOperator.eq,"a",2))
 * 		,new MongoQueryCondition(MongoQueryCondition.LinkKey.or,new MongoQueryValue(MongoOperator.eq,"b",1),new MongoQueryValue(MongoOperator.eq,"b",2)))
 */
public class MongoQueryCondition implements Serializable {

	private static final long serialVersionUID = 1330347558579707407L;

	public enum LinkKey{and,or;};
	
	/**
	 * 链接关键字
	 */
	private LinkKey lk;	
	/**
	 * mongo条件的基础对象(字段、条件运算符、值)
	 */
	private List<MongoQueryValue> qvs = new LinkedList<>();
	/**
	 * mongo条件的封装对象
	 * 对多条件 多链接类型的封装
	 */
	private MongoQueryCondition[] condtion;

	public MongoQueryCondition(LinkKey lk){
		this.lk = lk;
	}

	public MongoQueryCondition(LinkKey lk, MongoQueryCondition... condtion){
		this.lk = lk;
		this.condtion = condtion;
	}
	
	public MongoQueryCondition(LinkKey lk, MongoQueryValue... qvs) {
		this.lk = lk;
		for (MongoQueryValue qv : qvs) {
			this.qvs.add(qv);
		}
//		this.qvs = qvs;
	}

	public LinkKey getLk() {
		return lk;
	}

	public void setLk(LinkKey lk) {
		this.lk = lk;
	}

	public MongoQueryCondition[] getCondtion() {
		return condtion;
	}

	public void setCondtion(MongoQueryCondition[] condtion) {
		this.condtion = condtion;
	}

	public List<MongoQueryValue> getQvs() {
		return qvs;
	}

	public void setQvs(List<MongoQueryValue> qvs) {
		this.qvs = qvs;
	}

	public void add(MongoQueryValue value) {
		if(qvs == null) {
			qvs = new LinkedList<>();
		}
		qvs.add(value);
	}

	/**
	 * 清除查询条件
	 */
	public void clear (){
		if (null != qvs && !qvs.isEmpty()){
			qvs.clear();
		}
	}
}
