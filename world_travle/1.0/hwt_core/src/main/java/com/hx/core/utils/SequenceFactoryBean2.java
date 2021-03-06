package com.hx.core.utils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.FactoryBean;

public class SequenceFactoryBean2  implements FactoryBean<String> {
	
	private static long counter = 0;
	
	/**
	 * 交易编号唯一生成
	 */
	@Override
	public synchronized String getObject() throws Exception {
		String date = new SimpleDateFormat("yyyyMM00ddHHmmss").format(new Date());
        String sequ = new DecimalFormat("00000000").format(counter ++);
        return date + sequ;
	}

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		 return String.class;
	}

	@Override
	public boolean isSingleton() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public static void reset() {
        SequenceFactoryBean2.counter = 0;
    }
	
	public static void main(String[] args) throws Exception {
		
		for (int i = 0; i < 50; i++) {
			System.out.println(new SequenceFactoryBean2().getObject());
		}
		String saa = "{\"2018-09-19\":\"{\\\"date\\\":\\\"2018-09-19\\\",\\\"adultPrice\\\":\\\"1000.00\\\",\\\"childPrice\\\":\\\"2000.00\\\"}\",\"2018-09-15\":\"{\\\"date\\\":\\\"2018-09-15\\\",\\\"adultPrice\\\":\\\"1000.00\\\",\\\"childPrice\\\":\\\"2000.00\\\"}\",\"2018-09-16\":\"{\\\"date\\\":\\\"2018-09-16\\\",\\\"adultPrice\\\":\\\"1000.00\\\",\\\"childPrice\\\":\\\"2000.00\\\"}\",\"2018-09-17\":\"{\\\"date\\\":\\\"2018-09-17\\\",\\\"adultPrice\\\":\\\"1000.00\\\",\\\"childPrice\\\":\\\"2000.00\\\"}\",\"2018-09-18\":\"{\\\"date\\\":\\\"2018-09-18\\\",\\\"adultPrice\\\":\\\"1000.00\\\",\\\"childPrice\\\":\\\"2000.00\\\"}\"}";
		Map<String, Object> json2Map = JsonUtils.json2Map(saa);
		System.out.println(json2Map);
	}
}
