package com.hx.admin.controller.adminHx.cicerone;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.cicerone.HxCiceroneInfo;
import com.hwt.domain.entity.user.Vo.adminHxVo.HxUserReportVo;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.validate.ValidateParam;
import com.hx.adminHx.service.AdminHxCiceroneService;
import com.hx.adminHx.service.vo.PageResultHxCiceroneInfo;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.utils.ObjectHelper;

/**
 * 导游管理
 * @author Administrator
 *
 */
@RestController
public class HxCiceroneController {
	
	@Autowired
	private AdminHxCiceroneService adminHxCiceroneService;
	
	/**
	 * 跳转到导游列表
	 * 
	 * @return
	 */
	@RequestMapping("adminHx/cicerone_adopt/list")
	public ModelAndView cicerone_adopt_list() {
		return new ModelAndView("adminHx/cicerone/cicerone_adopt-list");
	}
	
	/**
	 * 通过条件查询导游
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param startNum
	 *            起始条数数
	 * @param orderBy
	 *            排序
	 * @param real_name
	 *            导游名字
	 * @param service_area_city_name
	 *            服务城市

	 * @return
	 */
	@RequestMapping("adminHx/cicerone_adopt/query")
	public ResultEntity query_adopt(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[real_name]", required = false) String real_name,
			@RequestParam(name = "paramMap[service_area_city_name]", required = false) String service_area_city_name) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("status", 2);
		map.put("real_name", real_name);
		map.put("service_area_city_name", service_area_city_name);


		PageResultHxCiceroneInfo pageResult = adminHxCiceroneService.queryByMap(map);
		return new ResultEntity(pageResult);
	}
	
	/**
	 * 跳转到导游申请列表
	 * 
	 * @return
	 */
	@RequestMapping("adminHx/cicerone_apply/list")
	public ModelAndView cicerone_list() {
		return new ModelAndView("adminHx/cicerone/cicerone_apply-list");
	}
	
	/**
	 * 通过条件查询导游
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param startNum
	 *            起始条数数
	 * @param orderBy
	 *            排序
	 * @param real_name
	 *            导游名字
	 * @param feild
	 *            走吧号或者手机号

	 * @return
	 */
	
	@RequestMapping("adminHx/cicerone_apply/query")
	public ResultEntity query_apply(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[status]", defaultValue="0") Integer status,
			@RequestParam(name = "paramMap[real_name]", required = false) String real_name,
			@RequestParam(name = "paramMap[feild]", required = false) String feild) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("status", status);
		map.put("real_name", real_name);
//		if (ObjectHelper.isNotEmpty(feild)){
//			if (ObjectHelper.isPhoneLegal(feild)){
//				map.put("account_phone", feild);
//			}else{
//				map.put("account", feild);
//			}
//		}
		map.put("feild", feild);
		PageResultHxCiceroneInfo pageResult = adminHxCiceroneService.queryByMap(map);
		return new ResultEntity(pageResult);
	}
	
	/**
	 * 申请验证
	 * @param status	审核状态 默认1 未审核 2-通过 3-未通过
	 * @param msg	审核通过，审核未通过
	 * @param reason	原因
	 * @param user_id	申请人id
	 * @return
	 */
	@ValidateParam(field = { "user_id" }, message = { "id不能为空" })
	@RequestMapping("adminHx/cicerone_apply/verification")
	public ResultEntity cicerone_verification(Integer status,String msg,String reason,Long user_id) throws Exception{
		adminHxCiceroneService.cicerone_verification(status,reason,user_id);
		
		return new ResultEntity("200",msg);
	}
	
	/**
	 * 查看详情
	 * @param id
	 * @return
	 */
	@ValidateParam(field = { "user_id" }, message = { "id不能为空" })
	@RequestMapping("adminHx/cicerone/info")
	public ResultEntity query_info(Long user_id) {
		Map<String, Object> hxCiceroneInfo = adminHxCiceroneService.query_infoById(user_id);
		if (hxCiceroneInfo==null){
			throw new RuntimeException("不存在该用户");
		}
		return new ResultEntity(hxCiceroneInfo);
	}
}
