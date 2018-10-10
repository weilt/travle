package com.hx.order.service.hx.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.bureau.HwTravelBureau;
import com.hwt.domain.entity.cicerone.HxCiceroneHide;
import com.hwt.domain.entity.cicerone.HxCiceroneInfo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneWorkTimeVo;
import com.hwt.domain.entity.mg.travel.line.HwTravelLine;
import com.hwt.domain.entity.mg.travel.line.LinePrice;
import com.hwt.domain.entity.order.HwInsurance;
import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.HwOrderRefund;
import com.hwt.domain.entity.order.HwOrderUnpaid;
import com.hwt.domain.entity.order.HwOrderUser;
import com.hwt.domain.entity.order.HwOrderUserInsubrance;
import com.hwt.domain.entity.order.HxOrderInfo;
import com.hwt.domain.entity.order.vo.HwOrderRefundVo;
import com.hwt.domain.entity.order.vo.HwOrderUserInsert;
import com.hwt.domain.entity.order.vo.HwOrderUserVo;
import com.hwt.domain.entity.order.vo.HwOrderVo;
import com.hwt.domain.entity.order.vo.HxOrderInfoBaseVo;
import com.hwt.domain.entity.order.vo.HxOrderInfoVo;
import com.hwt.domain.entity.order.vo.OrderDetailsVo;
import com.hwt.domain.entity.user.wallet.HxUserWallet;
import com.hwt.domain.entity.user.wallet.HxUserWalletBill;
import com.hwt.domain.mapper.bureau.HwTravelBureauMapper;
import com.hwt.domain.mapper.cicerone.HxCiceroneHideMapper;
import com.hwt.domain.mapper.cicerone.HxCiceroneInfoMapper;
import com.hwt.domain.mapper.order.HwInsuranceMapper;
import com.hwt.domain.mapper.order.HwOrderMapper;
import com.hwt.domain.mapper.order.HwOrderRefundMapper;
import com.hwt.domain.mapper.order.HwOrderUnpaidMapper;
import com.hwt.domain.mapper.order.HwOrderUserInsubranceMapper;
import com.hwt.domain.mapper.order.HwOrderUserMapper;
import com.hwt.domain.mapper.order.HxOrderInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletBillMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletMapper;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.utils.JsonUtils;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryProjections;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.redis.RedisLock;
import com.hx.core.redis.RedisLockTyep;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.DateUtils;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.NumUtils;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.utils.SequenceFactoryBean;
import com.hx.core.utils.SequenceFactoryBean2;
import com.hx.core.utils.UUIDHelper;
import com.hx.core.utils.ZZBigDecimalUtils;
import com.hx.core.wyim.entity.FriendOperationNotice;
import com.hx.core.wyim.entity.emu.SMSNotice;
import com.hx.core.wyim.entity.system.SystemNotice;
import com.hx.core.wyim.notice.SentNotice;
import com.hx.core.wyim.service.ImService;
import com.hx.order.service.hx.OrderService;

@Service
public class OrderServiceImpl implements OrderService{
	
	@Autowired
	private HwOrderMapper hwOrderMapper;
	
	@Autowired
	private HwOrderUserMapper hwOrderUserMapper;
	
	@Autowired
	private HxCiceroneInfoMapper hxCiceroneInfoMapper;
	
	@Autowired
	private HxOrderInfoMapper hxOrderInfoMapper;
	
	@Autowired
	private HwOrderRefundMapper hwOrderRefundMapper;
	
	@Autowired
	private HwOrderUserInsubranceMapper hwOrderUserInsubranceMapper;
	
	@Autowired
	private HwInsuranceMapper hwInsuranceMapper;
	
	@Autowired
	private HxUserWalletMapper hxUserWalletMapper;
	
	@Autowired
	private HxUserWalletBillMapper hxUserWalletBillMapper;
	
	@Autowired
	private HxCiceroneHideMapper hxCiceroneHideMapper;
	
	@Autowired
	private HwOrderUnpaidMapper hwOrderUnpaidMapper;
	
	
	@Autowired
	private HxUserMapper hxUserMapper;
	
	@Autowired
	private SentNotice sentNotice;
	
	@Autowired
	private HwTravelBureauMapper  hwTravelBureauMapper;
	
