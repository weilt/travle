package com.hx.core.utils;

import java.util.Random;
import java.util.UUID;

public class UUIDHelper {

	/**
	 * 
	 * 方法描述 - 生成ID号 唯一号
	 * @return
	 * @param id  String唯一标识       
	 * @param personid String 用户唯一标识
	 * @return rtobj BaseReturn 基本返回对象
	 * @变更记录 2016-7-18 下午3:22:30  刘钢  创建
	 */
	public static String createUUId(){
		 UUID uuid = UUID.randomUUID(); 
		 String id = uuid.toString().replaceAll("-", "");
		 return id;
	}
	public static void main(String[] args) {
		
		System.out.println(createUUId());
	}
	
	/**
	 * 生成 -4位 -随机数字
	 * @param number 多少位
	 * @return
	 */
	public static String randomNumber(){
		Random random = new Random();
		int ran = random.nextInt(9999);
		if(ran < 1000){
			ran += 1000;
		}
		return ran+"";
	} 
	
	/**
	 * 生成 随机的 字符串（字母字符串）
	 * @param length
	 * @return
	 */
	public static String code(int length){
		String randomcode = "";  
        for(int i = 0;i < length; i++)  
        {  
            //52个字母与6个大小写字母间的符号；范围为91~96  
            int value = (int)(Math.random()*58+65);  
            while(value>=91 && value<=96)  
                value = (int)(Math.random()*58+65);  
            randomcode = randomcode + (char)value;  
        }  
        return randomcode;
	}
	
	/**
	 * 生成随机数字和字母,  
	 * @param length
	 * @return
	 */
    public static String getStringRandom(int length) {  
        String val = "";  
        Random random = new Random();  
          
        //参数length，表示生成几位随机数  
        for(int i = 0; i < length; i++) {  
              
            String charOrNum = random.nextInt(2) % 2 == 0 ? "char" : "num";  
            //输出字母还是数字  
            if( "char".equalsIgnoreCase(charOrNum) ) {  
                //输出是大写字母还是小写字母  
                int temp = random.nextInt(2) % 2 == 0 ? 65 : 97;  
                val += (char)(random.nextInt(26) + temp);  
            } else if( "num".equalsIgnoreCase(charOrNum) ) {  
                val += String.valueOf(random.nextInt(10));  
            }  
        }  
        return val;  
    }
		
}
