package com.hwt.domain.mapper.cicerone;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.hwt.domain.entity.cicerone.HxCiceroneInfo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVo;
import com.hwt.domain.entity.cicerone.vo.HxCiceroneInfoVoInfo;

@Mapper
public interface HxCiceroneInfoMapper {


    int insertSelective(HxCiceroneInfo record);

    int updateSelective(HxCiceroneInfo record);

    int updateByPrimaryKeySelective(HxCiceroneInfo record);

    
    @Select(" SELECT count(cicerone_id) FROM hx_cicerone_info WHERE  user_id = #{userId}")
    long countByUserId(@Param("userId")Long userId);



    HxCiceroneInfo findByUserId(Long userId);


	List<HxCiceroneInfoVo> queryByList(Map<String, Object> map);

	/**
	 * 根据条件查询条数
	 * @param map
	 * @return
	 */
	long queryCountByMapToAdmin(Map<String, Object> map);

	/**
	 * 根据条件查询条数
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryByMapToAdmin(Map<String, Object> map);

	/**
	 * 查询总条数
	 * @return
	 */
	@Select("select count(user_id) from hx_cicerone_info")
	long queryCount();

	/**
	 * 根据status查询总条数
	 * @param status
	 * @return
	 */
	@Select("select count(user_id) from hx_cicerone_info where status = #{status}")
	long queryCountByStatus(@Param("status")int status);

	/**
	 * 查询所有
	 * @return
	 */
	@Select("select * from hx_cicerone_info")
	List<HxCiceroneInfo> queryAll();

	/**
	 * 通过id查询详情
	 * @param id
	 * @return
	 */
	@Select("select a.*,b.account,b.account_phone  from hx_cicerone_info a, hx_user b where a.user_id = #{user_id} and a.user_id = b.user_id")
	Map<String, Object> queryById(@Param("user_id")Long user_id);

//	/**
//	 * 根据用户id返回HxCiceroneInfoVo
//	 * @param user_id
//	 * @return
//	 */
//	@Select("select * from hx_cicerone_info where user_id = #{user_id}")
//	HxCiceroneInfoVo queryHxCiceroneInfoVoByUserId(@Param("user_id")Long user_id);
	
	/**
	 * 获取导游资料详情
	 * @param user_id
	 * @return
	 */
	@Select("select * from hx_cicerone_info where user_id = #{user_id}")
	HxCiceroneInfoVoInfo queryHxCiceroneHxCiceroneInfoVoInfoByUserId(@Param("user_id")Long user_id);

	/**
	 * 审核
	 * @param record
	 */
	@Update("update  hx_cicerone_info set reason = #{reason} ,status = #{status}, examine_time = CONCAT(UNIX_TIMESTAMP(NOW()), RIGHT(NOW(3), 3)) where user_id = #{user_id} and status = 1")
	int cicerone_verification(HxCiceroneInfo record);

	/**
	 * 通过id查询导游详情
	 * @param id
	 * @return
	 */
	@Select("select * from hx_cicerone_info where user_id = #{user_id}")
	HxCiceroneInfoVoInfo queryHxCiceroneHxCiceroneInfoVoInfoById(@Param("user_id")Long user_id);

	/**
	 * 修改工作时间
	 * @param workTime
	 * @param user_id
	 * @param id
	 */
	@Update("update  hx_cicerone_info set work_time = #{workTime}  where user_id = #{user_id}")
	void update_workTime(@Param("workTime")String workTime, @Param("user_id")Long user_id);

	/**
	 * 修改导游的故事
	 * @param about
	 * @param user_id
	 * @param id
	 */
	@Update("update  hx_cicerone_info set about = #{about}  where  user_id = #{user_id}")
	void update_about(@Param("about")String about,  @Param("user_id")Long user_id);

	@Select("select * from hx_cicerone_info where user_id = #{user_id}")
	HxCiceroneInfo queryHxCiceroneInfoById(@Param("user_id")Long user_id);

	/**
	 * 修导游封面
	 * @param cover
	 * @param user_id
	 */
	@Update("update  hx_cicerone_info set cover = #{cover}  where  user_id = #{user_id}")
	void update_cover(@Param("cover")String cover,  @Param("user_id")Long user_id);
	
	/**
	 * 修改导游的故事和封面
	 * @param about
	 * @param cover
	 * @param user_id
	 */
	@Update("update  hx_cicerone_info set about = #{about}, cover = #{cover}  where  user_id = #{user_id}")
	void update_about_cover(@Param("about")String about, @Param("cover")String cover, @Param("user_id")Long user_id);

	/**
	 * 导游被预约次数+1
	 * @param cicerone_id
	 */
	@Update("update  hx_cicerone_info set is_reserved_num = (is_reserved_num + 1)  where  user_id = #{user_id}")
	void is_reserved_numadd1( @Param("user_id")Long user_id);

	/**
	 * 导游接单次数+1
	 */
	@Update("update  hx_cicerone_info set service_count = (service_count + 1)  where  user_id = #{user_id}")
	void service_countadd1(@Param("user_id")Long user_id);

	/**
	 * 解除禁用
	 * @param user_id
	 */
	@Update("update  hx_cicerone_info set is_hide = 0  where  user_id = #{user_id}")
	void is_not_hide(@Param("user_id")Long user_id);

	/**
	 * 3天不能接单
	 */
	@Update("update  hx_cicerone_info set is_hide = 3  where  user_id = #{user_id}")
	void is_hide(@Param("user_id")Long user_id);

	/**
	 * 最加点评分数
	 * @param score
	 */
	@Update("update  hx_cicerone_info set score_num = score_num +1 ,score = (score + #{score})   where  user_id = #{user_id}")
	void addScore(@Param("user_id")Long user_id,@Param("score")Float score);

	
}