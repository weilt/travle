package com.hx.user.wallet.service.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.user.wallet.HxUserWallet;
import com.hwt.domain.entity.user.wallet.HxUserWalletBankCard;
import com.hwt.domain.entity.user.wallet.HxUserWalletBill;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletBankCardUserVo;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletBillVo;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletUserVo;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletBankCardMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletBillMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletMapper;
import com.hx.core.exception.BaseException;
import com.hx.core.pay.alipay.AliPayConstants;
import com.hx.core.pay.alipay.AlipaySDK;
import com.hx.core.pay.alipay.HWAliPayConstant;
import com.hx.core.pay.alipay.PayType;
import com.hx.core.pay.back.PayBack;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.utils.SequenceFactoryBean2;
import com.hx.core.utils.UUIDHelper;
import com.hx.core.utils.ZZBigDecimalUtils;
import com.hx.user.wallet.service.WalletService;

@Service
public class WalletServiceImpl implements WalletService {
	@Autowired
	private HxUserWalletMapper hxUserWalletMapper;

	@Autowired
	private HxUserWalletBankCardMapper hxUserWalletBankCardMapper;

	@Autowired
	private HxUserWalletBillMapper hxUserWalletBillMapper;

	@Autowired
	private HxUserMapper hxUserMapper;

	@Override
	@Transactional
	public HxUserWalletUserVo query(Long user_id) {
		HxUserWalletUserVo hxUserWalletVo = new HxUserWalletUserVo();

		HxUserWallet wallet = hxUserWalletMapper.selectByUserId(user_id);

		// 如果为null则创建
		if (wallet == null) {
			HxUserWallet hxUserWallet = new HxUserWallet();
			BigDecimal bigDecimal = new BigDecimal("0.00");
			hxUserWallet.setBalance(bigDecimal);
			hxUserWallet.setCreate_time(System.currentTimeMillis());
			hxUserWallet.setDay_pass_num(0);
			hxUserWallet.setIs_can(bigDecimal);
			hxUserWallet.setIs_not_can(bigDecimal);
			hxUserWallet.setName_id(user_id);
			hxUserWallet.setName_type(1);
			hxUserWallet.setPayment_salt(UUIDHelper.createUUId());
			hxUserWalletMapper.insertSelective(hxUserWallet);

			hxUserWalletVo.setBalance(bigDecimal);
			hxUserWalletVo.setIs_can(bigDecimal);
			hxUserWalletVo.setIs_not_can(bigDecimal);
			hxUserWalletVo.setUser_id(user_id);
			hxUserWalletVo.setIs_password(0);
		} else {
			hxUserWalletVo.setBalance(wallet.getBalance());
			hxUserWalletVo.setIs_can(wallet.getIs_can());
			hxUserWalletVo.setIs_not_can(wallet.getIs_not_can());
			hxUserWalletVo.setUser_id(user_id);
			if (ObjectHelper.isEmpty(wallet.getPayment_password())) {
				hxUserWalletVo.setIs_password(0);
			} else {
				hxUserWalletVo.setIs_password(1);
			}
		}
		return hxUserWalletVo;
	}

	@Override
	@Transactional
	public void paymentPassWord(Long user_id, String password) {

		HxUserWallet hxUserWallet = hxUserWalletMapper.selectByUserId(user_id);
		if (hxUserWallet != null) {
			String passJiaMi = ObjectHelper.passWord(password, hxUserWallet.getPayment_salt());
			hxUserWallet.setPayment_password(passJiaMi);
			hxUserWalletMapper.updateByPrimaryKeySelective(hxUserWallet);
		}
	}

	@Override
	@Transactional
	public void restPaymentPassWord(Long user_id, String password) {
		HxUserWallet hxUserWallet = hxUserWalletMapper.selectByUserId(user_id);
		if (hxUserWallet != null) {
			if (hxUserWallet.getPayment_password() == null) {
				throw new BaseException("请先设置密码!");
			}
			String passJiaMi = ObjectHelper.passWord(password, hxUserWallet.getPayment_salt());
			if (passJiaMi.equals(hxUserWallet.getPayment_password())) {
				throw new BaseException("新旧密码不能一样!");
			}
			hxUserWallet.setPayment_password(passJiaMi);
			hxUserWalletMapper.updateByPrimaryKeySelective(hxUserWallet);
		}

	}

	@Override
	@Transactional
	public void editPaymentPass(Long user_id, String password, String oldPassword) {
		HxUserWallet hxUserWallet = hxUserWalletMapper.selectByUserId(user_id);
		if (hxUserWallet != null) {
			String passJiaMi = ObjectHelper.passWord(oldPassword, hxUserWallet.getPayment_salt());
			if (!passJiaMi.equals(hxUserWallet.getPayment_password())) {
				throw new BaseException("旧密码错误!");
			}

			String newpassJiaMi = ObjectHelper.passWord(password, hxUserWallet.getPayment_salt());
			hxUserWallet.setPayment_password(newpassJiaMi);
			hxUserWalletMapper.updateByPrimaryKeySelective(hxUserWallet);
		}

	}

