package com.common.fileUpload;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.ObjectMetadata;
import com.common.excption.AuthExceptionConstants;
import com.common.excption.BaseException;
import com.common.util.PropertiesUtils;
import com.common.util.UUIDHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/10 15:58
 * @Description:
 */
public class OssUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(OssUtil.class);

    public final static String errorMessage = "不受支持的图片格式类型";

    private static final String KEY_P_ACCESS_ID = "ACCESS_ID";
    private static final String KEY_P_ACCESS_KEY = "ACCESS_KEY";
    private static final String KEY_P_EXTERNAL_ENDPOINT = "EXTERNAL_ENDPOINT";
    private static final String KEY_P_IMG_OSS_BUCKET_NAME = "IMG_OSS_BUCKET_NAME";
    private static final String IMG_OSS_FILE_PATH = "IMG_OSS_FILE_PATH";


    private static String access_id;
    private static String access_key;
    private static String external_endpoint;
    private static String img_oss_bucket_name;

    private static String img_oss_file_path;

    static {
        access_id = PropertiesUtils.getValue(KEY_P_ACCESS_ID, "aliyunoss.properties");
        access_key = PropertiesUtils.getValue(KEY_P_ACCESS_KEY, "aliyunoss.properties");
        external_endpoint = PropertiesUtils.getValue(KEY_P_EXTERNAL_ENDPOINT, "aliyunoss.properties");
        img_oss_bucket_name = PropertiesUtils.getValue(KEY_P_IMG_OSS_BUCKET_NAME, "aliyunoss.properties");
        img_oss_file_path = PropertiesUtils.getValue(IMG_OSS_FILE_PATH , "aliyunoss.properties");
    }

    private static OssUtil ossUtil;


    //OSS对象
    private static OSSClient client;

    /**
     * 单例
     */
    private OssUtil() {
    }





    /**
     * 初始方法
     * @return
     */
    public static OssUtil getOssUtil(){
        if (ossUtil == null)
            ossUtil = new OssUtil();
        if (ossUtil.client == null)
            ossUtil.client = new OSSClient(external_endpoint,access_id, access_key);
        return ossUtil;
    }


    /**
     * 上传文件
     * @param file Spring 文件
     * @return
     */
    public String saveImgMultipartFile(MultipartFile file){
        String name = file.getOriginalFilename();
        String suffix = name.substring(name.lastIndexOf(".") + 1).toUpperCase();
        if (!suffix.matches("^[(JPG)|(PNG)|(GIF)]+$")){
            return null;
        }
        String filename = img_oss_file_path + UUIDHelper.getUUID() + name.substring(file.getOriginalFilename().lastIndexOf('.'));
        return saveImgMultipartFile(filename,file);
    }





    /**
     *  上传文件
     * @param filename 带后缀名文件
     * @param file 文件
     * @throws IOException
     */
    public String saveImgMultipartFile(String filename,MultipartFile file){
        String contentType = file.getContentType();
        ObjectMetadata objectMeta = new ObjectMetadata();
        objectMeta.setContentLength(file.getSize());
        objectMeta.setContentType(contentType.toLowerCase());
        InputStream input = null;
        try {
            input = file.getInputStream();
            client.putObject(img_oss_bucket_name, filename, input, objectMeta);
        } catch (IOException e) {
           LOGGER.error("读取文件失败:",e.getMessage());
           return null;
        } catch (OSSException | ClientException e){
            LOGGER.error("上传失败:",e.getMessage());
            return null;
        }finally {
            try {
                input.close();
            } catch (IOException e) {
                LOGGER.error("关闭文件流失败:",e.getMessage());
            }
        }
        return external_endpoint.replace("http://", "https://"+img_oss_bucket_name+".")+ "/" + filename;
    }


    /**
     * 删除
     * @param url url
     * @return
     */
    public Boolean deleteImg(String url){
        String objectName = url.substring(url.lastIndexOf("/")+1);
        try{
            client.deleteObject(img_oss_bucket_name,objectName);
        }catch (OSSException | ClientException e){
            LOGGER.error("删除失败:",e.getMessage());
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }


    /**
     *
     * @param file
     * @return
     */
    public static String getFilePath(MultipartFile file){
        //组装文件名 - 信息
        String OriginalFilename = file.getOriginalFilename();
        String string = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));//.png

        String fileNames = UUIDHelper.createUUId();

        String pathTo = fileNames + fileNames + string;

        return pathTo;
    }


}


