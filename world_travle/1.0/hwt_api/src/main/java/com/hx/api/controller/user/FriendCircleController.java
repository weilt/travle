package com.hx.api.controller.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.mg.user.FriendCircle;
import com.hwt.domain.entity.mg.user.FriendCircleComment;
import com.hwt.domain.entity.user.Vo.FriendCircleVo;
import com.hwt.domain.entity.user.Vo.InitializationFriendVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultCode;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.user.service.FriendCircleService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "API - FriendCircleController", description = "朋友圈操作接口")
@RestController
public class FriendCircleController {

	@Autowired
	private FriendCircleService friendCircleService;

	/**
	 * 发表朋友圈
	 * 
	 * @param friendCircle
	 * @param token
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "发布朋友圈", notes = "发布朋友圈", response = FriendCircleVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "type", value = "类型  默认1-文本 2-图片 3-视频 4-链接 5-其他", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "content", value = "内容", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "description", value = "描述", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "can_see_user_id", value = "哪些人可以看 默认1-所有人能看  0-只能自己看  y1yy2yy3y----1,2,3能看   n1nn2nn3n----1,2,3不能看  只能选一种情况  ", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "remind_user_id", value = "提醒谁看 1,2,3  提醒1,2,3看", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "publish_address", value = "发表地址", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "longitude", value = "发朋友圈经度", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "latitude", value = "发朋友圈纬度", dataType = "string", required = false, paramType = "query"), })
	@ValidateParam(field = { "token", "content" }, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "friendCircle/insert", method = RequestMethod.POST)
	public ResultEntity friendCircle_insert(String token, @RequestParam(defaultValue = "1") Integer type,
			String content, String description, @RequestParam(defaultValue = "1") String can_see_user_id,
			String publish_address,String remind_user_id, String longitude, String latitude) throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);

		FriendCircle friendCircle = new FriendCircle();
		friendCircle.setCan_see_user_id(can_see_user_id);
		friendCircle.setContent(content);
		friendCircle.setCreate_time(new Date().getTime());
		friendCircle.setDescription(description);
		friendCircle.setLatitude(latitude);
		friendCircle.setLongitude(longitude);
		friendCircle.setRemind_user_id(remind_user_id);
		friendCircle.setType(type);
		friendCircle.setUser_id(loginVo.getUser_id());
		friendCircle.setPublish_address(publish_address);

		friendCircleService.friendCircle_insert(friendCircle,loginVo);
		return new ResultEntity(friendCircle);
	}

	/**
	 * 删除朋友圈
	 * 
	 * @param token
	 * @param friend_circle_id
	 *            朋友圈id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "删除朋友圈", notes = "删除朋友圈", response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "friend_circle_id", value = "朋友圈id", dataType = "string", required = false, paramType = "query"), })
	@ValidateParam(field = { "token", "friend_circle_id" }, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "friendCircle/delete", method = RequestMethod.POST)
	public ResultEntity friendCircle_delete(String token, Long friend_circle_id) throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		friendCircleService.friendCircle_delete(loginVo.getUser_id(), friend_circle_id);
		return new ResultEntity();
	}

	/**
	 * 查看朋友圈
	 * 
	 * @param token
	 * @param last_friend_circle_id
	 *           查询的最后一条朋友圈id  默认0
	 * @param pageSize
	 *            每页大小 默认10
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看朋友圈", notes = "查看朋友圈", response = FriendCircleVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "last_friend_circle_id", value = "查询的最后一条朋友圈id  默认0", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string", paramType = "query"), })
	@ValidateParam(field = { "token" }, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "friendCircle/query", method = RequestMethod.POST)
	public ResultEntity friendCircle_query(String token, @RequestParam(defaultValue = "0") Long last_friend_circle_id,
			@RequestParam(defaultValue = "10") Integer pageSize) throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		List<FriendCircleVo> friendCircleVos = friendCircleService.friendCircle_query(loginVo.getUser_id(), last_friend_circle_id,
				pageSize);
		return new ResultEntity(friendCircleVos);
	}

	/**
	 * 查看好友的朋友圈
	 * 
	 * @param token
	 * @param friend_id
	 *            好友id
	 * @param last_friend_circle_id
	 *            查询的最后一条朋友圈id  默认0
	 * @param pageSize
	 *            每页大小 默认10
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看好友的朋友圈", notes = "查看好友的朋友圈", response = FriendCircleVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "friend_id", value = "好友id", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "last_friend_circle_id", value = "查询的最后一条朋友圈id  默认0", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string", paramType = "query"), })
	@ValidateParam(field = { "token", "friend_id" }, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "friendCircle/query_friend", method = RequestMethod.POST)
	public ResultEntity friendCircle_query_friend(String token, @RequestParam(defaultValue = "0") Long last_friend_circle_id,
			@RequestParam(defaultValue = "10") Integer pageSize, Long friend_id) throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		List<FriendCircleVo> friendCircleVos = friendCircleService.friendCircle_query_friend(loginVo.getUser_id(),
				last_friend_circle_id, pageSize, friend_id);
		return new ResultEntity(friendCircleVos);
	}

	/**
	 * 查看自己的朋友圈
	 * 
	 * @param token
	 * @param last_friend_circle_id
	 *            查询的最后一条朋友圈id  默认0
	 * @param pageSize
	 *            每页大小 默认10
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看自己的朋友圈", notes = "查看自己的朋友圈", response = FriendCircleVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "last_friend_circle_id", value = "查询的最后一条朋友圈id  默认0", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string", paramType = "query"), })
	@ValidateParam(field = { "token" }, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "friendCircle/query_own", method = RequestMethod.POST)
	public ResultEntity friendCircle_query_own(String token, @RequestParam(defaultValue = "0") Long last_friend_circle_id,
			@RequestParam(defaultValue = "10") Integer pageSize) throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		List<FriendCircleVo> friendCircleVos = friendCircleService.friendCircle_query_own(loginVo.getUser_id(),
				last_friend_circle_id, pageSize);
		return new ResultEntity(friendCircleVos);
	}
	
	 /**
     * 好友详情界面的朋友圈
     * @param token
     * @param friend_id
     * @return
     */
    @ApiOperation(value = "好友详情界面的朋友圈", notes = "好友详情界面的朋友圈", response = FriendCircleVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "friend_id", value = "朋友id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "friendCircle/userInfo",method = RequestMethod.POST)
    public ResultEntity friendCircle_userInfo(String token,Long friend_id){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	List<FriendCircleVo> friendCircleVos = friendCircleService.friendCircle_userInfo(loginVo.getUser_id(),friend_id);
    	return new ResultEntity(friendCircleVos);
    }
	/**
	 * 朋友圈评论
	 * @param token
	 * @param last_friend_circle_id
	 * @param pageSize
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "朋友圈评论", notes = "朋友圈评论", response = FriendCircleComment.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "friend_circle_id", value = "朋友圈id", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "type", value = "类型  1-文本  2-点赞  默认1", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "content", value = "内容", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "parent_id", value = "父id  0-顶级  默认0", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "parent_user_id", value = "回复谁？ 0-不是回复  默认0 ", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "relation", value = "关联 ，共同好友才能看  如r1rr2rr3r  1,2,3能看", dataType = "string", paramType = "query"), })
	@ValidateParam(field = { "token","friend_circle_id" }, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "friendCircle/comment", method = RequestMethod.POST)
	public ResultEntity friendCircle_comment(String token, Long friend_circle_id, String content, String relation, 
			@RequestParam(defaultValue = "1") Integer type,
			@RequestParam(defaultValue = "0") Long parent_id, 
			@RequestParam(defaultValue = "0") Long parent_user_id)
					throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		FriendCircleComment friendCircleComment = new FriendCircleComment();
		friendCircleComment.setContent(content);
		friendCircleComment.setCreate_time(new Date().getTime());
		friendCircleComment.setFriend_circle_id(friend_circle_id);
		friendCircleComment.setParent_id(parent_id);
		friendCircleComment.setParent_user_id(parent_user_id);
		friendCircleComment.setRelation(relation);
		friendCircleComment.setType(type);
		friendCircleComment.setUser_id(loginVo.getUser_id());
		
		friendCircleComment = friendCircleService.friendCircle_comment(friendCircleComment);
		return new ResultEntity(friendCircleComment);
	}
	
	
	/**
	 * 删除朋友圈评论
	 * @param token
	 * @param comment_id 		评论id
	 * @param friend_circle_id	朋友圈id
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = " 删除朋友圈评论", notes = " 删除朋友圈评论", response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string", paramType = "query"),
			@ApiImplicitParam(name = "comment_id", value = "评论id", dataType = "string",required = true, paramType = "query"),
			@ApiImplicitParam(name = "friend_circle_id", value = "朋友圈id", dataType = "string", required = true, paramType = "query"), })

	@ValidateParam(field = { "token", "friend_circle_id" }, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "friendCircle_comment/delete", method = RequestMethod.POST)
	public ResultEntity friendCircle_comment_delete(String token, Long comment_id, Long friend_circle_id) throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		friendCircleService.friendCircle_comment_delete(loginVo.getUser_id(), comment_id, friend_circle_id);
		return new ResultEntity();
	}
	
	
	
    /**
     * 是否让朋友看我的朋友圈
     * @param token  - 验证登录状态 - Y
     * @param friend_see_me_space	是否让朋友看我的朋友圈吧 0-否  1-是 默认是1
     * @param friend_id		朋友id
     * @return
     */
    @ApiOperation(value = "是否让朋友看我的朋友圈", notes = "是否让朋友看我的朋友圈", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "friend_see_me_space", value = "是否让朋友看我的朋友圈吧 0-否  1-是 默认是1", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "friend_id", value = "朋友id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","friend_see_me_space","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "friendCircle/let_friend_see_friendCircle",method = RequestMethod.POST)
    public ResultEntity let_friend_see_friendCircle(String token,Integer friend_see_me_space, Long friend_id){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	friendCircleService.let_friend_see_friendCircle(loginVo.getUser_id(),friend_see_me_space,friend_id);
    	return new ResultEntity();
    }
    
    /**
     * 是否看朋友的朋友圈
     * @param token  - 验证登录状态 - Y
     * @param me_see_friend_space	我是否能看朋友的朋友圈  0-否  1-能 默认能
     * @param friend_id		朋友id
     * @return
     */
    @ApiOperation(value = "是否看朋友的朋友圈", notes = "是否看朋友的朋友圈", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "me_see_friend_space", value = "我是否能看朋友的朋友圈  0-否  1-能 默认能", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "friend_id", value = "朋友id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","me_see_friend_space","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "friendCircle/see_friend_friendCircle",method = RequestMethod.POST)
    public ResultEntity see_friend_friendCircle(String token,Integer me_see_friend_space, Long friend_id){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	friendCircleService.see_friend_friendCircle(loginVo.getUser_id(),me_see_friend_space,friend_id);
    	return new ResultEntity();
    }
    
    /**
     * 查看某一个朋友圈详情
     * @param token  - 验证登录状态 - Y
     * @param me_see_friend_space	我是否能看朋友的朋友圈  0-否  1-能 默认能
     * @param friend_id		朋友id
     * @return
     */
    @ApiOperation(value = "查看某一个朋友圈详情", notes = "查看某一个朋友圈详情", response = FriendCircleVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "friend_circle_id", value = "朋友圈id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","friend_circle_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "friendCircle/friendCircle_info",method = RequestMethod.POST)
    public ResultEntity friendCircle_info(String token,Long friend_circle_id){
    	
    	FriendCircleVo friendCircleVo = friendCircleService.query_friendCircle_info(friend_circle_id);
    	return new ResultEntity(friendCircleVo);
    }
    
}
