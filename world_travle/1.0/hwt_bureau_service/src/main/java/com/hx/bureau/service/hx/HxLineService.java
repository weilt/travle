package com.hx.bureau.service.hx;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.es.ESQuery;
import com.hwt.domain.entity.mg.travel.line.HwTravelLine;
import com.hwt.domain.entity.mg.travel.line.vo.HwTravelLineOtherVo;
import com.hwt.domain.entity.mg.travel.line.vo.HwTravelLineUpdateVo;
import com.hx.bureau.service.Vo.PageResultHxLine;

public interface HxLineService {

	/**
	 * 通过条件查询
	 * @param map
	 * @return
	 */
	PageResultHxLine  queryByMap(Map<String, Object> map);

	/**
	 * 添加
	 * @param hwTravelLine
	 */
	void insert(HwTravelLine hwTravelLine) throws Exception;

	/**
	 * 通过id 查询
	 * @param line_id
	 * @return
	 */
	Map<String, Object> queryByLineIdForUpdate(Long line_id);

	/**
	 * 更新
	 * @param hwTravelLine
	 */
	void update(HwTravelLineUpdateVo hwTravelLine);

	/**
	 * 通过id上下架
	 * 
	 * @param id
	 * @param type
	 *            0-下架  1-上架
	 * @return
	 */
	void deleteById(Long id, Integer type);

	/**
	 * 获取线路的属性信息
	 */
	Map<String, Object> qureyAttribute();

	/**
	 * 根据输入的返回景点信息
	 * @param scenic
	 * @return
	 */
	List<Map<String, Object>> qurey_scenic_spot(String scenic);

	/**
	 * 线路详情
	 * @param line_id
	 * @param user_id 
	 * @return
	 */
	Map<String, Object> details(Long line_id, Long user_id);

	/**
	 * 获取线路的属性
	 * @return
	 */
	Map<String, Object> getLineAttribute();

	/**
	 * 线路详情最下面的条数据返回
	 * @param user_id
	 * @param line_id
	 * @return
	 */
	HwTravelLineOtherVo details_other(Long user_id, Long line_id);

	/**
	 * 价格
	 * @param line_id
	 * @return
	 */
	Map<String, Object> line_price(Long line_id);

	
}
