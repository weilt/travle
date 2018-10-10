package com.hx.bureau.service.hx;

import java.util.Map;

import com.hwt.domain.entity.user.wallet.HxUserWalletBill;
import com.hx.core.page.asyn.PageResult;

public interface BureauWalletService {


	/**
	 * 查询旅行社钱包信息
	 * @param bureau_id 
	 * @return
	 */
	Map<String, Object> query_my(Long bureau_id);

	/**
	 * 提现申请
	 * @param forward   提现金额
	 * @param bureau_id
	 */
	void put_forward(Long forward, Long bureau_id)  throws Exception ;

	/**
	 *  资金明细查询
	 * @param map
	 * @return
	 */
	PageResult<Map<String, Object>> bill_query(Map<String, Object> map);

	/**
	 * 账单详情
	 * @param bill_id
	 * @param bureau_id 
	 * @return
	 */
	HxUserWalletBill bill_info(Long bill_id, Long bureau_id);

}
