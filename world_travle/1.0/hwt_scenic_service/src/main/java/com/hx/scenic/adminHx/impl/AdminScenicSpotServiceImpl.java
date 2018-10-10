package com.hx.scenic.adminHx.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.hwt.domain.entity.es.ESQuery;
import com.hwt.domain.entity.mg.scenic.ScenicSpot;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.mongo.value.MongoQueryProjections;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.Sort;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.utils.ZZLocationUtils;
import com.hx.scenic.adminHx.AdminScenicSpotService;

@Service
public class AdminScenicSpotServiceImpl implements AdminScenicSpotService{

	@Override
	public PageResult<Map<String,Object>> queryByMap(Map<String, Object> map) {
		
		
		Integer pageSize =  (Integer) map.get("pageSize");
		
		Integer currentPage =  (Integer) map.get("currentPage");
		
		String orderBy = (String) map.get("orderBy");
		
		String filed = (String) map.get("filed");
		String city = (String) map.get("city");
		
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
		
		List<MongoQueryCondition> list = new ArrayList<>();
			if (StringUtils.isNotBlank(filed)){
				MongoQueryCondition condition1 = new MongoQueryCondition(LinkKey.or);
				condition1.add(new MongoQueryValue(MongoOperator.regex, "spotName", filed));
				condition1.add(new MongoQueryValue(MongoOperator.regex, "description", filed));
				condition1.add(new MongoQueryValue(MongoOperator.regex, "brief", filed));
				condition1.add(new MongoQueryValue(MongoOperator.regex, "subtitle", filed));
				list.add(condition1);
			}
			
		
		
		
		if (StringUtils.isNoneBlank(city)){
			MongoQueryCondition condition2 = new MongoQueryCondition(LinkKey.and);
			
			condition2.add(new MongoQueryValue(MongoOperator.eq, "city", city));
			list.add(condition2);
		}
		if(list!=null&&list.size()>0){
			MongoQueryCondition[] conditions = new MongoQueryCondition[list.size()];
			for (int i = 0; i < conditions.length; i++) {
				conditions[i] = list.get(i);
			}
			condition.setCondtion(conditions);
		}
		
		
		
		//查询总条数
		long count = MongoService.findPageCount(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, condition );
		
		String[] split = orderBy.split(" ");
		
		//排序
		Sort sort = null;
		if (split[1].equals("asc")){
			sort = new Sort(split[0],Sort.ASC);
		}else{
			sort = new Sort(split[0],Sort.DESC);
		}
		
		//不显示字段 
		MongoQueryProjections projection = new MongoQueryProjections();
		projection.put("description", 0);
		projection.put("brief", 0);
		projection.put("subtitle", 0);
		projection.put("geoPoint", 0);
		projection.put("tags", 0);
		projection.put("rating", 0);
		projection.put("imageUrls", 0);
		projection.put("rank", 0);
		projection.put("location", 0);
		projection.put("ticketInfo", 0);
		projection.put("dataUrl", 0);
		projection.put("dataSources", 0);
		
		List<Map<String,Object>> findByPage = MongoService.findByPage(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, projection , condition, sort , currentPage, pageSize);
		PageResult<Map<String,Object>> pageResult = new PageResult<Map<String,Object>>();
		
		pageResult.setCount(count);
		pageResult.setDataList(findByPage);
		return pageResult;
	}

