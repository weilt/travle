package com.hx.core.wyim.sms;

import java.util.HashMap;
import java.util.Map;

import com.hx.core.utils.GsonUtil;
import com.hx.core.wyim.utils.HttpClientUtil;



/**
 * 网易短信业务接口
 * @author Administrator
 *
 */
public class SmsConmmon {
	public static final String APP_KEY = "74f268c4ce0ec424237840e32c3223f0";
	public static final String APP_SECRET = "c1abff9ae9ff";

	// 发送短信/语音短信验证码
	public static final String SMS_sendcode = "https://api.netease.im/sms/sendcode.action";

	// 校验验证码
	public static final String SMS_verifycode = "https://api.netease.im/sms/verifycode.action";

	// 发送通知类和运营类短信
	public static final String SMS_sendtemplate = "https://api.netease.im/sms/sendtemplate.action";

	// 查询通知类和运营类短信发送状态
	public static final String SMS_querystatus = "https://api.netease.im/sms/querystatus.action";

	/**
	 * 发送短信/语音短信验证码
	 * 			描述---向指定的手机号码发送短信验证码。
	 * @param mobile 目标手机号     
	 * @param deviceId 目标设备号，可选参数
	 * @param templateid 模板编号(如不指定则使用配置的默认模版)
	 * @param codeLen 验证码长度，范围4～10，默认为4
	 * @param authCode 客户自定义验证码，长度为4～10个数字 如果设置了该参数，则codeLen参数无效
	 * @param needUp 是否需要支持短信上行。true:需要，false:不需要 说明：如果开通了短信上行抄送功能，该参数需要设置为true，其它情况设置无效
	 * @return
	 * @throws Exception
	 */
	public static String sendcode(String mobile, String deviceId, int templateid, int codeLen, String authCode,
			Boolean needUp) throws Exception {
		Map<String, String> map = new HashMap<>();
		map.put("mobile", mobile);
		map.put("deviceId", deviceId);
		map.put("templateid", templateid+"");
		map.put("codeLen", codeLen+"");
		map.put("authCode", authCode+"");
		map.put("needUp", needUp ? "true" : "false");
		return HttpClientUtil.postHttp(SMS_sendcode, map);

	}
	
	/**
	 * 校验验证码
	 * 		描述---校验指定手机号的验证码是否合法。
	 * @param mobile
	 * @param code
	 * @return
	 * @throws Exception
	 */
	public static String verifycode(String mobile,String code) throws Exception{
		Map<String, String> map = new HashMap<>();
		map.put("mobile", mobile);
		map.put("code", code);
		return HttpClientUtil.postHttp(SMS_verifycode, map);
	}
	
	
	/**
	 * 发送通知类和运营类短信
	 * 		描述---向手机号发送内容格式预定义的短信，整个短信的内容由模板和变量组成。
	 * @param templateid 模板编号(由客户顾问配置之后告知开发者)
	 * @param mobiles 接收者号码列表，JSONArray格式,如["186xxxxxxxx","186xxxxxxxx"]，限制接收者号码个数最多为100个
	 * @param params 短信参数列表，用于依次填充模板，JSONArray格式，每个变量长度不能超过30字，如["xxx","yyy"];对于不包含变量的模板，不填此参数表示模板即短信全文内容
	 * @param needUp 是否需要支持短信上行。true:需要，false:不需要说明：如果开通了短信上行抄送功能，该参数需要设置为true，其它情况设置无效
	 * @return
	 * @throws Exception
	 */
	public static String sendtemplate(int templateid, String[] mobiles, String[] params, boolean needUp) throws Exception{
		Map<String, String> map = new HashMap<>();
		map.put("templateid", templateid+"");
		map.put("mobiles", GsonUtil.toJson(mobiles));
		map.put("params", GsonUtil.toJson(params));
		map.put("needUp", needUp ? "true" : "false");
		return HttpClientUtil.postHttp(SMS_verifycode, map);
	}
	
	/**
	 * 查询通知类和运营类短信发送状态
	 * 		描述---根据短信的sendid(sendtemplate.action接口中的返回值)，查询短信发送结果。
	 * @param sendid
	 * @return
	 * @throws Exception
	 */
	public static String querystatus(long sendid) throws Exception{
		Map<String, String> map = new HashMap<>();
		map.put("sendid", sendid+"");
		return HttpClientUtil.postHttp(SMS_verifycode, map);
	}
}
