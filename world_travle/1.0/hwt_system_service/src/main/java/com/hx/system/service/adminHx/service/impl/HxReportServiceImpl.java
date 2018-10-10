package com.hx.system.service.adminHx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwt.domain.entity.user.Vo.adminHxVo.HxUserReportVo;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.core.page.asyn.PageResult;
import com.hx.system.service.adminHx.service.HxReportService;

@Service
public class HxReportServiceImpl implements HxReportService{
	
	@Autowired
	private HxUserMapper hxUserMapper;

	@Override
	public PageResult<HxUserReportVo> queryByMap(Map<String, Object> map) {
		
		long count = hxUserMapper.queryHxUserReportCountByMap(map);
		
		List<HxUserReportVo> list = hxUserMapper.queryHxUserReportByMap(map);
		
		PageResult<HxUserReportVo> pageResult = new PageResult<HxUserReportVo>();
		
		pageResult.setCount(count);
		pageResult.setDataList(list);
		return pageResult;
	}

}
