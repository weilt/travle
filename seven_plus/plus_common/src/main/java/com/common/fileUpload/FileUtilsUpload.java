package com.common.fileUpload;


import com.common.util.FileUtils;
import com.common.util.PropertiesUtils;
import com.common.util.UUIDHelper;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 上传文件 逻辑处理信息
 * @author JIAO_LIU_BABA
 */
public class FileUtilsUpload {

	
	/** 
	 * 保存在指定位置中
	 * 上传文件
	 * @param uploadFile - uploadFile文件流
	 * @return string - 绝对路径
	 */
	public static String upload_json(MultipartFile uploadFile){
		String url = null;
		if(uploadFile != null){
			try {
				String pathTo = pathTo(uploadFile);
				//存放地址
				String filePath = PropertiesUtils.getValue("web.upload-path", "application.properties");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				String dateTi = sdf.format(new Date()) + "/";		
				String fileReqUrlname = PropertiesUtils.getValue("xx.file.request.urlname","application.properties");
				
				filePath += dateTi;
				fileReqUrlname += dateTi;
				FileUtils.uploadFile(filePath,uploadFile,pathTo);
				//绝对路径信息
				url = fileReqUrlname + pathTo;
			} catch (Exception e) {
				e.printStackTrace();
				url = null;
			}
		}
		return url;
	}
	
	
	/**
	 * 获取 对象名
	 */
	public static String pathTo(MultipartFile uploadFile) {
		
		//组装文件名 - 信息
		String OriginalFilename = uploadFile.getOriginalFilename();
		String string = OriginalFilename.substring(OriginalFilename.lastIndexOf("."));//.png
		
		String fileNames = UUIDHelper.getStringRandom(5).toUpperCase()+"_";//
		
		String pathTo = fileNames + (new Date().getTime())+ string;
		
		return pathTo;
	}
	
}
