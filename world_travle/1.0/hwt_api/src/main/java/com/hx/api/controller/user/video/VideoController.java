package com.hx.api.controller.user.video;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.user.Vo.LoginVo;
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
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.user.utils.BaseUtil;
import com.hx.user.video.service.VideoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "API - VideoController", description = "小视屏操作接口")
@RestController
public class VideoController {
	
	@Autowired
	private VideoService videoService;
	
	/**
	 * 发布小视频
	 * @param token - 验证登录状态 - Y
	 * @return
	 */
	@ApiOperation(value = "发布小视频", notes = "发布小视频" , response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "attribute_id", value = "分类  0-默认", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "dec", value = "描述", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "image", value = "封面图", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "content", value = "内容url", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "is_open", value = "是否公开 1-是 0-否   默认1", dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "longitude", value = "经度", dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "latitude", value = "纬度", dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "location", value = "地址", dataType = "string",paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","attribute_id","dec","content","image"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/insert",method = RequestMethod.POST)
	public ResultEntity accountInfo(String token,Long attribute_id,String dec,String image, String content, @RequestParam(defaultValue="1")Integer is_open, 
				String longitude, String latitude,String location){
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		videoService.insert(loginVo.getUser_id(), attribute_id,dec,image,content,is_open,longitude,latitude,location);
		return new ResultEntity();
	}
	
	/**
	 * 删除小时频
	 */
	@ApiOperation(value = "发布小视频", notes = "发布小视频" , response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "video_id", value = "小视频id ", dataType = "string",required = true, paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","video_id"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/delete",method = RequestMethod.POST)
	public ResultEntity delete(String token,Long video_id){
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		videoService.delete(loginVo.getUser_id(), video_id);
		return new ResultEntity();
	}
	
