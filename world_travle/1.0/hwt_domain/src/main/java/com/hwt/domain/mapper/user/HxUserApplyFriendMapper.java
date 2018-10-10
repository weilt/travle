package com.hwt.domain.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.user.HxUserApplyFriend;
import com.hwt.domain.entity.user.Vo.FriendApplyVo;
import com.hwt.domain.utils.sample.config.MyMapper;
@Mapper
public interface HxUserApplyFriendMapper{

	/**
	 * 根据用户id得到申请列表
	 * @param userId
	 * @return
	 */
	List<FriendApplyVo> queryListByType(Long userId);

	/**
	 * 根据用户id 和 朋友id 查询
	 * @param userId
	 * @param friendId
	 * @return
	 */
	@Select("select * from hx_user_apply_friend where apply_user_id = #{userId} and apply_friend_id = #{friendId}")
	HxUserApplyFriend queryByByUserIdAndFriendId(@Param("userId")Long userId, @Param("friendId")Long friendId);

	/**
	 * 更新好友申请状态
	 * @param userId
	 * @param friendId
	 * @param status
	 */
	@Update("update hx_user_apply_friend set apply_state = #{status} where apply_user_id = #{userId} and apply_friend_id = #{friendId}")
	void updateByUserIdAndFriendId(@Param("userId")Long userId, @Param("friendId")Long friendId, @Param("status")int status);

	int insert(HxUserApplyFriend hxUserApplyFriend);
	
	/**
	 * 删除记录
	 * @param friendId
	 * @param userId
	 */
	@Delete("Delete from hx_user_apply_friend where apply_user_id = #{userId} and apply_friend_id = #{friendId}")
	void deleteByUserIdAndFriendId(@Param("userId")Long userId, @Param("friendId")Long friendId);
	
}