//package com.hx.api.aliyun.file.oss;
//
//import com.hx.core.utils.FileUtils;
//import com.sun.image.codec.jpeg.JPEGCodec;
//import com.sun.image.codec.jpeg.JPEGImageEncoder;
//
//
//import org.apache.commons.fileupload.disk.DiskFileItem;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
//import javax.imageio.ImageIO;
//import javax.servlet.http.HttpServletRequest;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.UUID;
//
///**
// * Created by lyj on 2017/2/28.
// * 图片压缩
// */
//public class ImageCompress {
//
//    private final static Logger log = LoggerFactory.getLogger(ImageCompress.class);
//
//    public final static String realPath = "test/";
//    private Image img;  //图片文件
//    private int width;  //宽度
//    private int height; //高度
//    private File file;  // 传递的文件
//
//    /**
//     * 压缩图片的操作
//     * @param multipartFile
//     * @param w
//     * @param h
//     * @param request
//     * @return
//     */
//    public File compressIamge(MultipartFile multipartFile,int w, int h,HttpServletRequest request){
//        String path = request.getSession().getServletContext().getRealPath("/")+ ImageCompress.realPath+ UUID.randomUUID()+".jpg";
//        ImageCompress imageCompress = new ImageCompress(multipartFile,request);
//        imageCompress.resizeFix(w,h,path);
//        File nfile = new File(path);
//        return nfile;
//    }
//
//
//    /**
//     * 创建一个文件夹
//     * @param request
//     */
//    public void  createDir(HttpServletRequest request){
//        File realFile = new File(request.getSession().getServletContext().getRealPath("/")+realPath);
//        if(!realFile.exists()){
//            realFile.mkdir();//创建一个文件
//        }else{
//            FileUtils.doDeleteEmptyDir(realFile);
//        }
//    }
//
//
//    /**
//     * 构造
//     * @param fileName 文件名
//     */
//    public ImageCompress(String fileName, HttpServletRequest request) {
//        try {
//            createDir(request);
//            file = new File(fileName);// 读入文件
//            img = ImageIO.read(file);      // 构造Image对象
//            width = img.getWidth(null);    // 得到源图宽
//            height = img.getHeight(null);  // 得到源图长
//        }catch (Exception e){
//            log.error("文件初始化失败",e);
//        }
//    }
//
//    /**
//     * 构造
//     * @param inputFile 文件
//     */
//    public ImageCompress(File inputFile, HttpServletRequest request) {
//        try {
//            createDir(request);
//            file = inputFile;
//            img = ImageIO.read(file);      // 构造Image对象
//            width = img.getWidth(null);    // 得到源图宽
//            height = img.getHeight(null);  // 得到源图长
//        }catch (Exception e){
//            log.error("文件初始化失败",e);
//        }
//    }
//
//    /**
//     * 构造
//     * @param multipartFile spring 的文件类型
//     */
//    public ImageCompress(MultipartFile multipartFile, HttpServletRequest request){
//        try {
//            createDir(request);
//            CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
//            DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//            file = fi.getStoreLocation();
//            img = ImageIO.read(file);      // 构造Image对象
//            width = img.getWidth(null);    // 得到源图宽
//            height = img.getHeight(null);  // 得到源图长
//        }catch (Exception e){
//            log.error("文件初始化失败",e);
//        }
//    }
//
//
//    /**
//     * 按照宽度还是高度进行压缩
//     * @param w int 最大宽度
//     * @param h int 最大高度
//     */
//    public void resizeFix(int w, int h,String filePath){
//        if (width / height > w / h) {
//            resizeByWidth(w,filePath);
//        } else {
//            resizeByHeight(h,filePath);
//        }
//    }
//    /**
//     * 以宽度为基准，等比例放缩图片
//     * @param w int 新宽度
//     */
//    public void resizeByWidth(int w,String filePath){
//        int h = (int) (height * w / width);
//        compressImg(w, h,filePath);
//    }
//    /**
//     * 以高度为基准，等比例缩放图片
//     * @param h int 新高度
//     */
//    public void resizeByHeight(int h,String filePath){
//        int w = (int) (width * h / height);
//        compressImg(w, h,filePath);
//    }
//
//    /**
//     * 压缩图片到本地的缓存
//     * @param w
//     * @param h
//     * @param filePath
//     * @return
//     */
//    public void compressImg(int w, int h,String filePath){
//        FileOutputStream out = null;
//        try {
//            BufferedImage image = new BufferedImage(w, h,BufferedImage.TYPE_INT_RGB );
//            image.getGraphics().drawImage(img, 0, 0, w, h, null); // 绘制缩小后的图
//            File destFile = new File(filePath);
//            out = new FileOutputStream(destFile); // 输出到文件流
//            // 可以正常实现bmp、png、gif转jpg
//            JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
//            encoder.encode(image); // JPEG编码
//            if(file.exists()){
//                file.delete();
//            }
//        }catch (Exception ex){
//            log.error("压缩图片失败",ex);
//            try {
//                if(out != null)
//                    out.close();
//            } catch (IOException e) {
//                log.error("关闭流失败",e);
//                e.printStackTrace();
//            }
//        }finally {
//            try {
//                if(out != null)
//                     out.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//                log.error("关闭流失败",e);
//            }
//        }
//    }
//
//    /**
//     * 删除文件
//     * @param file
//     */
//    public void deleteFile(File file){
//        //System.out.println(file.exists()+"判断文件是否存在");
//        if(file.exists()){
//            file.getAbsoluteFile().delete();
//        }
//    }
//
//}
