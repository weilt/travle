package com.hx.api.controller.pay;

import java.math.BigDecimal;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alipay.api.AlipayApiException;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.core.pay.alipay.AliPayReturn;
import com.hx.core.pay.alipay.AlipaySDK;
import com.hx.order.service.hx.OrderService;
import com.hx.order.service.hx.PayService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 支付
 * @author Administrator
 *
 */
@Api(value = "API - PayController", description = "支付")
@RestController
public class PayController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PayService payService;
	
	/**
	 * 零钱支付
	 */
	@ApiOperation(value = "零钱支付" , notes = "零钱支付", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "order_num", value = "订单编号", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "payment_password", value = "支付密码", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "smsVerify", value = "超过5000", dataType = "string",paramType = "query"),
    })
	@ValidateParam(field={"token","order_num","payment_password"}, checkedType = CheckedType.TOKEN)
	@PostMapping("pay/wallet")
	public ResultEntity wallet_pay(String token ,String order_num, String payment_password,String smsVerify) throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		payService.wallet_pay(loginVo.getUser_id(),order_num,payment_password,smsVerify);
		return new ResultEntity();
	}
	
	/**
	 * 支付宝支付-预付单
	 */
	@ApiOperation(value = "支付宝支付-预付单" , notes = "支付宝支付-预付单", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "order_num", value = "订单编号", dataType = "string",required = true,paramType = "query"),
    })
	@ValidateParam(field={"token","order_num"}, checkedType = CheckedType.TOKEN)
	@PostMapping("pay/zhifubao")
	public ResultEntity zhifubao_pay(String token ,String order_num) throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		Object object = payService.zhifubao_pay( token,loginVo.getUser_id(),order_num);
		return new ResultEntity(object);
	}
	
	
	
	
	/**
	 * 第三方支付成功回调
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "第三方支付成功回调" , notes = "第三方支付成功回调", response = ResultEntity.class, hidden = true)
	@RequestMapping("/aliback")
	public String payZhiFuBao_callBack(HttpServletRequest request) {
		try {
			Map<String, String> map = new AlipaySDK().callBack(request);
			
			System.out.println("支付宝回调信息:" );
			System.out.println(map);
			
			/**
			 * {gmt_create=2018-04-19 16:27:24, charset=utf-8, seller_email=tdtgcm7068@sandbox.com, subject=洗车支付, body=洗车支付,
			 *  buyer_id=2088102175857910, invoice_amount=2.00, notify_id=d36281cdcae0bb8283d8a589d8e3282n0u,
			 *   fund_bill_list=[{"amount":"2.00","fundChannel":"ALIPAYACCOUNT"}], notify_type=trade_status_sync,
			 *    trade_status=TRADE_SUCCESS, receipt_amount=2.00, app_id=2016091600521349, buyer_pay_amount=2.00,
			 *     seller_id=2088102175824611, gmt_payment=2018-04-19 16:27:24, notify_time=2018-04-19 16:27:25,
			 *      passback_params={"token":"HXJL7f3c34e3f9622c61fade4a710a3f7326_5FT3bA","payType":2},
			 *       version=1.0, out_trade_no=1bdf4665fc644e028ce96361dc570422, total_amount=2.00,
			 *        trade_no=2018041921001004910200383522, auth_app_id=2016091600521349,
			 *         buyer_logon_id=cmm***@sandbox.com, point_amount=0.00}
			 */
			if(map != null) {
				//验证成功 返回信息
				String out_trade_no = map.get("out_trade_no").toString(); //订单号
				String passback_params = map.get("passback_params").toString(); //自定义封装数据信息
				
				payService.pay_callBack(out_trade_no, passback_params,2);
				
				return "success";
				//return AliPayReturn.TRADE_SUCCESS.name();
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return AliPayReturn.TRADE_CLOSED.name();
	}
	
	public static void main(String[] args) {
		String a = AliPayReturn.TRADE_SUCCESS.name();
		System.out.println(a);
	}
}

