package com.hwt.domain.mapper.user.video;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.user.video.HxUserVideoComment;
import com.hwt.domain.entity.user.video.vo.HxUserVideoCommentVo;

@Mapper
public interface HxUserVideoCommentMapper {

    int deleteByPrimaryKey(Long video_comment_id);

    int insert(HxUserVideoComment record);

    int insertSelective(HxUserVideoComment record);


    HxUserVideoComment selectByPrimaryKey(Long video_comment_id);


    int updateByPrimaryKeySelective(HxUserVideoComment record);

    int updateByPrimaryKey(HxUserVideoComment record);

    /**
     * 查看评论
     * @param map
     * @return
     */
	List<HxUserVideoCommentVo> query_comment(Map<String, Object> map);

	/**
	 * 返回主键添加
	 * @param hxUserVideoComment
	 */
	void insertBackId(HxUserVideoComment hxUserVideoComment);

	/**
	 * 获取评论次数
	 * @param user_id
	 * @return
	 */
	@Select("select count(a.video_comment_id) from hx_user_video_comment a , hx_user_video b where a.video_id = b.video_id and  b.user_id = #{user_id} and a.user_id!=#{user_id}")
	Long total_comment(@Param("user_id")Long user_id);
}