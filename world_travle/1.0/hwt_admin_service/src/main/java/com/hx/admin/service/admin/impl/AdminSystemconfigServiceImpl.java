package com.hx.admin.service.admin.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.admin.AdminSystemconfig;
import com.hwt.domain.entity.admin.vo.AdminSystemconfigVo;
import com.hwt.domain.mapper.admin.AdminSystemconfigMapper;
import com.hx.admin.service.admin.AdminSystemconfigService;
import com.hx.core.page.asyn.PageResult;

@Service
public class AdminSystemconfigServiceImpl implements AdminSystemconfigService{
	
	@Autowired
	private AdminSystemconfigMapper adminSystemconfigMapper;

	@Override
	public PageResult<AdminSystemconfigVo> queryByMap(Map<String, Object> map) {
		PageResult<AdminSystemconfigVo> pageResult = new PageResult<AdminSystemconfigVo>();
		
		int count = adminSystemconfigMapper.queryCountByMap(map);
		
		List<AdminSystemconfigVo> dataList = adminSystemconfigMapper.queryListByMap(map);
		
		pageResult.setCount(count);
		pageResult.setDataList(dataList);
		return pageResult;
	}
	
	@Transactional
	@Override
	public int insert(AdminSystemconfig adminSystemconfig) {
		adminSystemconfig.setCreateTime(new Date());
		return adminSystemconfigMapper.insertSelective(adminSystemconfig);
	}

	@Override
	public AdminSystemconfig queryById(Integer id) {
		
		return adminSystemconfigMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	@Override
	public int update(AdminSystemconfig adminSystemconfig) {
		adminSystemconfig.setConfigKey(null);
		return adminSystemconfigMapper.updateByPrimaryKeySelective(adminSystemconfig);
	}

}
