package com.hx.admin.controller.adminHx;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.user.Vo.adminHxVo.AdminHxUserFriendVo;
import com.hwt.domain.entity.user.Vo.adminHxVo.AdminHxUserInfo;
import com.hwt.domain.entity.user.Vo.adminHxVo.AdminHxUserVo;
import com.hx.admin.base.ResultEntity;
import com.hx.adminHx.service.AdminHxUserService;
import com.hx.core.page.asyn.PageResult;

/**
 * 客户端用户操作
 * 
 * @author Administrator 
 *
 */
@RestController
public class HxUserController {
	@Autowired
	private AdminHxUserService adminHxUserService;

	/**
	 * 跳转到用户操作界面
	 * 
	 * @return
	 */
	@RequestMapping("/adminHx/user/list")
	public ModelAndView list() {
		return new ModelAndView("adminHx/user/user-list");
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
	@RequestMapping("adminHx/user/query")
	public ResultEntity query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,
			@RequestParam(name = "paramMap[account]", required = false) String account,
			@RequestParam(name = "paramMap[user_sex]", required = false,defaultValue = "-1") Integer user_sex,
			@RequestParam(name = "paramMap[login_state]", required = false,defaultValue = "0") Integer login_state,
			@RequestParam(name = "paramMap[userType]", required = false,defaultValue = "-1") Integer userType,
			@RequestParam(name = "paramMap[account_phone]", required = false) String account_phone) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("account", account);
		map.put("account_phone", account_phone);
		map.put("user_sex", user_sex);
		map.put("userType", userType);
		map.put("login_state", login_state);

		PageResult<AdminHxUserVo> pageResult = adminHxUserService.queryByMap(map);
		return new ResultEntity(pageResult);
	}
	
	/**
	 * 查看用户详情
	 * @param user_id
	 * @return
	 */
	@RequestMapping("adminHx/user/userInfo")
	public ResultEntity userInfo(Long user_id){
		AdminHxUserInfo adminHxUserInfo = adminHxUserService.queryUserInfoByUserId(user_id);
		return new ResultEntity(adminHxUserInfo);
		
	}
	
	/**
	 * 冻结 
	 * @param user_id
	 * @param type 1-解除 2-冻结
	 * @return
	 */
	@RequestMapping("admin/module/frozen")
	public ResultEntity frozen(Long id, Integer type){
		int num = adminHxUserService.frozen(id, type);
		if(type==1){
			return new ResultEntity("200","解冻成功");
		}
		return new ResultEntity("200","冻结成功");
		
	}
	
	/**
	 * 跳转到用户好友列表
	 * @return
	 */
	@RequestMapping("adminHx/user/userFriend")
	public ModelAndView userFriend(Long id) {
		return new ModelAndView("adminHx/user/userFriend-list").addObject("user_id", id);
	}
	
	/**
	 * 查询好友列表
	 * @param pageSize
	 * @param startNum
	 * @param orderBy
	 * @param user_id
	 * @return
	 */
	@RequestMapping("adminHx/userFriend/query")
	public ResultEntity userFriend_query(@RequestParam(defaultValue = "10") Integer pageSize,
			@RequestParam(defaultValue = "0") Integer startNum, String orderBy,@RequestParam(defaultValue = "0")Long user_id) {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("pageSize", pageSize);
		map.put("startNum", startNum);
		map.put("orderBy", orderBy);
		map.put("user_id", user_id);

		PageResult<AdminHxUserFriendVo> pageResult = adminHxUserService.userFriend_queryByMap(map);
		return new ResultEntity(pageResult);
		
	}


}
