package com.hwt.domain.mapper.bureau;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.bureau.HxBureauModule;

/**
 * @author Administrator
 *
 */
@Mapper
public interface HxBureauModuleMapper {


    int deleteByPrimaryKey(Long bureau_module_id);

    int insert(HxBureauModule record);

    int insertSelective(HxBureauModule record);


    HxBureauModule selectByPrimaryKey(Long bureau_module_id);



    int updateByPrimaryKeySelective(HxBureauModule record);

    int updateByPrimaryKey(HxBureauModule record);

	/**
	 * 根据条件获取条数给后台
	 * @param map
	 * @return
	 */
	int queryCountByMap(Map<String, Object> map);

	/**
	 * 根据条件获取数据给后台
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryListByMap(Map<String, Object> map);

	/**
	 * 通过id删除模块
	 * 
	 * @param id
	 * @param type
	 *            2-删除 1-恢复
	 * @return
	 */
	@Update("Update hx_bureau_module set is_hide = #{type} where bureau_module_id = #{id}")
	int deleteById(@Param("id")Integer id, @Param("type")Integer type);

	

	/**
	 * 根据用户id获取权限  没有被禁用的
	 * @param bureau_user_id
	 * @return
	 */
	@Select("SELECT bureau_module_id,name,url,parent_id,module_image from hx_bureau_module where bureau_module_id in (select b.bureau_module_id from hx_bureau_module_user b where b.bureau_user_id= #{bureau_user_id}) and is_hide = 1 order by sort")
	List<HxBureauModule> queryAllBy_user_id(@Param("bureau_user_id")Long bureau_user_id);

	/**
	 * 获取所有正常模块
	 * @return
	 */
	@Select("SELECT bureau_module_id,name,url,parent_id,module_image from hx_bureau_module where is_hide = 1")
	List<HxBureauModule> queryAllIsNotHide();

	

}