package com.plus.admin.controller.api;

import com.admin.service.api.StoreService;
import com.common.consts.UserContext;
import com.domain.plus.param.OrderParam;
import com.domain.plus.param.StoreNameParam;
import com.domain.plus.param.StoreParam;
import com.domain.plus.vo.StoreVo;
import com.google.common.collect.Lists;
import com.plus.admin.controller.api.result.ResultCollection;
import com.plus.admin.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 15:32
 * @Description:
 */
@Api(value = "API - StoreController", description = "网点相关")
@RestController
public class StoreController extends BaseApiController {

    @Autowired
    private StoreService storeService;

    @ApiOperation(value = "首页获取网点信息" , notes = "getStore", response = StoreVo.class)
    @PostMapping("/getStore")
    public ResultCollection getStore(@RequestBody StoreParam param){
        List<StoreVo> list = storeService.getStoreByType(param.getStoreType(),param.getPageNo(),param.getPageSize());
        return ResultCollection.build(list);
    }

    @ApiOperation(value = "通过类型和名称获取网点信息" , notes = "getStoreByName", response = StoreVo.class)
    @PostMapping("/api/getStoreByName")
    public ResultCollection getStoreByName(@RequestBody StoreNameParam param) {
        if (StringUtils.isEmpty(param.getStoreName())){
            return ResultCollection.build(Lists.newArrayList());
        }
        List<StoreVo> list = storeService.getStoreByType(param.getStoreType(),param.getStoreName(),param.getPageNo(),param.getPageSize());
        return ResultCollection.build(list);
    }


}
