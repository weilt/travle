package com.hwt.domain.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.user.HxUserFriend;
import com.hwt.domain.entity.user.HxUserInfo;
import com.hwt.domain.entity.user.Vo.CommunicationListFriendVo;
import com.hwt.domain.entity.user.Vo.FriendCircleJurisdictionVo;
import com.hwt.domain.entity.user.Vo.FriendInfoVo;
import com.hwt.domain.entity.user.Vo.FriendUserVo;
import com.hwt.domain.entity.user.Vo.adminHxVo.AdminHxUserFriendVo;
import com.hwt.domain.utils.sample.config.MyMapper;

@Mapper
public interface HxUserFriendMapper{

	/**
	 * 好友列表
	 * @param userId
	 * @return
	 */
	
	/*@Select("SELECT a.user_id as userId,a.friend_id as friendId,a.friend_state as friendState,"
			+ "a.friend_remark as friendRemark,b.friend_state as friendToMeState,c.nickname as nickname,"
			+ " c.user_sex as userSex,"
			+ " c.user_birthday as userBirthday,c.user_icon as userIcon, c.user_profession as userProfession"
			+ " FROM hx_user_friend a "
			+ " left JOIN hx_user_friend b "
			+ " ON a.user_id = b.friend_id "
			+ " AND a.friend_id = b.user_id "
			+ " JOIN hx_user_info c "
			+ " ON c.user_id = a.friend_id "
			+ " WHERE  a.friend_state = 1 "
			+ " AND a.user_id = #{userId}")*/
	@Select("select a.friend_id, a.friend_remark, a.friend_to_me_state,a.friend_state,"
			+ " b.user_icon, b.im_id,b.nickname from hx_user_friend a JOIN hx_user_info b on a.user_id = #{userId} "
			+ " and a.friend_id = b.user_id and a.friend_state = 1")
	List<FriendUserVo> queryFriends(@Param("userId")Long userId);

	/**
	 * 根据用户id 朋友id 查询
	 * @param userId
	 * @param friendId
	 * @return
	 */
	@Select("select * from hx_user_friend where user_id = #{userId} and friend_id = #{friendId}")
	HxUserFriend queryByByUserIdAndFriendId(@Param("userId")Long userId, @Param("friendId")Long friendId);

	/**
	 * 更改好友关系
	 * @param userId
	 * @param friendId
	 * @param friendState
	 */
	@Update("update hx_user_friend set friend_state = #{friendState} where user_id = #{userId} and friend_id = #{friendId}")
	void recoveryByByUserIdAndFriendId(@Param("userId")Long userId, @Param("friendId")Long friendId, @Param("friendState")int friendState);

	/**
	 * 查询好友质料详情通过云信id
	 * @param im_id
	 * @param userId
	 * @return
	 */
	/*@Select("SELECT a.user_id as userId,a.friend_id as friendId,a.friend_state as friendState,"
			+ "a.friend_remark as friendRemark,c.nickname as nickname,"
			+ " c.user_sex as userSex,"
			+ " c.user_birthday as userBirthday,c.user_icon as userIcon, c.user_profession as userProfession"
			+ " FROM hx_user_friend a "
			+ " JOIN hx_user_info c "
			+ " ON c.user_id = a.friend_id "
			+ " WHERE  a.friend_state = 1 "
			+ " AND a.user_id = #{userId} AND a.friend_id = #{friendId}")*/
	/*@Select("SELECT a.user_id ,a.friend_id ,a.friend_state ,"
			+ "a.friend_remark ,c.nickname ,"
			+ " c.user_sex ,"
			+ " c.user_birthday ,c.user_icon , c.user_profession, d.account "
			+ " FROM hx_user_friend a "
			+ " JOIN hx_user_info c "
			+ " ON c.user_id = a.friend_id "
			+ "join hx_user d on d.user_id = a.friend_id "
			+ " WHERE a.friend_state = 1 "
			+ " AND a.user_id = #{userId} AND d.im_id = #{im_id}")*/
	@Select("select a.friend_remark,a.friend_state,a.friend_id,c.nickname , "
			+ " c.user_sex , c.user_area_province, c.user_area_province_name, c.user_area_city, c.user_area_city_name, c.user_area_district, c.user_area_district_name, "
			+ " c.user_birthday ,c.user_icon , c.user_profession, c.userType,c.im_id,c.account,c.user_area_state,c.user_area_state_name "
			+ "from hx_user_friend a join hx_user_info c on a.user_id = #{userId} and c.user_id = a.friend_id and c.im_id = #{im_id}")
	FriendInfoVo queryFriendInfoByImId(@Param("im_id")String im_id, @Param("userId")Long userId);

