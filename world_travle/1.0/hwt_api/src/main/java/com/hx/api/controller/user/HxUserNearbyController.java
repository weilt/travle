package com.hx.api.controller.user;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.user.HxUser;
import com.hwt.domain.entity.user.Vo.HxUserNearbyVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.core.exception.BaseException;
import com.hx.core.utils.ObjectHelper;
import com.hx.user.service.HxUserNearbyService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(value = "API - HxUserNearbyController", description = "附近的人")
public class HxUserNearbyController {
	@Autowired
	private HxUserNearbyService hxUserNearbyService;

	/**
	 * 附近的人
	 * 
	 * @param token
	 *            用户TOKEN
	 * @param longitude
	 *            纬度
	 * @param latitude
	 *            经度
	 * @param user_sex
	 *            用户性别 0-人妖 1-男 2-女
	 * @param user_birthday_min
	 *            最小生日 yyyy-MM-dd
	 * @param user_birthday_max
	 *            最大生日 yyyy-MM-dd
	 * @param user_emotion
	 *            用户情感  1-单身2-离异 3-热恋 4-已婚
	 * @return
	 */
	@ApiOperation(value = "附近的人", notes = "附近的人", response = HxUserNearbyVo.class)
	@ApiImplicitParams({
			@ApiImplicitParam(name = "token", value = "用户TOKEN", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "longitude", value = "纬度", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "latitude", value = "经度", dataType = "string", required = true, paramType = "query"),
			@ApiImplicitParam(name = "user_sex", value = "用户性别 0-人妖 1-男 2-女   -1-所有性别  默认 -1", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "user_birthday_min", value = "最小生日 yyyy-MM-dd", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "user_birthday_max", value = "最大生日 yyyy-MM-dd", dataType = "string", required = false, paramType = "query"),
			@ApiImplicitParam(name = "user_emotion", value = "用户情感 0-保密 1-单身2-离异 3-热恋 4-已婚  -1-所有人 默认-1", dataType = "string", required = false, paramType = "query"),

	})
	@ApiResponses(value = { @ApiResponse(code = 200, message = "成功!") })
	@ValidateParam(field = { "token", "longitude", "latitude" }, checkedType = CheckedType.TOKEN)
	@RequestMapping(value = "nearby/query", method = RequestMethod.POST)
	public ResultEntity queryNearby(String token, String longitude, String latitude, @RequestParam(defaultValue="-1")Integer user_sex,
			String user_birthday_min, String user_birthday_max, @RequestParam(defaultValue="-1")Integer user_emotion) {

		LoginVo loginVo = BaseUtil.getLoginVo(token);
		Map<String, Object> map = new HashMap<>();
		map.put("longitude", longitude);
		map.put("latitude", latitude);
		map.put("user_id", loginVo.getUser_id());
		
		map.put("user_sex", user_sex);
		map.put("user_birthday_min", user_birthday_min);
		map.put("user_birthday_max", user_birthday_max);
		map.put("user_emotion", user_emotion);
		List<HxUserNearbyVo> hxUserNearbyVos = hxUserNearbyService.queryNearbyMap(loginVo.getUser_id(), map);
		return new ResultEntity(hxUserNearbyVos);
	}
}
