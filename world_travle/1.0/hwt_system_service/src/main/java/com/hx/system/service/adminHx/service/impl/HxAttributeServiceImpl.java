package com.hx.system.service.adminHx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.system.HwAttribute;
import com.hwt.domain.mapper.system.HwAttributeMapper;
import com.hx.core.page.asyn.PageResult;
import com.hx.system.service.adminHx.service.HxAttributeService;

@Service
public class HxAttributeServiceImpl implements HxAttributeService{
	
	@Autowired
	private HwAttributeMapper attributeMapper;

	@Override
	public PageResult<Map<String, Object>> queryByMap(Map<String, Object> map) {
		//总条数
		long count = attributeMapper.queryCountByMapToAdmin(map);
		
		//数据
		List<Map<String, Object>> list = attributeMapper.queryByMapToAdmin(map);
		PageResult<Map<String,Object>> pageResult = new PageResult<Map<String, Object>>();
		pageResult.setCount(count);
		pageResult.setDataList(list);
		return pageResult;
	}

	@Override
	@Transactional
	public void insert(HwAttribute attribute) {
		attribute.setCreate_time(System.currentTimeMillis());
		attribute.setUpdate_time(System.currentTimeMillis());
		attributeMapper.insertSelective(attribute);
	}

	@Override
	@Transactional
	public HwAttribute queryById(Integer attribute_id) {
		return attributeMapper.selectByPrimaryKey(attribute_id);
	}

	@Override
	@Transactional
	public void update(HwAttribute attribute) {
		attribute.setUpdate_time(System.currentTimeMillis());
		attributeMapper.updateByPrimaryKeySelective(attribute);
		
	}
	
	@Override
	public void deleteById(Integer attribute_id, Integer type) {
		attributeMapper.deleteById(attribute_id,type);
		
	}
	
}
