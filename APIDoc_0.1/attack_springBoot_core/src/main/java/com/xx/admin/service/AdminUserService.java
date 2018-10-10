package com.xx.admin.service;

import com.xx.admin.entity.AdminUser;

public interface AdminUserService {

	
	/**
	 * 添加用户信息
	 * @param adminUser
	 */
	void AdminUserInsert(AdminUser adminUser,Integer userId);
	
	/**
	 * 修改用户信息
	 * @param adminUser
	 */
	void AdminUserUpdate(AdminUser adminUser) throws Exception;

	/**
	 * 更新后台用户登录信息
	 * @param id
	 * @return
	 */
	int adminUserLogin(Long id);
}
