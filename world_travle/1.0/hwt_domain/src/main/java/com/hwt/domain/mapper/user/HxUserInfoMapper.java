package com.hwt.domain.mapper.user;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.user.HxUserInfo;
import com.hwt.domain.entity.user.Vo.CommunicationListFriendVo;
import com.hwt.domain.entity.user.Vo.FriendInfoVo;
import com.hwt.domain.entity.user.Vo.HxUserNearbyVo;
import com.hwt.domain.entity.user.Vo.UserInfoVo;
import com.hwt.domain.entity.user.Vo.adminHxVo.AdminHxUserInfo;
import com.hwt.domain.utils.sample.config.MyMapper;

@Mapper
public interface HxUserInfoMapper{

	/**
	 * 根据用户id获得用户信息
	 * @param userId
	 * @return
	 */
	@Select("select h.* from hx_user_info h where user_id = #{userId}")
	HxUserInfo selectByUserId(@Param("userId")Long userId);

	/**
	 * 根据手机号查询用户信息
	 * @param accountPhone
	 * @return
	 */
	@Select("select a.*,a.user_id as friend_id from hx_user_info a, hx_user b where a.user_id = b.user_id and b.account_phone =#{accountPhone}")
	FriendInfoVo queryByAccountPhone(@Param("accountPhone")String accountPhone);
	/**
	 * 根据账号查询用户信息
	 * @param accountPhone
	 * @return
	 */
	@Select("select *,user_id as friend_id from hx_user_info  where  account = #{account}")
	FriendInfoVo queryByAccount(@Param("account")String account);

	int insert(HxUserInfo hxUserInfo);
	
	int insertSelective(HxUserInfo hxUserInfo);

	int updateByPrimaryKey(HxUserInfo hxUserInfoEdit);
	
	@Select("select h.*,h.user_id as friend_id from hx_user_info h where user_id = #{userId}")
	FriendInfoVo queryByUserId(@Param("userId")Long userId);

	/**
	 * 修改账号
	 * @param user_id
	 * @param account
	 */
	@Update("update  hx_user_info set account = #{account}  where user_id=#{userId}")
	void updateAccount(@Param("userId")Long user_id, @Param("account")String account);
	
	
	/**
	 * 通过云信id查找
	 * @param im_id
	 * @return
	 */
	@Select("select h.*,h.user_id as friend_id from hx_user_info h where im_id = #{im_id}")
	FriendInfoVo queryByUserIm_id(@Param("im_id")String im_id);

	/**
	 * 通过id查询用户详情返回给后台
	 * @param user_id
	 * @return
	 */
	@Select("select account, referrer_account_id, nickname, userType, phone, username, user_birthday, user_sex, user_autograph, user_profession,"
			+ " user_icon, user_area_state_name,user_area_province_name, user_area_city_name,user_area_district_name ,last_longitude,last_latitude "
			+ "  from hx_user_info  where user_id = #{userId}")
	AdminHxUserInfo queryAdminHxUserInfoByUserId(@Param("userId")Long user_id);

	
	 /**
     * 通过电话号码获取获取注册过淮信的用户
     * @param phones  - 电话号码 - Y
     * @return
     */
	List<CommunicationListFriendVo> findHxUserByPhone(@Param("phones")String[] phones);

	/**
	 * 修改用户经纬度
	 * @param user_id 用户ID
	 * @param longitude 纬度
	 * @param latitude 经度
	 * @return
	 */
	@Update("update hx_user_info set last_longitude=#{longitude},last_latitude=#{latitude} where user_id=#{user_id}")
	int updateUserInfo(@Param("longitude")String longitude,@Param("latitude")String latitude,@Param("user_id")Long user_id); 
	
//	/**
//	 * 查询附近的人
//	 * @param startlongitude 开始的纬度
//	 * @param startlatitude 开始的经度
//	 * @param lastlongitude 结束的纬度
//	 * @param lastlatitude 结束的经度
//	 * @return
//	 */
//	List<UserInfoVo> queryNearby(@Param("startlongitude")BigDecimal startlongitude,@Param("startlatitude")BigDecimal startlatitude,
//			@Param("lastlongitude")BigDecimal lastlongitude,@Param("lastlatitude")BigDecimal lastlatitude,
//			@Param("startlongitude1")BigDecimal startlongitude1,@Param("startlatitude1")BigDecimal startlatitude1,
//			@Param("lastlongitude1")BigDecimal lastlongitude1,@Param("lastlatitude1")BigDecimal lastlatitude1);
	
	/**
	 * 查询附近的人
	 * @return
	 */
	List<HxUserNearbyVo> queryNearbyMap(Map<String, Object> map);
}