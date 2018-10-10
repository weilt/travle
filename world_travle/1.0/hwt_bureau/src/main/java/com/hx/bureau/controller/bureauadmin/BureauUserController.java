package com.hx.bureau.controller.bureauadmin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.bureau.HxBureauModule;
import com.hwt.domain.entity.bureau.HxBureauUser;
import com.hx.bureau.base.ResultEntity;
import com.hx.bureau.service.cache.BureauUserCache;
import com.hx.bureau.service.cache.BureauUserCacheTools;
import com.hx.bureau.service.hx.BureauUserService;
import com.hx.bureau.validate.ValidateParam;
import com.hx.core.exception.BaseException;
import com.hx.core.logs.annotation.Log;
import com.hx.core.logs.annotation.Log.LogType;
import com.hx.core.page.asyn.PageResult;

/**
 * 后台用户操作
 * 
 * @author Administrator
 *
 */

@RestController
public class BureauUserController {

	
	@Autowired
	private BureauUserService bureauUserService;
	
	/**
	 * 跳转到用户管理界面
	 * @return
	 */
	@RequestMapping("adminbureau/user/list")
	public ModelAndView list() {
		return new ModelAndView("adminbureau/user/user-list");
	}

	/**
	 * 按条件查询
	 * 
	 * @param pageSize
	 *            页面大小
	 * @param startNum
	 *            起始条数数
	 * @param orderBy
	 *            排序
	 * @param user_account
	 *            用户名
	 * @param real_name
	 *            真实姓名
	 * @param sex
	 *            性别 0-全部 2女，1男
	 * @param isenable
	 *            是否 0-全部 1-正常 2-禁用
	 * @param telephone
	 *            电话号码
	 * @return
	 */
	@RequestMapping("adminbureau/user/query")
	public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[bureau_user_account]", required = false) String bureau_user_account,
			@RequestParam(name = "paramMap[real_name]", required = false) String real_name,
			@RequestParam(name = "paramMap[is_delete]", required = false) Integer is_delete,
			@RequestParam(name = "paramMap[phone]", required = false) String phone,HttpServletRequest request) {
		BureauUserCache bureauUserCache = BureauUserCacheTools.getSession(request);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("bureau_user_account", bureau_user_account);
		map.put("real_name", real_name);
		map.put("is_delete", is_delete);
		map.put("phone", phone);
		map.put("bureau_id", bureauUserCache.getBureau_id());
		
		
		PageResult<Map<String, Object>> pageResult = bureauUserService.queryByMap(map);
		return new ResultEntity(pageResult );
	}

	/**
	 * 用户添加
	 * @param adminUser
	 * @return
	 */
	@Log(logType = LogType.OPERATION, dec = "用户添加")
	@ValidateParam(field = { "bureau_user_account","real_name","password" }, message = { "用户名不能为空","真实姓名不能为空" })
	@RequestMapping("adminbureau/user/insert")
	public ResultEntity insert(HxBureauUser bureauUser,HttpServletRequest request,String conpassword) {
		String password = bureauUser.getPassword().trim();
		if (!password.equals(conpassword)){
			throw new RuntimeException("密码与确认密码不一致");
		}
		if(password.length()<6||password.length()>16){
			throw new RuntimeException("密码长度应该在6到16个字符");
		}
		bureauUser.setPassword(password);
		BureauUserCache bureauUserCache = BureauUserCacheTools.getSession(request);
		bureauUser.setBureau_id(bureauUserCache.getBureau_id());
		bureauUserService.insert(bureauUser);
		return new ResultEntity("200","添加成功");
	}

	/**
	 * 修改用户返回
	 * 
	 * @param adminuser
	 * @return
	 */
	@ValidateParam(field = { "bureau_user_id" }, message = { "bureau_user_id不能为空" })
	@RequestMapping("adminbureau/user/update_query")
	public ResultEntity update_query(HxBureauUser bureauUser) {
		
			HxBureauUser bureauUserEdit = bureauUserService.queryByBureau_user_id(bureauUser.getBureau_user_id());
			
			return new ResultEntity(bureauUserEdit);

		
	}
	/**
	 * 修改用户
	 * 
	 * @param adminuser
	 * @return
	 */
	@Log(logType = LogType.OPERATION, dec = "修改用户")
	@ValidateParam(field = { "bureau_user_id" }, message = { "bureau_user_id不能为空" })
	@RequestMapping("adminbureau/user/update")
	public ResultEntity update(HxBureauUser bureauUser) {
	
			bureauUserService.update(bureauUser);
			return new ResultEntity("200","修改成功");
			
		
	}

	/**
	 * 禁用用户
	 * 
	 * @param id
	
	 * @return
	 */
	@Log(logType = LogType.OPERATION, dec = "禁用用户")
	@ValidateParam(field = { "id" }, message = { "id不能为空" })
	@RequestMapping("adminbureau/user/delete")
	public ResultEntity delete(Long id) {
		bureauUserService.deleteById(id, 2);
		return new ResultEntity("200","删除成功");
	}
	/**
	 * 恢复用户
	 * 
	 * @param id
	 * @return
	 */
	@Log(logType = LogType.OPERATION, dec = "恢复用户")
	@ValidateParam(field = { "id" }, message = { "id不能为空" })
	@RequestMapping("adminbureau/user/recovery")
	public ResultEntity recovery(Long id) {
		bureauUserService.deleteById(id, 1);
		
		return new ResultEntity("200","恢复成功");
		
	}
	
	/**
	 * 重置用户密码
	 * @param id
	 * @return
	 */
	@Log(logType = LogType.OPERATION, dec = "重置用户密码")
	@ValidateParam(field = { "id" }, message = { "id不能为空" })
	@RequestMapping("adminbureau/user/resetPassword")
	public ResultEntity resetPassword(Long id){
		String resetPassword = bureauUserService.resetPassword(id);
		return new ResultEntity("200","密码重置成功，密码为"+resetPassword);
	}
	
	/**
	 * 用户授权查询
	 * @param id	角色id
	 * @return
	 */
	@RequestMapping("adminbureau/user/adpower_qurey")
	public ResultEntity adpower_qurey(Long id ,Long[] rightList){
		
		
		List<HxBureauModule> bureauModules = bureauUserService.queryAll();
		List<HxBureauModule> bureauModulesTobureau_user = bureauUserService.queryAllBybureau_user_id(id);
		
		Map<String, Object> map = new HashMap<>();
		map.put("bureauModules", bureauModules);
		map.put("bureauModulesTobureau_user", bureauModulesTobureau_user);
		return new ResultEntity(map);
		
	}
	
	/**
	 * 用户授权
	 * @param id	角色id
	 * @param type	1-返回角色权限信息 2-进行更新操作
	 * @return
	 */
	@Log(logType = LogType.OPERATION, dec = "用户授权")
	@RequestMapping("adminbureau/user/adpower")
	public ResultEntity adpower(Long id ,Long[] rightList){
		
		
			bureauUserService.adpower(id,rightList);
			
			return new ResultEntity("200","授权成功");
		
	}
}
