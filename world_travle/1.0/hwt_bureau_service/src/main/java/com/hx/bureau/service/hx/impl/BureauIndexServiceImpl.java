package com.hx.bureau.service.hx.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hwt.domain.entity.order.HwOrder;
import com.hwt.domain.mapper.order.HwOrderMapper;
import com.hx.bureau.service.hx.BureauIndexService;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.mongo.value.MongoOperator;
import com.hx.core.mongo.value.MongoQueryCondition;
import com.hx.core.mongo.value.MongoQueryCondition.LinkKey;
import com.hx.core.mongo.value.MongoQueryValue;
import com.hx.core.systemconfig.HXSystemConfig;
import com.hx.core.utils.ObjectHelper;

@Service
public class BureauIndexServiceImpl implements BureauIndexService{

	@Autowired
	private HwOrderMapper hwOrderMapper;
	
	@Override
	public Map<String, Object> query_index(Long bureau_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		//获取今日凌晨的时间戳
		Long time = geToday();
		//今日订单总数
		Long today_order = hwOrderMapper.query_today_order_by_bureau_id(bureau_id,time);
		map.put("today_order", today_order);
		//今日开始订单
		Long today_start_order = hwOrderMapper.query_today_start_order_by_bureau_id(bureau_id,time);
		map.put("today_start_order", today_start_order);
		//今日结束订单
		Long today_end_order = hwOrderMapper.query_today_end_order_by_bureau_id(bureau_id,(time-1));
		map.put("today_end_order", today_end_order);
		//今日退款订单
		Long today_refundable_order = hwOrderMapper.query_today_refundable_order_by_bureau_id(bureau_id,time);
		map.put("today_refundable_order", today_refundable_order);
		
		
		MongoQueryCondition condition = new MongoQueryCondition(LinkKey.and);
		condition.add(new MongoQueryValue(MongoOperator.eq, "bureau_id", bureau_id));
		//线路总数
		Long line_total = MongoService.findPageCount(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, condition );
		map.put("line_total", line_total);
		
		//下架线路总数
		condition.add(new MongoQueryValue(MongoOperator.eq, "is_hide", 1));
		Long line_hide_total = MongoService.findPageCount(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, condition );
		map.put("line_hide_total", line_hide_total);
		
		//今日新增
		condition.clear();
		condition.add(new MongoQueryValue(MongoOperator.eq, "bureau_id", bureau_id));
		condition.add(new MongoQueryValue(MongoOperator.gte, "create_time", time));
		Long line_today_total = MongoService.findPageCount(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, condition );
		map.put("line_today_total", line_today_total);
		
		//本月新增
		condition.clear();
		condition.add(new MongoQueryValue(MongoOperator.eq, "bureau_id", bureau_id));
		Long month = getmonth();
		condition.add(new MongoQueryValue(MongoOperator.gte, "create_time", month));
		Long line_tomonth_total = MongoService.findPageCount(HXSystemConfig.MONGO_Collection_NAME_hwt, HXSystemConfig.MONGO_Collection_NAME_HwTravelLine, condition );
		map.put("line_tomonth_total", line_tomonth_total);
		
		//本月订单总数
		
		//本周订单总数
		return map;
	}
	
