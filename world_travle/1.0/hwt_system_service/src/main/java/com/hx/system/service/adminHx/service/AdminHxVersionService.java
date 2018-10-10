package com.hx.system.service.adminHx.service;

import java.util.Map;

import com.hwt.domain.entity.version.HxVersion;
import com.hx.core.page.asyn.PageResult;

public interface AdminHxVersionService {

	/**
	 * 根据条件获取数据
	 * @param map
	 * @return
	 */
	PageResult<HxVersion> queryByMap(Map<String, Object> map);

	/**
	 * 添加
	 * @param hxVersion
	 */
	void insert(HxVersion hxVersion);

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	HxVersion queryById(Long id);

	/**
	 * 修改
	 * @param hxVersion
	 */
	void update(HxVersion hxVersion);

}
