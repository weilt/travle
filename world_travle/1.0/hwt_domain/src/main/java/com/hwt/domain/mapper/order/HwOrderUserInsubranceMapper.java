package com.hwt.domain.mapper.order;


import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hwt.domain.entity.order.HwOrderUserInsubrance;

@Mapper
public interface HwOrderUserInsubranceMapper {

    int deleteByPrimaryKey(Long user_insubrance_id);

    int insert(HwOrderUserInsubrance record);

    int insertSelective(HwOrderUserInsubrance record);


    HwOrderUserInsubrance selectByPrimaryKey(Long user_insubrance_id);


    int updateByPrimaryKeySelective(HwOrderUserInsubrance record);


    int updateByPrimaryKey(HwOrderUserInsubrance record);

    /**
	 * 多个添加
	 * @param hwOrderUserInsubrances
	 */
	void insertSelectiveList(@Param("list")List<HwOrderUserInsubrance> hwOrderUserInsubrances);
}