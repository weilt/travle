package com.hx.admin.service.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hwt.domain.entity.admin.AdminUser;
import com.hwt.domain.entity.admin.vo.AdminUserVo;
import com.hx.core.page.asyn.PageResult;

public interface AdminUserService {

	/**
	 * 通过条件获取用户信息
	 * @param map 条件
	 * @return
	 */
	PageResult<AdminUserVo> queryByMap(Map<String, Object> map);

	/**
	 * 用户添加
	 * @param adminUser
	 * @return
	 */
	int insert(AdminUser adminUser);

	/**
	 * 根据id返回对象
	 * @param user_id
	 * @return
	 */
	AdminUser queryById(Long user_id);

	/**
	 * 修改
	 * @param adminUser
	 * @return
	 */
	int update(AdminUser adminUser);
	
	/**
	 * 通过id删除模块
	 * @param id
	 * @param type 2-删除 1-恢复
	 * @return
	 */
	int deleteById(Long user_id, Integer type);

	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	String resetPassword(Long id);

	/**
	 * 登录 
	 * @param user_account 用户名
	 * @param password 密码
	 * @return
	 */
	void login(String user_account, String password, HttpServletRequest request);




}
