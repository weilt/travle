package com.xx.controller.admin;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.admin.dao.AdminAccesslogDao;
import com.xx.admin.dao.AdminRoleDao;
import com.xx.admin.dao.AdminSystemConfigDao;
import com.xx.admin.dao.AdminUserDao;
import com.xx.admin.entity.AdminRole;
import com.xx.admin.entity.AdminUser;
import com.xx.admin.service.AdminUserService;
import com.xx.admin.util.ApacheSecurityUtil;
import com.xx.controller.util.BaseController;
import com.xx.springBootUtil.excption.BaseException;
import com.xx.springBootUtil.result.JsonResult;
import com.xx.springBootUtil.util.MessageUtil;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.util.UUIDHelper;
import com.xx.springBootUtil.validate.*;
import com.xx.util.page.PageInfo;
import com.xx.util.result.JsonUtil;

@RestController
public class AdminUserController extends BaseController{

	@Resource
	private PageInfo pageInfo;
	@Resource
	private AdminUserDao adminUserDao;
	@Resource
	private AdminRoleDao adminRoleDao;
	@Resource
	private AdminAccesslogDao adminAccesslogDao;
	@Resource
	private AdminSystemConfigDao adminSystemConfigDao;
	@Autowired
	private AdminUserService adminUserService;
	
	
	
	/**
	 * 进入到后台页面信息中
	 * @return
	 */
	@Clear
	@RequestMapping("admin/user/main")
	public ModelAndView main(){
		return new ModelAndView("admin/login/main");
	}
	/**
	 * 获取左边的数据信息
	 * @return
	 */
	@Clear
	@RequestMapping("admin/user/left")
	public ModelAndView main_left(){
		return new ModelAndView("admin/login/left");
	}

	/**
	 * 获取后台网站首页信息
	 * @return
	 */
	@Clear
	@RequestMapping("admin/user/index")
	public ModelAndView main_index(){
		return new ModelAndView("admin/login/index");
	}
	
