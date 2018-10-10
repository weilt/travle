package com.hx.api.controller.area;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.area.vo.HotCityVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * 地区controller
 */

@Api(value = "API - AreaController", description = "地区")
@RestController
public class AreaController {
	
	
	/**
     * 热门城市
     * @return
     */
    @ApiOperation(value = "热门城市" , notes = "热门城市", response = HotCityVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",required = true,paramType = "query"),
    })
    @ValidateParam(field={"token"})
    @PostMapping("/area/hotcity")
    public ResultEntity hotcity(){
        List<HotCityVo> list = new ArrayList<>();
        list.add(new HotCityVo("023", "500200","重庆郊县"));
        list.add(new HotCityVo("023","500100", "重庆城区"));
        list.add(new HotCityVo("010", "110100","北京市"));
        list.add(new HotCityVo( "0591","350100", "福州市"));
        list.add(new HotCityVo( "028", "510100","成都市"));
        list.add(new HotCityVo( "0833", "511100","乐山市"));
        return new ResultEntity(list);
    }
}
