package com.hwt.domain.mapper.order;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.order.HwOrderUsuallyUser;
import com.hwt.domain.entity.order.vo.HwOrderUsuallyUserVo;


@Mapper
public interface HwOrderUsuallyUserMapper {


    int deleteByPrimaryKey(Long usually_id);

    int insert(HwOrderUsuallyUser record);

    int insertSelective(HwOrderUsuallyUser record);


    HwOrderUsuallyUser selectByPrimaryKey(Long usually_id);



    int updateByPrimaryKeySelective(HwOrderUsuallyUser record);

    int updateByPrimaryKey(HwOrderUsuallyUser record);

	/**
	 * 查询条数
	 * @param user_id
	 * @return
	 */
    @Select("select count(usually_id) from hw_order_usually_user where user_id = #{user_id}")
	int queryCount(@Param("user_id")Long user_id);

    /**
     * 删除最小的不是本人的
     * @param user_id
     */
    @Delete("delete from hw_order_usually_user where usually_id = #{usually_id} ")
	void delete_beyond(@Param("usually_id")Long usually_id);

    /**
     * 返回所有
     * @param user_id
     * @return
     */
    @Select("select * from hw_order_usually_user where user_id = #{user_id} order by is_own desc")
	List<HwOrderUsuallyUserVo> query(@Param("user_id")Long user_id);

    /**
     * 查看详情
     * @param user_id
     * @param usually_id
     * @return
     */
    @Select("select * from hw_order_usually_user where user_id = #{user_id} and usually_id = #{usually_id}")
	HwOrderUsuallyUserVo queryDetails(@Param("user_id")Long user_id, @Param("usually_id")Long usually_id);

    /**
     * 取消是本人
     * @param user_id
     */
    @Update("update hw_order_usually_user set is_own = 0 where user_id = #{user_id} and is_own = 1")
	void cancel_own(@Param("user_id")Long user_id);

	/**
	 * 删除
	 * @param user_id
	 * @param usually_id
	 */
    @Delete("delete from hw_order_usually_user where user_id = #{user_id} and usually_id = #{usually_id}")
	void delete(@Param("user_id")Long user_id, @Param("usually_id")Long usually_id);

    /**
     * 查询最小id
     * @param user_id
     * @return
     */
    @Select("select min(usually_id) from hw_order_usually_user where user_id = #{user_id} and is_own != 1")
	Long query_min(@Param("user_id")Long user_id);
}