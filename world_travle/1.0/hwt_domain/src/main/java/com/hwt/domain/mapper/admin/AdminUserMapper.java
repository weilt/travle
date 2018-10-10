package com.hwt.domain.mapper.admin;

import com.hwt.domain.entity.admin.AdminUser;
import com.hwt.domain.entity.admin.vo.AdminUserVo;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminUserMapper {

    /**
     * 删除用户
     * @param user_id
     * @return
     */
    int deleteByPrimaryKey(Long user_id);

    /**
     * 新增—全部字段新增
     * @param record
     * @return
     */
    int insert(AdminUser record);

    /**
     * 新增—部分字段新增
     * @param record
     * @return
     */
    int insertSelective(AdminUser record);

    /**
     * 查询指定用户
     * @param user_id
     * @return
     */
    AdminUser selectByPrimaryKey(Long user_id);

    /**
     * 查询指定用户
     * @return
     */
    List<AdminUser> queryAll();

    /**
     * 更新-部分字段
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(AdminUser record);

    /**
     * 更新-全部字段
     * @param record
     * @return
     */
    int updateByPrimaryKey(AdminUser record);
    
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
	List<AdminUserVo> queryListByMap(Map<String, Object> map);

	
	/**
	 * 通过id删除模块
	 * @param id
	 * @param type 2-删除 1-恢复
	 * @return
	 */
	@Update("Update admin_user set isenable = #{type} where user_id = #{id}" )
	int deleteById(@Param("id")Long user_id,@Param("type") Integer type);

	/**
	 * 重置密码
	 * @param id
	 * @param resetPassword 密码
	 * @return
	 */
	@Update("Update admin_user set password = #{resetPassword} where user_id = #{id}" )
	int resetPassword(@Param("id")Long id, @Param("resetPassword")String resetPassword);

	/**
	 * 通过用户名获取用户
	 * @param user_account
	 * @return
	 */
	@Select("select * from admin_user where user_account = #{user_account}")
	AdminUser queryByUser_account(@Param("user_account")String user_account);
}