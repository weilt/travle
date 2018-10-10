package com.xx.controller.adminBack.project;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.admin.dao.AdminAccesslogDao;
import com.xx.admin.entity.AdminUser;
import com.xx.controller.util.BaseController;
import com.xx.project.entity.HXProject;
import com.xx.project.entity.InterfaceDocument;
import com.xx.project.mapper.InterfaceDocumentMapper;
import com.xx.service.project.service.InterfaceDocumentService;
import com.xx.springBootUtil.result.JsonResult;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.util.UUIDHelper;
import com.xx.springBootUtil.validate.ValidateWriteInPost;
import com.xx.util.page.PageInfo;
import com.xx.util.result.JsonUtil;

@RestController
public class InterfaceDocumentController extends BaseController {
	@Resource
	PageInfo pageInfo; // 分页
	@Resource
	private AdminAccesslogDao adminAccesslogDao;
	@Autowired
	private InterfaceDocumentService interfaceDocumentService;
	@Autowired
	private InterfaceDocumentMapper interfaceDocumentMapper;

	
	/**
	 * 查询项目的文档列表
	 * 
	 * @param modelAndView
	 * @param url 路劲
	 * @param name 接口名称
	 * @param projectId 项目id
	 * @param projectIdPageNumber 项目的页数
	 * @param projectIdPageSize 项目的每页显示条数
	 * @return
	 */
	@RequestMapping("admin/interfaceDocument/query")
	public ModelAndView interfaceDocument_query(ModelAndView modelAndView, String url, String name, Integer projectId,String projectName,
			Integer projectIdPageNumber, Integer projectIdPageSize) {
		if (projectIdPageNumber!=null){
			
			request.getSession().setAttribute("projectIdPageNumber", projectIdPageNumber);
		}
		if (projectIdPageSize!=null){
			
			request.getSession().setAttribute("projectIdPageSize", projectIdPageSize);
		}
		if (projectId!=null){
			
			request.getSession().setAttribute("projectId", projectId);
		}
		if (StringUtils.isNotBlank(projectName)){
			
			request.getSession().setAttribute("projectName", projectName);
		}
		
		map.put("url", url);
		map.put("name", name);
		map.put("projectId", projectId);
		int count = interfaceDocumentMapper.queryListCount(map);
		pageInfo.format(count, request);//
		map.put("index", pageInfo.getPageSize() * (pageInfo.getPageNumber() - 1));
		map.put("last", pageInfo.getPageSize());

		// 数据

		List<Map<String, Object>> list = interfaceDocumentMapper.queryList(map);
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("adminBack/interfaceDocument/interfaceDocumentList");
		return modelAndView;

	}
	/**
	 * 添加接口文档
	 * @param interfaceDocument
	 * @return
	 */
	@ValidateWriteInPost(parameter={"name"},msg={"接口名不能为空"})
	@RequestMapping("admin/interfaceDocument/add")
	public ModelAndView interfaceDocument_add(InterfaceDocument interfaceDocument){
		if(ObjectHelper.getOrPost(request)){
			
			return new ModelAndView("adminBack/interfaceDocument/interfaceDocumentInsert");
		}else{
			
			AdminUser adminUser = sessionAdminUser();
			interfaceDocument.setUserId(adminUser.getId().intValue());
			
			Integer projectId = (Integer) request.getSession().getAttribute("projectId");
			interfaceDocument.setProjectId(projectId);
			
			interfaceDocumentService.insert(interfaceDocument);
			
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"添加接口文档："+interfaceDocument.getName(), 
					ObjectHelper.servletPath(request), adminUser.getId().intValue(), ObjectHelper.getIpAddress(request));
			
			responseJson(JsonUtil.jsonForward("添加成功", "/admin/interfaceDocument/query"));
		}
		return null;
		
	}
	
	/**
	 * 修改接口文档
	 * @param project
	 * @return
	 */
	@RequestMapping("admin/interfaceDocument/edit")
	@ValidateWriteInPost(parameter={"name"},msg={"接口名不能为空"})
	public ModelAndView project_edit(InterfaceDocument interfaceDocument){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("adminBack/interfaceDocument/interfaceDocumentUpdate")
					.addObject("interfaceDocument", interfaceDocumentMapper.findById(interfaceDocument.getId()));
			
		}else{
			
			interfaceDocumentService.update(interfaceDocument);
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"修改接口文档："+interfaceDocument.getName(), 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
			responseJson(JsonUtil.jsonForward("修改成功","/admin/interfaceDocument/query"));
		}
		return null;
	}
	
	/**
	 * 删除/恢复接口文档
	 * @return
	 */
	@RequestMapping("admin/interfaceDocument/delete")
	public JsonResult interfaceDocument_delete() {
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
				interfaceDocumentService.delete(a,status);
			}
			return JsonUtil.jsonRefresh("删除成功");
		}else{
			return JsonUtil.jsonError("删除无效");
		}
	}
	
	/**
	 * 接口文档查看详情
	 * @param id id
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("admin/interfaceDocument/desc")
	public ModelAndView interfaceDocument_desc(Integer id,ModelAndView modelAndView){
		InterfaceDocument interfaceDocument = interfaceDocumentMapper.findDescById(id);
		modelAndView.addObject("interfaceDocument", interfaceDocument);
		modelAndView.setViewName("adminBack/interfaceDocument/interfaceDocumentDesc");
		return modelAndView;
		
	}
}
