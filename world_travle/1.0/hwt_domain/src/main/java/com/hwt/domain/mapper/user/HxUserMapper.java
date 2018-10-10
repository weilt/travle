package com.hwt.domain.mapper.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.user.HxUser;
import com.hwt.domain.entity.user.Vo.UserVo;
import com.hwt.domain.entity.user.Vo.adminHxVo.AdminHxUserVo;
import com.hwt.domain.entity.user.Vo.adminHxVo.HxUserReportVo;
@Mapper
public interface HxUserMapper{

	/**
	 * 根据 手机号码 查询 返回实体
	 * @param phone
	 * @return
	 */
	@Select("select * from hx_user where account_phone = #{phone}")
	HxUser hxUserAccountPhone(@Param("phone")String phone);
	
	
	/**
	 * 根据手机号获取用户信息--登录用
	 * @param phone
	 * @return
	 */
	@Select("select a.user_id , a.account , a.account_phone , "
			+ " a.password , a.pwd_salt , a.im_id , a.im_token , a.im_token_time ,"
			+ " a.login_state , a.login_state2_begin , a.login_state2_end ,"
			+ " a.recently_login_time , a.recently_login_ip ,"
			+ " a.login_type , a.is_available , b.referrer_account_id ,"
			+ " b.nickname , b.userType , b.phone , b.username , b.user_birthday ,"
			+ " b.user_sex , b.user_icon,b.friend_circle_cover ,b.last_longitude , b.last_latitude  "
			+ " from hx_user a, hx_user_info b where a.account_phone = #{phone} and b.user_id = a.user_id")
/*	@Select("select a.user_id as userId, a.account as account, a.account_phone as accountPhone, "
			+ " a.password as password, a.pwd_salt as pwdSalt, a.im_id as imId, a.im_token as imToken, a.im_token_time as imTokenTime,"
			+ " a.login_state as loginState, a.login_state2_begin as loginState2Begin, a.login_state2_end as loginState2End,"
			+ " a.recently_login_time as recentlyLoginTime, a.recently_login_ip as recentlyLoginIp,"
			+ " a.login_type as loginType, a.is_available as isAvailable, b.referrer_account_id as referrerAccountId,"
			+ " b.nickname as nickname, b.userType as usertype, b.phone as phone, b.username as username, b.user_birthday as userBirthday,"
			+ " b.user_sex as userSex, b.user_icon as userIcon,b.last_longitude as lastLongitude, b.last_latitude as lastLatitude "
			+ " from hx_user a, hx_user_info b where a.account_phone = #{phone} and b.user_id = a.user_id")
*/	UserVo userVoAccountPhone(@Param("phone")String phone);

	/**
	 * 根据账号获取用户信息 --登录用
	 * @param account
	 * @return
	 */
/*	@Select("select a.user_id as userId, a.account as account, a.account_phone as accountPhone,"
			+ " a.password as password, a.pwd_salt as pwdSalt, a.im_id as imId, a.im_token as imToken, a.im_token_time as imTokenTime,"
			+ " a.login_state as loginState, a.login_state2_begin as loginState2Begin, a.login_state2_end as loginState2End,"
			+ " a.recently_login_time as recentlyLoginTime, a.recently_login_ip as recentlyLoginIp,"
			+ " a.login_type as loginType, a.is_available as isAvailable, b.referrer_account_id as referrerAccountId,"
			+ " b.nickname as nickname, b.userType as usertype, b.phone as phone, b.username as username, b.user_birthday as userBirthday,"
			+ " b.user_sex as userSex, b.user_icon as userIcon,b.last_longitude as lastLongitude, b.last_latitude as lastLatitude "
			+ " from hx_user a, hx_user_info b where a.account = #{account} and b.user_id = a.user_id")*/
	@Select("select a.user_id , a.account , a.account_phone ,"
			+ " a.password , a.pwd_salt , a.im_id , a.im_token , a.im_token_time ,"
			+ " a.login_state , a.login_state2_begin , a.login_state2_end ,"
			+ " a.recently_login_time , a.recently_login_ip ,"
			+ " a.login_type , a.is_available , b.referrer_account_id ,"
			+ " b.nickname , b.userType , b.phone as phone, b.username , b.user_birthday ,"
			+ " b.user_sex , b.user_icon, b.friend_circle_cover ,b.last_longitude , b.last_latitude  "
			+ " from hx_user a, hx_user_info b where a.account = #{account} and b.user_id = a.user_id")
	UserVo userVoAccount(@Param("account")String account);


	/**
	 * 根据账号获得个数
	 * @param account
	 * @return
	 */
	@Select("select COUNT(user_id) FROM hx_user where account = #{account}")
	int hxUseAccountCount(@Param("account")String account);


