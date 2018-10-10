package com.hx.bureau.controller.bureauadmin;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.hwt.domain.entity.bureau.HwTravelBureau;
import com.hx.bureau.base.ResultEntity;
import com.hx.bureau.service.cache.BureauUserCache;
import com.hx.bureau.service.cache.BureauUserCacheTools;
import com.hx.bureau.service.hx.BureauLoginService;
import com.hx.bureau.service.hx.BureauUserService;
import com.hx.bureau.validate.ValidateParam;
import com.hx.core.exception.BaseException;
import com.hx.core.logs.annotation.Log;
import com.hx.core.logs.annotation.Log.LogType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 旅行社登录注册管理
 * @author Administrator
 *
 */
@Api(value = "API - LoginController", description = "导游登录注册管理")
@RestController
public class BureauLoginController {
	
	@Autowired
	private BureauLoginService bureauLoginService;
	
	@Autowired
	private BureauUserService bureauUserService;
	/**
	 * 旅行社入驻
	 * @throws Exception 
	 */
	@ApiOperation(value = "旅行社入驻" , notes = "旅行社入驻", response = ResultEntity.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "company_name", value = "企业名称", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "license_no", value = "工商营业执照注册号码", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "legal_person", value = "法人姓名", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "legal_person_phome", value = "企业法人手机号", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "registered_capital", value = "注册资本", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "company_time", value = "成立时间", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "licen_validity_begin", value = "营业执照有效时期开始时间", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "licen_validity_end", value = "营业执照有效时期到期时间", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "taxpayer_state", value = "是否一般纳税人 0 否  1 是   默认：0", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "org_no", value = "组织机构代码证号", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "quality_deposit", value = "质量保证金（单位：万元）", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "address", value = "总部地址", dataType = "string",paramType = "query"),
            @ApiImplicitParam(name = "description", value = "简介描述", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "contacts_name", value = "联系人", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "contacts_phome", value = "联系人手机号", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "contacts_emil", value = "联系人邮箱", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "license_url", value = "营业执照", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "busine_licen_url", value = "经营许可证", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "duty_policy_url", value = "旅行社责任险保单", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "identity_positive", value = "法人身份证正面", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "identity_negative", value = "法人身份证反面", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "bureau_bank_account", value = "旅行社银行账户", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "bank_name", value = "旅行社银行名称", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"company_name","license_no","legal_person","legal_person_phome","registered_capital","company_time_str","licen_validity_begin_str",
    		"licen_validity_end_str","taxpayer_state","org_no","description","contacts_name","contacts_phome","contacts_emil","license_url",
    		"busine_licen_url","duty_policy_url","identity_positive","identity_negative","bureau_bank_account","bank_name"})
	@PostMapping("/bureau_enter")
	public ResultEntity enter(HwTravelBureau hwTravelBureau,String company_time_str,String licen_validity_begin_str,String licen_validity_end_str) throws Exception{
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		hwTravelBureau.setCompany_time(simpleDateFormat.parse(company_time_str).getTime());
		hwTravelBureau.setLicen_validity_begin(simpleDateFormat.parse(licen_validity_begin_str).getTime());
		hwTravelBureau.setLicen_validity_end(simpleDateFormat.parse(licen_validity_begin_str).getTime());
		bureauLoginService.enter(hwTravelBureau);
		
		return new ResultEntity("200","申请成功，等待审核！");
		
	}
	
	/**
	 * 登录 
	 * @param legal_person_phome 企业法人手机号
	 * @param password 密码
	 * @return
	 */
	@Log(logType = LogType.LOGIN, dec = "登录 ")
	@RequestMapping("back/login")
	public ResultEntity login(String legal_person_phome,String password,HttpServletRequest request){
		bureauLoginService.login(legal_person_phome,password,request);
		
		return new ResultEntity();
		
	}
	
	
	
	@RequestMapping("back")
	public ModelAndView back(HttpServletRequest request,HttpServletResponse response) throws IOException{
		BureauUserCacheTools.removeSession(request);
		response.sendRedirect("/login.html");
		return null;
	}
	
	
	/**
	 * 修改密码
	 * @param oldPassword 旧密码
	 * @param password - 新密码
	 * @param conPassword - 确认密码
	 * @return
	 */
	@Log(logType = LogType.OPERATION, dec = "修改密码")
	@ValidateParam(field={"oldPassword","password","conPassword"},
			message = {"旧密码不能为空","新密码不能为空","确认密码不能为空"})
	@RequestMapping("/passWord")
	public ResultEntity user_editPassWord(String oldPassword,String password,String conPassword,HttpServletRequest request){
		
		BureauUserCache bureauUserCache = BureauUserCacheTools.getSession(request);
		
		if(!password.equals(conPassword)){
			throw new BaseException("确认密码错误");
		}
		if(oldPassword.equals(password)){
			throw new BaseException("旧密码和新密码相同");
		}
		bureauUserService.user_editPassWord(oldPassword,password,bureauUserCache.getBureau_user_id());
		
		return new ResultEntity("200","修改成功");
	}
}
