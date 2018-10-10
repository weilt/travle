package com.xx.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xx.admin.entity.AdminModule;


@Mapper
public interface AdminModuleDao {

	
	/**
	 * 查询所有的模块信息
	 * @return
	 */
	@Select("SELECT id,name,url,parentId,moduleImage,isHide from admin_module order by sort")
	public List<AdminModule> queryList();
	
	/**
	 * 根据父级ID查询数据信息并返回
	 * @return
	 */
	@Select("SELECT * from admin_module where parentId = #{parentId}")
	public List<AdminModule> queryList_parentId(@Param("parentId")int parentId);
	
	/**
	 * 根据 角色 查询列表
	 * @param roleId 角色ID
	 * @return
	 */
	@Select("SELECT id,name,url,parentId,moduleImage,isHide from admin_module where id in (select b.moduleId from admin_rolemodule b where b.roleId= #{roleId}) order by sort")
	public List<AdminModule> findByRoleId(@Param("roleId")int roleId);
	
	/**
	 * 根据ID查询数据
	 * @param id
	 * @return
	 */
	@Select("SELECT * from admin_module where id = #{id}")
	AdminModule findById(@Param("id")Integer id);
	
	/**
	 * 根据ID查询模块名称
	 * @param id
	 * @return
	 */
	@Select("SELECT name from admin_module where id = #{id}")
	String findById_name(@Param("id")Integer id);
	
	/**
	 * 查询list集合
	 * @param map
	 * @return
	 */
	List<Map> queryListMap(Map<String, Object> map);
	
	/**
	 * 查询总数
	 * @param map
	 * @return
	 */
	int queryListMapCount(Map<String, Object> map);
	
	/**
	 * 添加数据信息
	 * @param adminModule
	 * @return
	 */
	@Insert("INSERT INTO admin_module(name,url,parentId,moduleImage,description,sort,isHide,userId,createTime) VALUES("
			+ "#{name},#{url},#{parentId},#{moduleImage},#{description},#{sort},#{isHide},#{userId},NOW())")
	int insert(AdminModule adminModule);
	
	/**
	 * 修改模块信息
	 * @param adminModule
	 * @return
	 */
	@Update("UPDATE admin_module SET name=#{name},url=#{url},parentId=#{parentId},moduleImage=#{moduleImage},description=#{description}"
			+ ",sort=#{sort},isHide=#{isHide},userId=#{userId} where id = #{id}")
	int update(AdminModule adminModule);
	
	/**
	 * 根据ID删除数据信息
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM admin_module where id = #{id}")
	int delete(@Param("id")int id);
	/**
	 * 根据父级parentId删除数据信息
	 * @param parentId
	 * @return
	 */
	@Delete("DELETE FROM admin_module where parentId = #{parentId}")
	int delete_parentId(@Param("parentId")int parentId);
}
