package com.xx.util.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RequestUtil {
	/**
	 * Get请求
	 * @param url
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doGetStr(String url){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet httpget= new HttpGet(url);
		String jsonObject =null;
		try {
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setConnectTimeout(15000).setConnectionRequestTimeout(11000)  
			        .setSocketTimeout(15000).build();  
			httpget.setConfig(requestConfig); 
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity =response.getEntity();
			if(entity!=null){
				jsonObject = EntityUtils.toString(entity,"utf-8");
			}
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	/**
	 * Post请求
	 * @param url
	 * @param outstr
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doPostStr(String url,String outStr){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String jsonObject =null;
		try {
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setConnectTimeout(15000).setConnectionRequestTimeout(11000)  
			        .setSocketTimeout(15000).build();  
			httpPost.setConfig(requestConfig); 
			
			//将该字符串设置为HttpEntity，并设置编码方式  
			httpPost.setEntity(new StringEntity(outStr,"utf-8"));
			HttpResponse response = httpClient.execute(httpPost);
			jsonObject =EntityUtils.toString(response.getEntity(),"utf-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return jsonObject;
	}
	/**
	 * Post请求
	 * @param url
	 * @param outstr
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String doPostStr(String url){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String result = null;
		try {
			RequestConfig requestConfig = RequestConfig.custom()  
			        .setConnectTimeout(15000).setConnectionRequestTimeout(11000)  
			        .setSocketTimeout(15000).build();  
			httpPost.setConfig(requestConfig); 
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity =response.getEntity();
			if(entity!=null){
				result =EntityUtils.toString(entity,"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	/**
	 * Post请求
	 * @param url
	 * @param outstr
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static String postBody(String url,String json){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		String result = null;
		try {
			RequestConfig requestConfig = RequestConfig.custom()  
					.setConnectTimeout(15000).setConnectionRequestTimeout(11000)  
					.setSocketTimeout(15000).build();  
			httpPost.setConfig(requestConfig); 
			StringEntity se = new StringEntity(json,"UTF-8");
			httpPost.setEntity(se);
			CloseableHttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity =response.getEntity();
			if(entity!=null){
				result =EntityUtils.toString(entity,"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	
	/**
	 * POST请求
	 * @param requestUrl
	 * @param outputStr
	 * @return
	 */
	public static String httpRequestPost(String requestUrl, String outputStr){
		return	httpRequest(requestUrl, "POST", outputStr);
	}
	
	/**
     * 发起http请求并获取结果
     *
     * @param requestUrl 请求地址
     * @param requestMethod 请求方式（GET、POST）
     * @param outputStr 提交的数据
     * @return
     */
    public static String httpRequest(String requestUrl, String requestMethod, String outputStr){
        StringBuffer buffer = new StringBuffer();
        try {
            URL url = new URL(requestUrl);
            HttpURLConnection httpUrlConn = (HttpURLConnection) url.openConnection();

            httpUrlConn.setDoOutput(true);
            httpUrlConn.setDoInput(true);
            httpUrlConn.setUseCaches(false);
            // 设置请求方式（GET/POST）
            httpUrlConn.setRequestMethod(requestMethod);

            httpUrlConn.connect();

            // 当有数据需要提交时
            if (StringUtils.isNotBlank(outputStr)) {
                OutputStream outputStream = httpUrlConn.getOutputStream();
                // 注意编码格式，防止中文乱码
                outputStream.write(outputStr.getBytes("UTF-8"));
                outputStream.close();
            }

            // 将返回的输入流转换成字符串
            InputStream inputStream = httpUrlConn.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                buffer.append(str);
            }
            bufferedReader.close();
            inputStreamReader.close();
            // 释放资源
            inputStream.close();
            inputStream = null;
            httpUrlConn.disconnect();
        } catch (ConnectException ce) {
        } catch (Exception e) {
        } finally {
		}
		return buffer.toString();
    }
}
