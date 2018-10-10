package com.plus.admin.controller.admin;


import com.common.pakag.PageInfo;
import com.common.result.JsonResult;
import com.common.util.DateUtil;
import com.common.util.JsonUtil;
import com.common.util.ObjectHelper;
import com.common.util.UUIDHelper;
import com.domain.admin.mapper.AdminAccesslogMapper;
import com.domain.admin.mapper.AdminRoleMapper;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
public class AdminAccesslogController extends BaseController {

	@Autowired
	private PageInfo pageInfo;
	@Autowired
	private AdminAccesslogMapper adminAccesslogMapper;
	@Autowired
	private AdminRoleMapper adminRoleMapper;
	
	
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
		int count = adminAccesslogMapper.queryListCount(map);
		pageInfo.format(count, request); //默认15页 
		map.put("index", pageInfo.getPageSize()*(pageInfo.getPageNumber()-1));
		map.put("last", pageInfo.getPageSize());
		
		
		List<Map> list = adminAccesslogMapper.queryList(map);
		modelAndView.addObject("roleList", adminRoleMapper.queryAllList());
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
			adminAccesslogMapper.deleteAccoun(list);
			String text = "删除日志";
			if(size > 1)
				text = "批量删除日志";
			adminAccesslogMapper.insert(UUIDHelper.createUUId(),text,
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
		adminAccesslogMapper.deleteAccounTiaojian(map);
		adminAccesslogMapper.insert(UUIDHelper.createUUId(),"条件删除日志",
				ObjectHelper.servletPath(request), sessionAdminUser().getId().intValue(), ObjectHelper.getIpAddress(request));
		return JsonUtil.jsonRefresh("删除成功！");
	}
	
	
	
}
