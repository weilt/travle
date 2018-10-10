package com.hx.api.controller.user;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.user.HxUserTeam;
import com.hwt.domain.entity.user.HxUserTeamUser;
import com.hwt.domain.entity.user.Vo.HxUserTeamVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.user.service.HxUserTeamService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 群操作
 * 
 * @author Administrator
 *
 */
@RestController
@Api(value = "API - HxUserTeamController", description = "群操作")
public class HxUserTeamController {
	@Autowired
	private HxUserTeamService hxUserTeamService;

	/**
	 * @param token			用户TOKEN
	 * @param team_id		群id
	 * @param name			群名称
	 * @param intro			群描述
	 * @param msg			邀请发送的文字
	 * @param announcement	群公告
	 * @param icon			群头像
	 * @param im_ids		用户im_id集
	 * @param magree		管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群  默认0
	 * @param beinvitemode	被邀请人同意方式，0-需要同意(默认),1-不需要同意
	 * @param invitemode	谁可以邀请他人入群，0-管理员(默认),1-所有人
	 * @param uptinfomode	谁可以修改群资料，0-管理员(默认),1-所有人
	 * @return
	 */
	@ApiOperation(value = "创建群", notes = "创建群", response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "team_id", value = "群id", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "name", value = "群名称", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "intro", value = "群描述", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "msg", value = "邀请发送的文字", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "announcement", value = "群公告", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "magree", value = "管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群  默认0", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "icon", value = "群头像", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "beinvitemode", value = "被邀请人同意方式，0-需要同意(默认),1-不需要同意", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "invitemode", value = "谁可以邀请他人入群，0-管理员(默认),1-所有人", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "uptinfomode", value = "谁可以修改群资料，0-管理员(默认),1-所有人", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "im_ids", value = "用户im_id集", dataType = "string", required = true, paramType = "query"),

	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功!") })
	@ValidateParam(field = { "token", "team_id","name" }, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "team/insert", method = RequestMethod.POST)
	public ResultEntity team_insert(String token, String team_id,String name, String intro, String msg, String icon, String[] im_ids, String announcement, 
			@RequestParam(defaultValue = "0")byte magree, 
			@RequestParam(defaultValue = "0")byte beinvitemode, 
			@RequestParam(defaultValue = "0")byte invitemode, 
			@RequestParam(defaultValue = "0")byte uptinfomode ) {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		
		HxUserTeam hxUserTeam = new HxUserTeam();
		hxUserTeam.setAnnouncement(announcement);
		hxUserTeam.setBeinvitemode(beinvitemode);
		hxUserTeam.setCreate_time(new Date());
		hxUserTeam.setIcon(icon);
		hxUserTeam.setIntro(intro);
		hxUserTeam.setInvitemode(invitemode);
		hxUserTeam.setMagree(magree);
		hxUserTeam.setTeam_id(team_id);
		hxUserTeam.setUptinfomode(uptinfomode);
		hxUserTeam.setOwner_id_im_id(loginVo.getIm_id());
		
		hxUserTeamService.insert_team(hxUserTeam,im_ids);
		return new ResultEntity();

	}
	
	/**
	 * 修改群信息
	 * @param token			用户TOKEN
	 * @param hxUserTeam
	 * @return
	 */
	@ApiOperation(value = "修改群", notes = "修改群", response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "team_id", value = "群id", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "name", value = "群名称", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "intro", value = "群描述", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "msg", value = "邀请发送的文字", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "announcement", value = "群公告", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "magree", value = "管理后台建群时，0不需要被邀请人同意加入群，1需要被邀请人同意才可以加入群  默认0", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "icon", value = "群头像", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "beinvitemode", value = "被邀请人同意方式，0-需要同意(默认),1-不需要同意", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "invitemode", value = "谁可以邀请他人入群，0-管理员(默认),1-所有人", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "uptinfomode", value = "谁可以修改群资料，0-管理员(默认),1-所有人", dataType = "string", required = false, paramType = "query"),

	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功!") })
	@ValidateParam(field = { "token", "team_id"}, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "team/update", method = RequestMethod.POST)
	public ResultEntity team_update(HxUserTeam hxUserTeam){
		hxUserTeamService.update_team(hxUserTeam);
		
		return new ResultEntity();
	}
	
