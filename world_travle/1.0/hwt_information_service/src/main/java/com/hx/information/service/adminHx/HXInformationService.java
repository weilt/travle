package com.hx.information.service.adminHx;

import java.util.Map;

import com.hwt.domain.entity.mg.information.HwInformation;
import com.hx.core.page.asyn.PageResult;

public interface HXInformationService {

	/**
	 * 分页查询
	 * @param map
	 * @return
	 */
	PageResult<Map<String, Object>> query(Map<String, Object> map);

	/**
	 * 添加
	 * @param hwInformation
	 */
	void insert(HwInformation hwInformation) throws Exception ;

	/**
	 * 查看详情
	 * @param information_id
	 * @return
	 */
	Map<String, Object> queryInfo(Long information_id);

}
