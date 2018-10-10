package com.hwt.domain.mapper.user.video;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.user.video.HxUserVideoLike;
import com.hwt.domain.entity.user.video.vo.HxUserVideoLikeListVo;
import com.hwt.domain.entity.user.video.vo.HxUserVideoVo;

@Mapper
public interface HxUserVideoLikeMapper {

    int insert(HxUserVideoLike record);

    int insertSelective(HxUserVideoLike record);


    int updateByPrimaryKeySelective(HxUserVideoLike record);

    int updateByPrimaryKey(HxUserVideoLike record);

	/**
	 * 删除喜欢
	 * @param user_id
	 * @param video_id
	 */
    @Delete("delete from hx_user_video_like where user_id = #{user_id} and video_id = #{video_id}")
	void delete_like(@Param("user_id")Long user_id, @Param("video_id")Long video_id);

    /**
     * 查询喜欢
     * @param map
     * @param last_create_time
     * @param pageSize
     * @return
     */
	List<HxUserVideoLikeListVo> like_query(Map<String, Object> map);

	/**
	 * 通过userid 查询获赞数
	 * @param user_id
	 * @return
	 */
    @Select("select count(a.create_time) from hx_user_video_like a,hx_user_video b where b.user_id = #{user_id} and a.video_id = b.video_id")
	Long get_total_good(@Param("user_id")Long user_id);

	/**
	 * 通过userid 查询赞数
	 * @param user_id
	 * @return
	 */
    @Select("select count(create_time) from hx_user_video_like where user_id = #{user_id}")
	Long total_good(@Param("user_id")Long user_id);
}