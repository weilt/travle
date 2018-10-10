package com.hwt.domain.mapper.admin;

import com.hwt.domain.entity.admin.AdminRole;
import com.hwt.domain.entity.admin.vo.AdminRoleToUserVo;
import com.hwt.domain.entity.admin.vo.AdminRoleVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface AdminRoleMapper {
    /**
     * 删除指定角色
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入新角色 - 全部字段
     * @param record
     * @return
     */
    int insert(AdminRole record);

    /**
     * 插入新角色 - 插入时null存的默认
     * @param record
     * @return
     */
    int insertSelective(AdminRole record);

    /**
     * 更新角色 - 全部字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(AdminRole record);

    /**
     * 更新角色 - 部分字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AdminRole record);

    /**
     * 根据id 查询指定角色
     * @param id
     * @return
     */
    AdminRole selectByPrimaryKey(Integer id);



	/**
	 * 根据条件返回总条数
	 * @param map
	 * @return
	 */
	int queryCountByMap(Map<String, Object> map);

	/**
	 * 根据条件返回条数
	 * @param map
	 * @return
	 */
	List<AdminRoleVo> queryListByMap(Map<String, Object> map);

	/**
	 * 获取信息给用户操作
	 * @return
	 */
	@Select("select id as role_id,name as roleName from admin_role")
	List<AdminRoleToUserVo> queryToUser();
}