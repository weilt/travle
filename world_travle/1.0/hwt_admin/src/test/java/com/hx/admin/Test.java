package com.hx.admin;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.apache.commons.lang3.tuple.MutablePair;
import org.slf4j.Logger;

import com.hx.core.wyim.utils.HttpClientUtil;

import javassist.compiler.ast.Pair;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class Test {
	
	public static void main(String[] args) throws Exception {
		System.out.println("体彩");
		ticai();
		System.out.println();
		System.out.println("---------------------");
		System.out.println("双色球");
		shuang();
	}
	/**
	 * 双色球
	 */
	public static void shuang(){
		//红球
		int[] reds = new int[6];
		for (int i = 0; i < reds.length; i++) {
			int red = aaa(33);
			if (is_not(red, reds)){
				i--;
			}else{
				reds[i] = red;
			}
		}
		System.out.print("红色：");
		for (int i = 0; i < reds.length; i++) {
			System.out.print(reds[i]+",");
		}
		System.out.println();
		//蓝球
		int[] blues = new int[1];
		for (int i = 0; i < blues.length; i++) {
			int blue = aaa(16);
			if (is_not(blue, blues)){
				i--;
			}else{
				blues[i] = blue;
			}
		}

		System.out.print("蓝色：");
		for (int i = 0; i < blues.length; i++) {
			System.out.print(blues[i]+",");
		}
	}
	/**
	 * 体彩
	 * 
	 */
	public static void ticai(){
		//红球
				int[] reds = new int[5];
				for (int i = 0; i < reds.length; i++) {
					int red = aaa(35);
					if (is_not(red, reds)){
						i--;
					}else{
						reds[i] = red;
					}
				}
				System.out.print("红色：");
				for (int i = 0; i < reds.length; i++) {
					System.out.print(reds[i]+",");
				}
				System.out.println();
				//蓝球
				int[] blues = new int[2];
				for (int i = 0; i < blues.length; i++) {
					int blue = aaa(12);
					if (is_not(blue, blues)){
						i--;
					}else{
						blues[i] = blue;
					}
				}

				System.out.print("蓝色：");
				for (int i = 0; i < blues.length; i++) {
					System.out.print(blues[i]+",");
				}
	}
	  /**
     * 随机数生成
     */
    public static int aaa(int b){
    	
    	Random r = new Random();
    	int a = r.nextInt(b)+1;
		return a;
    	
    }
    
    /**
     * 判断是否存在在数组内
     */
 public static boolean is_not(int b,int[] a){
    	
    	for (int i = 0; i < a.length; i++) {
			if (b == a[i]){
				return true;
			}
		}
    	return false;
    }
	private static final String KEY = "6f264b0451cbe7a8ebd42464aca1fb93";  
    private static final String OUTPUT = "JSON";  
    private static final String GET_LNG_LAT_URL = "http://restapi.amap.com/v3/geocode/geo";  
      
  
    public static void getLngLatFromOneAddr(String address) throws Exception{  
        
        Map params = new HashMap();  
        params.put("address", address);  
        params.put("output", OUTPUT);  
        params.put("key", KEY);  
        String result = HttpClientUtil.postHttp(GET_LNG_LAT_URL, params);  
        JSONObject jsonObject = JSONObject.fromObject(result);  
        //拿到返回报文的status值，高德的该接口返回值有两个：0-请求失败，1-请求成功；  
        int status = Integer.valueOf(jsonObject.getString("status"));  
        MutablePair pair = null;
		if(status == 1) {  
            JSONArray jsonArray = jsonObject.getJSONArray("geocodes");  
            for(int i = 0; i < jsonArray.size(); i++) {  
                JSONObject json = jsonArray.getJSONObject(i);  
                String lngLat = json.getString("location");  
                String[] lngLatArr = lngLat.split(",");  
                //经度  
                BigDecimal longitude = new BigDecimal(lngLatArr[0]);  
             System.out.println("经度" + longitude);  
                //纬度  
                BigDecimal latitude = new BigDecimal(lngLatArr[1]);  
             System.out.println("纬度" + latitude);  
                pair = new MutablePair(longitude, latitude);  
            }  
        } else {  
            String errorMsg = jsonObject.getString("info");  
        }  
       
    }
    
  
}