	/**
	 * 初始化返回群信息
	 * @param token			用户TOKEN
	 * @param team_ids
	 * @return
	 */
	@ApiOperation(value = "初始化返回群信息", notes = "初始化返回群信息", response = HxUserTeamVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "team_ids", value = "群id集", dataType = "string", required = true, paramType = "query"),
		
	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功!") })
	@ValidateParam(field = { "token"}, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "team/list", method = RequestMethod.POST)
	public ResultEntity team_list(String[] team_ids){
		
		List<HxUserTeamVo> hxUserTeamVos = hxUserTeamService.query_HxUserTeamVo_by_team_ids(team_ids);
		return new ResultEntity(hxUserTeamVos);
	}
	
	/**
	 * 删除群
	 * @param token			用户TOKEN
	 * @param team_id
	 * @return
	 */
	@ApiOperation(value = "删除群", notes = "删除群", response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "team_id", value = "群id", dataType = "string", required = true, paramType = "query"),
		
	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功!") })
	@ValidateParam(field = { "token"}, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "team/delete", method = RequestMethod.POST)
	public ResultEntity team_delete(String team_id){
		
		hxUserTeamService.team_delete(team_id);
		return new ResultEntity();
	}
	
	/**
	 * 拉人进群
	 * @param token			用户TOKEN
	 * @param user_id_im_ids
	 * @param team_id		群id
	 * @return
	 */
	@ApiOperation(value = "拉人进群", notes = "拉人进群", response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "team_id", value = "群id", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "user_id_im_ids", value = "用户im_id集", dataType = "string", required = true, paramType = "query"),
		
	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功!") })
	@ValidateParam(field = { "token"}, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "team/add_users", method = RequestMethod.POST)
	public ResultEntity team_add_users(String team_id, String[] user_id_im_ids){
		
		hxUserTeamService.team_add_users(team_id,user_id_im_ids);
		return new ResultEntity();
	}
	
	/**
	 * 提人出群
	 * @param token			用户TOKEN
	 * @param user_id_im_ids
	 * @param team_id		群id
	 * @return
	 */
	@ApiOperation(value = "提人出群", notes = "提人出群", response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "team_id", value = "群id", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "user_id_im_ids", value = "用户im_id集", dataType = "string", required = true, paramType = "query"),
		
	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功!") })
	@ValidateParam(field = { "token"}, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "team/delete_users", method = RequestMethod.POST)
	public ResultEntity team_delete_users(String team_id, String[] user_id_im_ids){
		
		hxUserTeamService.team_delete_users(team_id,user_id_im_ids);
		return new ResultEntity();
	}
	
	/**
	 * 修改用户在群中的信息
	 * @param token			用户TOKEN
	 * @param hxUserTeamUser		
	 * @return
	 */
	@ApiOperation(value = " 修改用户在群中的信息", notes = " 修改用户在群中的信息", response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "team_id", value = "群id", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "user_id_im_id", value = "用户im_id", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "state", value = "状态      1-正常状态,2-禁言状态 ，3-永久禁言", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "state2_begin", value = "禁言开始时间", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "login_state2_end", value = "禁言结束时间 - 禁言状态才去判断这个", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "type", value = "身份  1-群主 2-管理员 3-普通成员", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "team_nick", value = "群昵称", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "is_shield", value = "消息面打扰 0 否 1 是", dataType = "string", required = true, paramType = "query"),
		@ApiImplicitParam(name = "display_team_nick", value = "显示群昵称 0 否 1 是", dataType = "string", required = true, paramType = "query"),
	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功!") })
	@ValidateParam(field = { "token","user_id_im_id","team_id"}, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "team/update_user", method = RequestMethod.POST)
	public ResultEntity team_update_user(String token, HxUserTeamUser hxUserTeamUser){
		
		hxUserTeamService.team_update_user(hxUserTeamUser);
		return new ResultEntity();
	}
	
	
}
