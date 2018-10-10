package com.common.util;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.ssl.TrustStrategy;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/6 14:37
 * @Description:
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class); // 日志记录

    private static RequestConfig requestConfig = null;

    private static final String  DEFAULT_CHARSET = "utf-8";

    static {
        // 设置请求和传输超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(2000).setConnectTimeout(2000).build();
    }


    /**
     *  https请求
     * @return
     */
    public static CloseableHttpClient createSSLClientDefault(){
        try {
            SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
                //信任所有
                public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    return true;
                }
            }).build();
            SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext);
            return HttpClients.custom().setSSLSocketFactory(sslsf).build();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return  HttpClients.createDefault();
    }


    /**
     * post请求传输json参数
     * @param url  url地址
     * @param jsonParam 参数
     * @return
     */
    public static JSONObject httpPost(String url, JSONObject jsonParam) {
        CloseableHttpClient httpClient;
        if(!url.startsWith("https:")){
            httpClient = HttpClients.createDefault();
        }else {
            httpClient = createSSLClientDefault();
        }
        // post请求返回结果
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        // 设置请求和传输超时时间
        httpPost.setConfig(requestConfig);
        try {
            if (null != jsonParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(jsonParam.toString(), DEFAULT_CHARSET);
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/json");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            jsonResult = getResult(result);
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        } finally {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }

    /**
     * post请求传输String参数 例如：name=Jack&sex=1&type=2
     * Content-type:application/x-www-form-urlencoded
     * @param url            url地址
     * @param strParam       参数
     * @return
     */
    public static JSONObject httpPost(String url, String strParam) {
        CloseableHttpClient httpClient;
        if(!url.startsWith("https:")){
            httpClient = HttpClients.createDefault();
        }else {
            httpClient = createSSLClientDefault();
        }
        JSONObject jsonResult = null;
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        try {
            if (null != strParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(strParam, DEFAULT_CHARSET);
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            // 请求发送成功，并得到响应
            jsonResult = getResult(result);
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        } finally {
            httpPost.releaseConnection();
        }
        return jsonResult;
    }

    /**
     *  post请求返回String
     * @param url url
     * @param strParam 请求参数
     * @return string
     */
    public static String httpost(String url, String strParam) {
        CloseableHttpClient httpClient;
        if(!url.startsWith("https:")){
            httpClient = HttpClients.createDefault();
        }else {
            httpClient = createSSLClientDefault();
        }
        String resultStr = "";
        HttpPost httpPost = new HttpPost(url);
        httpPost.setConfig(requestConfig);
        try {
            if (null != strParam) {
                // 解决中文乱码问题
                StringEntity entity = new StringEntity(strParam, DEFAULT_CHARSET);
                entity.setContentEncoding("UTF-8");
                entity.setContentType("application/x-www-form-urlencoded");
                httpPost.setEntity(entity);
            }
            CloseableHttpResponse result = httpClient.execute(httpPost);
            resultStr = EntityUtils.toString(result.getEntity(), DEFAULT_CHARSET);
            // 请求发送成功，并得到响应
        } catch (IOException e) {
            logger.error("post请求提交失败:" + url, e);
        } finally {
            httpPost.releaseConnection();
        }
        return resultStr;
    }


    /**
     *  解析请求成功数据
     * @param result
     * @return
     */
    private static JSONObject getResult (CloseableHttpResponse result) {
        JSONObject jsonResult = null;
        // 请求发送成功，并得到响应
        if (result.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            try {
                // 读取服务器返回过来的json字符串数据
                String str = EntityUtils.toString(result.getEntity(), DEFAULT_CHARSET);
                // 把json字符串转换成json对象
                jsonResult = JSONObject.parseObject(str);
            } catch (Exception e) {
                logger.error("post请求提交失败:" , e);
            }
        }else {
            logger.error("post请求提交失败：",result.getStatusLine().getStatusCode());
        }
        return jsonResult;
    }


    /**
     * 发送get请求
     * @param url 路径
     * @return
     */
    public static JSONObject httpGet(String url) {
        // get请求返回结果
        JSONObject jsonResult = null;
        CloseableHttpClient httpClient;
        if(!url.startsWith("https:")){
            httpClient = HttpClients.createDefault();
        }else {
            httpClient = createSSLClientDefault();
        }
        // 发送get请求
        HttpGet request = new HttpGet(url);
        request.setConfig(requestConfig);
        try {
            CloseableHttpResponse response = httpClient.execute(request);

            // 请求发送成功，并得到响应
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                // 读取服务器返回过来的json字符串数据
                HttpEntity entity = response.getEntity();
                String strResult = EntityUtils.toString(entity, DEFAULT_CHARSET);
                // 把json字符串转换成json对象
                jsonResult = JSONObject.parseObject(strResult);
            } else {
                logger.error("get请求提交失败:" + url);
            }
        } catch (IOException e) {
            logger.error("get请求提交失败:" + url, e);
        } finally {
            request.releaseConnection();
        }
        return jsonResult;
    }
}
