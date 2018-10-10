package com.xx.admin.service;

public interface AdminRoleService {
	
	/**
	 * 根据角色ID删除角色信息
	 * @param roleId
	 */
	public void role_delete(Integer roleId);
	
	/**
	 * 给角色授权
	 * @param roleId - 角色ID
	 * @param array - 授权的module ID
	 */
	public void role_adpower(Integer roleId,String[] array);
}
