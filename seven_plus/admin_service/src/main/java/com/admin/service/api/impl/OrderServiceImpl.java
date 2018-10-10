package com.admin.service.api.impl;

import com.admin.service.api.OrderService;
import com.common.config.WechatConfig;
import com.common.config.WechatPayConfig;
import com.common.consts.Consts;
import com.common.excption.AuthExceptionConstants;
import com.common.excption.BaseException;
import com.common.util.PageUtil;
import com.common.util.PriceUtil;
import com.common.util.UUIDHelper;
import com.common.util.XMLUtil;
import com.common.wechat.wxpay.bean.notify.WxPayNotifyResponse;
import com.common.wechat.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.common.wechat.wxpay.bean.request.WxPayUnifiedOrderRequest;
import com.common.wechat.wxpay.bean.result.WxPayClientResult;
import com.common.wechat.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.common.wechat.wxpay.constant.WxPayConstants;
import com.common.wechat.wxpay.exception.WxPayException;
import com.common.wechat.wxpay.service.WxPayService;
import com.common.wechat.wxpay.service.impl.WxPayServiceImpl;
import com.common.wechat.wxpay.util.SignUtils;
import com.domain.admin.entity.AdminSystemconfig;
import com.domain.admin.mapper.AdminSystemConfigMapper;
import com.domain.plus.entity.*;
import com.domain.plus.mapper.*;
import com.domain.plus.param.CreatePriceParam;
import com.domain.plus.param.OrderRecordParam;
import com.domain.plus.param.PaymentParam;
import com.domain.plus.vo.CreatePriceVo;
import com.domain.plus.vo.OrderRecordApiVo;
import com.domain.plus.vo.OrderRenewVo;
import com.domain.plus.vo.PlusCarVo;
import io.swagger.annotations.ApiModelProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 15:29
 * @Description:
 */
@Service
public class OrderServiceImpl implements OrderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderService.class);


    @Autowired
    private AdminSystemConfigMapper adminSystemConfigMapper;

    @Autowired
    private PlusOrderMapper orderMapper;

    @Autowired
    private PlusCarMapper carMapper;

    @Autowired
    private PlusStoreMapper storeMapper;

    @Autowired
    private OrderRenewMapper orderRenewMapper;

    @Autowired
    private OrderRecordMapper orderRecordMapper;

    @Autowired
    private PlusUserMapper userMapper;

    @Autowired
    private PlusBrokerageMapper brokerageMapper;

    @Autowired
    private WxPayService wxPayService;

    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private WechatPayConfig wechatPayConfig;



    @Override
    public List<OrderRecordApiVo> getOrderRecord(Long id, Integer orderType, Integer pageNo, Integer pageSize) {
        Integer index = PageUtil.init(pageNo,pageSize).getIndex();
        Integer last = PageUtil.init(pageSize,pageSize).getPageSize();
        return orderRecordMapper.queryOrderRecordApiVo(id,orderType,index,last);
    }

    @Override
    public List<OrderRenewVo> getOrderPayment(Long id, Integer orderType) {
        return orderRenewMapper.queryOrderRenewByUserIdAndType(id,orderType);
    }

    @Override
    public CreatePriceVo createPrice(CreatePriceParam param) {
        CreatePriceVo priceVo = new CreatePriceVo();
        priceVo.setOrderType(param.getOrderType());
        long expireTime = 0;
        int count = 0;
        if (null != param.getOrderId()){
            PlusOrder order = orderMapper.queryOrderById(param.getOrderId());
            expireTime = order.getExpireTime();
            count = order.getTotalCount();
        }
        if (param.getOrderType() == Consts.PlusOrderType.TYPE_1.getCode()){
            //天天洗车
            String carWashPrice = adminSystemConfigMapper.getValue(Consts.CAR_WASH_PRICE_KEY);
            priceVo.setPrice(Long.parseLong(carWashPrice + "00"));
            // 获取使用次数
            priceVo.setCount(Integer.parseInt(adminSystemConfigMapper.getValue(Consts.CAR_WASH_COUNT)));
            return priceVo;
        } else {
            //划痕无忧
            //1.查询汽车
            PlusCar carVo = carMapper.findById(param.getCarId());
            double price = Double.parseDouble(carVo.getEvaluation());
            priceVo.setPrice(getPrice(price,carVo.getCarNature(),carVo.getDrivingAge()));
            priceVo.setCarNO(carVo.getCarNo());
            priceVo.setCarBrand(carVo.getCarBrand());
            priceVo.setCarNature(carVo.getCarNature());
            priceVo.setDrivingAge(carVo.getDrivingAge());
            priceVo.setCarType(carVo.getCarType());
            // 获取使用次数
            priceVo.setCount(count + Integer.parseInt(adminSystemConfigMapper.getValue(Consts.CAR_SCRATCH_COUNT)));
            priceVo.setExpireTime(expireTime == 0 ? System.currentTimeMillis() + Consts.YEAR_MILLISECOND : expireTime + Consts.YEAR_MILLISECOND);
            return priceVo;
        }
    }


