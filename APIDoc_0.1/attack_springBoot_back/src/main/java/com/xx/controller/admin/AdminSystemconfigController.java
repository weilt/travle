package com.xx.controller.admin;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.admin.dao.AdminAccesslogDao;
import com.xx.admin.dao.AdminSystemConfigDao;
import com.xx.admin.entity.AdminSystemconfig;
import com.xx.admin.entity.AdminUser;
import com.xx.controller.util.BaseController;
import com.xx.springBootUtil.result.JsonResult;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.util.UUIDHelper;
import com.xx.springBootUtil.validate.ValidateWriteIn;
import com.xx.springBootUtil.validate.ValidateWriteInPost;
import com.xx.util.page.PageInfo;
import com.xx.util.result.JsonUtil;

/**
 * 系统配置
 * @author JIAO_LIU_BABA
 */
@RestController
public class AdminSystemconfigController extends BaseController{

	@Resource
	private PageInfo pageInfo;
	@Resource
	private AdminSystemConfigDao adminSystemConfigDao;
	@Resource
	private AdminAccesslogDao adminAccesslogDao;
	/**
	 * 系统配置信息
	 * @param displayName - 显示名称
	 * @param configKey - 配置键
	 * @param modelAndView
	 * @return
	 */
	@RequestMapping("admin/systemconfig/query")
	public ModelAndView systemconfig_query(String displayName,String configKey,ModelAndView modelAndView){
		map.put("displayName", displayName);
		map.put("configKey", configKey);
		int count = adminSystemConfigDao.queryListCount(map);
		pageInfo.format(count, request); //默认15页 
		map.put("index", pageInfo.getPageSize()*(pageInfo.getPageNumber()-1));
		map.put("last", pageInfo.getPageSize());
		List<AdminSystemconfig> list = adminSystemConfigDao.queryList(map);
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("admin/adminSystemConfig/adminSystemConfigList");
		return modelAndView;
	}
	
	
	/**
	 * 添加数据信息
	 * @param adminSystemconfig
	 * @return
	 */
	@ValidateWriteInPost(parameter={"displayName","configKey"},msg={"名称不能为空","键值不能为空"})
	@RequestMapping("admin/systemconfig/insert")
	public ModelAndView systemconfig_insert(AdminSystemconfig adminSystemconfig){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminSystemConfig/adminSystemConfigInsert");
		}else{
			if(ObjectHelper.isNotEmpty(adminSystemConfigDao.getValue(adminSystemconfig.getConfigKey()))){
				responseJson(JsonUtil.jsonError("键值已存在，请重新命名"));
			}else{
				AdminUser adminUser = sessionAdminUser();
				adminSystemconfig.setIsReadOnly((byte)0);
				adminSystemconfig.setUserId(adminUser.getId().intValue());
				adminSystemconfig.setCreateTime(new Date());
				int ins = adminSystemConfigDao.insert(adminSystemconfig);
				if(ins == 0){
					responseJson(JsonUtil.jsonError("键值重复或添加信息错误"));
				}else{
					adminAccesslogDao.insert(UUIDHelper.createUUId(),"添加配置文件："+adminSystemconfig.getConfigKey(), 
							ObjectHelper.servletPath(request), adminUser.getId().intValue(), ObjectHelper.getIpAddress(request));
					responseJson(JsonUtil.jsonForward("添加成功", "/admin/systemconfig/query"));
				}
			}
		}
		return null;
	}
	
	
	/**
	 * 修改数据信息
	 * @param adminSystemconfig
	 * @return
	 */
	@ValidateWriteInPost(parameter={"id","displayName"},msg={"ID不能为空","名称不能为空"})
	@RequestMapping("admin/systemconfig/update")
	public ModelAndView systemconfig_update(AdminSystemconfig adminSystemconfig){
		if(ObjectHelper.getOrPost(request)){
			return new ModelAndView("admin/adminSystemConfig/adminSystemConfigUpdate")
					.addObject("obj", adminSystemConfigDao.findById(adminSystemconfig.getId()));
		}else{
			if(ObjectHelper.isNotEmpty(adminSystemConfigDao.getValue(adminSystemconfig.getConfigKey()))){
				responseJson(JsonUtil.jsonError("键值已存在，请重新命名"));
			}else{
				AdminUser adminUser = sessionAdminUser();
				AdminSystemconfig update = adminSystemConfigDao.findById(adminSystemconfig.getId());
				update.setDisplayName(adminSystemconfig.getDisplayName());
				update.setConfigValue(adminSystemconfig.getConfigValue());
				update.setDescription(adminSystemconfig.getDescription());
				adminSystemConfigDao.update(update);
				adminAccesslogDao.insert(UUIDHelper.createUUId(),"修改配置文件："+update.getConfigKey(), 
						ObjectHelper.servletPath(request), adminUser.getId().intValue(), ObjectHelper.getIpAddress(request));
				responseJson(JsonUtil.jsonForward("修改成功", "/admin/systemconfig/query"));
			}
		}
		return null;
	}
	
	/**
	 * 删除配置信息
	 * @param id
	 * @return
	 */
	@ValidateWriteIn(parameter={"id"},msg={"ID不能为空"})
	@RequestMapping("admin/systemconfig/delete")
	public JsonResult systemconfig_delete(Integer id){
		AdminSystemconfig delete = adminSystemConfigDao.findById(id);
		if(ObjectHelper.isNotEmpty(delete)){
			adminSystemConfigDao.delete(id);
			adminAccesslogDao.insert(UUIDHelper.createUUId(),"删除配置信息："+delete.getConfigKey(), 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		}
		return JsonUtil.jsonRefresh("删除成功");
	}
}
