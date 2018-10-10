package com.hwt.domain.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.HwOrderUnpaid;

@Mapper
public interface HwOrderUnpaidMapper {

    int deleteByPrimaryKey(Long order_id);

    int insert(HwOrderUnpaid record);

    int insertSelective(HwOrderUnpaid record);


    HwOrderUnpaid selectByPrimaryKey(Long order_id);


    int updateByPrimaryKeySelective(HwOrderUnpaid record);

    int updateByPrimaryKey(HwOrderUnpaid record);

    
    /**
     * 返回主键添加
     * @param hwOrder
     */
	void insertSelectiveBcakId(HwOrderUnpaid hwOrder);

	/**
	 * 根据订单编号查询
	 * @param order_num
	 * @return
	 */
	@Select("select * from hw_order_unpaid  where  order_num = #{order_num}")
	HwOrderUnpaid selectByOrder_num(@Param("order_num")String order_num);

	/**
	 * 查询超时的
	 * @param time
	 * @return
	 */
	@Select("select * from hw_order_unpaid  where  create_time <= #{time}")
	List<HwOrderUnpaid> query_unpaid(@Param("time")long time);

	@Delete("delete  from hw_order_unpaid  where  create_time <= #{time}")
	void unpaid(@Param("time")long time);
}