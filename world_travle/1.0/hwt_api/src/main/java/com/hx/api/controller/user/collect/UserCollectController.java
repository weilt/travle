package com.hx.api.controller.user.collect;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.es.ESQuery;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hwt.domain.entity.user.Vo.UserVo;
import com.hwt.domain.entity.user.collect.HwUserCollect;
import com.hwt.domain.entity.user.collect.vo.CollectListVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.user.collect.service.UserCollectService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
/**
 * 收藏夹
 * @author Administrator
 *
 */
@RestController
@Api(value = "API - UserCollectController", description = "收藏夹操作接口")
public class UserCollectController {
	
	@Autowired
	private UserCollectService userCollectService;
	
	/**
	 * 获取当前用户收藏夹
	 * @param token - 验证登录状态 - Y
	 * @return
	 */
	@ApiOperation(value = "获取当前用户收藏夹", notes = "获取当前用户收藏夹" , response = CollectListVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "pageIndex", value = "页码  默认是1", dataType = "string",required = true,paramType = "query"),
	        @ApiImplicitParam(name = "pageSize", value = "展示条数  默认10条", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "userCollect/query",method = RequestMethod.POST)
	public ResultEntity query(String token,@RequestParam(defaultValue="1")Integer pageIndex, @RequestParam(defaultValue="10")Integer pageSize){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		
		Map<String, Object> esQueries = userCollectService.query(loginVo.getUser_id(),(pageIndex <= 1 ? 0 : (pageIndex  - 1)) * pageSize,pageSize);
		
		return new ResultEntity(esQueries);
	}
	
	/**
	 * 添加收藏
	 * @param token		用户验证TOKEN - Y
	 * @param name_id	内容的实际id
	 * @param type		类型 1-景点 2-导游 3-线路 4-咨询
	 * @return
	 */
	@ApiOperation(value = "添加收藏", notes = "添加收藏" , response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "name_id", value = "内容的实际id", dataType = "string",required = true,paramType = "query"),
	        @ApiImplicitParam(name = "type", value = "类型 1-景点 2-导游 3-线路 4-咨询", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "userCollect/insert",method = RequestMethod.POST)
	public ResultEntity insert(String token,Long name_id, Integer type){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		HwUserCollect insert = userCollectService.insert(loginVo.getUser_id(),name_id,type);
		
		return new ResultEntity(insert);
	}
	
	/**
	 * 删除
	 * @param token			用户验证TOKEN - Y
	 * @param collec_ids	收藏夹id集 如   1,2,3
	 * @return
	 */
	@ApiOperation(value = "删除", notes = "删除" , response = ResultEntity.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
			@ApiImplicitParam(name = "collect_ids", value = "收藏夹id集 如   1,2,3", dataType = "string",required = true,paramType = "query"),
	})
	@ApiResponses({@ApiResponse(code=200,message="")})
	@ValidateParam(field={"token"},checkedType={CheckedType.TOKEN})
	@RequestMapping(value = "userCollect/delete",method = RequestMethod.POST)
	public ResultEntity delete(String token,String collect_ids){
		LoginVo loginVo = BaseUtil.getLoginVo(token);
		userCollectService.delete(loginVo.getUser_id(),collect_ids);
		return new ResultEntity();
	}
}
