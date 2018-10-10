package com.xx.controller.admin;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.xx.admin.dao.AdminAccesslogDao;
import com.xx.admin.dao.AdminRoleDao;
import com.xx.controller.util.BaseController;
import com.xx.springBootUtil.result.JsonResult;
import com.xx.springBootUtil.util.ObjectHelper;
import com.xx.springBootUtil.util.UUIDHelper;
import com.xx.util.page.PageInfo;
import com.xx.util.result.JsonUtil;
import com.xx.util.util.DateUtil;

@RestController
public class AdminAccesslogController extends BaseController{

	@Resource
	private PageInfo pageInfo;
	@Resource
	private AdminAccesslogDao adminAccesslogDao;
	@Resource
	private AdminRoleDao adminRoleDao;
	
	
	/**
	 * 查询日志信息
	 * @param userName 用户名
	 * @param roleId - 角色ID
	 * @param startTime - 开始时间
	 * @param lastTime 结束时间
	 * @param modelAndView - 返回的视图
	 * @return
	 */
	@RequestMapping("admin/accesslog/query")
	public ModelAndView accesslog_query(String userName,String roleId,String startTime,String lastTime,ModelAndView modelAndView){
		map.put("userName", userName);
		map.put("roleId", roleId);
		if(ObjectHelper.isEmpty(startTime) && ObjectHelper.isEmpty(lastTime)){
			Date date = new Date();
			startTime = DateUtil.datetime(DateUtil.addDay(date, -1), "second");
			lastTime = DateUtil.datetime(date, "second");
		}
		map.put("startTime", startTime);
		map.put("lastTime", lastTime);
		int count = adminAccesslogDao.queryListCount(map);
		pageInfo.format(count, request); //默认15页 
		map.put("index", pageInfo.getPageSize()*(pageInfo.getPageNumber()-1));
		map.put("last", pageInfo.getPageSize());
		List<Map> list = adminAccesslogDao.queryList(map);
		modelAndView.addObject("roleList", adminRoleDao.queryAllList());
		modelAndView.addObject("map", map);
		modelAndView.addObject("list", list);
		modelAndView.addObject("pageInfo", pageInfo);
		modelAndView.setViewName("admin/adminAccesslog/accesslogList");
		return modelAndView;
	}
	
	
	/**
	 * 删除日志信息
	 * @return
	 */
	@RequestMapping("admin/accesslog/delete")
	public JsonResult accesslog_delete(){
		String[] atty = getParaValues("accesslogs");
		int size = atty.length;
		if(size > 0){
			List<String> list = Arrays.asList(atty);
			adminAccesslogDao.deleteAccoun(list);
			String text = "删除日志";
			if(size > 1)
				text = "批量删除日志";
			adminAccesslogDao.insert(UUIDHelper.createUUId(),text, 
					ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		}
		return JsonUtil.jsonRefresh("删除成功！");
	}
	
	
	/**
	 * 条件删除日志信息
	 * @return
	 */
	@RequestMapping("admin/accesslog/deletetj")
	public JsonResult accesslog_deleteTJ(String userName,String roleId,String startTime,String lastTime){
		map.clear();
		map.put("userName", userName);
		map.put("roleId", roleId);
		map.put("startTime", startTime);
		map.put("lastTime", lastTime);
		adminAccesslogDao.deleteAccounTiaojian(map);
		adminAccesslogDao.insert(UUIDHelper.createUUId(),"条件删除日志", 
				ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		return JsonUtil.jsonRefresh("删除成功！");
	}
	
	
	
}
