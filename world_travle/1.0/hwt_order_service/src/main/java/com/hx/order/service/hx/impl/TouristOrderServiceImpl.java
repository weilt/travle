package com.hx.order.service.hx.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoUserInfo;
import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.vo.OrderInfoVO;
import com.hwt.domain.mapper.cicerone.HxCiceroneInfoMapper;
import com.hwt.domain.mapper.order.HwOrderMapper;
import com.hwt.domain.mapper.order.HxOrderInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.order.service.hx.TouristOrderService;

@Service
public class TouristOrderServiceImpl implements TouristOrderService{
	
	@Autowired
	private HxOrderInfoMapper hxOrderInfoMapper;
	
	@Autowired
	private HwOrderMapper hwOrderMapper;
	
	@Autowired
	private HxCiceroneInfoMapper hxCiceroneInfoMapper;
	
	@Autowired
	private HxUserMapper hxUserMapper;
	
	/**
	 * 一天的毫秒数
	 */
	private static Long oneDayMillis = 24*60*60*1000l;
	
	@Override
	public List<OrderInfoVO> qureyByMap(Map<String, Object> map) {
		List<OrderInfoVO> list = hwOrderMapper.qureyByMapToTourist(map);
		return list;
	}


	@Override
	@Transactional
	public void apply_refundable(Long user_id, Long order_id) {
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder==null){
			throw new RuntimeException("该订单不存在");
			
		}
		//导游
		if (hwOrder.getBureau_id()==0){
			Long create_time = hwOrder.getCreate_time();
			if ((System.currentTimeMillis()-create_time)<oneDayMillis){
				hwOrder.setStatus(4);
				hwOrder.setApply_sales_time(System.currentTimeMillis());
				hwOrder.setUpdate_time(System.currentTimeMillis());
				hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
			}else{
				throw new RuntimeException("订单超过1天不能退款");
			}
		}else{
			
		}
		
	}


	@Override
	@Transactional
	public void delete(Long user_id, Long order_id) {
		hwOrderMapper.tourist_delete(user_id,order_id);
		
	}


	@Override
	public HxCiceroneInfoUserInfo cicerone_im_id(Long cicerone_user_id) {
		HxCiceroneInfoUserInfo hxCiceroneInfoUserInfo = new HxCiceroneInfoUserInfo();
		 String im_id = hxUserMapper.queryImIdByUserId(cicerone_user_id);
		 if (StringUtils.isBlank(im_id)){
			 throw new RuntimeException("该用户不存在");
		 }
		 hxCiceroneInfoUserInfo.setIm_id(im_id);
		return hxCiceroneInfoUserInfo;
	}


	@Override
	public OrderInfoVO query_info_by_order_id_user_id(Long order_id, Long user_id) {
		OrderInfoVO orderInfoVO = hwOrderMapper.query_info_by_order_id_user_id(order_id,user_id);
		return orderInfoVO;
	}
	
}
