package com.hwt.domain.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hwt.domain.entity.order.HwInsurance;

@Mapper
public interface HwInsuranceMapper {

    int deleteByPrimaryKey(Long insurance_id);

    int insert(HwInsurance record);

    int insertSelective(HwInsurance record);


    HwInsurance selectByPrimaryKey(Long insurance_id);


    int updateByPrimaryKeySelective(HwInsurance record);


    int updateByPrimaryKey(HwInsurance record);

	/**
	 * 通过id集查询
	 * @param insubrance_ids
	 * @return
	 */
	List<HwInsurance> queryByInsubrance_ids(@Param("insurance_ids")Long[] insubrance_ids);
}