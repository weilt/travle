package com.xx.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.admin.dao.AdminAccesslogDao;
import com.xx.admin.dao.AdminModuleDao;
import com.xx.admin.dao.AdminRoleDao;
import com.xx.admin.entity.AdminRole;
import com.xx.admin.entity.AdminUser;
import com.xx.admin.service.AdminRoleService;
import com.xx.admin.util.ApacheSecurityUtil;
import com.xx.controller.util.BaseController;
import com.xx.springBootUtil.result.JsonResult;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.util.UUIDHelper;
import com.xx.springBootUtil.validate.ValidateWriteIn;
import com.xx.springBootUtil.validate.ValidateWriteInPost;
import com.xx.util.page.PageInfo;
import com.xx.util.result.JsonUtil;

@RestController
public class AdminRoleController extends BaseController{
	@Resource
	PageInfo pageInfo; //分页
	@Resource
	private AdminRoleDao adminRoleDao;
	@Resource
	private AdminAccesslogDao adminAccesslogDao;
	@Resource
	private AdminModuleDao adminModuleDao;
	@Autowired
	private AdminRoleService adminRoleService;
	
	/**
	 * 查询角色信息
	 * @param name
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("admin/role/query")
	public ModelAndView role_query(String name,ModelAndView modelAndView){
		map.put("name", name);
		int count = adminRoleDao.queryListCount(map);
		pageInfo.format(count, request); //默认15页 
		map.put("index", pageInfo.getPageSize()*(pageInfo.getPageNumber()-1));
		map.put("last", pageInfo.getPageSize());
		List<AdminRole> list = adminRoleDao.queryList(map);
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("admin/adminRole/roleList");
		return modelAndView;
	}
	
	/**
	 * 添加角色信息
	 * @return
	 * @throws Exception 
	 */
	@ValidateWriteInPost(parameter={"name"},msg={"角色名称不能为空"})
	@RequestMapping("admin/role/add")
	public ModelAndView role_add(AdminRole adminRole) throws Exception{
		
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminRole/roleInsert");
		}else{
			AdminUser adminUser = sessionAdminUser();
			adminRole.setUserId(adminUser.getId().intValue());
			adminRoleDao.insert(adminRole);
			
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"添加角色："+adminRole.getName(), 
					ObjectHelper.servletPath(request), adminUser.getId().intValue(), ObjectHelper.getIpAddress(request));
			
			responseJson(JsonUtil.jsonForward("添加成功", "/admin/role/query"));
		}
		return null;
	}
	
	/**
	 * 修改角色信息
	 * @return
	 * @throws Exception 
	 */
	@ValidateWriteInPost(parameter={"name"},msg={"角色名称不能为空"})
	@RequestMapping("admin/role/edit")
	public ModelAndView role_edit(AdminRole adminRole) throws Exception{
		String encryptionId = adminRole.getEncryptionId();
		String EncryptionId =  ApacheSecurityUtil.dec(encryptionId+"",ApacheSecurityUtil.aes);
		Integer id = Integer.parseInt(EncryptionId);
		adminRole.setId(id);
		if(ObjectHelper.getOrPost(request)){
			adminRole = adminRoleDao.findById(adminRole.getId());
			return new ModelAndView("admin/adminRole/roleUpdate").addObject("adminRole", adminRole);
		}else{
			adminRoleDao.update(adminRole);
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"修改角色："+adminRole.getName(), 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("修改成功", "/admin/role/query"));
		}
		return null;
	}
	
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 * @throws Exception 
	 */
	@ValidateWriteIn(parameter={"encryptionId"},msg={"角色ID不能为空"})
	@RequestMapping("admin/role/delete")
	public JsonResult role_delete(String encryptionId){
		String EncryptionId = "";
		try {
			EncryptionId = ApacheSecurityUtil.dec(encryptionId, ApacheSecurityUtil.aes);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("不要修改参数");
		}
		
		Integer id = Integer.parseInt(EncryptionId);
		adminRoleService.role_delete(id);
		adminAccesslogDao.insert(UUIDHelper.createUUId(),"删除角色信息" + id, 
				ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		return JsonUtil.jsonRefresh("删除成功");
	}
	
	
	/**
	 * 重置角色人数
	 * @return
	 */
	@RequestMapping("admin/role/numberAll")
	public JsonResult role_numberAll(){
		adminRoleDao.update_numberAll();
		adminAccesslogDao.insert(UUIDHelper.createUUId(),"重置角色人数" , 
				ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		return JsonUtil.jsonRefresh("操作成功");
	}
	
	/**
	 * 添加授权信息
	 * @param adminRole
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("admin/role/adpower")
	public ModelAndView role_adpower(AdminRole adminRole) throws Exception{
		String encryptionId = adminRole.getEncryptionId();
		String EncryptionId =  ApacheSecurityUtil.dec(encryptionId+"",ApacheSecurityUtil.aes);
		Integer id = Integer.parseInt(EncryptionId);
		adminRole.setId(id);
		AdminRole adminRole1 = adminRoleDao.findById(adminRole.getId());
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminRole/powerAdd").addObject("viewrole", adminRole1)
					.addObject("moduleList", adminModuleDao.queryList())
					.addObject("roleRightList", adminModuleDao.findByRoleId(adminRole1.getId()));
		}else{
			if(adminRole.getId() <= 0){
				responseJson(JsonUtil.jsonError("授权错误，请联系管理员"));
				return null;
			}
			String[] array = getParaValues("rightList");
			adminRoleService.role_adpower(adminRole.getId(), array);
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"角色授权成功：" + adminRole.getName(), 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonSuccess("授权成功"));
		}
		return null;
	}
	
}
