package com.hwt.domain.mapper.user.collect;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.user.collect.HwUserCollect;

@Mapper
public interface HwUserCollectMapper {

    int deleteByPrimaryKey(Long collect_id);

    int insert(HwUserCollect record);

    int insertSelective(HwUserCollect record);


    HwUserCollect selectByPrimaryKey(Long collect_id);


    int updateByPrimaryKeySelective(HwUserCollect record);

    int updateByPrimaryKey(HwUserCollect record);

	/**
	 * 查询收藏夹
	 * @param user_id
	 * @param startNum
	 * @param pageSize
	 * @return
	 */
    @Select("select * from hw_user_collect where user_id = #{user_id} order by create_time desc limit #{startNum}, #{pageSize}")
	List<HwUserCollect> query(@Param("user_id")Long user_id, @Param("startNum")int startNum, @Param("pageSize")Integer pageSize);

	/**
	 * 删除
	 * @param user_id
	 * @param collec_id
	 */
	void deleteIds(@Param("user_id")Long user_id, @Param("collect_ids")Long[] collec_id);

	/**
	 * 根据用户id 名字id 类型返回
	 * @param user_id
	 * @param name_id
	 * @param type
	 * @return
	 */
	@Select("select * from hw_user_collect where user_id = #{user_id} and name_id = #{name_id} and type = #{type}")
	HwUserCollect queryIsCollect(@Param("user_id")Long user_id, @Param("name_id")Long name_id, @Param("type")Integer type);

	/**
	 * 添加 返回主键
	 * @param hwUserCollect
	 */
	void insertBackId(HwUserCollect hwUserCollect);
}