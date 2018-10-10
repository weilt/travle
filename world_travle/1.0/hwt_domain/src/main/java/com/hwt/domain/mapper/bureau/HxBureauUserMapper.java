package com.hwt.domain.mapper.bureau;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.bureau.HwTravelBureau;
import com.hwt.domain.entity.bureau.HxBureauUser;

public interface HxBureauUserMapper {


    int deleteByPrimaryKey(Long bureau_user_id);

    int insert(HxBureauUser record);

    int insertSelective(HxBureauUser record);


    HxBureauUser selectByPrimaryKey(Long bureau_user_id);

    int updateByPrimaryKeySelective(HxBureauUser record);

    int updateByPrimaryKey(HxBureauUser record);

	/**
	 * 通过账号获取
	 * @param bureau_user_account
	 * @return
	 */
    @Select("select * from hx_bureau_user where bureau_user_account = #{bureau_user_account}")
	HxBureauUser query_bureau_user_account(@Param("bureau_user_account")String bureau_user_account);

	/**
	 * 通过条件获取总条数
	 * @param map
	 * @return
	 */
	int queryCountByMap(Map<String, Object> map);

	/**
	 * 通过条件获取数据
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryListByMap(Map<String, Object> map);

	
	/**
	 * 通过id删除
	 * 
	 * @param id
	 * @param type
	 *            2-删除 1-恢复
	 * @return
	 */
	@Update("update hx_bureau_user set is_delete = #{type} where bureau_user_id = #{bureau_user_id}")
	int deleteById(@Param("bureau_user_id")Long id, @Param("type")Integer type);

	/**
	 * 通过id查询
	 * @param bureau_user_id
	 * @return
	 */
	@Select("select bureau_user_id ,bureau_user_account,phone,real_name,description,remarks from hx_bureau_user where bureau_user_id = #{bureau_user_id}")
	HxBureauUser queryByBureau_user_id(@Param("bureau_user_id")Long bureau_user_id);
}