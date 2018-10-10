package com.hx.order.service.bureau.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.HwOrderRefund;
import com.hwt.domain.entity.order.vo.HwOrderRefundVo;
import com.hwt.domain.entity.order.vo.HwOrderUserVo;
import com.hwt.domain.entity.order.vo.HwOrderVo;
import com.hwt.domain.entity.order.vo.HxOrderInfoVo;
import com.hwt.domain.entity.order.vo.OrderDetailsVo;
import com.hwt.domain.entity.user.wallet.HxUserWallet;
import com.hwt.domain.entity.user.wallet.HxUserWalletBill;
import com.hwt.domain.mapper.order.HwOrderMapper;
import com.hwt.domain.mapper.order.HwOrderRefundMapper;
import com.hwt.domain.mapper.order.HwOrderUserMapper;
import com.hwt.domain.mapper.order.HxOrderInfoMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletBillMapper;
import com.hwt.domain.mapper.user.wallet.HxUserWalletMapper;
import com.hx.core.mongo.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.utils.SequenceFactoryBean2;
import com.hx.core.utils.ZZBigDecimalUtils;
import com.hx.core.wyim.notice.SentNotice;
import com.hx.order.service.bureau.BureauOrderService;
import com.hx.order.service.bureau.vo.PageResultOrder;

@Service
public class BureauOrderServiceImpl implements BureauOrderService {
	
	@Autowired
	private HwOrderMapper hwOrderMapper;
	@Autowired
	private HxOrderInfoMapper hxOrderInfoMapper;
	@Autowired
	private HwOrderRefundMapper hwOrderRefundMapper;
	@Autowired
	private HwOrderUserMapper hwOrderUserMapper;
	@Autowired
	private HxUserWalletMapper hxUserWalletMapper;
	
	@Autowired
	private HxUserWalletBillMapper hxUserWalletBillMapper;
	
	@Autowired
	private SentNotice sentNotice;
	
	@Autowired
	private HxUserMapper hxUserMapper;
	

	@Override
	public PageResultOrder query(Map<String, Object> map) {
		
		//全部订单
		Long status0 = 0l;
		
		//待确认
		Long status1 = 0l;
		
		//待开始
		Long status2 = 0l;
		
		//进行中
		Long status3 = 0l;
		
		//已完成
		Long status4 = 0l;
		
		//退款订单
		Long status5 = 0l;
		
		List<HwOrder> hwOrders = hwOrderMapper.queryAllByBureau_id(Long.parseLong(map.get("bureau_id").toString()));
		if (!ObjectHelper.isEmpty(hwOrders)){
			status0 = (long) hwOrders.size();
			for (HwOrder hwOrder : hwOrders) {
				if (hwOrder.getStatus()==1){
					status1++;
				}
				if (hwOrder.getStatus()==2&&System.currentTimeMillis()<hwOrder.getStart_time()){
					status2++;
				}
				if(hwOrder.getStatus()==2&&System.currentTimeMillis()>=hwOrder.getStart_time()){
					status3++;
				}
				if (hwOrder.getStatus()==3){
					status4++;
				}
				if (hwOrder.getStatus()==4||hwOrder.getStatus()==5){
					status5++;
				}
			}
		}
		
		//满足条件的总条数
		Long count = hwOrderMapper.qureyCountByMapToBureau(map);
		
		//满足条件的数据
		List<Map<String, Object>> list = hwOrderMapper.qureyByMapToBureau(map);
		
		PageResultOrder pageResultOrder = new PageResultOrder();
		pageResultOrder.setCount(count);
		pageResultOrder.setDataList(list);
		pageResultOrder.setStatus0(status0);
		pageResultOrder.setStatus1(status1);
		pageResultOrder.setStatus2(status2);
		pageResultOrder.setStatus3(status3);
		pageResultOrder.setStatus4(status4);
		pageResultOrder.setStatus5(status5);
		
		return pageResultOrder;
	}

