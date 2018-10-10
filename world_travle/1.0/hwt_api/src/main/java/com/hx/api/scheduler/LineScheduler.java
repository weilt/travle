package com.hx.api.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hx.core.mongo.service.MongoService;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;

@Component
@EnableScheduling
public class LineScheduler {
	
	/**
	 * 删除线路的接单日期
	 * @throws ParseException 
	 */
	@Scheduled(cron = " 0 0/5 * * * ?")
	//@Scheduled(cron = "0 0 4 * * ? ")
	public void delete_work_time() throws ParseException{
		Long geToday = geToday();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String,Object>> findAll = MongoService.findAll(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, null, null);
		if (!ObjectHelper.isEmpty(findAll)){
			for (Map<String, Object> map : findAll) {
				Long line_id = Long.parseLong(map.get("line_id").toString());
				if (!ObjectHelper.isEmpty(map.get("line_price"))){
					Map<String, Object> line_price = JsonUtils.json2Map(map.get("line_price").toString());
					
					if (!ObjectHelper.isEmpty(line_price.keySet())){
						List<String> list = new ArrayList<>();
						Set<String> keySet = line_price.keySet();
						for (String str : keySet) {
							Date parse = simpleDateFormat.parse(str);
							if (parse.getTime()<geToday){
								list.add(str);
							}
						}
						if (!ObjectHelper.isEmpty(list)){
							for (String string : list) {
								line_price.remove(string);
							}
						}
						
						//修改
						Map<String, Object> updateMap = new HashMap<>();
				    	updateMap.put("line_price", JsonUtils.Bean2Json(line_price));
						Map<String, Object> filterMap = new HashMap<>();
						filterMap.put("line_id", line_id);
						MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, filterMap , updateMap );
						
						//es修改
						Map<String, Object> updateMap1 = new HashMap<>();
				    	updateMap.put("price", JsonUtils.Bean2Json(line_price));
						Map<String, Object> filterMap1 = new HashMap<>();
						filterMap.put("name_id", line_id);
						filterMap.put("type", 3);
						MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.ES_Collection_NAME_ESQuery, filterMap1 , updateMap1 );
					}
					
				}
				
				
			}
		}
	}
	
	/**
	 * 获取今日凌晨的时间戳
	 * @return
	 */
	private Long geToday() {
		Long currentTimestamps=System.currentTimeMillis();
        Long oneDayTimestamps= Long.valueOf(60*60*24*1000);
        return currentTimestamps-(currentTimestamps+60*60*8*1000)%oneDayTimestamps;
	}
}
