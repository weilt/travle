package com.hx.scenic.scenicVo.imp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.hx.core.es.entity.SearchRequestParam;
import com.hx.core.es.utils.ElasticsearchUtils;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.Sort;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.ObjectHelper;
import com.hx.scenic.scenicVo.IndexService;

@Service
public class IndexServiceImpl implements IndexService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(IndexServiceImpl.class);
	
	@Override
	public Map<String, Object> searchIndex(String area_code) {
		
		SearchRequestParam param = new SearchRequestParam();
		param.setCurrentPage(1);
		param.setPageSize(10);
		param.setIndexes(new String[]{HXSystemConfig.ES_Collection_NAME_hwt});
		param.setTypes(new String[]{HXSystemConfig.ES_Collection_NAME_ESQuery});
		
		param.setSortField((Map<String, SortOrder>) new HashMap<String, SortOrder>().put("is_hot",SortOrder.DESC));
		//高亮显示字段
		//param.setHighlightField(new String[]{"spotName","description","brief","tags","location","ticketInfo","city"});
		
		
		
		Map<String, Object> mustStr = new HashMap<>();
		mustStr.put("is_hide", 0);
		
		param.setMustStr(mustStr);
		param.setMatchPhrase(false);
	
		//查询导游
		mustStr.put("type", 2);
		
		mustStr.put("area_code", area_code!=null?area_code:"010");
		
		List<Map<String, Object>> cicerones = ElasticsearchUtils.searchDataPage(param).getRecordList();
		//查询线路
		mustStr.put("type", 3);
		mustStr.remove("area_code");
		List<Map<String, Object>> lines = ElasticsearchUtils.searchDataPage(param).getRecordList();
		
		Map<String, Object> map = new HashMap<>();
		map.put("cicerones", cicerones);
		map.put("lines", lines);
		return map;
	}

	@Override
	public List<Map<String, Object>> search(String searchKey, Integer type, Integer pageIndex, Integer pageSize) {
		List<Map<String,Object>> queryList = null;
		try {
			SearchRequestParam param = new SearchRequestParam();
			param.setCurrentPage(pageIndex);
			param.setPageSize(pageSize);
			param.setIndexes(new String[]{HXSystemConfig.ES_Collection_NAME_hwt});
			param.setTypes(new String[]{HXSystemConfig.ES_Collection_NAME_ESQuery});
			
			param.setSortField((Map<String, SortOrder>) new HashMap<String, SortOrder>().put("create_time",SortOrder.DESC));
			Map<String, Float> boots = new HashMap<>();
			boots.put("name", 6.0F);
			boots.put("location", 5.0F);
			boots.put("tar", 4.0F);
			param.setBoots(boots );
			//高亮显示字段
			//param.setHighlightField(new String[]{"spotName","description","brief","tags","location","ticketInfo","city"});
			Map<String, Object> shouldStr = new HashMap<>();
			shouldStr.put("name", searchKey);
			shouldStr.put("location", searchKey);
			shouldStr.put("tar", searchKey);
			shouldStr.put("area_code_name", searchKey);	
			param.setShouldStr(shouldStr);
			
			Map<String, Object> mustStr = new HashMap<>();
			if (type!=null && type>0){
				mustStr.put("type", type);
			}
			mustStr.put("is_hide", 0);
			param.setMustStr(mustStr);
			param.setMatchPhrase(false);
			queryList = ElasticsearchUtils.searchDataPage(param).getRecordList();
		} catch (Exception e) {
			LOGGER.error("ES查询出错：%m",e.getMessage());
			queryList =	queryData(searchKey,type, null, null, null, pageIndex, pageSize);
		}
		return queryList;
	}
	
	
	//MongoDB查询
	private List<Map<String,Object>> queryData (String str, Integer type, Boolean isHot, Boolean isRecommend, String city, Integer pageIndex, Integer pageSize) {
		MongoQueryCondition conditionOr = new MongoQueryCondition(LinkKey.or);
		MongoQueryCondition conditionAnd = new MongoQueryCondition(LinkKey.and);
		if (!StringUtils.isEmpty(str)){
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "name",str));
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "tar",str));
			conditionOr.add(new MongoQueryValue(MongoOperator.regex, "location",str));
		}
//	        conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "city",city));
		conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "is_hide",0));
		if (type!=null && type>0){
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "type",type));
		}
		if ( null != isHot && isHot)
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "is_hot",1));
		if ( null != isRecommend && isRecommend)
			conditionAnd.add(new MongoQueryValue(MongoOperator.eq, "is_recommend",1));
		//复合条件查询
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and,conditionOr,conditionAnd);

		List<Map<String, Object>> list = MongoService.findByPage(HXSystemConfig.MONGO_Collection_NAME_hwt,HXSystemConfig.MONGO_Collection_NAME_ESQuery, null, condition, new Sort("id", Sort.DESC), pageIndex, pageSize);
		return list;
	}

	@Override
	public List<Map<String, Object>> like(String like_field, Integer pageIndex, Integer pageSize) {
		List<Map<String,Object>> queryList = null;
		List<Map<String, Object>> recordList = null;
		try {
			SearchRequestParam param = new SearchRequestParam();
			param.setCurrentPage(pageIndex);
			param.setPageSize(pageSize);
			param.setIndexes(new String[]{HXSystemConfig.ES_Collection_NAME_hwt});
			param.setTypes(new String[]{HXSystemConfig.ES_Collection_NAME_ESQuery});
			
			param.setSortField((Map<String, SortOrder>) new HashMap<String, SortOrder>().put("create_time",SortOrder.DESC));
			//高亮显示字段
			//param.setHighlightField(new String[]{"spotName","description","brief","tags","location","ticketInfo","city"});
			Map<String, Object> shouldStr = new HashMap<>();
			shouldStr.put("name", like_field);
			shouldStr.put("location", like_field);
			shouldStr.put("tar", like_field);
			shouldStr.put("area_code_name", like_field);	
			param.setShouldStr(shouldStr);
			
			Map<String, Float> boots = new HashMap<>();
			boots.put("name", 6.0F);
			boots.put("location", 5.0F);
			boots.put("tar", 4.0F);
			param.setBoots(boots );
			Map<String, Object> mustStr = new HashMap<>();
			mustStr.put("is_hide", 0);
			param.setMustStr(mustStr);
			mustStr.put("type", 1);
			param.setMatchPhrase(false);
			queryList = ElasticsearchUtils.searchDataPage(param).getRecordList();
			mustStr.put("type", 3);
			recordList = ElasticsearchUtils.searchDataPage(param).getRecordList();
		} catch (Exception e) {
			LOGGER.error("ES查询出错：%m",e.getMessage());
			queryList =	queryData(like_field,1, null, null, null, pageIndex, pageSize);
			recordList = queryData(like_field,3, null, null, null, pageIndex, pageSize);
		}
		queryList.addAll(recordList);
		return queryList;
	}


}
