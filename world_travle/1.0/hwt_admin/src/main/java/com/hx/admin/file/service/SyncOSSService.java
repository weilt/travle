package com.hx.admin.file.service;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 同步服务
 */
@Component
@Scope("singleton")
public class SyncOSSService {

    @Resource
    private OSSService ossService;

    /**
     *
     * @param file 上传的文件
     * @param url_r_key 如果过是图片请直接用.jpg 来制作，如果是视频或者音频，原文件是什么格式，那么存储的就是什么格式
     * @return
     * @throws IOException
     */
    public void saveFile(MultipartFile file, String url_r_key) throws IOException{
        ossService.saveImgSync(file, url_r_key);
    }

    /**
     *
     * @param url_r_key
     * @throws IOException
     */
    public void deleteFile(String url_r_key) throws IOException{
        ossService.deleteImgSync(url_r_key);
    }
}
