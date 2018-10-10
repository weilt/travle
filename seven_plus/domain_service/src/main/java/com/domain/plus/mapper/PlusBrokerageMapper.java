package com.domain.plus.mapper;

import com.domain.plus.entity.PlusBrokerage;
import com.domain.plus.vo.BrokerageVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/28 15:34
 * @Description:
 */
@Mapper
public interface PlusBrokerageMapper {

    int insertPlusBrokerage(PlusBrokerage object);

    int updatePlusBrokerage(PlusBrokerage object);

    int update(PlusBrokerage.UpdateBuilder object);

    List<PlusBrokerage> queryPlusBrokerage(PlusBrokerage object);

    PlusBrokerage queryPlusBrokerageLimit1(PlusBrokerage object);

    /**
     * 通过userId获取佣金记录
     * @param userId
     * @return
     */
    List<BrokerageVo> queryBrokerageByUserId(@Param("userId")Long userId);
}
