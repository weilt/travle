package com.hwt.domain.mapper.user.video;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.user.video.HxUserVideoFollow;
import com.hwt.domain.entity.user.video.vo.VideoFollowVo;
import com.hwt.domain.entity.user.video.vo.VideoUserVo;

@Mapper
public interface HxUserVideoFollowMapper {

    int insert(HxUserVideoFollow record);

    int insertSelective(HxUserVideoFollow record);

    int updateByPrimaryKeySelective(HxUserVideoFollow record);

    int updateByPrimaryKey(HxUserVideoFollow record);

    /**
     * 删除
     * @param user_id
     * @param follow_user_id
     */
    @Delete("delete from hx_user_video_follow where user_id = #{user_id} and follow_user_id = #{follow_user_id}")
	void delete_follow(@Param("user_id")Long user_id, @Param("follow_user_id")Long follow_user_id);

    
    /**
	 * 查询关注列表
	 * @param map
	 * @return
	 */
	List<VideoFollowVo> query_follow(Map<String, Object> map);

	/**
	 * 通过userid 查询被关注数量
	 * @param user_id
	 * @return
	 */
    @Select("select count(create_time) from hx_user_video_follow where follow_user_id = #{user_id}")
	Long get_total_follow(@Param("user_id")Long user_id);

	/**
	 * 通过userid 查询关注数量
	 * @param user_id
	 * @return
	 */
    @Select("select count(create_time) from hx_user_video_follow where user_id = #{user_id}")
	Long total_follow(@Param("user_id")Long user_id);

    @Select("select * from hx_user_video_follow where user_id = #{user_id} and follow_user_id = #{follow_user_id}")
	HxUserVideoFollow selectByUserIdFollowUserId(@Param("user_id")Long user_id,@Param("follow_user_id")Long follow_user_id);
}