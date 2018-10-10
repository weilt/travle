package com.hx.admin;

import com.google.gson.Gson;
import com.hx.core.mongo.service.MongoService;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.ZZLocationUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 抓取景点数据
 */
public class A {

    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static JSONObject postRequestFromUrl(String url, String body) throws IOException, JSONException {
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection(); conn.setDoOutput(true);
        conn.setDoInput(true);
        PrintWriter out = new PrintWriter(conn.getOutputStream()); out.print(body); out.flush();

        InputStream instream = conn.getInputStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);
            JSONObject json = new JSONObject(jsonText);
            return json;
        }finally { instream.close(); }
    }

    public static JSONObject getRequestFromUrl(String url) throws IOException, JSONException {
        URL realUrl = new URL(url);
        URLConnection conn = realUrl.openConnection();
        InputStream instream = conn.getInputStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(instream, Charset.forName("UTF-8")));
            String jsonText = readAll(rd); JSONObject json = new JSONObject(jsonText); return json;
        }
        finally {
            instream.close();
        }
    }


    public static void main(String[] args) throws IOException, JSONException {
    	
    	int[] ids = {31,
    			315,
    			342,
    			275,
    			212,
    			911   ,
    			8     ,
    			24    ,
    			623   ,
    			487   ,
    			988   ,
    			207   ,
    			514   ,
    			164   ,
    			426   ,
    			33    ,
    			120058,
    			151   ,
    			37    ,
    			495   ,
    			196   ,
    			1096  ,
    			350   ,
    			120496,
    			120418,
    			19    ,
    			120061,
    			183   ,
    			156   ,
    			213   ,
    			345   ,
    			458   ,
    			68    ,
    			362   ,
    			272   ,
    			284   ,
    			267   ,
    			128   ,
    			219   ,
    			25    ,
    			165   ,
    			124   ,
    			428   ,
    			29    ,
    			77    ,
    			231   ,
    			36    ,
    			103   ,
    			238   ,
    			88    ,
    			120535,
    			480   ,
    			441   ,
    			105   ,
    			198   ,
    			20    ,
    			319   ,
    			768   ,
    			87    ,
    			175   ,
    			166   
};
    	for (int j = 0; j < ids.length; j++) {
    		 //要修改的
            String key = "cTU6APd6fKjiAWPVMnintum9enZJ7j9tSEhOxmJ41XWEIpJJ5ILHyKBpMk8A8xkH";
            String dbname = "hwt6";
            Integer cityid = ids[j];

            System.out.println("开始获取"+cityid+"的数据");
            int pageToken = 1;
            int count = 3;
            int total = 0;

            // 请求示例 url 默认请求参数已经做URL编码
            //String url = "http://api01.bitspaceman.com:8000/sight/ctrip?apikey=htDWFQQBdjgaMz3epKEPMacnZNQK9Oy09IUurhjrxiB3IbAa26U2EYzj9BYYcrCN&cityid=158&sort=1&pageToken=";
        	String url1 = "http://api01.bitspaceman.com:8000/sight/ctrip?apikey="+key+"&cityid="+cityid+"&sort=1&pageToken=";
////            String url = "http://120.76.205.241:8000/sight/qyer?cityid=11186&apikey=htDWFQQBdjgaMz3epKEPMacnZNQK9Oy09IUurhjrxiB3IbAa26U2EYzj9BYYcrCN";

            //重庆景点初始ID
            Integer spot_id = 10000;
            //判断是否是中国
            if(!is_cha(url1, 1)){
            	continue;
            }
           
            
            for (int i = 0; i < 100000; i++) {
                //url += "&pageToken=" + pageToken;
            	if(pageToken>251){
            		break;
            	}
            	String url = url1 + pageToken;
            	//System.out.println("传入的pageToken------------"+pageToken+"=========================");
                JSONObject json;
            	try {
                    json = getRequestFromUrl(url);
                }catch (Exception e){
                	e.printStackTrace();
                    count --;
                    if (count == 0){
                        pageToken++;
                        count = 3;
                        continue;
                    }
                    continue;
                }

                Map<String,Object> m = JsonUtils.json2Map(json.toString());
                if (m.get("retcode").equals("100702")){
					System.out.println("余额不足");
					
					String content1 = cityid+"\t\t"+total+"\t\t"+pageToken+"\n";
					writerText("C:/Users/Administrator/Desktop/余额不足记录.txt", content1 );
					throw new RuntimeException("余额不足");
				}
               // System.out.println("返回的pageToken------------"+m.get("pageToken"));
                
                if(m.get("pageToken") == null){
                	System.out.println(m.get("pageToken"));
                	System.err.println("最后一页了");
                	break;
                }
                if(!((pageToken+1)+"").equals(m.get("pageToken"))){
                	//throw new RuntimeException("最后一页");
                	break;
                }
                pageToken = m.get("pageToken") == null ? pageToken +1 : pageToken + Integer.valueOf(m.get("pageToken").toString());

                if(m != null) {
                    //如果还有信息
                    if(m.get("hasNext") == null || !Boolean.valueOf(m.get("hasNext").toString()) || !"000000".equals(m.get("retcode").toString())) {
                    	System.out.println("没有查询到数据");
                    	break;
                    }
                    //pageToken = Integer.valueOf(m.get("pageToken").toString()) + 1;
                    pageToken = Integer.valueOf(m.get("pageToken").toString());
                    //System.out.println("开始存入数据到MONGO，当前是第 <" + i +"> 条数据, 数据信息 :: "  + JsonUtils.Bean2Json(m.get("data")));
                    //
                    List<Map<String,Object>> l = new Gson().fromJson(JsonUtils.Bean2Json(m.get("data")), ArrayList.class);
                    

                    for (Map<String, Object> objectMap : l) {
                        ScenicSpot ss = createSpot(objectMap);
                        try {


    						MongoService.insertIncId(dbname,"scenic_spot", ss, "spotId");

    					}  catch (Exception e) {
    						// TODO Auto-generated catch block
                            System.out.println("存数据报错：");
                            e.printStackTrace();
    						return;
    					}
                        spot_id++;
                        total++;
                    }

                } else {
                	System.out.println("没有查询到数据");
                	break;
                }
            }
            //io保存数据信息
            
            String content = cityid+"\t\t"+total+"\t\t"+pageToken+"\n";
            System.out.println(+cityid+"的数据结束\t\t"+content);
			writerText("C:/Users/Administrator/Desktop/数据记录.txt", content );
		}
    	
       
    }
    
    /**
    *
    * @param filePath  文件路径
    * @param content  追加内容
    * @throws IOException
    */
   private static void writerText(String filePath,String content){
       File file = new File(filePath);
      
       FileWriter fw = null;
       BufferedWriter bufferedWriter = null;
       try {
    	   if (!file.exists()){
               file.createNewFile();
           }
           fw = new FileWriter(file,true);
           bufferedWriter = new BufferedWriter(fw);
           bufferedWriter.write(content);
          bufferedWriter.newLine();
       }catch ( IOException e){
           e.printStackTrace();
           System.out.println("写文件出错");
       }finally {
           try{
        	   bufferedWriter.close();
               fw.close();
           }catch (Exception e){
               e.printStackTrace();
           }
       }
   }

    /**
     * 判断是否是中国
     */
    public static boolean is_cha(String url ,int a ){
    	if (a>3){
    		throw new RuntimeException("数据请求异常");
    	}
    	for (int i = 0; i < 3; i++) {
        	 JSONObject requestFromUrl;
			try {
				requestFromUrl = getRequestFromUrl(url+1);
				Map<String, Object> json2Map = JsonUtils.json2Map(requestFromUrl.toString());
				if (json2Map.get("retcode").equals("100702")){
					System.out.println("余额不足");
					writerText("C:/Users/Administrator/Desktop/余额不足记录.txt", "余额不足" );
					return true;
				}else{
					List<Map<String,Object>> l = new Gson().fromJson(JsonUtils.Bean2Json(json2Map.get("data")), ArrayList.class);
					if(l!=null&&l.size()>0){
						if ("中国".equals(l.get(0).get("country"))){
							return true;
						}else{
							System.out.println("不是中国的");
							return false;
						}
					}
				}
			} catch (Exception e) {
				
				e.printStackTrace();
				return is_cha( url ,a++);
			} 
            
		}
		return false;
    	
    }
    
    
    
    private static ScenicSpot createSpot(Map<String,Object> map ) {
        ScenicSpot ss = new ScenicSpot();
        ss.setSpotName(map.get("title") + "");
        ss.setCountry(map.get("country") + "");
        ss.setCity(map.get("city") + "");
        ss.setDescription(map.get("description") + "");
        ss.setBrief(map.get("tipInfo") + "");
        ss.setSubtitle(map.get("subtitle") + "");
        ss.setRating(map.get("rating") +"");
        ss.setTelephones(map.get("telephones")+"");
        ss.setGeoPoint(map.get("geoPoint") + "");
        ss.setTags(map.get("tags") + "");
        ss.setImageUrls(map.get("imageUrls") + "");
        ss.setRank(map.get("rank") +"");
        ss.setOpeningHours(map.get("openingHours") + "");
        ss.setLocation(map.get("location") + "");
        ss.setTicketInfo(map.get("ticketInfo") + "");
        ss.setDataUrl(map.get("url") + "");
        ss.setDataSources("携程");
        ss.setDataTime(System.currentTimeMillis());
        ss.setIsHide(1);
        ss.setVisitsNum(0l);
        ss.setIsHot(0);
        ss.setIsRecommend(0);
        String geoPoint = ss.getGeoPoint();
      
        String[] split = geoPoint.split(",");
        Map<String, String> map2 = ZZLocationUtils.get_province_city_district_by_gaode_log_lat(split[0].replace("{", "").split("=")[1], split[1].replace("}", "").split("=")[1]);
        if (map2!=null){
        	ss.setCountry(map2.get("country"));
        	ss.setArea_code(map2.get("area_code"));
        	ss.setCity(map2.get("city"));
        	ss.setCity_code(map2.get("city_code"));
        	ss.setDistrict(map2.get("district"));
        	ss.setProvince(map2.get("province"));
        }
       
      
       return ss;
       // return ss;
    }
    
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
    
    @Test
    public void aaa(){
    	//String add = getAdd("106.449721", "29.581198");    
    	String add = getAdd("106.449715","29.581207");  
		 net.sf.json.JSONObject jsonObject =  net.sf.json.JSONObject.fromObject(add);    
		 net.sf.json.JSONArray jsonArray =  net.sf.json.JSONArray.fromObject(jsonObject.getString("addrList"));  
		 System.out.println(jsonArray);
		 net.sf.json.JSONObject j_2 =  net.sf.json.JSONObject.fromObject(jsonArray.get(0));    
        String allAdd = j_2.getString("admName");
       String status =  j_2.getString("status");
       System.out.println(status);
        String arr[] = allAdd.split(",");    
        System.out.println("省:"+arr[0]+"\n市:"+arr[1]+"\n区:"+arr[2]);    
    }
    @Test
    public void de(){
    	Map<String, Object> filterMap = new HashMap<>();
    	filterMap.put("city", "西岭雪山");
		MongoService.delete("hwt2","scenic_spot", filterMap );
    }
    
    
}