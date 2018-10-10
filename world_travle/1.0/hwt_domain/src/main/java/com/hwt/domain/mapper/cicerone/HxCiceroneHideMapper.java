package com.hwt.domain.mapper.cicerone;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.cicerone.HxCiceroneHide;

@Mapper
public interface HxCiceroneHideMapper {

    int deleteByPrimaryKey(Long user_id);

    int insert(HxCiceroneHide record);

    int insertSelective(HxCiceroneHide record);

    HxCiceroneHide selectByPrimaryKey(Long user_id);

    int updateByPrimaryKeySelective(HxCiceroneHide record);

    int updateByPrimaryKey(HxCiceroneHide record);

	/**
	 * 查询所有
	 * @return
	 */
    @Select("select * from hx_cicerone_hide")
	List<HxCiceroneHide> queryAll();
}