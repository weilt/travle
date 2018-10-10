package com.hx.admin.controller.adminHx.information;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.mg.information.HwInformation;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.validate.ValidateParam;
import com.hx.core.page.asyn.PageResult;
import com.hx.information.service.adminHx.HXInformationService;

/**
 * 资讯管理
 * @author Administrator
 *
 */
@RestController
public class HwInformationController {
	@Autowired
	private HXInformationService hxInformationService;
	
	/**
	 * 跳转到资讯管理
	 * @return
	 */
	@RequestMapping("adminHx/information/list")
	public ModelAndView information_list(){
		
		return new ModelAndView("adminHx/information/information-list");
		
	}
	
	/**
	 * 质询查询
	 * @param pageSize    页面大小
	 * @param currentPage 页码
	 * @param orderBy	  排序
	 * @param titel		 标题
	 * @return
	 */
	@RequestMapping("adminHx/information/query")
	public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0",name="currentPage") Integer currentPage, String orderBy,
			@RequestParam(name = "paramMap[titel]", required = false) String titel) {
		
		Map<String, Object> map = new HashMap<>();
		map.put("pageSize", pageSize);
		map.put("currentPage", currentPage);
		map.put("orderBy", orderBy);
		map.put("titel", titel);
		
		PageResult<Map<String, Object>> pageResult = hxInformationService.query(map);
		
		return new ResultEntity(pageResult);
		
	}
	
	/**
	 * 添加
	 * @param tilte				标题
	 * @param content			内容
	 * @param information_type	资讯分类  0-为默认分类
	 * @param images			列表图片展示
	 * @param is_display		是否显示 1-是 0-否
	 * @return
	 */
	@ValidateParam(field={"content","tilte"})
	@RequestMapping("adminHx/information/insert")
	public ResultEntity insert(String tilte, String content,@RequestParam(defaultValue="0")Long information_type,
			String images,@RequestParam(defaultValue="1")Integer is_display)  throws Exception {
		
		HwInformation hwInformation = new HwInformation();
		hwInformation.setAuthor("探路科技");
		hwInformation.setComment_num(0l);
		hwInformation.setContent(content);
		hwInformation.setCreate_time(System.currentTimeMillis());
		hwInformation.setGood_num(0l);
		hwInformation.setImages(images);
		hwInformation.setInformation_type(information_type);
		hwInformation.setIs_display(is_display);
		hwInformation.setIs_hide(0);
		hwInformation.setLook_num(0l);
		hwInformation.setSource("探路科技");
		hwInformation.setTilte(tilte);
		hxInformationService.insert(hwInformation);
		return new ResultEntity("200","添加成功");
	}
	
	/**
	 * 通过id查询详情
	 */
	@ValidateParam(field={"information_id"})
	@RequestMapping("adminHx/information/queryInfo")
	public ResultEntity queryInfo(Long information_id)  throws Exception {
		
		Map<String, Object> map = hxInformationService.queryInfo(information_id);
		return new ResultEntity(map);
	}
	
	/**
	 * 修改
	 * @param information_id	id
	 * @param tilte				标题
	 * @param content			内容
	 * @param information_type	资讯分类  0-为默认分类
	 * @param images			列表图片展示
	 * @param is_display		是否显示 1-是 0-否
	 * @return
	 */
	@ValidateParam(field={"information_id","content","tilte"})
	@RequestMapping("adminHx/information/update")
	public ResultEntity update(Long information_id, String tilte, String content,@RequestParam(defaultValue="0")Long information_type,
			String images,@RequestParam(defaultValue="1")Integer is_display)  throws Exception {
		
		
		return new ResultEntity("200","修改成功");
	}
}
