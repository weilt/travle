package com.xx.util.util;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.google.gson.Gson;



public class RequestNOUtil {
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
		try {
			HttpResponse response = httpClient.execute(httpget);
			HttpEntity entity =response.getEntity();
			if(entity!=null){
				String str = EntityUtils.toString(entity,"utf-8");
				return str;
			}
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
		return null;
	}
	
	/**
	 * Post请求
	 * @param url
	 * @param outstr
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void doPostStr(String url,String outStr){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		try {
			httpPost.setEntity(new StringEntity(outStr,"utf-8"));
			HttpResponse response = httpClient.execute(httpPost);
			EntityUtils.toString(response.getEntity(),"utf-8");
		} catch (UnsupportedEncodingException e) {
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
	}
	/**
	 * Post请求
	 * @param url
	 * @param outstr
	 * @return
	 * @throws IOException 
	 * @throws ClientProtocolException 
	 */
	public static void doPostStr(String url){
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpPost httpPost = new HttpPost(url);
		try {
			HttpResponse response = httpClient.execute(httpPost);
			HttpEntity entity =response.getEntity();
			if(entity!=null){
				EntityUtils.toString(entity,"utf-8");
			}
		} catch (UnsupportedEncodingException e) {
		} catch (ClientProtocolException e) {
		} catch (IOException e) {
		}
	}
}
