package com.hx.api.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.user.HxUserConfig;
import com.hwt.domain.entity.user.HxUserInfo;
import com.hwt.domain.entity.user.Vo.HxUserConfigVo;
import com.hwt.domain.entity.user.Vo.UserVo;
import com.hx.api.aliyun.file.app.APPUpload;
import com.hx.api.aliyun.file.app.AliyunAPPSTS;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.user.service.AccountInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@Api(value = "API - LoginController", description = "用户信息操作接口")
@RestController
public class AccountInfoController {
	@Autowired
	private AccountInfoService accountInfoService;
	
	/**
	 * 获取当前用户信息
	 * @param token - 验证登录状态 - Y
	 * @return
	 */
	@ApiOperation(value = "获取当前用户信息", notes = "获取当前用户信息" , response = UserVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
	})
	
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "account/accountInfo",method = RequestMethod.POST)
	public ResultEntity accountInfo(String token){
		Object o = accountInfoService.accountInfo(token);
		return new ResultEntity(o);
	}
	
	
	/**
	 * 修改用户信息
	 * @param token - 验证登录状态 - Y
	 * @param hxUserInfo - 用户信息 - Y
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "修改用户信息" , notes = "修改用户信息", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "nickname", value = "用户昵称 - （最大16个字符）", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_autograph", value = "个性签名", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "username", value = "真实姓名", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_age", value = "用户年龄", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_icon", value = "用户头像", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_emotion", value = "用户情感 0-保密 1-单身2-离异 3-热恋 4-已婚", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "friend_circle_cover", value = "朋友圈封面", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_birthday", value = "用户生日", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_sex", value = "用户性别 0-其他 1-男 2-女", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_profession", value = "用户职业", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_areaState", value = "用户地区 - 国 标识", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_area_state_name", value = "用户地区 - 国 - 名称", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_area_province", value = "用户地区 - 省", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_area_province_name", value = "用户地区 - 省 - 名称", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_area_city", value = "用户地区 - 市", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_area_city_name", value = "用户地区 - 市 - 名称", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_area_district", value = "用户地区 - 区", dataType = "string",required = false,paramType = "query"),
			@ApiImplicitParam(name = "user_area_district_name", value = "用户地区 - 区 - 名称", dataType = "string",required = false,paramType = "query"),
	})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "account/editAccount",method = RequestMethod.POST)
	public ResultEntity editAccount(String token,HxUserInfo hxUserInfo) throws Exception{
		accountInfoService.editAccount(token, hxUserInfo);
		return new ResultEntity();
	}
	
	/**
	 * 获取图片上传token
	 * @param token - 验证登录状态 - Y
	 * @return
	 */
	@ApiOperation(value = "获取图片上传token", notes = "获取图片上传token", response = AliyunAPPSTS.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
	})
	
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "account/getAppToken",method = RequestMethod.POST)
	public ResultEntity getAppToken(){
		
		return new ResultEntity(APPUpload.getToken());
	}
	/**
	 * 修改用户账号
	 * @param token - 验证登录状态 - Y
	 * @param account - 用户账号 - Y
	 * @return
	 * @throws Exception 
	 */
	@ApiOperation(value = "修改用户账号" , notes = "修改用户账号", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "account", value = "用户账号 - （最大16个字符）", dataType = "string",required = false,paramType = "query"),
	})
	@ValidateParam(field={"token","account"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "account/updateAccount",method = RequestMethod.POST)
	public ResultEntity updateAccount(String token,String account) throws Exception{
		accountInfoService.updateAccount(token, account);
		return new ResultEntity();
	}

	/**
	 * 获取用户的配置信息
	 * @param token
	 * @return
	 */
	@ApiOperation(value = "获取当前用户配置信息" , notes = "获取当前用户配置信息", response = HxUserConfigVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
	})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "account/userConfig",method = RequestMethod.POST)
	public ResultEntity userConfig(String token) {
		HxUserConfigVo hxUserConfigVo = accountInfoService.queryById(token);
		return new ResultEntity(hxUserConfigVo);
	}

	/**
	 * 修改当前用户配置信息
	 * @param token 登录标识
	 * @param hxUserConfig 配置信息
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "修改当前用户配置信息" , notes = "修改当前用户配置信息", response = String.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "communication_add_me_validate", value = "加我需要验证 0-不需要 1-需要", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "communication_to_me_recommend_communication", value = "向我推荐通讯录朋友 0-不可以 1-可以", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "friend_circle_stranger_see_10", value = "陌生人能看10条 0-不可以 1-可以", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "friend_circle_friend_see_day", value = "允许朋友查看朋友圈的范围 0-全部 1-3天 2-半年", dataType = "string",required = true,paramType = "query"),
	})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "account/updateUserConfig",method = RequestMethod.POST)
	public ResultEntity updateUserConfig(String token,HxUserConfig hxUserConfig) throws Exception{
		accountInfoService.updateAuthority(token, hxUserConfig);
		return new ResultEntity();
	}
	
	/**
	 * 修改密码
	 * @param token
	 * @param newpassword
	 * @param oldpassword
	 * @param type
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "修改当前用户密码" , notes = "修改当前用户密码", response = String.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "newpassword", value = "新密码", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "oldpassword", value = "旧密码", dataType = "string",required = false,paramType = "query"),
		@ApiImplicitParam(name = "type", value = "标识 1-修改密码 2-验证码登录修改密码", dataType = "string",required = true,paramType = "query"),
	})
	@ValidateParam(field={"token","newpassword","type"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "account/updatePassWord",method = RequestMethod.POST)
	public ResultEntity updatePassWord(String token,String newpassword,String oldpassword,int type) throws Exception{
		accountInfoService.updatePassWord(token, newpassword, oldpassword, type);
		return new ResultEntity();
	}

	/**
	 * 根据验证码修改密码
	 * @param newpassword
	 * @param phone
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "根据验证码修改当前用户密码" , notes = "根据验证码修改当前用户密码", response = String.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "newpassword", value = "新密码", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "phone", value = "电话号码", dataType = "string",required = false,paramType = "query"),
		@ApiImplicitParam(name = "code", value = "验证码", dataType = "string",required = true,paramType = "query"),
	})
	@ValidateParam(field={"newpassword","phone","code"},checkedType={CheckedType.NULL})
	@RequestMapping(value = "account/updatePassWordByCode",method = RequestMethod.POST)
	public ResultEntity updatePassWordByCode(String newpassword,String phone,String code) throws Exception{
		accountInfoService.updatePassWordByCode(newpassword, phone, code);
		return new ResultEntity();
	}
	
	/**
	 * 查看忘记密码的验证码
	 * @param token
	 * @param smsVerify
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	@ApiOperation(value = "查看忘记密码的验证码" , notes = "查看忘记密码的验证码", response = String.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "smsVerify", value = "验证码", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "phone", value = "电话号码", dataType = "string",required = true,paramType = "query"),
	})
	@ValidateParam(field={"token","smsVerify","phone"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "account/updatePassWordToCode",method = RequestMethod.POST)
	public ResultEntity updatePassWordToCode(String token,String smsVerify,String phone) throws Exception{
		accountInfoService.updatePassWordToCode(token, smsVerify, phone);
		return new ResultEntity();
	}
}
