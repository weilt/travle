package com.hx.user.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.user.Vo.HxUserNearbyVo;

/**
 * 用户查看附近的人
 * @author Administrator
 *
 */
public interface HxUserNearbyService {

	/**
	 * 查询附近的人
	 * @param user_id
	 * @param map
	 * @return
	 */
	List<HxUserNearbyVo> queryNearbyMap(Long user_id, Map<String, Object> map);

}
