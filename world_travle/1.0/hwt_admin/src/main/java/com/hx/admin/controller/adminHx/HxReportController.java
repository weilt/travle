package com.hx.admin.controller.adminHx;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.user.Vo.adminHxVo.HxUserReportVo;
import com.hx.admin.base.ResultEntity;
import com.hx.core.page.asyn.PageResult;
import com.hx.system.service.adminHx.service.HxReportService;

/**
 * 淮信报表
 * @author Administrator
 *
 */
@RestController
public class HxReportController {
	
	@Autowired
	private HxReportService hxReportService;
	
	/**
	 * 跳转到用户报表界面
	 * 
	 * @return
	 */
	@RequestMapping("adminHx/userReport/list")
	public ModelAndView list() {
		return new ModelAndView("adminHx/report/userReport-list");
	}
	
	/**
	 * 通过条件查询用户报表
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param startNum
	 *            起始条数数
	 * @param orderBy
	 *            排序
	 * @param account
	 *            账号
	 * @param account_phone
	 *            电话号码
	 * @param registerDate
	 *            注册时间  0-全部 1-当天 2-本周 3-本月
	 * @return
	 */
	@RequestMapping("adminHx/userReport/query")
	public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[account]", required = false) String account,
			@RequestParam(name = "paramMap[registerDate]", required = false,defaultValue = "0") Integer registerDate,
			@RequestParam(name = "paramMap[account_phone]", required = false) String account_phone) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("account", account);
		map.put("account_phone", account_phone);
		map.put("registerDate", registerDate);


		PageResult<HxUserReportVo> pageResult = hxReportService.queryByMap(map);
		return new ResultEntity(pageResult);
	}
}
