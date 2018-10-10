package com.hx.user.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import com.hwt.domain.entity.user.HxUser;
import com.hwt.domain.entity.user.SmsLog;
import com.hx.core.wyim.entity.emu.SMSType;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.core.exception.BaseException;
import com.hx.core.redis.RedisCache;
import com.hx.core.utils.GsonUtil;
import com.hx.core.utils.RandomUtils;
import com.hx.core.wyim.entity.SMSUser;
import com.hx.core.wyim.service.SmsService;
import com.hx.user.logs.entity.parent.SMSLog;
import com.hx.user.logs.service.LogseeService;
import com.hx.user.service.SmsMessageService;


@Service
public class SmsMessageServiceImpl implements SmsMessageService {

	private Logger logger = LoggerFactory.getLogger(SmsMessageService.class);

	@Autowired
	private SmsService smsService;
	
	@Autowired
	private LogseeService logseeService;
	
	@Autowired
	private HxUserMapper hxUserMapper;

	@Override
	public String sendRegisterIdentifyCode(String phone) throws Exception {
		return sendIdentifyCode(phone,SMSType.register,true);
	}

	@Override
	public String sendFindPwdIdentifyCode(String phone) throws Exception {
		return sendIdentifyCode(phone,SMSType.findPass,true);
	}

	@Override
	public String sendLoginIdentifyCode(String phone) throws Exception {
		
		//
		HxUser hxUser = hxUserMapper.hxUserAccountPhone(phone);
		if (hxUser==null){
			throw new BaseException("该手机号未注册，请先注册");
		}
		return sendIdentifyCode(phone,SMSType.login,true);
	}
	
	
	
	/**
	 * 发送短息验证码
	 * @param phone 手机号
	 * @param smsType 验证码类型
	 * @param needUp		是否需要支持短信上行。true:需要，false:不需要 说明：如果开通了短信上行抄送功能，该参数需要设置为true，其它情况设置无效
	 * @return
	 */
	private String sendIdentifyCode(final String phone, final SMSType smsType,Boolean needUp) throws Exception {
		
		if (needUp ==null){
			needUp = true;
		}
		
		if(smsType.smsTypePrefix.equals("HXJLRegister_")){
			HxUser hxUser = hxUserMapper.hxUserAccountPhone(phone.trim());
			if(hxUser!=null){
				throw new BaseException("该手机已被注册");
			}
			
		}
		String redisKey = smsType.smsTypePrefix + phone.trim();
		//验证是否过期
		String code = RedisCache.get(redisKey);
		if (StringUtils.isBlank(code)){
			
			code = RandomUtils.randomNumber(6);
			//添加缓存的记录信息
			RedisCache.set(redisKey, code, smsType.cacheTimeout);
		}
		String message = smsType.smsTypeLogMsg;
		message = message.replace("{code}", code);
		
		
		//发送短信
		String remarks = sms(phone,code,smsType.smsTypeTemplateNumber,needUp);
		
	
		//添加短信日志记录到mongodb
		try {
			addLog(phone,message,smsType.smsTypePrefix,remarks);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if ("频率太快，请稍后".equals(remarks)){
			 throw new RuntimeException("该手机号今日短信条数上限，请明日在试");
		}
		return code;
	}

	/**
	 * 添加短信日志记录到mongodb
	 * @param phone 手机号
	 * @param content 内容
	 * @param type  类型
	 */
	@Async
	private void addLog(String phone, String content, String type, String remarks) {
		
		SMSLog smsLog = new SMSLog();
		smsLog.setContent(content);
		smsLog.setCreateTime(new Date());
		smsLog.setPhone(phone);
		smsLog.setRemarks(remarks==null?"不知名错误":remarks);
		smsLog.setTimeStamp(new Date().getTime());
		smsLog.setType(type);
		
		logseeService.add(smsLog);
	}	

	/**
	 * 短信发送验证码
	 * @param phone		电话
	 * @param code		验证码
	 * @param smsTemplateNumber		验证码模板编号
	 * @param needUp		是否需要支持短信上行。true:需要，false:不需要说明：如果开通了短信上行抄送功能，该参数需要设置为true，其它情况设置无效
	 * @return
	 * @throws Exception
	 */
	private String sms(String phone, String code, int smsTemplateNumber,boolean needUp) throws Exception {
		//这里发送消息 TODO
		SMSUser smsUser = new SMSUser(phone, null, smsTemplateNumber, 6, code, true);
		String sendcode = smsService.sendcode(smsUser);
		logger.info("为用户 :: {} 发送的短信验证码为 ::{} ",phone,code);
		Map map = GsonUtil.fromJson(sendcode, Map.class);
		System.out.println(map);
		String returnCode = map.get("code")+"";
		if (returnCode.equals("200.0")||returnCode.equals("200")){
//			//添加短信日志记录
//			SmsLog log = new SmsLog(UUIDHelper.createUUId(), phone, message, type, new Date());
//			smsLogDao.insertSelective(log);
		/*	return code;
		}else if (returnCode.equals("315")){
			throw new RuntimeException("IP被限制，请联系客服");
		}else if (returnCode.equals("403")){
			throw new RuntimeException("非法操作或没有权限，请联系客服");
		}else if(returnCode.equals("414")){
			throw new RuntimeException("参数错误，请联系客服");
		}else if(returnCode.equals("416")){
			throw new RuntimeException("频率太快，请稍后");
		}else if(returnCode.equals("500")){
			throw new RuntimeException("服务器内部错误，请联系客服");
		}*/
			return "发送成功";
		}else if (returnCode.equals("315")||returnCode.equals("315.0")){
			return  "IP被限制，请联系客服";
		}else if (returnCode.equals("403")||returnCode.equals("403.0")){
			return "非法操作或没有权限，请联系客服";
		}else if(returnCode.equals("414")||returnCode.equals("414.0")){
			return "参数错误，请联系客服";
		}else if(returnCode.equals("416")||returnCode.equals("416.0")){
			return "频率太快，请稍后";
		}else if(returnCode.equals("500")||returnCode.equals("500.0")){
			return "服务器内部错误，请联系客服";
		}
		return null;
	}

	@Override
	public String sendInsertOrderIdentifyCode(String phone) throws Exception {
		return sendIdentifyCode(phone,SMSType.order,false);
	}

	@Override
	public String sendPaymentPassWordIdentifyCode(String phone) throws Exception {
		return sendIdentifyCode(phone,SMSType.paymentPassWord,true);
	}

	@Override
	public String sendResetPaymentPassIdentifyCode(String phone) throws Exception {
		return sendIdentifyCode(phone, SMSType.resetPaymentPass,true);
	}

	@Override
	public String sendWalletPayIdentifyCode(String phone) throws Exception {
		return sendIdentifyCode(phone, SMSType.walletPay,false);
	}

	@Override
	public String sendPutForwardIdentifyCode(String phone) throws Exception {
		return sendIdentifyCode(phone, SMSType.putForward,true);
	}


}
