package com.hx.order.service.hx.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.entity.order.HwOrderComment;
import com.hwt.domain.entity.order.vo.HwOrderCommentListVo;
import com.hwt.domain.entity.order.vo.HwOrderCommentVo;
import com.hwt.domain.mapper.cicerone.HxCiceroneInfoMapper;
import com.hwt.domain.mapper.order.HwOrderCommentMapper;
import com.hwt.domain.mapper.order.HwOrderMapper;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.ObjectHelper;
import com.hx.order.service.hx.OrderCommentService;

@Service
public class OrderCommentServiceImpl implements OrderCommentService{
	
	@Autowired
	private HwOrderMapper hwOrderMapper;
	
	@Autowired
	private HwOrderCommentMapper hwOrderCommentMapper;
	
	@Autowired
	private HxCiceroneInfoMapper hxCiceroneInfoMapper;

	@Override
	@Transactional
	public HwOrderComment insert(Long user_id, Long order_id, Long parent_comment_id, Long parent_user_id,
			String comment_dec, String image, Integer match_score, Integer trip_score, Integer service_score,
			Float score) {
		
		HwOrder hwOrder = hwOrderMapper.selectByPrimaryKey(order_id);
		if (ObjectHelper.isEmpty(hwOrder)){
			throw new RuntimeException("该订单不存在！");
		}
		if (hwOrder.getStatus()!=3) {
			throw new RuntimeException("该订单目前还不能评论！");
		}
		
		if (hwOrder.getBuyer_rate()==0){
			if (hwOrder.getUser_id()!=user_id){
				throw new RuntimeException("非自己的订单不能操作！");
			}else{
				hwOrderMapper.comment(hwOrder.getOrder_id());
			}
		}
		
		HwOrderComment hwOrderComment = new HwOrderComment();
		hwOrderComment.setComment_dec(comment_dec);
		hwOrderComment.setCreate_time(System.currentTimeMillis());
		hwOrderComment.setImage(image);
		if (ObjectHelper.isEmpty(image)){
			hwOrderComment.setIs_image(0);	
		}else {
			hwOrderComment.setIs_image(1);	
		}
		
		if (hwOrder.getCicerone_id()!=0){
			//说明是导游的
			if (ObjectHelper.isEmpty(score)){
				throw new RuntimeException("没有评分！");
			}
			hwOrderComment.setScore(score);
			hwOrderComment.setName_id(hwOrder.getCicerone_id());
			hwOrderComment.setName_type(2);
			//导游最加评论次数
			hxCiceroneInfoMapper.addScore(hwOrder.getCicerone_id(),score);
			//修改ESmong
	        MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
	        condition.add(new MongoQueryValue(MongoOperator.eq, "name_id", hwOrder.getCicerone_id()));
	        condition.add(new MongoQueryValue(MongoOperator.eq, "type", 2));
	        Map<String, Object> findOne2 = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, condition, null);
	    	
	        Map<String, Object> updateMap = new HashMap<>();
	    	updateMap.put("comment_count", (findOne2.get("comment_count")==null?0:(int)findOne2.get("comment_count"))+1);
	    	updateMap.put("comment_score", (findOne2.get("comment_score")==null?0F:(float)findOne2.get("comment_score"))+score);
	    	
			Map<String, Object> filterMap = new HashMap<>();
			filterMap.put("id", findOne2.get("id"));
			MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap , updateMap );
		}else{
			//说明是线路的
			if (ObjectHelper.isEmpty(service_score)){
				throw new RuntimeException("服务没有评分！");
			}
			hwOrderComment.setService_score(service_score);
			if (ObjectHelper.isEmpty(trip_score)){
				throw new RuntimeException("行程安排没有评分！");
			}
			hwOrderComment.setTrip_score(trip_score);
			if (ObjectHelper.isEmpty(match_score)){
				throw new RuntimeException("相符度没有评分！");
			}
			hwOrderComment.setMatch_score(match_score);
			score =  ((float)(service_score+trip_score+match_score))/3;
			
			hwOrderComment.setName_id(hwOrder.getLine_id());
			hwOrderComment.setName_type(3);
			hwOrderComment.setScore(score);
			
			//线路最加评论次数
			MongoQueryValue queryValue = new MongoQueryValue(MongoOperator.eq, "line_id", hwOrder.getLine_id());
		
			Map<String, Object> findOne = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, queryValue, null );
			findOne.put("total_comment_num", (findOne.get("total_comment_num")==null?0:(int)findOne.get("total_comment_num"))+1);
			findOne.put("total_service_score", (findOne.get("total_service_score")==null?0:(int)findOne.get("total_service_score"))+service_score);
			findOne.put("total_trip_score", (findOne.get("total_trip_score")==null?0:(int)findOne.get("total_trip_score"))+trip_score);
			findOne.put("total_match_score", (findOne.get("total_match_score")==null?0:(int)findOne.get("total_match_score"))+match_score);
			findOne.put("total_score", (findOne.get("total_score")==null?0D:(Double)findOne.get("total_score"))+score);
			
			Map<String, Object> filterMap = new HashMap<>();
			filterMap.put("line_id", hwOrder.getLine_id());
			MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, filterMap, findOne);
			
			//修改ESmong
	        MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
	        condition.add(new MongoQueryValue(MongoOperator.eq, "name_id", hwOrder.getLine_id()));
	        condition.add(new MongoQueryValue(MongoOperator.eq, "type", 3));
	        Map<String, Object> findOne2 = MongoService.findOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, condition, null);
	    	
	        Map<String, Object> updateMap = new HashMap<>();
	    	updateMap.put("comment_count", (findOne2.get("comment_count")==null?0:(int)findOne2.get("comment_count"))+1);
	    	updateMap.put("comment_score", (findOne2.get("comment_score")==null?0F:(float)findOne2.get("comment_score"))+score);
	    	
			Map<String, Object> filterMap1 = new HashMap<>();
			filterMap.put("id", findOne2.get("id"));
			MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_ESQuery, filterMap1 , updateMap );
		}
		hwOrderComment.setOrder_id(hwOrder.getOrder_id());
		hwOrderComment.setParent_comment_id(parent_comment_id);
		hwOrderComment.setParent_user_id(parent_user_id);
		hwOrderComment.setUser_id(user_id);
		hwOrderCommentMapper.insertBackId(hwOrderComment);
		
		
		return hwOrderComment;
	}

	@Override
	public HwOrderCommentListVo query(Map<String, Object> map) {
		Long name_id = (Long) map.get("name_id");
		Integer name_type = (Integer) map.get("name_type");
		HwOrderCommentListVo hwOrderCommentListVo = new HwOrderCommentListVo();
		List<HwOrderCommentVo> list = hwOrderCommentMapper.query_comment(map);
		
		Long bad_comment = hwOrderCommentMapper.query_bad_comment(name_id,name_type);
		hwOrderCommentListVo.setBad_comment(bad_comment);
		
		Long good_comment = hwOrderCommentMapper.query_good_comment(name_id,name_type);;
		hwOrderCommentListVo.setGood_comment(good_comment);
		
		Long have_image = hwOrderCommentMapper.query_have_image(name_id,name_type);;
		hwOrderCommentListVo.setHave_image(have_image);
		
		Long secondary_comment = hwOrderCommentMapper.query_secondary_comment(name_id,name_type);;
		hwOrderCommentListVo.setSecondary_comment(secondary_comment);
		
		hwOrderCommentListVo.setHwOrderCommentVos(list);
		return hwOrderCommentListVo;
	}
	
	
}
