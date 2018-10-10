package com.admin.service.admin.impl;

import com.admin.service.admin.AdminRoleService;
import com.common.excption.BaseAdminException;
import com.common.excption.BaseException;
import com.common.util.ObjectHelper;
import com.domain.admin.mapper.AdminRoleMapper;
import com.domain.admin.mapper.AdminRolemoduleMapper;
import com.domain.admin.mapper.AdminUserMapper;
import com.domain.admin.entity.AdminRolemodule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

	@Autowired
	private AdminUserMapper adminUserMapper;
	@Autowired
	private AdminRolemoduleMapper adminRolemoduleMapper;
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	
	
	@Transactional
	public void role_delete(Integer roleId) {
		int userRoleIdCount = adminUserMapper.countByRoleId(roleId);
		if(userRoleIdCount > 0){
			throw new BaseAdminException("当前角色下还有用户存在，请转移用户信息后再删除该角色信息");
		}
		adminRolemoduleMapper.deleteByRoleId(roleId);
		adminRoleMapper.delete(roleId);
	}
	
	@Transactional
	public void role_adpower(Integer roleId, String[] array) {
		adminRolemoduleMapper.deleteByRoleId(roleId);
		if(ObjectHelper.isNotEmpty(array)){
			List<AdminRolemodule> list = new ArrayList<AdminRolemodule>();
			for (String mId : array) {
				AdminRolemodule rm = new AdminRolemodule();
				rm.setModuleId(Long.valueOf(mId));
				rm.setRoleId((long) roleId);
				list.add(rm);
			}
			adminRolemoduleMapper.savaRoleModule(list);
		}
	}

}
