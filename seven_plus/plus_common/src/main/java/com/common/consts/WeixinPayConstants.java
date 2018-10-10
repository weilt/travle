package com.common.consts;

public interface WeixinPayConstants {

	
	 // --------- ** 微信支付填充信息 **-------------
	 /*String appid = "wxbdffc7702a29bb55";//在微信开发平台登记的
     String appsecret = "5f19b10cfd97ef4239d40eae4578d8ef";
     String partner = "1307350901";//商户号
     String partnerkey ="RpCrWUwatC2PSGcajJDRMb7Ubc9M3f59";//不是商户登录密码，是商户在微信平台设置的32位长度的api秘钥，
     String notify_url="http://7secai.cn/api/weixin/what_payCallBack";//异步通知地址
     */
     String appid = "wx971ceabd0fe9e861";//在微信开发平台登记的
     String appsecret = "4ea64bb20edfb1356462e315fdd54835";
     String partner = "1494591662";//商户号
     String partnerkey ="TepOjZToSCsTonlgL3jUnzXobRWdpHvd";//不是商户登录密码，是商户在微信平台设置的32位长度的api秘钥，
     String notify_url="http://www.cqtsp.cn/weixin/payBack";//异步通知地址
     //http://liugang.tunnel.qydev.com/qscMobilier_userSide/weixin/what_payCallBack
     // --------- ** 微信转账填充信息 **-------------
     String AppID = "wx4113125cf12116f8"; // 开发者ID
     String mchId = "1494591662";// 商户号
     String key = "";// 支付key
 	 String certPath = "src/main/resources/apiclient_cert.p12";//安全证书地址
 	 
     
     
} 