	@Override
	@Transactional
	public HwOrderUnpaid insert(HwOrderUnpaid hwOrder, String hwOrderUserInserts)  throws Exception {
		hwOrder.setPeople_num(hwOrder.getChildren_num()+hwOrder.getAdult_num());
		HxOrderInfo hxOrderInfo = new HxOrderInfo();
		hxOrderInfo.setTravel_line_id(hwOrder.getLine_id());
		//封装hwOrder 和 hxOrderInfo
		List<HwOrderUserInsert> list = null;
		if (!ObjectHelper.isEmpty(hwOrderUserInserts)){
			list = JsonUtils.json2List(hwOrderUserInserts, HwOrderUserInsert.class);
		}
		
		handleOrder(hwOrder,list,hxOrderInfo);
		
		hxOrderInfo.setOrder_id(hwOrder.getOrder_id());
		hxOrderInfoMapper.insertSelective(hxOrderInfo);
		
		//支付
		//Object object = orderPay(hwOrder ,hxOrderInfo);
		
		
		return hwOrder;
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
	 * 订单处理
	 * @param hwOrder
	 * @param hwOrderUserInserts 
	 * @param hxOrderInfo
	 * @throws Exception 
	 */
	private void handleOrder(HwOrderUnpaid hwOrder, List<HwOrderUserInsert> hwOrderUserInserts, HxOrderInfo hxOrderInfo) throws Exception {
		long create_time = System.currentTimeMillis();
		hwOrder.setCreate_time(create_time);
		hwOrder.setOrder_num(new SequenceFactoryBean().getObject());
		//判断是哪种订单
		if(hwOrder.getCicerone_id()!=null&&hwOrder.getCicerone_id()!=0){
			HxCiceroneInfo hxCiceroneInfo = hxCiceroneInfoMapper.queryHxCiceroneInfoById(hwOrder.getCicerone_id());
			if (hxCiceroneInfo==null){
				throw new RuntimeException("导游找不到");
			}
			
			hxOrderInfo.setImage(hxCiceroneInfo.getCover());
			
			Long start_time = hwOrder.getStart_time();
			Long end_time = hwOrder.getEnd_time();
			
			//计算两个毫秒时间戳的天数
			int day = currentTimeMillis_day(start_time,end_time);
			
			//redis锁
			RedisLock redisLock = new RedisLock(RedisLockTyep.orderCicerone.redisLockTypePrefix+hxCiceroneInfo.getUser_id());
			redisLock.lock(RedisLockTyep.orderCicerone.timeout, RedisLockTyep.orderCicerone.expireSecs);
			
			//判断是否能下单
			cicerone_is_insert_order(start_time,day,hxCiceroneInfo.getUser_id(),redisLock);
			
			//解锁
			redisLock.unLock();
			
			hxOrderInfo.setDec("导游服务"+day+"天");
			
			BigDecimal everyday_price = hxCiceroneInfo.getEveryday_price();
			hxOrderInfo.setUnit_price(everyday_price);
			
			BigDecimal total = ZZBigDecimalUtils.safeMultiply(everyday_price, day);
			hwOrder.setPayment(total);
			//导游被预约次数+1
			hxCiceroneInfoMapper.is_reserved_numadd1(hwOrder.getCicerone_id());
			
		}else {
			if (hxOrderInfo.getTravel_line_id()==null||hxOrderInfo.getTravel_line_id()==0){
				throw new RuntimeException("必须选择路线或者导游");
			}
			//线路
			MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "line_id", hxOrderInfo.getTravel_line_id());
			MongoQueryProjections projections = new MongoQueryProjections();
			HwTravelLine hwTravelLine = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, queryValue, projections , HwTravelLine.class);
			hxOrderInfo.setDec(hwTravelLine.getLine_name());
			hxOrderInfo.setImage(hwTravelLine.getLine_cover());
			hxOrderInfo.setTravel_line_id(hwTravelLine.getLine_id());
			hwOrder.setBureau_id(hwTravelLine.getBureau_id());
			//根据订单的开始时间计算价格
			BigDecimal payment = returnPayment(hwOrder,hwTravelLine.getLine_price(),hxOrderInfo);
			hwOrder.setPayment(payment);
		}
		
