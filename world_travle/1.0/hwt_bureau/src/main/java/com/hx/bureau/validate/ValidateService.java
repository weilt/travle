package com.hx.bureau.validate;


import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import com.hx.core.redis.RedisCache;
import com.hx.core.utils.ObjectHelper;

import javax.servlet.http.HttpServletRequest;

/**
 * 执行验证的接口
 * 处理验证请求参数注解
 * @author WJ
 *
 */
@Component
public class ValidateService {

	public boolean validateService(HttpServletRequest request, ValidateParam v) throws ValidateException {
		//System.out.println(request.getRequestURI());
		// 需要检查的参数名称
		String[] fields = v.field();
		// 异常消息
		String[] msg = v.message();
		// 验证类型
		ValidateParam.CheckedType[] checkedType = v.checkedType();
		for (int i = 0; i < fields.length; i++) {
			String o = request.getParameter(fields[i]);
			
			ValidateParam.CheckedType tempCheckedType = checkedType.length <= i ? ValidateParam.CheckedType.NULL : checkedType[i];
			
			switch (tempCheckedType) {
			case NULL:
				if(StringUtils.isBlank(o)) {
					if(msg.length < i+1) {
						System.out.println(fields[i]);
						throw new ValidateException("提交了不能为空的值,请确认!");
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			case EMAIL:
				if(o == null || !o.getClass().equals(String.class) || !ValidateUtils.isEmail(o.toString())) {
					if(msg.length < i+1) {
						throw new ValidateException("邮箱地址输出格式错误!");
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			case MOBIL:
				if(o == null || !o.getClass().equals(String.class) || !ValidateUtils.isMobileNO(o.toString())) {
					if(msg.length < i+1) {
						throw new ValidateException("手机号码输入错误！");
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			case PWD:
				if(o == null || !o.getClass().equals(String.class) || !ValidateUtils.isPassword(o.toString())) {
					if(msg.length < i+1) {
						throw new ValidateException("密码输入错误，请输入3-12位的数字和字母！");
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				break;
			case TOKEN:
				if(o == null || "".equals(o) || "null".equals(o)) {
					if(msg.length < i+1) {
						throw new ValidateException("请求参数异常! param is " + fields[i]);
					} else {
						throw new ValidateException(msg[i]);
					}
				}
				if(ObjectHelper.isEmpty(RedisCache.get(o))) {
					throw new RuntimeException("SESSION_OUT");
				}
				break;
			default:
				return true;
			}
		}
		return true;
	}
}
