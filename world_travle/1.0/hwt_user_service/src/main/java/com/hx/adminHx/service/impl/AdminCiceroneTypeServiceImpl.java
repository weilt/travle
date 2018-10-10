package com.hx.adminHx.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.cicerone.HxCiceroneType;
import com.hwt.domain.mapper.cicerone.HxCiceroneTypeMapper;
import com.hx.adminHx.service.AdminCiceroneTypeService;
import com.hx.core.page.asyn.PageResult;

@Service
public class AdminCiceroneTypeServiceImpl implements AdminCiceroneTypeService{
	
	@Autowired
	private HxCiceroneTypeMapper hxCiceroneTypeMapper;

	@Override
	public PageResult<Map<String, Object>> queryByMap(Map<String, Object> map) {
		long count = hxCiceroneTypeMapper.queryCountAllByMapToAdmin(map);
		List<Map<String, Object>> list = hxCiceroneTypeMapper.queryAllByMapToAdmin(map);
		PageResult<Map<String,Object>> pageResult = new PageResult<Map<String, Object>>();
		pageResult.setCount(count);
		pageResult.setDataList(list);
		return pageResult;
	}

	@Transactional
	@Override
	public void insert(HxCiceroneType ciceroneType) {
		
		ciceroneType.setCreate_time(new Date().getTime());
		ciceroneType.setUpdate_time(new Date().getTime());
		hxCiceroneTypeMapper.insertSelective(ciceroneType);
		
	}

	@Override
	public HxCiceroneType queryById(Long id) {
		return hxCiceroneTypeMapper.selectByPrimaryKey(id);
	}

	@Transactional
	@Override
	public void update(HxCiceroneType ciceroneType) {
		hxCiceroneTypeMapper.updateByPrimaryKeySelective(ciceroneType);
		
	}
}
