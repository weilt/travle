package com.hx.admin.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.admin.AdminRole;
import com.hwt.domain.entity.admin.AdminSystemconfig;
import com.hwt.domain.entity.admin.vo.AdminModuleVo;
import com.hwt.domain.entity.admin.vo.AdminSystemconfigVo;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.service.admin.AdminSystemconfigService;
import com.hx.admin.validate.ValidateParam;
import com.hx.core.page.asyn.PageResult;

/**
 * 系统配置
 * @author Administrator
 *
 */
@RestController
public class AdminSystemconfigController {
	@Autowired
	private AdminSystemconfigService adminSystemconfigService;
	
	/**
	 * 跳转到系统配置界面
	 * @return
	 */
	@RequestMapping("/admin/systemconfig/list")
	public ModelAndView list() {
		return new ModelAndView("admin/systemconfig/systemconfig-list");
	}
	
	/**
	 * 通过条件查询
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param startNum
	 *            起始条数数
	 * @param orderBy
	 *            排序
	 * @param displayName
	 *            名称
	 * @param configKey
	 *            配置键
	 * @return
	 */
	@RequestMapping("admin/systemconfig/query")
	public ResultEntity query(@RequestParam(defaultValue = "10")Integer pageSize, @RequestParam(defaultValue = "0")Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[displayName]", required = false) String displayName,
			@RequestParam(name = "paramMap[configKey]", required = false ) String configKey) {
		
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("displayName", displayName);
		map.put("configKey", configKey);

		PageResult<AdminSystemconfigVo> pageResult = adminSystemconfigService.queryByMap(map);
		return new ResultEntity(pageResult);
	}
	
	/**
	 * 系统配置添加
	 * @param adminRole
	 * @return
	 */
	@ValidateParam(field={"displayName","configKey"},message={"名称不能为空","键值不能为空"})
	@RequestMapping("admin/systemconfig/insert")
	public ResultEntity insert(AdminSystemconfig adminSystemconfig){
		adminSystemconfigService.insert(adminSystemconfig);
		return new ResultEntity("200","添加成功");
	}
	
	/**
	 * 配置修改
	 * @param adminRole
	 * @param type 1-返回值对象 2-进行更新操作
	 * @return
	 */
	@ValidateParam(field={"id"}, message={"id不能为空"})
	@RequestMapping("admin/systemconfig/update")
	public ResultEntity update(AdminSystemconfig adminSystemconfig,@RequestParam(defaultValue="1")Integer type){
		if (type==1){
			
			AdminSystemconfig adminSystemconfigEdit = adminSystemconfigService.queryById(adminSystemconfig.getId());
			return new ResultEntity(adminSystemconfigEdit);
		}else{
			adminSystemconfigService.update(adminSystemconfig);
			return new ResultEntity("200","修改成功");
		}
	}
}
