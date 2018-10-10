//package com.hx.api.aliyun.file.oss;
//import java.io.*;
//import java.awt.*;
//import java.awt.image.BufferedImage;
//import javax.imageio.IIOImage;
//import javax.imageio.ImageIO;
//import javax.imageio.ImageTypeSpecifier;
//import javax.imageio.metadata.IIOMetadata;
//import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
//import javax.imageio.stream.ImageOutputStream;
//import javax.servlet.http.HttpServletRequest;
//
//import com.hx.core.utils.FileUtils;
//import com.sun.imageio.plugins.jpeg.*;
//
//
//import org.apache.commons.fileupload.disk.DiskFileItem;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.web.multipart.MultipartFile;
//import org.springframework.web.multipart.commons.CommonsMultipartFile;
//
///**
// * 图片缩放工具类
// */
//public class ImgCompressUtil {
//
//    /**
//     * log4j
//     */
//    private final static Logger logger = LoggerFactory.getLogger(ImgCompressUtil.class);
//
//    private final static String realPath = "test/";
//
//    /**
//     * 创建一个文件夹
//     * @param request
//     */
//    public static void  createDir(HttpServletRequest request){
//        File realFile = new File(request.getSession().getServletContext().getRealPath("/")+realPath);
//        if(!realFile.exists()){
//            realFile.mkdir();//创建一个文件
//        }else{
//            FileUtils.doDeleteEmptyDir(realFile);
//        }
//    }
//
//    /**
//     * 外部调用压缩图片
//     * @param multipartFile
//     * @param request
//     * @param w
//     * @param h
//     * @param JPEGcompression
//     * @param filePath
//     */
//    public static void imageCompress(MultipartFile multipartFile,HttpServletRequest request,
//                                     int w, int h, float JPEGcompression,String filePath){
//        //创建一个文件
//        createDir(request);
//        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
//        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//        //获取到本利的文件
//        File file = fi.getStoreLocation();
//        imgCompress(file,w,h,JPEGcompression,filePath);
//        if(file.exists()){
//            file.delete();
//        }
//    }
//
//    /**
//     * 外部调用压缩图片(放大或缩小比例来实现)
//     * @param multipartFile
//     * @param request
//     * @param JPEGcompression
//     * @param filePath
//     */
//    public static void imageCompress(MultipartFile multipartFile,HttpServletRequest request,
//                                     float ratios, float JPEGcompression,String filePath){
//        //创建一个文件
//        createDir(request);
//        CommonsMultipartFile cf = (CommonsMultipartFile) multipartFile;
//        DiskFileItem fi = (DiskFileItem) cf.getFileItem();
//        //获取到本利的文件
//        File file = fi.getStoreLocation();
//        imgCompress(file,ratios,JPEGcompression,filePath);
//        if(file.exists()){
//            file.delete();
//        }
//    }
//
//
//    /**
//     * 按比例缩放图片
//     * @param file 文件
//     * @param ratios 缩放比例系数
//     * @param JPEGcompression 缩放质量
//     * @param filePath 文件的保存路径
//     * @return
//     */
//    public static String imgCompress(File file,float ratios, float JPEGcompression,String filePath){
//        try {
//            BufferedImage bufferedImage =  ImageIO.read(file);
//            int new_w = bufferedImage.getWidth(null);
//            int new_h = bufferedImage.getHeight(null);
//
//            //按比例进行缩小或者方法
//            if(ratios > 0 && ratios < 1.5){
//                new_w = (int) (new_w * ratios);
//                new_h = (int) (new_h * ratios);
//            }
//
//            BufferedImage image_to_save = new BufferedImage(new_w, new_h,
//                    bufferedImage.getType());
//            image_to_save.getGraphics().drawImage(
//                    bufferedImage.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
//                    0, null);
//            FileOutputStream fos = new FileOutputStream(filePath); // 输出到文件流
//            saveAsJPEG(image_to_save, JPEGcompression, fos);
//            //关闭输出流
//            fos.close();
//        }catch (Exception ex){
//            logger.error("图片处理流失败",ex);
//        }
//        return filePath;
//    }
//
//
//    /**
//     * 压缩图片
//     * @param file  传入的文件对象
//     * @param w  宽
//     * @param h  高
//     * @param JPEGcompression 压缩的分辨率
//     * @param filePath 保存的图片路径
//     */
//    public static String imgCompress(File file,int w, int h, float JPEGcompression,String filePath){
//        try {
//            BufferedImage bufferedImage =  ImageIO.read(file);
//            //计算等比例的缩放
//            int width = bufferedImage.getWidth(null);
//            int height = bufferedImage.getHeight(null);
//
//            int new_w = width;
//            int new_h = height;
//
//            if (width / height > w / h) {
//                new_h = (int) (height * w / width);
//            } else {
//                new_w = (int) (width * h / height);
//            }
//
//            BufferedImage image_to_save = new BufferedImage(new_w, new_h,
//                    bufferedImage.getType());
//            image_to_save.getGraphics().drawImage(
//                    bufferedImage.getScaledInstance(new_w, new_h, Image.SCALE_SMOOTH), 0,
//                    0, null);
//
//            FileOutputStream fos = new FileOutputStream(filePath); // 输出到文件流
//            saveAsJPEG(image_to_save, JPEGcompression, fos);
//            //关闭输出流
//            fos.close();
//        }catch (Exception ex){
//            logger.error("图片处理流失败",ex);
//        }
//        return filePath;
//    }
//
//
//
//
//
//
//    /**
//     * 以JPEG编码保存图片
//     * @param image_to_save 要处理的图像图片
//     * @param JPEGcompression 压缩比
//     * @param fos 文件输出流
//     * @throws IOException
//     */
//    public static void saveAsJPEG(BufferedImage image_to_save,
//                                  float JPEGcompression, FileOutputStream fos) throws IOException {
//
//        JPEGImageWriter imageWriter = (JPEGImageWriter) ImageIO
//                .getImageWritersBySuffix("jpg").next();
//        ImageOutputStream ios = ImageIO.createImageOutputStream(fos);
//        imageWriter.setOutput(ios);
//        IIOMetadata imageMetaData = imageWriter.getDefaultImageMetadata(
//                new ImageTypeSpecifier(image_to_save), null);
//
//        if (JPEGcompression >= 0 && JPEGcompression <= 1f) {
//            JPEGImageWriteParam jpegParams = (JPEGImageWriteParam) imageWriter
//                    .getDefaultWriteParam();
//            jpegParams.setCompressionMode(JPEGImageWriteParam.MODE_EXPLICIT);
//            jpegParams.setCompressionQuality(JPEGcompression);
//
//        }
//        imageWriter.write(imageMetaData,
//                new IIOImage(image_to_save, null, null), null);
//        ios.close();
//        imageWriter.dispose();
//    }
//
//
//    /**
//     * 删除文件
//     * @param file
//     */
//    public static void deleteFile(File file){
//        if(file.exists()){
//            file.getAbsoluteFile().delete();
//        }
//    }
//}