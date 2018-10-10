package com.hx.api.aliyun.file.oss;

import org.springframework.web.multipart.MultipartFile;

import com.hx.api.aliyun.file.type.NotSupportedImgException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;


public interface FileOperate {



	/**
	 * 直接保存，不进行校验 其 content 设置为MultipartFile的，如果为空，就不设置
	 * @param r_key 加了后缀的key
	 * @param file
	 * @throws IOException
	 */
	public void saveImgMultipartFile(String r_key, MultipartFile file) throws IOException;

	/**
	 * base64转码上传 用于前端JS（localResizeIMG）压缩图片上传
     * @param filedata 上传的文件BASE64编码
     * @param key 上传到阿里云服务器的文件路径公共部分
     * @param basePath 临时文件保存的上传路径公共部分
     * @param file_name 上传图片的名称集合
     * @throws IOException
	 * @throws NotSupportedImgException 
	 */
	public void saveBase64File(String filedata,String key, String basePath, List<String> file_name) throws IOException, NotSupportedImgException;
	
	/**
	 * 直接保存，不进行校验 其 content 设置为MultipartFile的，如果为空，就不设置
	 * @param ossBucketName
	 * @param r_key  加了后缀的key
	 * @param file
	 * @throws IOException
	 */
	public void saveImgMultipartFile(String ossBucketName, String r_key, MultipartFile file) throws IOException;


	/**
	 * 获取输入流的类型
	 * @param inputStream
	 * @return
	 * @throws NotSupportedImgException
	 * @throws IOException
	 */
	public String getImgType(InputStream inputStream) throws NotSupportedImgException, IOException;

	/**
	 * 删除
	 * @param objectR_key  带后缀的真实的
	 */
	public  void deleteImg(String objectR_key);


	/*******************************************/
	void saveFileAsyn(File file, String url_r_key) throws IOException, NotSupportedImgException;
	/*******************************************/

	/**
	 * 断点续传(对大文件上传可以体现出其优势)
	 * @param r_key
	 * @param localFilePath
	 * @param partSize
	 * @param taskNum
	 * @throws Throwable
	 */
	public void breakPointSaveImgMultipartFile(String r_key,String localFilePath,Long partSize,Integer taskNum) throws Throwable;


}
