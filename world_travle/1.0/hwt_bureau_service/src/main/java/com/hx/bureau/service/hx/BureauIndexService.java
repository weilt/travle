package com.hx.bureau.service.hx;

import java.util.List;
import java.util.Map;

public interface BureauIndexService {

	/**
	 * 首页数据返回
	 * @param bureau_id
	 * @return
	 */
	Map<String, Object> query_index(Long bureau_id);

	/**
	 * 统计订单
	 * @param bureau_id 
	 * @param choose_type
	 * @return
	 */
	Map<String, Object> orderStatistics(Long bureau_id, Integer choose_type) throws Exception ;

	/**
	 * 最近一周数据统计
	 * @param bureau_id
	 * @return
	 */
	Map<String, Object> queryLatelyOrderStatistics(Long bureau_id);

}
