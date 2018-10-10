package com.admin.service.admin;

import com.domain.admin.entity.AdminUser;

public interface AdminUserService {

	
	/**
	 * 添加用户信息
	 * @param adminUser
	 */
	void AdminUserInsert(AdminUser adminUser, Integer userId);
	
	/**
	 * 修改用户信息
	 * @param adminUser
	 */
	void AdminUserUpdate(AdminUser adminUser);
}
