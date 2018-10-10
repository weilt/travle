package com.hx.api.aliyun.file.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.hx.api.aliyun.file.type.NotSupportedImgException;




/**
 * 异步服务
 */
@Component
@Scope("singleton")
public class AsynAOSSService {

	private static Logger log = LoggerFactory.getLogger(AsynAOSSService.class);
	
    @Resource
    private OSSService ossService;

    /**
     *
     * @param file
     * @param url_r_key   必须带图片格式后缀
     * @return
     * @throws IOException
     */
    public void saveFile(MultipartFile file, String url_r_key) throws IOException {
        ossService.saveImgAsyn(file, url_r_key);
    }

    /**
     * 删除文件
     * @param url_r_key
     * @throws IOException
     */
    public void deleteFile(String url_r_key) throws IOException{
         ossService.deleteImgSync(url_r_key);
    }

    //-----------------------------------------------------------


    public void saveFile(File file, String url_r_key){
        ossService.saveFileAsyn(file,url_r_key);
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
        ossService.breakPointSaveImgMultipartFile(r_key,localFilePath,partSize,taskNum);
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
    @SuppressWarnings("all")
	public void base64UpFile(String filedata,String key, String basePath, List<String> file_name) throws IOException, NotSupportedImgException {
    	ossService.saveBase64File(filedata,key, basePath, file_name);
    }
}