	/**
	 * 获取本周的时间戳
	 * @return
	 */
	private Long getweek() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return cal.getTimeInMillis();

	}
	/**
	 * 获取本月时间戳
	 * @return
	 */
	private Long getmonth() {
		Calendar calendar = Calendar.getInstance();// 获取当前日期
		calendar.add(Calendar.MONTH, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
 
		return calendar.getTimeInMillis();

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

	@Override
	public Map<String, Object> orderStatistics(Long bureau_id,Integer choose_type) throws Exception {
		Map<String, Object> map = new HashMap<>();
		Long start = null;
		Long end =  System.currentTimeMillis();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String end_time = simpleDateFormat.format(new Date(end));
		map.put("end_time", end_time);
		if (choose_type==1){
			start = geToday();
		} else if (choose_type==2){
			start = getweek();
			
		} else {
			start = getmonth();
			
		}
		//查询数据
		List<HwOrder> list = hwOrderMapper.query_statistics_to_bureau(bureau_id,start,end);
		
		//数据处理
		orderHandle(list,choose_type,map,end);
		String start_time = simpleDateFormat.format(new Date(start));
		map.put("start_time", start_time);
		return map;
	}
	
	/**
	 * 数据封装
	 * @param list        订单集
	 * @param choose_type	   1-今日  2-本周  3-本月
	 * @param map        
	 * @param end        结束时间
	 */
	private void orderHandle(List<HwOrder> list, Integer choose_type, Map<String, Object> map, Long end) {
		String dec = "本日订单统计";
		//横坐标最大值
		Integer abscissa_max = 0;
		switch(choose_type){
		case 1:
		    //获取小时数
			abscissa_max = getHour(end);
			dec = "本日订单统计";
		    break;
		case 2:
		    //获取星期数
			abscissa_max = getWeekDay(end);
			dec = "本周订单统计";
		    break;
		case 3:
		    //获取月的日期数
			abscissa_max = getMonthDay(end);
			dec = "本月订单统计";
		    break;
		
		default:
	    //...;
	    break;
		}
		//横坐标
		Integer[] abscissa = null;
		if (choose_type == 1){
			abscissa = new Integer[abscissa_max+1];
		}else {
			abscissa = new Integer[abscissa_max];
		}
		//纵坐标
		int[] ordinate = new int[abscissa.length];
		if (choose_type == 1){
			for (int i = 0; i <= abscissa_max; i++) {
				abscissa[i] = i;
			}
		} else {
			for (int i = 1; i <= abscissa_max; i++) {
				abscissa[i-1] = i;
			}
		}
		
		map.put("abscissa", abscissa);
		map.put("dec", dec);
		
		if (!ObjectHelper.isEmpty(list)){
			switch(choose_type){
			case 1:
			    //获取小时数
				for (int i = 0; i < list.size(); i++) {
					Integer hour = getHour(list.get(0).getCreate_time());
					for (int j = 0; j < ordinate.length; j++) {
						if (abscissa[j] == hour){
							ordinate[j] = ++ordinate[j];
							break;
						}
						
					}
					
				}
			    break;
			case 2:
			    //获取星期数
				for (int i = 0; i < list.size(); i++) {
					Integer week = getWeekDay(list.get(0).getCreate_time());
					for (int j = 0; j < ordinate.length; j++) {
						if (abscissa[j] == week){
							ordinate[j] = ++ordinate[j];
							break;
						}
						
					}
				}
			    break;
			case 3:
			    //获取月的日期数
				for (int i = 0; i < list.size(); i++) {
					Integer monthDay = getMonthDay(list.get(0).getCreate_time());
					for (int j = 0; j < ordinate.length; j++) {
						if (abscissa[j] == monthDay){
							ordinate[j] = ++ordinate[j];
							break;
						}
						
					}
				}
			    break;
			
			default:
		    //...;
		    break;
			}
		}
		
		map.put("ordinate", ordinate);
		
	}
	
	/**
	 * 获取月的日期数
	 * @param end   时间戳
	 * @return
	 */
	private  Integer getMonthDay(Long end) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(end));
        int day = calendar.get(Calendar.DAY_OF_MONTH);
		return day;
	}

	/**
	 * 获取星期数
	 * @param end   时间戳
	 * @return
	 */
	private static Integer getWeekDay(Long end) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(end));
        int week = calendar.get(Calendar.DAY_OF_WEEK)-1;
        if (week == 0){
        	return 7;
        }
		return week;
	}

	/**
	 * 获取小时数
	 * @param end   时间戳
	 * @return
	 */
	private  Integer getHour(Long end) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(end));
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
		return hour;
	}

	@Override
	public Map<String, Object> queryLatelyOrderStatistics(Long bureau_id) {
		Map<String, Object> map = new HashMap<>();
		//横坐标
		int[] abscissa = getAbscissaLatelyWeek();
		map.put("abscissa", abscissa);
		//纵坐标
		int[] ordinate = new int[7];
		
		Long time = geToday();
		Long end = time;
		Long start = time - 7*24*60*60*1000;
		List<HwOrder> list = hwOrderMapper.query_statistics_to_bureau(bureau_id,start,end);
		if (!ObjectHelper.isEmpty(list)){
			for (HwOrder hwOrder : list) {
				Integer weekDay = getWeekDay(hwOrder.getCreate_time());
				for (int j = 0; j < ordinate.length; j++) {
					if (abscissa[j] == weekDay){
						ordinate[j] = ++ordinate[j];
						break;
					}
					
				}
			}
		}
		map.put("ordinate", ordinate);
		return map;
	}

	/**
	 * 通过本日获取最近一周横坐标
	 * @return
	 */
	private static int[] getAbscissaLatelyWeek() {
		Integer weekDay = getWeekDay(System.currentTimeMillis());
		int[] abscissa = new int[7];
		
		int a = 0;
		for (int i = abscissa.length-1; i < abscissa.length && i>=0; i--) {
			weekDay--;
			if (weekDay!=0){
				abscissa[i] = weekDay;
				a++;
			}else {
				weekDay = 7;
				break;
			}
		}
		if (weekDay==7){
			for (int i = abscissa.length-1-a; i < (abscissa.length-a) && i>=0; i--) {
				abscissa[i] = weekDay;
				weekDay--;
			}
		}
		
		return abscissa;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(getAbscissaLatelyWeek()));
	}

}
