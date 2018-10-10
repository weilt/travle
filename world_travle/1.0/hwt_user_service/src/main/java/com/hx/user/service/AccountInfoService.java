package com.hx.user.service;

import com.hwt.domain.entity.user.HxUserConfig;
import com.hwt.domain.entity.user.HxUserInfo;
import com.hwt.domain.entity.user.Vo.HxUserConfigVo;

public interface AccountInfoService {

	/**
	 * 获取当前用户信息
	 * @param token
	 * @return
	 */
	Object accountInfo(String token);
	
	/**
	 * 根据Id获取配置信息
	 * @return
	 */
	HxUserConfigVo queryById(String token);

	/**
	 *  修改用户信息
	 * @param token
	 * @param hxUserInfo
	 */
	void editAccount(String token, HxUserInfo hxUserInfo) throws Exception;

	/**
	 * 修改用户账号
	 * @param token
	 * @param account
	 */
	void updateAccount(String token, String account);
	
	/**
	 * 修改隐私权限
	 * @param token
	 * @param hxUserConfig
	 */
	void updateAuthority(String token, HxUserConfig hxUserConfig);
	
	/**
	 * 修改密码
	 * @param token 登录标识
	 * @param newpassword 新密码
	 * @param oldpassword 旧密码
	 * @param type 标识 1-修改密码 2-验证码登录修改密码
	 */
	void updatePassWord(String token,String newpassword,String oldpassword,int type);
	
	/**
	 * 修改密码
	 * @param newpassword 新密码
	 * @param phone 电话号码
	 * @param code 验证码
	 */
	void updatePassWordByCode(String newpassword,String phone,String code);
	
	/**
	 * 忘记密码判断验证码
	 * @param token
	 * @param smsVerify
	 * @param phone
	 */
	void updatePassWordToCode(String token,String smsVerify,String phone);
}
