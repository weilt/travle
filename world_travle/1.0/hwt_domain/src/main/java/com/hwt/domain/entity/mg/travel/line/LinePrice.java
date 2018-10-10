package com.hwt.domain.entity.mg.travel.line;

import java.io.Serializable;

/***
 * 线路日期价格
 * @author Administrator
 *
 */
public class LinePrice implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 日期    如 2018,08,25
	 */
	private String date;// 2018,08,25
	
	/**
	 * 成人价格
	 */
	private String adultPrice;
	
	/**
	 * 儿童价格
	 */
	private String childPrice;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getAdultPrice() {
		return adultPrice;
	}

	public void setAdultPrice(String adultPrice) {
		this.adultPrice = adultPrice;
	}

	public String getChildPrice() {
		return childPrice;
	}

	public void setChildPrice(String childPrice) {
		this.childPrice = childPrice;
	}
	
	
}
