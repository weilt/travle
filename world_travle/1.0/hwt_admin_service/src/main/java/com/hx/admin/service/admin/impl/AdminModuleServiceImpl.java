package com.hx.admin.service.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.admin.AdminModule;
import com.hwt.domain.entity.admin.vo.AdminModuleVo;
import com.hwt.domain.mapper.admin.AdminModuleMapper;
import com.hx.admin.service.admin.AdminModuleService;
import com.hx.core.page.asyn.PageResult;

@Service
public class AdminModuleServiceImpl implements AdminModuleService{
	
	@Autowired
	private AdminModuleMapper adminModuleMapper;

	@Override
	public PageResult<AdminModuleVo> queryByMap(Map<String, Object> map) {
		PageResult<AdminModuleVo> pageResult = new PageResult<AdminModuleVo>();
		
		int count = adminModuleMapper.queryCountByMap(map);
		
		List<AdminModuleVo> dataList = adminModuleMapper.queryListByMap(map);
		
		pageResult.setCount(count);
		pageResult.setDataList(dataList);
		
		return pageResult;
	}
	
	@Transactional
	@Override
	public int insert(AdminModule adminModule) {
		adminModule.setCreateTime(new Date());
		return adminModuleMapper.insertSelective(adminModule);
	}
	
	@Override
	public AdminModule queryById(Integer id) {
		return adminModuleMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	@Override
	public int update(AdminModule adminModule) {
		return adminModuleMapper.updateByPrimaryKeySelective(adminModule);
	}
	
	@Transactional
	@Override
	public int deleteById(Integer id, Integer type) {
		return adminModuleMapper.deleteById(id,type);
	}

	@Override
	public List<AdminModule> queryAll() {
		// TODO Auto-generated method stub
		return adminModuleMapper.queryAll();
	}

	@Override
	public List<AdminModule> queryAllByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return adminModuleMapper.queryAllByRoleId(roleId);
	}

	
}
