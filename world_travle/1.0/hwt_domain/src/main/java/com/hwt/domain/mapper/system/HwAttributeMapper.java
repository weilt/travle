package com.hwt.domain.mapper.system;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.system.HwAttribute;

@Mapper
public interface HwAttributeMapper {

    int deleteByPrimaryKey(Integer attribute_id);

    int insert(HwAttribute record);

    int insertSelective(HwAttribute record);


    HwAttribute selectByPrimaryKey(Integer attribute_id);


    int updateByPrimaryKeySelective(HwAttribute record);

    int updateByPrimaryKey(HwAttribute record);

	/**
	 * 根据条件查询总条数
	 * @param map
	 * @return
	 */
	long queryCountByMapToAdmin(Map<String, Object> map);

	/**
	 * 根据条件查询数据
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryByMapToAdmin(Map<String, Object> map);

	
	/**
	 * 通过id删除
	 * @param id
	 * @param type 2-删除 1-恢复
	 * @return
	 */
	@Update("update hw_attribute set is_hide = #{type} where attribute_id = #{attribute_id}")
	void deleteById(@Param("attribute_id")Integer attribute_id, @Param("type")Integer type);

	/**
	 * 通过key获取key下所有属性
	 * @param key
	 * @return
	 */
	@Select("select * from hw_attribute where attribute_key = #{attribute_key}")
	List<HwAttribute> queryAll(@Param("attribute_key")String key);
}