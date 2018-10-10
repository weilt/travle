package com.hx.user.service;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import com.hwt.domain.entity.user.Vo.LoginVo;



/**
 * 登录注册业务层
 * @author Administrator
 *
 */
public interface LoginService {

	/**
	 * 验证token
	 * @param token
	 * @return
	 */
	LoginVo validateToken(String token);
	
	/**
     * 用户手机号+验证码登录
     * @param phone 手机号
     * @param smsVerify 手机短信验证码
     * @return
     */
	Object imLogin(String phone, String smsVerify, HttpServletRequest request) throws ParseException;

	/**
	 * 用户手机号或者账号+密码
	 * @param acc
	 * @param pwd
	 * @param request
	 * @return
	 */
	Object imLogin_accPass(String acc, String pwd, HttpServletRequest request) throws ParseException;

	/**
	 * 注册
	 * @param phone
	 * @param userNickname
	 * @param pass
	 * @param referrerAccountId 
	 * @return
	 */
	Object imRegister(String phone, String userNickname, String pass, Long referrerAccountId);

}
