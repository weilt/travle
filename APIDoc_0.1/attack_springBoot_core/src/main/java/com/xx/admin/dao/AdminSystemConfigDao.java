package com.xx.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.xx.admin.entity.AdminSystemconfig;

@Mapper
public interface AdminSystemConfigDao {
	
	/**
	 * 根据key获取value值
	 * @param configKey
	 * @return
	 */
	@Select("select configValue from admin_systemConfig where configKey = #{configKey}")
	String getValue(@Param("configKey") String configKey);
	
	/**
	 * 根据ID查询 - 数据信息
	 * @param id
	 * @return
	 */
	@Select("select * from admin_systemConfig where id = #{id}")
	AdminSystemconfig findById(Integer id);
	
	/**
	 * 查询列表总数据信息 - 总数
	 * @param map
	 * @return
	 */
	int queryListCount(Map<String, Object> map);
	/**
	 * 查询列表信息
	 * @param map
	 * @return
	 */
	List<AdminSystemconfig> queryList(Map<String, Object> map);
	
	/**
	 * 添加数据信息
	 * @param adminSystemconfig
	 * @return
	 */
	int insert(AdminSystemconfig adminSystemconfig);
	
	/**
	 * 修改数据信息
	 * @param adminSystemconfig
	 * @return
	 */
	int update(AdminSystemconfig adminSystemconfig);
	
	/**
	 * 根据ID删除数据信息 - 强制删除
	 * @param id
	 * @return
	 */
	@Delete("delete from admin_Systemconfig where id = #{id}")
	int delete(Integer id);
	
}
