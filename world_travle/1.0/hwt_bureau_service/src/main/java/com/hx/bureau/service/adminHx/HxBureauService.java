package com.hx.bureau.service.adminHx;

import java.util.Map;

import com.hwt.domain.entity.bureau.HwTravelBureau;
import com.hx.bureau.service.Vo.PageResultHxBureauInfo;

public interface HxBureauService {

	PageResultHxBureauInfo queryByMap(Map<String, Object> map);

	
	/**
	 * 申请验证
	 * @param bureau_id
	 * @param status	审核状态 默认1 未审核 2-通过 3-未通过
	 * @param msg	审核通过，审核未通过
	 * @param reason	原因
	 * @return
	 */
	void bureaut_verification(Long bureau_id, Integer status, String reason)  throws Exception;


	/**
	 * 查看详情
	 * @param bureau_id
	 * @return
	 */
	HwTravelBureau query_infoById(Long bureau_id);

}
