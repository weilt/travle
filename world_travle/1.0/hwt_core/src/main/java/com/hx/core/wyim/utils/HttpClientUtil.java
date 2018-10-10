package com.hx.core.wyim.utils;


import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.hx.core.wyim.ntes.NtesHeader;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by RO on 2018/3/7.
 */
public class HttpClientUtil {

    /**
     * HTTPClient POST请求
     * @param url       请求地址
     * @param paramMap  参数列表
     * @return  请求响应JSON
     * @throws Exception
     */
    public static String postHttp(String url, Map<String,String> paramMap) throws Exception {
        DefaultHttpClient httpClient = new DefaultHttpClient();
        HttpPost httpPost = new HttpPost(url);

        // 设置请求的header
        NtesHeader header = new NtesHeader();
        httpPost.addHeader("AppKey", header.getAppKey());
        httpPost.addHeader("Nonce", header.getNonce());
        httpPost.addHeader("CurTime", header.getCurTime());
        httpPost.addHeader("CheckSum", header.getCheckSum());
        httpPost.addHeader("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

        // 设置请求的参数
        List<NameValuePair> nvps = new ArrayList<>();

        if(paramMap != null) {
            for (String s : paramMap.keySet()) {
                nvps.add(new BasicNameValuePair(s, paramMap.get(s)));
            }
        }
        httpPost.setEntity(new UrlEncodedFormEntity(nvps, "utf-8"));
        System.err.println("---------------------++++++++++++++----");
        HttpResponse response = httpClient.execute(httpPost);
        return EntityUtils.toString(response.getEntity(), "utf-8");
    }
}
