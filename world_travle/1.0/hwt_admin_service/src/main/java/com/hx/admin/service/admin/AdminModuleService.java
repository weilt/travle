package com.hx.admin.service.admin;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.admin.AdminModule;
import com.hwt.domain.entity.admin.vo.AdminModuleVo;
import com.hx.core.page.asyn.PageResult;

public interface AdminModuleService {

	/**
	 * 通过条件返回数据
	 * @param map
	 * @return
	 */
	PageResult<AdminModuleVo> queryByMap(Map<String, Object> map);

	/**
	 * 模块添加
	 * @param adminModule
	 * @return
	 */
	int insert(AdminModule adminModule);

	/**
	 * 通过id返回模块
	 * @param id
	 * @return
	 */
	AdminModule queryById(Integer id);

	/**
	 * 更新模块
	 * @param adminModule
	 * @return
	 */
	int update(AdminModule adminModule);

	/**
	 * 通过id删除模块
	 * @param id
	 * @param type 2-删除 1-恢复
	 * @return
	 */
	int deleteById(Integer id, Integer type);

	/**
	 * 查询所有的模块
	 * @return
	 */
	List<AdminModule> queryAll();

	/**
	 * 查询该角色下的所有模块
	 * @param roleId 角色id
	 * @return
	 */
	List<AdminModule> queryAllByRoleId(Integer roleId);

}
