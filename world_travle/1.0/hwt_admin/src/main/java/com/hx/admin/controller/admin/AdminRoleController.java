package com.hx.admin.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.admin.AdminModule;
import com.hwt.domain.entity.admin.AdminRole;
import com.hwt.domain.entity.admin.vo.AdminRoleToUserVo;
import com.hwt.domain.entity.admin.vo.AdminRoleVo;

import com.hx.admin.base.ResultEntity;
import com.hx.admin.service.admin.AdminModuleService;
import com.hx.admin.service.admin.AdminRoleService;
import com.hx.admin.validate.ValidateParam;
import com.hx.core.page.asyn.PageResult;

/**
 * 角色操作
 * @author Administrator
 *
 */
@RestController
public class AdminRoleController {
	@Autowired
	private AdminRoleService adminRoleService;
	
	@Autowired
	private AdminModuleService adminModuleService;
	
	
	/**
	 * 跳转到角色列表
	 * @return
	 */
	@RequestMapping("admin/role/list")
	public ModelAndView list(){
		return new ModelAndView("admin/role/role-list");
		
	}
	
	/**
	 * 根据条件查询
	 * @param pageSize 页面大小
	 * @param startNum 起始条数数
	 * @param orderBy 排序
	 * @param name 角色名字
	 * @return
	 */
	@RequestMapping("admin/role/query")
	public ResultEntity query(@RequestParam(defaultValue = "10")Integer pageSize, @RequestParam(defaultValue = "0")Integer startNum, String orderBy,@RequestParam(name = "paramMap[name]",required=false) String name){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("name", name);
		PageResult<AdminRoleVo> pageResult = adminRoleService.queryByMap(map);
		return new ResultEntity(pageResult);
		
	}
	
	/**
	 * 角色添加
	 * @param adminRole
	 * @return
	 */
	@ValidateParam(field={"name"}, message={"名称不能为空"})
	@RequestMapping("admin/role/insert")
	public ResultEntity insert(AdminRole adminRole){
		adminRoleService.insert(adminRole);
		return new ResultEntity("200","添加成功");
	}
	
	/**
	 * 修改角色
	 * @param adminRole
	 * @param type 1-返回值对象 2-进行更新操作
	 * @return
	 */
	@ValidateParam(field={"id"}, message={"id不能为空"})
	@RequestMapping("admin/role/update")
	public ResultEntity update(AdminRole adminRole,@RequestParam(defaultValue="1")Integer type){
		if (type==1){
			
			AdminRole adminRoleEdit = adminRoleService.queryById(adminRole.getId());
			return new ResultEntity(adminRoleEdit);
		}else{
			adminRoleService.update(adminRole);
			return new ResultEntity("200","修改成功");
		}
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @return
	 */
	@RequestMapping("admin/role/delete")
	public ResultEntity delete(Integer id){
		int num = adminRoleService.deleteById(id);
		return new ResultEntity("200","删除成功");
	}
	
	/**
	 * 获取信息给用户操作
	 * @return
	 */
	@RequestMapping("admin/role/toUser")
	public ResultEntity toUser(){
		List<AdminRoleToUserVo> list = adminRoleService.queryToUser();
		return new ResultEntity(list);
	}
	
	/**
	 * 角色授权
	 * @param id	角色id
	 * @param type	1-返回角色权限信息 2-进行更新操作
	 * @return
	 */
	@RequestMapping("admin/role/adpower")
	public ResultEntity adpower(Integer id, @RequestParam(defaultValue="1")Integer type ,Integer[] rightList){
		
		if (type==1){
			List<AdminModule> adminModules = adminModuleService.queryAll();
			List<AdminModule> adminModulesToRole = adminModuleService.queryAllByRoleId(id);
			
			Map<String, Object> map = new HashMap<>();
			map.put("adminModules", adminModules);
			map.put("adminModulesToRole", adminModulesToRole);
			return new ResultEntity(map);
		}else{
			adminRoleService.adpower(id,rightList);
			
			return new ResultEntity("200","授权成功");
		}
	}
}
