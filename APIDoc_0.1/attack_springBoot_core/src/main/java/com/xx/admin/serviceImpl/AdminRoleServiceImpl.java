package com.xx.admin.serviceImpl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xx.admin.dao.AdminRoleDao;
import com.xx.admin.dao.AdminRolemoduleDao;
import com.xx.admin.dao.AdminUserDao;
import com.xx.admin.entity.AdminRolemodule;
import com.xx.admin.service.AdminRoleService;
import com.xx.springBootUtil.excption.BaseException;
import com.xx.springBootUtil.util.ObjectHelper;

@Service
public class AdminRoleServiceImpl implements AdminRoleService {

	@Resource
	private AdminUserDao adminUserDao;
	@Resource
	private AdminRolemoduleDao adminRolemoduleDao;
	@Resource
	private AdminRoleDao adminRoleDao;
	
	
	@Transactional
	public void role_delete(Integer roleId) {
		int userRoleIdCount = adminUserDao.countByRoleId(roleId);
		if(userRoleIdCount > 0){
			throw new BaseException("当前角色下还有用户存在，请转移用户信息后再删除该角色信息");
		}
		adminRolemoduleDao.deleteByRoleId(roleId);
		adminRoleDao.delete(roleId);
	}
	
	@Transactional
	public void role_adpower(Integer roleId, String[] array) {
		adminRolemoduleDao.deleteByRoleId(roleId);
		if(ObjectHelper.isNotEmpty(array)){
			List<AdminRolemodule> list = new ArrayList<AdminRolemodule>();
			for (String mId : array) {
				AdminRolemodule rm = new AdminRolemodule();
				rm.setModuleId(Long.valueOf(mId));
				rm.setRoleId((long) roleId);
				list.add(rm);
			}
			adminRolemoduleDao.savaRoleModule(list);
		}
	}

}
