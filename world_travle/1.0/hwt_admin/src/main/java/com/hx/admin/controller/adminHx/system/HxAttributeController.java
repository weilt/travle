package com.hx.admin.controller.adminHx.system;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.system.HwAttribute;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.validate.ValidateParam;
import com.hx.core.page.asyn.PageResult;
import com.hx.system.service.adminHx.service.HxAttributeService;

@RestController
public class HxAttributeController {
	
	@Autowired
	private HxAttributeService attributeService;
	
	/**
	 * 跳转到线路分类
	 * @return
	 */
	@RequestMapping("adminHx/line_type/list")
	public ModelAndView line_type_list() {
		return new ModelAndView("adminHx/system/line_type/line_type-list");
	}
	/**
	 * 跳转到线路服务
	 * @return
	 */
	@RequestMapping("adminHx/line_service/list")
	public ModelAndView line_service_list() {
		return new ModelAndView("adminHx/system/line_service/line_service-list");
	}

	/**
	 * 查询
	 * @param pageSize
	 * @param startNum
	 * @param orderBy
	 * @param real_name
	 * @param service_area_city_name
	 * @return
	 */
	@RequestMapping("adminHx/{key}/query")
	public ResultEntity query(@PathVariable(name="key") String attribute_key, @RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[attribute_name]", required = false) String attribute_name){
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("attribute_name", attribute_name);
		map.put("attribute_key", attribute_key);


		PageResult<Map<String, Object>> pageResult = attributeService.queryByMap(map);
		return new ResultEntity(pageResult);
		
	}
	
	/**
	 * 属性添加
	 * @param adminModule
	 * @return
	 */
	@ValidateParam(field={"attribute_name"}, message={"名称不能为空"})
	@RequestMapping("adminHx/{key}/insert")
	public ResultEntity insert(@PathVariable(name="key") String attribute_key,HwAttribute attribute){
		attribute.setAttribute_key(attribute_key);
		attributeService.insert(attribute);
		return new ResultEntity("200","添加成功");
	}
	
	/**
	 * 属性修改
	 * @param 
	 * @param type 1-返回值对象 2-进行更新操作
	 * @return
	 */
	@ValidateParam(field={"attribute_id"}, message={"id不能为空"})
	@RequestMapping("adminHx/{key}/update")
	public ResultEntity update(@PathVariable(name="key") String attribute_key,@RequestParam(defaultValue="1")Integer type,HwAttribute attribute){
		if (type==1){
			HwAttribute attributeEdit = attributeService.queryById(attribute.getAttribute_id());
			return new ResultEntity(attributeEdit);
		}else{
			attribute.setAttribute_key(null);
			attributeService.update(attribute);
			return new ResultEntity("200","修改成功");
		}
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @param type 2-删除 1-恢复
	 * @return
	 */
	@ValidateParam(field={"id"}, message={"id不能为空"})
	@RequestMapping("adminHx/{key}/delete")
	public ResultEntity delete(@PathVariable(name="key") String attribute_key,@RequestParam(name="id")Integer attribute_id,Integer type){
		attributeService.deleteById(attribute_id,type);
		if(type==1){
			return new ResultEntity("200","恢复成功");
		}
		return new ResultEntity("200","删除成功");
	}
}
