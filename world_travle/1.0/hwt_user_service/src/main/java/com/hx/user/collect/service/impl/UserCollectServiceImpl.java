package com.hx.user.collect.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwt.domain.entity.es.ESQuery;
import com.hwt.domain.entity.user.collect.HwUserCollect;
import com.hwt.domain.mapper.user.collect.HwUserCollectMapper;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.mongo.value.MongoQueryProjections;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.ObjectHelper;
import com.hx.user.collect.service.UserCollectService;

@Service
public class UserCollectServiceImpl implements UserCollectService {

	@Autowired
	private HwUserCollectMapper hwUserCollectMapper;

	@Override
	public Map<String, Object>  query(Long user_id, int startNum, Integer pageSize) {
		Map<String, Object>  map = new HashMap<String, Object>();
		List<HwUserCollect> hwUserCollects = hwUserCollectMapper.query(user_id,startNum,pageSize);
		if (ObjectHelper.isEmpty(hwUserCollects)){
			return null;
		}
		map.put("hwUserCollects", hwUserCollects);
		
		List<Map<String, Object>> list = null;
		 
		//查询收藏夹的数据
		 MongoQueryCondition[] conditions = new MongoQueryCondition[hwUserCollects.size()];
		for (int i = 0; i < hwUserCollects.size(); i++) {
			HwUserCollect hwUserCollect = hwUserCollects.get(i);
			MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
			condition.add(new MongoQueryValue(MongoOperator.eq, "name_id", hwUserCollect.getName_id()));
			condition.add(new MongoQueryValue(MongoOperator.eq, "type", hwUserCollect.getType()));
			conditions[i] = condition;
		}
		
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.or,conditions);
		list = MongoService.findCustom(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, condition , null, null);
		map.put("dataList", list);
		return map;
	}

	@Override
	public HwUserCollect insert(Long user_id, Long name_id, Integer type) {
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
		condition.add(new MongoQueryValue(MongoOperator.eq, "name_id", name_id));
		condition.add(new MongoQueryValue(MongoOperator.eq, "type", type));
		Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, condition, null);
		if (ObjectHelper.isEmpty(findOne)){
			throw new RuntimeException("该数据不存在");
		}
		HwUserCollect collect = hwUserCollectMapper.queryIsCollect(user_id, name_id, type);
		if (!ObjectHelper.isEmpty(collect)){
			throw new RuntimeException("已经收藏了！");
		}
		HwUserCollect hwUserCollect = new HwUserCollect();
		hwUserCollect.setCreate_time(System.currentTimeMillis());
		hwUserCollect.setName_id(name_id);
		hwUserCollect.setType(type);
		hwUserCollect.setUser_id(user_id);
		hwUserCollectMapper.insertBackId(hwUserCollect);
		
		return hwUserCollect;
	}

	@Override
	public void delete(Long user_id, String collec_ids) {
		if (ObjectHelper.isEmpty(collec_ids)){
			return;
		}else{
			String[] split = collec_ids.split(",");
			Long[] collec_id = new Long[split.length];
			for (int i = 0; i < split.length; i++) {
				collec_id[i] = Long.parseLong(split[i]);
			}
			hwUserCollectMapper.deleteIds(user_id,collec_id);
		}
		
	}
}
