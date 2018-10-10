package com.hx.adminHx.service;

import java.util.Map;

import com.hwt.domain.entity.cicerone.HxCiceroneInfo;
import com.hwt.domain.entity.user.Vo.adminHxVo.HxUserReportVo;
import com.hx.adminHx.service.vo.PageResultHxCiceroneInfo;
import com.hx.core.page.asyn.PageResult;

public interface AdminHxCiceroneService {

	/**
	 * 通过条件查询导游
	 * @param map
	 * @return
	 */
	PageResultHxCiceroneInfo queryByMap(Map<String, Object> map);

	/**
	 * 审核操作
	 * @param status
	 * @param reason
	 * @param user_id 
	 */
	void cicerone_verification(Integer status, String reason, Long user_id) throws Exception;

	/**
	 * 通过id查询详情
	 * @param id
	 * @return
	 */
	Map<String, Object> query_infoById(Long id);

}
