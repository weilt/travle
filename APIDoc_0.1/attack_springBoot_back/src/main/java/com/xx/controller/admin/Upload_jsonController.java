package com.xx.controller.admin;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.gson.Gson;
import com.xx.controller.util.BaseController;
import com.xx.springBootUtil.util.PropertiesUtils;
import com.xx.springBootUtil.util.UUIDHelper;
import com.xx.util.util.FileUtils;

@RestController
public class Upload_jsonController extends BaseController{

	@RequestMapping("kindEditorUpload")
	public String upload_jsonController(@RequestParam(value = "uploadFile", required = false)MultipartFile uploadFile){
		try {
			if(uploadFile != null) {
				String string = uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf("."));
				String pathTo = UUIDHelper.createUUId()+ string;
				//存放地址
				String filePath = PropertiesUtils.getValue("web.upload-path", "application.properties");
				
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
				String dateTi = sdf.format(new Date())  + "/";		
				String fileReqUrlname = PropertiesUtils.getValue("xx.file.request.urlname","application.properties");
				
				filePath += dateTi;
				fileReqUrlname += dateTi;
				FileUtils.uploadFile(filePath,uploadFile,pathTo);
				map.put("error", 0);
				map.put("url", fileReqUrlname + pathTo);
	        }else{
	        	map.put("error", 1);
				map.put("message", "上传失败");
	        }
		} catch (Exception e) {
			// TODO: handle exception
			map.put("error", 1);
			map.put("message", "上传失败");
		}
		return new Gson().toJson(map);
	}
}
