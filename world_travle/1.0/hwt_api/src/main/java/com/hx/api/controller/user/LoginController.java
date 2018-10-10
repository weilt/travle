package com.hx.api.controller.user;

import java.text.ParseException;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultCode;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.core.exception.BaseException;
import com.hx.core.redis.RedisCache;
import com.hx.core.wyim.entity.emu.SMSType;
import com.hx.user.service.LoginService;
import com.hx.user.service.SmsMessageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Created by RO on 2018/4/3.
 * 用户登录退出操作接口
 * 
 */
@Api(value = "API - LoginController", description = "用户登录退出操作接口")
@RestController
public class LoginController {
	
	@Autowired
	private LoginService loginService;
	
	@Autowired
	private SmsMessageService smsMessageService;
	
	
	/**
	 * 校验用户TOKEN
	 * @param token 用户TOKEN
	 * @return ResultEntity
	 */
	@ApiOperation(value = "校验用户TOKEN", notes = "校验用户TOKEN", response = LoginVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field = {"token"})
	@RequestMapping(value = "im/validateToken",method = RequestMethod.POST)
	public ResultEntity validateToken(String token) {
		LoginVo loginVo = loginService.validateToken(token);
		if(loginVo == null) return new ResultEntity(ResultCode.SESSION_OUT);
		return new ResultEntity(loginVo);
	}


