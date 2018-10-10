package com.domain.plus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.PlusAddress;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface PlusAddressMapper {

    int insertPlusAddress(PlusAddress object);

    int updatePlusAddress(PlusAddress object);

    int update(PlusAddress.UpdateBuilder object);

    int delete(@Param("userId")Long userId,@Param("id") Long id);

    List<PlusAddress> queryPlusAddress(PlusAddress object);

    PlusAddress queryPlusAddressLimit1(PlusAddress object);

    List<PlusAddress> queryAddrByUserId(Long userId);

    PlusAddress queryAddressById(@Param("id")Long id);

}