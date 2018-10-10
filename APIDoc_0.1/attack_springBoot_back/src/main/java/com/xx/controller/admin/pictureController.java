package com.xx.controller.admin;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xx.util.util.FTPUtil;
import com.xx.util.util.IDUtils;
import com.xx.util.util.JsonUtils;


@RestController
public class pictureController {
	
	//获取ip地址  
    @Value("${FTP_ADDRESS}")  
    private String FTP_ADDRESS;  
    //用户名  
    @Value("${FTP_USERNAME}")  
    private String FTP_USERNAME;  
    //密码  
    @Value("${FTP_PASSWORD}")  
    private String FTP_PASSWORD;  
    //基本路径  
    @Value("${FTP_BASEPATH}")  
    private String FTP_BASEPATH;
    //下载地址地基础url  
    @Value("${IMAGE_BASE_URL}")  
    private String IMAGE_BASE_URL;  
	/**
	 * 上传图片
	 * @param uploadFile 图片数据
	 * @return
	 */
	@RequestMapping("/pic/upload")
	public String pic_upload(MultipartFile uploadFile) {
		
		Map<String,Object> map = new HashMap<>();  
		String json;
        try {  
            // 生成一个文件名  
            // 获取旧的名字  
            String oldName = uploadFile.getOriginalFilename();  
            String newName = IDUtils.genImageName();  
            //新名字  
            newName = newName + oldName.substring(oldName.lastIndexOf("."));  
            //上传的路径  
            
            SimpleDateFormat sdf=new SimpleDateFormat("/yyyy/mm/dd");
            String imagePath = sdf.format(new Date());
            //端口号  
            System.out.println(FTP_BASEPATH);  
            //调用方法，上传文件  
            boolean result = FTPUtil.uploadFile(FTP_ADDRESS,  
                    FTP_USERNAME, FTP_PASSWORD, FTP_BASEPATH, imagePath,  
                    newName, uploadFile.getInputStream()); 
            System.out.println(result);
            //判断是否上传成功  
            if (!result) {  
                map.put("error", 1);  
                map.put("message", "上传失败"); 
                json = JsonUtils.objectToJson(map);
                return json;
            }  
            map.put("error", 0);  
            map.put("url", IMAGE_BASE_URL + imagePath +"/"+ newName);  
            json = JsonUtils.objectToJson(map);
            System.out.println(map.get("url"));
            return json;
  
        } catch (IOException e) {  
            map.put("error", 1);  
            map.put("message", "上传发生异常");
            json = JsonUtils.objectToJson(map);
            return json;
        }  

	}
}
