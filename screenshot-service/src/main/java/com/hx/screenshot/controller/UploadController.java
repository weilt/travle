package com.hx.screenshot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.Arrays;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/3 16:33
 * @Description:
 */
@RestController
public class UploadController {

    @Value("${file.path}")
    private String filePath;

    @PostMapping("/upload")
    public String upload(HttpServletRequest request){
        String mac = request.getHeader("MAC");
        String time = request.getHeader("TIME");
        int length = request.getContentLength();//获取请求参数长度。
        byte[] bytes = new byte[length];//定义数组，长度为请求参数的长度
        try {
            DataInputStream dis = new DataInputStream(request.getInputStream());//获取请求内容，转成数据输入流
            int readcount = 0;//定义输入流读取数
            while(readcount < length){
                int index= dis.read(bytes,readcount,length); //读取输入流，放入bytes数组，返回每次读取的数量
                readcount += index; //下一次的读取开始从readcount开始
            }
            byte2FileSave(bytes,filePath +File.separator+ mac,time + ".jpg");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "OK";
    }


    /**
     * 多文件上传
     * @param request
     * @param files
     * @return
     */
    @PostMapping("/uploads")
    public String uploads(HttpServletRequest request, @RequestParam("files") MultipartFile[] files){
        long begin = System.currentTimeMillis();
        String mac = request.getHeader("MAC");
        Arrays.asList(files).parallelStream().forEach(f ->
            saveFile(filePath + File.separator + mac,f)
        );
        System.out.println("存储文件花销时间："+ (System.currentTimeMillis() - begin));
        return "OK";
    }


    /**
     *  保存文件
     * @param filePath
     * @param file
     */
    public static void saveFile(String filePath, MultipartFile file){
        File dir = new File(filePath);
        if (!dir.exists() && !dir.isDirectory()){
            dir.mkdirs();
        }
        String fileName = file.getOriginalFilename();
        //按天切分文件
        String dayPath = fileName.split("/.")[0].substring(0,10).replaceAll("_","-");
        File dayFile = new File(filePath + File.separator + dayPath);
        if (!dayFile.exists() && !dayFile.isDirectory()){
            dayFile.mkdirs();
        }
        File copFile = new File(filePath + File.separator +dayPath+ File.separator + fileName);
        try {
            file.transferTo(copFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     *  byte[] 转换为文件
     * @param buf
     * @param filePath
     * @param fileName
     */
    public static void byte2FileSave(byte[] buf, String filePath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        try {
            File dir = new File(filePath);
            //判断文件夹是否存在
            if (!dir.exists() && !dir.isDirectory()) {
                dir.mkdirs();
            }
            //按天切分文件
            String dayPath = fileName.split("/.")[0].substring(0,10).replaceAll("_","-");
            File dayFile = new File(filePath + File.separator + dayPath);
            if (!dayFile.exists() && !dayFile.isDirectory()){
                dayFile.mkdirs();
            }
            File file = new File(filePath + File.separator +dayPath+ File.separator + fileName);
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
}
