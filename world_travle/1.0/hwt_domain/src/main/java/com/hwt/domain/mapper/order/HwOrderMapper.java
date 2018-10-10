package com.hwt.domain.mapper.order;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.vo.HwOrderVo;
import com.hwt.domain.entity.order.vo.HxOrderInfoBaseVo;
import com.hwt.domain.entity.order.vo.OrderInfoVO;

@Mapper
public interface HwOrderMapper {


    int deleteByPrimaryKey(Long order_id);

    int insert(HwOrder record);

    int insertSelective(HwOrder record);


    HwOrder selectByPrimaryKey(Long order_id);



    int updateByPrimaryKeySelective(HwOrder record);

    int updateByPrimaryKey(HwOrder record);

	/**
	 * 通过条件查询返回给游客
	 * @param map
	 * @return
	 */
	List<OrderInfoVO> qureyByMapToTourist(Map<String, Object> map);

	

	

	/**
	 * 通过条件查询返回给导游
	 * @param map
	 * @return
	 */
	List<OrderInfoVO> qureyByMapToCicerone(Map<String, Object> map);

	/**
	 * 通过订单id 下单人id查询详情
	 * @param order_id
	 * @param user_id
	 * @return
	 */
	@Select("select * from hw_order a, hx_order_info b where a.order_id = b.order_id and a.order_id = #{order_id} and a.user_id = #{user_id}")
	OrderInfoVO query_info_by_order_id_user_id(@Param("order_id")Long order_id, @Param("user_id")Long user_id);

	/**
	 * 根据订单id 导游id 查询详情
	 * @param order_id
	 * @param cicerone_id
	 * @return
	 */
	@Select("select * from hw_order a, hx_order_info b where a.order_id = b.order_id and a.order_id = #{order_id} and a.cicerone_id = #{cicerone_id}")
	OrderInfoVO query_info_by_order_id_cicerone_id(@Param("order_id")Long order_id, @Param("cicerone_id")Long cicerone_id);

	/**
	 * 游客删除订单
	 * @param user_id
	 * @param order_id
	 */
	@Update("update hw_order set tourist_is_delete = 1 where order_id = #{order_id} and user_id = #{user_id} and status in (3,5,6)  ")
	void tourist_delete(@Param("user_id")Long user_id, @Param("order_id")Long order_id);

	/**
	 * 返回主键添加
	 * @param hwOrder
	 */
	void insertSelectiveBcakId(HwOrder hwOrder);

	/**
	 * 客户端查询订单
	 * @param map
	 * @return
	 */
	List<HxOrderInfoBaseVo> qureyByMapToHx(Map<String, Object> map);

	/**
	 * 通过id查询
	 * @param order_id
	 * @return
	 */
	@Select("select * from hw_order  where  order_id = #{order_id}")
	HwOrderVo query_by_order_id(@Param("order_id")Long order_id);

	/**
	 * 根据订单编号查询
	 * @param order_num
	 * @return
	 */
	@Select("select * from hw_order  where  order_num = #{order_num}")
	HwOrder selectByOrder_num(@Param("order_num")String order_num);

	/**
	 * 订单自动完成
	 * @param currentTimeMillis 
	 */
	@Update("update hw_order set completion_time = UNIX_TIMESTAMP()*1000 , status = 3, update_time = #{currentTimeMillis} where end_time < #{currentTimeMillis} and status = 2")
	void auto_complete(@Param("currentTimeMillis")long currentTimeMillis);

	/**
	 * 更具条件查询给旅行社
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> qureyByMapToBureau(Map<String, Object> map);

	/**
	 *  更具条件查询条数给旅行社
	 * @param map
	 * @return
	 */
	Long qureyCountByMapToBureau(Map<String, Object> map);

	/**
	 * 通过旅行社订单查询所有
	 * @param bureau_id
	 * @return
	 */
	@Select("select * from hw_order where status != 0 and status != 7  and `status` != 6 and bureau_id = #{bureau_id}")
	List<HwOrder> queryAllByBureau_id(@Param("bureau_id")long bureau_id);

	/**
	 * 旅行社确认订单
	 * @param order_id
	 * @param bureau_id
	 */
	@Update("update hw_order set status = 2, accept_time = UNIX_TIMESTAMP()*1000, update_time = UNIX_TIMESTAMP()*1000 where order_id = #{order_id} and bureau_id = #{bureau_id} and status = 1  ")
	void bureauConfirm(@Param("order_id")Long order_id, @Param("bureau_id")Long bureau_id);

	/**
	 * 订单评论
	 * 
	 * @param user_id
	 */
	@Update("update hw_order set buyer_rate = 1 where order_id = #{order_id} ")
	void comment(@Param("order_id")Long order_id);

