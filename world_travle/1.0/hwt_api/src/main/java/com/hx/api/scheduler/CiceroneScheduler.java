package com.hx.api.scheduler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.hwt.domain.entity.cicerone.HxCiceroneHide;
import com.hwt.domain.mapper.cicerone.HxCiceroneHideMapper;
import com.hwt.domain.mapper.cicerone.HxCiceroneInfoMapper;
import com.hwt.domain.mapper.order.HwOrderMapper;
import com.hwt.domain.mapper.user.HxUserMapper;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ObjectHelper;
import com.hx.core.wyim.notice.SentNotice;

@Component
@EnableScheduling
public class CiceroneScheduler {
	
	@Autowired
	private HxCiceroneHideMapper hxCiceroneHideMapper;
	
	@Autowired
	private HxCiceroneInfoMapper hxCiceroneInfoMapper;
	
	@Autowired
	private SentNotice sentNotice;
	
	@Autowired
	private HxUserMapper hxUserMapper;
	
	/**
	 * 处理禁用的导游  5分钟执行一次
	 * @throws Exception 
	 */
	@Transactional
	@Scheduled(cron = " 0 0/5 * * * ?")
    public void order_complete() throws Exception {
		List<HxCiceroneHide> list = hxCiceroneHideMapper.queryAll();
		if (!ObjectHelper.isEmpty(list)) {
			for (HxCiceroneHide hxCiceroneHide : list) {
				if (hxCiceroneHide.getEnd_time()!=null){
					if (hxCiceroneHide.getEnd_time()<System.currentTimeMillis()){
						hxCiceroneHideMapper.deleteByPrimaryKey(hxCiceroneHide.getUser_id());
						hxCiceroneInfoMapper.is_not_hide(hxCiceroneHide.getUser_id());
						sentNotice.sendSystem(getUserImId(hxCiceroneHide.getUser_id()), "取消导游不能被下单", "取消导游不能被下单，禁用时间已到。", null, 1, null, null);
					}
				}
			}
		}
    }
	
	/**
	 * 删除导游的接单日期
	 * @throws ParseException 
	 */
	@Scheduled(cron = " 0 0/5 * * * ?")
	//@Scheduled(cron = "0 0 3 * * ? ")
	public void delete_work_time() throws ParseException{
		Long geToday = geToday();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Map<String,Object>> findAll = MongoService.findAll(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, null, null);
		if (!ObjectHelper.isEmpty(findAll)){
			for (Map<String, Object> map : findAll) {
				Long user_id = Long.parseLong(map.get("user_id").toString());
				if (!ObjectHelper.isEmpty(map.get("work_time"))){
					Map<String, Object> work_time = JsonUtils.json2Map(map.get("work_time").toString());
					if (!ObjectHelper.isEmpty(work_time.keySet())){
						List<String> list = new ArrayList<>();
						for (String str : work_time.keySet()) {
							Date parse = simpleDateFormat.parse(str);
							if (parse.getTime()<geToday){
								list.add(str);
							}
						}
						
						if (!ObjectHelper.isEmpty(list)){
							for (String string : list) {
								work_time.remove(string);
							}
						}
						
						//修改
						Map<String, Object> updateMap = new HashMap<>();
				    	updateMap.put("work_time", JsonUtils.Bean2Json(work_time));
						Map<String, Object> filterMap = new HashMap<>();
						filterMap.put("user_id", user_id);
						MongoService.updateOne(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_CiceroneInfo, filterMap , updateMap );
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
	
	
	/**
	 * 通过用户id查询im_id
	 * @param user_id
	 * @return
	 */
	private String getUserImId(Long user_id) {
		String im_id = hxUserMapper.queryImIdByUserId(user_id);
		return im_id;
	}
}