	/**
	 * 删除好友关系
	 * @param userId
	 * @param friendId
	 */
	@Delete("delete from hx_user_friend where (user_id = #{userId} and friend_id = #{friendId}) or (user_id = #{friendId} and friend_id = #{userId})")
	void deleteByUserIdAndFriendId(@Param("userId")Long userId, @Param("friendId")Long friendId);

	/**
	 * 修改好友备注
	 * @param userId
	 * @param friendId
	 * @param friendRemark
	 */
	@Update("update hx_user_friend set friend_remark = #{friendRemark} where user_id = #{userId} and friend_id = #{friendId}")
	void updateFriendRemark(@Param("userId")Long userId, @Param("friendId")Long friendId, @Param("friendRemark")String friendRemark);

	int insertSelective(HxUserFriend hxUserFriend);

	int updateByPrimaryKey(HxUserFriend hxUserFriend);
	
	/**
	 * 修改我在好友中的状态
	 * @param userId
	 * @param friendId
	 */
	@Update("update hx_user_friend set friend_to_me_state = #{state} where user_id = #{userId} and friend_id = #{friendId}")
	void updateFriendToUser(@Param("userId")Long userId, @Param("friendId")Long friendId, @Param("state")Integer state);

	/**
	 * 恢复好友 ， 有备注情况
	 * @param userId
	 * @param friendId
	 * @param friendState
	 * @param friendRemark
	 */
	@Update("update hx_user_friend set friend_state = #{friendState},friend_remark = #{friendRemark} where user_id = #{userId} and friend_id = #{friendId}")
	void recoveryByByUserIdAndFriendIdTofriendRemark(@Param("userId")Long userId, @Param("friendId")Long friendId, @Param("friendState")int friendState, @Param("friendRemark")String friendRemark);

	/**
	 * 查询把我删除的im——id
	 * @param friendId
	 * @return
	 */
	@Select("select b.im_id from hx_user_friend a ,hx_user b where  a.friend_id = #{friendId} and b.user_id = a.user_id and friend_state = 3")
	List<String> queryBeiDeleteImIdByUserId(@Param("friendId")Long friendId);

	/**
	 * 通过条件获取好友总条数 返回给后台
	 * @param map
	 * @return
	 */
	long queryCountUserFriendByMapToAdmin(Map<String, Object> map);

	/**
	 * 通过条件获取好列表返回给后台
	 * @param map
	 * @return
	 */
	List<AdminHxUserFriendVo> queryUserFriendListByMapToAdmin(Map<String, Object> map);

	/**
	 * 根据用户id获取双方都是好友关系的朋友id和朋友圈权限
	 * @param user_id
	 * @return
	 */
//	@Select("select friend_id,friend_circle_friend_see_day as friend_circle_friend_see_day from hx_user_friend a"
//			+ "where friend_state = 1 and friend_to_me_state = 1 and user_id = #{user_id}")
	@Select("SELECT a.friend_id, c.friend_circle_friend_see_day AS friend_circle_friend_see_day FROM hx_user_friend a JOIN hx_user_friend b ON a.friend_id = b.user_id "
			+ " AND b.friend_id = a.user_id AND a.friend_state = 1 AND a.friend_to_me_state = 1 AND a.me_see_friend_space = 1 AND b.friend_see_me_space = 1 ANd a.user_id = #{user_id}  "
			+ "JOIN hx_user_config c ON c.user_id = a.friend_id  ")
	List<FriendCircleJurisdictionVo> query1to1FriendByUse_id(@Param("user_id")Long user_id);

	
	/**
	 * 根据用户id和朋友id和获取朋友id和朋友圈权限
	 * @param user_id
	 * @param friend_id
	 * @return
	 */
	@Select("SELECT a.friend_state,a.friend_to_me_state,a.me_see_friend_space,b.friend_see_me_space as friend_let_me_see_space, "
			+ "a.friend_id, c.friend_circle_friend_see_day AS friend_circle_friend_see_day FROM hx_user_friend a JOIN hx_user_friend b ON a.friend_id = b.user_id "
			+ " AND b.friend_id = a.user_id   ANd a.user_id = #{user_id} "
			+ " AND a.friend_id = #{friend_id} "
			+ "JOIN hx_user_config c ON c.user_id = a.friend_id  ")
	FriendCircleJurisdictionVo query1to1FriendByUse_idAndFriend_id(@Param("user_id")Long user_id, @Param("friend_id")Long friend_id);

