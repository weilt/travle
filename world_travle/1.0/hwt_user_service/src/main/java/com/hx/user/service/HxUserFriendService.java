package com.hx.user.service;

import java.util.List;

import com.hwt.domain.entity.user.Vo.CommunicationListFriendVo;
import com.hwt.domain.entity.user.Vo.FriendInfoVo;
import com.hwt.domain.entity.user.Vo.FriendUserVo;
import com.hwt.domain.entity.user.Vo.InitializationFriendVo;


public interface HxUserFriendService {

	/**
	 * 获取好友列表
	 * @param userId
	 * @return
	 */
	List<FriendUserVo> getFriends(Long userId);

	/**
	 * 获取好友质料详情 通过云信id
	 * @param im_id
	 * @param userId
	 * @return
	 */
	FriendInfoVo getFriendInfo(String im_id, Long userId);

	/**
	 * 删除好友关系
	 * @param userId
	 * @param friendId
	 * @param im_id 
	 */
	void deleteFriend(Long userId, Long friendId, String im_id)  throws Exception;

	/**
	 * 修改好友备注
	 * @param userId
	 * @param friendId
	 * @param friendRemark
	 */
	void updateFriendRemark(Long userId, Long friendId, String friendRemark);

	/**
	 * 根据账号或者电话号码搜索用户
	 * @param field
	 * @param userId
	 * @return
	 */
	FriendInfoVo searchAccountByField(String field, Long userId);

	/**
	 * 通过好友id查询好友详情
	 * @param friend_id
	 * @param user_id
	 * @return
	 */
	FriendInfoVo searchAccountByFriendId(Long friend_id, Long user_id);

	/**
	 * 初始化返回好友对我的 删除和拉黑 了 的im_id
	 * @param user_id
	 * @return
	 */
	InitializationFriendVo initializationFriendToMe(Long user_id);
	
	 /**
     * 通过电话号码获取获取注册过淮信的用户
     * @param phones  - 电话号码 - Y
     * @return
     */
	List<CommunicationListFriendVo> findHxUserByPhone(String[] phones);

}
