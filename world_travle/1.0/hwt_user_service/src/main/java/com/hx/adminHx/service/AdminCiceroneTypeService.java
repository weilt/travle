package com.hx.adminHx.service;

import java.util.Map;

import com.hwt.domain.entity.cicerone.HxCiceroneType;
import com.hx.core.page.asyn.PageResult;

public interface AdminCiceroneTypeService {

	/**
	 *分页查询
	 * @param map
	 * @return
	 */
	PageResult<Map<String, Object>> queryByMap(Map<String, Object> map);

	/**
	 * 添加
	 * @param ciceroneType
	 */
	void insert(HxCiceroneType ciceroneType);

	/**
	 * 根据id获取
	 * @param id
	 * @return
	 */
	HxCiceroneType queryById(Long id);

	/**
	 * 修改
	 * @param ciceroneType
	 */
	void update(HxCiceroneType ciceroneType);

}