		hwOrderUnpaidMapper.insertSelectiveBcakId(hwOrder);
		//订单用户保险
		if (!ObjectHelper.isEmpty(hwOrderUserInserts)){
			List<HwOrderUserInsubrance> hwOrderUserInsubrances = new ArrayList<>();
			for (int i = 0; i < hwOrderUserInserts.size(); i++) {
				
				hwOrderUserInserts.get(i).setOrder_id(hwOrder.getOrder_id());
				hwOrderUserInserts.get(i).setCreate_time(create_time);
				HwOrderUser hwOrderUser = hwOrderUserInserts.get(i);
				//订单用户添加，返回主键
				hwOrderUserMapper.insertSelectiveBcakId(hwOrderUser);
				//保险
				if(ObjectHelper.isEmpty(hwOrderUserInserts.get(i).getInsubrance_ids())){
					//选择默认保险
					// TODO Auto-generated method stub
					
				}else {
					String[] Insubrance_ids = hwOrderUserInserts.get(i).getInsubrance_ids().split(",");
					Long[] insubrance_ids = NumUtils.strToLong(Insubrance_ids);
					List<HwInsurance> hwInsurances = hwInsuranceMapper.queryByInsubrance_ids(insubrance_ids);
					if(hwInsurances!=null&&hwInsurances.size()==insubrance_ids.length){
						
						for (HwInsurance hwInsurance : hwInsurances) {
							if(hwInsurance.getIs_hide()==0){
								throw new RuntimeException(hwInsurance.getInsurance_name()+"保险已被下架，请重新选择");
							}else{
								//封装用户保险对象
								HwOrderUserInsubrance hwOrderUserInsubrance = new HwOrderUserInsubrance();
								hwOrderUserInsubrance.setCreate_tiem(create_time);
								hwOrderUserInsubrance.setInsubrance_id(hwInsurance.getInsurance_id());
								hwOrderUserInsubrance.setInsurance_company(hwInsurance.getInsurance_company());
								hwOrderUserInsubrance.setInsurance_dec(hwInsurance.getInsurance_dec());
								hwOrderUserInsubrance.setInsurance_name(hwInsurance.getInsurance_name());
								BigDecimal insurance_price = hwInsurance.getInsurance_price();
								hwOrderUserInsubrance.setInsurance_price(insurance_price);
								hwOrder.setPayment(ZZBigDecimalUtils.safeAdd(hwOrder.getPayment(), insurance_price));
								hwOrderUserInsubrance.setInsurance_url(hwInsurance.getInsurance_url());
								hwOrderUserInsubrance.setOrder_user_id(hwOrderUser.getOrder_user_id());
								hwOrderUserInsubrances.add(hwOrderUserInsubrance);
							}
						}
					}else {
						throw new RuntimeException("保险id传入有误");
					}
					
				}
			}
			//添加用户保险信息
			if (!ObjectHelper.isEmpty(hwOrderUserInsubrances)){
				hwOrderUserInsubranceMapper.insertSelectiveList(hwOrderUserInsubrances);
			}
			
		}
			
	}
	
	/**
	 * 判断连续这几天是否又被下过单的
	 * @param start_time
	 * @param day
	 * @param user_id  导游id
	 * @param redisLock 
	 */
	private void cicerone_is_insert_order(Long start_time, int day, Long user_id, RedisLock redisLock) {
		
		MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "user_id", user_id);
		Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, queryValue , null);
		
		Object work_time = findOne.get("work_time");
		if (work_time==null){
			//解锁
			redisLock.unLock();
			throw new RuntimeException("该导游还没有编辑可预约时间");
		}
		Map<String, Object> map = JsonUtils.json2Map(work_time.toString());
		for (int i = 0; i < day; i++) {
			String ymd = DateUtils.YMD(start_time+(24*60*60*1000L*i));
			Object object = map.get(ymd);
			if (object==null){
				//解锁
				redisLock.unLock();
				throw new RuntimeException(ymd+"不能被预约！");
			}
			HxCiceroneWorkTimeVo json2Bean = JsonUtils.json2Bean(object.toString(), HxCiceroneWorkTimeVo.class);
			if (json2Bean.getStatus()==1){
				//解锁
				redisLock.unLock();
				throw new RuntimeException(ymd+"已被预约，请重新下单");
			}else{
				
				//更改为被预约
				json2Bean.setStatus(1);
				map.put(ymd, JsonUtils.Bean2Json(json2Bean));
			}
		}
		
		//修改
		Map<String, Object> updateMap = new HashMap<>();
    	updateMap.put("work_time", JsonUtils.Bean2Json(map));
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("user_id", user_id);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, filterMap , updateMap );
	}
	/**
	 * 返回实际花费金额
	 * @param hwOrder
	 * @param price
	 * @param hxOrderInfo
	 * @return
	 */
	private BigDecimal returnPayment(HwOrderUnpaid hwOrder, String price, HxOrderInfo hxOrderInfo) {
		
		Map<String, Object> json2Map = JsonUtils.json2Map(price);
		String ymd = DateUtils.YMD(hwOrder.getStart_time());
		Object object = json2Map.get(ymd);
		if (object==null){
			throw new RuntimeException(ymd+"该时间不可选");
		}
		LinePrice json2Bean = JsonUtils.json2Bean(object.toString(), LinePrice.class);
		Integer children_num = hwOrder.getChildren_num();
		Integer adult_num = hwOrder.getAdult_num();
		
		BigDecimal children = ZZBigDecimalUtils.safeMultiply(children_num, new BigDecimal(json2Bean.getChildPrice()==null?"0.00":json2Bean.getChildPrice()));
		BigDecimal adult = ZZBigDecimalUtils.safeMultiply(adult_num, new BigDecimal(json2Bean.getAdultPrice()));
		hxOrderInfo.setUnit_price(adult);
		return ZZBigDecimalUtils.safeAdd(children, adult);
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

	@Override
	public List<HxOrderInfoBaseVo> qureyByMapToHx(Map<String, Object> map) {
		List<HxOrderInfoBaseVo> list =  hwOrderMapper.qureyByMapToHx(map);
		return list;
	}

	@Override
	@Transactional
	public void apply_refundable(Long user_id, Long order_id) throws Exception {
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder!=null){
			if(hwOrder.getUser_id() != user_id){
				throw new RuntimeException("非本人订单，不能操作");
			}else{
				
				HwOrderRefund hwOrderRefund = apply_sales( hwOrder);
				hwOrderRefundMapper.insert(hwOrderRefund);
				hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
			}
		}else{
			throw new RuntimeException("该订单不存在");
		}
	}
	
	/**
	 * 申请退款
	 * @param hwOrder
	 * @throws Exception 
	 */
	private HwOrderRefund apply_sales(HwOrder hwOrder) throws Exception {
		
		long currentTimeMillis = System.currentTimeMillis();
		HwOrderRefund hwOrderRefund = new HwOrderRefund();
		hwOrderRefund.setOrder_id(hwOrder.getOrder_id());
		
		if (hwOrder.getStatus()==1){
			//直接退全款
			BigDecimal payment = hwOrder.getPayment();
			hwOrderRefund.setRefund_dec("订单未被确认，直接全额退款");
			hwOrderRefund.setIs_complete(1);
			
			hwOrderRefund.setRefund_sum(payment);
			
			hwOrder.setRefundable_time(currentTimeMillis);
			hwOrder.setStatus(5);
			
			//退款
			refund(payment ,hwOrder.getUser_id());
			
			//账单
			Long user_id = hwOrder.getOrder_id();
			hxUserWalletMapper.addBalanceByUserId(user_id, payment);
			HxUserWalletBill insertBill = insertBill(hwOrder,hwOrder.getPayment(), 2, 4, "全额退款", 1, 1);
			hxUserWalletBillMapper.insertSelective(insertBill);
			
			sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "用户取消订单", "订单未被确认，直接全额退款", null, 2, null, JsonUtils.Bean2Json(insertBill));
			//判断是否是导游的订单，是则修改导游的被预约状态
			if (hwOrder.getCicerone_id()!=0){
				sales_cicerone_update_workTime(hwOrder);
				sentNotice.sendSystem(getUserImId(hwOrder.getCicerone_id()), "用户取消订单", "您有一个订单已被买家取消", null, 4, hwOrder.getOrder_id()+"", null);
			}else {
				sentNotice.sendSystemSms(getBureauPhone(hwOrder.getBureau_id()), SMSNotice.bureauordercancel.smsTypeTemplateNumber, hwOrder.getOrder_num());
			}
			
		}else if(hwOrder.getStatus()==2){
			//申请退款操作
			if (hwOrder.getCicerone_id()!=0){
				Long start_time = hwOrder.getStart_time();
				Long oneDay = 24*60*60*1000L;  //24小时毫秒数
				Long threeDay = 72*60*60*1000L;  //72小时毫秒数
				Long time = start_time - currentTimeMillis;
				
				//违约金
				BigDecimal penalty = new BigDecimal("0.00");
				String remarks = "";
				if(time<=0){
					throw new RuntimeException("订单已开始无法退款");
				} else if ( time > 0 && time <oneDay){
					//导游退款为订单开始前不足24小时扣订单金额的20%
					penalty = ZZBigDecimalUtils.safeMultiply(hwOrder.getPayment(), 0.2);
					hwOrderRefund.setRefund_dec("导游退款为订单开始前不足24小时扣订单金额的20%");
					remarks = "抠20%违约金";
					//账单
					Long user_id = hwOrder.getCicerone_id();
					hxUserWalletMapper.addBalanceByUserId(user_id, penalty);
					HxUserWalletBill insertBill = insertBill(hwOrder,penalty, 2, 6, "违约金收入", 1, 1);
					hxUserWalletBillMapper.insertSelective(insertBill);
					sentNotice.sendSystem(getUserImId(hwOrder.getCicerone_id()), "用户取消订单", "订单开始前不足24小时得到订单金额的20%违约金", null, 2, null, JsonUtils.Bean2Json(insertBill));
				} else if (time>=oneDay && time < threeDay){
					//大于24小时小于72小时扣10%
					penalty = ZZBigDecimalUtils.safeMultiply(hwOrder.getPayment(), 0.1);
					hwOrderRefund.setRefund_dec("大于24小时小于72小时扣10%");
					remarks = "抠10%违约金";
					//账单
					Long user_id = hwOrder.getCicerone_id();
					hxUserWalletMapper.addBalanceByUserId(user_id, penalty);
					HxUserWalletBill insertBill = insertBill(hwOrder,penalty, 2, 6, "违约金收入", 1, 1);
					hxUserWalletBillMapper.insertSelective(insertBill);
					sentNotice.sendSystem(getUserImId(hwOrder.getCicerone_id()), "用户取消订单", "订单开始前不足大于24小时小于72小时得到订单金额的10%违约金", null, 2, null, JsonUtils.Bean2Json(insertBill));
				} else {
					//大于72小时不扣违约金
					hwOrderRefund.setRefund_dec("大于72小时不扣违约金");
					remarks = "全额退款";
				}
				BigDecimal safeSubtract = ZZBigDecimalUtils.safeSubtract(true, hwOrder.getPayment(), penalty);
				hwOrderRefund.setRefund_sum(safeSubtract);
				hwOrderRefund.setIs_complete(1);
				
				//账单
				Long user_id = hwOrder.getOrder_id();
				hxUserWalletMapper.addBalanceByUserId(user_id, safeSubtract);
				HxUserWalletBill insertBill = insertBill(hwOrder,safeSubtract, 2, 4, remarks, 1, 1);
				hxUserWalletBillMapper.insertSelective(insertBill);
				sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "用户取消订单", "订单退款金额", null, 2, null, JsonUtils.Bean2Json(insertBill));
				
				sales_cicerone_update_workTime(hwOrder);
				sentNotice.sendSystem(getUserImId(hwOrder.getCicerone_id()), "用户取消订单", "您有一个订单已被买家取消", null, 4, hwOrder.getOrder_id()+"", null);
				hwOrder.setStatus(5);
				hwOrder.setRefundable_time(currentTimeMillis);
			} else {
				//旅行社
				hwOrderRefund.setIs_complete(0);
				hwOrder.setStatus(4);
			}
			
			hwOrder.setApply_sales_time(currentTimeMillis);
			hwOrder.setUpdate_time(currentTimeMillis);
			
			
		}else {
			throw new RuntimeException("该订单已无法进行退款操作");
		}
		return hwOrderRefund;
	}
	

	/**
	 * 将导游被预约改为可预约
	 * @param hwOrder
	 */
	private void sales_cicerone_update_workTime(HwOrder hwOrder) {
		
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
	 * 退款
	 * @param payment  //退款金额
	 * @param user_id  //用户id
	 */
	private void refund(BigDecimal payment, Long user_id) {
		HxUserWallet hxUserWallet = hxUserWalletMapper.selectByUserId(user_id);
		if (hxUserWallet==null){
			HxUserWallet hxUserWallet2 = new HxUserWallet();
			BigDecimal bigDecimal = new BigDecimal("0.00");
			hxUserWallet2.setBalance(payment);
			hxUserWallet2.setCreate_time(System.currentTimeMillis());
			hxUserWallet2.setDay_pass_num(0);
			hxUserWallet2.setIs_can(payment);
			hxUserWallet2.setIs_not_can(bigDecimal);
			hxUserWallet2.setName_id(user_id);
			hxUserWallet2.setName_type(1);
			hxUserWallet2.setPayment_salt(UUIDHelper.createUUId());
			hxUserWalletMapper.insertSelective(hxUserWallet);
		}else {
			hxUserWalletMapper.addBalanceByUserId(user_id, payment);
		}
	}

	@Override
	@Transactional
	public void tourist_delete(Long user_id, Long order_id) {
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder==null){
			throw new RuntimeException("该订单不存在");
		}else{
			if (hwOrder.getUser_id()!=user_id){
				throw new RuntimeException("非本人不能操作");
			}else{
				if (hwOrder.getStatus()==3||hwOrder.getStatus()==5||hwOrder.getStatus()==6){
					hwOrder.setTourist_is_delete(1);
					hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
					
				}else{
					if (hwOrder.getTourist_is_delete()==1){
						throw new RuntimeException("该订单已删除");
					}else{
						throw new RuntimeException("该订单还不能删除");
					}
					
				}
			}
		}
		
	}

	@Override
	@Transactional
	public void cicerone_delete(Long user_id, Long order_id) {
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder==null){
			throw new RuntimeException("该订单不存在");
		}else{
			if (hwOrder.getCicerone_id()!=user_id){
				throw new RuntimeException("非本人不能操作");
			}else{
				if (!(hwOrder.getStatus()==3||hwOrder.getStatus()==5||hwOrder.getStatus()==6)){
					throw new RuntimeException("该订单还不能删除");
				}else{
					if (hwOrder.getCicerone_is_delete()==1){
						throw new RuntimeException("该订单已删除");
					}
					hwOrder.setCicerone_is_delete(1);
					hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
				}
			}
		}
		
	}

	@Override
	@Transactional
	public void confirm_order(Long order_id, Long user_id) throws Exception {
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder==null){
			throw new RuntimeException("该订单不存在");
		}else{
			if (hwOrder.getCicerone_id()!=user_id){
				throw new RuntimeException("非本人不能操作");
			}else{
				if (hwOrder.getStatus()!=1){
					throw new RuntimeException("该订单已被处理，不能确认");
				}else {
					long currentTimeMillis = System.currentTimeMillis();
					hwOrder.setUpdate_time(currentTimeMillis);
					//确认接单
					hwOrder.setStatus(2);
					hwOrder.setAccept_time(currentTimeMillis);
					
					//修改接单次数
					hxCiceroneInfoMapper.service_countadd1(hwOrder.getCicerone_id());
					hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
					System.err.println("++++++++++++++确认");
					sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "订单被确认", "您有一个订单被导游家确认", null, 4, hwOrder.getOrder_id()+"", null);
				}
			}
		}
		
	}

	@Override
	@Transactional
	public void confirm_refundable(Long order_id, Long user_id) {
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder==null){
			throw new RuntimeException("该订单不存在");
		}else{
			if (hwOrder.getCicerone_id()!=user_id){
				throw new RuntimeException("非本人不能操作");
			}else{
				if (hwOrder.getStatus()!=4){
					throw new RuntimeException("该订单已被处理，不能确认");
				}else {
					
					//执行退款业务
					HwOrderRefund orderRefund = hwOrderRefundMapper.selectByPrimaryKey(order_id);
					BigDecimal refund_sum = orderRefund.getRefund_sum();
					//退款
					refund(refund_sum, hwOrder.getUser_id());
					
					orderRefund.setIs_complete(1);
					hwOrderRefundMapper.updateByPrimaryKeySelective(orderRefund);
					//退款完成
					long currentTimeMillis = System.currentTimeMillis();
					hwOrder.setUpdate_time(currentTimeMillis);
					hwOrder.setStatus(5);
					hwOrder.setRefundable_time(currentTimeMillis);
					hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
				}
			}
		}
		
	}

	@Override
	@Transactional
	public void cicerone_cancel(Long user_id, Long order_id) throws Exception {
		
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder==null){
			throw new RuntimeException("该订单不存在");
		}else{
			if (hwOrder.getCicerone_id()!=user_id){
				throw new RuntimeException("非本人不能操作");
			}else{
				if (hwOrder.getStatus()!=2){
					throw new RuntimeException("该订单已不能取消");
				}else {
					long currentTimeMillis = System.currentTimeMillis();
					
					HwOrderRefund hwOrderRefund = new HwOrderRefund();
					hwOrderRefund.setOrder_id(hwOrder.getOrder_id());
					BigDecimal payment = hwOrder.getPayment();
					
					//退款
					refund(payment, hwOrder.getUser_id());
					
					hwOrderRefund.setRefund_dec("导游发起的取消订单，所以全额退款");
					hwOrderRefund.setIs_complete(1);
					hwOrderRefund.setRefund_sum(payment);
					hwOrderRefundMapper.insert(hwOrderRefund);
					
					
					hwOrder.setRefundable_time(currentTimeMillis);
					hwOrder.setUpdate_time(currentTimeMillis);
					hwOrder.setStatus(5);
					hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
					
					hxUserWalletMapper.addBalanceByUserId(user_id, payment);
					HxUserWalletBill insertBill = insertBill(hwOrder,hwOrder.getPayment(), 1, 4, "全额退款", 1, 1);
					hxUserWalletBillMapper.insertSelective(insertBill);
					sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "导游取消订单", "导游发起的取消订单，所以全额退款", null, 2, null, JsonUtils.Bean2Json(insertBill));
					
					//将导游的状态修改为不能接单  3天内不能接单
					hxCiceroneInfoMapper.is_hide(user_id);
					HxCiceroneHide hxCiceroneHide = new HxCiceroneHide();
					hxCiceroneHide.setStart_time(currentTimeMillis);
					hxCiceroneHide.setEnd_time(currentTimeMillis+3*24*60*60*1000);
					hxCiceroneHide.setHide_dec("由于取消用户订单，禁止3天内接单");
					hxCiceroneHide.setUser_id(user_id);
					HxCiceroneHide selectByPrimaryKey = hxCiceroneHideMapper.selectByPrimaryKey(user_id);
					if (selectByPrimaryKey != null){
						hxCiceroneHideMapper.updateByPrimaryKeySelective(hxCiceroneHide);
					}else{
						hxCiceroneHideMapper.insertSelective(hxCiceroneHide);
					}
					
					sentNotice.sendSystem(getUserImId(hwOrder.getCicerone_id()), "导游取消订单", "导游主动取消订单，3天内不能被下单！", null, 1, null, null);
				}
				sales_cicerone_update_workTime(hwOrder);
				sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "订单被取消", "您有一个订单被导游取消", null, 4, hwOrder.getOrder_id()+"", null);
				
			}
			
		}
	}

	@Override
	@Transactional
	public void cicerone_refuse(Long user_id, Long order_id, int type) throws Exception {
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (hwOrder==null){
			throw new RuntimeException("该订单不存在");
		}else{
			if (hwOrder.getCicerone_id()!=user_id){
				throw new RuntimeException("非本人不能操作");
			}else{
				long currentTimeMillis = System.currentTimeMillis();
				hwOrder.setRefuse_time(currentTimeMillis);
				hwOrder.setUpdate_time(currentTimeMillis);
				hwOrder.setStatus(6);
				hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
			}
			
		}
		sales_cicerone_update_workTime(hwOrder);
		
		//拒绝订单 全额退款
		hxUserWalletMapper.addBalanceByUserId(user_id, hwOrder.getPayment());
		HxUserWalletBill insertBill = insertBill(hwOrder,hwOrder.getPayment(), 2, 4, "全额退款", 1, 1);
		hxUserWalletBillMapper.insertSelective(insertBill);
		if(type==0){
			sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "订单被拒绝", "您有一个订单由于导游没有在规定时间内确认,被自动拒绝", null, 4, hwOrder.getOrder_id()+"", null);
			sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "导游拒绝订单", "订单由于导游没有在规定时间内确认,被自动拒绝，所以全额退款", null, 2, null, JsonUtils.Bean2Json(insertBill));
		}else{
			sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "订单被拒绝", "您有一个订单被导游拒绝", null, 4, hwOrder.getOrder_id()+"", null);
			sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "导游拒绝订单", "导游拒绝订单，所以全额退款", null, 2, null, JsonUtils.Bean2Json(insertBill));
		}
	}

	@Override
	public OrderDetailsVo qureyDetails(Long user_id, Long order_id) {
		OrderDetailsVo orderDetailsVo = new OrderDetailsVo();
		//基本信息
		HwOrderVo hwOrderVo = hwOrderMapper.query_by_order_id(order_id);
		if(hwOrderVo == null){
			throw new RuntimeException("该订单不存在");
		}
		if(hwOrderVo.getUser_id()==user_id || hwOrderVo.getCicerone_id() == user_id){
			orderDetailsVo.setHwOrderVo(hwOrderVo);
			//商品信息
			HxOrderInfoVo hxOrderInfoVo = hxOrderInfoMapper.query_by_order_id(order_id);
			orderDetailsVo.setHxOrderInfoVo(hxOrderInfoVo);
			if (hwOrderVo.getApply_sales_time()!=null||hwOrderVo.getRefundable_time()!=null){
				//说明存在退款情况
				HwOrderRefundVo hwOrderRefundVo = hwOrderRefundMapper.query_by_order_id(order_id);
				orderDetailsVo.setHwOrderRefundVo(hwOrderRefundVo);
			}
			//用户信息
			List<HwOrderUserVo> hwOrderUserVos =  hwOrderUserMapper.query_list_by_order_id(order_id);
			orderDetailsVo.setHwOrderUserVos(hwOrderUserVos);
		}else {
			throw new RuntimeException("非本人不能操作");
		}
		return orderDetailsVo;
	}

	@Override
	@Transactional
	public void pay_success(String order_num) throws Exception {
		HwOrder hwOrder = hwOrderMapper.selectByOrder_num(order_num);
		hwOrder.setPaymen_type(1);
		hwOrder.setPayment_time(System.currentTimeMillis());
		hwOrder.setStatus(1);
		hwOrderMapper.updateByPrimaryKeySelective(hwOrder);
		
		HxUserWalletBill insertBill = insertBill(hwOrder,hwOrder.getPayment(), 1, 2,"付宝支付", 0, 1);
		insertBill.setIs_wallet(1);
		hxUserWalletBillMapper.insertSelective(insertBill);
		
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
		HxUserWallet hxUserWallet = hxUserWalletMapper.selectByUserId(hwOrder.getUser_id());
		if (is_get==1&&is_wallet==2){
			hxUserWallet.setBalance(ZZBigDecimalUtils.safeAdd(hxUserWallet.getBalance(), operation_amount));
		}
		if (is_get==0&&is_wallet==2){
			if (hxUserWallet.getIs_can().compareTo(operation_amount)==-1){
				throw new RuntimeException("余额不足");
			}
			hxUserWallet.setBalance(ZZBigDecimalUtils.safeSubtract(true,hxUserWallet.getBalance(), operation_amount));
		}
		HxUserWalletBill createBill = createBill(hwOrder.getUser_id(), hxOrderInfo.getDec(), hxOrderInfo.getDec(), bill_type, hwOrder.getOrder_num(), new SequenceFactoryBean2().getObject(),
				remarks, operation_amount, hxUserWallet.getBalance(), null, is_get, is_success);
		return createBill;
		
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
}
