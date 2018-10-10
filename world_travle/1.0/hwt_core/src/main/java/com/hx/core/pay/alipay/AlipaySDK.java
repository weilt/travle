package com.hx.core.pay.alipay;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.hx.core.exception.BaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 支付宝支付工具类
 */
public class AlipaySDK {

	/**
	 * 日志输出
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(AlipaySDK.class);

	/**
	 * 数据格式
	 */
	private static final String FORMAT = "json";

	/**
	 * 编码
	 */
	private static final String CHARSET = "utf-8";

	/**
	 * 支付宝拉取预支付 - APP预支付订单
	 * @param finalMoney     支付总金额，单位为元，精确到小数点后两位，取值范围[0.01,100000000]
	 * @param describe       描述
	 * @param attachTo	   	  自定义信息
	 * @param outTradeNoTo 商户网站唯一订单号
	 * @return
	 * @throws AlipayApiException
	 */
	public static String toPay(String finalMoney,String describe,String attachTo,String outTradeNoTo) {
		//实例化客户端
		AlipayClient alipayClient = new DefaultAlipayClient(AliPayConstants.url, AliPayConstants.app_id, AliPayConstants.privateKey, FORMAT, CHARSET, AliPayConstants.publicKey, "RSA2");
		//实例化具体API对应的request类,类名称和接口名称对应,当前调用接口名称：alipay.trade.app.pay
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		//SDK已经封装掉了公共参数，这里只需要传入业务参数。以下方法为sdk的model入参方式(model和biz_content同时存在的情况下取biz_content)。
		AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
		model.setBody(describe); //对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。 128
		model.setSubject(describe);//商品的标题/交易标题/订单标题/订单关键字等。 256
		model.setOutTradeNo(outTradeNoTo); //订单ID
		model.setTimeoutExpress("90m");
		model.setTotalAmount(finalMoney);
//		model.setProductCode("QUICK_MSECURITY_PAY"); //支付宝与商家约定的商品码
		model.setPassbackParams(attachTo);
		request.setBizModel(model);
		request.setNotifyUrl(AliPayConstants.notify_url);
		try {
	        //这里和普通的接口调用不同，使用的是sdkExecute
	        AlipayTradeAppPayResponse response = alipayClient.sdkExecute(request);
	        return response.getBody();//就是orderString 直接给客户端请求，无需再做处理。
	    } catch (AlipayApiException e) {
			LOGGER.error("支付宝预支付失败：{}",e.getErrMsg());
	        throw new BaseException(e);
		}
	} 
	
	/**
	 * 服务端验证异步通知信息参数示例
	 * @param request
	 * @param constants
	 * @return  如果验证正确，返回map有值;如果错误，返回null
	 * @throws AlipayApiException
	 */
	public static Map<String,String> callBack(HttpServletRequest request) {
		//获取支付宝POST过来反馈信息
		Map<String,String> params = new HashMap<>();
		Map requestParams = request.getParameterMap();
		for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
		    String name = (String) iter.next();
		    String[] values = (String[]) requestParams.get(name);
		    String valueStr = "";
		    for (int i = 0; i < values.length; i++) {
		        valueStr = (i == values.length - 1) ? valueStr + values[i]
		                    : valueStr + values[i] + ",";
		  	}
		    //乱码解决，这段代码在出现乱码时使用。
			//valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
			params.put(name, valueStr);
		}
		//切记alipaypublickey是支付宝的公钥，请去open.alipay.com对应应用下查看。
		//boolean AlipaySignature.rsaCheckV1(Map<String, String> params, String publicKey, String charset, String sign_type)
		try {
			boolean flag = AlipaySignature.rsaCheckV1(params, AliPayConstants.publicKey, "utf-8","RSA2");
			if(flag) {
				 params = null;
			}
		}catch (AlipayApiException e) {
			LOGGER.error("验证失败:{}",e);
			return params;
		}
		return params;
	}
  
}