	@Override
	public boolean deleteById(Long spotId, Integer type) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("spotId", spotId);
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("isHide", type);
		boolean updateOne = MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, filterMap, updateMap);
		
		Map<String, Object> filterMap1 = new HashMap<>();
		filterMap1.put("name_id", spotId);
		filterMap1.put("type", 1);
		Map<String, Object> updateMap1 = new HashMap<String, Object>();
		updateMap1.put("is_hide", type);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap1, updateMap1);
		return updateOne;
	}

	@Override
	public Map<String, Object> queryScenicInfoBySpotId(Long spotId) {
		MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "spotId", spotId);
		Map<String, Object> map = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, queryValue , null);
		return map;
	}

	@Override
	public void update(ScenicSpot scenicSpot) {
		String geoPoint = scenicSpot.getGeoPoint();
		if (StringUtils.isNoneBlank(geoPoint)){
			String[] split = geoPoint.split(",");
			Map<String, String> map = ZZLocationUtils.get_province_city_district_by_gaode_log_lat(split[0].replace("{", "").split("=")[1], split[1].replace("}", "").split("=")[1]);
			scenicSpot.setCountry(map.get("country"));
			scenicSpot.setArea_code(map.get("area_code"));
			scenicSpot.setCity(map.get("city"));
			scenicSpot.setCity_code(map.get("city_code"));
			scenicSpot.setDistrict(map.get("district"));
			scenicSpot.setProvince(map.get("province"));
		
		}
		MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "spotId", scenicSpot.getSpotId());
		Map<String, Object> map = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, queryValue , null);
		//高德获取到城市说明是台湾省 或者海域 ，所以保留数据库中的
		if (ObjectHelper.isEmpty(scenicSpot.getCity())||"[]".equals(scenicSpot.getCity())){
			
			scenicSpot.setCity(map.get("city") + "");
        }
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("spotId", scenicSpot.getSpotId());
		Map<String, Object> updateMap = ObjectHelper.obj2Map(scenicSpot);
		map.putAll(updateMap);
		System.out.println(updateMap);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, filterMap, map);
		esQueryScenicSpotUpdate(map);
	}

	@Override
	public void insert(ScenicSpot scenicSpot) throws Exception {
		String geoPoint = scenicSpot.getGeoPoint();
		if (StringUtils.isNoneBlank(geoPoint)){
			String[] split = geoPoint.split(",");
			Map<String, String> map = ZZLocationUtils.get_province_city_district_by_gaode_log_lat(split[0].replace("{", "").split("=")[1], split[1].replace("}", "").split("=")[1]);
			scenicSpot.setCountry(ObjectHelper.isEmpty(map.get("country"))?scenicSpot.getCountry():map.get("country"));
			scenicSpot.setArea_code(ObjectHelper.isEmpty(map.get("area_code"))?scenicSpot.getArea_code():map.get("area_code"));
			scenicSpot.setCity(ObjectHelper.isEmpty(map.get("city"))?scenicSpot.getCity():map.get("city"));
			scenicSpot.setCity_code(ObjectHelper.isEmpty(map.get("city_code"))?scenicSpot.getCity_code():map.get("city_code"));
			scenicSpot.setDistrict(ObjectHelper.isEmpty(map.get("district"))?scenicSpot.getDistrict():map.get("district"));
			scenicSpot.setProvince(ObjectHelper.isEmpty(map.get("province"))?scenicSpot.getProvince():map.get("province"));
		}
		scenicSpot.setDataSources("淮信");
		scenicSpot.setVisitsNum(0l);
		long currentTimeMillis = System.currentTimeMillis();
		scenicSpot.setDataTime(currentTimeMillis);
		scenicSpot.setCreate_time(currentTimeMillis);
		//MongoService.insertIncId(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, scenicSpot, "spotId");
		//MongoService.f
		MongoService.insertIncId(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, scenicSpot,"spotId");
		esQueryScenicSpotInsert(scenicSpot);
	}

	@Override
	public boolean recommendById(Long spotId, Integer type) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("spotId", spotId);
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("isRecommend", type);
		boolean updateOne = MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, filterMap, updateMap);
		
		return updateOne;
		
	}

	@Override
	public boolean isHotById(Long spotId, Integer type) {
		Map<String, Object> filterMap = new HashMap<String, Object>();
		filterMap.put("spotId", spotId);
		Map<String, Object> updateMap = new HashMap<String, Object>();
		updateMap.put("isHot", type);
		boolean updateOne = MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ScenicSpot, filterMap, updateMap);
		
		Map<String, Object> filterMap1 = new HashMap<>();
		filterMap1.put("name_id", spotId);
		filterMap1.put("type", 1);
		Map<String, Object> updateMap1 = new HashMap<String, Object>();
		updateMap1.put("is_hot", type);
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap1, updateMap1);
		return updateOne;
		
	}
	
	/**
	 * 景点添加 es数据导入
	 * @throws IllegalAccessException 
	 * @throws InvocationTargetException 
	 * @throws Exception 
	 */
	public static void esQueryScenicSpotInsert(ScenicSpot scenicSpot) throws Exception {
		ESQuery esQuery = new ESQuery();
		esQuery.setArea_code(scenicSpot.getArea_code());
		esQuery.setName_id(scenicSpot.getSpotId());
		esQuery.setName(scenicSpot.getSpotName());
		esQuery.setType(1);
		String image = null;
		if (scenicSpot.getImageUrls()!=null){
			String imageUrls = scenicSpot.getImageUrls().toString();
			String replace = imageUrls.replace("[", "");
			String replace2 = replace.replace("]", "");
			image = replace2.split(",")[0];
		}
		esQuery.setImage(image);
		esQuery.setIs_hot(0);
		esQuery.setSales(0);
		esQuery.setPrice(null);
		esQuery.setComment_count(0);
		esQuery.setComment_score(0F);
		esQuery.setLocation(scenicSpot.getLocation());
		esQuery.setTar(scenicSpot.getTags());
		esQuery.setGeo_point(scenicSpot.getGeoPoint());
		esQuery.setCreate_time(scenicSpot.getCreate_time());
		esQuery.setIs_hide(scenicSpot.getIsHide());
		MongoService.insertIncId(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, esQuery, "id");
	}
	
	/**
	 * 景点修改
	 */
	public static void esQueryScenicSpotUpdate(Map<String, Object> map){
		ESQuery esQuery = new ESQuery();
		esQuery.setArea_code(map.get("area_code") == null?null:map.get("area_code").toString());
		esQuery.setName_id(Long.parseLong(map.get("spotId").toString()));
		esQuery.setName(map.get("spotName") == null?null:map.get("spotName").toString());
		esQuery.setType(1);
		String image = null;
		if (map.get("imageUrls")!=null){
			String imageUrls = map.get("imageUrls").toString();
			String replace = imageUrls.replace("[", "");
			String replace2 = replace.replace("]", "");
			image = replace2.split(",")[0];
		}
		esQuery.setImage(image);
		esQuery.setIs_hot(map.get("isHot") == null?0:Integer.parseInt(map.get("isHot").toString()));
		esQuery.setSales(0);
		esQuery.setPrice(null);
		esQuery.setComment_count(map.get("comment_count") == null?1:Integer.parseInt(map.get("comment_count").toString()));
		esQuery.setComment_score(map.get("rating") == null?null:Float.parseFloat(map.get("rating").toString()));
		esQuery.setLocation(map.get("location") == null?null:map.get("location").toString());
		esQuery.setTar(map.get("tags") == null?null:map.get("tags").toString());
		esQuery.setGeo_point(map.get("geoPoint") == null?null:map.get("geoPoint").toString());
		esQuery.setCreate_time(map.get("create_time") == null?null:Long.parseLong(map.get("create_time").toString()));
		esQuery.setIs_hide(map.get("isHide") == null?1:Integer.parseInt(map.get("isHide").toString()));
		
		Map<String, Object> filterMap = new HashMap<>();
		filterMap.put("name_id", esQuery.getName_id());
		filterMap.put("type", esQuery.getType());
		Map<String, Object> obj2Map = ObjectHelper.obj2Map(esQuery);
		obj2Map.remove("id");
		MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap, obj2Map);
	}
}
