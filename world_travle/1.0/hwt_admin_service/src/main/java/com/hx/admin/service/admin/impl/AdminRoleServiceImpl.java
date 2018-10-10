package com.hx.admin.service.admin.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.admin.AdminRole;
import com.hwt.domain.entity.admin.AdminRolemodule;
import com.hwt.domain.entity.admin.vo.AdminRoleToUserVo;
import com.hwt.domain.entity.admin.vo.AdminRoleVo;
import com.hwt.domain.mapper.admin.AdminRoleMapper;
import com.hwt.domain.mapper.admin.AdminRolemoduleMapper;
import com.hx.admin.service.admin.AdminRoleService;
import com.hx.core.page.asyn.PageResult;


@Service
public class AdminRoleServiceImpl implements AdminRoleService{
	
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	
	@Autowired
	private AdminRolemoduleMapper adminRolemoduleMapper;

	@Override
	public PageResult<AdminRoleVo> queryByMap(Map<String, Object> map) {
		PageResult<AdminRoleVo> pageResult = new PageResult<AdminRoleVo>();
		
		int count = adminRoleMapper.queryCountByMap(map);
		
		List<AdminRoleVo> dataList = adminRoleMapper.queryListByMap(map);
		
		pageResult.setCount(count);
		pageResult.setDataList(dataList);
		return pageResult;
	}
	
	@Transactional
	@Override
	public int insert(AdminRole adminRole) {
		
		int num = adminRoleMapper.insertSelective(adminRole);
		return num;
	}

	@Override
	public AdminRole queryById(Integer id) {
		return adminRoleMapper.selectByPrimaryKey(id);
	}
	
	@Transactional
	@Override
	public int update(AdminRole adminRole) {
		return adminRoleMapper.updateByPrimaryKeySelective(adminRole);
	}

	@Override
	public int deleteById(Integer id) {
		if(id==1){
			throw new RuntimeException("最高权限不能删除");
		}
		return adminRoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<AdminRoleToUserVo> queryToUser() {
		return adminRoleMapper.queryToUser();
	}

	@Override
	public int adpower(Integer id, Integer[] rightList) {
		adminRolemoduleMapper.deleteByRoleId(id);
		if(rightList!=null&&rightList.length>0){
			List<AdminRolemodule> list = new ArrayList<AdminRolemodule>();
			for (Integer mId : rightList) {
				AdminRolemodule rm = new AdminRolemodule();
				rm.setModuleId(Long.valueOf(mId));
				rm.setRoleId((long) id);
				list.add(rm);
			}
			adminRolemoduleMapper.savaRoleModule(list);
		}
		return 0;
	}
}
