package com.admin.service.admin.impl;

import com.admin.service.admin.AdminUserService;
import com.common.excption.BaseAdminException;
import com.common.excption.BaseException;
import com.common.util.ObjectHelper;
import com.domain.admin.mapper.AdminRoleMapper;
import com.domain.admin.mapper.AdminSystemConfigMapper;
import com.domain.admin.mapper.AdminUserMapper;
import com.domain.admin.entity.AdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class AdminUserServiceImpl implements AdminUserService {

	@Autowired
	private AdminSystemConfigMapper adminSystemConfigMapper;
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	
	@Transactional
	public void AdminUserInsert(AdminUser adminUser,Integer userId) {
		if(adminUserMapper.findByUserNameCount(adminUser.getUserName()) > 0){
			throw new BaseAdminException("用户名已存在，请重新填写");
		}
		String initPassword = adminSystemConfigMapper.getValue("initPassword");// 系统初始化密码
		if (ObjectHelper.isEmpty(initPassword)) {
			initPassword = "123456";
		}
		adminUser.setPassWord(ObjectHelper.md5Hex(initPassword));
		adminUser.setUserId(userId);
		adminUser.setCreateTime(new Date());
		adminUser.setIsDelete((byte)1);
		adminRoleMapper.findUpdateNumber(adminUser.getRoleId(), 1);
		adminUserMapper.insert(adminUser);
	}
	
	@Transactional
	public void AdminUserUpdate(AdminUser adminUser) {
		AdminUser user = adminUserMapper.findById(adminUser.getId());
		if (!user.getRoleId().equals(adminUser.getRoleId())) {// 判断角色信息有没有改变 如果改变修改对应角色数量
			//-1
			adminRoleMapper.findUpdateNumber(user.getRoleId(), -1);
			//+1
			adminRoleMapper.findUpdateNumber(adminUser.getRoleId(), 1);
		}
		user.setRoleId(adminUser.getRoleId());
		user.setRealName(adminUser.getRealName());
		user.setSex(adminUser.getSex());
		user.setTelephone(adminUser.getTelephone());
		user.setAddress(adminUser.getAddress());
		adminUserMapper.update(user);
	}
	
}
