package com.hx.scenic.scenicVo.imp;



import com.hwt.domain.entity.mg.scenic.vo.ScenicSpotVO;
import com.hwt.domain.entity.user.collect.HwUserCollect;
import com.hwt.domain.mapper.user.collect.HwUserCollectMapper;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.ObjectHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hx.core.es.entity.SearchRequestParam;
import com.hx.core.es.utils.ElasticsearchUtils;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.mongo.value.MongoQueryProjections;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.Sort;
import com.hx.scenic.scenicVo.ScenicSpotService;

@Service
public class ScenicSpotServiceImp implements ScenicSpotService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ScenicSpotServiceImp.class);
	
	@Autowired
	private HwUserCollectMapper hwUserCollectMapper;

	/**
	 *  用于是否删除，热卖，推荐
	 */
	public static final Integer IS_TRUE = 1;


	/**
	 * 热门 推荐默认条数
	 */
	public static final Integer  DEFAULT_PAGE_SIZE = 10;

	@Override
    public boolean createMapping(){
    	try {
			boolean createMapping = ElasticsearchUtils.createMapping(HXSystemConfig.ES_Collection_NAME_hwt, HXSystemConfig.ES_Collection_NAME_ScenicSpot);
			return createMapping;
    	} catch (Exception e) {
			
			e.printStackTrace();
		}
		return false;
    }
	/**
	 * 根据条件分页查询
	 */
	@Override
	public List<Map<String,Object>> queryScenic(String str, Integer pageIndex, Integer pageSize) {
		
		List<Map<String,Object>> queryList = null;
		try {
			SearchRequestParam param = new SearchRequestParam();
			param.setCurrentPage(pageIndex);
			param.setPageSize(pageSize);
			param.setIndexes(new String[]{HXSystemConfig.ES_Collection_NAME_hwt});
			param.setTypes(new String[]{HXSystemConfig.ES_Collection_NAME_ScenicSpot});
			
			param.setSortField((Map<String, SortOrder>) new HashMap<String, SortOrder>().put("dataTime",SortOrder.DESC));
			//高亮显示字段
			//param.setHighlightField(new String[]{"spotName","description","brief","tags","location","ticketInfo","city"});
			Map<String, Object> shouldStr = new HashMap<>();
			shouldStr.put("spotName", str);
			shouldStr.put("description", str);
			shouldStr.put("brief", str);
			shouldStr.put("tags", str);
			shouldStr.put("location", str);
			shouldStr.put("ticketInfo", str);
			shouldStr.put("city", str);
			param.setShouldStr(shouldStr);
			
			Map<String, Object> mustStr = new HashMap<>();
			mustStr.put("isHide", 1);
			param.setMustStr(mustStr);
			param.setMatchPhrase(false);
			queryList = ElasticsearchUtils.searchDataPage(param).getRecordList();
		} catch (Exception e) {
			LOGGER.error("ES查询出错：%m",e.getMessage());
			queryList =	queryData(str, null, null, null, pageIndex, pageSize);
		}
		return queryList;
	}

	@Override
	public List<Map<String, Object>> hotOrRecommend(Integer isHot_or_isRecommend, Integer pageIndex, Integer pageSize) {
		
		List<Map<String,Object>> queryList = null;
		try {
			SearchRequestParam param = new SearchRequestParam();
			param.setCurrentPage(pageIndex);
			param.setPageSize(pageSize);
			param.setIndexes(new String[]{HXSystemConfig.ES_Collection_NAME_hwt});
			param.setTypes(new String[]{HXSystemConfig.ES_Collection_NAME_ScenicSpot});
			param.setSortField((Map<String, SortOrder>) new HashMap<String, SortOrder>().put("dataTime",SortOrder.DESC));
			//高亮显示字段
			//param.setHighlightField(new String[]{"spotName","description","brief","tags","location","ticketInfo","city"});
			Map<String, Object> mustStr = new HashMap<>();
			if (isHot_or_isRecommend==1)
				mustStr.put("isHot", 1);
			if (isHot_or_isRecommend ==2)
				mustStr.put("isRecommend",1);
			param.setMustStr(mustStr);
			param.setMatchPhrase(false);
			queryList = ElasticsearchUtils.searchDataPage(param).getRecordList();
		} catch (Exception e) {
			LOGGER.error("ES查询出错：%m",e.getMessage());
			if (isHot_or_isRecommend==1)
				queryList =	queryData(null, true, false, null, pageIndex, pageSize);
			if (isHot_or_isRecommend ==2)
				queryList =	queryData(null, false, true, null, pageIndex, pageSize);
		}
		return queryList;
	}

	//MongoDB查询
	private List<Map<String,Object>> queryData (String str, Boolean isHot, Boolean isRecommend, String city, Integer pageIndex, Integer pageSize) {
		MongoQueryCondition conditionOr = new MongoQueryCondition(LinkKey.or);
		MongoQueryCondition conditionAnd = new MongoQueryCondition(LinkKey.and);
		if (!StringUtils.isEmpty(str)){
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "spotName",str));
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "tags",str));
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "remarks",str));
		}
