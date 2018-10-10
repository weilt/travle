package com.hx.user.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.user.HxUser;
import com.hwt.domain.entity.user.Vo.HxUserNearbyVo;
import com.hwt.domain.entity.user.Vo.UserInfoVo;
import com.hwt.domain.mapper.user.HxUserInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.user.service.HxUserNearbyService;

@Service
public class HxUserNearbyServiceImp implements HxUserNearbyService {

	@Autowired
	private HxUserInfoMapper hxUserMapper;
	/**
	 * 查询不同距离的附近的人
	 *//*
	@Override
	public Map<String, Object> queryNearby(BigDecimal longitude, BigDecimal latitude) {
		Map<String, Object> map = new HashMap<>();
		System.out.println("--------------------------100--------");
		List<UserInfoVo> list100 = hxUserMapper.queryNearby(
				longitude, latitude, 
				longitude.add(new BigDecimal("0.001")), latitude.add(new BigDecimal("0.001")), 
				longitude, latitude, 
				longitude.subtract(new BigDecimal("0.001")), latitude.subtract(new BigDecimal("0.001")));
		map.put("list100", list100);
		System.out.println("--------------------------200--------");
		List<UserInfoVo> list200 = hxUserMapper.queryNearby(
				longitude.add(new BigDecimal("0.001")), latitude.add(new BigDecimal("0.001")), 
				longitude.add(new BigDecimal("0.002")), latitude.add(new BigDecimal("0.002")), 
				longitude.subtract(new BigDecimal("0.001")), latitude.subtract(new BigDecimal("0.001")),
				longitude.subtract(new BigDecimal("0.002")), latitude.subtract(new BigDecimal("0.002")));
		map.put("list200", list200);
		System.out.println("--------------------------300--------");
		List<UserInfoVo> list300 = hxUserMapper.queryNearby(
				longitude.add(new BigDecimal("0.002")), latitude.add(new BigDecimal("0.002")), 
				longitude.add(new BigDecimal("0.003")), latitude.add(new BigDecimal("0.003")), 
				longitude.subtract(new BigDecimal("0.002")), latitude.subtract(new BigDecimal("0.002")),
				longitude.subtract(new BigDecimal("0.003")), latitude.subtract(new BigDecimal("0.003")));
		map.put("list300", list300);
		System.out.println("--------------------------400--------");
		List<UserInfoVo> list400 = hxUserMapper.queryNearby(
				longitude.add(new BigDecimal("0.003")), latitude.add(new BigDecimal("0.003")), 
				longitude.add(new BigDecimal("0.004")), latitude.add(new BigDecimal("0.004")), 
				longitude.subtract(new BigDecimal("0.003")), latitude.subtract(new BigDecimal("0.003")),
				longitude.subtract(new BigDecimal("0.004")), latitude.subtract(new BigDecimal("0.004")));
		map.put("list400", list400);
		System.out.println("--------------------------500--------");
		List<UserInfoVo> list500 = hxUserMapper.queryNearby(
				longitude.add(new BigDecimal("0.004")), latitude.add(new BigDecimal("0.004")), 
				longitude.add(new BigDecimal("0.005")), latitude.add(new BigDecimal("0.005")), 
				longitude.subtract(new BigDecimal("0.004")), latitude.subtract(new BigDecimal("0.004")),
				longitude.subtract(new BigDecimal("0.005")), latitude.subtract(new BigDecimal("0.005")));
		map.put("list500", list500);
		System.out.println("--------------------------600--------");
		List<UserInfoVo> list600 = hxUserMapper.queryNearby(
				longitude.add(new BigDecimal("0.005")), latitude.add(new BigDecimal("0.005")), 
				longitude.add(new BigDecimal("0.006")), latitude.add(new BigDecimal("0.006")), 
				longitude.subtract(new BigDecimal("0.005")), latitude.subtract(new BigDecimal("0.005")),
				longitude.subtract(new BigDecimal("0.006")), latitude.subtract(new BigDecimal("0.006")));
		map.put("list600", list600);
		System.out.println("--------------------------700--------");
		List<UserInfoVo> list700 = hxUserMapper.queryNearby(
				longitude.add(new BigDecimal("0.006")), latitude.add(new BigDecimal("0.006")), 
				longitude.add(new BigDecimal("0.007")), latitude.add(new BigDecimal("0.007")), 
				longitude.subtract(new BigDecimal("0.006")), latitude.subtract(new BigDecimal("0.006")),
				longitude.subtract(new BigDecimal("0.007")), latitude.subtract(new BigDecimal("0.007")));
		map.put("list700", list700);
		System.out.println("--------------------------800--------");
		List<UserInfoVo> list800 = hxUserMapper.queryNearby(
				longitude.add(new BigDecimal("0.007")), latitude.add(new BigDecimal("0.007")), 
				longitude.add(new BigDecimal("0.008")), latitude.add(new BigDecimal("0.008")), 
				longitude.subtract(new BigDecimal("0.007")), latitude.subtract(new BigDecimal("0.007")),
				longitude.subtract(new BigDecimal("0.008")), latitude.subtract(new BigDecimal("0.008")));
		map.put("list800", list800);
		System.out.println("--------------------------900--------");
		List<UserInfoVo> list900 = hxUserMapper.queryNearby(
				longitude.add(new BigDecimal("0.008")), latitude.add(new BigDecimal("0.008")), 
				longitude.add(new BigDecimal("0.009")), latitude.add(new BigDecimal("0.009")), 
				longitude.subtract(new BigDecimal("0.008")), latitude.subtract(new BigDecimal("0.008")),
				longitude.subtract(new BigDecimal("0.009")), latitude.subtract(new BigDecimal("0.009")));
		map.put("list900", list900);
		System.out.println("--------------------------1000--------");
		List<UserInfoVo> list1000 = hxUserMapper.queryNearby(
				longitude.add(new BigDecimal("0.009")), latitude.add(new BigDecimal("0.009")), 
				longitude.add(new BigDecimal("0.010")), latitude.add(new BigDecimal("0.010")), 
				longitude.subtract(new BigDecimal("0.009")), latitude.subtract(new BigDecimal("0.009")),
				longitude.subtract(new BigDecimal("0.010")), latitude.subtract(new BigDecimal("0.010")));
		map.put("list1000", list1000);
		return map;
	}*/
	
	
	@Transactional
	@Override
	public List<HxUserNearbyVo> queryNearbyMap(Long user_id, Map<String, Object> map) {
		
		String longitude = (String) map.get("longitude"); 
		
		String latitude = (String) map.get("latitude");
		//跟新用户的经纬度
		hxUserMapper.updateUserInfo(longitude, latitude, user_id);
		
		List<HxUserNearbyVo> hxUserNearbyVos = hxUserMapper.queryNearbyMap(map);
		
		return hxUserNearbyVos;
	}
}
