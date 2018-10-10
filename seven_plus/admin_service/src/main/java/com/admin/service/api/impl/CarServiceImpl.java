package com.admin.service.api.impl;

import com.admin.service.api.CarService;
import com.common.excption.AuthExceptionConstants;
import com.common.excption.BaseException;
import com.common.util.PageUtil;
import com.domain.plus.entity.CarHomeData;
import com.domain.plus.entity.PlusCar;
import com.domain.plus.mapper.CarHomeDataMapper;
import com.domain.plus.mapper.PlusCarMapper;
import com.domain.plus.param.CarNoParam;
import com.domain.plus.param.IndexParam;
import com.domain.plus.vo.CarHomeDataVo;
import com.domain.plus.vo.ServerOrderCarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 10:32
 * @Description:
 */
@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private PlusCarMapper mapper;

    @Autowired
    private CarHomeDataMapper dataMapper;

    @Override
    public Integer saveCar(PlusCar car) {
        CarHomeData data = dataMapper.queryCarHomeDataById(car.getId());
        if (null == data )
            throw BaseException.build(AuthExceptionConstants.DATA_PRICE_IS_NULL);
        try {
            Double price = Double.parseDouble(data.getPrice());
            car.setEvaluation(price.toString());
        } catch (NumberFormatException e){
            throw BaseException.build(AuthExceptionConstants.DATA_PRICE_IS_NULL);
        }
        //兼容传人数据时id为车辆型号id
        car.setId(null);
        car.setCreateTime(System.currentTimeMillis());
        return mapper.insertPlusCar(car);
    }

    @Override
    public List<CarHomeDataVo> getCarData(String brand,Integer pageNo,Integer pageSize) {

        Integer index = PageUtil.init(pageNo,pageSize).getIndex();
        Integer last = PageUtil.init(pageNo,pageSize).getPageSize();

        List<CarHomeDataVo> list = dataMapper.queryCarVoBy(brand,index,last);
        if (null == list || list.isEmpty()){
            throw BaseException.build(AuthExceptionConstants.DATA_IS_NULL);
        }
        return list;
    }

    @Override
    public PlusCar getCarByNo(Long userId, CarNoParam param) {
        return mapper.queryCarByNo(param.getCarNo(),userId);
    }

    @Override
    public List<ServerOrderCarVo> getServerTypeCar(Long id, IndexParam param) {
        return mapper.queryServerOrderByUserId(id,param.getType());
    }
}
