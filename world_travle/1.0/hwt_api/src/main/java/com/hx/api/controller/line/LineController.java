package com.hx.api.controller.line;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.es.ESQuery;
import com.hwt.domain.entity.mg.travel.line.vo.HwTravelLineDetailsVo;
import com.hwt.domain.entity.mg.travel.line.vo.HwTravelLineOtherVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.bureau.service.hx.HxLineService;
import com.hx.core.utils.ObjectHelper;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@CrossOrigin(origins = "*", maxAge = 3600)
@Api(value = "API - LineController", description = "线路")
@RestController
public class LineController {

	@Autowired
	private HxLineService hxLineService;
	/**
	 * 查看线路详情
	 * @param hwTravelLine
	 * @param type
	 * @return
	 */
	@ApiOperation(value = "查看线路详情" , notes = "查看线路详情", response = HwTravelLineDetailsVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",paramType = "query"),
    	@ApiImplicitParam(name = "line_id", value = "线路id", dataType = "string",required = true,paramType = "query"),
    })
	@ValidateParam(field={"line_id"}, message={"Id不能为空"})
	@PostMapping("bureau/line/details")
	public ResultEntity details(String token ,Long line_id){
		Long user_id = null;
		if (!ObjectHelper.isEmpty(token)){
			LoginVo loginVo = BaseUtil.getLoginVo(token);
			user_id = loginVo.getUser_id();
		}
		Map<String, Object> map  = hxLineService.details(line_id,user_id);
		return new ResultEntity(map);
		
	}
	
	@ApiOperation(value = "线路详情最下面的条数据返回" , notes = "线路详情最下面的条数据返回", response = HwTravelLineOtherVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",paramType = "query"),
    	@ApiImplicitParam(name = "line_id", value = "线路id", dataType = "string",required = true,paramType = "query"),
    })
	 @ValidateParam(field={"token","line_id"}, checkedType = CheckedType.TOKEN)
	@PostMapping("bureau/line/details_other")
	public ResultEntity details_other(String token ,Long line_id){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		HwTravelLineOtherVo hwTravelLineOtherVo = hxLineService.details_other(loginVo.getUser_id(),line_id);
		return new ResultEntity(hwTravelLineOtherVo);
		
	}
	
	/**
	 * 线路价格返回
	 */
	@ApiOperation(value = "线路价格返回" , notes = "线路价格返回", response = ESQuery.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",paramType = "query"),
    	@ApiImplicitParam(name = "line_id", value = "线路id", dataType = "string",required = true,paramType = "query"),
    })
	 @ValidateParam(field={"token","line_id"}, checkedType = CheckedType.TOKEN)
	@PostMapping("bureau/line/line_price")
	public ResultEntity line_price(String token ,Long line_id){
		
		Map<String, Object> esQuery = hxLineService.line_price(line_id);
		return new ResultEntity(esQuery);
		
	}
}
