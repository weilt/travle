package com.admin.service.admin.impl;


import com.admin.service.admin.UploadJsonService;
import com.common.fileUpload.FileUtilsUpload;
import com.common.fileUpload.FileUtilsUploadQiniu;
import com.common.fileUpload.OssUtil;
import com.common.util.ObjectHelper;
import com.domain.admin.mapper.AdminSystemConfigMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
public class UploadJsonServiceImpl implements UploadJsonService {
	
	@Autowired
	private AdminSystemConfigMapper adminSystemConfigMapper;
	
	
	/**
	 * 上传图片信息
	 * @param uploadFile
	 * @param uploadFile  上传的位置  0-本地，1-七牛，2
	 * @return
	 */
	public String upload(MultipartFile uploadFile) {
		String url = null;
		
		String type = adminSystemConfigMapper.getValue("uploadFile");
		if(ObjectHelper.isEmpty(type))
			type = "0";
		try {
			if(uploadFile != null) {
				//普通上传 - 到本地信息				
//				url = FileUtilsUpload.upload_json(uploadFile);
				//普通上传 - 到七牛
//				String url = FileUtilsUploadQiniu.upload_json(uploadFile);
				//断点上传 - 到七牛
//				String url = FileUtilsUploadQiniu.upload_jsonDuanDian(uploadFile);
				switch (Integer.parseInt(type)) {
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



	@Override
	public String uploadOss(MultipartFile uploadFile) {
		return OssUtil.getOssUtil().saveImgMultipartFile(uploadFile);
	}
}
