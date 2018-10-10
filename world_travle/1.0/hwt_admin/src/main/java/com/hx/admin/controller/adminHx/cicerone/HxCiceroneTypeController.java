package com.hx.admin.controller.adminHx.cicerone;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.admin.AdminModule;
import com.hwt.domain.entity.cicerone.HxCiceroneType;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.service.cache.AdminUserCache;
import com.hx.admin.service.cache.AdminUserCacheTools;
import com.hx.admin.validate.ValidateParam;
import com.hx.adminHx.service.AdminCiceroneTypeService;
import com.hx.core.page.asyn.PageResult;

/**
 * 导游分类
 * @author Administrator
 *
 */
@RestController
public class HxCiceroneTypeController {
	
	@Autowired
	private AdminCiceroneTypeService adminCiceroneTypeService;
	
	/**
	 * 跳转到导游分类列表
	 * @return
	 */
	@RequestMapping("adminHx/cicerone_type/list")
	public ModelAndView cicerone_adopt_list() {
		return new ModelAndView("adminHx/cicerone_type/cicerone_type-list");
	}
	
	/**
	 * 跳转到导游分类列表
	 * @return
	 */
	@RequestMapping("adminHx/cicerone_type/query")
	public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[type_value]", required = false) String type_value) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("type_value", type_value);
		PageResult<Map<String, Object>> pageResult = adminCiceroneTypeService.queryByMap(map);
		
		return new ResultEntity(pageResult);
		
	}
	
	/**
	 * 导游分类添加
	 * @param adminModule
	 * @return
	 */
	@ValidateParam(field={"type_value"}, message={"名称不能为空"})
	@RequestMapping("adminHx/cicerone_type/insert")
	public ResultEntity insert(HxCiceroneType ciceroneType,HttpServletRequest request){
		
		ciceroneType.setCreate_id(AdminUserCacheTools.getSession(request).getAdminUserId());

		adminCiceroneTypeService.insert(ciceroneType);
		return new ResultEntity("200","添加成功");
	}
	
	/**
	 * 导游分类修改
	 * @param ciceroneType
	 * @param type 1-返回值对象 2-进行更新操作
	 * @return
	 */
	@ValidateParam(field={"id"}, message={"id不能为空"})
	@RequestMapping("adminHx/cicerone_type/update")
	public ResultEntity update(HxCiceroneType ciceroneType,@RequestParam(defaultValue="1")Integer type){
		if (type==1){
			
			HxCiceroneType ciceroneTypeEdit = adminCiceroneTypeService.queryById(ciceroneType.getId());
			return new ResultEntity(ciceroneTypeEdit);
		}else{
			adminCiceroneTypeService.update(ciceroneType);
			return new ResultEntity("200","修改成功");
		}
	}
}
