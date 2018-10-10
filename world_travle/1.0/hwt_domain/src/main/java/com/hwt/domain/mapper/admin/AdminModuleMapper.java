package com.hwt.domain.mapper.admin;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.admin.AdminModule;
import com.hwt.domain.entity.admin.vo.AdminModuleVo;

@Mapper
public interface AdminModuleMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(AdminModule record);

	int insertSelective(AdminModule record);

	AdminModule selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(AdminModule record);

	int updateByPrimaryKey(AdminModule record);

	/**
	 * 通过条件获取条数
	 * 
	 * @param map
	 * @return
	 */
	int queryCountByMap(Map<String, Object> map);

	/**
	 * 通过条件获取数据
	 * 
	 * @param map
	 * @return
	 */
	List<AdminModuleVo> queryListByMap(Map<String, Object> map);

	/**
	 * 通过id删除模块
	 * 
	 * @param id
	 * @param type
	 *            2-删除 1-恢复
	 * @return
	 */
	@Update("Update admin_module set isHide = #{type} where id = #{id}")
	int deleteById(@Param("id") Integer id, @Param("type") Integer type);

	/**
	 * 查询所有的模块
	 * 
	 * @return
	 */
	@Select("SELECT id,name,url,parentId,moduleImage,isHide from admin_module order by sort")
	List<AdminModule> queryAll();

	/**
	 * 根据角色id查询所有的模块
	 * 
	 * @param roleId
	 * @return
	 */
	@Select("SELECT id,name,url,parentId,moduleImage,isHide from admin_module where id in (select b.moduleId from admin_rolemodule b where b.roleId= #{roleId}) order by sort")
	List<AdminModule> queryAllByRoleId(@Param("roleId")Integer roleId);
	
	/**
	 * 查询所有的模块   没有被禁用的
	 * @return
	 */
	@Select("SELECT id,name,url,parentId,moduleImage from admin_module where isHide = 1 order by sort")
	List<AdminModule> queryAllIsNotHide();
	
	/**
	 * 根据角色id查询所有的模块   没有被禁用的
	 * @param role_id
	 * @return
	 */
	@Select("SELECT id,name,url,parentId,moduleImage from admin_module where id in (select b.moduleId from admin_rolemodule b where b.roleId= #{roleId}) and isHide = 1 order by sort")
	List<AdminModule> queryAllIsNotHideByRoleId(Integer role_id);
}