	@Override
	@Transactional
	public HxUserWalletBankCard add_bank(Long user_id, String card_num, String bank_name, String image,
			String bank_address, String username) {
		HxUserWalletBankCard hxUserWalletBank = hxUserWalletBankCardMapper.selectByCard_num(card_num);
		if (hxUserWalletBank != null) {
			throw new BaseException("该银行卡已被使用！");
		}
		List<HxUserWalletBankCard> list = hxUserWalletBankCardMapper.selectByUserId(user_id);
		HxUserWalletBankCard hxUserWalletBankCard2 = new HxUserWalletBankCard();
		if (list != null && list.size() > 0) {
			if (list.size() > 2) {
				throw new BaseException("银行卡最多添加3张！");
			} else {
				HxUserWalletBankCard hxUserWalletBankCard = list.get(0);
				String username2 = hxUserWalletBankCard.getUsername();
				if (!username2.equals(username)) {
					throw new BaseException("银行卡的持卡人必须一致！");
				}
				hxUserWalletBankCard2.setBank_address(bank_address);
				hxUserWalletBankCard2.setBank_name(bank_name);
				hxUserWalletBankCard2.setCard_num(card_num);
				hxUserWalletBankCard2.setCreate_time(System.currentTimeMillis());
				hxUserWalletBankCard2.setImage(image);
				hxUserWalletBankCard2.setName_id(user_id);
				hxUserWalletBankCard2.setName_type(1);
				hxUserWalletBankCard2.setUsername(username);
			}
		} else {
			hxUserWalletBankCard2.setBank_address(bank_address);
			hxUserWalletBankCard2.setBank_name(bank_name);
			hxUserWalletBankCard2.setCard_num(card_num);
			hxUserWalletBankCard2.setCreate_time(System.currentTimeMillis());
			hxUserWalletBankCard2.setImage(image);
			hxUserWalletBankCard2.setName_id(user_id);
			hxUserWalletBankCard2.setName_type(1);
			hxUserWalletBankCard2.setUsername(username);
		}
		hxUserWalletBankCardMapper.insertBackId(hxUserWalletBankCard2);
		return hxUserWalletBankCard2;
	}

	@Transactional
	@Override
	public void delet_bank(Long user_id, Long bank_card_id) {

		HxUserWalletBankCard selectByPrimaryKey = hxUserWalletBankCardMapper.selectByPrimaryKey(bank_card_id);
		if (selectByPrimaryKey == null) {
			throw new RuntimeException("该银行卡已被删除！");
		} else {
			if (selectByPrimaryKey.getName_type() != 1 && selectByPrimaryKey.getName_id() != user_id) {
				throw new RuntimeException("非本人的不能操作！");
			} else {
				hxUserWalletBankCardMapper.deleteByPrimaryKey(bank_card_id);
			}
		}
	}

	@Override
	public List<HxUserWalletBankCardUserVo> query_bank(Long user_id) {
		List<HxUserWalletBankCardUserVo> list = hxUserWalletBankCardMapper.query_bankByUserId(user_id);
		return list;
	}

	@Override
	public List<HxUserWalletBillVo> query_bill(Map<String, Object> map) {
		List<HxUserWalletBillVo> list = hxUserWalletBillMapper.query_bill_app(map);
		return list;
	}

	@Override
	@Transactional
	public Object recharge(Long user_id, Integer recharge_type, BigDecimal amount) throws Exception {
		String trans_num = new SequenceFactoryBean2().getObject();
		
		//账单
		HxUserWalletBill createBill = createBill(user_id, "我的钱包", "普通充值", recharge_type, null,
				trans_num, null, amount,
				null, null, 1, 0);
		createBill.setEnd_time(null);
		createBill.setStare_time(System.currentTimeMillis());
		hxUserWalletBillMapper.insertSelective(createBill);
		
		if (recharge_type == 1) {
			Map<String, Object> map = null;
			PayType payType = new PayType(user_id, 1);
			String attach = JsonUtils.Bean2Json(payType);  //自定义数据包
			String body = new AlipaySDK().toPay(amount.toString(), "充值", attach, trans_num);
			map = new HashMap<>();
			map.put("body", body);
			map.put("appid", AliPayConstants.app_id); //APPID
			if(map == null || map.size() == 0) {
				throw new BaseException("获取支付订单失败");
			}
			System.out.println(map);
			return map;
		}
		return null;
	}

