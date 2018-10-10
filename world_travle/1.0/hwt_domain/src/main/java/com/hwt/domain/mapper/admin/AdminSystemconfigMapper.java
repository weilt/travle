package com.hwt.domain.mapper.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.admin.AdminSystemconfig;
import com.hwt.domain.entity.admin.vo.AdminSystemconfigVo;

@Mapper
public interface AdminSystemconfigMapper {


    int deleteByPrimaryKey(Integer id);

    int insert(AdminSystemconfig record);

    int insertSelective(AdminSystemconfig record);

    AdminSystemconfig selectByPrimaryKey(Integer id);


    int updateByPrimaryKeySelective(AdminSystemconfig record);

    int updateByPrimaryKey(AdminSystemconfig record);

	/**
	 * 根据条件获取总条数
	 * @param map
	 * @return
	 */
	int queryCountByMap(Map<String, Object> map);

	/**
	 * 根据条件获取数据
	 * @param map
	 * @return
	 */
	List<AdminSystemconfigVo> queryListByMap(Map<String, Object> map);

	/**
	 * 通过配置键获取值
	 * @param configValue 配置键
	 * @return
	 */
	@Select("select configValue from admin_systemconfig where configKey = #{configKey}")
	String queryValueByConfigKey(@Param("configKey")String configKey);
}