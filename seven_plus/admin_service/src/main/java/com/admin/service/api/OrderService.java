package com.admin.service.api;

import com.common.wechat.wxpay.bean.result.WxPayClientResult;
import com.common.wechat.wxpay.bean.result.WxPayUnifiedOrderResult;
import com.domain.plus.param.CreatePriceParam;
import com.domain.plus.param.OrderRecordParam;
import com.domain.plus.param.PaymentParam;
import com.domain.plus.vo.CreatePriceVo;
import com.domain.plus.vo.OrderRecordApiVo;
import com.domain.plus.vo.OrderRenewVo;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 15:29
 * @Description:
 */
public interface OrderService {

    /**
     * 获取订单价格
     * @param param
     * @return
     */
    CreatePriceVo createPrice(CreatePriceParam param);

    /**
     * 订单支付信息
     * @param id
     * @param orderType
     * @return
     */
    List<OrderRenewVo> getOrderPayment(Long id, Integer orderType);


    /**
     * 订单使用记录
     * @param id
     * @param orderType
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<OrderRecordApiVo> getOrderRecord(Long id, Integer orderType, Integer pageNo, Integer pageSize);

    /**
     * 统一下单（新订单）
     * @param param
     * @param ip
     * @return
     */
    WxPayClientResult unifiedOrder(PaymentParam param, String ip, Long userId, String openId);

    /**
     * 使用服务
     * @param id
     * @param param
     * @return
     */
    Boolean orderRecord(Long id, OrderRecordParam param);

    /**
     * 支付回调
     * @param xmlData
     * @return
     */
    String notifyCallback(String xmlData);
}
