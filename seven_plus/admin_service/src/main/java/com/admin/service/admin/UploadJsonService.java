package com.admin.service.admin;

import org.springframework.web.multipart.MultipartFile;

public interface UploadJsonService {

	/**
	 * 上传
	 * @param uploadFile
	 * @return
	 */
	String upload(MultipartFile uploadFile);

	/**
	 * oss上传
	 * @param uploadFile
	 * @return
	 */
	String uploadOss(MultipartFile uploadFile);
}
