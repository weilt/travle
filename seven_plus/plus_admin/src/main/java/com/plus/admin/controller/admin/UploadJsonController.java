package com.plus.admin.controller.admin;


import com.admin.service.admin.UploadJsonService;
import com.alibaba.fastjson.JSONObject;
import com.common.util.GsonUtil;
import com.plus.admin.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class UploadJsonController extends BaseController {

	@Autowired
	private UploadJsonService uploadJsonService;
	
	@RequestMapping("/adminBack/kindEditorUpload")
	public String upload_jsonController(@RequestParam(value = "uploadFile", required = false)MultipartFile uploadFile){
		try {
			if(uploadFile != null) {
				String url = uploadJsonService.upload(uploadFile);
				map.put("error", 0);
				map.put("url", url);
	        }else{
	        	map.put("error", 1);
				map.put("message", "上传失败");
	        }
		} catch (Exception e) {
			// TODO: handle exception
			map.put("error", 1);
			map.put("message", "上传失败");
		}
		return GsonUtil.toJson(map);
	}


	/**
	 * 阿里云上传
	 * @param uploadFile
	 * @return
	 */
	@RequestMapping("/admin/uploadoss")
	public String upload(@RequestParam(value = "uploadFile")MultipartFile uploadFile){
		super.map.clear();
		String url = uploadJsonService.uploadOss(uploadFile);
		if (null != url){
			map.put("error", 0);
			map.put("url", url);
		} else {
			map.put("error", 1);
			map.put("message", "上传失败");
		}
		return JSONObject.toJSONString(map);
	}

}
