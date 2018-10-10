package com.domain.plus.mapper;

import java.util.List;

import com.domain.plus.vo.OrderRecordApiVo;
import com.domain.plus.vo.OrderRecordVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.OrderRecord;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface OrderRecordMapper {

    int insertOrderRecord(OrderRecord object);

    int updateOrderRecord(OrderRecord object);

    int update(OrderRecord.UpdateBuilder object);

    List<OrderRecord> queryOrderRecord(OrderRecord object);

    OrderRecord queryOrderRecordLimit1(OrderRecord object);

    OrderRecord queryRecordById(@Param("id")Long id);

    List<OrderRecordVo> queryOrderRecordVo(@Param("phone")String phone,
                                           @Param("storeName")String storeName,
                                           @Param("storeAddress")String storeAddress,
                                           @Param("orderType") Integer orderType,
                                           @Param("index")Integer index,@Param("last")Integer last);

    Integer countOrderRecord(@Param("phone")String phone,
                             @Param("storeName")String storeName,
                             @Param("storeAddress")String storeAddress,
                             @Param("orderType") Integer orderType);

    List<OrderRecordVo> queryScratchOrder(@Param("phone")String phone,@Param("beginTime")Long beginTime,
                                          @Param("endTime")Long endTime,@Param("orderType") Integer orderType,
                                          @Param("state")Integer state,@Param("index")Integer index,@Param("last")Integer last);

    Integer countScratchOrder(@Param("phone")String phone, @Param("beginTime")Long beginTime,
                              @Param("endTime")Long endTime, @Param("orderType") Integer orderType, @Param("state")Integer state);

    List<OrderRecordApiVo> queryOrderRecordApiVo(@Param("userId")Long userId,@Param("orderType")Integer orderType,
                                                 @Param("index")Integer index,@Param("last")Integer last);


    Integer countScratchOrderId(@Param("orderId")Long orderId);
}