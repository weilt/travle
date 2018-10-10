package com.admin.service.api;

import com.domain.plus.entity.PlusCar;
import com.domain.plus.param.CarNoParam;
import com.domain.plus.param.IndexParam;
import com.domain.plus.vo.CarHomeDataVo;
import com.domain.plus.vo.ServerOrderCarVo;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 10:32
 * @Description:
 */
public interface CarService {
    /**
     * 保存车辆信息
     * @param car
     * @return
     */
    Integer saveCar(PlusCar car);

    /**
     * 获取品牌列表
     * @param brand
     * @return
     */
    List<CarHomeDataVo> getCarData(String brand,Integer pageNo,Integer pageSize);

    /**
     *  通过车牌号获取车辆信息
     * @param userId
     * @param param
     * @return
     */
    PlusCar getCarByNo(Long userId,CarNoParam param);

    /**
     * 获取办理业务的车辆
     * @param id
     * @param param
     * @return
     */
    List<ServerOrderCarVo> getServerTypeCar(Long id, IndexParam param);
}
