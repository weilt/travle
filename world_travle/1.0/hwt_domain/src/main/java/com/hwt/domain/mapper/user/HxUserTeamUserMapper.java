package com.hwt.domain.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.user.HxUserTeamUser;

/**
 * 群——用户 持久层 
 * @author Administrator
 *
 */
@Mapper
public interface HxUserTeamUserMapper  {

    int insert(HxUserTeamUser record);

    int insertSelective(HxUserTeamUser record);



    int updateByPrimaryKeySelective(HxUserTeamUser record);

    int updateByPrimaryKey(HxUserTeamUser record);

	/**
	 * 添加多个
	 * @param list
	 */
	int insertList(@Param("list")List<HxUserTeamUser> list);

	/**
	 * 通过群id删除
	 * @param team_id
	 */
	@Delete("delete from hx_user_team_user where team_id = #{team_id}")
	int deleteByTeam_id(@Param("team_id")String team_id);

	/**
	 * 退群
	 * @param team_id
	 * @param user_id_im_ids
	 */
	int team_delete_users(@Param("team_id")String team_id, @Param("user_id_im_ids")String[] user_id_im_ids);

	/**
	 * 通过
	 * @param user_id_im_id
	 * @param team_id
	 * @return
	 */
	@Select("select * from hx_user_team_user where team_id = #{team_id} and user_id_im_id = #{user_id_im_id}")
	HxUserTeamUser queryByUser_im_idAndTeamId(@Param("user_id_im_id")String user_id_im_id, @Param("team_id")String team_id);
}