package com.hx.user.service;

import java.util.List;

import com.hwt.domain.entity.user.HxUserTeam;
import com.hwt.domain.entity.user.HxUserTeamUser;
import com.hwt.domain.entity.user.Vo.HxUserTeamVo;

/**
 * 群业务层
 * @author Administrator
 *
 */
/**
 * @author Administrator
 *
 */
public interface HxUserTeamService {

	/**
	 * 创建群
	 * @param hxUserTeam  群
	 * @param im_ids		用户集
	 */
	void insert_team(HxUserTeam hxUserTeam, String[] im_ids);

	/**
	 * 修改群信息
	 * @param hxUserTeam
	 */
	void update_team(HxUserTeam hxUserTeam);

	/**
	 * 初始化返回群信息
	 * @param team_ids
	 * @return
	 */
	List<HxUserTeamVo> query_HxUserTeamVo_by_team_ids(String[] team_ids);

	/**
	 * 删除群
	 * @param team_id
	 */
	void team_delete(String team_id);

	/**
	 * 拉人进群
	 * @param team_id
	 * @param user_id_im_ids
	 */
	void team_add_users(String team_id, String[] user_id_im_ids);

	
	/**
	 * 提人出群
	 * @param token			用户TOKEN
	 * @param user_id_im_ids
	 * @param team_id		群id
	 * @return
	 */
	void team_delete_users(String team_id, String[] user_id_im_ids);

	/**
	 * 修改用户在群中的信息
	 * @param hxUserTeamUser		
	 * @return
	 */
	void team_update_user(HxUserTeamUser hxUserTeamUser);

}
