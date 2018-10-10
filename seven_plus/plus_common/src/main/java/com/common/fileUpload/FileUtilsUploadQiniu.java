package com.common.fileUpload;

import com.common.util.ObjectHelper;
import com.google.gson.Gson;
import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.persistent.FileRecorder;
import com.qiniu.util.Auth;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Paths;

/**
 * 上传信息到七牛云信息中
 * @author
 */
public class FileUtilsUploadQiniu {
	
	// - 这个仅仅是测试的 不能用于商业用途
	public static final String AccessKey = "BXhqd20h54PViIi24bMafpBdRtRoi8Nb7IVBgfEs";
	
	public static final String SecretKey = "oidyfxQ9XbdwXtEdAgR-Sx4-WmUOzMjhDB1RaxA4";
	
	public static final String bucket = "xicheapp";
	
	public static final String imgUrl = "http://p7f7nhnfb.bkt.clouddn.com/";
	
	
	/**
	 * 普通上传
	 * 上传的七牛云中 - 直接上传
	 * 上传文件信息
	 * @return
	 */
	public static String upload_json(MultipartFile uploadFile) {
		String url = null;
		if(uploadFile != null) {
			try {
				//组装文件名 - 信息
				String pathTo = FileUtilsUpload.pathTo(uploadFile);
				//自动区分华南华北
		        Zone z = Zone.autoZone();
		        Configuration c = new Configuration(z);
				//创建上传对象
				UploadManager uploadManager = new UploadManager(c);
				//上传图片到七牛云存储
				//执行上传信息
				Response res = uploadManager.put(uploadFile.getBytes(), pathTo, qiniuToken());
				String body = res.bodyString();
				if(ObjectHelper.isNotEmpty(body)) {
					//组装结果信息
					DefaultPutRet defaultPutRet = new Gson().fromJson(body, DefaultPutRet.class);
					if(ObjectHelper.isNotEmpty(defaultPutRet) && ObjectHelper.isNotEmpty(defaultPutRet.key)) {
						url = imgUrl + defaultPutRet.key;
					}
				}
			} catch (QiniuException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return url;
	}

	/**
	 * 断点上传
	 * 上传的七牛云中 - 直接上传
	 * 上传文件信息
	 * @return
	 */
	public static String upload_jsonDuanDian(MultipartFile uploadFile) {
		String url = null;
		if(uploadFile != null) {
			try {
				//组装文件名 - 信息
				String pathTo = FileUtilsUpload.pathTo(uploadFile);
				//自动区分华南华北
				Zone z = Zone.autoZone();
				Configuration c = new Configuration(z);
				
				//断点续传的保存的位置
				String localTempDir = Paths.get(System.getenv("java.io.tmpdir"), bucket).toString();
				FileRecorder fileRecorder = new FileRecorder(localTempDir);
				//创建上传对象
				UploadManager uploadManager = new UploadManager(c,fileRecorder);
				//上传图片到七牛云存储
				//执行上传信息
				Response res = uploadManager.put(uploadFile.getBytes(), pathTo, qiniuToken());
				
				String body = res.bodyString();
				if(ObjectHelper.isNotEmpty(body)) {
					//组装结果信息
					DefaultPutRet defaultPutRet = new Gson().fromJson(body, DefaultPutRet.class);
					if(ObjectHelper.isNotEmpty(defaultPutRet) && ObjectHelper.isNotEmpty(defaultPutRet.key)) {
						url = imgUrl + defaultPutRet.key;
					}
				}
			} catch (QiniuException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return url;
	}
	
	/**
	 * 获取七牛云token
	 * @return
	 */
	public static String qiniuToken () {
		Auth auth = Auth.create(AccessKey, SecretKey);
		String token = auth.uploadToken(bucket);
		return token;
	}
}
