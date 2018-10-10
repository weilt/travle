package com.xx.controller.adminBack.project;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.admin.dao.AdminAccesslogDao;
import com.xx.admin.entity.AdminUser;
import com.xx.admin.util.ApacheSecurityUtil;
import com.xx.controller.util.BaseController;
import com.xx.project.entity.HXProject;
import com.xx.project.mapper.HXProjectMapper;
import com.xx.service.project.service.HXProjectService;
import com.xx.springBootUtil.result.JsonResult;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.util.UUIDHelper;
import com.xx.springBootUtil.validate.ValidateWriteInPost;
import com.xx.util.page.PageInfo;
import com.xx.util.result.JsonUtil;

@RestController
public class HXProjectContrlller extends BaseController{
	@Resource
	PageInfo pageInfo; //分页
	@Resource
	private AdminAccesslogDao adminAccesslogDao;
	@Autowired
	private HXProjectService hxProjectService;
	@Autowired
	private HXProjectMapper hxProjectMapper;
	
	/**
	 * 查看项目
	 * @param modelAndView
	 * @param name 项目名称
	 * @return
	 */
	@RequestMapping("admin/project/query")
	public ModelAndView project_query(ModelAndView modelAndView, String name){
		map.put("name", name);
		int count = hxProjectMapper.queryListCount(map);
		pageInfo.format(count, request);//
		map.put("index", pageInfo.getPageSize()*(pageInfo.getPageNumber()-1));
		map.put("last", pageInfo.getPageSize());
		
		//数据
		List<Map<String, Object>> list = hxProjectMapper.queryList(map);
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("adminBack/project/projectList");
		return modelAndView;
		
	}
	/**
	 * 项目添加
	 * @param project 项目
	 * @return
	 */
	@ValidateWriteInPost(parameter={"name"},msg={"项目名不能为空"})
	@RequestMapping("admin/project/add")
	public ModelAndView project_add(HXProject project){
		if(ObjectHelper.getOrPost(request)){
			
			return new ModelAndView("adminBack/project/projectInsert");
		}else{
			AdminUser adminUser = sessionAdminUser();
			project.setUserId(adminUser.getId().intValue());
			hxProjectService.insert(project);
			
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"添加项目："+project.getName(), 
					ObjectHelper.servletPath(request), adminUser.getId().intValue(), ObjectHelper.getIpAddress(request));
			
			responseJson(JsonUtil.jsonForward("添加成功", "/admin/project/query"));
		}
		return null;
		
	}
	/**
	 * 修改项目
	 * @param project
	 * @return
	 */
	@RequestMapping("admin/project/edit")
	@ValidateWriteInPost(parameter={"name"},msg={"项目名不能为空"})
	public ModelAndView project_edit(HXProject project){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("adminBack/project/projectUpdate")
					.addObject("project", hxProjectMapper.findById(project.getId()));
			
		}else{
			
			hxProjectService.update(project);
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"修改项目信息："+project.getName(), 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("修改成功","/admin/project/query"));
		}
		return null;
	}
	
	/**
	 * 删除/恢复
	 * @return
	 */
	@RequestMapping("admin/project/delete")
	public JsonResult project_delete() {
		String[] ids = getParaValues("ids");
		String[] stuts = getParaValues("stuts");
		Integer status;//1-恢复 2-删除
		if (stuts!=null&&stuts.length!=0){
			
			status = Integer.parseInt(stuts[0]);
		}else{
			status = 2;
		}
		if(ObjectHelper.isNotEmpty(ids) && ids.length > 0){
			for(String id : ids){
				int a = Integer.parseInt(id);
				hxProjectService.delete(a,status);
			}
			return JsonUtil.jsonRefresh("删除成功");
		}else{
			return JsonUtil.jsonError("删除无效");
		}
	}
}
