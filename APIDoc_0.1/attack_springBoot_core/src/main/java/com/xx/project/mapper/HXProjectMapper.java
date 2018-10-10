package com.xx.project.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xx.project.entity.HXProject;

import scala.annotation.meta.param;
@Mapper
public interface HXProjectMapper {

	/**
	 * 查询满足条件总条数
	 * @param map 条件
	 * @return
	 */
	int queryListCount(Map<String, Object> map);

	/**
	 * 查询满足条件数据
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryList(Map<String, Object> map);

	/**
	 * 添加
	 * @param project 项目
	 * @return
	 */
	@Insert("insert into hx_project(name,userId,description,isDelete,createTime,updateTime)"
			+ "values(#{name},#{userId},#{description},#{isDelete},NOW(),NOW())")
	int insert(HXProject project);
	
	/**
	 * 通过主键查找
	 * @param id
	 * @return
	 */
	@Select("select * from hx_project where id = #{id}")
	HXProject findById(@Param("id")Integer id);
	
	/**
	 * 跟新
	 * @param project
	 * @return
	 */
	int update(HXProject project);
	
	/**
	 * 改状态
	 * @param id
	 * @param status
	 * @return
	 */
	@Update("update hx_project set isDelete = #{status} ,updateTime = NOW() where id = #{id}")
	int delete(@Param("id")int id, @Param("status")Integer status);

}