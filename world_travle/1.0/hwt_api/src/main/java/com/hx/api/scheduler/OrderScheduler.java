package com.hx.api.scheduler;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.cicerone.vo.HxCiceroneWorkTimeVo;
import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.HwOrderUnpaid;
import com.hwt.domain.entity.order.vo.HxOrderInfoVo;
import com.hwt.domain.entity.user.wallet.HxUserWallet;
import com.hwt.domain.entity.user.wallet.HxUserWalletBill;
import com.hwt.domain.mapper.order.HwOrderMapper;
import com.hwt.domain.mapper.order.HwOrderUnpaidMapper;
import com.hwt.domain.mapper.order.HxOrderInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletBillMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletMapper;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.utils.JsonUtils;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.DateUtils;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.utils.SequenceFactoryBean2;
import com.hx.core.utils.ZZBigDecimalUtils;
import com.hx.core.wyim.notice.SentNotice;
import com.hx.order.service.bureau.BureauOrderService;
import com.hx.order.service.hx.OrderService;

@Component
@EnableScheduling
public class OrderScheduler {
	
	@Autowired
	private HwOrderMapper hwOrderMapper;
	
	@Autowired
	private HxOrderInfoMapper hxOrderInfoMapper;
	
	@Autowired
	private HxUserWalletMapper hxUserWalletMapper;
	
	@Autowired
	private HxUserWalletBillMapper hxUserWalletBillMapper;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private BureauOrderService bureauOrderService;
	
	@Autowired
	private HwOrderUnpaidMapper hwOrderUnpaidMapper;
	
	@Autowired
	private SentNotice sentNotice;
	
	@Autowired
	private HxUserMapper hxUserMapper;
	
