package com.hx.admin.file.service;



import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hx.admin.file.oss.AliyunImgOperate;
import com.hx.admin.file.oss.FileOperate;
import com.hx.admin.file.type.NotSupportedImgException;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 单例模式，即scope="singleton"。另外scope还有prototype、request、session、global session作用域。
 */

@Component
@Scope("singleton")
public class OSSService {

    private final static Logger logger = LoggerFactory.getLogger(OSSService.class);

    private final static FileOperate fileOperate = AliyunImgOperate.getAliyunImgOperate();


    /**
     * 异步保存 系统自动判定图片后缀
     * @param imgfile  文件信息
     * @param url_r_key  保存的地址字符串（相应OSS服务）必须带图片格式后缀
     * @throws IOException
     */
    @Async
    public void saveImgAsyn(MultipartFile imgfile,String url_r_key) throws IOException {
        logger.debug("进入 OSSService saveImgAsyncs  @Async");
//		String name =file.getOriginalFilename(); // 原始名称
//		UUID uuid = UUID.randomUUID();

        try {
            fileOperate.saveImgMultipartFile(url_r_key, imgfile);
        } catch (Exception e) {
            logger.warn("saveImgAsyn----url_r_key:{}",url_r_key);
            logger.warn(e.getMessage(), e);
            throw new IOException(e);
        }

    }

    /**
     * 同步保存，系统自动判定图片后缀
     * @param imgfile
     * @param url_r_key   保存的地址字符串（相应OSS服务）必须带图片格式后缀
     * @return
     * @throws IOException
     */
    public void saveImgSync(MultipartFile imgfile,String url_r_key) throws IOException{
        logger.debug("进入 OSSService saveImgSync");
        try {
            fileOperate.saveImgMultipartFile(url_r_key, imgfile);
        } catch (Exception e) {
            logger.warn("saveImgSync----url_r_key:{}",url_r_key);
            logger.warn(e.getMessage(), e);
            throw new IOException(e);
        }
        logger.debug("执行到尾 saveImgSync");

    }

    public void deleteImgSync(String url_r_key) throws IOException{
        try {
            fileOperate.deleteImg(url_r_key);
        } catch (Exception e) {
            throw new IOException(e);
        }

    }

    /**
     * 加上图片后缀
     * @param file
     * @param url_key 不能带图片后缀的key
     * @return 得到带图片后缀的key
     * @throws IOException
     */
    public String getImgFullkey(MultipartFile file, String url_key) throws IOException {
        try {
            String url_r_key = url_key + "." + fileOperate.getImgType(file.getInputStream());
            return url_r_key;
        } catch (Exception e) {
            logger.warn("getImgFullkey----rkey---key:{}",url_key);
            logger.warn(e.getMessage(), e);
            throw new IOException(e);
        }
    }

    /**
     * 同步上传一个FILE的
     * @param file
     * @param url_r_key
     */
    public void saveFileAsyn(File file, String url_r_key) {
        logger.debug("进入 OSSService saveImgSync");
        try {
            fileOperate.saveFileAsyn(file,url_r_key);
        } catch (Exception e) {
            logger.warn("saveImgSync----url_r_key:{}",url_r_key);
            logger.warn(e.getMessage(), e);
        }
        logger.debug("执行到尾 saveImgSync");
    }

    /**
     * 断点上传(对大文件上传可以体现出其优势)
     * @param r_key
     * @param localFilePath
     * @param partSize
     * @param taskNum
     * @throws Throwable
     */
    public void breakPointSaveImgMultipartFile(String r_key,String localFilePath,Long partSize,Integer taskNum) throws Throwable {
        fileOperate.breakPointSaveImgMultipartFile(r_key,localFilePath,partSize,taskNum);
    }
    
    /**
	 * base64转码上传 用于前端JS（localResizeIMG）压缩图片上传
     * @param filedata 上传的文件BASE64编码
     * @param key 上传到阿里云服务器的文件路径公共部分
     * @param basePath 临时文件保存的上传路径公共部分
     * @param file_name 上传图片的名称集合
     * @throws IOException
	 * @throws NotSupportedImgException 
	 */
    public void saveBase64File(String filedata,String key, String basePath, List<String> file_name) throws IOException, NotSupportedImgException {
    	fileOperate.saveBase64File(filedata,key, basePath, file_name);
    }
}
