package com.hx.bureau.service.hx;

import java.util.Map;

import com.hx.core.page.asyn.PageResult;

public interface BureauLogService {

	/**
	 * 查询日志
	 * @param map
	 * @return
	 */
	PageResult<Map<String, Object>> query(Map<String, Object> map);

}
