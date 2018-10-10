package com.hx.admin.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.admin.AdminUser;
import com.hwt.domain.entity.admin.vo.AdminRoleToUserVo;
import com.hwt.domain.entity.admin.vo.AdminUserVo;
import com.hwt.domain.mapper.admin.AdminSystemconfigMapper;
import com.hx.admin.base.ResultEntity;
import com.hx.admin.service.admin.AdminRoleService;
import com.hx.admin.service.admin.AdminUserService;
import com.hx.admin.service.utils.AdminConfigKey;
import com.hx.admin.validate.ValidateParam;
import com.hx.core.page.asyn.PageResult;

/**
 * 后台用户操作
 * 
 * @author Administrator
 *
 */
@RestController
public class AdminUserController {

	@Autowired
	private AdminUserService adminUserService;
	
	@Autowired
	private AdminRoleService adminRoleService;
	
	
	/**
	 * 跳转到用户管理界面
	 * @return
	 */
	@RequestMapping("/admin/user/list")
	public ModelAndView list() {
		return new ModelAndView("admin/user/user-list");
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
	@RequestMapping("admin/user/query")
	public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[user_account]", required = false) String user_account,
			@RequestParam(name = "paramMap[real_name]", required = false) String real_name,
			@RequestParam(name = "paramMap[telephone]", required = false) String telephone,
			@RequestParam(defaultValue = "0", name = "paramMap[sex]") Integer sex,
			@RequestParam(defaultValue = "0", name = "paramMap[isenable]") Integer isenable) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("user_account", user_account);
		map.put("real_name", real_name);
		map.put("sex", sex);
		map.put("isenable", isenable);
		map.put("telephone", telephone);

		PageResult<AdminUserVo> pageResult = adminUserService.queryByMap(map);
		return new ResultEntity(pageResult);
	}

	/**
	 * 用户添加
	 * @param adminUser
	 * @return
	 */
	@ValidateParam(field = { "user_account","role_id" }, message = { "用户名不能为空","请选择角色" })
	@RequestMapping("admin/user/insert")
	public ResultEntity insert(AdminUser adminUser) {
		adminUserService.insert(adminUser);
		return new ResultEntity("200","添加成功");
	}

	/**
	 * 修改用户
	 * 
	 * @param adminuser
	 * @param type
	 *            1-返回值对象 2-进行更新操作
	 * @return
	 */
	@ValidateParam(field = { "user_id" }, message = { "user_id不能为空" })
	@RequestMapping("admin/user/update")
	public ResultEntity update(AdminUser adminUser, @RequestParam(defaultValue = "1") Integer type) {
		if (type == 1) {
			Map<String, Object> map = new HashMap<>();
			AdminUser adminUserEdit = adminUserService.queryById(adminUser.getUser_id());
			map.put("adminUserEdit", adminUserEdit);
			List<AdminRoleToUserVo> queryToUser = adminRoleService.queryToUser();
			map.put("roles", queryToUser);
			return new ResultEntity(map);

		} else {

			adminUserService.update(adminUser);
			return new ResultEntity("200","修改成功");

		}
	}

	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @param type
	 *            2-删除 1-恢复
	 * @return
	 */
	@ValidateParam(field = { "id" }, message = { "id不能为空" })
	@RequestMapping("admin/user/delete")
	public ResultEntity delete(Long id, Integer type) {
		int num = adminUserService.deleteById(id, type);
		if(type==1){
			return new ResultEntity("200","恢复成功");
		}
		return new ResultEntity("200","删除成功");
	}
	
	/**
	 * 重置用户密码
	 * @param id
	 * @return
	 */
	@ValidateParam(field = { "id" }, message = { "id不能为空" })
	@RequestMapping("admin/user/resetPassword")
	public ResultEntity resetPassword(Long id){
		String resetPassword = adminUserService.resetPassword(id);
		return new ResultEntity("200","密码重置成功，密码为"+resetPassword);
	}
	

}
