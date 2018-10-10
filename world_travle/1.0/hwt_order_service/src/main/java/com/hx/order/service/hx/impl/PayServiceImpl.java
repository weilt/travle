package com.hx.order.service.hx.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.HwOrderUnpaid;
import com.hwt.domain.entity.order.HxOrderInfo;
import com.hwt.domain.entity.order.vo.HxOrderInfoVo;
import com.hwt.domain.entity.user.wallet.HxUserWallet;
import com.hwt.domain.entity.user.wallet.HxUserWalletBill;
import com.hwt.domain.mapper.bureau.HwTravelBureauMapper;
import com.hwt.domain.mapper.order.HwOrderMapper;
import com.hwt.domain.mapper.order.HwOrderUnpaidMapper;
import com.hwt.domain.mapper.order.HxOrderInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletBillMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletMapper;
import com.hx.core.exception.BaseException;
import com.hx.core.pay.alipay.AliPayConstants;
import com.hx.core.pay.alipay.AlipaySDK;
import com.hx.core.pay.alipay.PayType;
import com.hx.core.redis.RedisCache;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.utils.SequenceFactoryBean2;
import com.hx.core.utils.ZZBigDecimalUtils;
import com.hx.core.wyim.entity.emu.SMSNotice;
import com.hx.core.wyim.entity.emu.SMSType;
import com.hx.core.wyim.notice.SentNotice;
import com.hx.order.service.hx.PayService;

@Service
public class PayServiceImpl implements PayService{
	
	@Autowired
	private HwOrderMapper hwOrderMapper;
	
	@Autowired
	private HxOrderInfoMapper hxOrderInfoMapper;
	
	@Autowired
	private HxUserWalletBillMapper hxUserWalletBillMapper;
	
	@Autowired
	private HxUserWalletMapper hxUserWalletMapper;
	
	@Autowired
	private HwOrderUnpaidMapper hwOrderUnpaidMapper;
	
	@Autowired
	private SentNotice sentNotice;
	
	@Autowired
	private HxUserMapper hxUserMapper;
	
	@Autowired 
	private HwTravelBureauMapper hwTravelBureauMapper;

