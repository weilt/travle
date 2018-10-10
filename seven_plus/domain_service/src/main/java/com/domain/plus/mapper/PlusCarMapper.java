package com.domain.plus.mapper;

import java.util.List;

import com.domain.plus.vo.PlusCarTaskVo;
import com.domain.plus.vo.PlusCarVo;
import com.domain.plus.vo.ServerOrderCarVo;
import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.PlusCar;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface PlusCarMapper {

    int insertPlusCar(PlusCar object);

    int updatePlusCar(PlusCar object);

    int update(PlusCar.UpdateBuilder object);

    List<PlusCar> queryPlusCar(PlusCar object);

    PlusCar queryPlusCarLimit1(PlusCar object);

    List<PlusCarVo> findCarByUserId(@Param("userId") Long userId);

    PlusCar findOneCarByUserId(@Param("userId") Long userId);

    Integer countCarByPhone(@Param("phone")String phone);

    List<PlusCarVo> findCarByPhone(@Param("phone")String phone, @Param("index")Integer index, @Param("last")Integer last);

    PlusCarVo queryCarById(@Param("id") Long id);

    PlusCar findById(@Param("id") Long id);

    PlusCar queryCarByNo(@Param("carNo")String carNo,@Param("userId")Long userId);

    List<PlusCarTaskVo> queryCarTask(@Param("index")Integer index, @Param("last")Integer last);

    int deleteCarById(@Param("id")Long id);

    List<ServerOrderCarVo> queryServerOrderByUserId(@Param("userId")Long userId, @Param("orderType")Integer orderType);
}