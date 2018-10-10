package com.plus.admin.controller.admin;

import com.common.consts.Global;
import com.common.result.JsonResult;
import com.common.util.JsonUtil;
import com.common.util.MessageUtil;
import com.common.util.ObjectHelper;
import com.common.util.UUIDHelper;
import com.common.validate.Clear;
import com.common.validate.ValidateWriteIn;
import com.domain.admin.entity.AdminModule;
import com.domain.admin.entity.AdminRole;
import com.domain.admin.entity.AdminUser;
import com.domain.admin.mapper.*;
import com.plus.admin.controller.base.BaseController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.*;

/**
 * 后台管理进入后台管理
 * @author LiuGang
 */
@Clear
@RestController
public class AdminLoginController extends BaseController {

	@Resource
	private AdminUserMapper adminUserMapper;
	@Resource
	private AdminRoleMapper adminRoleMapper;
	@Resource
	private AdminModuleMapper adminModuleMapper;
	@Resource
	private AdminSystemConfigMapper adminSystemconfigMapper;
	@Resource
	private AdminAccesslogMapper adminAccesslogMapper;
	
	/**
	 * 跳转到登录页面 - 切清除所有session的值和登录的cookie值
	 */
	@RequestMapping("/back")
	public ModelAndView back(){
		//执行退出
		super.getSession().invalidate();
		Map map = new HashMap();
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
	@RequestMapping("/back/login")
	@ValidateWriteIn(parameter={"userName","passWord"},msg={"户名和密码不能为空","户名和密码不能为空!"})
	public JsonResult adminLogin(String userName, String passWord, String checked){
		JsonResult jsonResult = null;
		AdminUser adminUser = adminUserMapper.userLogin(userName, MessageUtil.encodeMD5(passWord));
		if(ObjectHelper.isEmpty(adminUser)){
			jsonResult = JsonUtil.jsonError("账号或者密码错误");
		}else if(adminUser.getIsDelete() == 2){
			jsonResult = JsonUtil.jsonError("账号已被禁用，有问题请联系管理员");
		}else if(adminUser.getRoleId().intValue() <= 0){
			jsonResult = JsonUtil.jsonError("该用户还没分配角色,有问题请联系管理员");
		}else{
			//查询角色信息
			AdminRole role = adminRoleMapper.findById(adminUser.getRoleId());
			if(ObjectHelper.isEmpty(role)){
				return JsonUtil.jsonError("角色信息错误,请联系管理员");
			}
			//查询权限
			List<AdminModule> listModule = null;
			//超级管理员权限
			if(adminUser.getRoleId() == Global.IS_SUPER_ADMIN){
				listModule = adminModuleMapper.queryList();
			}else{
				listModule = adminModuleMapper.findByRoleId(adminUser.getRoleId());
			}
			//获取 值
			String value = adminSystemconfigMapper.getValue("systemName");
			
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
			Optional<AdminModule> optional = listModule.stream().filter(l->l.getName().equals("用户电话查看")).findFirst();
			if (optional.isPresent()){
				session.setAttribute("seePhone",1);
			}
			//添加登录操作日志
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),"账号:"+adminUser.getUserName()+"登录进入系统", ObjectHelper.servletPath(request), adminUser.getId().intValue(), ObjectHelper.getIpAddress(request));
//			setCookie("roleId", role.getId()+"", Integer.MAX_VALUE);
			
			jsonResult = JsonUtil.jsonSuccess(role);
		}
		return jsonResult;
	}
}
