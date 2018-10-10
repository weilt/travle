package com.hwt.domain.mapper.user;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.user.HxUserConfig;
import com.hwt.domain.entity.user.Vo.HxUserConfigVo;


/**
 * 淮信用户配置持久层
 * @author Administrator
 *
 */
@Mapper
public interface HxUserConfigMapper {
   
    /**
     * 根据用户id删除
     * @param user_id
     * @return
     */
    int deleteByPrimaryKey(Long user_id);

    /**
     * 添加
     * @param record
     * @return
     */
    int insert(HxUserConfig record);

    
    /**
     * 添加 空值为默认
     * @param record
     * @return
     */
    int insertSelective(HxUserConfig record);


    /**
     * 通过用户id查找
     * @param user_id
     * @return
     */
    HxUserConfig selectByPrimaryKey(Long user_id);


    /**
     * 更新 空值不更新
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(HxUserConfig record);

    /**
     * 更新 空值null
     * @param record
     * @return
     */
    int updateByPrimaryKey(HxUserConfig record);
    
    /**
     * 修改用户配置
     * @param hxUserConfig
     */
    void updateUserConfig(HxUserConfig hxUserConfig);
    
    /**
     * 根据用户ID查询用户信息
     * @param user_id
     * @return
     */
    @Select("select user_id, communication_add_me_validate, communication_to_me_recommend_communication ,friend_circle_stranger_see_10, friend_circle_friend_see_day"
    		+ " from hx_user_config where user_id=#{user_id}")
    HxUserConfigVo queryByUserId(@Param("user_id")Long user_id);
}