	/**
	 * 订单自动完成
	 * @throws Exception 
	 */
	@Transactional
	@Scheduled(cron = "0 0 1 * * ? ")
    public void order_complete() throws Exception {
		System.err.println("----------------");
		long currentTimeMillis = System.currentTimeMillis();
		List<HwOrder> hwOrders = hwOrderMapper.query_auto_complete(currentTimeMillis);
		if (!ObjectHelper.isEmpty(hwOrders)){
			for (HwOrder hwOrder : hwOrders) {
				
				//完成将钱加入冻结金额中
				if (hwOrder.getCicerone_id()!=0){
					hxUserWalletMapper.addBalanceByNameId(hwOrder.getCicerone_id(),1, hwOrder.getPayment());
					
				}else {
					hxUserWalletMapper.addBalanceByNameId(hwOrder.getBureau_id(),2, hwOrder.getPayment());
				}
				HxUserWalletBill insertBill = insertBill(hwOrder,hwOrder.getPayment(), 2, 3, "订单完成", 1, 1);
				hxUserWalletBillMapper.insertSelective(insertBill);
				
				if (hwOrder.getCicerone_id()!=0){
					sentNotice.sendSystem(getUserImId(hwOrder.getCicerone_id()), "订单完成", "订单完成，钱存入冻结金额里面", null, 2, null, JsonUtils.Bean2Json(insertBill));
				}
				
				sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "订单完成", "订单完成", null, 7, hwOrder.getOrder_num(), null);
			}
			hwOrderMapper.auto_complete(currentTimeMillis);
		}
    }
	
	

	/**
	 * 订单完成7天将金额结算到可用余额
	 */
	@Transactional
	@Scheduled(cron = "0 0 2 * * ? ")
    public void order_settlement() throws Exception {
		System.err.println("----------------");
		Long time = 7*24*60*60*1000l;
		long currentTimeMillis = System.currentTimeMillis();
		List<HwOrder> hwOrders = hwOrderMapper.query_settlement(currentTimeMillis-time);
		if (!ObjectHelper.isEmpty(hwOrders)){
			for (HwOrder hwOrder : hwOrders) {
				int is_not_can_to_can;
				//将钱加入可用金额中
				if (hwOrder.getCicerone_id()!=0){
					is_not_can_to_can = hxUserWalletMapper.is_not_can_to_can(hwOrder.getCicerone_id(),1, hwOrder.getPayment());
					if (is_not_can_to_can!=1){
						throw new RuntimeException("金额有异常，联系开发！");
					}
					
					sentNotice.sendSystem(getUserImId(hwOrder.getCicerone_id()), "订单结算", "你有一个订单，已结算到可用余额", null, 1, null, null);
				}else {
					is_not_can_to_can =hxUserWalletMapper.is_not_can_to_can(hwOrder.getBureau_id(),2, hwOrder.getPayment());
					if (is_not_can_to_can!=1){
						throw new RuntimeException("金额有异常，联系开发！");
					}
				}
				
			}
			hwOrderMapper.auto_settlement(currentTimeMillis,currentTimeMillis-time);
		}
    }
	
	/**
	 * 24小时未接受的订单自动拒绝
	 * @throws Exception 
	 */
	@Transactional
	@Scheduled(cron = " 0 0/20 * * * ?")
	public void order_refuse() throws Exception {
		System.err.println("----------------");
		Long time = 20*60*1000l;
		long currentTimeMillis = System.currentTimeMillis();
		List<HwOrder> hwOrders = hwOrderMapper.query_auto_refuse(currentTimeMillis - time);
		if (!ObjectHelper.isEmpty(hwOrders)){
			for (HwOrder hwOrder : hwOrders) {
				if (hwOrder.getCicerone_id()!=0){
					orderService.cicerone_refuse(hwOrder.getCicerone_id(), hwOrder.getOrder_id(),0);
				}else {
					bureauOrderService.refuse(hwOrder.getOrder_id(), hwOrder.getBureau_id(), null);
				}
			}
			
			hwOrderMapper.auto_refuse(currentTimeMillis,currentTimeMillis - time);
		}
    }
	
	
	
	/**
	 * 账单
	 * @param hwOrder		订单
	 * @param operation_amount		操作金额
	 * @param is_wallet		是否是钱包操作  1-否 2-是  默认1
	 * @param bill_type		1-充值  2-支付 3-收入 4-退款 5-提现  6-违约金收入
	 * @param remarks		备注
	 * @param is_get		是否进账  0-否 1- 是
	 * @param is_success	是否成功  0-否 1-是
	 * @throws Exception 
	 */
	private HxUserWalletBill insertBill(HwOrder hwOrder,BigDecimal operation_amount,Integer is_wallet,Integer bill_type ,String remarks,Integer is_get,Integer is_success ) throws Exception{
		HxOrderInfoVo hxOrderInfo = hxOrderInfoMapper.query_by_order_id(hwOrder.getOrder_id());
		HxUserWallet hxUserWallet = null;
		if (hwOrder.getCicerone_id()!=0){
			hxUserWallet = hxUserWalletMapper.selectByUserId(hwOrder.getCicerone_id());
		}else {
			hxUserWallet = hxUserWalletMapper.queryBureau(hwOrder.getBureau_id());
		}
		
		if (is_get==1&&is_wallet==2){
			hxUserWallet.setBalance(ZZBigDecimalUtils.safeAdd(hxUserWallet.getBalance(), operation_amount));
		}
		if (is_get==0&&is_wallet==2){
			if (hxUserWallet.getIs_can().compareTo(operation_amount)==-1){
				throw new RuntimeException("余额不足");
			}
			hxUserWallet.setBalance(ZZBigDecimalUtils.safeSubtract(true,hxUserWallet.getBalance(), operation_amount));
		}
		HxUserWalletBill createBill = createBill(hwOrder, hxOrderInfo.getDec(), hxOrderInfo.getDec(), bill_type, hwOrder.getOrder_num(), new SequenceFactoryBean2().getObject(),
				remarks, operation_amount, hxUserWallet.getBalance(), null, is_get, is_success);
		return createBill;
		
	}
	
	/**
	 * 创建账单
	 * @param hwOrder    用户id
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
	private HxUserWalletBill createBill(HwOrder hwOrder, String bill_dec,String bill_title, Integer bill_type,String order_num, 
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
		if (hwOrder.getCicerone_id()!=0){
			hxUserWalletBill.setName_id(hwOrder.getCicerone_id());
			hxUserWalletBill.setName_type(1);
		}else {
			hxUserWalletBill.setName_id(hwOrder.getBureau_id());
			hxUserWalletBill.setName_type(2);
		}
		
		hxUserWalletBill.setOrder_num(order_num);
		hxUserWalletBill.setRemarks(remarks);
		hxUserWalletBill.setSource(1);
		hxUserWalletBill.setStare_time(currentTimeMillis);
		hxUserWalletBill.setTrans_num(trans_num);
		hxUserWalletBill.setOperation_amount(operation_amount);
		return hxUserWalletBill;
	}
	
	/**
	 * 导游未付款订单，时间内把接单状态修改回来
	 */
	@Transactional
	@Scheduled(cron = " 0 0/5 * * * ?")
	public void order_unpaid() throws Exception {
		long currentTimeMillis = System.currentTimeMillis();
		long time = currentTimeMillis - 5*60*1000;
		List<HwOrderUnpaid> query_unpaid = hwOrderUnpaidMapper.query_unpaid(time);
		if (!ObjectHelper.isEmpty(query_unpaid)){
			for (HwOrderUnpaid hwOrderUnpaid : query_unpaid) {
				if (hwOrderUnpaid.getCicerone_id()!=0){
					sales_cicerone_update_workTime(hwOrderUnpaid);
				}
			}
			hwOrderUnpaidMapper.unpaid(time);
		}
		
	}
	
	/**
	 * 将导游被预约改为可预约
	 * @param hwOrder
	 */
	private void sales_cicerone_update_workTime(HwOrderUnpaid hwOrder) {
		
		Long start_time = hwOrder.getStart_time();
		Long end_time = hwOrder.getEnd_time();
		int day = currentTimeMillis_day(start_time, end_time);
		
		Long cicerone_id = hwOrder.getCicerone_id();
		
		MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "user_id", cicerone_id);
		Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, queryValue , null);
		
		
		Object work_time = findOne.get("work_time");
		
		Map<String, Object> map = JsonUtils.json2Map(work_time.toString());
		for (int i = 0; i < day; i++) {
			String ymd = DateUtils.YMD(start_time+(24*60*60*1000L*i));
			Object object = map.get(ymd);
			
			HxCiceroneWorkTimeVo json2Bean = JsonUtils.json2Bean(object.toString(), HxCiceroneWorkTimeVo.class);
				//更改为未被预约
				json2Bean.setStatus(0);
				map.put(ymd, JsonUtils.Bean2Json(json2Bean));
		}
		
		//修改
		Map<String, Object> updateMap = new HashMap<>();
    	updateMap.put("work_time", JsonUtils.Bean2Json(map));
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("user_id", cicerone_id);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, filterMap , updateMap );
		
	}
	
	/**
	 * 计算两个毫秒时间戳的天数
	 * @param start_time
	 * @param end_time
	 * @return
	 */
	private int currentTimeMillis_day(Long start_time, Long end_time) {
		long Day = 24*60*60*1000l;
		
		long DAY = end_time - start_time;
		if (DAY<=0){
			throw new RuntimeException("结束时间有误！");
		}
		
		int day = (int) (DAY/Day);
		
		return DAY%Day>0?(day+1):day;
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
}
