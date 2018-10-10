package com.xx.util.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 
 *@Class： DateUtil
 *@Description： 日期工具类
 *刘钢 2016年6月29日11:08:51
 */
public class DateUtil {
	
	/**
	 * 
	 * 方法说明：比较两个日期是否在同一年的同一个月
	 * @author 刘钢 2016年6月29日11:09:00
	 * @param dateOne 第一个日期
	 * @param dateTow 第二个日期
	 * @return b true在 false 不在
	 */
   public boolean isMonth(Date dateOne,Date dateTow){
	   boolean b=false; 
	   Calendar c=Calendar.getInstance();
	   c.setTime(dateOne);
	   int monthOne=c.get(Calendar.MONTH);
	   int yearOne=c.get(Calendar.YEAR);
	   c.setTime(dateTow);
	   int monthTow=c.get(Calendar.MONTH);
	   int yearTow=c.get(Calendar.YEAR);
	   if(monthOne==monthTow && yearOne==yearTow){
		   b=true;
	   }
	   return b;
   }
   
   /**
    * 
    * @Title: datetime 
    * @Description:  格式化时间
    * @param date 时间
    * @return Date 格式化后的时间
    */
   public static Date datetime(Date date){
	   Date d=new Date();
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			 d=sdf.parse(sdf.format(date));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d;
   }

   public static String getNow() {
	   SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   return sdf.format(new Date());
   }
   
   /**
    * 
    * @Title: datetime 
    * @Description:  格式化时间
    * @param date 时间
    * @param format 时间的格式 "day"天 "hour"小时 "minute"分 "second"秒
    * @return Date 格式化后的时间
    */
   public static String datetime(Date date,String format){
	   String dateStr;
	   SimpleDateFormat sdf=null;
	   if(format!=null&&!format.equals("")&&format.equals("day")){
		    sdf=new SimpleDateFormat("yyyy-MM-dd");
	   }else if(format!=null&&!format.equals("")&&format.equals("hour")){
		    sdf=new SimpleDateFormat("yyyy-MM-dd HH");
	   }else if(format!=null&&!format.equals("")&&format.equals("minute")){
		    sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   }else if(format!=null&&!format.equals("")&&format.equals("second")){
		    sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   }
	dateStr=sdf.format(date);
	return dateStr;
   }
   
   
   /**
    * 转换成Date
    * @param dateString 时间的格式 "day"天 "hour"小时 "minute"分 "second"秒
    * @param format 时间
    * @return
    */
   public static Date datetime(String dateString,String format ){
	   Date date = null;
	   SimpleDateFormat sdf=null;
	   if(format!=null&&!format.equals("")&&format.equals("day")){
		    sdf=new SimpleDateFormat("yyyy-MM-dd");
	   }else if(format!=null&&!format.equals("")&&format.equals("hour")){
		    sdf=new SimpleDateFormat("yyyy-MM-dd HH");
	   }else if(format!=null&&!format.equals("")&&format.equals("minute")){
		    sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
	   }else if(format!=null&&!format.equals("")&&format.equals("second")){
		    sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	   }
	   try {
		   date=sdf.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	   return date;
   }
   
   
   
   
   /**  
    * 计算两个日期之间相差的天数  
    * @param smdate 较小的时间 
    * @param bdate  较大的时间 
    * @return 相差天数 
    * @throws ParseException  
    */    
   public static int daysBetween(Date smdate,Date bdate)     {    
       SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
       try {
		smdate=sdf.parse(sdf.format(smdate));
		bdate=sdf.parse(sdf.format(bdate));  
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
       Calendar cal = Calendar.getInstance();    
       cal.setTime(smdate);    
       long time1 = cal.getTimeInMillis();                 
       cal.setTime(bdate);    
       long time2 = cal.getTimeInMillis();         
       long between_days=(time2-time1)/(1000*3600*24);  
           
      return Integer.parseInt(String.valueOf(between_days));           
   } 
   
   /**
    * 日期加减小时
    * @Title: addHour
    * @Description: 日期加减小时
    * @param: @param time
    * @param: @return   
    * @return: Date   
    * @throws
    */
   public static Date addHour(Date time,int hours){

		Calendar calendar=Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.HOUR_OF_DAY,calendar.get(Calendar.HOUR_OF_DAY)+hours);//让日期加减小时
		time = calendar.getTime();
		return time;
   }
   
   /**
    * 日期加减天数
    * @Title: addDay
    * @Description: 日期加减天数
    * @param: @param time
    * @param: @return   
    * @return: Date   
    * @throws
    */
   public static Date addDay(Date time,int days){

		Calendar calendar=Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.DAY_OF_MONTH,calendar.get(Calendar.DAY_OF_MONTH)+days);//让日期加减天数
		time = calendar.getTime();
		return time;
   }
   
   /**
    * 日期加减月份
    * @Title: addMonth
    * @Description: 日期加减月份
    * @param: @param time
    * @param: @param months
    * @param: @return   
    * @return: Date   
    * @throws
    */
   public static Date addMonth(Date time,int months){

		Calendar calendar=Calendar.getInstance();
		calendar.setTime(time);
		calendar.set(Calendar.MONTH,calendar.get(Calendar.MONTH)+months);//让日期加减月份
		time = calendar.getTime();
		return time;
  }
   
   
  
   
   
	/**
	 * 获取本周的开始时间
	 * @return
	 */
    public static Date getBeginDayOfWeek() {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int dayofweek = cal.get(Calendar.DAY_OF_WEEK);
        if (dayofweek == 1) {
            dayofweek += 7;
        }
        cal.add(Calendar.DATE, 2 - dayofweek);
        return cal.getTime();
    }
    
    /**
     * 获取本周的结束时间
     * @return
     */
    public static Date getEndDayOfWeek(){
        Calendar cal = Calendar.getInstance();
        cal.setTime(getBeginDayOfWeek());  
        cal.add(Calendar.DAY_OF_WEEK, 6); 
        Date weekEndSta = cal.getTime();
        return weekEndSta;
    }
  
    public static void main(String[] args) {
		System.out.println(datetime(getBeginDayOfWeek(), "day"));
	}
   
  
}