	/**
	 * 修改密码
	 * @param oldPassword 旧密码
	 * @param password - 新密码
	 * @param conPassword - 确认密码
	 * @return
	 */
	@ValidateWriteInPost(parameter={"oldPassword","password","conPassword"},type = {ValidateType.NULL,ValidateType.NULL,ValidateType.NULL},
			msg = {"旧密码不能为空","新密码不能为空","确认密码不能为空"})
	@RequestMapping("admin/user/passWord")
	public ModelAndView user_editPassWord(String oldPassword,String password,String conPassword){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminUser/passWord");
		}else{
			if(!password.equals(conPassword)){
				throw new BaseException("确认密码错误");
			}
			if(oldPassword.equals(password)){
				throw new BaseException("旧密码和新密码相同");
			}
			AdminUser adminUser = adminUserDao.findById(sessionAdminUser().getId());
			if(!adminUser.getPassWord().equals(MessageUtil.encodeMD5(oldPassword))){
				throw new BaseException("旧密码错误");
			}
			password = MessageUtil.encodeMD5(password);
			adminUserDao.editPassWord(password, adminUser.getId());
			responseJson(JsonUtil.jsonSuccess("修改成功"));
			return null;
		}
	}
	
	/**
	 * 修改个人信息
	 * @return
	 */
	@RequestMapping("admin/adminUser/personalData")
	public ModelAndView user_editInfo(String realName,String sex,String jobPosition,String address,
			String telephone,String aboutMe,String wchat){
		AdminUser adminUser = sessionAdminUser();
		adminUser = adminUserDao.findById(adminUser.getId());
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminUser/personalData").addObject("adminUser", adminUser);
		}else{
			adminUser.setRealName(realName);
			adminUser.setSex((byte)Integer.parseInt(sex));
			adminUser.setJobPosition(jobPosition);
			adminUser.setAddress(address);
			adminUser.setTelephone(telephone);
			adminUser.setAboutMe(aboutMe);
			adminUser.setWchat(wchat);
			adminUserDao.update(adminUser);
			responseJson(JsonUtil.jsonSuccess("修改成功！"));
		}
		return null;
	}
	
	// 修改会员 信息 *******************************************************
	
	/**
	 * 查询 用户信息
	 * @param userName - 用户名
	 * @param realName - 真实姓名
	 * @param sex -性别
	 * @param roleId -角色ID
	 * @param telephone 电话
	 * @param isDelete 是否删除
	 * @param modelAndView
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("admin/adminUser/query")
	public ModelAndView user_query(String userName,String realName,String sex,String roleId,
			String telephone,String isDelete,ModelAndView modelAndView) throws Exception{
		map.clear();
		map.put("userName", userName);
		map.put("realName", realName);
		map.put("sex", ObjectHelper.isEmpty(sex)? -1:Integer.parseInt(sex));
		map.put("roleId", ObjectHelper.isEmpty(roleId)? -1:Integer.parseInt(roleId));
		map.put("telephone", telephone);
		map.put("isDelete", ObjectHelper.isEmpty(isDelete)? -1:Integer.parseInt(isDelete));
		
		int count = adminUserDao.queryListCount(map);
		pageInfo.format(count, request); //默认15页 
		map.put("index", pageInfo.getPageSize()*(pageInfo.getPageNumber()-1));
		map.put("last", pageInfo.getPageSize());
		List<Map> list = adminUserDao.queryList(map);
		for (Map map : list) {
			map.put("encryptionId", ApacheSecurityUtil.aes(map.get("id").toString(), ApacheSecurityUtil.aes));
		}
		modelAndView.addObject("roleList", adminRoleDao.queryAllList());
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("admin/adminUser/userList");
		return modelAndView;
	}
	
	
	/**
	 * 添加用户信息
	 * @param adminUser
	 * @return
	 * @throws Exception 
	 */
	@ValidateWriteInPost(parameter={"userName","encryptionRoleId"},msg={"用户名不能为空","请选择角色"})
	@RequestMapping("admin/adminUser/insert")
	public ModelAndView user_insert(AdminUser adminUser) throws Exception{
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminUser/userInsert")
					.addObject("roleList", adminRoleDao.queryAllList());
		}else{
			String encryptionRoleId = adminUser.getEncryptionRoleId();
			String EncryptionRoleId =  ApacheSecurityUtil.dec(encryptionRoleId+"",ApacheSecurityUtil.aes);
			Integer roldId = Integer.parseInt(EncryptionRoleId);
			adminUser.setRoleId(roldId);
			adminUserService.AdminUserInsert(adminUser,sessionAdminUser().getId().intValue());
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"USER添加用户："+adminUser.getUserName(), 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("添加成功", "/admin/adminUser/query"));
		}
		return null;
	}
	
	/**
	 * 修改用户信息
	 * @param adminUser
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("admin/adminUser/update")
	public ModelAndView user_update(AdminUser adminUser) throws Exception {
		String encryptionId = adminUser.getEncryptionId();
		String EncryptionId =  ApacheSecurityUtil.dec(encryptionId+"",ApacheSecurityUtil.aes);
		Long id = Long.parseLong(EncryptionId);
		adminUser.setId(id);
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminUser/userUpdate")
					.addObject("roleList", adminRoleDao.queryAllList())
					.addObject("obj", adminUserDao.findById(adminUser.getId()));
		} else {
			String encryptionRoleId = adminUser.getEncryptionRoleId();
			String EncryptionRoleId =  ApacheSecurityUtil.dec(encryptionRoleId+"",ApacheSecurityUtil.aes);
			Integer roldId = Integer.parseInt(EncryptionRoleId);
			adminUser.setRoleId(roldId);
			adminUserService.AdminUserUpdate(adminUser);
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"USER修改用户信息："+adminUser.getUserName(), 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("修改成功", "/admin/adminUser/query"));
		}
		return null;
	}
	
	/**
	 * 重置用户密码
	 * @return
	 * @throws Exception 
	 */
	@ValidateWriteInPost(parameter={"encryptionId"},msg={"参数错误"})
	@RequestMapping("admin/adminUser/adreset")
	public JsonResult user_adreset(String encryptionId) {
		String resetPassword = adminSystemConfigDao.getValue("resetPassword");// 系统初始化密码
		if (resetPassword == null || "".equals(resetPassword)) 
			resetPassword = "666666";
		String userId = "";
		try {
			userId = ApacheSecurityUtil.dec(encryptionId, ApacheSecurityUtil.aes);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("不要修改参数");
		}
		adminUserDao.editPassWord(DigestUtils.md5Hex(resetPassword), Long.valueOf(userId));
		
		adminAccesslogDao.insert(UUIDHelper.createUUId(),"重置用户密码userId："+userId, 
				ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		
		return JsonUtil.jsonSuccess("重置成功，密码是："+resetPassword);
	}
	
	/**
	 * 禁用恢复用户
	 * @return
	 * @throws Exception 
	 */
	@ValidateWriteInPost(parameter={"encryptionId"},msg={"参数错误"})
	@RequestMapping("admin/adminUser/delete")
	public JsonResult user_delete(String encryptionId) throws Exception{
		String userId = ApacheSecurityUtil.dec(encryptionId, ApacheSecurityUtil.aes);
		Long id = Long.valueOf(userId);
		AdminUser user = adminUserDao.findById(id);
		if(ObjectHelper.isNotEmpty(user)){
			int isDelete = 1;//1-正常 2-禁用
			if(user.getIsDelete() == 1){
				isDelete = 2;
			}
			adminUserDao.editIsDelete(isDelete,id);
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"禁用启用用户userId："+userId, 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		}
		return JsonUtil.jsonRefresh("操作成功");
	}
	
}