	@Override
	@Transactional
	public void wallet_pay(Long user_id, String order_num, String payment_password, String smsVerify) throws Exception {
		
		
		HwOrderUnpaid hwOrderUnpaid = hwOrderUnpaidMapper.selectByOrder_num(order_num);
		if (hwOrderUnpaid==null){
			throw new BaseException("订单不存在!");
		}
		int compareTo = hwOrderUnpaid.getPayment().compareTo(new BigDecimal("5000"));
		if (compareTo!=-1){
			//说明不小于5000
			String code = RedisCache.get(SMSType.walletPay.smsTypePrefix + hwOrderUnpaid.getBuyer_phone());
			
			if(StringUtils.isBlank(code) || !code.equals(smsVerify)) {
				throw new BaseException("验证码输入错误!");
			}
		}
		HwOrder hwOrder = getOrder(hwOrderUnpaid);
		
		HxOrderInfoVo hxOrderInfo = hxOrderInfoMapper.query_by_order_id(hwOrder.getOrder_id());
		
		HxUserWalletBill walletPay = walletPay(hwOrder, payment_password, hxOrderInfo);
		
		
		hwOrder.setUpdate_time(System.currentTimeMillis());
		
		hwOrderMapper.insertSelective(hwOrder);
		hxUserWalletBillMapper.insertSelective(walletPay);
		hwOrderUnpaidMapper.deleteByPrimaryKey(hwOrderUnpaid.getOrder_id());
		
		if(hwOrder.getCicerone_id()!=0){
			System.err.println("++++++++++++++确认");
			sentNotice.sendSystem(getUserImId(hwOrder.getCicerone_id()), "新的订单", "有人向你下单", null, 8, hwOrder.getOrder_id()+"", null);
		}else {
			sentNotice.sendSystemSms(getBureauPhone(hwOrder.getBureau_id()), SMSNotice.bureauorder.smsTypeTemplateNumber, null);
		}
		
		sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "零钱支付", "零钱支付订单", null, 2, null, JsonUtils.Bean2Json(walletPay));
	}
	
	/**
	 * 创建订单
	 * @param hwOrderUnpaid
	 * @return
	 */
	private HwOrder getOrder(HwOrderUnpaid hwOrderUnpaid) {
		HwOrder hwOrder = new HwOrder();
		hwOrder.setOrder_id(hwOrderUnpaid.getOrder_id());
		hwOrder.setOrder_num(hwOrderUnpaid.getOrder_num());
		hwOrder.setBureau_id(hwOrderUnpaid.getBureau_id());
		hwOrder.setCicerone_id(hwOrderUnpaid.getCicerone_id());
		hwOrder.setLine_id(hwOrderUnpaid.getLine_id());
		hwOrder.setUser_id(hwOrderUnpaid.getUser_id());
		hwOrder.setInsurance_id(hwOrderUnpaid.getInsurance_id());
		hwOrder.setCoupon_id(hwOrderUnpaid.getCoupon_id());
		hwOrder.setPayment(hwOrderUnpaid.getPayment());
		hwOrder.setCreate_time(hwOrderUnpaid.getCreate_time());
		hwOrder.setStart_time(hwOrderUnpaid.getStart_time());
		hwOrder.setEnd_time(hwOrderUnpaid.getEnd_time());
		hwOrder.setBuyer_message(hwOrderUnpaid.getBuyer_message());
		hwOrder.setBuyer_name(hwOrderUnpaid.getBuyer_name());
		hwOrder.setBuyer_phone(hwOrderUnpaid.getBuyer_phone());
		hwOrder.setAdult_num(hwOrderUnpaid.getAdult_num());
		hwOrder.setChildren_num(hwOrderUnpaid.getChildren_num());
		hwOrder.setPeople_num(hwOrderUnpaid.getPeople_num());
		return hwOrder;
	}
	/**
	 * 零钱支付
	 * @param hwOrder
	 * @param payment_password
	 * @param hxOrderInfo 
	 * @return
	 * @throws Exception 
	 */
	private HxUserWalletBill walletPay(HwOrder hwOrder, String payment_password, HxOrderInfoVo hxOrderInfo) throws Exception {
		Long user_id = hwOrder.getUser_id();
		BigDecimal payment = hwOrder.getPayment();
		
		HxUserWallet hxUserWallet = hxUserWalletMapper.selectByUserId(user_id);
		
		if (ObjectHelper.isEmpty(hxUserWallet)){
			throw new RuntimeException("余额不足，请充值，或者采用其他方式支付！");
		}
		
		if (ObjectHelper.isEmpty(hxUserWallet.getPayment_password())){
			throw new RuntimeException("请先设置支付密码！");
		}
		if(!hxUserWallet.getPayment_password().equals(ObjectHelper.passWord(payment_password, hxUserWallet.getPayment_salt()))){
			
			throw new RuntimeException("支付密码错误！");
		}
		
		if (hxUserWallet.getIs_can().compareTo(payment)==-1){
			throw new RuntimeException("余额不足，请充值，或者采用其他方式支付！");
		}
		
		//扣钱
		int num = hxUserWalletMapper.safeBalanceByUserId(user_id, payment);
		if (num!=1){
			throw new RuntimeException("余额不足，请充值，或者采用其他方式支付！");
		}
		hwOrder.setStatus(1);
		hwOrder.setPayment_time(System.currentTimeMillis());
		hwOrder.setPaymen_type(3);
		//hxUserWalletMapper.safeBalanceByUserId(user_id, payment);
		HxUserWalletBill createBill = createBill(user_id, hxOrderInfo.getDec(), hxOrderInfo.getDec(), 2, hwOrder.getOrder_num(), new SequenceFactoryBean2().getObject(),
				"订单支付", hwOrder.getPayment(), ZZBigDecimalUtils.safeSubtract(true, hxUserWallet.getBalance(), hwOrder.getPayment()), null, 0, 2);
		
		
		return createBill;
	}
	
	/**
	 * 创建账单
	 * @param user_id    用户id
	 * @param bill_dec		描述
	 * @param bill_title	说明
	 * @param bill_type		1-充值  2-支付 3-收入 4-退款 5-提现  6-违约金收入
	 * @param order_num		订单编号
	 * @param trans_num		交易单号
	 * @param remarks		备注
	 * @param operation_amount  操作金额 
	 * @param balance		总余额
	 * @param flow_num		第三方支付流水号
	 * @param is_get		是否进账  0-否 1- 是
	 * @param is_success	是否成功  0-否 1-是
	 * @return
	 */
	private HxUserWalletBill createBill(Long user_id, String bill_dec,String bill_title, Integer bill_type,String order_num, 
			String trans_num,String remarks,BigDecimal operation_amount,BigDecimal balance, String flow_num, Integer is_get,Integer is_success){
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
	
	/**
	 * 通过用户id查询im_id
	 * @param user_id
	 * @return
	 */
	private String getUserImId(Long user_id) {
		String im_id = hxUserMapper.queryImIdByUserId(user_id);
		return im_id;
	}
	
	/**
	 * 通过旅行社id查询电话
	 */
	private String getBureauPhone(Long bureau_id){
		String phone = hwTravelBureauMapper.getBureauPhone(bureau_id);
		return phone ;
	}

	@Override
	public Object zhifubao_pay(String token,Long user_id, String order_num) {
		HwOrderUnpaid hwOrderUnpaid = hwOrderUnpaidMapper.selectByOrder_num(order_num);
		
		Map<String, Object> map = null;
		PayType payType = new PayType(user_id, 2);
		String attach = JsonUtils.Bean2Json(payType);  //自定义数据包
		//支付宝支付
		
		String body = new AlipaySDK().toPay(hwOrderUnpaid.getPayment().toString(), "订单支付", attach, order_num);
		map = new HashMap<>();
		map.put("body", body);
		map.put("appid", AliPayConstants.app_id); //APPID
		if(map == null || map.size() == 0) {
			throw new BaseException("获取支付订单失败");
		}
		System.out.println(map);
		return map;
	}

	
	@Override
	@Transactional
	public void pay_callBack(String out_trade_no, String passback_params, int type) throws Exception {
		PayType payType = JsonUtils.json2Bean(passback_params, PayType.class);
		
		if(ObjectHelper.isEmpty(payType)) {
			return;
		}
		// 1- 充值支付  ，2- 支付
		switch (payType.getPayType()) {
		case 1:
			payRecharge(out_trade_no, payType,type);
			break;
		case 2:
			payOrder(out_trade_no, payType, type);
			break;
		
		default:
			break;
		}
	}
	
	/**
	 * 支付回调信息 通知处理
	 * @param out_trade_no - 订单ID
	 * @param attach - 自定义参数信息
	 * @param type - 1-微信支付，2-支付宝充值
	 */
	private void payOrder(String out_trade_no, PayType payType, int type) throws Exception {
		HwOrderUnpaid hwOrderUnpaid = hwOrderUnpaidMapper.selectByOrder_num(out_trade_no);
		if (hwOrderUnpaid==null){
			throw new BaseException("订单不存在!");
		}
		
		HwOrder hwOrder = getOrder(hwOrderUnpaid);
		
		HxOrderInfoVo hxOrderInfo = hxOrderInfoMapper.query_by_order_id(hwOrder.getOrder_id());
		
		HxUserWalletBill threePay = threePay(hwOrder, hxOrderInfo,type,payType);
		
		//根据是否充值判断是不是钱包操作
		if (payType.getPayType()==1){
			threePay.setIs_wallet(1);
		} else {
			threePay.setIs_wallet(0);
		}
		
		
		hwOrder.setUpdate_time(System.currentTimeMillis());
		
		hwOrderMapper.insertSelective(hwOrder);
		hxUserWalletBillMapper.insertSelective(threePay);
		hwOrderUnpaidMapper.deleteByPrimaryKey(hwOrderUnpaid.getOrder_id());
		
		//给商家发通知
		if(hwOrder.getCicerone_id()!=0){
		
			sentNotice.sendSystem(getUserImId(hwOrder.getCicerone_id()), "新的订单", "有人向你下单", null, 8, hwOrder.getOrder_id()+"", null);
		}else {
			sentNotice.sendSystemSms(getBureauPhone(hwOrder.getBureau_id()), SMSNotice.bureauorder.smsTypeTemplateNumber, null);
		}
		
		//给用户发通知
		if (type==1){
			
			sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "微信支付", "微信支付订单", null, 2, null, JsonUtils.Bean2Json(threePay));
		}else {
			
			sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "支付宝支付", "支付宝支付订单", null, 2, null, JsonUtils.Bean2Json(threePay));
		}
	}

	/**
	 * 第三方支付
	 * @param hwOrder
	 * @param hxOrderInfo
	 * @param type   - 1-微信支付，2-支付宝充值
	 * @param payType
	 * @return
	 * @throws Exception
	 */
	private HxUserWalletBill threePay(HwOrder hwOrder, HxOrderInfoVo hxOrderInfo, int type, PayType payType) throws Exception {
		Long user_id = hwOrder.getUser_id();
		HxUserWallet hxUserWallet = hxUserWalletMapper.selectByUserId(user_id);
		
		hwOrder.setStatus(1);
		hwOrder.setPayment_time(System.currentTimeMillis());
		hwOrder.setPaymen_type(3);
		//hxUserWalletMapper.safeBalanceByUserId(user_id, payment);
		HxUserWalletBill createBill = createBill(user_id, hxOrderInfo.getDec(), hxOrderInfo.getDec(), 2, hwOrder.getOrder_num(), new SequenceFactoryBean2().getObject(),
				"订单支付", hwOrder.getPayment(),  hxUserWallet.getBalance(), "111111111", 0, 2);
		
		return createBill;
	}

	/**
	 * 充值
	 * @param out_trade_no  订单编号
	 * @param payType
	 * @param type
	 * @throws Exception 
	 */
	private void payRecharge(String out_trade_no, PayType payType, int type) throws Exception {
		HxUserWalletBill selectByTrans_num = hxUserWalletBillMapper.selectByTrans_num(out_trade_no);
		if (selectByTrans_num.getIs_success()==1){
			System.out.println("已经充值成功");
			return ;
		}
		
		Long user_id = selectByTrans_num.getName_id();
		
		//查询钱包
		HxUserWallet wallet = hxUserWalletMapper.selectByUserId(user_id);
		hxUserWalletMapper.addBalanceByUserId(user_id, selectByTrans_num.getOperation_amount());
		
		//账单
		selectByTrans_num.setBalance(ZZBigDecimalUtils.safeAdd(selectByTrans_num.getOperation_amount(), wallet.getBalance()));
		selectByTrans_num.setIs_success(1);
		selectByTrans_num.setEnd_time(System.currentTimeMillis());
		hxUserWalletBillMapper.updateByPrimaryKeySelective(selectByTrans_num);
		
		//通知
		sentNotice.sendSystem(getUserImId(selectByTrans_num.getName_id()), "充值", "充值", null, 2, null, JsonUtils.Bean2Json(selectByTrans_num));
	}
}
