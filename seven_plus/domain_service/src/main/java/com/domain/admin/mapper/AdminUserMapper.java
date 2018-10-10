package com.domain.admin.mapper;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.domain.admin.entity.AdminUser;

@Mapper
public interface AdminUserMapper {
	
	/**
	 * 根据账号和密码查询用户信息
	 * @param userName
	 * @param passWord
	 * @return
	 */
	@Select("select * from admin_user where userName=#{userName} and passWord=#{passWord}")
	AdminUser userLogin(@Param("userName") String userName, @Param("passWord") String passWord);
	
	/**
	 * 根据账号查询用户数量是否含有
	 * @param userName
	 * @return
	 */
	@Select("select COUNT(id) from admin_user where userName=#{userName}")
	int findByUserNameCount(@Param("userName") String userName);
	
	/**
	 * 根据 ID 查询用户信息
	 * @param id
	 * @return
	 */
	@Select("select * from admin_user where id=#{id}")
	AdminUser findById(@Param("id") Long id);
	
	/**
	 * 根据ID - 修改密码
	 * @param passWord
	 * @param id
	 * @return
	 */
	@Update("update admin_user set passWord = #{passWord} where id = #{id}")
	int editPassWord(@Param("passWord") String passWord, @Param("id") Long id);
	/**
	 * 根据ID - 禁用启用用户
	 * @param isDelete  1-正常 2-禁用
	 * @param id
	 * @return
	 */
	@Update("update admin_user set isDelete = #{isDelete} where id = #{id}")
	int editIsDelete(@Param("isDelete") int isDelete, @Param("id") Long id);
	
	/**
	 * 添加用户信息
	 * @param adminUser
	 * @return
	 */
	int insert(AdminUser adminUser);
	/**
	 * 修改用户信息
	 * @param adminUser
	 * @return
	 */
	int update(AdminUser adminUser);
	
	/**
	 * 根据角色ID查询当前角色下用户数量
	 * @param roleId
	 * @return
	 */
	@Select("select COUNT(id) from admin_user where roleId = #{roleId}")
	int countByRoleId(Integer roleId);
	
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
	List<Map> queryList(Map<String, Object> map);
}
