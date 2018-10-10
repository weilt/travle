package com.hwt.domain.mapper.bureau;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.hwt.domain.entity.bureau.HxBureauModuleUser;

@Mapper
public interface HxBureauModuleUserMapper {


    int deleteByPrimaryKey(Long id);

    int insert(HxBureauModuleUser record);

    int insertSelective(HxBureauModuleUser record);


    HxBureauModuleUser selectByPrimaryKey(Long id);



    int updateByPrimaryKeySelective(HxBureauModuleUser record);

    int updateByPrimaryKey(HxBureauModuleUser record);
    
    
    /**
	 * 添加权限
	 * @param list
	 * @return
	 */
	int savaUserModule(List<HxBureauModuleUser> list);
	
	/**
	 * 通过用户id删除权限
	 * @param bureau_user_id
	 * @return
	 */
    @Delete("delete from hx_bureau_module_user where bureau_user_id = #{bureau_user_id}")
	int deleteByUserId(@Param("bureau_user_id")Long bureau_user_id);
}