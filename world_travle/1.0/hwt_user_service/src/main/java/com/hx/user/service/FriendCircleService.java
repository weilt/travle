package com.hx.user.service;

import java.util.List;

import com.hwt.domain.entity.mg.user.FriendCircle;
import com.hwt.domain.entity.mg.user.FriendCircleComment;
import com.hwt.domain.entity.user.Vo.FriendCircleVo;
import com.hwt.domain.entity.user.Vo.LoginVo;

public interface FriendCircleService {

	/**
	 * 发表朋友圈
	 * @param friendCircle
	 * @param loginVo 
	 */
	void friendCircle_insert(FriendCircle friendCircle, LoginVo loginVo) throws Exception;

	/**
	 * 删除朋友圈
	 * @param user_id 用户id
	 * @param friend_circle_id  朋友圈id
	 */
	void friendCircle_delete(Long user_id, Long friend_circle_id);

	/**
	 * 查看朋友圈
	 * @param user_id
	 * @param last_friend_circle_id  	页码  默认1
	 * @param pageSize		每页大小 默认10
	 * @return
	 * @throws Exception
	 */
	List<FriendCircleVo> friendCircle_query(Long user_id, Long last_friend_circle_id, Integer pageSize);

	/**
	 * 查看好友的朋友圈
	 * @param user_id
	 * @param last_friend_circle_id  最后一条朋友圈id
	 * @param pageSize
	 * @param friend_id
	 * @return
	 */
	List<FriendCircleVo> friendCircle_query_friend(Long user_id, Long last_friend_circle_id, Integer pageSize, Long friend_id);

	/**
	 * 查看自己的朋友圈
	 * @param user_id
	 * @param last_friend_circle_id	最后一条朋友圈id
	 * @param pageSize
	 * @return
	 */
	List<FriendCircleVo> friendCircle_query_own(Long user_id, Long last_friend_circle_id, Integer pageSize);

	/**
	 * 朋友圈评论
	 * @param friendCircleComment
	 */
	FriendCircleComment friendCircle_comment(FriendCircleComment friendCircleComment) throws Exception;

	/**
	 * 删除朋友圈评论
	 * @param user_id
	 * @param comment_id
	 * @param friend_circle_id
	 */
	void friendCircle_comment_delete(Long user_id, Long comment_id, Long friend_circle_id);

	  /**
     * 是否让朋友看我的朋友圈
     * @param user_id  
     * @param friend_see_me_space	是否让朋友看我的朋友圈吧 0-否  1-是 默认是1
     * @param friend_id		朋友id
     * @return
     */
	int let_friend_see_friendCircle(Long user_id, Integer friend_see_me_space, Long friend_id);

	
	/**
     * 是否看朋友的朋友圈
     * @param user_id
     * @param me_see_friend_space	我是否能看朋友的朋友圈  0-否  1-能 默认能
     * @param friend_id		朋友id
     * @return
     */
	int see_friend_friendCircle(Long user_id, Integer me_see_friend_space, Long friend_id);
	
	
	 /**
     * 好友详情界面的朋友圈
     * @param user_id
     * @param friend_id
     * @return
     */
	List<FriendCircleVo> friendCircle_userInfo(Long user_id, Long friend_id);

	/**
	 * 根据朋友圈id 返回朋友圈详细请
	 * @param friend_circle_id
	 * @return
	 */
	FriendCircleVo query_friendCircle_info(Long friend_circle_id);

}