	  /**
     * 是否让朋友看我的朋友圈
     * @param user_id  
     * @param friend_see_me_space	是否让朋友看我的朋友圈吧 0-否  1-是 默认是1
     * @param friend_id		朋友id
     * @return
     */
	@Update("update hx_user_friend set friend_see_me_space = #{friend_see_me_space} where user_id = #{user_id} and friend_id = #{friend_id}")
	int let_friend_see_friendCircle(@Param("user_id")Long user_id, @Param("friend_see_me_space")Integer friend_see_me_space, @Param("friend_id")Long friend_id);

	 /**
     * 朋友 是否让我 他看朋友圈
     * @param user_id  
     * @param friend_friend_see_me_space	朋友 是否让我 他看朋友圈 0-否  1-是 默认是1
     * @param friend_id		朋友id
     * @return
     */
	@Update("update hx_user_friend set friend_friend_see_me_space = #{friend_friend_see_me_space} where user_id = #{user_id} and friend_id = #{friend_id}")
	void friend_let_friend_see_friendCircle(@Param("user_id")Long user_id, @Param("friend_friend_see_me_space")Integer friend_friend_see_me_space, @Param("friend_id")Long friend_id);
	
	/**
     * 是否看朋友的朋友圈
     * @param user_id
     * @param me_see_friend_space	我是否能看朋友的朋友圈  0-否  1-能 默认能
     * @param friend_id		朋友id
     * @return
     */
	@Update("update hx_user_friend set me_see_friend_space = #{me_see_friend_space} where user_id = #{user_id} and friend_id = #{friend_id}")
	int see_friend_friendCircle(@Param("user_id")Long user_id, @Param("me_see_friend_space")Integer me_see_friend_space, @Param("friend_id")Long friend_id);

	
	/**
     * 朋友 朋友是否愿意看我的朋友圈
     * @param user_id
     * @param friend_me_see_friend_space	朋友 朋友是否愿意看我的朋友圈  0-否  1-能 默认能
     * @param friend_id		朋友id
     * @return
     */
	@Update("update hx_user_friend set friend_me_see_friend_space = #{friend_me_see_friend_space} where user_id = #{user_id} and friend_id = #{friend_id}")
	void friend_see_friend_friendCircle(@Param("user_id")Long user_id, @Param("friend_me_see_friend_space")Integer friend_me_see_friend_space,@Param("friend_id") Long friend_id);
	
	/**
	 * 朋友圈添加时能发通知所有人能看
	 * @param user_id
	 * @return
	 */
	List<String> queryFriend_im_id_for_friendCircle_all(@Param("user_id")Long user_id);

	/**
	 * 朋友圈添加时能发通知只给ids看
	 * @param user_id
	 * @param ids
	 * @return
	 */
	List<String> queryFriend_im_id_for_friendCircle_Y(@Param("user_id")Long user_id, @Param("ids")Long[] ids);

	
	/**
	 * 朋友圈添加时能发通知 不让谁看
	 * @param user_id
	 * @param ids
	 * @return
	 */
	List<String> queryFriend_im_id_for_friendCircle_N(@Param("user_id")Long user_id, @Param("ids")Long[] ids);

	
	
	

	
}