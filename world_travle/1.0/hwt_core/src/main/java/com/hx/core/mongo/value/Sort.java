package com.hx.core.mongo.value;

import org.bson.Document;

/**
 * 排序 继承了mongo的Document
 * @author Ro
 *
 */
public class Sort extends Document{

	private static final long serialVersionUID = 599883138419317489L;
	
	public final static Integer ASC = 1;
	public final static Integer DESC = -1;

	/**
	 * 排序
	 * @param key		需要排序的字段
	 * @param value		排序的值 ASC = 1 ， DESC = -1
	 * 例 ： new Sort("id",Sort.ASC) 或者 new Sort("id",1)
	 */
	public Sort(String key, Integer value) {
		super(key,value);
	}
}
