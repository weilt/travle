package com.hwt.domain.mapper.admin;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

import com.hwt.domain.entity.admin.AdminRolemodule;

public interface AdminRolemoduleMapper {

    int deleteByPrimaryKey(Long id);

    int insert(AdminRolemodule record);

    int insertSelective(AdminRolemodule record);


    AdminRolemodule selectByPrimaryKey(Long id);



    int updateByPrimaryKeySelective(AdminRolemodule record);

    int updateByPrimaryKey(AdminRolemodule record);

	/**
	 * 通过角色id删除权限
	 * @param id
	 * @return
	 */
    @Delete("delete from admin_rolemodule where roleId = #{roleId}")
	int deleteByRoleId(@Param("roleId")Integer roleId);

	/**
	 * 添加权限
	 * @param list
	 * @return
	 */
	int savaRoleModule(List<AdminRolemodule> list);
}