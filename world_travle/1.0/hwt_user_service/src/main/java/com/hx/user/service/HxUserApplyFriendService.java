package com.hx.user.service;

import java.util.List;

import com.hwt.domain.entity.user.Vo.FriendApplyVo;


public interface HxUserApplyFriendService {

	 /**
     * 获取用户的好友申请列表
     * @param userId 用户ID
     * @return
     */
	List<FriendApplyVo> getApplyUsers(Long userId);

	/**
	 * 申请好友
	 * @param userId 用户id
	 * @param friendId  朋友id
	 * @param remark 申请备注
	 * @param imToken 通信token
	 * @param friendRemark 好友备注
	 * @param friend_see_me_space 是否让朋友看我的朋友圈吧 0-否  1-是 默认是1
	 */
	void apply(Long userId, Long friendId, String remark, String imToken, String friendRemark, Byte friend_see_me_space) throws Exception;

	 /**
     * 同意好友申请
     * @param userId             用户ID - Y
     * @param friendId          好友ID - Y
     * @param friendRemark      好友备注
     * @param friendSeeMeSpace  是否让好友查看我的朋友圈 0-是 1-不允许
     * @param remark            备注
     * @return
     */
	void agree(Long userId, Long friendId, String friendRemark, int friendSeeMeSpace, String remark, String im_id) throws Exception;

	/**
	 * 删除申请
	 * @param user_id
	 * @param friend_id
	 */
	void applyRefuse(Long user_id, Long friend_id);

	
	
}
