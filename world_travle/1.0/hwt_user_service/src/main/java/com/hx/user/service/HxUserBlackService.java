package com.hx.user.service;

import java.util.List;

import com.hwt.domain.entity.user.Vo.BlackFriendUserVo;


public interface HxUserBlackService {

	/**
	 * 获取黑名单列表
	 * @param userId
	 * @return
	 */
	List<BlackFriendUserVo> getBlackfriends(Long userId);

	/**
	 * 加入黑名单
	 * @param userId
	 * @param friendId
	 * @param remark
	 * @param im_id 
	 */
	void defriend(Long userId, Long friendId, String remark, String im_id) throws Exception;

	/**
	 * 移除黑名单
	 * @param userId
	 * @param friendId
	 * @param im_id 
	 * @throws Exception
	 */
	void removeDefriend(Long userId, Long friendId, String im_id) throws Exception;

}
