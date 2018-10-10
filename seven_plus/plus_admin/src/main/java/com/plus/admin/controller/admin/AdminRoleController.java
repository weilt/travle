package com.plus.admin.controller.admin;

import com.admin.service.admin.AdminRoleService;
import com.common.pakag.PageInfo;
import com.common.result.JsonResult;
import com.common.util.JsonUtil;
import com.common.util.ObjectHelper;
import com.common.util.UUIDHelper;
import com.common.validate.ValidateWriteIn;
import com.common.validate.ValidateWriteInPost;
import com.domain.admin.mapper.AdminAccesslogMapper;
import com.domain.admin.mapper.AdminModuleMapper;
import com.domain.admin.mapper.AdminRoleMapper;
import com.domain.admin.entity.AdminRole;
import com.domain.admin.entity.AdminUser;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class AdminRoleController extends BaseController {
	@Autowired
	PageInfo pageInfo; //分页
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	@Autowired
	private AdminAccesslogMapper adminAccesslogMapper;
	@Autowired
	private AdminModuleMapper adminModuleMapper;
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
		int count = adminRoleMapper.queryListCount(map);
		pageInfo.format(count, request); //默认15页 
		map.put("index", pageInfo.getPageSize()*(pageInfo.getPageNumber()-1));
		map.put("last", pageInfo.getPageSize());
		List<AdminRole> list = adminRoleMapper.queryList(map);
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("admin/adminRole/roleList");
		return modelAndView;
	}
	
	/**
	 * 添加角色信息
	 * @return
	 */
	@ValidateWriteInPost(parameter={"name"},msg={"角色名称不能为空"})
	@RequestMapping("admin/role/add")
	public ModelAndView role_add(AdminRole adminRole){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminRole/roleInsert");
		}else{
			AdminUser adminUser = sessionAdminUser();
			adminRole.setUserId(adminUser.getId().intValue());
			adminRoleMapper.insert(adminRole);
			
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),"添加角色："+adminRole.getName(),
					ObjectHelper.servletPath(request), adminUser.getId().intValue(), ObjectHelper.getIpAddress(request));
			
			responseJson(JsonUtil.jsonForward("添加成功", "/admin/role/query"));
		}
		return null;
	}
	
	/**
	 * 修改角色信息
	 * @return
	 */
	@ValidateWriteInPost(parameter={"name"},msg={"角色名称不能为空"})
	@RequestMapping("admin/role/edit")
	public ModelAndView role_edit(AdminRole adminRole){
		if(ObjectHelper.getOrPost(request)){
			adminRole = adminRoleMapper.findById(adminRole.getId());
			return new ModelAndView("admin/adminRole/roleUpdate").addObject("adminRole", adminRole);
		}else{
			adminRoleMapper.update(adminRole);
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),"修改角色："+adminRole.getName(),
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("修改成功", "/admin/role/query"));
		}
		return null;
	}
	
	
	/**
	 * 删除角色
	 * @param id
	 * @return
	 */
	@ValidateWriteIn(parameter={"id"},msg={"角色ID不能为空"})
	@RequestMapping("admin/role/delete")
	public JsonResult role_delete(Integer id){
		adminRoleService.role_delete(id);
		adminAccesslogMapper.insert(UUIDHelper.createUUId(),"删除角色信息" + id,
				ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		return JsonUtil.jsonRefresh("删除成功");
	}
	
	
	/**
	 * 重置角色人数
	 * @return
	 */
	@RequestMapping("admin/role/numberAll")
	public JsonResult role_numberAll(){
		adminRoleMapper.update_numberAll();
		adminAccesslogMapper.insert(UUIDHelper.createUUId(),"重置角色人数" ,
				ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		return JsonUtil.jsonRefresh("操作成功");
	}
	
	/**
	 * 添加授权信息
	 * @param roleId
	 * @return
	 */
	@RequestMapping("admin/role/adpower")
	public ModelAndView role_adpower(Integer roleId){
		AdminRole adminRole = adminRoleMapper.findById(roleId);
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminRole/powerAdd").addObject("viewrole", adminRole)
					.addObject("moduleList", adminModuleMapper.queryList())
					.addObject("roleRightList", adminModuleMapper.findByRoleId(roleId));
		}else{
			if(roleId <= 0){
				responseJson(JsonUtil.jsonError("授权错误，请联系管理员"));
				return null;
			}
			String[] array = getParaValues("rightList");
			adminRoleService.role_adpower(roleId, array);
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),"角色授权成功：" + adminRole.getName(),
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonSuccess("授权成功"));
		}
		return null;
	}
	
}