	/**
	 * 通过userId获得用户详情
	 * @param userId
	 * @return
	 */
	/*@Select("select a.user_id as userId, a.account as account, a.account_is_edit as accountIsEdit, a.account_phone as accountPhone,"
			+ " a.password as password, a.pwd_salt as pwdSalt, a.im_id as imId, a.im_token as imToken, a.im_token_time as imTokenTime,"
			+ " a.login_state as loginState, a.login_state2_begin as loginState2Begin, a.login_state2_end as loginState2End,"
			+ " a.recently_login_time as recentlyLoginTime, a.recently_login_ip as recentlyLoginIp,"
			+ " a.login_type as loginType, a.is_available as isAvailable, b.referrer_account_id as referrerAccountId,"
			+ " b.nickname as nickname, b.userType as usertype, b.phone as phone, b.username as username, b.user_birthday as userBirthday,"
			+ " b.user_autograph as userAutograph, b.user_profession as userProfession,"
			+ " b.user_area_state as userAreaState, b.user_area_state_name as userAreaStateName, b.user_area_province as userAreaProvince,"
			+ " b.user_area_province_name as userAreaProvinceName, b.user_area_city as userAreaCity, b.user_area_city_name as userAreaCityName,"
			+ " b.user_area_district as userAreaDistrict, b.user_area_district_name as userAreaDistrictName,"
			+ " b.user_sex as userSex, b.user_icon as userIcon,"
			+ " b.last_longitude as lastLongitude, b.last_latitude as lastLatitude "
			+ " from hx_user a, hx_user_info b where a.user_id = #{userId} and b.user_id = a.user_id")*/
	@Select("select a.user_id , a.account , a.account_is_edit , a.account_phone ,"
			+ " a.password , a.pwd_salt , a.im_id , a.im_token , a.im_token_time ,"
			+ " a.login_state , a.login_state2_begin , a.login_state2_end ,"
			+ " a.recently_login_time , a.recently_login_ip ,"
			+ " a.login_type , a.is_available , b.referrer_account_id ,"
			+ " b.nickname , b.userType , b.phone , b.username , b.user_birthday ,"
			+ " b.user_autograph , b.user_profession ,"
			+ " b.user_area_state , b.user_area_state_name , b.user_area_province ,"
			+ " b.user_area_province_name , b.user_area_city , b.user_area_city_name ,"
			+ " b.user_area_district , b.user_area_district_name ,"
			+ " b.user_sex , b.user_icon ,"
			+ " b.last_longitude , b.last_latitude  "
			+ " from hx_user a, hx_user_info b where a.user_id = #{userId} and b.user_id = a.user_id")
	UserVo selectByUserId(@Param("userId")Long userId);


	/**
	 * 返回主键添加
	 * @param hxUser2
	 * @return
	 */
	int insertReturnUserId(HxUser hxUser2);


	int updateByPrimaryKeySelective(HxUser hxUser);


	/**
	 * 通过userid获取imid
	 * @param userId
	 * @return
	 */
	@Select("select im_id from hx_user where user_id=#{userId}")
	String queryImIdByUserId(@Param("userId")Long userId);


	/**
	 * 修改账号
	 * @param user_id
	 * @param account
	 */
	@Update("update hx_user set account = #{account} ,account_is_edit = 1  where user_id=#{userId} and account_is_edit = 0")
	int updateAccount(@Param("userId")Long user_id, @Param("account")String account);


	/**
	 * 根据条件返回条数给后台
	 * @param map
	 * @return
	 */
	long queryCountByMap(Map<String, Object> map);


	/**
	 * 根据条件返回给后台
	 * @param map
	 * @return
	 */
	List<AdminHxUserVo> queryListByMap(Map<String, Object> map);


	/**
	 * 冻结/解冻
	 * @param user_id
	 * @param type
	 * @return
	 */
	@Update("update hx_user set login_state = #{type}  where user_id=#{userId} ")
	int frozen(@Param("userId")Long user_id, @Param("type")Integer type);

	/**
	 * 根据条件返回条数给报表
	 * @param map
	 * @return
	 */
	long queryHxUserReportCountByMap(Map<String, Object> map);

	/**
	 * 根据条件返回数据给报表
	 * @param map
	 * @return
	 */
	List<HxUserReportVo> queryHxUserReportByMap(Map<String, Object> map);
	
	/**
	 * 根据账号修改密码
	 * @param acc
	 * @param passWord
	 * @return
	 */
	@Update("update hx_user set password=#{passWord} where account=#{acc}")
	int updatePassWord(@Param("acc")String acc,@Param("passWord")String passWord);
	
}