	/**
	 * 看客的身份查询小视频
	 */
	@ApiOperation(value = "看客的身份查询小视频", notes = "看客的身份查询小视频" , response = HxVideoVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "field", value = "搜索字段", dataType = "string",paramType = "query"),
		@ApiImplicitParam(name = "video_user_id", value = "小视频发布人id  0为查看所有人的  默认0", dataType = "string",paramType = "query"),
		@ApiImplicitParam(name = "last_video_id", value = "查询的最后一条小视频id  默认0", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string", paramType = "query")
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/onlooker_query",method = RequestMethod.POST)
	public ResultEntity onlooker_query(String token,@RequestParam(defaultValue="0") Long video_user_id ,@RequestParam(defaultValue="0")Long last_video_id,String field
			,@RequestParam(defaultValue="10") Integer pageSize) {
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("video_user_id", video_user_id);
		map.put("last_video_id", last_video_id);
		map.put("pageSize", pageSize);
		map.put("field", field);
		map.put("user_id", loginVo.getUser_id());
		List<HxVideoVo> list = videoService.onlooker_query(map);
		return new ResultEntity(list);
	}
	
	/**
	 * 查看自己的小视频
	 */
	@ApiOperation(value = "查看自己的小视频", notes = "查看自己的小视频" , response = HxUserVideoVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "last_video_id", value = "查询的最后一条小视频id  默认0", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/own_query",method = RequestMethod.POST)
	public ResultEntity own_query(String token,@RequestParam(defaultValue="0") Integer last_video_id 
			,@RequestParam(defaultValue="10") Integer pageSize) {
		
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		
		List<HxUserVideo> list = videoService.own_query(loginVo.getUser_id(),last_video_id,pageSize);
		return new ResultEntity(list);
	}
	
	/**
	 * 添加喜欢
	 */
	@ApiOperation(value = "添加喜欢", notes = "添加喜欢" , response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "video_id", value = "小视频id ", dataType = "string",required = true, paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","video_id"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/add_like",method = RequestMethod.POST)
	public ResultEntity add_like(String token, Long video_id) {
		
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		
		videoService.add_like(loginVo.getUser_id(),video_id);
		return new ResultEntity();
	}
	
	/**
	 * 删除喜欢
	 */
	@ApiOperation(value = "删除喜欢", notes = "删除喜欢" , response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "video_id", value = "小视频id ", dataType = "string",required = true, paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","video_id"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/delete_like",method = RequestMethod.POST)
	public ResultEntity delete_like(String token, Long video_id) {
		
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		
		videoService.delete_like(loginVo.getUser_id(),video_id);
		return new ResultEntity();
	}
	
	/**
	 * 查询喜欢
	 */
	@ApiOperation(value = "查询喜欢", notes = "查询喜欢" , response = HxUserVideoLikeListVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "last_like_time", value = "查询的最后一条喜欢时间   0为第一页查询", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/like_query",method = RequestMethod.POST)
	public ResultEntity like_query(String token,@RequestParam(defaultValue="10") Integer pageSize 
			,@RequestParam(defaultValue="10") Long last_like_time) {
		
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", loginVo.getUser_id());
		map.put("last_like_time", last_like_time);
		map.put("pageSize", pageSize);
		List<HxUserVideoLikeListVo> list = videoService.like_query(map);
		return new ResultEntity(list);
	}
	
	
	/**
	 * 添加关注
	 */
	@ApiOperation(value = "添加关注", notes = "添加关注" , response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "follow_user_id", value = "被关注人的id", dataType = "string",required = true, paramType = "query"),
		
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","follow_user_id"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/add_follow",method = RequestMethod.POST)
	public ResultEntity add_follow(String token, Long follow_user_id) {
		
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		
		videoService.add_follow(loginVo.getUser_id(),follow_user_id);
		return new ResultEntity();
	}
	
	/**
	 * 删除关注
	 */
	@ApiOperation(value = "删除关注", notes = "删除关注" , response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "follow_user_id", value = "被关注人的id", dataType = "string",required = true, paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","follow_user_id"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/delete_follow",method = RequestMethod.POST)
	public ResultEntity delete_follow(String token, Long follow_user_id) {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		videoService.delete_follow(loginVo.getUser_id(),follow_user_id);
		return new ResultEntity();
	}
	
	/**
	 * 查询关注列表
	 */
	@ApiOperation(value = "查询关注列表", notes = "查询关注列表" , response = VideoFollowVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "last_follow_time", value = "查询的最后一条关注时间   0为第一页查询", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/query_follow",method = RequestMethod.POST)
	public ResultEntity query_follow(String token,Long last_follow_time, Integer pageSize) {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user_id", loginVo.getUser_id());
		map.put("last_follow_time", last_follow_time);
		map.put("pageSize", pageSize);
		List<VideoFollowVo> list = videoService.query_follow(map);
		return new ResultEntity(list);
	}
	
	/**
	 * 评论
	 */
	@ApiOperation(value = "评论", notes = "评论" , response = HxUserVideoComment.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "video_id", value = "小视频   id", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "content", value = "评论内容", dataType = "string",paramType = "query"),
		@ApiImplicitParam(name = "type", value = "1-评论 2-点赞   默认1", dataType = "string",paramType = "query"),
		@ApiImplicitParam(name = "parent_id", value = "回复的哪一条  0-不是回复  默认0", dataType = "string",paramType = "query"),
		@ApiImplicitParam(name = "parent_user_id", value = "回复的谁  0-不是回复 默认0", dataType = "string",paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","video_id"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/add_comment",method = RequestMethod.POST)
	public ResultEntity add_comment(String token, Long video_id,String content, Integer type, Long parent_id, Long parent_user_id) {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		HxUserVideoComment comment = videoService.comment(loginVo.getUser_id(),video_id,content,type,parent_id,parent_user_id);
		return new ResultEntity(comment);
	}
	
	/**
	 * 删除评论
	 */
	@ApiOperation(value = "删除评论", notes = "删除评论" , response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "video_comment_id", value = "评论id", dataType = "string",required = true, paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","video_comment_id"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/delete_comment",method = RequestMethod.POST)
	public ResultEntity delete_comment(String token, Long video_comment_id) {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		videoService.delete_comment(loginVo.getUser_id(),video_comment_id);
		return new ResultEntity();
	}
	
	/**
	 * 查询评论
	 */
	@ApiOperation(value = "查询评论", notes = "查询评论" , response = HxUserVideoCommentVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "video_id", value = "小视频id", dataType = "string",paramType = "query"),
		@ApiImplicitParam(name = "last_video_comment_id", value = "查询的最后一条评论id  默认0", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string", paramType = "query")
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","video_id"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "video/query_comment",method = RequestMethod.POST)
	public ResultEntity query_comment(@RequestParam(defaultValue="0") Long video_id ,Long last_video_comment_id ,
			@RequestParam(defaultValue="10") Integer pageSize) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("video_id", video_id);
		map.put("last_video_comment_id", last_video_comment_id);
		map.put("pageSize", pageSize);
		List<HxUserVideoCommentVo> list = videoService.query_comment(map);
		return new ResultEntity(list);
	}
	
	/**
	 * 小视频个人首页数据返回
	 */
	@ApiOperation(value = "小视频个人首页数据返回", notes = "小视频个人首页数据返回" , response = HxUserVideoBasicNumShow.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
	
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"})
	@RequestMapping(value = "video/query_own_basicNum",method = RequestMethod.POST)
	public ResultEntity query_own_basicNum(String token) {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		HxUserVideoBasicNumShow basicNumShow = videoService.query_own_basicNum(loginVo.getUser_id());
		return new ResultEntity(basicNumShow);
	}
	
	
	/**
	 * 小视频他人首页数据返回
	 */
	@ApiOperation(value = "小视频他人首页数据返回", notes = "小视频他人首页数据返回" , response = HxUserVideoOtherBasicNumShow.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "other_user_id", value = "用户id", dataType = "string",required = true,paramType = "query"),
	
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","other_user_id"})
	@RequestMapping(value = "video/query_other_basicNum",method = RequestMethod.POST)
	public ResultEntity query_other_basicNum(String token ,Long other_user_id) {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		if (other_user_id==loginVo.getUser_id()){
			throw new RuntimeException("接口调错！");
		}
		HxUserVideoOtherBasicNumShow basicNumShow = videoService.query_other_basicNum(loginVo.getUser_id(),other_user_id);
		return new ResultEntity(basicNumShow);
	}
	
	
}
