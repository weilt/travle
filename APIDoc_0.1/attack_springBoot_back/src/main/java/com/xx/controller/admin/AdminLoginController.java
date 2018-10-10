package com.xx.controller.admin;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.admin.dao.AdminAccesslogDao;
import com.xx.admin.dao.AdminModuleDao;
import com.xx.admin.dao.AdminRoleDao;
import com.xx.admin.dao.AdminSystemConfigDao;
import com.xx.admin.dao.AdminUserDao;
import com.xx.admin.entity.AdminModule;
import com.xx.admin.entity.AdminRole;
import com.xx.admin.entity.AdminUser;
import com.xx.admin.service.AdminUserService;
import com.xx.admin.util.ApacheSecurityUtil;
import com.xx.controller.util.BaseController;
import com.xx.springBootUtil.result.JsonResult;
import com.xx.springBootUtil.util.MessageUtil;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.util.UUIDHelper;
import com.xx.springBootUtil.validate.Clear;
import com.xx.springBootUtil.validate.ValidateWriteIn;
import com.xx.util.page.Global;
import com.xx.util.result.JsonUtil;

/**
 * 后台管理进入后台管理
 * @author LiuGang
 */
@Clear
@RestController
public class AdminLoginController extends BaseController{

	@Resource
	private AdminUserDao adminUserDao;
	@Resource
	private AdminRoleDao adminRoleDao;
	@Resource
	private AdminModuleDao adminModuleDao;
	@Resource
	private AdminSystemConfigDao adminSystemconfigDao;
	@Resource
	private AdminAccesslogDao adminAccesslogDao;
	
	@Autowired
	private AdminUserService adminUserService;
	/**
	 * 跳转到登录页面 - 切清除所有session的值和登录的cookie值
	 */
	@RequestMapping("back")
	public ModelAndView back(){
		//执行退出
		getSession().invalidate();
		Map<String, Object> map = new HashMap<>();
		//获取cooklie的值
		return new ModelAndView("admin/login/login").addAllObjects(map);
	}
	
	
	/**
	 * 用户登录
	 * @param userName
	 * @param passWord
	 * @param checked  - 检查cook
	 * @return
	 */
	@RequestMapping("back/login")
	@ValidateWriteIn(parameter={"userName","passWord"},msg={"户名和密码不能为空","户名和密码不能为空!"})
	public JsonResult adminLogin(String userName,String passWord,String checked){
		JsonResult jsonResult = null;
		AdminUser adminUser = adminUserDao.userLogin(userName, MessageUtil.encodeMD5(passWord));
		if(ObjectHelper.isEmpty(adminUser)){
			jsonResult = JsonUtil.jsonError("账号或者密码错误");
		}else if(adminUser.getIsDelete() == 2){
			jsonResult = JsonUtil.jsonError("账号已被禁用，有问题请联系管理员");
		}else if(adminUser.getRoleId() <= 0){
			jsonResult = JsonUtil.jsonError("该用户还没分配角色,有问题请联系管理员");
		}else{
			//查询角色信息
			AdminRole role = adminRoleDao.findById(adminUser.getRoleId());
			if(ObjectHelper.isEmpty(role)){
				return JsonUtil.jsonError("角色信息错误,请联系管理员");
			}
			//查询权限
			List<AdminModule> listModule = null;
			//超级管理员权限
			if(adminUser.getRoleId() == Global.IS_SUPER_ADMIN){
				listModule = adminModuleDao.queryList();
			}else{
				listModule = adminModuleDao.findByRoleId(adminUser.getRoleId());
			}
			//获取 值
			String value = adminSystemconfigDao.getValue("systemName");
			
			/*setCookie("topRoleName", role.getName(), Integer.MAX_VALUE);
			setCookie(Global.setGetCookieUserName, userName, Integer.MAX_VALUE);
			if(ObjectHelper.isNotEmpty(checked)){
				setCookie(Global.setGetCookieUserPassWord, passWord, Integer.MAX_VALUE);
			}else{
				removeCookie(Global.setGetCookieUserPassWord);
			}*/
			session.setAttribute("titleValue", value);
//			session.setAttribute("userImage", adminUser.getUserImage()); //保存头像
			adminUser.setPassWord(null);
			adminUser.setUserImage(null);
			adminUser.setCreateTime(new Date());
			session.setAttribute("adminUser", adminUser);
			
			session.setAttribute("role", role);
//			session.setAttribute("modulesList", moduleList);
			session.setAttribute("listModule", listModule);
			//更新用户登录信息
			adminUserService.adminUserLogin(adminUser.getId());
			//添加登录操作日志
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"账号:"+adminUser.getUserName()+"登录进入系统", ObjectHelper.servletPath(request), adminUser.getId().intValue(), ObjectHelper.getIpAddress(request));
//			setCookie("roleId", role.getId()+"", Integer.MAX_VALUE);
			
			jsonResult = JsonUtil.jsonSuccess(role);
		}
		return jsonResult;
	}
}
