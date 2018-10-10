package com.hx.bureau.controller.bureauadmin;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hx.bureau.base.ResultEntity;
import com.hx.bureau.service.hx.BureauLogService;
import com.hx.bureau.util.BaseController;
import com.hx.core.page.asyn.PageResult;

/**
 * 日志
 * @author Administrator
 *
 */
@RestController
public class BureauLogController extends BaseController{
	
	@Autowired
	private BureauLogService bureauLogService;
	
	/**
	 * 跳转到日志
	 */
	@RequestMapping("bureau/log/list")
	public ModelAndView list() {
		return new ModelAndView("bureau/log/log-list");
	}
	
	
	/**
	 * 日志查询
	 */
	@RequestMapping("bureau/log/query")
	public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "1") Integer currentPage, String orderBy,
			@RequestParam(name = "paramMap[line_name]", required = false) String line_name) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("currentPage", currentPage);
		map.put("orderBy", orderBy);
		map.put("line_name", line_name);
		map.put("bureau_id", sessionUser().getBureau_id());
		PageResult<Map<String, Object>> pageResult = bureauLogService.query(map);
		return new ResultEntity(pageResult);
	}
}
