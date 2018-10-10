package com.hx.admin.service.admin;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.admin.AdminRole;
import com.hwt.domain.entity.admin.vo.AdminRoleToUserVo;
import com.hwt.domain.entity.admin.vo.AdminRoleVo;
import com.hx.core.page.asyn.PageResult;

public interface AdminRoleService {

	/**
	 * 根据条件返回数据
	 * @param map  条件集
	 * @return
	 */
	PageResult<AdminRoleVo> queryByMap(Map<String, Object> map);

	/**
	 * 角色添加
	 * @param adminRole
	 * @return
	 */
	int insert(AdminRole adminRole);

	/**
	 * 通过id获取角色
	 * @param id
	 * @return
	 */
	AdminRole queryById(Integer id);

	/**
	 * 修改
	 * @param adminRole
	 * @return
	 */
	int update(AdminRole adminRole);

	/**
	 * 通过id删除
	 * @param id  
	 * @return
	 */
	int deleteById(Integer id);

	/**
	 * 获取信息给用户操作
	 * @return
	 */
	List<AdminRoleToUserVo> queryToUser();

	/**
	 * 更新授权
	 * @param id
	 * @param rightList 模块集
	 * @return
	 */
	int adpower(Integer id, Integer[] rightList);

}
