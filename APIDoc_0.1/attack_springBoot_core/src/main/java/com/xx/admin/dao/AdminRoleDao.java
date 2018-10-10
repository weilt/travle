package com.xx.admin.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.xx.admin.entity.AdminRole;

@Mapper
public interface AdminRoleDao {

	/**
	 * 根据ID查询角色信息
	 * @param id
	 * @return
	 */
	@Select("select * from admin_role where id = #{id}")
	AdminRole findById(@Param("id")Integer id);
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
	List<AdminRole> queryList(Map<String, Object> map);
	
	/**
	 * 添加角色
	 * @param adminRole
	 * @return
	 */
	@Insert("insert into admin_role(name,number,description,userId,createTime)values(#{name},0,#{description},#{userId},NOW())")
	int insert(AdminRole adminRole);
	
	/**
	 * 修改角色
	 * @param adminRole
	 * @return
	 */
	@Update("update admin_role set name=#{name},description=#{description} where id = #{id}")
	int update(AdminRole adminRole);
	
	/**
	 * 修改角色的 人员数量 - 指定角色
	 * @param id
	 * @return
	 */
	@Update("update admin_role set number = (select COUNT(id) from admin_user where roleId = #{id}) where id = #{id}")
	int update_number(int id);
	/**
	 * 重置角色的 所有人员数量 
	 * @param id
	 * @return
	 */
	@Update("UPDATE admin_role a SET a.number = (SELECT COUNT(id) FROM admin_user WHERE roleId = a.id)")
	int update_numberAll();
	
	/**
	 * 根据ID删除角色
	 * @param id
	 * @return
	 */
	@Delete("DELETE FROM admin_role WHERE id = #{id}")
	int delete(Integer id);
	
	/**
	 * 查询超级管理员以下的数据信息
	 * @return
	 */
	@Select("SELECT * FROM admin_role where id > 1 order by id")
	List<AdminRole> queryAllList();
	
	/**
	 * 加减角色数量信息
	 * @param id
	 * @param number
	 * @return
	 */
	@Update("update admin_role set number = number + #{number} where id = #{id}")
	int findUpdateNumber(@Param("id")Integer id,@Param("number")int number);
	
}
