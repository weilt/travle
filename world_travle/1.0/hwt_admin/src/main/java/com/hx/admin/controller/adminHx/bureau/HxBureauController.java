package com.hx.admin.controller.adminHx.bureau;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.bureau.HwTravelBureau;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.validate.ValidateParam;
import com.hx.bureau.service.Vo.PageResultHxBureauInfo;
import com.hx.bureau.service.adminHx.HxBureauService;
/**
 * 旅行社管理
 * @author Administrator
 *
 */
@RestController
public class HxBureauController {
	
	@Autowired
	private HxBureauService hxBureauService;
	/**
	 * 跳转到导游列表
	 * 
	 * @return
	 */
	@RequestMapping("adminHx/bureaut/list")
	public ModelAndView bureaut_list() {
		return new ModelAndView("adminHx/bureaut/bureaut-list");
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
	@RequestMapping("adminHx/bureaut/query")
	public ResultEntity query_adopt(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[company_name]", required = false) String company_name) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("company_name", company_name);


		PageResultHxBureauInfo pageResult = hxBureauService.queryByMap(map);
		return new ResultEntity(pageResult);
	}
	
	/**
	 * 申请验证
	 * @param bureau_id
	 * @param status	审核状态  0 待处理，1 已完成，2 已拒绝
	 * @param msg	审核通过，审核未通过
	 * @param reason	原因
	 * @param user_id	申请人id
	 * @return
	 */
	@ValidateParam(field = { "bureau_id" }, message = { "id不能为空" })
	@RequestMapping("adminHx/bureaut/verification")
	public ResultEntity bureaut_verification(Long bureau_id,Integer status,String msg,String reason) throws Exception{
		hxBureauService.bureaut_verification(bureau_id,status,reason);
		
		return new ResultEntity("200",msg);
	}
	
	/**
	 * 查看详情
	 * @param bureau_id
	 * @return
	 */
	@ValidateParam(field = { "bureau_id" }, message = { "id不能为空" })
	@RequestMapping("adminHx/bureaut/info")
	public ResultEntity query_info(Long bureau_id) {
		HwTravelBureau map = hxBureauService.query_infoById(bureau_id);
		if (map==null){
			throw new RuntimeException("不存在该用户");
		}
		return new ResultEntity(map);
	}
}
