package com.plus.admin.controller.api;

import com.admin.service.api.AddressService;
import com.common.consts.UserContext;
import com.domain.plus.entity.PlusAddress;
import com.domain.plus.param.AddressParam;
import com.plus.admin.controller.api.result.ResultCollection;
import com.plus.admin.controller.api.result.ResultEntity;
import com.plus.admin.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/21 18:03
 * @Description:
 */
@Api(value = "API - AddressController", description = "地址API")
@RestController
public class AddressController extends BaseApiController {

    @Autowired
    private AddressService addressService;

    @ApiOperation(value = "保存地址" , notes = "保存地址", response = ResultEntity.class)
    @PostMapping("/api/addAddress")
    public ResultEntity addAddress(@RequestBody AddressParam param){
        UserContext context = getUserContext();
        addressService.addAddress(context.getId(),param.getAddress());
        return ResultEntity.build();
    }


    @ApiOperation(value = "删除地址" , notes = "删除地址", response = ResultEntity.class)
    @PostMapping("/api/delAddress")
    public ResultEntity delAddress(@RequestBody AddressParam param){
        UserContext context = getUserContext();
        addressService.delAddress(context.getId(),param.getId());
        return ResultEntity.build();
    }


    @ApiOperation(value = "更新地址" , notes = "更新地址", response = ResultEntity.class)
    @PostMapping("/api/updateAddress")
    public ResultEntity updateAddress(@RequestBody AddressParam param) {
        UserContext context = getUserContext();
        addressService.updateAddress(context.getId(),param.getId(),param.getAddress());
        return ResultEntity.build();
    }


    @ApiOperation(value = "获取地址" , notes = "获取地址", response = PlusAddress.class)
    @PostMapping("/api/getAddress")
    public ResultCollection getAddress() {
        UserContext context = getUserContext();
       List<PlusAddress> list = addressService.getAddress(context.getId());
       return ResultCollection.build(list);
    }
}
