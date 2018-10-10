
package com.hx.user.video.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.user.video.HxUserVideo;
import com.hwt.domain.entity.user.video.HxUserVideoComment;
import com.hwt.domain.entity.user.video.HxUserVideoFollow;
import com.hwt.domain.entity.user.video.HxUserVideoLike;
import com.hwt.domain.entity.user.video.vo.HxUserVideoBasicNumShow;
import com.hwt.domain.entity.user.video.vo.HxUserVideoCommentVo;
import com.hwt.domain.entity.user.video.vo.HxUserVideoLikeListVo;
import com.hwt.domain.entity.user.video.vo.HxUserVideoOtherBasicNumShow;
import com.hwt.domain.entity.user.video.vo.HxUserVideoVo;
import com.hwt.domain.entity.user.video.vo.HxVideoVo;
import com.hwt.domain.entity.user.video.vo.VideoFollowVo;
import com.hwt.domain.entity.user.video.vo.VideoUserVo;
import com.hwt.domain.mapper.user.video.HxUserVideoCommentMapper;
import com.hwt.domain.mapper.user.video.HxUserVideoFollowMapper;
import com.hwt.domain.mapper.user.video.HxUserVideoLikeMapper;
import com.hwt.domain.mapper.user.video.HxUserVideoMapper;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.utils.ZZLocationUtils;
import com.hx.user.video.service.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
	@Autowired
	private HxUserVideoCommentMapper hxUserVideoCommentMapper;
	
	@Autowired
	private HxUserVideoFollowMapper hxUserVideoFollowMapper;
	
	@Autowired
	private HxUserVideoLikeMapper hxUserVideoLikeMapper;
	
	@Autowired
	private HxUserVideoMapper hxUserVideoMapper;

	@Override
	@Transactional
	public void insert(Long user_id, Long attribute_id, String dec, String image, String content, Integer is_open, String longitude,
			String latitude, String location) {
		HxUserVideo hxUserVideo = new HxUserVideo();
		if (ObjectHelper.isEmpty(longitude)||ObjectHelper.isEmpty(latitude)){
			
		}else{
			Map<String, String> map = ZZLocationUtils.get_province_city_district_by_gaode_log_lat(longitude,latitude);
			String area_code = map.get("area_code");
			String city = map.get("city");
			hxUserVideo.setArea_code(area_code);
			hxUserVideo.setCity(city);
		}
		
		
		
		hxUserVideo.setLocation(location);
		hxUserVideo.setAttribute_id(attribute_id);
		
		hxUserVideo.setContent(content);
		hxUserVideo.setCreate_time(System.currentTimeMillis());
		hxUserVideo.setDec(dec);
		hxUserVideo.setIs_open(is_open);
		hxUserVideo.setLatitude(latitude);
		hxUserVideo.setLongitude(longitude);
		hxUserVideo.setUser_id(user_id);
		hxUserVideo.setImage(image);
		
		hxUserVideoMapper.insertSelective(hxUserVideo);
	}

	@Override
	public List<HxVideoVo> onlooker_query(Map<String, Object> map) {
		List<HxVideoVo> list = hxUserVideoMapper.onlooker_query(map);
		return list;
	}

	@Override
	public List<HxUserVideo> own_query(Long user_id, Integer last_video_id, Integer pageSize) {
		if (last_video_id==null||last_video_id<=0){
			return hxUserVideoMapper.own_query_1(user_id, pageSize);
		}
		List<HxUserVideo> list = hxUserVideoMapper.own_query(user_id,last_video_id,pageSize);
		return list;
	}

	@Override
	@Transactional
	public void add_like(Long user_id, Long video_id) {
		HxUserVideoLike hxUserVideoLike = new HxUserVideoLike();
		hxUserVideoLike.setCreate_time(System.currentTimeMillis());
		hxUserVideoLike.setUser_id(user_id);
		hxUserVideoLike.setVideo_id(video_id);
		hxUserVideoLikeMapper.insert(hxUserVideoLike);
		hxUserVideoMapper.likeAdd1(video_id);
	}

	@Transactional
	@Override
	public void delete_like(Long user_id, Long video_id) {
		hxUserVideoLikeMapper.delete_like(user_id,video_id);
		hxUserVideoMapper.likeReduce1(video_id);
		
	}

	@Override
	public List<HxUserVideoLikeListVo> like_query(Map<String, Object> map) {
		List<HxUserVideoLikeListVo> list = hxUserVideoLikeMapper.like_query(map);
		return list;
	}

	@Override
	@Transactional
	public void delete(Long user_id, Long video_id) {
		HxUserVideo selectByPrimaryKey = hxUserVideoMapper.selectByPrimaryKey(video_id);
		if (selectByPrimaryKey.getUser_id()!=user_id){
			throw new RuntimeException("非本人不能操作");
		}
		selectByPrimaryKey.setIs_hide(1);
		hxUserVideoMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
	}


	@Transactional
	@Override
	public void add_follow(Long user_id, Long follow_user_id) {
		HxUserVideoFollow hxUserVideoFollow = new HxUserVideoFollow();
		hxUserVideoFollow.setCreate_time(System.currentTimeMillis());
		hxUserVideoFollow.setFollow_user_id(follow_user_id);
		hxUserVideoFollow.setUser_id(user_id);
		hxUserVideoFollowMapper.insertSelective(hxUserVideoFollow);
		
	}

	@Override
	@Transactional
	public void delete_follow(Long user_id, Long follow_user_id) {
		hxUserVideoFollowMapper.delete_follow(user_id,follow_user_id);
		
	}

	@Override
	public List<VideoFollowVo> query_follow(Map<String, Object> map) {
		return hxUserVideoFollowMapper.query_follow(map);
	}

	@Override
	@Transactional
	public HxUserVideoComment comment(Long user_id, Long video_id, String content, Integer type, Long parent_id,
			Long parent_user_id) {
		
		HxUserVideoComment hxUserVideoComment = new HxUserVideoComment();
		hxUserVideoComment.setContent(content);
		hxUserVideoComment.setCreate_time(System.currentTimeMillis());
		hxUserVideoComment.setParent_id(parent_id);
		hxUserVideoComment.setParent_user_id(parent_user_id);
		hxUserVideoComment.setType(type);
		hxUserVideoComment.setVideo_id(video_id);
		hxUserVideoComment.setUser_id(user_id);
		hxUserVideoComment.setIs_hide(0);
		hxUserVideoCommentMapper.insertBackId(hxUserVideoComment);
		hxUserVideoMapper.commentAdd1(video_id);
		return hxUserVideoComment;
	}

	@Override
	@Transactional
	public void delete_comment(Long user_id, Long video_comment_id) {
		HxUserVideoComment selectByPrimaryKey = hxUserVideoCommentMapper.selectByPrimaryKey(video_comment_id);
		if (selectByPrimaryKey.getUser_id()!=user_id){
			throw new RuntimeException("非本人不能操作");
		}
		selectByPrimaryKey.setIs_hide(1);
		hxUserVideoCommentMapper.updateByPrimaryKeySelective(selectByPrimaryKey);
		hxUserVideoMapper.commentReduce1(selectByPrimaryKey.getVideo_id());
	}

	@Override
	public List<HxUserVideoCommentVo> query_comment(Map<String, Object> map) {
		return hxUserVideoCommentMapper.query_comment(map);
	}

	@Override
	public HxUserVideoBasicNumShow query_own_basicNum(Long user_id) {
		HxUserVideoBasicNumShow hxUserVideoBasicNumShow = new HxUserVideoBasicNumShow();
		
		//被关注数量
		Long get_total_follow = hxUserVideoFollowMapper.get_total_follow(user_id);
		hxUserVideoBasicNumShow.setGet_total_follow(get_total_follow);
		
		//被赞数量
		Long get_total_good = hxUserVideoLikeMapper.get_total_good(user_id);
		hxUserVideoBasicNumShow.setGet_total_good(get_total_good);
		
		//评论次数
		Long total_comment = hxUserVideoCommentMapper.total_comment(user_id);
		hxUserVideoBasicNumShow.setTotal_comment(total_comment);
		
		//关注数量
		Long total_follow = hxUserVideoFollowMapper.total_follow(user_id);
		hxUserVideoBasicNumShow.setTotal_follow(total_follow);
		
		//喜欢数量
		Long total_good = hxUserVideoLikeMapper.total_good(user_id);
		hxUserVideoBasicNumShow.setTotal_good(total_good);
		
		//自己发的小视频数量
		Long total_my = hxUserVideoMapper.total_my(user_id);
		hxUserVideoBasicNumShow.setTotal_my(total_my);
		
		return hxUserVideoBasicNumShow;
	}

	@Override
	public HxUserVideoOtherBasicNumShow query_other_basicNum(Long user_id,Long other_user_id) {
		HxUserVideoOtherBasicNumShow hxUserVideoOtherBasicNumShow = new HxUserVideoOtherBasicNumShow();
		//被关注数量
		Long get_total_follow = hxUserVideoFollowMapper.get_total_follow(other_user_id);
		hxUserVideoOtherBasicNumShow.setGet_total_follow(get_total_follow);
		
		//被赞数量
		Long get_total_good = hxUserVideoLikeMapper.get_total_good(other_user_id);
		hxUserVideoOtherBasicNumShow.setGet_total_good(get_total_good);
		
		//评论次数
		Long total_comment = hxUserVideoCommentMapper.total_comment(other_user_id);
		hxUserVideoOtherBasicNumShow.setTotal_comment(total_comment);
		
		//查询是否关注
		HxUserVideoFollow selectByUserIdFollowUserId = hxUserVideoFollowMapper.selectByUserIdFollowUserId(user_id,other_user_id);
		if (selectByUserIdFollowUserId!=null){
			hxUserVideoOtherBasicNumShow.setIs_follow(1);
		}else {
			hxUserVideoOtherBasicNumShow.setIs_follow(0);
		}
		return hxUserVideoOtherBasicNumShow;
	}
}
