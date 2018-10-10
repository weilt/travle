package com.hx.user.video.service;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.user.video.HxUserVideo;
import com.hwt.domain.entity.user.video.HxUserVideoComment;
import com.hwt.domain.entity.user.video.vo.HxUserVideoBasicNumShow;
import com.hwt.domain.entity.user.video.vo.HxUserVideoCommentVo;
import com.hwt.domain.entity.user.video.vo.HxUserVideoLikeListVo;
import com.hwt.domain.entity.user.video.vo.HxUserVideoOtherBasicNumShow;
import com.hwt.domain.entity.user.video.vo.HxUserVideoVo;
import com.hwt.domain.entity.user.video.vo.HxVideoVo;
import com.hwt.domain.entity.user.video.vo.VideoFollowVo;
import com.hwt.domain.entity.user.video.vo.VideoUserVo;

public interface VideoService {

	/**
	 * 发布小视频 
	 * @param user_id			用户id
	 * @param attribute_id		分类
	 * @param dec				描述
	 * @param content			内容 url
	 * @param image 
	 * @param is_open			是否公开   1-是 0-否
	 * @param longitude			经度
	 * @param latitude			纬度
	 * @param location 
	 */
	void insert(Long user_id, Long attribute_id, String dec, String content, String image, Integer is_open, String longitude,
			String latitude, String location);

	
	/**
	 * 看客查看
	 */
	List<HxVideoVo> onlooker_query(Map<String, Object> map);


	/**
	 * 查看自己的小视频
	 * @param user_id
	 * @param last_video_id
	 * @param pageSize
	 * @return
	 */
	List<HxUserVideo> own_query(Long user_id, Integer last_video_id, Integer pageSize);

	/**
	 * 添加喜欢
	 * @param user_id
	 * @param video_id
	 */
	void add_like(Long user_id, Long video_id);


	/**
	 * 删除喜欢
	 * @param user_id
	 * @param video_id
	 */
	void delete_like(Long user_id, Long video_id);

	/**
	 * 查询喜欢
	 * @param map
	 * @param pageSize 
	 * @param last_video_id 
	 * @return
	 */
	List<HxUserVideoLikeListVo> like_query(Map<String, Object> map);


	/**
	 * 删除
	 * @param user_id
	 * @param video_id
	 */
	void delete(Long user_id, Long video_id);


	/**
	 * 添加关注
	 * @param user_id
	 * @param follow_user_id
	 */
	void add_follow(Long user_id, Long follow_user_id);


	/**
	 * 删除关注
	 * @param user_id
	 * @param follow_user_id
	 */
	void delete_follow(Long user_id, Long follow_user_id);

	/**
	 * 查询关注列表
	 * @param map
	 * @return
	 */
	List<VideoFollowVo> query_follow(Map<String, Object> map);


	/**
	 * 评论
	 * @param user_id
	 * @param video_id
	 * @param content
	 * @param type
	 * @param parent_id
	 * @param parent_user_id
	 */
	HxUserVideoComment comment(Long user_id, Long video_id, String content, Integer type, Long parent_id, Long parent_user_id);


	/**
	 * 删除评论
	 * @param user_id
	 * @param video_comment_id
	 */
	void delete_comment(Long user_id, Long video_comment_id);


	/**
	 * 查看评论
	 * @param map
	 * @return
	 */
	List<HxUserVideoCommentVo> query_comment(Map<String, Object> map);


	/**
	 * 小视频个人首页数据返回
	 * @param user_id
	 * @return
	 */
	HxUserVideoBasicNumShow query_own_basicNum(Long user_id);


	/**
	 * 小视频个人首页数据返回
	 * @param other_user_id
	 */
	HxUserVideoOtherBasicNumShow query_other_basicNum(Long user_id, Long other_user_id);

}
