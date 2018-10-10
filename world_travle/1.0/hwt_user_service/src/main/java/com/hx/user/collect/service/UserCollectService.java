package com.hx.user.collect.service;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.user.collect.HwUserCollect;

public interface UserCollectService {

	
	/**
	 * 查询收藏夹
	 * @param map
	 * @return
	 */
	Map<String, Object> query(Long user_id, int startNum, Integer pageSize);

	/**
	 * 添加
	 * @param user_id
	 * @param name_id	内容的实际id
	 * @param type		类型 1-景点 2-导游 3-线路 4-咨询
	 */
	HwUserCollect insert(Long user_id, Long name_id, Integer type);

	
	/**
	 * 删除
	 * @param collec_ids	收藏夹id集 如   1,2,3
	 * @return
	 */
	void delete(Long user_id, String collec_ids);

}
