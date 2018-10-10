package com.hx.admin.controller.adminHx;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.mg.scenic.ScenicSpot;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.validate.ValidateParam;
import com.hx.core.page.asyn.PageResult;
import com.hx.scenic.adminHx.AdminScenicSpotService;

/**
 * 景区模块管理
 * @author Administrator
 *
 */
@RestController
public class HxScenicSpotController {
	
	@Autowired
	private AdminScenicSpotService adminScenicSpotService;
	/**
	 * 旅游模块管理操作界面
	 * 
	 * @return
	 */
	@RequestMapping("/adminHx/scenic/list")
	public ModelAndView list() {
		return new ModelAndView("adminHx/scenic/scenic-list");
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
	 * @param account
	 *            账号
	 * @param account_phone
	 *            电话号码
	 * @return
	 */
	@RequestMapping("adminHx/scenic/query")
	public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0",name="currentPage") Integer currentPage, String orderBy,
			@RequestParam(name = "paramMap[filed]", required = false) String filed,
			@RequestParam(name = "paramMap[city]", required = false) String city) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("pageSize", pageSize);
		map.put("currentPage", currentPage);
		map.put("orderBy", orderBy);
		
		map.put("filed", filed);
		map.put("city", city);

		PageResult<Map<String,Object>> pageResult = adminScenicSpotService.queryByMap(map);
		return new ResultEntity(pageResult);
	}
	
	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @param type
	 *            0-删除 1-恢复
	 * @return
	 */
	@ValidateParam(field = { "id" }, message = { "id不能为空" })
	@RequestMapping("adminHx/scenic/delete")
	public ResultEntity delete(Long id, Integer type) {
		adminScenicSpotService.deleteById(id, type);
		
		if(type==0){
			return new ResultEntity("200","恢复成功");
		}
		return new ResultEntity("200","隐藏成功");
	}
	/**
	 * 通过id推荐
	 * 
	 * @param id
	 * @param type
	 *            0-不推荐 1-推荐
	 * @return
	 */
	@ValidateParam(field = { "id" }, message = { "id不能为空" })
	@RequestMapping("adminHx/scenic/isRecommend")
	public ResultEntity recommend(Long id, Integer type) {
		adminScenicSpotService.recommendById(id, type);
		
		if(type==1){
			return new ResultEntity("200","推荐成功");
		}
		return new ResultEntity("200","取消推荐成功");
	}
	/**
	 * 通过id热门
	 * 
	 * @param id
	 * @param type
	 *            0-不热门 1-热门
	 * @return
	 */
	@ValidateParam(field = { "id" }, message = { "id不能为空" })
	@RequestMapping("adminHx/scenic/isHot")
	public ResultEntity isHot(Long id, Integer type) {
		adminScenicSpotService.isHotById(id, type);
		
		if(type==1){
			return new ResultEntity("200","热门成功");
		}
		return new ResultEntity("200","取消热门成功");
	}
	
	/**
	 * 通过id查询详情
	 * 
	 * @param spotId
	 * @return
	 */
	@ValidateParam(field = { "spotId" }, message = { "spotId不能为空" })
	@RequestMapping("adminHx/scenic/scenicInfo")
	public ResultEntity scenicInfo(Long spotId) {
		Map<String, Object> map = adminScenicSpotService.queryScenicInfoBySpotId(spotId);
		return new ResultEntity(map);
		
	}
	/**
	 * 修改景点
	 * @param scenicSpot
	 * @param type 1-返回值对象 2-进行更新操作
	 * @return
	 */
	@ValidateParam(field={"spotId"}, message={"spotId不能为空"})
	@RequestMapping("adminHx/scenic/update")
	public ResultEntity update(ScenicSpot scenicSpot,@RequestParam(defaultValue="1")Integer type){
		if (type==1){
			
			Map<String, Object> map = adminScenicSpotService.queryScenicInfoBySpotId(scenicSpot.getSpotId());
			return new ResultEntity(map);
		}else{
			adminScenicSpotService.update(scenicSpot);
			return new ResultEntity("200","修改成功");
		}
	}
	

	/**
	 * 景点添加
	 * @param adminRole
	 * @return
	 * @throws Exception 
	 */
	@ValidateParam(field={"spotName","country","city","brief","subtitle","tags","rank","openingHours","location","geoPoint"}/*, message={"景点名不能为空","2","3","4","5","6","7","8","9","10"}*/)
	@RequestMapping("adminHx/scenic/insert")
	public ResultEntity insert(ScenicSpot scenicSpot) throws Exception{
		adminScenicSpotService.insert(scenicSpot);
		return new ResultEntity("200","添加成功");
	}
	
	
	
}
