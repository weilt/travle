package com.hx.admin.controller.admin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.admin.AdminModule;
import com.hwt.domain.entity.admin.vo.AdminModuleVo;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.service.admin.AdminModuleService;
import com.hx.admin.validate.ValidateParam;
import com.hx.core.page.asyn.PageResult;

@RestController
public class AdminModuleController {

	@Autowired
	private AdminModuleService adminModuleService;

	/**
	 * 跳转到模块管理界面
	 * @return
	 */
	@RequestMapping("/admin/module/list")
	public ModelAndView list() {
		return new ModelAndView("admin/module/module-list");
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
	 * @param url
	 *            地址
	 * @param parentId
	 *            父id
	 * @return
	 */
	@RequestMapping("admin/module/query")
	public ResultEntity query(@RequestParam(defaultValue = "10")Integer pageSize, @RequestParam(defaultValue = "0")Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[url]", required = false) String url,
			@RequestParam(name = "paramMap[parentId]", required = false ,defaultValue="0") Integer parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("url", url);
		map.put("parentId", parentId);

		PageResult<AdminModuleVo> pageResult = adminModuleService.queryByMap(map);
		return new ResultEntity(pageResult);
	}
	
	/**
	 * 模块添加
	 * @param adminModule
	 * @return
	 */
	@ValidateParam(field={"name"}, message={"名称不能为空"})
	@RequestMapping("admin/module/insert")
	public ResultEntity insert(AdminModule adminModule){
		adminModuleService.insert(adminModule);
		return new ResultEntity("200","添加成功");
	}
	
	/**
	 * 修改模块
	 * @param adminModule
	 * @param type 1-返回值对象 2-进行更新操作
	 * @return
	 */
	@ValidateParam(field={"id"}, message={"id不能为空"})
	@RequestMapping("admin/module/update")
	public ResultEntity update(AdminModule adminModule,@RequestParam(defaultValue="1")Integer type){
		if (type==1){
			
			AdminModule adminModuleEdit = adminModuleService.queryById(adminModule.getId());
			return new ResultEntity(adminModuleEdit);
		}else{
			adminModuleService.update(adminModule);
			return new ResultEntity("200","修改成功");
		}
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @param type 2-删除 1-恢复
	 * @return
	 */
	@RequestMapping("admin/module/delete")
	public ResultEntity delete(Integer id,Integer type){
		int num = adminModuleService.deleteById(id,type);
		if(type==1){
			return new ResultEntity("200","恢复成功");
		}
		return new ResultEntity("200","删除成功");
	}
	
}
