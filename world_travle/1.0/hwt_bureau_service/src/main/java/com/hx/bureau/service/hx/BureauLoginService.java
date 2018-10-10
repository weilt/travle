package com.hx.bureau.service.hx;

import javax.servlet.http.HttpServletRequest;

import com.hwt.domain.entity.bureau.HwTravelBureau;

public interface BureauLoginService {
	
	/**
	 * 入驻
	 * @param hwTravelBureau
	 */
	void enter(HwTravelBureau hwTravelBureau);

	/**
	 * 登录 
	 * @param legal_person_phome 企业法人手机号
	 * @param password 密码
	 * @return
	 */
	void login(String legal_person_phome, String password, HttpServletRequest request);

}
