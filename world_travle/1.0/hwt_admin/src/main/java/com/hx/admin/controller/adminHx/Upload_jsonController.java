package com.hx.admin.controller.adminHx;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hx.admin.base.ResultEntity;
import com.hx.admin.file.service.OSSService;
import com.hx.core.utils.JsonUtils;
import com.hx.core.utils.PropertiesUtils;
import com.hx.core.utils.UUIDHelper;



@RestController
public class Upload_jsonController {

	@Resource
	private OSSService ossService;
	
	@RequestMapping("/pic/upload")
	public String upload_jsonUserImageController(String token,HttpServletRequest request
			,@RequestParam(name="uploadFile",required = true)MultipartFile uploadFile) {
		if (uploadFile==null){
			throw new RuntimeException("请选择图片");
		}
		Map<String, Object> map = new HashMap<>();
		String url = "ScenicSpot/"+UUIDHelper.createUUId()+uploadFile.getOriginalFilename().substring(uploadFile.getOriginalFilename().lastIndexOf('.'));
		try {
			ossService.saveImgSync(uploadFile, url);
			String EXTERNAL_ENDPOINT = PropertiesUtils.getValue("EXTERNAL_ENDPOINT", "aliyun.properties");
			String IMG_OSS_BUCKET_NAME = PropertiesUtils.getValue("IMG_OSS_BUCKET_NAME", "aliyun.properties");
			
			url = EXTERNAL_ENDPOINT.replace("http://", "https://"+IMG_OSS_BUCKET_NAME+".") + "/" + url;
			
			map.put("error", 0);
			map.put("url", url);
		} catch (IOException e) {
			map.put("error", 1);
			map.put("message", "上传失败");
			e.printStackTrace();
		}
		String json = JsonUtils.Bean2Json(map);
		return json;
		
		
		
	}
}
