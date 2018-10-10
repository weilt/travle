package com.xx.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.admin.dao.AdminAccesslogDao;
import com.xx.admin.dao.AdminModuleDao;
import com.xx.admin.entity.AdminModule;
import com.xx.admin.service.AdminModuleService;
import com.xx.admin.util.ApacheSecurityUtil;
import com.xx.controller.util.BaseController;
import com.xx.springBootUtil.result.JsonResult;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.util.UUIDHelper;
import com.xx.springBootUtil.validate.ValidateWriteInPost;
import com.xx.util.page.PageInfo;
import com.xx.util.result.JsonUtil;

@RestController
public class AdminModuleController extends BaseController{

	@Resource
	PageInfo pageInfo;
	@Resource
	private AdminModuleDao adminModuledao;
	@Autowired
	private AdminModuleService adminModuleService;
	@Resource
	private AdminAccesslogDao adminAccesslogDao;
	
	/**
	 * 获取module数据信息
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("admin/module")
	public ModelAndView queryList(Integer parentId,String url,ModelAndView modelAndView) throws NumberFormatException, Exception{
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
		for (Map map2 : list) {
			map2.put("encryptionId", ApacheSecurityUtil.aes(map2.get("id").toString(), ApacheSecurityUtil.aes));
		}
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("admin/adminModule/moduleList");
		return modelAndView;
	}
	
	
	/**
	 * 添加信息
	 * @return
	 * @throws Exception 
	 */
	@ValidateWriteInPost(parameter={"name"},msg={"名称不能为空"})
	@RequestMapping("admin/module/insert")
	public ModelAndView moduleInsert(AdminModule adminModule) throws Exception{
		
		if(ObjectHelper.getOrPost(request)){
			setAttr("parentId", adminModule.getParentId()==null?0:adminModule.getParentId());
			return new ModelAndView("admin/adminModule/moduleInsert");
		}else{
			adminModuledao.insert(adminModule);
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"添加模块信息："+adminModule.getUrl(), 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("添加成功","/admin/module"));
		}
		return null;
	}
	
	/**
	 * 修改信息
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("admin/module/update")
	public ModelAndView moduleUpdate(AdminModule adminModule) throws Exception{
		String encryptionId = adminModule.getEncryptionId();
		String EncryptionId =  ApacheSecurityUtil.dec(encryptionId+"",ApacheSecurityUtil.aes);
		Integer id = Integer.parseInt(EncryptionId);
		adminModule.setId(id);
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
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"修改模块信息："+adminModule.getUrl(), 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("修改成功","/admin/module"));
		}
		return null;
	}
	
	
	/**
	 * 删除数据
	 * @return
	 * @throws Exception 
	 * @throws NumberFormatException 
	 */
	@RequestMapping("admin/module/delete")
	public JsonResult moduleDelete() throws NumberFormatException, Exception{
		String[] moduleIds = getParaValues("moduleIds");
		if(ObjectHelper.isNotEmpty(moduleIds) && moduleIds.length > 0){
			for(String id : moduleIds){
				int moduleId = Integer.parseInt(ApacheSecurityUtil.dec(id, ApacheSecurityUtil.aes));
				adminModuleService.deleteModuleParentId(moduleId);
				adminModuledao.delete(moduleId);
			}
			return JsonUtil.jsonRefresh("删除成功");
		}else{
			return JsonUtil.jsonError("删除无效");
		}
	}
	
	
}
