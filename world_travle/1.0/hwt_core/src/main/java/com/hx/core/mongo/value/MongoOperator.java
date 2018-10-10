package com.hx.core.mongo.value;

/**
 * 条件运算符类型
 * @author Ro
 *
 */
/**
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public enum MongoOperator {
	/**
	 * 符号 “>”
	 */
	gt,
	/**
	 * 符号 “<”
	 */
	lt,
	/**
	 * 符号 “>=”
	 */
	gte,
	/**
	 * 符号 “<=”
	 */
	lte,
	/**
	 * 符号 “=”
	 */
	eq,
	/**
	 * 符号 “!=”
	 */
	ne,
	/**
	 * nin: not in
	 */
	nin,
	/**
	 * nin:in
	 */
	in,
	/**
	 * 模糊查询
	 */
	regex,
	
	/**
	 * not like
	 */
	notregex;
}