//        conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "city",city));
		conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "isHide",IS_TRUE));
		if ( null != isHot && isHot)
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "isHot",IS_TRUE));
		if ( null != isRecommend && isRecommend)
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "isRecommend",IS_TRUE));
		//复合条件查询
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and,conditionOr,conditionAnd);

		List<Map<String, Object>> list = MongoService.findByPage(HXSystemConfig.MONGO_Collection_NAME_hwt,HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, null, condition, new Sort("spotId", Sort.ASC), pageIndex, pageSize);
		return list;
	}


	/**
	 *  将map对象转换为dto
	 * @param source
	 * @return
	 */
	private List<ScenicSpotVO> transformation (List<Map<String,Object>> source){

		if (ObjectHelper.isEmpty(source)){
			return Collections.EMPTY_LIST;
		}
		List<ScenicSpotVO> list = new ArrayList<>();
		source.stream().forEach(l -> {
			ScenicSpotVO scenicSpotDTO = ObjectHelper.map2Obj(l,ScenicSpotVO.class);
			list.add(scenicSpotDTO);
		});

		return list;
	}

	@Override
	public Map<String,Object> queryIndex() {
		Map<String,Object> resultMap = new HashMap<>();
		try {
			SearchRequestParam param = new SearchRequestParam();
			param.setCurrentPage(1);
			param.setPageSize(10);
			param.setIndexes(new String[]{HXSystemConfig.ES_Collection_NAME_hwt});
			param.setTypes(new String[]{HXSystemConfig.ES_Collection_NAME_ScenicSpot});
			
			param.setSortField((Map<String, SortOrder>) new HashMap<String, SortOrder>().put("dataTime",SortOrder.DESC));
			
			Map<String, Object> mustStr = new HashMap<>();
			mustStr.put("isHide", 1);
			mustStr.put("isHot", 1);
			param.setMustStr(mustStr);
			List<Map<String, Object>> hotList = ElasticsearchUtils.searchListData(param);
			
			resultMap.put("hotSpot",hotList);
			mustStr.remove("isHot");
			mustStr.put("isRecommend", 1);
			param.setMustStr(mustStr);
			List<Map<String, Object>> recommendList = ElasticsearchUtils.searchListData(param);
			resultMap.put("recommendSpot",recommendList);
			
		} catch (Exception e) {
			e.printStackTrace();
			MongoQueryCondition conditionAnd = new MongoQueryCondition(LinkKey.and);
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "isHide",IS_TRUE));
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "isHot",IS_TRUE));
			//复合条件查询
			MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and,conditionAnd);
			List<Map<String, Object>> hotList = MongoService.findByPage(HXSystemConfig.MONGO_Collection_NAME_hwt,HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, null, condition, new Sort("spotId", Sort.ASC), 0, DEFAULT_PAGE_SIZE);
			conditionAnd.clear();
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "isHide",IS_TRUE));
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "isRecommend",IS_TRUE));
			condition.clear();
			condition = new MongoQueryCondition(LinkKey.and,conditionAnd);
			List<Map<String, Object>> recommendList = MongoService.findByPage(HXSystemConfig.MONGO_Collection_NAME_hwt,HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, null, condition, new Sort("spotId", Sort.ASC), 0, DEFAULT_PAGE_SIZE);
			resultMap.put("hotSpot",hotList);
			resultMap.put("recommendSpot",recommendList);
		}
		
		return resultMap;
	}

	@Override
	public Map<String,Object> querySpotInfo(Long spotId, Long user_id) {
		MongoQueryCondition conditionAnd = new MongoQueryCondition(LinkKey.and);
		conditionAnd.add(new MongoQueryValue(MongoOperator.eq,"spotId",spotId));
		Map<String,Object> resultMap = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt,HXSystemConfig.MONGO_Collection_NAME_ScenicSpot,conditionAnd,null);
		
		Map<String, Object> map = new HashMap<>();
		map.put("scenic", resultMap);
		
		//查询关联线路
		MongoQueryProjections projection = new MongoQueryProjections();
		projection.put("line_id", 1);
		projection.put("line_name", 1);
		projection.put("line_cover", 1);
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
		condition.add(new MongoQueryValue(MongoOperator.eq, "is_hide", 0));
		condition.add(new MongoQueryValue(MongoOperator.regex, "scenics", ","+spotId+","));
		Sort sort = new Sort("sell_num", Sort.DESC);
		List<Map<String, Object>> findByPage = MongoService.findByPage(HXSystemConfig.MONGO_Collection_NAME_hwt,HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, projection, condition, sort, 1, 5);
		map.put("lines", findByPage);
		
		//查看是否被收藏
		map.put("is_collection", 0);
		if (!ObjectHelper.isEmpty(user_id)){
			HwUserCollect queryIsCollect = hwUserCollectMapper.queryIsCollect(user_id, spotId, 1);
			if (queryIsCollect!=null){
				map.put("is_collection", 1);
				map.put("collect_id", queryIsCollect.getCollect_id());
			}else {
				map.put("is_collection", 0);
			}
		}
		
		
		//访问次数加1
		Map<String, Object> filterMap  = new HashMap<>();
		filterMap.put("spotId",spotId);
		Map<String, Object> updateMap = new HashMap<>();
		updateMap.put("visitsNum",(Long.parseLong(String.valueOf(resultMap.get("visitsNum")==null?0:resultMap.get("visitsNum"))) + 1));
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt,HXSystemConfig.MONGO_Collection_NAME_ScenicSpot,filterMap,updateMap);
		return map;
	}

	@Override
	public List<Map<String, Object>> query_sight_search(String filed) {
		List<Map<String,Object>> list = null;
		try {
			SearchRequestParam param = new SearchRequestParam();
			param.setCurrentPage(1);
			param.setPageSize(10);
			param.setIndexes(new String[]{HXSystemConfig.ES_Collection_NAME_hwt});
			param.setTypes(new String[]{HXSystemConfig.ES_Collection_NAME_ScenicSpot});
			
			param.setSortField((Map<String, SortOrder>) new HashMap<String, SortOrder>().put("dataTime",SortOrder.DESC));
			//高亮显示字段
			//param.setHighlightField(new String[]{"spotName"/*,"description","brief","tags","location","ticketInfo"*/,"city"});
			Map<String, Object> shouldStr = new HashMap<>();
			shouldStr.put("spotName", filed);
			param.getBoots().put("spotName", 5.0F);
			shouldStr.put("description", filed);
			param.getBoots().put("description", 4.0F);
			shouldStr.put("brief", filed);
			shouldStr.put("tags", filed);
			shouldStr.put("location", filed);
			param.getBoots().put("location", 3.0F);
			shouldStr.put("ticketInfo", filed);
			shouldStr.put("city", filed);
			param.setShouldStr(shouldStr);
			
			Map<String, Object> mustStr = new HashMap<>();
			mustStr.put("isHide", 1);
			param.setMustStr(mustStr );
			param.setMatchPhrase(false);
			list = ElasticsearchUtils.searchDataPage(param).getRecordList();
		}catch (Exception e){
			MongoQueryCondition conditionOr = new MongoQueryCondition(LinkKey.or);
			MongoQueryCondition conditionAnd = new MongoQueryCondition(LinkKey.and);
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "spotName",filed));
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "tags",filed));
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "remarks",filed));
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "city",filed));
			
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "isHide",IS_TRUE));
			
			//复合条件查询
			MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and,conditionOr,conditionAnd);

			list = MongoService.findByPage(HXSystemConfig.MONGO_Collection_NAME_hwt,HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, null, condition, new Sort("spotId", Sort.ASC), 1, 10);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String, Object>> province_city_search(String province, String areaCodes, Integer pageIndex, Integer pageSize) {
		List<Map<String, Object>> list = new ArrayList<>();
		if(ObjectHelper.isEmpty(areaCodes)){
			
		}
		String[] split = areaCodes.split(",");
		for (int i = 0; i < split.length; i++) {
			Map<String, Object> map = new HashMap<>();
			List<Map<String, Object>> scenicSpots;
			try {
				SearchRequestParam param = new SearchRequestParam();
				param.setCurrentPage(pageIndex);
				param.setPageSize(pageSize);
				param.setIndexes(new String[]{HXSystemConfig.ES_Collection_NAME_hwt});
				param.setTypes(new String[]{HXSystemConfig.ES_Collection_NAME_ESQuery});
				
				param.setSortField((Map<String, SortOrder>) new HashMap<String, SortOrder>().put("create_time",SortOrder.DESC));
				
				Map<String, Object> mustStr = new HashMap<>();
				mustStr.put("is_hide", 0);
				mustStr.put("area_code", split[i]);
				mustStr.put("type", 1);
				param.setMustStr(mustStr);
				param.setMatchPhrase(false);
				scenicSpots = ElasticsearchUtils.searchDataPage(param).getRecordList();
			}catch (Exception e){
				
				//复合条件查询
				MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
				condition.add(new MongoQueryValue(MongoOperator.eq, "is_hide", 0));
				condition.add(new MongoQueryValue(MongoOperator.eq, "area_code",split[i]));
				condition.add(new MongoQueryValue(MongoOperator.eq, "type",1));
				

				scenicSpots = MongoService.findByPage(HXSystemConfig.MONGO_Collection_NAME_hwt,HXSystemConfig.MONGO_Collection_NAME_ESQuery, null, condition, new Sort("spotId", Sort.ASC), 1, 10);
			}
			if (!ObjectHelper.isEmpty(scenicSpots)){
				map.put("area_code", split[i]);
				map.put("list", scenicSpots);
				list.add(map);
			}
		
		}
		return list;
	}


	//	//elasticsearch查询
//	private List<Map<String, Object>> aas(String str, String city, Integer pageIndex, Integer pageSize){
//		EsPage searchDataPage = ElasticsearchUtils.searchDataPage("hwt", "scenic_spot", pageIndex, pageSize, 0l,new Date().getTime(), null, null, false, "city,spotName,tags", "city="+city+",spotName="+str+",tags="+str);
//		return searchDataPage.getRecordList();
//	}

}
