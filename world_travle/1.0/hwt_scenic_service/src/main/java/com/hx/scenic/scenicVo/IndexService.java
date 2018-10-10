package com.hx.scenic.scenicVo;

import java.util.List;
import java.util.Map;

public interface IndexService {

	/**
	 * 首页数据
	 * @param area_code 
	 * @return
	 */
	Map<String, Object> searchIndex(String area_code);

	

    /**
     * 综合搜索
     * @param searchKey	`		查询条件
     * @param type				类型  0-所有 1-景点 2-导游  3-线路  4-咨询    默认0
     * @param pageIndex			开始页	默认0
     * @param pageSize			展示条数	默认0
     * @return	
     */
	List<Map<String, Object>> search(String searchKey, Integer type, Integer pageIndex, Integer pageSize);


	/**
     * 猜你喜欢
     */
	List<Map<String, Object>> like(String like_field, Integer pageIndex, Integer pageSize);

	
	

}