    /**
     * 获取验证码
     * @param phone 电话号码
     * @param type  1-用户注册(前缀HXJLRegister) 2-用户登录(前缀HXJLLogin)  3-找回密码(前缀HXJLFindPass) 4-
     * @throws Exception 
     */
    @ApiOperation(value = "获取短信验证码", notes = "获取短信验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机号", dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "type", value = "默认1，1-用户注册(前缀HXJLRegister) 2-用户登录(前缀HXJLLogin)  3-找回密码(前缀HXJLFindPass) 4-下单（前缀HXJLOrder）"
            		+ "5-设置支付密码（前缀HXJLPaymentPassWord） 6-重置支付密码（前缀HXJLResetPaymentPassWord）7-零钱支付 8-提现"
			, dataType = "string",required = false,paramType = "query"),
    })

    @ApiResponses({@ApiResponse(code=200,message="userId:用户ID,userName:用户名称")})
    @ValidateParam(field = {"phone","phone"},checkedType = {CheckedType.NULL,CheckedType.MOBIL})
    @RequestMapping(value = "/getVcode",method = RequestMethod.GET)
    public ResultEntity getVcode(String phone,Integer type) throws Exception {
		type = type == null ? 1 : type;
		String code = "";
       switch (type) {
		   case 1 : code = smsMessageService.sendRegisterIdentifyCode(phone);break;
		   case 2 : code = smsMessageService.sendLoginIdentifyCode(phone);break;
		   case 3 : code = smsMessageService.sendFindPwdIdentifyCode(phone);break;
		   case 4 : code = smsMessageService.sendInsertOrderIdentifyCode(phone);break;
		   case 5 : code = smsMessageService.sendPaymentPassWordIdentifyCode(phone);break;
		   case 6 : code = smsMessageService.sendResetPaymentPassIdentifyCode(phone);break;
		   case 7 : code = smsMessageService.sendWalletPayIdentifyCode(phone);break;
		   case 8 : code = smsMessageService.sendPutForwardIdentifyCode(phone);break;
		   default: throw new BaseException("获取验证码失败!");
	   }
        return new ResultEntity();
    }
    
    /**
     * 验证验证码
     * @param phone 电话号码
     * @param type  1-用户注册(前缀HXJLRegister) 2-用户登录(前缀HXJLLogin)  3-找回密码(前缀HXJLFindPass) 4-
     * @param smsVerify 手机短信验证码
     * @throws Exception 
     */
    @ApiOperation(value = "验证验证码", notes = "验证验证码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "phone", value = "用户手机号", dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "type", value = "默认1，1-用户注册(前缀HXJLRegister) 2-用户登录(前缀HXJLLogin)  3-找回密码(前缀HXJLFindPass) 4-"
			, dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "smsVerify", value = "短信验证码 - Y", dataType = "string",required = true,paramType = "query")
    })

    @ApiResponses({@ApiResponse(code=200,message="userId:用户ID,userName:用户名称")})
    @ValidateParam(field = {"phone","phone","smsVerify"},checkedType = {CheckedType.NULL,CheckedType.MOBIL})
    @RequestMapping(value = "/verificationCode",method = RequestMethod.POST)
	public ResultEntity verificationCode(String phone,String smsVerify,Integer type){
    	
		String code = "";
		
		switch (type) {
		   case 1 : code = RedisCache.get(SMSType.register.smsTypePrefix + phone);break;
		   case 2 : code = RedisCache.get(SMSType.login.smsTypePrefix + phone);break;
		   case 3 : code = RedisCache.get(SMSType.findPass.smsTypePrefix + phone);break;
		   case 4 : code = RedisCache.get(SMSType.order.smsTypePrefix + phone);break;
		   default: throw new BaseException("获取验证码失败!");
	   }
		if(StringUtils.isBlank(code) || !code.equals(smsVerify)) {
			throw new BaseException("验证码输入错误!");
		}
		return new ResultEntity();
		
    }
    /**
     * 用户手机号+验证码登录
     * @param phone 手机号
     * @param smsVerify 手机短信验证码
     * @return
     * @throws ParseException 
     */
    @ApiOperation(value = "手机号 + 验证码登录", notes = "手机号 + 验证码登录", response = LoginVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "phone", value = "手机号码 - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "smsVerify", value = "短信验证码 - Y", dataType = "string",required = true,paramType = "query")
    })
    @ValidateParam(field = {"phone","phone","smsVerify"},checkedType = {CheckedType.NULL,CheckedType.MOBIL})
    @RequestMapping(value = "/loginToCode",method = RequestMethod.POST)
    public ResultEntity loginToCode(String phone, String smsVerify,HttpServletRequest request) throws ParseException {
    	Object o = loginService.imLogin( phone, smsVerify, request );
        return new ResultEntity(o);
    }


    /**
     * 用户手机号或者账号密码登录
     * @param acc 	登录手机号或者账号
     * @param pwd   密码
     * @return
     * @throws ParseException 
     */
    @ApiOperation(value = "用户手机密码登录", notes = "用户手机密码登录", response = LoginVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "acc", value = "登录手机号或者账号", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pwd", value = "登录密码", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field = {"acc","pwd"})
    @RequestMapping(value = "/loginToPwd",method = RequestMethod.POST)
    public ResultEntity loginToPwd(String acc, String pwd,HttpServletRequest request) throws ParseException {
    	Object o = loginService.imLogin_accPass( acc, pwd,  request );
		return new ResultEntity(o);
    }
    
    
    /**
	 * im - 注册
	 * @param phone - 电话 - Y
	 * @param smsVerify - 短信验证码 - Y
	 * @param userNickname - 昵称 - Y
	 * @param pass - 密码 - Y
	 * @return
	 */
	@ApiOperation(value = "im - 注册", notes = "im - 注册", response = String.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "phone", value = "电话 - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "smsVerify", value = "短信验证码 - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "nickname", value = "昵称 - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "pass", value = "密码 - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "referrer_accountId", value = "推荐人id- Y", dataType = "string",required = false,paramType = "query"),
	})
	@ValidateParam(field = {"phone","phone","smsVerify","nickname","pass"},
			checkedType = {CheckedType.NULL,CheckedType.MOBIL})
	@RequestMapping(value = "im/imRegister",method = RequestMethod.POST)
	public ResultEntity imRegister(String phone,String smsVerify,String nickname,String pass,Long referrer_accountId){
		String code = RedisCache.get(SMSType.register.smsTypePrefix + phone);
		
		if(StringUtils.isBlank(code) || !code.equals(smsVerify)) {
			throw new BaseException("验证码输入错误!");
		}
		
		
		Object o = loginService.imRegister( phone, nickname, pass,referrer_accountId );
		RedisCache.del(SMSType.register.smsTypePrefix + phone);
		return new ResultEntity(o);
	}
}
