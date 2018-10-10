package com.xx.admin.service;

import java.util.List;
import java.util.Map;

import com.xx.admin.entity.AdminModule;

public interface AdminModuleService {

	
	/**
	 * 查询分类的上级分类菜单
	 * Title: getRightPathIdToTop
	 * Description:
	 * @param module
	 * @return
	 */
	public String getRightPathIdToTop(AdminModule module);
	
	/**
	 * 查询模块菜单地址信息
	 * Title: getRightPathIdToTop
	 * Description:
	 * @param module
	 * @return
	 */
	public List<Map> getRightPathIdToTop(Map<String, Object> map);
	
	/**
	 * 根据父级ID检查下级信息并删除下级所有数据
	 * @param parentId
	 */
	public void deleteModuleParentId(int parentId);
}
