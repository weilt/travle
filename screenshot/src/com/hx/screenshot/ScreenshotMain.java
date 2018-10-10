package com.hx.screenshot;

import com.hx.screenshot.util.HttpClientUtil;
import com.hx.screenshot.util.MACAddressUtil;
import com.hx.screenshot.util.ScreenshotUtil;

import java.io.*;
import java.util.*;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/2 09:27
 * @Description:
 */
public class ScreenshotMain {

    /**
     * 秒
     */
    private static final long SECOND_LONG = 1000;

    /**
     * 文件注释
     */
    private static final char ANNOTATION = '#';


    /**
     * 存数据
     */
    private static Map<String,byte[]> fileMap = new HashMap<>();

    /**
     * 调用线程
     * @param args
     */
    public static void main(String[] args) {
        String rootPath = System.getProperty("user.dir");
//        System.out.println("ROOTPATH = "+rootPath);
        String filePath = rootPath.substring(0,rootPath.lastIndexOf(File.separator));
        //获取根目录
//        Map<String,String> map = getConfig(rootPath+File.separator+"config/screenshot.config");//打成jar包时直接用rootPath
        Map<String,String> map = getConfig(filePath+File.separator+"config/screenshot.config");
        startTimer(map.get("host") == null ? "http://127.0.0.1:8080/uploads" : map.get("host")
                ,map.get("localPath") == null ? rootPath : map.get("localPath")
                ,map.get("delay") == null? 1 : Long.parseLong(map.get("delay"))
                ,map.get("period") == null? 20 : Long.parseLong(map.get("period"))
                ,map.get("fileCount") == null ? 10 : Integer.parseInt(map.get("fileCount")));
    }

    /**
     * 定时任务
     * @param url 请求服务地址
     * @param localPath 服务器出错是本地保存文件地址
     * @param delay 该任务延迟delay秒后执行
     * @param period 然后会每隔period秒重复执行
     */
    public static void startTimer(String url,String localPath,long delay,long period, int fileCount){
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //获取截屏
                byte[] imageByte = ScreenshotUtil.getByteToImage();
                String mac = MACAddressUtil.getMACAddress();
                fileMap.put(ScreenshotUtil.getTimeFormat(),imageByte);
                if (fileMap.size() >= fileCount){
                    // 多文件上传
                    HttpClientUtil.postUpload(url,mac,fileMap);
                    fileMap.clear();
                }
//                HttpClientUtil.postUpload(url,mac,imageByte);
            }
        };
        Timer timer = new Timer();
        timer.schedule(task, delay * SECOND_LONG ,period * SECOND_LONG);
    }

    /**
     *  byte[] 转换为文件
     * @param buf
     * @param filePath
     * @param fileName
     */
    public static void byte2File(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(filePath);
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs();
            }
            file = new File(filePath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(buf);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取txt文件的内容
     * @param filePath 想要读取的文件对象
     * @return 返回文件内容
     */
    public static Map<String,String> getConfig(String filePath){
        Map<String,String> resultMap = new HashMap<>();
        try{
            File file = new File(filePath);
            if (!file.exists()){
                return resultMap;
            }
            BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
            String s = null;
            while((s = br.readLine())!=null){//使用readLine方法，一次读一行
                //去掉注释
                if (ANNOTATION == s.charAt(0)){
                    continue;
                }
                String[] config = s.split("=");
                if (config.length == 2){
                    resultMap.put(config[0].trim(),config[1].trim());
                }

            }
            br.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }
}
