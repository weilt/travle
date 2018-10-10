package com.hx.bureau.service.adminHx.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.admin.vo.AdminModuleVo;
import com.hwt.domain.entity.bureau.HxBureauModule;
import com.hwt.domain.mapper.bureau.HxBureauModuleMapper;
import com.hx.bureau.service.adminHx.HxBureauModuleService;
import com.hx.core.page.asyn.PageResult;

@Service
public class HxBureauModuleServiceImpl implements HxBureauModuleService{
	
	@Autowired
	private HxBureauModuleMapper hxBureauModuleMapper;
	@Override
	public PageResult<Map<String, Object>> queryByMap(Map<String, Object> map) {
		
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		
		int count = hxBureauModuleMapper.queryCountByMap(map);
		
		List<Map<String, Object>> dataList = hxBureauModuleMapper.queryListByMap(map);
		
		pageResult.setCount(count);
		pageResult.setDataList(dataList);
		
		return pageResult;
	}
	
	@Transactional
	@Override
	public void insert(HxBureauModule bureauModule) {
		bureauModule.setCreate_time(System.currentTimeMillis());
		hxBureauModuleMapper.insertSelective(bureauModule);
		
	}

	@Override
	public HxBureauModule queryById(Long bureau_module_id) {
		// TODO Auto-generated method stub
		return hxBureauModuleMapper.selectByPrimaryKey(bureau_module_id);
	}

	@Override
	public void update(HxBureauModule bureauModule) {
		hxBureauModuleMapper.updateByPrimaryKeySelective(bureauModule);
		
	}

	@Override
	public int deleteById(Integer id, Integer type) {
		
		return hxBureauModuleMapper.deleteById(id,type);
	}

}