	@Override
	@Transactional
	public void refuse(Long order_id, Long bureau_id,String business_remarks) throws Exception {
		//基本信息
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if(hwOrder == null){
			throw new RuntimeException("该订单不存在");
		}
		if(hwOrder.getBureau_id()==bureau_id){
			if (hwOrder.getStatus()!=1){
				throw new RuntimeException("该订单不可做拒绝超作！");
			}
			//拒绝订单 全额退款
			hxUserWalletMapper.addBalanceByUserId(hwOrder.getUser_id(), hwOrder.getPayment());
			HxUserWalletBill insertBill = insertBill(hwOrder,hwOrder.getPayment(), 2, 4, "全额退款", 1, 1);
			insertBill.setSource(3);
			hxUserWalletBillMapper.insertSelective(insertBill);
			
			hwOrderMapper.bureauOrderRefuse(order_id,bureau_id);
			
			HwOrderRefund record = createHwOrderRefund(order_id, bureau_id, business_remarks==null?"自动拒绝":business_remarks);
			record.setIs_complete(1);
			record.setRefund_sum(hwOrder.getPayment());
			record.setRefund_dec("商家拒绝订单全额退款！");
			hwOrderRefundMapper.insertSelective(record );
			if(business_remarks==null){
				sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "订单被拒绝", "您有一个订单由于旅行社没有在规定时间内确认,被自动拒绝", null, 4, hwOrder.getOrder_id()+"", null);
				sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "旅行社拒绝订单", "订单由于旅行社没有在规定时间内确认,被自动拒绝，所以全额退款", null, 2, null, JsonUtils.Bean2Json(insertBill));
			}else{
				sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "订单被拒绝", "您有一个订单被旅行社拒绝", null, 4, hwOrder.getOrder_id()+"", null);
				sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "旅行社拒绝订单", "旅行社拒绝订单，所以全额退款", null, 2, null, JsonUtils.Bean2Json(insertBill));
			}
		}else {
			throw new RuntimeException("非自己旅行社订单不能操作");
		}
		
	}
	
	/**
	 * 创建订单退款
	 * @param order_id
	 * @param bureau_id
	 * @param business_remarks
	 * @return
	 */
	private HwOrderRefund createHwOrderRefund(Long order_id, Long bureau_id,String business_remarks){
		HwOrderRefund record = new HwOrderRefund();
		record.setBusiness_remarks(business_remarks);
		record.setOrder_id(order_id);
		return record;
	}
	
	@Override
	public OrderDetailsVo qureyDetails(Long bureau_id, Long order_id) {
		OrderDetailsVo orderDetailsVo = new OrderDetailsVo();
		//基本信息
		HwOrderVo hwOrderVo = hwOrderMapper.query_by_order_id(order_id);
		if(hwOrderVo == null){
			throw new RuntimeException("该订单不存在");
		}
		if(hwOrderVo.getBureau_id()==bureau_id){
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
			throw new RuntimeException("非自己旅行社订单不能操作");
		}
		return orderDetailsVo;
	}

	@Override
	public void cancel(Long order_id, Long bureau_id,String business_remarks) throws Exception {
		//基本信息
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if(hwOrder == null){
			throw new RuntimeException("该订单不存在");
		}
		if(hwOrder.getBureau_id()==bureau_id){
			if (hwOrder.getStatus()!=2){
				throw new RuntimeException("该订单不可做取消超作！");
			}
			
			hwOrderMapper.bureauOrderCancel(order_id,bureau_id);
			
			HwOrderRefund record = createHwOrderRefund(order_id, bureau_id, business_remarks);
			record.setIs_complete(0);
			
			hwOrderRefundMapper.insertSelective(record );
			sentNotice.sendSystem(getUserImId(hwOrder.getUser_id()), "订单被拒绝", "您有一个订单被旅行社拒绝", null, 4, hwOrder.getOrder_id()+"", null);
		}else {
			throw new RuntimeException("非自己旅行社订单不能操作");
		}
		
	}

	@Override
	@Transactional
	public void confirm(Long bureau_id, Long order_id) throws Exception {
		HwOrder order = hwOrderMapper.selectByPrimaryKey(order_id);
		if (ObjectHelper.isEmpty(order)){
			throw new RuntimeException("订单不存在");
		}
		
		if (order.getBureau_id()!=bureau_id ){
			throw new RuntimeException("该订单不是本公司所有");
		}
		
		if (order.getStatus()!=1){
			throw new RuntimeException("该订单已不能确认");
		}
		hwOrderMapper.bureauConfirm(order_id,bureau_id);
		
		sentNotice.sendSystem(getUserImId(order.getUser_id()), "订单被确认", "您有一个订单被旅行社确认", null, 4, order.getOrder_id()+"", null);
		
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
}
