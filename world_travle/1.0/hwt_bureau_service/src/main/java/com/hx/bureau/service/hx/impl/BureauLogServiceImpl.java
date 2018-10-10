package com.hx.bureau.service.hx.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.hx.bureau.service.hx.BureauLogService;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.Sort;
import com.hx.core.page.asyn.PageResult;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.ObjectHelper;

@Service
public class BureauLogServiceImpl implements BureauLogService {

	@Override
	public PageResult<Map<String, Object>> query(Map<String, Object> map) {
		
		Integer pageSize =  (Integer) map.get("pageSize");
		
		Integer currentPage =  (Integer) map.get("currentPage");
		
		Long bureau_id =  (Long) map.get("bureau_id");
		
		String orderBy = (String) map.get("orderBy");
		
		String line_name = (String) map.get("line_name");
		
		//排序
		String[] split = orderBy.split(" ");
		Sort sort = null;
		if (split[1].equals("asc")){
			sort = new Sort(split[0],Sort.ASC);
		}else{
			sort = new Sort(split[0],Sort.DESC);
		}
		

		
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
		
		condition.add(new MongoQueryValue(MongoOperator.eq, "bureau_id", bureau_id));
		
		if (!ObjectHelper.isEmpty(line_name)){
			condition.add(new MongoQueryValue(MongoOperator.regex, "line_name", line_name));
		}
		
		//查询数据
		List<Map<String,Object>> list = MongoService.findByPage(HXSystemConfig.MONGO_DB_NAME_Logs, HXSystemConfig.MONGO_Collection_NAME_Bureau_Logs, null, condition, sort, currentPage, pageSize);
		
		//总条数
		long count = MongoService.findPageCount(HXSystemConfig.MONGO_DB_NAME_Logs, HXSystemConfig.MONGO_Collection_NAME_Bureau_Logs, condition );
		
		PageResult<Map<String,Object>> pageResult = new PageResult<Map<String, Object>>();
		pageResult.setCount(count);
		pageResult.setDataList(list);
		return pageResult;
	}

}
