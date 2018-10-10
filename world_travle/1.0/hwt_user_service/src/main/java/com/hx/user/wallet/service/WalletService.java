package com.hx.user.wallet.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


import com.hwt.domain.entity.user.wallet.HxUserWalletBankCard;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletBankCardUserVo;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletBillVo;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletUserVo;

public interface WalletService {

	/**
	 * 查询钱包
	 * @param user_id
	 * @return
	 */
	HxUserWalletUserVo query(Long user_id);

	/**
	 * 第一次设置支付密码
	 * @param user_id
	 * @param password 
	 * @return
	 */
	void paymentPassWord(Long user_id, String password);

	/**
	 * 重设支付密码
	 * @param user_id
	 * @param password
	 */
	void restPaymentPassWord(Long user_id, String password);

	/**
	 * 修改密码
	 * @param user_id
	 * @param password
	 * @param oldPassword
	 */
	void editPaymentPass(Long user_id, String password, String oldPassword);

	/**
	 * 添加银行卡
	 * @param user_id
	 * @param card_num
	 * @param bank_name
	 * @param image
	 * @param bank_address
	 * @param username
	 * @return 
	 */
	HxUserWalletBankCard add_bank(Long user_id, String card_num, String bank_name, String image, String bank_address, String username);

	/**
	 * 删除银行卡
	 * @param user_id
	 * @param bank_card_id
	 */
	void delet_bank(Long user_id, Long bank_card_id);

	/**
	 * 查询银行卡
	 * @param user_id
	 * @return
	 */
	List<HxUserWalletBankCardUserVo> query_bank(Long user_id);

	/**
	 * 查询账单
	 * @param map
	 * @return
	 */
	List<HxUserWalletBillVo> query_bill(Map<String, Object> map);

	/**
	 * 充值
	 * @param user_id
	 * @param recharge_type  1-支付宝 2-微信 3-其他 默认1
	 * @param amount
	 * @return
	 */
	Object recharge(Long user_id, Integer recharge_type, BigDecimal amount) throws Exception ;

	/**
	 * 充值成功
	 * @param user_id
	 * @param recharge_type
	 * @param amount
	 */
	void recharge_success(Long user_id, Integer recharge_type, BigDecimal amount) throws Exception ;

	/**
	 * 提现申请
	 * @param user_id
	 * @param bank_card_id
	 * @param amount
	 * @param payment_password 
	 */
	void put_forward(Long user_id, Long bank_card_id, BigDecimal amount, String payment_password) throws Exception ;

}
