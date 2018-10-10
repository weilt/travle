package com.domain.plus.mapper;

import java.util.List;

import com.domain.plus.vo.OrderVo;
import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.PlusOrder;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface PlusOrderMapper {

    int insertPlusOrder(PlusOrder object);

    int updatePlusOrder(PlusOrder object);

    int update(PlusOrder.UpdateBuilder object);

    List<PlusOrder> queryPlusOrder(PlusOrder object);

    PlusOrder queryPlusOrderLimit1(PlusOrder object);

    PlusOrder queryOrderById(@Param("id") Long id);

    List<OrderVo> queryOrder(@Param("phone")String phone,@Param("count")Integer count,
                             @Param("beginTime")Long beginTime,@Param("endTime")Long endTime,
                             @Param("index")Integer index,@Param("last")Integer last);

    Integer countOrder(@Param("phone")String phone,@Param("count")Integer count,
                       @Param("beginTime")Long beginTime,@Param("endTime")Long endTime);

    Integer countOrderByUserIdAndType(@Param("userId")Long userId,@Param("orderType")Integer orderType);

    PlusOrder queryOrderByUserIdAndType(@Param("userId")Long userId,@Param("orderType")Integer orderType);

    List<PlusOrder> queryListOrderByUserIdAndType(@Param("userId")Long userId,@Param("orderType")Integer orderType);

    List<PlusOrder> queryOrderByUnPay(@Param("index")Integer index,@Param("last")Integer last);


    int deleteOrderById(@Param("id")Long id);

    List<PlusOrder> queryListOrderByUserId(@Param("userId")Long id);
}