	@Transactional
	@Override
	public void recharge_success(Long user_id, Integer recharge_type, BigDecimal amount) throws Exception {
		HxUserWallet wallet = hxUserWalletMapper.selectByUserId(user_id);
		hxUserWalletMapper.addBalanceByUserId(user_id, amount);
		HxUserWalletBill createBill = createBill(user_id, "我的钱包", "普通充值", recharge_type, null,
				new SequenceFactoryBean2().getObject(), null, amount,
				ZZBigDecimalUtils.safeAdd(amount, wallet.getBalance()), null, 1, 1);
		hxUserWalletBillMapper.insertSelective(createBill);

	}

	/**
	 * 创建账单
	 * 
	 * @param user_id
	 *            用户id
	 * @param bill_dec
	 *            描述
	 * @param bill_title
	 *            说明
	 * @param bill_type
	 *            1-充值 2-支付 3-收入 4-退款 5-提现 6-违约金收入
	 * @param order_num
	 *            订单编号
	 * @param trans_num
	 *            交易单号
	 * @param remarks
	 *            备注
	 * @param balance
	 *            总余额
	 * @param operation_amount
	 *            操作金额
	 * @param flow_num
	 *            第三方支付流水号
	 * @param is_get
	 *            是否进账 0-否 1- 是
	 * @param is_success
	 *            是否成功 0-否 1-是
	 * @return
	 */
	private HxUserWalletBill createBill(Long user_id, String bill_dec, String bill_title, Integer bill_type,
			String order_num, String trans_num, String remarks, BigDecimal operation_amount, BigDecimal balance,
			String flow_num, Integer is_get, Integer is_success) {
		HxUserWalletBill hxUserWalletBill = new HxUserWalletBill();
		long currentTimeMillis = System.currentTimeMillis();
		hxUserWalletBill.setBalance(balance);
		hxUserWalletBill.setBill_dec(bill_dec);
		hxUserWalletBill.setBill_title(bill_title);
		hxUserWalletBill.setBill_type(bill_type);
		hxUserWalletBill.setCreate_time(currentTimeMillis);
		hxUserWalletBill.setEnd_time(currentTimeMillis);
		hxUserWalletBill.setFlow_num(flow_num);
		hxUserWalletBill.setIs_get(is_get);
		hxUserWalletBill.setIs_success(is_success);
		hxUserWalletBill.setIs_wallet(1);
		hxUserWalletBill.setName_id(user_id);
		hxUserWalletBill.setName_type(1);
		hxUserWalletBill.setOrder_num(order_num);
		hxUserWalletBill.setRemarks(remarks);
		hxUserWalletBill.setSource(1);
		hxUserWalletBill.setStare_time(currentTimeMillis);
		hxUserWalletBill.setTrans_num(trans_num);
		hxUserWalletBill.setOperation_amount(operation_amount);
		return hxUserWalletBill;
	}

	@Override
	@Transactional
	public void put_forward(Long user_id, Long bank_card_id, BigDecimal amount, String payment_password)
			throws Exception {

		// 判断余额是否充足
		HxUserWallet wallet = hxUserWalletMapper.selectByUserId(user_id);
		if (ObjectHelper.isEmpty(wallet.getPayment_password())) {
			throw new BaseException("请先设置支付密码!");
		}
		if (!ObjectHelper.isEmpty(payment_password)){
			String passJiaMi = ObjectHelper.passWord(payment_password, wallet.getPayment_salt());
			if (!passJiaMi.equals(wallet.getPayment_password())) {

				throw new BaseException("支付密码错误!");
			}
		}
		
		if (wallet.getIs_can().compareTo(amount) >= 0) {
			// 创建提现申请

			HxUserWalletBankCard bankCard = hxUserWalletBankCardMapper.selectByPrimaryKey(bank_card_id);
			if (!ObjectHelper.isEmpty(bankCard)) {
				if (bankCard.getName_type() == 1 && bankCard.getName_id() == user_id) {
					// 扣去金额
					int num = hxUserWalletMapper.safeBalanceByUserId(user_id, amount);
					if (num == 0) {
						throw new RuntimeException("余额不足！");
					}

					HxUserWalletBill createBill = createBill(user_id, bankCard.getBank_name(), "普通提现", 5, null,
							new SequenceFactoryBean2().getObject(), bankCard.getCard_num(), 
							amount,ZZBigDecimalUtils.safeSubtract(true, wallet.getBalance(), amount), null, 0, 0);
					hxUserWalletBillMapper.insertSelective(createBill);
				} else {
					throw new RuntimeException("非本人的不能操作！");
				}
			} else {
				throw new RuntimeException("该银行卡不存在！");
			}

		} else {
			throw new RuntimeException("余额不足！");
		}

	}
}
