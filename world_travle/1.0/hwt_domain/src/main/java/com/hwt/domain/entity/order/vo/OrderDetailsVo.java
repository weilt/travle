package com.hwt.domain.entity.order.vo;

import java.io.Serializable;
import java.util.List;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.HwOrderRefund;
import com.hwt.domain.entity.order.HwOrderUser;
import com.hwt.domain.entity.order.HxOrderInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 订单详情
 * @author Administrator
 *
 */
@ApiModel(value="订单详情")
public class OrderDetailsVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 订单基本信息
	 */
	@ApiModelProperty(value = "订单基本信息" )
	private HwOrderVo hwOrderVo;
	
	/**
	 * 订单用户信息
	 */
	@ApiModelProperty(value = "订单用户信息" )
	private List<HwOrderUserVo> hwOrderUserVos;
	
	/**
	 * 订单商品信息
	 */
	@ApiModelProperty(value = "订单商品信息" )
	private HxOrderInfoVo hxOrderInfoVo;
	
	/**
	 * 订单退款信息
	 */
	@ApiModelProperty(value = "订单退款信息" )
	private HwOrderRefundVo hwOrderRefundVo;

	public HwOrderVo getHwOrderVo() {
		return hwOrderVo;
	}

	public void setHwOrderVo(HwOrderVo hwOrderVo) {
		this.hwOrderVo = hwOrderVo;
	}
	
	
	public List<HwOrderUserVo> getHwOrderUserVos() {
		return hwOrderUserVos;
	}

	public void setHwOrderUserVos(List<HwOrderUserVo> hwOrderUserVos) {
		this.hwOrderUserVos = hwOrderUserVos;
	}

	public HxOrderInfoVo getHxOrderInfoVo() {
		return hxOrderInfoVo;
	}

	public void setHxOrderInfoVo(HxOrderInfoVo hxOrderInfoVo) {
		this.hxOrderInfoVo = hxOrderInfoVo;
	}

	public HwOrderRefundVo getHwOrderRefundVo() {
		return hwOrderRefundVo;
	}

	public void setHwOrderRefundVo(HwOrderRefundVo hwOrderRefundVo) {
		this.hwOrderRefundVo = hwOrderRefundVo;
	}
}
