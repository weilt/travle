package com.hwt.domain.entity.area.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 热门城市
 * @author Administrator
 *
 */
@ApiModel(value=" 热门城市")
public class HotCityVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 地区编码
	 */
	@ApiModelProperty(value="地区编码")
	private String area_code; 
	
	/**
	 * 城市编码
	 * 
	 */
	@ApiModelProperty(value="城市编码")
	private String city_code;
	
	/**
	 * 城市名称
	 */
	@ApiModelProperty(value="城市名称")
	private String city_name;
	
	public HotCityVo(String area_code, String city_code, String city_name) {
		super();
		this.area_code = area_code;
		this.city_code = city_code;
		this.city_name = city_name;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getCity_name() {
		return city_name;
	}

	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	
	
	public static void main(String[] args) {
		
		int a = 104%30;
		System.out.println(a);
	}
	
}
