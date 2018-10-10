package com.hwt.domain.mapper.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hwt.domain.entity.user.HxUserTeam;
import com.hwt.domain.entity.user.Vo.HxUserTeamVo;


/**
 * 群
 * @author Administrator
 *
 */
@Mapper
public interface HxUserTeamMapper {


    int deleteByPrimaryKey(String id);

    int insert(HxUserTeam record);

    int insertSelective(HxUserTeam record);


    HxUserTeam selectByPrimaryKey(String id);



    int updateByPrimaryKeySelective(HxUserTeam record);

    int updateByPrimaryKey(HxUserTeam record);

    

	/**
	 * 初始化返回群信息
	 * @param team_ids 群id集
	 * @return
	 */
	List<HxUserTeamVo> query_HxUserTeamVo_by_team_ids(@Param("team_ids")String[] team_ids);
}