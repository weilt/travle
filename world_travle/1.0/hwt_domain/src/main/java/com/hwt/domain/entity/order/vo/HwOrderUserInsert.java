package com.hwt.domain.entity.order.vo;

import java.io.Serializable;

import com.hwt.domain.entity.order.HwOrderUser;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="订单人员传参数")
public class HwOrderUserInsert extends HwOrderUser implements Serializable {
	
	

	/**
	 * 保险id集
	 */
	@ApiModelProperty(value = "保险id集     比如 1,2,3")
	private String insubrance_ids;

    private static final long serialVersionUID = 1L;

	public String getInsubrance_ids() {
		return insubrance_ids;
	}

	public void setInsubrance_ids(String insubrance_ids) {
		this.insubrance_ids = insubrance_ids;
	}

	

   
 
}