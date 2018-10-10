package com.hx.api.controller.user;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.user.Vo.BlackFriendUserVo;
import com.hwt.domain.entity.user.Vo.CommunicationListFriendVo;
import com.hwt.domain.entity.user.Vo.FriendApplyVo;
import com.hwt.domain.entity.user.Vo.FriendInfoVo;
import com.hwt.domain.entity.user.Vo.FriendUserVo;
import com.hwt.domain.entity.user.Vo.InitializationFriendVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultCode;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.user.service.HxUserApplyFriendService;
import com.hx.user.service.HxUserBlackService;
import com.hx.user.service.HxUserFriendService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 好友模块
 * @author JIAO_LIU_BABA
 */
@Api(value = "API - FriendInfoController", description = "好友管理接口")
@RestController
public class FriendInfoController {
	
	
	@Autowired
	private HxUserFriendService hxUserFriendService;
	
	@Autowired
	private HxUserApplyFriendService hxUserApplyFriendService;
	
	@Autowired
	private HxUserBlackService hxUserBlackService;
	
	/**
	 * 获取我的 - 好友列表信息(联系人列表)
	 * @param token - 验证登录状态 - Y
	 * @return 
	 * @throws Exception 
	 */
	@ApiOperation(value = "获取我的 - 好友列表信息", notes = "获取我的 - 好友列表信息", response = FriendUserVo.class)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",paramType = "query"),
	})
	@ValidateParam(field={"token"},checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "im/myFriendList", method = RequestMethod.POST)
	public ResultEntity myFriendList(String token) throws Exception{
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		List<FriendUserVo> list = hxUserFriendService.getFriends(loginVo.getUser_id());
		return new ResultEntity(list, ResultCode.SUCCESS);
	}
	
	/**
	 * 获取我的 - 好友申请表信息 - 列表
	 * @param token - 验证登录状态 - Y
	 * @return
	 */
    @ApiOperation(value = "获取我的 - 好友申请表信息 - 列表", notes = "好友申请表信息", response = FriendApplyVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"},checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "im/friendApplyList",method = RequestMethod.POST)
	public ResultEntity friendApplyList(String token){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		List<FriendApplyVo> list =  hxUserApplyFriendService.getApplyUsers(loginVo.getUser_id());
		return new ResultEntity(list, ResultCode.SUCCESS);
	}
    
    /**
     * 获取黑名单列表
     * @param token 用户验证TOKEN
     * @return
     */
    @ApiOperation(value = "获取黑名单列表", notes = "获取黑名单列表", response = BlackFriendUserVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/getDefriendFriends",method = RequestMethod.POST)
	public ResultEntity getDefriendFriends(String token) {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
        List<BlackFriendUserVo> list =  hxUserBlackService.getBlackfriends(loginVo.getUser_id());
        return new ResultEntity(list, ResultCode.SUCCESS);
    }
    
    /**
     * 申请好友
     * @param token     用户验证TOKEN
     * @param friend_id  好友ID
     * @param remark    备注
     * @return
     */
    @ApiOperation(value = "申请好友", notes = "申请好友", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "friend_id", value = "好友ID", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "friend_remark", value = "好友备注", dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "friend_see_me_space", value = "是否让朋友看我的朋友圈吧 0-否  1-是 默认是1", dataType = "string",required = false,paramType = "query"),
    })
    @ValidateParam(field={"token","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/applyFriend",method = RequestMethod.POST)
    public ResultEntity applyFriend(String token,Long friend_id,String remark,String friend_remark,Byte friend_see_me_space) throws Exception {
    	
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
        if (friend_id.equals(loginVo.getUser_id())){
			throw new RuntimeException("操作用户错误");
		}

    	if (StringUtils.isNoneBlank(friend_remark) && friend_remark.length()>16){
			throw new RuntimeException("备注不能超过16个字");
		}
    	hxUserApplyFriendService.apply(loginVo.getUser_id(),friend_id,remark, loginVo.getIm_id(),friend_remark,friend_see_me_space);
        return new ResultEntity(ResultCode.SUCCESS);
    }
    
    /**
     * 加入黑名单
     * @param token     用户TOKEN
     * @param friend_id  黑名单用户ID
     * @param remark    备注
     * @return
     * @throws Exception 
     */
    @ApiOperation(value = "加入黑名单", notes = "加入黑名单", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "friend_id", value = "黑名单用户ID", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "string",required = false),
    })
    @ValidateParam(field={"token","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/defriend",method = RequestMethod.POST)
    public ResultEntity defriend(String token,Long friend_id,String remark) throws Exception{
     	LoginVo loginVo = BaseUtil.getLoginVo(token);
        if (friend_id.equals(loginVo.getUser_id())){
			throw new RuntimeException("操作用户错误");
		}
        hxUserBlackService.defriend(loginVo.getUser_id(),friend_id,remark,loginVo.getIm_id());
        
        return new ResultEntity(ResultCode.SUCCESS);
    }
    
    /**
     * 恢复黑名单用户
     * @param token     用户TOKEN - Y
     * @param friend_id  黑名单用户ID - Y
     * @return
     */
    @ApiOperation(value = "恢复黑名单用户", notes = "恢复黑名单用户", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "friend_id", value = "黑名单用户ID", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/removeDefriend",method = RequestMethod.POST)
    public ResultEntity removeDefriend(String token,Long friend_id) throws Exception{
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	hxUserBlackService.removeDefriend(loginVo.getUser_id(),friend_id,loginVo.getIm_id());
        return new ResultEntity(ResultCode.SUCCESS);
    }
    
    /**
     * 同意好友申请
     * @param token             用户TOKEN - Y
     * @param friend_id          好友ID - Y
     * @param friend_remark      好友备注
     * @param friendSeeMeSpace  是否让好友查看我的朋友圈 0-是 1-不允许
     * @param remark            备注
     * @return
     */
    @ApiOperation(value = "同意好友申请", notes = "同意好友申请", response = ResultEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "friend_id", value = "好友ID", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "friend_remark", value = "好友备注", dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "friend_see_me_space", value = "是否让好友查看我的朋友圈 0-否 1-是", dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "remark", value = "备注", dataType = "string",required = false,paramType = "query"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功!")
    })
    @ValidateParam(field={"token","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/agree",method = RequestMethod.POST)
    public ResultEntity agree(String token, Long friend_id, String friend_remark, Integer friend_see_me_space, String remark) throws Exception {
    	if (StringUtils.isNoneBlank(friend_remark) && friend_remark.length()>16){
			throw new RuntimeException("备注不能超过16个字");
		}
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	hxUserApplyFriendService.agree(loginVo.getUser_id(),friend_id,friend_remark,friend_see_me_space == null ? 0 : 1,remark,loginVo.getIm_id());
        return new ResultEntity(ResultCode.SUCCESS);
    }
    
    /**
     * 删除好友申请
     * @param token             用户TOKEN - Y
     * @param friend_id          好友ID - Y
     * @return
     */
    @ApiOperation(value = "删除好友申请", notes = "删除好友申请", response = ResultEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "friend_id", value = "好友ID", dataType = "string",required = true,paramType = "query"),
    })
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "成功!")
    })
    @ValidateParam(field={"token","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/applyRefuse",method = RequestMethod.POST)
    public ResultEntity deleteApply(String token, Long friend_id) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	hxUserApplyFriendService.applyRefuse(loginVo.getUser_id(),friend_id);
        return new ResultEntity(ResultCode.SUCCESS);
    }
    
    /**
	 * 查看 - 好友资料 -通过云信id
	 * @param token  - 验证登录状态 - Y
	 * @param im_id - 好友im_id- Y
	 * @return
	 */
    @ApiOperation(value = "好友资料-通过云信id", notes = "好友资料-通过云信id", response = FriendInfoVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "im_id", value = "好友im_id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","im_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/friendInfoByImId",method = RequestMethod.POST)
	public ResultEntity friendInfo(String token,String im_id){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	
        FriendInfoVo friendInfoVo = hxUserFriendService.getFriendInfo(im_id,loginVo.getUser_id());
		return new ResultEntity(friendInfoVo,ResultCode.SUCCESS);
	}
    
    /**
     * 删除好友
     * @param token     用户ID - Y
     * @param friend_id  好友ID - Y
     * @return
     * @throws Exception 
     */
    @ApiOperation(value = "删除好友", notes = "删除好友", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "friend_id", value = "好友ID", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/removeFriend",method = RequestMethod.POST)
    public ResultEntity removeFriend(String token, Long friend_id) throws Exception {
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
        if (friend_id.equals(loginVo.getUser_id())){
			throw new RuntimeException("操作用户错误");
		}
        hxUserFriendService.deleteFriend(loginVo.getUser_id(),friend_id,loginVo.getIm_id());
        return new ResultEntity(ResultCode.SUCCESS);
    }
    
    /** 
     * 修改好友备注
     * @param token		用户ID - Y
     * @param friend_id	好友ID - Y
     * @param friend_remark	备注
     * @return
     */
    @ApiOperation(value = "修改好友备注", notes = "修改好友备注", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "friend_id", value = "好友ID", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "friend_remark", value = "好友备注", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/editRemarks",method = RequestMethod.POST)
    public ResultEntity editRemarks(String token,Long friend_id, String friend_remark) {
    	
    	if (StringUtils.isNoneBlank(friend_remark) && friend_remark.length()>16){
			throw new RuntimeException("备注不能超过16个字");
		}
    	
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	hxUserFriendService.updateFriendRemark(loginVo.getUser_id(),friend_id,friend_remark);
    	return new ResultEntity(ResultCode.SUCCESS);
    }
    
    /**
     * 搜索账号
     * @param token 用户ID - Y
     * @param field 账号或者电话号码
     * @return
     */
    @ApiOperation(value = "搜索账号", notes = "搜索账号", response = FriendInfoVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "field", value = "账号或者电话号码", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","field"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/searchAccount",method = RequestMethod.POST)
    public ResultEntity searchAccount(String token,String field){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	FriendInfoVo friendInfoVo = hxUserFriendService.searchAccountByField(field,loginVo.getUser_id());
	   	return new ResultEntity(friendInfoVo,ResultCode.SUCCESS);
    }
    
    /**
	 * 查看 - 好友资料 -通过id
	 * @param token  - 验证登录状态 - Y
	 * @param im_id - 好友im_id- Y
	 * @return
	 */
    @ApiOperation(value = "查看 - 好友资料 -通过friend_id", notes = "查看 - 好友资料 -通过friend_id", response = FriendInfoVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "friend_id", value = "好友id", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","friend_id"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/friendInfoByFriendId",method = RequestMethod.POST)
    public ResultEntity friendInfoByFriendId(String token,Long friend_id){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	FriendInfoVo friendInfoVo = hxUserFriendService.searchAccountByFriendId(friend_id,loginVo.getUser_id());
    	return new ResultEntity(friendInfoVo,ResultCode.SUCCESS);
    }
    /**
     * 初始化返回好友对我的 删除和拉黑 了 的im_id
     * @param token  - 验证登录状态 - Y
     * @return
     */
    @ApiOperation(value = "初始化返回好友对我的 删除和拉黑 了 的im_id", notes = "初始化返回好友对我的 删除和拉黑 了 的im_id", response = InitializationFriendVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/InitializationFriendToMe",method = RequestMethod.POST)
    public ResultEntity initializationFriendToMe(String token){
    	LoginVo loginVo = BaseUtil.getLoginVo(token);
    	InitializationFriendVo  initializationFriendVo = hxUserFriendService.initializationFriendToMe(loginVo.getUser_id());
    	return new ResultEntity(initializationFriendVo,ResultCode.SUCCESS);
    }
    
    /**
     * 通过电话号码获取获取注册过淮信的用户
     * @param token  - 验证登录状态 - Y
     * @param phones  - 电话号码 - Y
     * @return
     */
    @ApiOperation(value = "通过电话号码获取获取注册过淮信的用户", notes = "通过电话号码获取获取注册过淮信的用户", response = CommunicationListFriendVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "phones", value = "通讯录电话号码", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"},checkedType = CheckedType.TOKEN)
    @RequestMapping(value = "im/findHxUserByPhone",method = RequestMethod.POST)
    public ResultEntity findHxUserByPhone(String token,String[] phones){
    	
    	List<CommunicationListFriendVo> communicationListFriendVos = hxUserFriendService.findHxUserByPhone(phones);
    	return new ResultEntity(communicationListFriendVos,ResultCode.SUCCESS);
    }
    
}	
