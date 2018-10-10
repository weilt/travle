package com.hwt.domain.mapper.user.video;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.user.video.HxUserVideo;
import com.hwt.domain.entity.user.video.vo.HxUserVideoVo;
import com.hwt.domain.entity.user.video.vo.HxVideoVo;

@Mapper
public interface HxUserVideoMapper {

    int deleteByPrimaryKey(Long video_id);

    int insert(HxUserVideo record);

    int insertSelective(HxUserVideo record);


    HxUserVideo selectByPrimaryKey(Long video_id);


    int updateByPrimaryKeySelective(HxUserVideo record);
    
    int updateByPrimaryKey(HxUserVideo record);

	/**
	 * 游客查看
	 * @param video_user_id
	 * @param last_video_id
	 * @param pageSize
	 * @return
	 */
	List<HxVideoVo> onlooker_query(Map<String, Object> map);

	/**
	 * 查看自己的
	 * @param user_id
	 * @param startNum
	 * @param pageSize
	 * @return
	 */
	@Select("select * from hx_user_video where user_id = #{user_id} and video_id < #{last_video_id} order by create_time desc limit 0 , #{pageSize}")
	List<HxUserVideo> own_query(@Param("user_id")Long user_id, @Param("last_video_id")Integer last_video_id, @Param("pageSize")Integer pageSize);
	
	/**
	 * 查看自己的第一页
	 * @param user_id
	 * @param startNum
	 * @param pageSize
	 * @return
	 */
	@Select("select * from hx_user_video where user_id = #{user_id} order by create_time desc limit 0 , #{pageSize}")
	List<HxUserVideo> own_query_1(@Param("user_id")Long user_id, @Param("pageSize")Integer pageSize);

	/**
	 * 评论次数加1
	 * @param video_id
	 */
	@Update("update hx_user_video set comment_num = comment_num + 1 where video_id = #{video_id}")
	void commentAdd1(@Param("video_id")Long video_id);
	
	/**
	 * 评论次数减1
	 * @param video_id
	 */
	@Update("update hx_user_video set comment_num = comment_num - 1 where video_id = #{video_id} and comment_num > 0")
	void commentReduce1(@Param("video_id")Long video_id);
	
	/**
	 * 喜欢次数加1
	 * @param video_id
	 */
	@Update("update hx_user_video set good_num = good_num + 1 where video_id = #{video_id}")
	void likeAdd1(@Param("video_id")Long video_id);
	
	/**
	 * 喜欢次数减1
	 * @param video_id
	 */
	@Update("update hx_user_video set good_num = good_num - 1 where video_id = #{video_id} and good_num > 0")
	void likeReduce1(@Param("video_id")Long video_id);

	/**
	 * 查询自己发的小视频数量
	 * @param user_id
	 * @return
	 */
	@Select("select count(video_id) from hx_user_video where user_id = #{user_id}")
	Long total_my(Long user_id);
	
	
}