	/**
	 * 旅行社拒绝订单
	 * @param bureau_id
	 * @param order_id
	 */
	@Update("update hw_order set status = 6, refuse_time = UNIX_TIMESTAMP()*1000, update_time = UNIX_TIMESTAMP()*1000 where order_id = #{order_id} and bureau_id = #{bureau_id} and status = 1  ")
	void bureauOrderRefuse(@Param("order_id")Long order_id, @Param("bureau_id")Long bureau_id);

	/**
	 *自动拒绝
	 * @param time 
	 * @param currentTimeMillis 
	 */
	@Update("update hw_order set refuse_time =  #{currentTimeMillis} , status = 6, update_time = #{currentTimeMillis} where create_time < #{time} and status = 1")
	void auto_refuse(@Param("currentTimeMillis")long currentTimeMillis, @Param("time")long time);

	/**
	 * 自动拒绝查询
	 * @param time 
	 * @return 
	 */
	@Select("select * from hw_order where create_time < #{time} and status = 1")
	List<HwOrder> query_auto_refuse(@Param("time")long time);
	
	/**
	 * 旅行社取消订单
	 * @param order_id
	 * @param bureau_id
	 */
	@Update("update hw_order set apply_sales_time = UNIX_TIMESTAMP()*1000 , status = 4, update_time = UNIX_TIMESTAMP()*1000 where   order_id = #{order_id} and bureau_id = #{bureau_id} and status = 2 and start_time > UNIX_TIMESTAMP()*1000")
	void bureauOrderCancel(@Param("order_id")Long order_id, @Param("bureau_id")Long bureau_id);
	
	/**
	 * 查询可以完成的订单
	 * @param currentTimeMillis 
	 * @return
	 */
	@Select("select * from  hw_order  where end_time < #{currentTimeMillis} and status = 2")
	List<HwOrder> query_auto_complete(@Param("currentTimeMillis")long currentTimeMillis);

	/**
	 * 根据旅行社id 查询今日订单数
	 * @param bureau_id
	 * @param time 
	 * @return
	 */
	@Select("select count(order_id) from  hw_order  where bureau_id =#{bureau_id} and status = 1 and create_time >=#{time}")
	Long query_today_order_by_bureau_id(@Param("bureau_id")Long bureau_id, @Param("time")Long time);

	/**
	 * 根据旅行社id 查询 今日开始订单
	 * @param bureau_id
	 * @param time
	 * @return
	 */
	@Select("select count(order_id) from  hw_order  where bureau_id =#{bureau_id} and status = 2 and start_time =#{time}")
	Long query_today_start_order_by_bureau_id(@Param("bureau_id")Long bureau_id, @Param("time")Long time);

	/**
	 * 根据旅行社id 查询 今日结束订单
	 * @param bureau_id
	 * @param time
	 * @return
	 */
	@Select("select count(order_id) from  hw_order  where bureau_id =#{bureau_id} and status = 3 and end_time = #{time}")
	Long query_today_end_order_by_bureau_id(@Param("bureau_id")Long bureau_id, @Param("time")Long time);

	/**
	 * 根据旅行社id 查询 今日退款订单
	 * @param bureau_id
	 * @param time
	 * @return
	 */
	@Select("select count(order_id) from  hw_order  where bureau_id =#{bureau_id} and status = 4 and apply_sales_time >= #{time}")
	Long query_today_refundable_order_by_bureau_id(@Param("bureau_id")Long bureau_id, @Param("time")Long time);

	/**
	 * 旅行社订单统计
	 * @param bureau_id
	 * @param start
	 * @param end
	 * @return
	 */
	@Select("select * from hw_order where bureau_id =#{bureau_id} and accept_time is not null and create_time >= #{start} and create_time< #{end}")
	List<HwOrder> query_statistics_to_bureau(@Param("bureau_id")Long bureau_id, @Param("start")Long start, @Param("end")Long end);

	/**
	 * 查询可结算订单
	 * @return
	 */
	@Select("select * from  hw_order  where completion_time < #{time} and status = 3 and is_settlement = 0")
	List<HwOrder> query_settlement(@Param("time")Long time);

	/**
	 * 结算成功
	 * @param time
	 * @param l 
	 */
	@Update("update hw_order set is_settlement = 1, status = 3, update_time = currentTimeMillis where  completion_time < #{time} and status = 3 and is_settlement = 0")
	void auto_settlement(@Param("currentTimeMillis")Long currentTimeMillis, @Param("time")Long time);

	/**
	 * 根据订单id 导游id 确认订单
	 * @param order_id
	 * @param cicerone_id
	 */
//	@Update("update hw_order set status = 2 where order_id = #{order_id} and cicerone_id = #{cicerone_id}")
//	void cicerone_confirm_order(@Param("order_id")Long order_id, @Param("cicerone_id")Long cicerone_id);
}