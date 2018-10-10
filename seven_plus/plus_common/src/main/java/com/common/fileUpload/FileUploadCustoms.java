package com.common.fileUpload;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadCustoms {

	
	/**
	 * 上传图片信息
	 * @param uploadFile
	 * @param type  上传的位置  0-本地，1-七牛，2-oss
	 * @return
	 */
	public static String upload(MultipartFile uploadFile,Integer type) {
		String url = null;
		if(type == null)
			type = 0;
		try {
			if(uploadFile != null) {
				//普通上传 - 到本地信息				
//				url = FileUtilsUpload.upload_json(uploadFile);
				//普通上传 - 到七牛
//				String url = FileUtilsUploadQiniu.upload_json(uploadFile);
				//断点上传 - 到七牛
//				String url = FileUtilsUploadQiniu.upload_jsonDuanDian(uploadFile);
				switch (type.intValue()) {
					case 0:
						//普通上传 - 到本地信息				
						url = FileUtilsUpload.upload_json(uploadFile);
						break;
					case 1:
						//普通上传 - 到七牛
						url = FileUtilsUploadQiniu.upload_json(uploadFile);
						break;
					default:
						break;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return url;
	}
}
