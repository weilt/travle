package com.hwt.domain.mapper.area;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.area.AmapAreaCode;
import com.hwt.domain.entity.area.vo.CityVo;
import com.hwt.domain.entity.area.vo.ProvinceCityVo;

@Mapper
public interface AmapAreaCodeMapper {


    int deleteByPrimaryKey(Long id);

    int insert(AmapAreaCode record);

    int insertSelective(AmapAreaCode record);


    AmapAreaCode selectByPrimaryKey(Long id);



    int updateByPrimaryKeySelective(AmapAreaCode record);

    int updateByPrimaryKey(AmapAreaCode record);

	/**
	 * 获取所有的市
	 * @return
	 */
    @Select("select city_code, city_name,area_code from amap_area_code where level = 'city' order by convert(city_name using gbk) asc")
	List<CityVo> getAll_city_code();
    /**
	 * 获取所有的省
	 * @return
	 */
    @Select("select province_code, province_name from amap_area_code where level = 'province' order by convert(province_name using gbk) asc")
	List<ProvinceCityVo> getAllProvince();

	/**
	 * 通过省级编码获取市
	 * @param province_code
	 * @return
	 */
    @Select("select city_code, city_name,area_code from amap_area_code where level = 'city' and province_code = #{province_code} order by convert(city_name using gbk) asc") 
	List<CityVo> getAll_city_code_by_province_code(@Param("province_code")String province_code);
}