package com.hx.admin.service.admin;

import java.util.Map;

import com.hwt.domain.entity.admin.AdminSystemconfig;
import com.hwt.domain.entity.admin.vo.AdminSystemconfigVo;
import com.hx.core.page.asyn.PageResult;

/**
 * 系统配置
 * @author Administrator
 *
 */
public interface AdminSystemconfigService {
	/**
	 * 通过条件查询
	 * @return
	 */
	PageResult<AdminSystemconfigVo> queryByMap(Map<String, Object> map);

	/**
	 * 系统配置增加
	 * @param adminSystemconfig
	 * @return
	 */
	int insert(AdminSystemconfig adminSystemconfig);

	/**
	 * 根据主键获得
	 * @param id
	 * @return
	 */
	AdminSystemconfig queryById(Integer id);

	/**
	 * 跟新
	 * @param adminSystemconfig
	 * @return
	 */
	int update(AdminSystemconfig adminSystemconfig);

}
