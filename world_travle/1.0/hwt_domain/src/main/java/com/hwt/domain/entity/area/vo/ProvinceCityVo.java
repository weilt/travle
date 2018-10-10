package com.hwt.domain.entity.area.vo;

import java.util.ArrayList;
import java.util.List;
/**
 * 省市
 * @author Administrator
 *
 */
public class ProvinceCityVo {
	private String province_code;
	private String province_name;
	private List<CityVo> citys = new ArrayList<>();
	public String getProvince_code() {
		return province_code;
	}
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	public String getProvince_name() {
		return province_name;
	}
	public void setProvince_name(String province_name) {
		this.province_name = province_name;
	}
	public List<CityVo> getCitys() {
		return citys;
	}
	public void setCitys(List<CityVo> citys) {
		this.citys = citys;
	}
	
}
