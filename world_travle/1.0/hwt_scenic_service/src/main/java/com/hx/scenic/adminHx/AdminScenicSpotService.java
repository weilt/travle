package com.hx.scenic.adminHx;

import java.util.Map;

import com.hwt.domain.entity.mg.scenic.ScenicSpot;
import com.hx.core.page.asyn.PageResult;

public interface AdminScenicSpotService {

	/**
	 * 通过条件查询数据
	 * @param map
	 * @return
	 */
	PageResult<Map<String,Object>> queryByMap(Map<String, Object> map);

	/**
	 * 删除/恢复
	 * @param spotId 景点id
	 * @param type
	 * @return
	 */
	boolean deleteById(Long spotId, Integer type);

	/**
	 * 通过id查询详情
	 * @param spotId
	 * @return
	 */
	Map<String, Object> queryScenicInfoBySpotId(Long spotId);

	/**
	 * 修改景点
	 * @param scenicSpot
	 */
	void update(ScenicSpot scenicSpot);

	/**
	 * 添加
	 * @param scenicSpot
	 */
	void insert(ScenicSpot scenicSpot)  throws Exception;

	/**
	 * 通过id推荐
	 * 
	 * @param spotId
	 * @param type
	 *            0-不推荐 1-推荐
	 * @return
	 */
	boolean recommendById(Long spotId, Integer type);

	
	/**
	 * 通过id热门
	 * 
	 * @param spotId
	 * @param type
	 *            0-不热门 1-热门
	 * @return
	 */
	boolean isHotById(Long spotId, Integer type);

}
