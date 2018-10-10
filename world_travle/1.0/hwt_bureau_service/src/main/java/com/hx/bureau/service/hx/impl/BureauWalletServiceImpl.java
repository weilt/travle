package com.hx.bureau.service.hx.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.bureau.vo.HxBureauBankdAccount;
import com.hwt.domain.entity.user.wallet.HxUserWallet;
import com.hwt.domain.entity.user.wallet.HxUserWalletBankCard;
import com.hwt.domain.entity.user.wallet.HxUserWalletBill;
import com.hwt.domain.mapper.bureau.HwTravelBureauMapper;
import com.hwt.domain.mapper.bureau.HxBureauModuleMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletBillMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletMapper;
import com.hx.bureau.service.hx.BureauWalletService;
import com.hx.core.exception.BaseException;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.utils.SequenceFactoryBean2;
import com.hx.core.utils.UUIDHelper;
import com.hx.core.utils.ZZBigDecimalUtils;

@Service
public class BureauWalletServiceImpl implements BureauWalletService{
	
	@Autowired
	private HwTravelBureauMapper hwTravelBureauMapper;
	
	@Autowired
	private HxUserWalletMapper hxUserWalletMapper;
	
	@Autowired
	private HxUserWalletBillMapper hxUserWalletBillMapper;

	@Override
	@Transactional
	public Map<String, Object> query_my(Long bureau_id) {
		
		HxBureauBankdAccount hxBureauBankdAccount = hwTravelBureauMapper.query_bankdAccount(bureau_id);
		HxUserWallet hxUserWallet = hxUserWalletMapper.queryBureau(bureau_id);
		if (hxUserWallet==null){
			hxUserWallet = new HxUserWallet();
			BigDecimal bigDecimal = new BigDecimal("0.00");
			hxUserWallet.setBalance(bigDecimal);
			hxUserWallet.setCreate_time(System.currentTimeMillis());
			hxUserWallet.setDay_pass_num(0);
			hxUserWallet.setIs_can(bigDecimal);
			hxUserWallet.setIs_not_can(bigDecimal);
			hxUserWallet.setName_id(bureau_id);
			hxUserWallet.setName_type(2);
			hxUserWallet.setPayment_salt(UUIDHelper.createUUId());
			hxUserWalletMapper.insertSelective(hxUserWallet);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("hxBureauBankdAccount", hxBureauBankdAccount);
		map.put("hxUserWallet", hxUserWallet);
		
		
		return map;
	}

	@Override
	public void put_forward(Long forward, Long bureau_id) throws Exception {
		// 判断余额是否充足
		HxUserWallet wallet = hxUserWalletMapper.queryBureau(bureau_id);
		BigDecimal bigDecimal = new BigDecimal(forward);
		
		if (wallet.getIs_can().compareTo(bigDecimal) >= 0) {
			// 创建提现申请

			HxBureauBankdAccount hxBureauBankdAccount = hwTravelBureauMapper.query_bankdAccount(bureau_id);
			
				
			// 扣去金额
			int num = hxUserWalletMapper.safeBalanceByBureauId(bureau_id, bigDecimal);
			if (num == 0) {
				throw new RuntimeException("余额不足！");
			}

			HxUserWalletBill createBill = createBill(bureau_id, hxBureauBankdAccount.getBank_name(), "旅行社提现", 5, null,
					new SequenceFactoryBean2().getObject(), hxBureauBankdAccount.getBureau_bank_account(), 
					ZZBigDecimalUtils.safeSubtract(true, wallet.getBalance(), bigDecimal),bigDecimal, null, 0, 0);
			hxUserWalletBillMapper.insertSelective(createBill);

		} else {
			throw new RuntimeException("余额不足！");
		}

	}

	/**
	 * 创建账单
	 * 
	 * @param bureau_id
	 *            旅行社id
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
	private HxUserWalletBill createBill(Long bureau_id, String bill_dec, String bill_title, Integer bill_type,
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
		hxUserWalletBill.setName_id(bureau_id);
		hxUserWalletBill.setName_type(2);
		hxUserWalletBill.setOrder_num(order_num);
		hxUserWalletBill.setRemarks(remarks);
		hxUserWalletBill.setSource(1);
		hxUserWalletBill.setStare_time(currentTimeMillis);
		hxUserWalletBill.setTrans_num(trans_num);
		hxUserWalletBill.setOperation_amount(operation_amount);
		return hxUserWalletBill;
	}

	@Override
	public PageResult<Map<String, Object>> bill_query(Map<String, Object> map) {
		PageResult<Map<String, Object>> pageResult = new PageResult<>();
		List<Map<String, Object>> list = hxUserWalletBillMapper.query_bill_bureau(map);
		pageResult.setDataList(list);
		long count = hxUserWalletBillMapper.query_bill_bureau_count(map);
		pageResult.setCount(count );
		return pageResult;
	}

	@Override
	public HxUserWalletBill bill_info(Long bill_id, Long bureau_id) {
		HxUserWalletBill hxUserWalletBill = hxUserWalletBillMapper.selectByPrimaryKey(bill_id);
		if (hxUserWalletBill.getName_type()!=2&&hxUserWalletBill.getName_id()!=bureau_id ){
			throw new RuntimeException("该账单不存在！");
		}
		return hxUserWalletBill;
	}
}
