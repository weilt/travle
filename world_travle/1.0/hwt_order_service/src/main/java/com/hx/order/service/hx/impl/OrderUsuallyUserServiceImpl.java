package com.hx.order.service.hx.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.order.HwOrderUsuallyUser;
import com.hwt.domain.entity.order.vo.HwOrderUsuallyUserVo;
import com.hwt.domain.mapper.order.HwOrderUsuallyUserMapper;
import com.hx.order.service.hx.OrderUsuallyUserService;

@Service
public class OrderUsuallyUserServiceImpl implements OrderUsuallyUserService {
	
	@Autowired
	private HwOrderUsuallyUserMapper hwOrderUsuallyUserMapper;

	@Transactional
	@Override
	public void insert(HwOrderUsuallyUser hwOrderUsuallyUser) {
		//判断是否有10条了
		int count = hwOrderUsuallyUserMapper.queryCount(hwOrderUsuallyUser.getUser_id());
		if (count>=10){
			//删除超出9条的数据
			Long id = hwOrderUsuallyUserMapper.query_min(hwOrderUsuallyUser.getUser_id());
			hwOrderUsuallyUserMapper.delete_beyond(id);
		}
		//判断是否是本人
		if (hwOrderUsuallyUser.getIs_own()==1) {
			//将原本是本人的改为不是本人
			hwOrderUsuallyUserMapper.cancel_own(hwOrderUsuallyUser.getUser_id());
		}
		hwOrderUsuallyUser.setCreate_time(System.currentTimeMillis());
		hwOrderUsuallyUserMapper.insertSelective(hwOrderUsuallyUser);
		
	}

	@Override
	public List<HwOrderUsuallyUserVo> query(Long user_id) {
		
		return hwOrderUsuallyUserMapper.query(user_id);
	}

	@Override
	public HwOrderUsuallyUserVo queryDetails(Long user_id, Long usually_id) {
		
		return hwOrderUsuallyUserMapper.queryDetails(user_id,usually_id);
	}

	@Transactional
	@Override
	public void update(HwOrderUsuallyUserVo hwOrderUsuallyUserVo) {
		//查看是否是本人的
		HwOrderUsuallyUser hwOrderUsuallyUser = hwOrderUsuallyUserMapper.selectByPrimaryKey(hwOrderUsuallyUserVo.getUsually_id());
		
		if (hwOrderUsuallyUser.getUser_id()!=hwOrderUsuallyUserVo.getUser_id()){
			throw new RuntimeException("非本人不能操作");
		}
		hwOrderUsuallyUser.setBirthday(hwOrderUsuallyUserVo.getBirthday());
		hwOrderUsuallyUser.setEng_name(hwOrderUsuallyUserVo.getEng_name());
		hwOrderUsuallyUser.setEng_surname(hwOrderUsuallyUserVo.getEng_surname());
		hwOrderUsuallyUser.setIdentity_effective(hwOrderUsuallyUserVo.getIdentity_effective());
		hwOrderUsuallyUser.setIdentity_num(hwOrderUsuallyUserVo.getIdentity_num());
		hwOrderUsuallyUser.setIdentity_type(hwOrderUsuallyUserVo.getIdentity_type());
		hwOrderUsuallyUser.setIs_own(hwOrderUsuallyUserVo.getIs_own());
		hwOrderUsuallyUser.setName(hwOrderUsuallyUserVo.getName());
		hwOrderUsuallyUser.setPhone(hwOrderUsuallyUserVo.getName());
		hwOrderUsuallyUser.setSex(hwOrderUsuallyUserVo.getSex());
		//判断是否是本人
		if (hwOrderUsuallyUser.getIs_own()==1) {
			//将原本是本人的改为不是本人
			hwOrderUsuallyUserMapper.cancel_own(hwOrderUsuallyUser.getUser_id());
		}
		hwOrderUsuallyUserMapper.updateByPrimaryKeySelective(hwOrderUsuallyUser);
	}

	@Override
	public void delete(Long user_id, Long usually_id) {
		hwOrderUsuallyUserMapper.delete(user_id,usually_id);
		
	}
	
}
