package com.hx.user.opinion.service.impl;

import org.springframework.stereotype.Service;

import com.hwt.domain.entity.mg.opinion.HxOpinion;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.user.opinion.service.OpinionService;

@Service
public class OpinionServiceImpl implements OpinionService{

	@Override
	public void insert(HxOpinion hxOpinion) throws Exception {
		
		hxOpinion.setIs_handle(0);
		hxOpinion.setCreate_time(System.currentTimeMillis());
		MongoService.insertIncId(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_Opinion, hxOpinion, "opinion_id");
	}

}
