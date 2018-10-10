package com.hx.admin.controller.adminHx.bureau;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.oss.common.comm.ServiceClient.Request;
import com.hwt.domain.entity.admin.AdminModule;
import com.hwt.domain.entity.admin.vo.AdminModuleVo;
import com.hwt.domain.entity.bureau.HxBureauModule;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.service.cache.AdminUserCache;
import com.hx.admin.service.cache.AdminUserCacheTools;
import com.hx.admin.validate.ValidateParam;
import com.hx.bureau.service.adminHx.HxBureauModuleService;
import com.hx.core.page.asyn.PageResult;

/**
 * 旅行社模块管理
 * @author Administrator
 *
 */
@RestController
public class HxBureauModuleController {
	
	@Autowired
	private HxBureauModuleService hxBureauModuleService;
	
	/**
	 * 跳转到模块管理界面
	 * @return
	 */
	@RequestMapping("adminHx/bureaut/module")
	public ModelAndView list() {
		return new ModelAndView("adminHx/bureaut/module/module-list");
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
	@RequestMapping("adminHx/bureaut/module/query")
	public ResultEntity query(@RequestParam(defaultValue = "10")Integer pageSize, @RequestParam(defaultValue = "0")Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[url]", required = false) String url,
			@RequestParam(name = "paramMap[parentId]", required = false ,defaultValue="0") Integer parentId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("url", url);
		map.put("parentId", parentId);

		PageResult<Map<String, Object>> pageResult = hxBureauModuleService.queryByMap(map);
		return new ResultEntity(pageResult);
	}
	
	/**
	 * 模块添加
	 * @param adminModule
	 * @return
	 */
	@ValidateParam(field={"name"}, message={"名称不能为空"})
	@RequestMapping("adminHx/bureaut/module/insert")
	public ResultEntity insert(HxBureauModule bureauModule,HttpServletRequest request){
		AdminUserCache userCache = AdminUserCacheTools.getSession(request);
		bureauModule.setUser_id(userCache.getAdminUserId());
		hxBureauModuleService.insert(bureauModule);
		return new ResultEntity("200","添加成功");
	}
	
	/**
	 * 修改模块
	 * @param adminModule
	 * @param type 1-返回值对象 2-进行更新操作
	 * @return
	 */
	@ValidateParam(field={"bureau_module_id"}, message={"id不能为空"})
	@RequestMapping("adminHx/bureaut/module/update")
	public ResultEntity update(HxBureauModule bureauModule,@RequestParam(defaultValue="1")Integer type){
		if (type==1){
			
			HxBureauModule bureauModuleEdit = hxBureauModuleService.queryById(bureauModule.getBureau_module_id());
			return new ResultEntity(bureauModuleEdit);
		}else{
			hxBureauModuleService.update(bureauModule);
			return new ResultEntity("200","修改成功");
		}
	}
	
	/**
	 * 通过id删除
	 * @param id
	 * @param type 2-删除 1-恢复
	 * @return
	 */
	@RequestMapping("adminHx/bureaut/module/delete")
	public ResultEntity delete(Integer id,Integer type){
		int num = hxBureauModuleService.deleteById(id,type);
		if(type==1){
			return new ResultEntity("200","恢复成功");
		}
		return new ResultEntity("200","删除成功");
	}
}
