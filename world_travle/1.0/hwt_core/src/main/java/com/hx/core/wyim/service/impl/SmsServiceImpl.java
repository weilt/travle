package com.hx.core.wyim.service.impl;

import org.springframework.stereotype.Service;

import com.hx.core.wyim.entity.SMSUser;
import com.hx.core.wyim.service.SmsService;
import com.hx.core.wyim.sms.SmsConmmon;


@Service
public class SmsServiceImpl implements SmsService {

	@Override
	public String sendcode(SMSUser smsUser) throws Exception {

		return SmsConmmon.sendcode(smsUser.getMobile(), smsUser.getDeviceId(), smsUser.getTemplateid(),
				smsUser.getCodeLen(), smsUser.getAuthCode(), smsUser.getNeedUp());
	}

	@Override
	public String verifycode(SMSUser smsUser) throws Exception {
		
		return SmsConmmon.verifycode(smsUser.getMobile(), smsUser.getCode());
	}

	@Override
	public String sendtemplate(SMSUser smsUser) throws Exception {
		
		return SmsConmmon.sendtemplate(smsUser.getTemplateid(), smsUser.getMobiles(), smsUser.getParams(), smsUser.getNeedUp());
	}

	@Override
	public String querystatus(SMSUser smsUser) throws Exception {

		return SmsConmmon.querystatus(smsUser.getSendid());
	}

}
