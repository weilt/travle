package com.hx.core.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import scala.annotation.migration;

/**
 * 日期工具类
 * @author WJ
 *
 */
public class DateUtils {

    /**
     * 默认时区（系统自带时区）
     */
    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    /**
     * 10年一个年代
     */
    private static final int YEAS_VALUE = 10;

    /**
     * 截取年份后两位
     */
    private static final int BEGIN_INDEX = 2;

	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException 
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
      
	/** 
	*字符串的日期格式的计算 
	*/  
    public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days)); 
    }
    
    /**
     * 计算两个时间的小时
     * @param smdate
     * @param bdate
     * @return
     * @throws ParseException
     */
    public static int getHours(Date smdate,Date bdate) throws ParseException {
    	long nd = 1000*24*60*60;//一天的毫秒数
    	long nh = 1000*60*60;//一小时的毫秒数
    	Long a = bdate.getTime() - smdate.getTime() ;
    	long day = a/nd;//计算差多少天
    	return (int) (day*24 + a%nd/nh);
    }

    /**
     * 计算两个时间的分钟数
     * @param endDate
     * @param nowDate
     * @return
     */
    public static long getMinutes(Date endDate,Date nowDate) {
        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少分钟
        return diff % nd % nh / nm;
    }


    public static String getDatePoor(Date endDate, Date nowDate) {

        long nd = 1000 * 24 * 60 * 60;
        long nh = 1000 * 60 * 60;
        long nm = 1000 * 60;
        // long ns = 1000;
        // 获得两个时间的毫秒时间差异
        long diff = endDate.getTime() - nowDate.getTime();
        // 计算差多少天
        long day = diff / nd;
        // 计算差多少小时
        long hour = diff % nd / nh;
        // 计算差多少分钟
        long min = diff % nd % nh / nm;
        // 计算差多少秒//输出结果
        // long sec = diff % nd % nh % nm / ns;
        return day + "天" + hour + "小时" + min + "分钟";
    }



    /**
     * 获取某个日期时间的第N天的日期
     * 可以获取前N天或者后N天的日期
     * @param date	日期
     * @param days	要获取的前N天或者后N天  (前N天 : -N)
     * @return
     */
    public static Date getNextDay(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		date = calendar.getTime();
		return date;
	}

	/**
	 * 判断时间
	 * @return 返回自定义前端的时间显示描述
 	 */
	public static String getDateHtml(Date dateStr) {
		String returnStr = "";
		try {
			SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			// 日期
			Date d = dateStr;
			// System.err.println("d == "+s.format(d));
			// 传入日期是当前日期的时间差
			int day = daysBetween(new Date(), d);
			// System.out.println("day == "+day);
			SimpleDateFormat cc = new SimpleDateFormat("HH:mm");
			// 相等
			switch (day) {
			case 0:
				returnStr = cc.format(d);
				break;
			case -1:
				returnStr = "昨天 " + cc.format(d);
				break;
//			case -2:
//				returnStr = s.format(dateStr);
//				break;
			default:
				returnStr = s.format(dateStr);
				break;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnStr;
	}

    /**
     * 是否超过当天12点
     * @return 超过 true / false
     */
	public static boolean isExcessTwelve() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        if(c.get(Calendar.HOUR_OF_DAY) >= 12) {
            return true;
        }
        return false;
    }


    /**
     *  获取指定年份第一天
     * @param year
     * @return
     */
    public static Long beginYear(Integer  year){
	    //获取时间
        LocalDate date = LocalDate.of(year,01,01);
        LocalTime time = LocalTime.of(0,0,0);
        LocalDateTime localDateTime = LocalDateTime.of(date,time);
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }

    /**
     * 获取指定年份最后一天
     * @param year
     * @return
     */
    public static Long endYear(Integer year){
        //获取时间
        LocalDate date = LocalDate.of(year,12,31);
        LocalTime time = LocalTime.of(23,59,59);
        LocalDateTime localDateTime = LocalDateTime.of(date,time);
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     *  获取年代 （map.put(2010,"10")）
     * @param index 向前推多少个年代
     * @param size 返回多少个年年代（map.size）
     * @return
     */
    public static Map<Integer,String> getBeforeYears(int index,int size){
        if (size > index || index <= 0){
            throw new IllegalArgumentException("参数错误");
        }
        Map<Integer,String> result = new HashMap<>(size);
        LocalDateTime localDateTime = LocalDateTime.now();
        int year = localDateTime.getYear();
        String yy = String.valueOf(year).substring(0,String.valueOf(year).length() - 1) + "0";
        year = Integer.parseInt(yy);
        for(;size > 0; size -- ){
            int years = year - index * YEAS_VALUE;
            String yearsValue = String.valueOf(years);
            int begin = yearsValue.length() - BEGIN_INDEX;
            String xx = yearsValue.substring(begin,begin + 1) + "0";
            result.put(years,xx);
            index --;
        }
        return result;
    }
    
    /**
     * 根据时间戳 毫秒  获取年-月-日
     */
    public static String YMD(Long currentTimeMillis){
    	
		 SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	     // 根据时间戳获取时间
	   
	     String date = sdf.format(new Date(currentTimeMillis));
	     return date;

    }
    
    public static void main(String[] args) {
		System.out.println(YMD(System.currentTimeMillis()));
	}
}
