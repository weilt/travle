package com.hx.api.controller.opinion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.mg.opinion.HxOpinion;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.user.opinion.service.OpinionService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;


/**
 *  意见反馈
 * @author Administrator
 *
 */
@Api(value = "API - OpinionController", description = " 意见反馈")
@RestController
public class OpinionController {
	
	@Autowired
	private OpinionService opinionService;
	
	/**
	 * 意见反馈
	 * @throws Exception 
	 */
	@ApiOperation(value = " 意见反馈" , notes = " 意见反馈", response = ResultEntity.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token","content"}, checkedType = CheckedType.TOKEN)
	@PostMapping("/opinion/insert")
	public ResultEntity insert(String token,HxOpinion hxOpinion) throws Exception{
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		hxOpinion.setUser_id(loginVo.getUser_id());
		opinionService.insert(hxOpinion);
		return new ResultEntity();
		
	}
}