//    public static void main(String[] args) {
//        OrderServiceImpl orderService = new OrderServiceImpl();
//
//        System.out.println(orderService.getPrice(13.99,));
//    }


    /**
     *  计算价格
     * @param carPrice 车价万
     * @param nature 用途
     * @param carAge 车辆
     * @return 价格分
     */
    private Long getPrice(Double carPrice, Integer nature, Integer carAge){
        //基础价格
        List<AdminSystemconfig> basePriceList = adminSystemConfigMapper.getConfigByKey(PriceUtil.SCRATCH_BASE_PRICE);
        //营运非营运
        List<AdminSystemconfig> naturePriceList = adminSystemConfigMapper.getConfigByKey(PriceUtil.SCRATCH_OPERATION_RATE);
        //车龄
        List<AdminSystemconfig> agePriceList = adminSystemConfigMapper.getConfigByKey(PriceUtil.SCRATCH_AGE_RATE);
        //附加费率
        List<AdminSystemconfig> additionaPriceList = adminSystemConfigMapper.getConfigByKey(PriceUtil.SCRATCH_ADDITIONA_RATE);
        Integer basePrice = 0;
        Integer perationRate = 0;
        Integer ageRate = 0;
        Integer additionaRate = 0;
        Optional<AdminSystemconfig> basePriceConfig = basePriceList.stream().filter(l -> {
            int[] interval = getInterval(l.getDisplayName());
            return interval[0] < carPrice && interval[1] >= carPrice;
        } ).findFirst();
        if (basePriceConfig.isPresent())
            basePrice = Integer.parseInt(basePriceConfig.get().getConfigValue());
        Optional<AdminSystemconfig> natureConfig = naturePriceList.stream().filter(l -> getValue(l.getDisplayName()) == nature).findFirst();
        if (natureConfig.isPresent())
            perationRate = Integer.parseInt(natureConfig.get().getConfigValue());
        Optional<AdminSystemconfig> ageConfig = agePriceList.stream().filter(l -> getValue(l.getDisplayName()) == carAge).findFirst();
        if (ageConfig.isPresent())
            ageRate = Integer.parseInt(ageConfig.get().getConfigValue());
        Optional<AdminSystemconfig> additionaConfig = additionaPriceList.stream().filter(l -> {
            int[] interval = getInterval(l.getDisplayName());
            return interval[0] < carPrice && interval[1] >= carPrice;
        } ).findFirst();
        if (additionaConfig.isPresent())
            additionaRate = Integer.parseInt(additionaConfig.get().getConfigValue());
        return PriceUtil.getPrice(basePrice,perationRate,ageRate,additionaRate);
    }

    /**
     * 划痕无忧附加费率{100-&}
     * @return
     */
    private int[] getInterval(String str){
        String base = str.substring(str.indexOf("{") + 1,str.length() -1);
        String[] n = base.split("-");
        return new int[] {Integer.parseInt(n[0]),n[1].equals("&")?Integer.MAX_VALUE:Integer.parseInt(n[1])};
    }

    /**
     * 划痕无忧营运费率{1}
     * @param str
     * @return
     */
    private int getValue(String str){
        String base = str.substring(str.indexOf("{") + 1,str.length() -1);
        return Integer.parseInt(base);
    }

    @Transactional
    @Override
    public WxPayClientResult unifiedOrder(PaymentParam param, String ip, Long userId, String openId) {
        //获取价格
        CreatePriceVo priceVo = createPrice(param);
        //价格
        Integer price = Integer.parseInt(String.valueOf(priceVo.getPrice()));
        String orderNo = UUIDHelper.getUUID();
        //失败或抛异常
        // 前期帐号没有权限需要后期测试 调起统一下单
        WxPayClientResult result = unifiedOrder(price, ip, openId, orderNo);
        //微信下单成功
        if (param.getPaymentType() == 0) {
            //新增 order
            PlusOrder order  = new PlusOrder();
            order.setCarId(param.getCarId());
            order.setCreateTime(System.currentTimeMillis());
            order.setUserId(userId);
            order.setIsExpire(Consts.OrderIsExpire.paying.getCode());
            order.setExpireTime(order.getCreateTime() + Consts.YEAR_MILLISECOND);
            order.setOrderType(param.getOrderType());
            order.setTotalCount(priceVo.getCount());
            orderMapper.insertPlusOrder(order);
            //订单支付信息
            OrderRenew renew = new OrderRenew();
            renew.setCreateTime(order.getCreateTime());
            renew.setOrderId(order.getId());
            renew.setOrderNo(orderNo);
            renew.setRenewMoney(Long.valueOf(price));
            renew.setUpdateTime(order.getExpireTime());
            orderRenewMapper.insertOrderRenew(renew);
        } else {
            // 续费
            PlusOrder order = orderMapper.queryOrderById(param.getOrderId());
            //订单支付信息
            OrderRenew renew = new OrderRenew();
            renew.setCreateTime(System.currentTimeMillis());
            renew.setOrderId(order.getId());
            renew.setOrderNo(orderNo);
            renew.setRenewMoney(Long.valueOf(price));
            renew.setUpdateTime(order.getExpireTime() < System.currentTimeMillis() ? System.currentTimeMillis() + Consts.YEAR_MILLISECOND : order.getExpireTime() + Consts.YEAR_MILLISECOND);
            renew.setRenewType(Consts.RenewType.RENEW.getCode());
            orderRenewMapper.insertOrderRenew(renew);

        }
        return result;
    }

    /**
     * 微信支付统一下单
     * @param totalFee
     * @param ip
     * @param openId
     * @param orderNo
     * @return
     */
    private WxPayClientResult unifiedOrder (Integer totalFee,String ip,String openId,String orderNo) {
        WxPayUnifiedOrderResult result;
        WxPayUnifiedOrderRequest request = WxPayUnifiedOrderRequest.newBuilder()
                .body("旗驾服务业务办理")
//                .totalFee(1)
                .totalFee(totalFee)
                .spbillCreateIp(ip)
                .notifyUrl(wechatConfig.getNotifyUrl())
                .tradeType(WxPayConstants.TradeType.JSAPI)
                .openid(openId)
                .outTradeNo(orderNo)
                .build();
        WxPayClientResult clientResult = new WxPayClientResult();
        try {
            //配置参数注入
            wxPayService.setConfig(wechatPayConfig);
            result = wxPayService.unifiedOrder(request);
            //paySign = MD5(appId=wxd678efh567hg6787&nonceStr=5K8264ILTKCH16CQ2502SI8ZNMTM67VS&package=prepay_id=wx2017033010242291fcfe0db70013231072&signType=MD5&timeStamp=1490840662&key=qazwsxedcrfvtgbyhnujmikolp111111)
            clientResult.setAppId(result.getAppid());
            clientResult.setNonceStr(result.getNonceStr());
            clientResult.setPackageData("prepay_id="+result.getPrepayId());
            clientResult.setSignType("MD5");
            clientResult.setTimeStamp(System.currentTimeMillis()/100 + "");
            String sing = SignUtils.createSign(clientResult,clientResult.getSignType(),wechatPayConfig.getMchKey(),true) ;
            clientResult.setPaySign(sing);
        } catch (WxPayException e) {
            throw BaseException.build(AuthExceptionConstants.UNIFIED_ORDER_ERR);
        }
        return clientResult;
    }

    @Transactional
    @Override
    public String notifyCallback(String xmlData) {
        WxPayOrderNotifyResult result;
        try {
            result = wxPayService.parseOrderNotifyResult(xmlData);
        } catch (WxPayException e) {
            LOGGER.error("支付回调出错：{}",e.getCustomErrorMsg());
            return WxPayNotifyResponse.fail("FAIL");
        }
        String orderNo = result.getOutTradeNo();
        OrderRenew renew = orderRenewMapper.queryOrderRenewByOrderNo(orderNo);
        if (null == renew){
            LOGGER.error("支付回调出错：无效通知");
            return WxPayNotifyResponse.fail("FAIL");
        }
        if (renew.getRenewState() != Consts.RenewState.paying.ordinal()){
            //重复通知
            return WxPayNotifyResponse.success("OK");
        }
        PlusOrder order = orderMapper.queryOrderById(renew.getOrderId());
        Integer count = Integer.parseInt(adminSystemConfigMapper.getValue(order.getOrderType() == Consts.PlusOrderType.TYPE_1.getCode()? Consts.CAR_WASH_COUNT:Consts.CAR_SCRATCH_COUNT));
        if (renew.getRenewType() == Consts.RenewType.NEW.getCode()){
            //新开通
            order.setIsExpire(Consts.OrderIsExpire.normal.getCode());
            order.setUpdateTime(System.currentTimeMillis());
            orderMapper.updatePlusOrder(order);
            renew.setRenewState(Consts.RenewState.success.ordinal());
            orderRenewMapper.updateOrderRenew(renew);
            //新开通抽佣
            PlusUser user = userMapper.queryById(order.getUserId());
            if (null != user.getParentId() && user.getParentId() > 0) {
                PlusBrokerage brokerage = new PlusBrokerage();
                //有上级会员
                PlusUser parent = userMapper.queryById(user.getParentId());
                //划痕
                String value = adminSystemConfigMapper.getValue("brokerage");
                long brokerageValue = renew.getRenewMoney() * Integer.parseInt(value) / 100;
                if (order.getOrderType() == Consts.PlusOrderType.TYPE_1.getCode()){
                    //洗车
                    brokerageValue = 3500;
                }
                parent.setUpdateTime(System.currentTimeMillis());
                parent.setBrokerage((parent.getBrokerage()==null ? 0 : parent.getBrokerage()) + brokerageValue);
                userMapper.updatePlusUser(parent);
                brokerage.setBrokerage(brokerageValue);
                brokerage.setCreateTime(System.currentTimeMillis());
                brokerage.setUserId(user.getParentId());
                brokerage.setSecondId(user.getId());
                brokerage.setOrderType(order.getOrderType());
                brokerageMapper.insertPlusBrokerage(brokerage);
            }
            user.setVipType(user.getVipType() == null ? 1: (user.getVipType() + 1) > 2 ? 2 : (user.getVipType() + 1));
            user.setUpdateTime(System.currentTimeMillis());
            userMapper.updatePlusUser(user);
        } else {
            //续费
            order.setIsExpire(Consts.OrderIsExpire.normal.getCode());
            order.setUpdateTime(System.currentTimeMillis());
            order.setExpireTime(order.getExpireTime() < System.currentTimeMillis() ? System.currentTimeMillis() + Consts.YEAR_MILLISECOND : order.getExpireTime() + Consts.YEAR_MILLISECOND);
            order.setTotalCount(order.getExpireTime() < System.currentTimeMillis() ? count : order.getTotalCount() + count);
            //续费使用次数清零
//            order.setUseCount(0);
            order.setIsExpire(Consts.OrderIsExpire.normal.getCode());
            orderMapper.updatePlusOrder(order);
            renew.setRenewState(Consts.RenewState.success.ordinal());
            orderRenewMapper.updateOrderRenew(renew);
        }
        return WxPayNotifyResponse.success("OK");
    }

    @Override
    public Boolean orderRecord(Long id, OrderRecordParam param) {
        PlusOrder order;
        Integer state;
        if (null == param.getStoreId()){
            //划痕
            order = orderMapper.queryOrderByUserIdAndType(id,Consts.PlusOrderType.TYPE_2.getCode());
            if (null != param.getOrderId())
                order = orderMapper.queryOrderById(param.getOrderId());
            state = Consts.RecordState.UNAUDITED.ordinal();
        }else {
            //洗车
            order = orderMapper.queryOrderByUserIdAndType(id,Consts.PlusOrderType.TYPE_1.getCode());
            if (null != param.getOrderId())
                order = orderMapper.queryOrderById(param.getOrderId());
            state = Consts.RecordState.AUDIT_PASS.ordinal();
            order.setTotalCount(order.getTotalCount()-1);
            order.setUseCount(order.getUseCount() +1);
            order.setUpdateTime(System.currentTimeMillis());
            PlusStore store = storeMapper.queryStoreById(param.getStoreId());
            store.setOrderCount(store.getOrderCount() == null? 1 : store.getOrderCount() + 1);
            store.setUpdateTime(System.currentTimeMillis());
            storeMapper.updatePlusStore(store);
        }
        if (null == order){
            throw BaseException.build(AuthExceptionConstants.NO_OPENING_SERVICE);
        }
        if (System.currentTimeMillis() > order.getExpireTime()){
            // 设置订单过期
            order.setTotalCount(0);
            order.setIsExpire(Consts.OrderIsExpire.expire.getCode());
            orderMapper.updatePlusOrder(order);
            //会员降级
            PlusUser user = userMapper.queryById(id);
            List<PlusOrder> list = orderMapper.queryListOrderByUserId(id);
            if (null == list || list.isEmpty()){
                user.setVipType(0);
                user.setUpdateTime(System.currentTimeMillis());
            } else if (1 == list.size()) {
                if (list.get(0).getId().intValue() == order.getId()) {
                    user.setVipType(0);
                    user.setUpdateTime(System.currentTimeMillis());
                }
            } else {
                PlusOrder finalOrder = order;
                Map<Integer,String> map = new HashMap<>();
                list.stream().forEach(l -> {
                    if (l.getId().intValue() != finalOrder.getId()){
                        map.put(l.getOrderType(),"");
                    }
                });
                if (map.size() < 2){
                    user.setVipType(user.getVipType() == 0 ? 0 : user.getVipType() - 1);
                    user.setUpdateTime(System.currentTimeMillis());
                }
            }
            userMapper.updatePlusUser(user);
            throw BaseException.build(AuthExceptionConstants.SERVICE_EXPIRE);
        }
        int count = orderRecordMapper.countScratchOrderId(order.getId());
        if (count >= order.getTotalCount()){
            throw BaseException.build(AuthExceptionConstants.SERVICE_UNIQUE);
        }
        orderMapper.updatePlusOrder(order);
        if (null == param.getId() || param.getId() <= 0){
            OrderRecord record = new OrderRecord();
            record.setOrderId(order.getId());
            record.setStoreId(param.getStoreId());
            record.setImgUrl(param.getImgUrl());
            record.setOrderType(order.getOrderType());
            record.setState(state);
            record.setUserId(id);
            record.setCreateTime(System.currentTimeMillis());
            orderRecordMapper.insertOrderRecord(record);
        }else {
            OrderRecord record = orderRecordMapper.queryRecordById(param.getId());
            record.setImgUrl(param.getImgUrl());
            record.setState(Consts.RecordState.UNAUDITED.ordinal());
            record.setUpdateTime(System.currentTimeMillis());
            orderRecordMapper.updateOrderRecord(record);
        }
        return Boolean.TRUE;
    }
}
