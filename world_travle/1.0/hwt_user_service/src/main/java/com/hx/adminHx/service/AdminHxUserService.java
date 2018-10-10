package com.hx.adminHx.service;

import java.util.Map;

import com.hwt.domain.entity.user.Vo.adminHxVo.AdminHxUserFriendVo;
import com.hwt.domain.entity.user.Vo.adminHxVo.AdminHxUserInfo;
import com.hwt.domain.entity.user.Vo.adminHxVo.AdminHxUserVo;
import com.hx.core.page.asyn.PageResult;

/**
 * 后台操作hx用户业务层
 * @author Administrator
 *
 */
public interface AdminHxUserService {
	

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
	PageResult<AdminHxUserVo> queryByMap(Map<String, Object> map);
	
	/**
	 * 通过id查看用户详情
	 * @param user_id
	 * @return
	 */
	AdminHxUserInfo queryUserInfoByUserId(Long user_id);

	/**
	 * 冻结 
	 * @param user_id
	 * @param type 1-解除 2-冻结
	 * @return
	 */
	int frozen(Long user_id, Integer type);

	/**
	 * 通过条件查询好友列表
	 * @param map
	 * @return
	 */
	PageResult<AdminHxUserFriendVo> userFriend_queryByMap(Map<String, Object> map);
	
	

}
