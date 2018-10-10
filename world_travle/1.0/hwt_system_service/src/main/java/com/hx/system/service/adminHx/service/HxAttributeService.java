package com.hx.system.service.adminHx.service;

import java.util.Map;

import com.hwt.domain.entity.system.HwAttribute;
import com.hx.core.page.asyn.PageResult;

public interface HxAttributeService {

	PageResult<Map<String, Object>> queryByMap(Map<String, Object> map);

	/**
	 * 添加
	 * @param attribute
	 */
	void insert(HwAttribute attribute);

	/**
	 * 根据id获取
	 * @param attribute_id
	 * @return
	 */
	HwAttribute queryById(Integer attribute_id);

	/**
	 * 修改
	 * @param attribute
	 */
	void update(HwAttribute attribute);

	/**
	 * 通过id删除
	 * @param id
	 * @param type 2-删除 1-恢复
	 * @return
	 */
	void deleteById(Integer attribute_id, Integer type);

}
