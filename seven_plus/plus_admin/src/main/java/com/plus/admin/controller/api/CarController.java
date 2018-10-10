package com.plus.admin.controller.api;

import com.admin.service.api.CarService;
import com.common.consts.UserContext;
import com.common.util.CarPlateNumberUtil;
import com.domain.plus.entity.PlusCar;
import com.domain.plus.param.CarBrandParam;
import com.domain.plus.param.CarNoParam;
import com.domain.plus.param.IndexParam;
import com.domain.plus.vo.CarHomeDataVo;
import com.domain.plus.vo.ServerOrderCarVo;
import com.plus.admin.controller.api.result.ResultCollection;
import com.plus.admin.controller.api.result.ResultEntity;
import com.plus.admin.controller.base.BaseApiController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 09:59
 * @Description:
 */
@Api(value = "API - CarController", description = "车辆管理")
@RestController
public class CarController extends BaseApiController {

    @Autowired
    public CarService service;

    /**
     * 保存
     * @return
     */
    @ApiOperation(value = "保存车辆信息" , notes = "保存车辆信息", response = ResultEntity.class)
    @PostMapping("/api/saveCar")
    public ResultEntity<Long> saveCar(@RequestBody PlusCar car){
        UserContext context = super.getUserContext();
        CarPlateNumberUtil.check(car.getCarNo());
        car.setUserId(context.getId());
        service.saveCar(car);
        return ResultEntity.build(car.getId());
    }


    @ApiOperation(value = "获取车辆型号" , notes = "获取车辆型号", response = CarHomeDataVo.class)
    @PostMapping("/api/getCarData")
    public ResultCollection<CarHomeDataVo> getCarData(@RequestBody CarBrandParam param){
        List<CarHomeDataVo> list = service.getCarData(param.getBrand(),param.getPageNo(),param.getPageSize());
        return ResultCollection.build(list);
    }


    @ApiOperation(value = "获取车牌号" , notes = "获取车牌号", response = PlusCar.class)
    @PostMapping("/api/getCarByNo")
    public ResultEntity<PlusCar> getCarByNo(@RequestBody CarNoParam param){
        UserContext context = super.getUserContext();
        PlusCar car = service.getCarByNo(context.getId(),param);
        return ResultEntity.build(car);
    }

    @ApiOperation(value = "获取指定服务的车辆", notes = "获取指定服务的车辆", response = ServerOrderCarVo.class)
    @PostMapping("/api/getServerTypeCar")
    public ResultCollection<ServerOrderCarVo> getServerTypeCar(@RequestBody IndexParam param){
        UserContext context = super.getUserContext();
        List<ServerOrderCarVo> list = service.getServerTypeCar(context.getId(),param);
        return ResultCollection.build(list);
    }

}
