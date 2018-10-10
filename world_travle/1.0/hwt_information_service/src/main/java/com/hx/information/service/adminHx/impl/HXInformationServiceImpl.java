package com.hx.information.service.adminHx.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hwt.domain.entity.mg.information.HwInformation;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryProjections;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.Sort;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.ObjectHelper;
import com.hx.information.service.adminHx.HXInformationService;

@Service
public class HXInformationServiceImpl implements HXInformationService {

	@Override
	public PageResult<Map<String, Object>> query(Map<String, Object> map) {
		Integer pageSize =  (Integer) map.get("pageSize");
		
		Integer currentPage =  (Integer) map.get("currentPage");
		
		String orderBy = (String) map.get("orderBy");
		
		String titel = (String) map.get("titel");
		
		
		//查询条件封装
		MongoQueryCondition condition = null;
		if (!ObjectHelper.isEmpty(titel)){
			condition = new MongoQueryCondition(LinkKey.and);
			condition.add(new MongoQueryValue(MongoOperator.regex, "titel", titel));
		}
		
		
		//查询总条数
		Long count = MongoService.findPageCount(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_Information, condition);
		
		
		
		//排序
		String[] split = orderBy.split(" ");
		Sort sort = null;
		if (split[1].equals("asc")){
			sort = new Sort(split[0],Sort.ASC);
		}else{
			sort = new Sort(split[0],Sort.DESC);
		}
		//不显示字段 
		MongoQueryProjections projection = new MongoQueryProjections();
		projection.put("author", 0);
		projection.put("information_type", 0);
		projection.put("content", 0);
		projection.put("source", 0);
		
		List<Map<String,Object>> findByPage = MongoService.findByPage(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_Information, projection , condition, sort , currentPage, pageSize);
		PageResult<Map<String,Object>> pageResult = new PageResult<Map<String,Object>>();
		
		pageResult.setCount(count);
		pageResult.setDataList(findByPage);
		return pageResult;
	}

	@Override
	public void insert(HwInformation hwInformation) throws Exception {
		MongoService.insertIncId(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_Information, hwInformation, "information_id");
	}

	@Override
	public Map<String, Object> queryInfo(Long information_id) {
		MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "information_id", information_id);
		Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_Information, queryValue , null);
		return findOne;
	}

}
