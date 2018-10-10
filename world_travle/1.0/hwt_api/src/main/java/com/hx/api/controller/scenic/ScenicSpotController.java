package com.hx.api.controller.scenic;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVo;
import com.hwt.domain.entity.es.ESQuery;
import com.hwt.domain.entity.mg.scenic.vo.HxSpotIndexVO;
import com.hwt.domain.entity.mg.scenic.vo.QueryScenicSpotDetailsVo;
import com.hwt.domain.entity.mg.scenic.vo.ScenicSpotVO;
import com.hwt.domain.entity.mg.scenic.vo.TravelIndexVo;
import com.hwt.domain.entity.user.Vo.LoginVo;
import com.hx.api.base.ResultEntity;
import com.hx.api.validate.ValidateParam;
import com.hx.api.validate.ValidateParam.CheckedType;
import com.hx.core.utils.ObjectHelper;
import com.hx.scenic.scenicVo.ScenicSpotService;
import com.hx.user.service.HxCiceroneService;
import com.hx.user.utils.BaseUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author zhoudu
 * 景点controller
 */

@Api(value = "API - ScenicSpotController", description = "景点查询接口")
@RestController
public class ScenicSpotController  {

    @Autowired
    private ScenicSpotService scenicSpotService;
    
    @Autowired
    private HxCiceroneService hxCiceroneService;


    /**
     * 景点查询
     * @param searchKey
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询景点信息" , notes = "查询景点信息", response = ScenicSpotVO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "searchKey", value = "查询条件", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "开始页   1开始 默认 1  ", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "展示条数", dataType = "string",required = true,paramType = "query")
    })
    @ValidateParam(field={"searchKey"})
    @PostMapping("/scenic/search")
    public ResultEntity search(@RequestParam(name = "searchKey") String searchKey,
                                    @RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,
                                    @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize){
        List<Map<String,Object>> list = scenicSpotService.queryScenic(searchKey,pageIndex,pageSize);
        return new ResultEntity(list);
    }


    /**
     * 查询景点信息  热门 OR 推荐
     * @param isHot_or_isRecommend
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "查询景点信息  热门 OR 推荐" , notes = "查询景点信息  热门 OR 推荐", response = ScenicSpotVO.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isHot_or_isRecommend", value = "热门或者推荐 1-热门2-推荐 默认是1", dataType = "string",required = false,paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "开始页   1开始 默认 1  ", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "展示条数", dataType = "string",required = true,paramType = "query")
    })
    @PostMapping("/scenic/hotOrRecommend")
    public ResultEntity searchHotRoRecommend(@RequestParam(name = "isHot_or_isRecommend",defaultValue="1",required = false) Integer isHot_or_isRecommend,
                                             @RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,
                                             @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize){
        List<Map<String,Object>> list = scenicSpotService.hotOrRecommend(isHot_or_isRecommend,pageIndex,pageSize);
        return new ResultEntity(list);
    }

    /**
     *  景点首页查询
     * @return
     */
    @ApiOperation(value = "热门推荐查询景点信息" , notes = "热门推荐查询景点信息", response = TravelIndexVo.class)
    @GetMapping("/scenic/index")
    public ResultEntity searchIndex(){
    	List<HxCiceroneInfoVo> ciceroneInfoVos = hxCiceroneService.searchCicerone(null,null,null,null,null,1,10);
        Map<String, Object> map = new HashMap<>();
        map.put("ciceroneInfoVos", ciceroneInfoVos);
        return new ResultEntity(map);
    }


    /**
     * 详情查询
     * @param spotId
     * @return
     */
    @ApiOperation(value = "景点信息详情" , notes = "景点信息详情", response = QueryScenicSpotDetailsVo.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "spotId", value = "查询条件", dataType = "Long",required = true,paramType = "query"),
            @ApiImplicitParam(name = "token", value = "用户验证TOKEN - Y", dataType = "string",paramType = "query"),
    })
    @ValidateParam(field={"spotId"})
    @PostMapping("/scenic/spotInfo")
    public ResultEntity searchSpotInfo(@RequestParam("spotId")Long spotId,String token){
    	Long user_id = null;
    	if (!ObjectHelper.isEmpty(token)){
    		LoginVo loginVo = BaseUtil.getLoginVo(token);
        	
        	if (!ObjectHelper.isEmpty(loginVo)){
        		user_id = loginVo.getUser_id();
        	}
    	}
		Map<String,Object> scenicSpotVO = scenicSpotService.querySpotInfo(spotId,user_id);
        return new ResultEntity(scenicSpotVO);
    }
    
    /**
     * 景点检索输入时返回值
     * @param filed  内容
     * @return
     */
    @ApiOperation(value = "景点检索输入时返回值" , notes = "景点检索输入时返回值", response = ScenicSpotVO.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "filed", value = "内容", dataType = "string",required = true,paramType = "query"),
    })
    @GetMapping("/scenic/sight_search")
    @ValidateParam(field={"filed"})
    public ResultEntity sight_search(@RequestParam("filed")String filed){
    	List<Map<String,Object>> list = scenicSpotService.query_sight_search(filed);
    	return new ResultEntity(list);
    }


    /**
     *
     * @param province
     * @param areaCodes
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @ApiOperation(value = "省级联动查询" , notes = "省级联动查询", response = ESQuery.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "province", value = "省", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "areaCodes", value = "区号数组  用逗号隔开", dataType = "string",required = true,paramType = "query"),
    	@ApiImplicitParam(name = "pageIndex", value = "开始页   1开始 默认 1  ", dataType = "string",paramType = "query"),
        @ApiImplicitParam(name = "pageSize", value = "展示条数", dataType = "string",paramType = "query")
    })
    @ValidateParam(field={"province","areaCodes"})
    @PostMapping("/scenic/province_city_search")
    public ResultEntity province_city_search(String province, String areaCodes , @RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,
            @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize){
    	List<Map<String, Object>> list = scenicSpotService.province_city_search(province,areaCodes,pageIndex,pageSize);
    	return new ResultEntity(list);
    }
    
    
}
