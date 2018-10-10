package com.hx.system.service.adminHx.service;

import java.util.Map;

import com.hwt.domain.entity.user.Vo.adminHxVo.HxUserReportVo;
import com.hx.core.page.asyn.PageResult;

/**
 * 报表
 * @author Administrator
 *
 */
public interface HxReportService {

	/**
	 * 查询用户报表
	 * @param map
	 * @return
	 */
	PageResult<HxUserReportVo> queryByMap(Map<String, Object> map);

}
