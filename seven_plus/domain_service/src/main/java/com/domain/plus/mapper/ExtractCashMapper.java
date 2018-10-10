package com.domain.plus.mapper;

import java.util.List;

import com.domain.plus.entity.ExtractCash;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface ExtractCashMapper {

    int insertExtractCash(ExtractCash object);

    int updateExtractCash(ExtractCash object);

    int update(ExtractCash.UpdateBuilder object);

    List<ExtractCash> queryExtractCash(ExtractCash object);

    ExtractCash queryExtractCashLimit1(ExtractCash object);

    List<ExtractCash> queryExtractCashByUserId(@Param("userId")Long userId);

}