package com.hwt.domain.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hwt.domain.entity.order.HwOrderUser;
import com.hwt.domain.entity.order.vo.HwOrderUserInsert;
import com.hwt.domain.entity.order.vo.HwOrderUserVo;

@Mapper
public interface HwOrderUserMapper {

    int deleteByPrimaryKey(Long order_user_id);

    int insert(HwOrderUser record);

    int insertSelective(HwOrderUser record);


    int updateByPrimaryKeySelective(HwOrderUser record);

    int updateByPrimaryKey(HwOrderUser record);

	/**
	 * 多个添加
	 * @param orderUsers
	 */
	void insertSelectiveList(@Param("list")HwOrderUser[] orderUsers);
	
	/**
	 * 返回主键添加
	 * @param hwOrderUser
	 */
	void insertSelectiveBcakId(HwOrderUser hwOrderUser);

	/**
	 * 通过订单id查询用户信息以及保险信息
	 * @param order_id
	 * @return
	 */
	List<HwOrderUserVo> query_list_by_order_id(@Param("order_id")Long order_id);
}