package com.hwt.domain.mapper.order;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.order.HxOrderInfo;
import com.hwt.domain.entity.order.vo.HxOrderInfoVo;

@Mapper
public interface HxOrderInfoMapper {


    int insert(HxOrderInfo record);

    int insertSelective(HxOrderInfo record);

	/**
	 * 通过订单id查询
	 * @param order_id
	 * @return
	 */
    @Select("select * from hx_order_info where order_id = #{order_id}")
	HxOrderInfoVo query_by_order_id(@Param("order_id")Long order_id);



}