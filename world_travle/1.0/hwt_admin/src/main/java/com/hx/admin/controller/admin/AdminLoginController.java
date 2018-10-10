package com.hx.admin.controller.admin;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hx.admin.base.ResultEntity;
import com.hx.admin.service.admin.AdminUserService;
import com.hx.admin.service.cache.AdminUserCacheTools;

/**
 * 登录登出
 * @author Administrator
 *
 */
@RestController
public class AdminLoginController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	/**
	 * 登录 
	 * @param user_account 用户名
	 * @param password 密码
	 * @return
	 */
	@RequestMapping("back/login")
	public ResultEntity login(String user_account,String password,HttpServletRequest request){
		adminUserService.login(user_account,password,request);
		
		return new ResultEntity();
		
	}
	
	@RequestMapping("back")
	public ModelAndView back(HttpServletRequest request,HttpServletResponse response) throws IOException{
		AdminUserCacheTools.removeSession(request);
		response.sendRedirect("/login.html");
		return null;
	}
}
