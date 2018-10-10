package com.hx.api.controller.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.order.HwOrderUsuallyUser;
import com.hwt.domain.entity.order.vo.HwOrderUsuallyUserVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.order.service.hx.OrderUsuallyUserService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 常用用户
 * @author Administrator
 *
 */
@Api(value = "API - OrderUsuallyUserController", description = "常用用户")
@RestController
public class OrderUsuallyUserController {

	@Autowired
	private OrderUsuallyUserService orderUsuallyUserService;
	
	/**
	 * 添加
	 */
	@ApiOperation(value = "添加" , notes = "添加", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","name","identity_type","identity_num"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/orderUsuallyUser/insert")
	public ResultEntity insert(String token, HwOrderUsuallyUser hwOrderUsuallyUser){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		hwOrderUsuallyUser.setUser_id(loginVo.getUser_id());
		orderUsuallyUserService.insert(hwOrderUsuallyUser);
		return new ResultEntity();
	}
	
	/**
	 * 查看
	 */
	@ApiOperation(value = "查看" , notes = "查看", response = HwOrderUsuallyUserVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/orderUsuallyUser/query")
	public ResultEntity query(String token){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		List<HwOrderUsuallyUserVo> hwOrderUsuallyUserVos = orderUsuallyUserService.query(loginVo.getUser_id());
		return new ResultEntity(hwOrderUsuallyUserVos);
	}
	
	/**
	 * 查看详情
	 */
	@ApiOperation(value = "查看详情" , notes = "查看详情", response = HwOrderUsuallyUserVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","usually_id"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/orderUsuallyUser/queryDetails")
	public ResultEntity queryDetails(String token,Long usually_id){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		HwOrderUsuallyUserVo hwOrderUsuallyUserVo = orderUsuallyUserService.queryDetails(loginVo.getUser_id(),usually_id);
		return new ResultEntity(hwOrderUsuallyUserVo);
	}
	
	/**
	 * 更新
	 */
	@ApiOperation(value = " 更新" , notes = " 更新", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","usually_id","name","identity_type","identity_num"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/orderUsuallyUser/update")
	public ResultEntity update(String token, HwOrderUsuallyUserVo hwOrderUsuallyUserVo){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		hwOrderUsuallyUserVo.setUser_id(loginVo.getUser_id());
		orderUsuallyUserService.update(hwOrderUsuallyUserVo);
		return new ResultEntity();
	}
	
	/**
	 * 删除
	 */
	@ApiOperation(value = " 删除" , notes = " 删除", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","usually_id"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/orderUsuallyUser/delete")
	public ResultEntity delete(String token, Long usually_id){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		
		orderUsuallyUserService.delete(loginVo.getUser_id() ,usually_id);
		return new ResultEntity();
	}
}
