package com.xx.springBootUtil.validate;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.xx.springBootUtil.excption.ValidateException;
import com.xx.springBootUtil.util.ObjectHelper;


@Component
public class ValidateService {
	
	public void validateService(HttpServletRequest request, ValidateWriteIn v) {
		// 需要检查的参数名称
		final String[] parameter = v.parameter();
		// 异常消息
		final String[] msg = v.msg();
		// 验证类型
		final ValidateType[] type = v.type();   
		for (int i = 0; i < parameter.length; i++) {
			String o = request.getParameter(parameter[i]);
			//判断类型
			ValidateType validateType = type.length <= i ? ValidateType.NULL : type[i];
			ValidateTypeSwitch(validateType, parameter, msg, i, o);
		}
	}
	
	public void validateService(HttpServletRequest request, ValidateWriteInPost v) {
		// 需要检查的参数名称
		final String[] parameter = v.parameter();
		// 异常消息
		final String[] msg = v.msg();
		// 验证类型
		final ValidateType[] type = v.type();   
		for (int i = 0; i < parameter.length; i++) {
			String o = request.getParameter(parameter[i]);
			//判断类型
			ValidateType validateType = type.length <= i ? ValidateType.NULL : type[i];
			ValidateTypeSwitch(validateType, parameter, msg, i, o);
		}
	}
	
	
	
	
	public void ValidateTypeSwitch(ValidateType validateType,String[] parameter,String[] msg,int i,String o){
		switch (validateType) {
			case NULL: //判断为空
				if(ObjectHelper.isEmpty(o)) {
					if(msg.length < i+1) {
						throw new ValidateException("参数不能为空! param is " + parameter[i]);
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			case EMAIL: //验证邮箱
				if(ObjectHelper.isNotEmpty(o) && !ObjectHelper.checkEmail(o)) {
					if(msg.length < i+1) {
						throw new ValidateException("邮箱格式错误! param is " + parameter[i]);
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			case PHONE:
				if(ObjectHelper.isNotEmpty(o) && !ObjectHelper.isPhoneLegal(o)) {
					if(msg.length < i+1) {
						throw new ValidateException("号码格式错误! param is " + parameter[i]);
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			case IdCard:
				if(ObjectHelper.isNotEmpty(o) && !ObjectHelper.checkIdCard(o)) {
					if(msg.length < i+1) {
						throw new ValidateException("身份证格式错误! param is " + parameter[i]);
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			case Digit:
				if(ObjectHelper.isNotEmpty(o) && !ObjectHelper.checkDigit(o)) {
					if(msg.length < i+1) {
						throw new ValidateException("数字类型错误! param is " + parameter[i]);
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			case Decimals:
				if(ObjectHelper.isNotEmpty(o) && !ObjectHelper.checkDecimals(o)) {
					if(msg.length < i+1) {
						throw new ValidateException("小数类型错误! param is " + parameter[i]);
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			default:
		}
	}
}
