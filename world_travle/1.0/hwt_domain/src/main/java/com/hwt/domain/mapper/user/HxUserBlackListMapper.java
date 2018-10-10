package com.hwt.domain.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.user.HxUserBlackList;
import com.hwt.domain.entity.user.Vo.BlackFriendUserVo;
import com.hwt.domain.utils.sample.config.MyMapper;
@Mapper
public interface HxUserBlackListMapper{
	
	/**
	 * 根据用户id查询黑名单列表
	 * @param userId
	 * @return
	 */
/*	@Select("SELECT c.nickname as nickname, c.user_id AS userId, c.user_icon as userIcon, b.friend_remark as friendRemark  FROM "
			+ "hx_user_black_list a LEFT JOIN hx_user_friend b JOIN hx_user_info c "
			+ "ON a.blacklist_id = c.user_id AND b.friend_id = a.blacklist_id AND b.user_id = a.user_id "
			+ "WHERE a.user_id = #{userId}")*/
	@Select("SELECT b.user_id as friend_id, b.nickname,b.user_icon,a.friend_remark "
			+ "from hx_user_black_list a join hx_user_info b on a.blacklist_id = b.user_id and a.user_id = #{userId}")
	List<BlackFriendUserVo> queryListByUserId(@Param("userId")Long userId);

	/**
	 * 根据  用户id 和  好友id 查询
	 * @param friendId
	 * @param userId
	 * @return
	 */
	@Select("select *,blacklist_id as frind_id from hx_user_black_list where user_id = #{userId} and blacklist_id = #{friendId}")
	HxUserBlackList queryByUserIdAndFriendId(@Param("userId")Long userId, @Param("friendId")Long friendId);

	/**
	 * 移除黑名单
	 * @param userId
	 * @param friendId
	 */
	@Delete("delete from hx_user_black_list where user_id = #{userId} and blacklist_id = #{friendId}")
	void deleteByUserIdAndFriendId(@Param("userId")Long userId, @Param("friendId")Long friendId);

	int insert(HxUserBlackList hxUserBlackList);

	/**
	 * 跟新黑名单中的备注
	 * @param userId
	 * @param friendId
	 * @param friendRemark
	 */
	@Update("Update hx_user_black_list set friend_remark = #{friendRemark} where user_id = #{userId} and blacklist_id = #{friendId} ")
	void updateFriendRemark(@Param("userId")Long userId, @Param("friendId")Long friendId, @Param("friendRemark")String friendRemark);

	/**
	 * 查询把我拉黑的im——id
	 * @param friendId
	 * @return
	 */
	@Select("select b.im_id  from hx_user_black_list a, hx_user b where a.blacklist_id = #{friendId} and b.user_id = a.user_id")
	List<String> queryBeiBlackImIdByUserId(@Param("friendId")Long friendId);

	
    
}