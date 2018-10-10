package com.hx.admin.controller.adminHx;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.version.HxVersion;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.validate.ValidateParam;
import com.hx.core.page.asyn.PageResult;
import com.hx.system.service.adminHx.service.AdminHxVersionService;

/**
 * 版本管理
 * @author Administrator
 *
 */
@RestController
public class HxVersionController {
	
	@Autowired
	private AdminHxVersionService adminHxVersionService;
	
	/**
	 * 跳转到版本管理操作界面
	 * 
	 * @return
	 */
	@RequestMapping("adminHx/version/list")
	public ModelAndView list() {
		return new ModelAndView("adminHx/version/version-list");
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
	 * @param version_name
	 *            版本名称
	 * @param version_number
	 *            版本号
	 * @return
	 */
	@RequestMapping("adminHx/version/query")
	public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[version_name]", required = false) String version_name,
			@RequestParam(name = "paramMap[version_number]", required = false) String version_number ){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("version_name", version_name);
		map.put("version_number", version_number);
		
		PageResult<HxVersion> pageResult = adminHxVersionService.queryByMap(map);
		return new ResultEntity(pageResult);
	}

	/**
	 * 版本添加
	 * @param hxVersion
	 * @return
	 */
	@ValidateParam(field={"version_number","version_number","minimun"}, message={"版本号不能为空"})
	@RequestMapping("adminHx/version/insert")
	public ResultEntity insert(HxVersion hxVersion){
		adminHxVersionService.insert(hxVersion);
		return new ResultEntity("200","添加成功");
	}
	
	/**
	 * 修改版本
	 * @param hxVersion
	 * @param type 1-返回值对象 2-进行更新操作
	 * @return
	 */
	@ValidateParam(field={"id"}, message={"id不能为空"})
	@RequestMapping("adminHx/version/update")
	public ResultEntity update(HxVersion hxVersion,@RequestParam(defaultValue="1")Integer type){
		if (type==1){
			
			HxVersion hxVersionEdit = adminHxVersionService.queryById(hxVersion.getId());
			return new ResultEntity(hxVersionEdit);
		}else{
			adminHxVersionService.update(hxVersion);
			return new ResultEntity("200","修改成功");
		}
	}
	
	/**
	 * 查看版本详情
	 * @param hxVersion
	 * @return
	 */
	@ValidateParam(field={"id"}, message={"id不能为空"})
	@RequestMapping("adminHx/version/versionInfo")
	public ResultEntity versionInfo(Long id){
	
			
		HxVersion version = adminHxVersionService.queryById(id);
		
		return new ResultEntity(version);
		
	}
}
