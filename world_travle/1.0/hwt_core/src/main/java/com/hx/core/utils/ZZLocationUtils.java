package com.hx.core.utils;

import java.lang.reflect.Array;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 地区工具
 * @author Administrator
 *
 */
public class ZZLocationUtils {
	
	private static final String[] shixia_city = {"重庆市","北京市","上海市","天津市"};
	
	/**
	 * 阿里通过经度纬度获取地址详情
	 * @param log	经度
	 * @param lat	纬度
	 * @return
	 */
	public static String getAdd(String log, String lat ){ 
		
        //lat 小  log  大    
        //参数解释: 纬度,经度 type 001 (100代表道路，010代表POI，001代表门址，111可以同时显示前三项)   
        String urlString = "http://gc.ditu.aliyun.com/regeocoding?l="+lat+","+log+"&type=010";    
        String res = "";       
        try {       
            URL url = new URL(urlString);      
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();      
            conn.setDoOutput(true);      
            conn.setRequestMethod("POST");      
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));      
            String line;      
           while ((line = in.readLine()) != null) {      
               res += line+"\n";      
         }      
            in.close();      
        } catch (Exception e) {      
            System.out.println("error in wapaction,and e is " + e.getMessage());      
        }     
        System.out.println(res);    
        return res;      
    }    
	
	/**
	 * 解析阿里通过经度纬度获取地址的详情 来获取 [省,市,区]
	 * 
	 */
	public static String[] get_province_city_district(String res){
		
		JSONObject jsonObject =  JSONObject.fromObject(res);  
		JSONArray jsonArray =  JSONArray.fromObject(jsonObject.getString("addrList"));
		
		JSONObject j_2 = JSONObject.fromObject(jsonArray.get(0)); 
		 
		String status =  j_2.getString("status");
		 
		if ("0".equals(status)){
			 return null;
		}else{
			String allAdd = j_2.getString("admName"); 
		    String arr[] = allAdd.split(","); 
		    return arr;
		}
	}
	
	/**
	 * 阿里通过经度纬度来获取 [省,市,区]
	 * @param log	经度
	 * @param lat	纬度
	 * @return
	 */
	public static String[] get_province_city_district_by_log_lat(String log, String lat ){
		
		return get_province_city_district(getAdd(log,lat));
		
	}
	
	public static void main(String[] args) {
		
		System.out.println(get_province_city_district_by_log_lat("79.291084", "37.209641"));
		System.out.println(getAdd_gaode("107.004463435047", "28.932787951631"));
		System.out.println(getAdd_gaode(getAdd_gaode("121.60653", "23.98214")));
		
	}
	
	/**
	 * 高德通过经度纬度获取地址详情
	 * @param log	经度
	 * @param lat	纬度
	 * @return
	 */
	public static String getAdd_gaode(String log, String lat ){ 
		
        
        String urlString = "https://restapi.amap.com/v3/geocode/regeo?output=json&location="+log+","+lat+"&key=a1e5c3d35ae9873281498558d800114b&radius=1000&extensions=all";    
        String res = "";       
        try {       
            URL url = new URL(urlString);      
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection)url.openConnection();      
            conn.setDoOutput(true);      
            conn.setRequestMethod("POST");      
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(conn.getInputStream(),"UTF-8"));      
            String line;      
           while ((line = in.readLine()) != null) {      
               res += line+"\n";      
         }      
            in.close();      
        } catch (Exception e) {      
            System.out.println("error in wapaction,and e is " + e.getMessage());      
        }     
      //  System.out.println(res);    
        return res;      
    }
	
	/**
	 * 解析高德返回的数据获取省市区，区编码等
	 */
	public static Map<String, String> getAdd_gaode(String res ){
		//System.out.println(res);
		JSONObject jsonObject =  JSONObject.fromObject(res);  
		String status =  jsonObject.getString("status");
		 
		if ("0".equals(status)){
			 throw new RuntimeException("高德接口请求失败");
		}else{
			String regeocode = jsonObject.getString("regeocode"); 
			JSONObject json_regeocode = JSONObject.fromObject(regeocode);
			String addressComponent = json_regeocode.getString("addressComponent"); 
			JSONObject json_addressComponent = JSONObject.fromObject(addressComponent);
			Map<String, String> map = new HashMap<String, String>();
			//国家
			String country = json_addressComponent.getString("country");
			map.put("country", country);
			
			//省
			String province = json_addressComponent.getString("province");
			map.put("province", province);
			//市
			String city = json_addressComponent.getString("city");
			//区
			String district = json_addressComponent.getString("district");
			map.put("district", "[]".equals(district)?null:district);
			//当所在城市为北京、上海、天津、重庆四个直辖市时，该字段返回为空
			//当所在城市属于县级市的时候，此字段为空
			if("[]".equals(city)){
				if (is_not_existence(shixia_city, province)){
					map.put("city", province);
				}else{
					map.put("city", district);
				}
			}else{
				
				map.put("city", city);
			}
			//行政编码
			map.put("city_code", json_addressComponent.getString("adcode"));
			//区号
			map.put("area_code", json_addressComponent.getString("citycode"));
			return map;
		} 
	}
	
	/**
	 * 
	 */
	public static boolean is_not_existence(String[] arr,String str){
		if (ObjectHelper.isEmpty(str)){
			return false;
		}
		if (ObjectHelper.isEmpty(arr)){
			return false;
		}
		for (int i = 0; i < arr.length; i++) {
			if (str.equals(arr[i])){
				return true;
			}
		}
		return false;
		
	}
	
	/**
	 * 高德通过经度纬度来获取 [省,市,区] 区号等
	 * @param log	经度
	 * @param lat	纬度
	 * @return
	 */
	public static Map<String, String> get_province_city_district_by_gaode_log_lat(String log, String lat ){
		
		return getAdd_gaode(getAdd_gaode(log,lat));
		
	}
}
