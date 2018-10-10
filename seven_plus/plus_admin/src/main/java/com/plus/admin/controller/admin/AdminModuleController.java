package com.plus.admin.controller.admin;

import com.admin.service.admin.AdminModuleService;
import com.common.pakag.PageInfo;
import com.common.result.JsonResult;
import com.common.util.JsonUtil;
import com.common.util.ObjectHelper;
import com.common.util.UUIDHelper;
import com.common.validate.ValidateWriteInPost;
import com.domain.admin.mapper.AdminAccesslogMapper;
import com.domain.admin.mapper.AdminModuleMapper;
import com.domain.admin.entity.AdminModule;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AdminModuleController extends BaseController {

	@Autowired
	PageInfo pageInfo;
	@Autowired
	private AdminModuleMapper adminModuledao;
	@Autowired
	private AdminModuleService adminModuleService;
	@Autowired
	private AdminAccesslogMapper adminAccesslogMapper;
	
	/**
	 * 获取module数据信息
	 * @return
	 */
	@RequestMapping("admin/module")
	public ModelAndView queryList(Integer parentId,String url,ModelAndView modelAndView){
		if(ObjectHelper.isEmpty(parentId)){
			parentId = 0;
		}
		if (parentId > 0) {
			List<AdminModule> riList = new ArrayList<AdminModule>();
			AdminModule mo = adminModuledao.findById(parentId);
			modelAndView.addObject("module", mo);
			// 权限路径设置
			String rightPath = adminModuleService.getRightPathIdToTop(mo);
			if (ObjectHelper.isNotEmpty(rightPath)) {
				String[] tem = rightPath.split("->");
				for (int i = 0; i < tem.length; i++) {
					String[] ch = tem[i].split(":");
					AdminModule mod = new AdminModule();
					mod.setId(Integer.parseInt(ch[0]));
					mod.setName(ch[1]);
					riList.add(mod);
				}
			}
			modelAndView.addObject("riList", riList);
		}
		Map<String, Object> map = new HashMap<>();
		map.put("parentId", parentId);
		map.put("url", url);
		Integer totalRow = adminModuledao.queryListMapCount(map);
		pageInfo.format(totalRow, request); //默认15页 
		map.put("index", pageInfo.getPageSize()*(pageInfo.getPageNumber()-1));
		map.put("last", pageInfo.getPageSize());
		List<Map> list = adminModuleService.getRightPathIdToTop(map);
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("admin/adminModule/moduleList");
		return modelAndView;
	}
	
	
	/**
	 * 添加信息
	 * @return
	 */
	@ValidateWriteInPost(parameter={"name"},msg={"名称不能为空"})
	@RequestMapping("admin/module/insert")
	public ModelAndView moduleInsert(AdminModule adminModule){
		if(ObjectHelper.getOrPost(request)){
			setAttr("parentId", adminModule.getParentId()==null?0:adminModule.getParentId());
			return new ModelAndView("admin/adminModule/moduleInsert");
		}else{
			adminModuledao.insert(adminModule);
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),"添加模块信息："+adminModule.getUrl(),
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("添加成功","/admin/module"));
		}
		return null;
	}
	
	/**
	 * 修改信息
	 * @return
	 */
	@RequestMapping("admin/module/update")
	public ModelAndView moduleUpdate(AdminModule adminModule){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminModule/moduleUpdate")
					.addObject("adminModule", adminModuledao.findById(adminModule.getId()));
			
		}else{
			AdminModule module = adminModuledao.findById(adminModule.getId());
			module.setName(adminModule.getName());
			module.setUrl(adminModule.getUrl());
			module.setModuleImage(adminModule.getModuleImage());
			module.setSort(adminModule.getSort());
			module.setIsHide(adminModule.getIsHide());
			module.setDescription(adminModule.getDescription());
			adminModuledao.update(module);
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),"修改模块信息："+adminModule.getUrl(),
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("修改成功","/admin/module"));
		}
		return null;
	}
	
	
	/**
	 * 删除数据
	 * @return
	 */
	@RequestMapping("admin/module/delete")
	public JsonResult moduleDelete(){
		String[] moduleIds = getParaValues("moduleIds");
		if(ObjectHelper.isNotEmpty(moduleIds) && moduleIds.length > 0){
			for(String id : moduleIds){
				int moduleId = Integer.parseInt(id);
				adminModuleService.deleteModuleParentId(moduleId);
				adminModuledao.delete(moduleId);
			}
			return JsonUtil.jsonRefresh("删除成功");
		}else{
			return JsonUtil.jsonError("删除无效");
		}
	}
	
	
}
