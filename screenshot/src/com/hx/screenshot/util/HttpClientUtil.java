package com.hx.screenshot.util;




import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.EntityBuilder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 * @Auther: Zhoudu
 * @Date: 2018/7/3 14:13
 * @Description: http请求工具
 */
public class HttpClientUtil {


    /**
     * post请求
     * @param url 请求地址
     * @param paramMap 请求参数
     * @return
     */
    public static String post(String url, Map<String,String> paramMap){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        //拼接参数
        List<NameValuePair> list = new ArrayList<>();
        paramMap.entrySet().stream().forEach(m -> {
            NameValuePair pair = new BasicNameValuePair(m.getKey(), m.getValue());
            list.add(pair);
        });
        CloseableHttpResponse response;
        String result = null;
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(list));
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     *  多文件上传 (使用 MultipartEntityBuilder 实现多文件上传)
     * @param url url
     * @param files map
     * @return
     */
    public static String postUpload(String url,String mac, Map<String,byte[]> files){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        RequestConfig requestConfig = RequestConfig.custom()
                .setConnectTimeout(10000)//连接超时时间
                .setConnectionRequestTimeout(5000)
                .setSocketTimeout(10000)//请求响应时间
                .build();
        httpPost.setConfig(requestConfig);
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        //设置请求的编码格式
        builder.setCharset(Charset.forName("utf-8"));
        //设置浏览器兼容模式
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        //MAC地址放到请求头上
        httpPost.addHeader("MAC",mac);


        if (null == files || files.isEmpty()){
            throw new IllegalArgumentException("文件错误");
        }
        files.keySet().stream().forEach(l ->
            builder.addPart("files",new ByteArrayBody(files.get(l),ContentType.create("image/jpg"),l+".jpg"))
        );
        CloseableHttpResponse response;
        String result = "FAIL";
        try {
            //文件
            httpPost.setEntity(builder.build());
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                HttpEntity httpEntity = response.getEntity();
                result = EntityUtils.toString(httpEntity);
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     *  上传文件 （流的形式）
     * @param mac 本机MAC地址
     * @param bytes 图片数组
     * @return
     */
    public static String postUpload(String url,String mac,byte[] bytes){
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        //MAC地址放到请求头上
        httpPost.addHeader("MAC",mac);
        httpPost.addHeader("TIME",getTime());
        CloseableHttpResponse response;
        String result = "FAIL";
        try {
            //文件
            HttpEntity fileEntity = getMultiDefaultArrayEntity(bytes);
            httpPost.setEntity(fileEntity);
            response = httpClient.execute(httpPost);
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
                fileEntity = response.getEntity();
                result = EntityUtils.toString(fileEntity);
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }



    /**
     *  文件上传
     * @param bytes
     * @return
     */
    public static HttpEntity getMultiDefaultArrayEntity(byte[] bytes) {
        if (bytes.length == 0){
            throw new IllegalArgumentException("文件错误！");
        }
        EntityBuilder builder = EntityBuilder.create();
        builder.setBinary(bytes);
        return builder.build();
    }

    public static String getTime(){
        LocalDateTime localDateTime = LocalDateTime.now();
        return localDateTime.format(DateTimeFormatter.ofPattern("yyyy_MM_dd_HH_mm_ss"));
    }


}
