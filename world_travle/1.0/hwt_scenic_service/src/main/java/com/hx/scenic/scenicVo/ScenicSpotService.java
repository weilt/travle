package com.hx.scenic.scenicVo;

import java.util.List;
import java.util.Map;

public interface ScenicSpotService {
	
	boolean createMapping();

	/**
	 * 根据条件分页·查询景点列表
	 * @param str 关键字
	 * @param pageIndex 开始下标
	 * @param pageSize 每页展示条数
	 * @return
	 */
	List<Map<String,Object>> queryScenic(String str, Integer pageIndex, Integer pageSize);


	/**
	 *  热门 OR 推荐
	 * @param isHot_or_isRecommend
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> hotOrRecommend(Integer isHot_or_isRecommend, Integer pageIndex, Integer pageSize);

	/**
	 *  首页热门和推荐查询
	 * @return
	 */
	Map<String,Object> queryIndex();

	/**
	 * 景点详情
	 * @param spotId
	 * @param user_id 
	 * @return
	 */
	Map<String,Object> querySpotInfo(Long spotId, Long user_id);

	 /**
     * 景点检索输入时返回值
     * @param filed  内容
     * @return
     */
	List<Map<String, Object>> query_sight_search(String filed);

	 /**
     * 省级联动查询
     * @param province  省
     * @param areaCodes		市集合
	 * @param pageSize 
	 * @param pageIndex 
     * @return
     */
	List<Map<String, Object>> province_city_search(String province, String areaCodes, Integer pageIndex, Integer pageSize);

	


}
