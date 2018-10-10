package com.hwt.domain.mapper.cicerone;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.cicerone.HxCiceroneType;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneTypeVo;


public interface HxCiceroneTypeMapper {

    int deleteByPrimaryKey(Long id);

    int insert(HxCiceroneType record);

    int insertSelective(HxCiceroneType record);

	HxCiceroneType selectByPrimaryKey(Long id);

    List<HxCiceroneTypeVo > findAll();

    int updateByPrimaryKeySelective(HxCiceroneType record);

    int updateByPrimaryKey(HxCiceroneType record);

	/**
	 * 根据条件查询数据返回给admin
	 * @param map 
	 * @return
	 */
	List<Map<String, Object>> queryAllByMapToAdmin(Map<String, Object> map);

	/**
	 * 根据条件查询总条数返回给admin
	 * @return
	 */
	long queryCountAllByMapToAdmin(Map<String, Object> map);

	/**
	 * 查询该导游的所有类型
	 * @param user_id
	 * @return
	 */
	@Select("select a.id,a.type_value as typeValue from hx_cicerone_type a ,hx_cicerone_relevance b ,hx_cicerone_info c where c.user_id = #{user_id} and b.cic_id = c.user_id and a.id = b.type_id")
	List<HxCiceroneTypeVo> query_by_user_id(@Param("user_id")Long user_id);

	
	@Select("select a.id,a.type_value as typeValue from hx_cicerone_type a ,hx_cicerone_relevance b  where  a.id = b.type_id and b.cic_id = #{user_id}")
	List<HxCiceroneTypeVo> query_by_cicerone_id(@Param("user_id")Long user_id);
}