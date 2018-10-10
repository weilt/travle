package com.hwt.domain.entity.mg.travel.line.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 线路
 * @author Administrator
 *
 */
@ApiModel(value="线路基本信息")
public class HwTravelLineBase {
	
	/**
	 * 线路id
	 */
	@ApiModelProperty(value = "线路id")
	private Long line_id; 
	
	/**
	 * 线路名称
	 */
	@ApiModelProperty(value = "线路名称")
	private Long line_name; 
	
	/**
	 * 封面图
	 */
	@ApiModelProperty(value = "封面图")
	private Long line_cover; 
	
	/**
	 * 价格
	 */
	@ApiModelProperty(value = "价格")
	private Long line_price;

	public Long getLine_id() {
		return line_id;
	}

	public void setLine_id(Long line_id) {
		this.line_id = line_id;
	}

	public Long getLine_name() {
		return line_name;
	}

	public void setLine_name(Long line_name) {
		this.line_name = line_name;
	}

	public Long getLine_cover() {
		return line_cover;
	}

	public void setLine_cover(Long line_cover) {
		this.line_cover = line_cover;
	}

	public Long getLine_price() {
		return line_price;
	}

	public void setLine_price(Long line_price) {
		this.line_price = line_price;
	} 
	
	
}
