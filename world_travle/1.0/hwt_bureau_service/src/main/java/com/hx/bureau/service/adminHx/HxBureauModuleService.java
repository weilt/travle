package com.hx.bureau.service.adminHx;

import java.util.Map;

import com.hwt.domain.entity.bureau.HxBureauModule;
import com.hx.core.page.asyn.PageResult;

public interface HxBureauModuleService {

	/**
	 * 根据添加查询
	 * @param map
	 * @return
	 */
	PageResult<Map<String, Object>> queryByMap(Map<String, Object> map);

	/**
	 * 添加
	 * @param bureauModule
	 */
	void insert(HxBureauModule bureauModule);

	/**
	 * 通过id查询
	 * @param bureau_module_id
	 * @return
	 */
	HxBureauModule queryById(Long bureau_module_id);

	/**
	 * 更新
	 * @param bureauModule
	 */
	void update(HxBureauModule bureauModule);

	/**
	 * 通过id删除
	 * @param id
	 * @param type
	 * @return
	 */
	int deleteById(Integer id, Integer type);

}
