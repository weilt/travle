package com.hx.order.service.hx.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.vo.OrderInfoVO;
import com.hwt.domain.mapper.cicerone.HxCiceroneInfoMapper;
import com.hwt.domain.mapper.order.HwOrderMapper;
import com.hwt.domain.mapper.order.HxOrderInfoMapper;
import com.hx.order.service.hx.CiceroneOrderService;

@Service
public class CiceroneOrderServiceImpl implements CiceroneOrderService {
	@Autowired
	private HxOrderInfoMapper hxOrderInfoMapper;
	
	@Autowired
	private HwOrderMapper hwOrderMapper;
	
	@Autowired
	private HxCiceroneInfoMapper hxCiceroneInfoMapper;

	@Override
	public List<OrderInfoVO> qureyByMap(Map<String, Object> map) {
		List<OrderInfoVO> list = hwOrderMapper.qureyByMapToCicerone(map);
		return list;
	}

	@Override
	public OrderInfoVO query_info_by_order_id_cicerone_id(Long order_id, Long cicerone_id) {
		OrderInfoVO orderInfoVO = hwOrderMapper.query_info_by_order_id_cicerone_id(order_id,cicerone_id);
		return orderInfoVO;
	}

	@Override
	@Transactional
	public void confirm_order(Long order_id, Long cicerone_id) {
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder.getCicerone_id() != cicerone_id){
			throw new RuntimeException("非自己的订单无法操作");
		}
		if(hwOrder.getStatus()!=1){
			throw new RuntimeException("该订单已不能确认");
		}else{
			hwOrder.setStatus(2);
		}
		hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
	}

	@Override
	public void confirm_refundable(Long order_id, Long cicerone_id) {
		
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder.getCicerone_id() != cicerone_id){
			throw new RuntimeException("非自己的订单无法操作");
		}
		if(hwOrder.getStatus()==4){
			hwOrder.setStatus(5);
			hwOrder.setUpdate_time(System.currentTimeMillis());
			
			//退款业务
			
			
			hwOrder.setRefundable_time(System.currentTimeMillis());
			
		}
		hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
	}

}
