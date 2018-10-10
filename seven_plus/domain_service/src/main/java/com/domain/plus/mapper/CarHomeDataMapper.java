package com.domain.plus.mapper;

import com.domain.plus.entity.CarHomeData;
import com.domain.plus.vo.CarHomeDataVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 18:02
 * @Description:
 */
@Mapper
public interface CarHomeDataMapper {
    int insertCarHomeData(CarHomeData object);

    int updateCarHomeData(CarHomeData object);

    int update(CarHomeData.UpdateBuilder object);

    List<CarHomeData> queryCarHomeData(CarHomeData object);

    CarHomeData queryCarHomeDataLimit1(CarHomeData object);

    List<CarHomeDataVo> queryCarVoBy(@Param("brand") String brand,@Param("index")Integer index,@Param("last")Integer last);

    CarHomeData queryCarHomeDataById(@Param("id") Long id );

}
