package com.hx.bureau.service.hx;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.bureau.HxBureauModule;
import com.hwt.domain.entity.bureau.HxBureauUser;
import com.hx.core.page.asyn.PageResult;

public interface BureauUserService {

	/**
	 * 通过条件分页查询
	 * @param map
	 * @return
	 */
	PageResult<Map<String, Object>> queryByMap(Map<String, Object> map);

	/**
	 * 用户添加
	 * @param bureauUser
	 */
	void insert(HxBureauUser bureauUser);

	/**
	 * 根据用户id获取
	 * @param bureau_user_id
	 * @return
	 */
	HxBureauUser queryByBureau_user_id(Long bureau_user_id);

	/**
	 * 更新
	 * @param bureauUser
	 */
	void update(HxBureauUser bureauUser);

	
	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @param type
	 *            2-删除 1-恢复
	 * @return
	 */
	int deleteById(Long id, Integer type);

	/**
	 * 重置密码
	 * @param id
	 * @return
	 */
	String resetPassword(Long id);

	/**
	 * 返回所有的模块
	 * @return
	 */
	List<HxBureauModule> queryAll();

	/**
	 * 根据用户id返回所有的模块
	 * @param id
	 * @return
	 */
	List<HxBureauModule> queryAllBybureau_user_id(Long id);

	/**
	 * 授权
	 * @param id
	 * @param rightList
	 */
	void adpower(Long id, Long[] rightList);

	/**
	 * 修改密码
	 * @param oldPassword
	 * @param password
	 */
	void user_editPassWord(String oldPassword, String password, Long bureau_user_id);


	

}
