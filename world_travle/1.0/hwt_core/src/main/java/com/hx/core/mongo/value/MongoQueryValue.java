package com.hx.core.mongo.value;

import com.hx.core.mongo.exception.MongoBusinessException;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * mongo的查询条件基础对象
 * @author Ro
 *
 */
public class MongoQueryValue implements Serializable {

	private static final long serialVersionUID = -6470776687089309300L;
	
	/**
	 * 查询类型
	 */
	private MongoOperator key;
	/**
	 * 字段名称
	 */
	private String filed;
	/**
	 * 匹配值对象 
	 * 可为任意基础类型或者字符串
	 */
	private Object value;

	/**
	 * 默认等于的构造
	 * @param filed
	 * @param value
	 */
	public MongoQueryValue(String filed, Object value) {
		if(!isWrapClass(value.getClass())) {
			try {
				throw new MongoBusinessException("the MongoQueryValue construction method passed the wrong type to the field \"value\"");
			} catch (MongoBusinessException e) {
				e.printStackTrace();
			}
		}
		this.key = MongoOperator.eq;
		this.filed = filed;
		this.value = value;
	}

	public MongoQueryValue(MongoOperator key,String filed, Object value){
//		if(!isWrapClass(value.getClass())) {
//			try {
//				throw new MongoBusinessException("the MongoQueryValue construction method passed the wrong type to the field \"value\"");
//			} catch (MongoBusinessException e) {
//				e.printStackTrace();
//			}
//		}
		this.key = key;
		this.filed = filed;
		this.value = value;
	}

	public MongoOperator getKey() {
		return key;
	}

	public void setKey(MongoOperator key) {
		this.key = key;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

	public String getFiled() {
		return filed;
	}

	public void setFiled(String filed) {
		this.filed = filed;
	}
	
	/**
	 * 判断类型是否为基础类型
	 * @param clz
	 * @return
	 */
	private static boolean isWrapClass(Class clz) {
		try {
        	if(clz.equals(String.class) || clz.equals(MongoQueryValue.class)
					|| clz.equals(ArrayList.class) || clz.equals(Object[].class))
        		return true;
            return ((Class) clz.getField("TYPE").get(null)).isPrimitive();
        } catch (Exception e) { 
            return false; 
        } 
    } 
}
