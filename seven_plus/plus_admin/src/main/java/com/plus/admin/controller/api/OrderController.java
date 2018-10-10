package com.plus.admin.controller.api;

import com.admin.service.api.OrderService;
import com.common.consts.UserContext;
import com.common.wechat.wxpay.bean.WxPayApiData;
import com.common.wechat.wxpay.bean.coupon.*;
import com.common.wechat.wxpay.bean.notify.WxPayOrderNotifyResult;
import com.common.wechat.wxpay.bean.notify.WxPayRefundNotifyResult;
import com.common.wechat.wxpay.bean.notify.WxScanPayNotifyResult;
import com.common.wechat.wxpay.bean.request.*;
import com.common.wechat.wxpay.bean.result.*;
import com.common.wechat.wxpay.config.WxPayConfig;
import com.common.wechat.wxpay.exception.WxPayException;
import com.common.wechat.wxpay.service.EntPayService;
import com.common.wechat.wxpay.service.WxPayService;
import com.common.wechat.wxpay.service.impl.WxPayServiceImpl;
import com.domain.plus.param.CreatePriceParam;
import com.domain.plus.param.OrderParam;
import com.domain.plus.param.OrderRecordParam;
import com.domain.plus.param.PaymentParam;
import com.domain.plus.vo.CreatePriceVo;
import com.domain.plus.vo.OrderRecordApiVo;
import com.domain.plus.vo.OrderRenewVo;
import com.plus.admin.controller.api.result.ResultCollection;
import com.plus.admin.controller.api.result.ResultEntity;
import com.plus.admin.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/15 17:26
 * @Description:
 */
@Api(value = "API - OrderController", description = "订单相关API")
@RestController
public class OrderController extends BaseApiController {

    @Autowired
    private OrderService orderService;


    @ApiOperation(value = "计算订单价格" , notes = "createPrice", response = CreatePriceVo.class)
    @PostMapping("/api/createPrice")
    public ResultEntity<CreatePriceVo> createPrice(@RequestBody CreatePriceParam param){
        CreatePriceVo priceVo = orderService.createPrice(param);
        return ResultEntity.build(priceVo);
    }


    @ApiOperation(value = "订单支付列表" , notes = "getOrderPayment", response = OrderRenewVo.class)
    @PostMapping("/api/getOrderPayment")
    public ResultCollection getOrderPayment(@RequestBody OrderParam param) {
        UserContext context = getUserContext();
        List<OrderRenewVo> list = orderService.getOrderPayment(context.getId(),param.getOrderType());
        return ResultCollection.build(list);
    }

    @ApiOperation(value = "订单使用列表" , notes = "getOrderRecord", response = OrderRecordApiVo.class)
    @PostMapping("/api/getOrderRecord")
    public ResultCollection getOrderRecord(@RequestBody OrderParam param) {
        UserContext context = getUserContext();
        List<OrderRecordApiVo> list = orderService.getOrderRecord(context.getId(),param.getOrderType(),param.getPageNo(),param.getPageSize());
        return ResultCollection.build(list);
    }

    @ApiOperation(value = "统一下单（掉起支付前一步）" , notes = "unifiedOrder", response = WxPayUnifiedOrderResult.class)
    @PostMapping("/api/unifiedOrder")
    public ResultEntity unifiedOrder (@RequestBody PaymentParam param) {
        UserContext context = getUserContext();
        String ip = getClientIp();
        WxPayClientResult result = orderService.unifiedOrder(param,ip,context.getId(),context.getOpenId());
        return ResultEntity.build(result);
    }


    @ApiOperation(value = "去洗车(划痕申请)" , notes = "orderRecord", response = ResultEntity.class)
    @PostMapping("/api/orderRecord")
    public ResultEntity orderRecord (@RequestBody OrderRecordParam param) {
        UserContext context = getUserContext();
        orderService.orderRecord(context.getId(),param);
        return ResultEntity.build();
    }

    /**
     * 支付回调
     * @param xmlData
     * @return
     */
    @RequestMapping("/notifyCallback")
    public String notifyCallback(@RequestBody String xmlData) {
        return orderService.notifyCallback(xmlData);
    }

}
