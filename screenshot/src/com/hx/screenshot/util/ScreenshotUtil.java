package com.hx.screenshot.util;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/2 09:24
 * @Description: 截屏工具
 */
public class ScreenshotUtil {

    /**
     * 默认格式
     */
    private static final String DEFAULT_IMAGE_FORMAT = "jpg";

    /**
     * 默认时间格式
     */
    private static final String DEFAULT_TIME_FORMAT = "yyyy_MM_dd_HH_mm_ss";



    /**
     * 对屏幕进行拍照
     * @param filePath
     * @return
     */
    public static String snapShot(String filePath) {
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            //拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new
                    Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
            //根据文件前缀变量和文件格式变量，自动生成文件名
            File dir = new File(filePath);
            if (!dir.exists()){
                dir.mkdirs();
            }
            String name = filePath+getTimeFormat()+"."+DEFAULT_IMAGE_FORMAT;
            File file = new File(name);
            System.out.print("Save File "+name);
            //将screenshot对象写入图像文件
            ImageIO.write(screenshot, DEFAULT_IMAGE_FORMAT, file);
            System.out.print("..Finished!\n");
            return name;
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 获取时间
     * @return
     */
    public static  String getTimeFormat(){
        LocalDateTime localDateTime = LocalDateTime.now();
        String timeTmp = localDateTime.format(DateTimeFormatter.ofPattern(DEFAULT_TIME_FORMAT));
        return timeTmp;
    }
    /**
     *  获取截屏字符
     * @return
     */
    public static  byte[] getByteToImage(){
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
        try {
            //拷贝屏幕到一个BufferedImage对象screenshot
            BufferedImage screenshot = (new Robot()).createScreenCapture(new
                    Rectangle(0, 0, (int) d.getWidth(), (int) d.getHeight()));
            //压缩图片
//            screenshot = Thumbnails.of(screenshot).scale(1f).outputQuality(0.5).asBufferedImage();
            ImageIO.write(screenshot,DEFAULT_IMAGE_FORMAT,out);
            return out.toByteArray();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
