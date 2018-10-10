package com.hx.api.controller.index;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hwt.domain.entity.es.ESQuery;
import com.hwt.domain.entity.mg.scenic.vo.TravelIndexVo;
import com.hx.api.base.ResultEntity;
import com.hx.scenic.scenicVo.IndexService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(value = "API - IndexController", description = "首页")
@RestController
public class IndexController {
	
	@Autowired
	private IndexService indexService;
	
	 /**
     *  首页
     * @return
     */
    @ApiOperation(value = "首页" , notes = "首页", response = TravelIndexVo.class)
    @ApiImplicitParams({
    	@ApiImplicitParam(name = "area_code", value = "区号", dataType = "string",paramType = "query"),
    })
    @PostMapping("/hw/index")
    public ResultEntity searchIndex(String area_code){
    	Map<String, Object> map = indexService.searchIndex(area_code);
        return new ResultEntity(map);
    }
    
    /**
     * 综合搜索
     * @param searchKey	`		查询条件
     * @param type				类型  0-所有 1-景点 2-导游  3-线路  4-咨询    默认0
     * @param pageIndex			开始页	默认0
     * @param pageSize			展示条数	默认0
     * @return	
     */
    @ApiOperation(value = "综合搜索" , notes = "综合搜索", response = ESQuery.class)
    @ApiImplicitParams({
    		@ApiImplicitParam(name = "type", value = "类型  0-所有 1-景点 2-导游  3-线路  4-咨询    默认0", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "searchKey", value = "查询条件", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "开始页   默认1", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "展示条数  默认10", dataType = "string",required = true,paramType = "query")
    })
    @PostMapping("/index/search")
    public ResultEntity search(String searchKey,@RequestParam(name = "type",defaultValue="0") Integer type,
                                    @RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,
                                    @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize){
        List<Map<String,Object>> list = indexService.search(searchKey,type,pageIndex,pageSize);
        return new ResultEntity(list);
    }

    /**
     * 猜你喜欢
     */
    @ApiOperation(value = " 猜你喜欢" , notes = " 猜你喜欢", response = ESQuery.class)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "like_field", value = "常用搜索", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageIndex", value = "开始页   默认1", dataType = "string",required = true,paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "展示条数  默认10", dataType = "string",required = true,paramType = "query")
    })
    @PostMapping("/index/like")
    public ResultEntity like(String like_field,
                                    @RequestParam(name = "pageIndex",defaultValue = "1")Integer pageIndex,
                                    @RequestParam(name = "pageSize",defaultValue = "10")Integer pageSize){
        List<Map<String,Object>> list = indexService.like(like_field,pageIndex,pageSize);
        return new ResultEntity(list);
    }

}
