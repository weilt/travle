package com.domain.admin.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.domain.admin.entity.AdminRolemodule;

@Mapper
public interface AdminRolemoduleMapper {

	/**
	 * 根据角色删除 授权的目录信息
	 * @param roleId
	 * @return
	 */
	@Delete("DELETE FROM admin_rolemodule WHERE roleId = #{roleId}")
	int deleteByRoleId(Integer roleId);
	
	/**
	 * 批量授权添加信息
	 * @param list
	 * @return
	 */
	int savaRoleModule(List<AdminRolemodule> list);
}
