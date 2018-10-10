package com.hx.api.controller.version;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.user.Vo.UserVo;
import com.hwt.domain.entity.version.HxVersion;
import com.hwt.domain.entity.version.vo.HxVersionVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.system.service.hx.VersionService;
import com.hx.user.service.AccountInfoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * 版本管理
 * @author Administrator
 *
 */
@Api(value = "API - VersionController", description = "版本接口")
@RestController
public class VersionController {
	@Autowired
	private VersionService versionService;
	
	/**
	 * 获取当前版本
	 * @param token - 验证登录状态 - Y
	 * @return
	 */
	@ApiOperation(value = "获取当前版本", notes = "获取当前版本" , response = HxVersionVo.class)
	@ApiImplicitParams({
		
	})
	
	@ApiResponses({@ApiResponse(code=200,message="")})
	@RequestMapping(value = "version/current",method = RequestMethod.POST)
	public ResultEntity accountInfo(){
		List<HxVersionVo> versionVos = versionService.verificationVersion();
		return new ResultEntity(versionVos);
	}
	
}
