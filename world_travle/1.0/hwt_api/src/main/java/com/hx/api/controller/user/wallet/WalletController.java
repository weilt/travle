package com.hx.api.controller.user.wallet;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hwt.domain.entity.user.wallet.HxUserWalletBankCard;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletBankCardUserVo;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletBillVo;
import com.hwt.domain.entity.user.wallet.Vo.HxUserWalletUserVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.core.exception.BaseException;
import com.hx.core.redis.RedisCache;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.wyim.entity.emu.SMSType;
import com.hx.user.utils.BaseUtil;
import com.hx.user.wallet.service.WalletService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "API - WalletController", description = "钱包操作接口")
@RestController
public class WalletController {

	@Autowired
	private WalletService walletService;
	
	/**
	 * 查询钱包
	 */
	@ApiOperation(value = "查询钱包", notes = "查询钱包" , response = HxUserWalletUserVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/query")
	public ResultEntity query(String token){
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 HxUserWalletUserVo hxUserWalletVo = walletService.query(loginVo.getUser_id());
		return new ResultEntity(hxUserWalletVo);
	}
	
	/**
	 * 第一次设置支付密码
	 */
	@ApiOperation(value = "第一次设置支付密码", notes = "第一次设置支付密码" , response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "smsVerify", value = "验证码", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "confirm_password", value = "确认密码", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","smsVerify","password","confirm_password"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/paymentPassWord")
	public ResultEntity paymentPassWord(String token,String smsVerify, String password ,String confirm_password){
		if (!password.equals(confirm_password)){
			throw new BaseException("确认密码不一致!");
		}
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 String code = RedisCache.get(SMSType.paymentPassWord.smsTypePrefix + loginVo.getAccount_phone());
		 if(StringUtils.isBlank(code) || !code.equals(smsVerify)) {
			throw new BaseException("验证码输入错误!");
		 }
		 walletService.paymentPassWord(loginVo.getUser_id(),password);
		 return new ResultEntity();
	}
	
	
	/**
	 * 重设支付密码
	 */
	@ApiOperation(value = "重设支付密码", notes = "重设支付密码" , response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "smsVerify", value = "验证码", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "confirm_password", value = "确认密码", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","smsVerify","password","confirm_password"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/resetPaymentPass")
	public ResultEntity resetPaymentPass(String token,String smsVerify, String password,String confirm_password){
		if (!password.equals(confirm_password)){
			throw new BaseException("确认密码不一致!");
		}
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 String code = RedisCache.get(SMSType.resetPaymentPass.smsTypePrefix + loginVo.getAccount_phone());
		 if(StringUtils.isBlank(code) || !code.equals(smsVerify)) {
			throw new BaseException("验证码输入错误!");
		 }
		 walletService.restPaymentPassWord(loginVo.getUser_id(),password);
		 return new ResultEntity();
	}
	
	
	/**
	 * 修改密码
	 */
	@ApiOperation(value = "修改支付密码", notes = "修改支付密码" , response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "oldPassword", value = "旧密码", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "password", value = "密码", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "confirm_password", value = "确认密码", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","oldPassword","password","confirm_password"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/editPaymentPass")
	public ResultEntity editPaymentPass(String token,String oldPassword, String password,String confirm_password){
		if (oldPassword.equals(password)){
			throw new BaseException("新旧密码不能一致!");
		}
		if (!password.equals(confirm_password)){
			throw new BaseException("确认密码不一致!");
		}
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 
		 walletService.editPaymentPass(loginVo.getUser_id(),password,oldPassword);
		 return new ResultEntity();
	}
	
	/**
	 * 添加银行卡
	 */
	@ApiOperation(value = "添加银行卡", notes = "添加银行卡" , response = HxUserWalletBankCardUserVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "card_num", value = "卡号", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "bank_name", value = "银行名称", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "username", value = "持卡人用户名", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "image", value = "封面图", dataType = "string",paramType = "query"),
			@ApiImplicitParam(name = "bank_address", value = "开户银行地址", dataType = "string",paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","card_num","bank_name","username"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/add_bank")
	public ResultEntity add_bank(String token,String card_num, String bank_name,String image,String bank_address,String username){
		
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 
		 HxUserWalletBankCard hxUserWalletBankCard = walletService.add_bank(loginVo.getUser_id(),card_num,bank_name,image,bank_address,username);
		 return new ResultEntity(hxUserWalletBankCard);
	}
	
	/**
	 * 删除银行卡
	 */
	@ApiOperation(value = "删除银行卡", notes = "删除银行卡" , response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "bank_card_id", value = "银行卡id", dataType = "string",paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","bank_card_id"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/delete_bank")
	public ResultEntity delet_bank(String token, Long bank_card_id){
		
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 
		 walletService.delet_bank(loginVo.getUser_id(),bank_card_id);
		 return new ResultEntity();
	}
	
	/**
	 * 查询银行卡
	 */
	@ApiOperation(value = "查询银行卡", notes = "查询银行卡" , response = HxUserWalletBankCardUserVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/query_bank")
	public ResultEntity query_bank(String token){
		
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 
		 List<HxUserWalletBankCardUserVo> list =  walletService.query_bank(loginVo.getUser_id());
		 return new ResultEntity(list);
	}
	
	/**
	 * 查询账单/余额明细
	 */
	@ApiOperation(value = "查询账单", notes = "查询账单" , response = HxUserWalletBillVo.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "last_bill_id", value = "查询的最后一条id  默认0", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "pageSize", value = "每页大小 默认10", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "bill_type", value = "1-充值  2-支付 3-收入 4-退款 5-提现  6-违约金收入 0-全部", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "is_wallet", value = "是否是余额明细  1否  2是 默认 1", dataType = "string", paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/query_bill")
	public ResultEntity query_bill(String token,Long last_bill_id, Integer pageSize,Integer bill_type){
		
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 Map<String, Object> map = new HashMap<String, Object>();
			map.put("last_bill_id", last_bill_id);
			map.put("pageSize", pageSize);
			map.put("user_id", loginVo.getUser_id());
			map.put("bill_type", bill_type);
		 
		 List<HxUserWalletBillVo> list =  walletService.query_bill(map);
		 return new ResultEntity(list);
	}
	
	
	/**
	 * 充值
	 */
	@ApiOperation(value = "充值", notes = "充值" , response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "recharge_type", value = "1-支付宝 2-微信 3-其他 默认1'", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "amount", value = "充值金额", dataType = "string", paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token","amount"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/recharge")
	public ResultEntity recharge(String token, Integer recharge_type,BigDecimal amount) throws Exception {
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 if (amount.compareTo(new BigDecimal("0.00"))!=1){
			 throw new RuntimeException("充值金额必须大于0！");
		 }
		 Object object = walletService.recharge(loginVo.getUser_id(),recharge_type,amount );
		
		 return new ResultEntity(object);
	}
	
	/**
	 * 充值成功返回
	 */
	@ApiOperation(value = "充值成功返回", notes = "充值成功返回" , response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "recharge_type", value = "1-支付宝 2-微信 3-其他 默认1'", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "amount", value = "充值金额", dataType = "string", paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/recharge_success")
	public ResultEntity recharge_success(String token, Integer recharge_type,BigDecimal amount) throws Exception {
		 LoginVo loginVo = BaseUtil.getLoginVo(token);
		 
		 walletService.recharge_success(loginVo.getUser_id(),recharge_type,amount );
		
		 return new ResultEntity();
	}
	
	/**
	 * 提现申请
	 */
	@ApiOperation(value = "提现申请", notes = "提现申请" , response = ResultEntity.class)
	@ApiImplicitParams({
		@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
		@ApiImplicitParam(name = "bank_card_id", value = "银行卡id", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "forward", value = "提现金额", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "payment_password", value = "支付密码", dataType = "string", paramType = "query"),
		@ApiImplicitParam(name = "smsVerify", value = "提现验证码  超过5000必填", dataType = "string", paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@PostMapping(value = "wallet/put_forward")
	public ResultEntity put_forward(String token, Long bank_card_id,BigDecimal forward,String payment_password,String smsVerify) throws Exception {
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		int compareTo = forward.compareTo(new BigDecimal("5000"));
		
		if (compareTo!=-1){
			String code = RedisCache.get(SMSType.putForward.smsTypePrefix + loginVo.getAccount_phone());
			
			if(StringUtils.isBlank(code) || !code.equals(smsVerify)) {
				throw new BaseException("验证码输入错误!");
			}
			
		}
		//注意发送了验证码不需要输入密码
		if (smsVerify!=null){
			if (ObjectHelper.isEmpty("payment_password")){
				throw new BaseException("密码错误!");
			}
		}
		 walletService.put_forward(loginVo.getUser_id(),bank_card_id,forward,payment_password );
		 return new ResultEntity();
	}
}
