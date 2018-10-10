package com.hwt.domain.mapper.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.hwt.domain.entity.order.HwOrderComment;
import com.hwt.domain.entity.order.vo.HwOrderCommentVo;

@Mapper
public interface HwOrderCommentMapper {

    int deleteByPrimaryKey(Long order_comment_id);

    int insert(HwOrderComment record);

    int insertSelective(HwOrderComment record);


    HwOrderComment selectByPrimaryKey(Long order_comment_id);


    int updateByPrimaryKeySelective(HwOrderComment record);

    int updateByPrimaryKey(HwOrderComment record);

	/**
	 * 返回主键添加
	 * @param hwOrderComment
	 */
	void insertBackId(HwOrderComment hwOrderComment);

	/**
	 * 查询
	 * @param map
	 * @return
	 */
	List<HwOrderCommentVo> query_comment(Map<String, Object> map);

	/**
	 * 查询差评数
	 * @param name_id
	 * @param name_type
	 * @return
	 */
	@Select("select count(order_comment_id) from hw_order_comment where name_id =#{name_id} and name_type = #{name_type} and score < 3")
	Long query_bad_comment(@Param("name_id")Long name_id, @Param("name_type")Integer name_type);

	/**
	 * 查询好评数
	 * @param name_id
	 * @param name_type
	 * @return
	 */
	@Select("select count(order_comment_id) from hw_order_comment where name_id =#{name_id} and name_type = #{name_type} and score > 3")
	Long query_good_comment(@Param("name_id")Long name_id, @Param("name_type")Integer name_type);

	/**
	 * 查询有图数
	 * @param name_id
	 * @param name_type
	 * @return
	 */
	@Select("select count(order_comment_id) from hw_order_comment where name_id =#{name_id} and name_type = #{name_type} and is_image = 1")
	Long query_have_image(@Param("name_id")Long name_id, @Param("name_type")Integer name_type);

	/**
	 * 查询中评数
	 * @param name_id
	 * @param name_type
	 * @return
	 */
	@Select("select count(order_comment_id) from hw_order_comment where name_id =#{name_id} and name_type = #{name_type} and score = 3")
	Long query_secondary_comment(@Param("name_id")Long name_id, @Param("name_type")Integer name_type);

	/**
	 * 查询线路最近的一次好评
	 * @param line_id
	 * @return
	 */
	List<HwOrderCommentVo> query_good_one_comment(@Param("name_id")Long line_id, @Param("name_type")Integer name_type);
}