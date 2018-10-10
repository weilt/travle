package com.hx.api.aliyun.file.oss;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.OSSErrorCode;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.ServiceException;
import com.aliyun.oss.model.CannedAccessControlList;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.UploadFileRequest;
import com.hx.api.aliyun.file.type.NotSupportedImgException;
import com.hx.api.aliyun.file.type.type.ImgType;
import com.hx.core.utils.PropertiesUtils;
import com.hx.api.aliyun.file.type.FileTypeJudge;

import sun.misc.BASE64Decoder;
/**
 * 阿里云OSS服务
 * 现在只提供针对图片及管理的
 * 其他的后面再扩充
 */
public class AliyunImgOperate implements FileOperate {
	private final static Logger logger = LoggerFactory.getLogger(AliyunImgOperate.class);


//	private static final String  PROPERTIES_NAME = Properties.aliyun_properties.value; //"aliyun/aliyun.properties";

	public final static String errorMessage = "不受支持的图片格式类型";

	private static final String  KEY_P_ACCESS_ID = "ACCESS_ID";
	private static final String  KEY_P_ACCESS_KEY = "ACCESS_KEY";
	private static final String  KEY_P_EXTERNAL_ENDPOINT = "EXTERNAL_ENDPOINT";
	private static final String  KEY_P_IMG_OSS_BUCKET_NAME = "IMG_OSS_BUCKET_NAME";
	private static  String   ACCESS_ID;
	private static  String  ACCESS_KEY;
	private static  String  EXTERNAL_ENDPOINT;
	private static String IMG_OSS_BUCKET_NAME;

	private final static AliyunImgOperate aliyunImgOperate = new AliyunImgOperate();

	// 线程安全，单对象
	private static OSSClient client;

	private AliyunImgOperate() {
	}


	static{
		// 初始化配置文件
		init();
	}

	public final static AliyunImgOperate getAliyunImgOperate(){
		return aliyunImgOperate;
	}


	/**
	 * 初始化配置参数
	 */
	private static void init(){
		
			ACCESS_ID = PropertiesUtils.getValue(KEY_P_ACCESS_ID, "aliyun.properties");
			ACCESS_KEY = PropertiesUtils.getValue(KEY_P_ACCESS_KEY, "aliyun.properties");
			EXTERNAL_ENDPOINT = PropertiesUtils.getValue(KEY_P_EXTERNAL_ENDPOINT, "aliyun.properties");
			IMG_OSS_BUCKET_NAME = PropertiesUtils.getValue(KEY_P_IMG_OSS_BUCKET_NAME, "aliyun.properties");
			client = new OSSClient(EXTERNAL_ENDPOINT,ACCESS_ID, ACCESS_KEY);
		
	}

	public void saveImgMultipartFile(String r_key,MultipartFile file) throws IOException {
		this.saveImgMultipartFile(IMG_OSS_BUCKET_NAME, r_key, file);
	}

	public void saveImgMultipartFile(String ossBucketName,String r_key,MultipartFile file) throws IOException {
		init();
		String contentType = file.getContentType();
		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.getSize());
		objectMeta.setContentType(contentType.toLowerCase());

		// 记录日志
		this.logInfo(ossBucketName, objectMeta, r_key,  file.getOriginalFilename());

		InputStream input = file.getInputStream();
		client.putObject(IMG_OSS_BUCKET_NAME, r_key, input, objectMeta);
		input.close();
	}

	/**
	 * 断点上传(对大文件上传可以体现出其优势)
	 * @param r_key
	 * @param localFilePath
	 * @param partSize
	 * @param taskNum
	 * @throws Throwable
	 */
	public void breakPointSaveImgMultipartFile(String r_key,String localFilePath,Long partSize,Integer taskNum) throws Throwable {
		// 设置断点续传请求
		UploadFileRequest uploadFileRequest = new UploadFileRequest(IMG_OSS_BUCKET_NAME, r_key);
		// 指定上传的本地文件
		uploadFileRequest.setUploadFile(localFilePath);
		// 指定上传并发线程数
		if(taskNum == null || taskNum <= 0)
			taskNum = 5;
		uploadFileRequest.setTaskNum(taskNum);
		// 指定上传的分片大小
		if(partSize == null || partSize <= 0)
			partSize = 1024*1024*1L;
		uploadFileRequest.setPartSize(partSize);

		// 开启断点续传
		uploadFileRequest.setEnableCheckpoint(true);
		// 断点续传上传
		client.uploadFile(uploadFileRequest);

	}


	/**
	 * 系统自动判定图片格式添加为后缀，先不开放
	 * @param key
	 * @param file
	 * @return
	 * @throws NotSupportedImgException
	 * @throws IOException
	 */
	public String createImgMultipartFile(String key,MultipartFile file) throws NotSupportedImgException, IOException{
		return this.createImgMultipartFile(IMG_OSS_BUCKET_NAME, key, file);
	}

	public String createImgMultipartFile(String ossBucketName,String key,MultipartFile file) throws NotSupportedImgException, IOException {
		init();

		String contentType = file.getContentType();

		String type = this.getImgType( file.getInputStream());
		String r_contentType  = "image/"+type;

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.getSize());
		objectMeta.setContentType(r_contentType);

		String r_key = key+"." + type;

		// 记录日志
		this.logInfo(ossBucketName, objectMeta, r_key,  file.getOriginalFilename());


		InputStream input = file.getInputStream();

		client.putObject(ossBucketName, r_key, input,objectMeta);
		input.close();
		return r_key;

	}


	/**
	 * 上传图片到服务
	 * @param ossBucketName   oss建立的Bucket
	 * @param key    eg:"details";  // 显示于 OSS object的名字，也是全局该Bucket的唯一标识，不带后缀，系统自动判定真实后缀
	 * @param file    本地文件变量
	 * @throws NotSupportedImgException  非定义的图片格式不受支持
	 * @throws IOException   本地文件操作出现异常
	 * @return 真实的key
	 */
	protected String saveImgFile(String ossBucketName,String key,File file) throws NotSupportedImgException, IOException {
		init();
		// 这里要相当的注意：
		// 传入的file，如果传入InputStream,改方法会操作InputStream的标识位
		// 如果该InputStream继续给其他操作，就会出现问题
		// 现在的解决方式就是传入一个file，改方法自己使用一个InputStream，并完成关闭
		String type = this.getImgType(new FileInputStream(file));
		String contentType = "image/"+type;

		ObjectMetadata objectMeta = new ObjectMetadata();
		objectMeta.setContentLength(file.length());
		objectMeta.setContentType(contentType);

		//判断后缀
		String r_key = key;
		if(!key.contains("."))
			r_key = key+"." + type;

		// 记录日志
		this.logInfo(ossBucketName, objectMeta, r_key, file.getName());

		InputStream input = new FileInputStream(file);

		client.putObject(ossBucketName, r_key, input,objectMeta);
		input.close();
		return r_key;
	}

	public String getImgType(InputStream inputStream) throws NotSupportedImgException, IOException{
		ImgType imgType = FileTypeJudge.getImgType(inputStream);
		String type;
		if(imgType == null){ // 如果是其他文件，都暂时设置成这个
			//contentType = "application/octet-stream";
			throw new NotSupportedImgException(errorMessage);
		}else{
			type = imgType.name().toLowerCase();
		}
		inputStream.close();//关闭流
		return type;

	}

	public void deleteImg(String objectR_key){

		this.deleteImg(IMG_OSS_BUCKET_NAME, objectR_key);
	}

	//********************************************************/
	@Override
	public void saveFileAsyn(File file, String url_r_key) throws IOException, NotSupportedImgException {
		saveImgFile(IMG_OSS_BUCKET_NAME,url_r_key,file);
	}
	//*****************************************************/

	// 删除一个Objects
	protected  void deleteImg(String ossBucketName,String objectR_key){
		init();
		client.deleteObject(IMG_OSS_BUCKET_NAME, objectR_key);
	}

	// 按照File上传文件
	protected void uploadFile(OSSClient client, String bucketName, String key, File file,ObjectMetadata objectMeta)
			throws OSSException, ClientException, FileNotFoundException {
		try {
			init();
			objectMeta.setContentLength(file.length());
			FileInputStream input = new FileInputStream(file);
			client.putObject(bucketName, key, input, objectMeta);
			input.close();
		}catch (Exception e){
			logger.error("上传失败",e);
		}
	}


	// 下载文件
	protected  void downloadFile(OSSClient client, String bucketName, String key, String filename)
			throws OSSException, ClientException {
		init();
		client.getObject(new GetObjectRequest(bucketName, key), new File(filename));
	}


	// 创建Bucket.
	protected  void ensureBucket(OSSClient client, String bucketName)
			throws OSSException, ClientException{
		init();
		try {
			// 创建bucket
			client.createBucket(bucketName);
		} catch (ServiceException e) {
			//  if (!OSSErrorCode.BUCKES_ALREADY_EXISTS.equals(e.getErrorCode())) {
			if (!OSSErrorCode.BUCKET_ALREADY_EXISTS.equals(e.getErrorCode())) {
				// 如果Bucket已经存在，则忽略
				logger.info("bucketName:BUCKET_ALREADY_EXISTS 已经存在 ErrorCode：{} ",e.getErrorCode());
			}
			throw e;
		}
	}

	// 删除一个Bucket和其中的Objects
	protected  void deleteBucket(OSSClient client, String bucketName)
			throws OSSException, ClientException {
		init();
		ObjectListing ObjectListing = client.listObjects(bucketName);
		List<OSSObjectSummary> listDeletes = ObjectListing.getObjectSummaries();
		for (int i = 0; i < listDeletes.size(); i++) {
			String objectName = listDeletes.get(i).getKey();
			// 如果不为空，先删除bucket下的文件
			client.deleteObject(bucketName, objectName);
		}
		client.deleteBucket(bucketName);
	}

	// 把Bucket设置为所有人可读
	protected  void setBucketPublicReadable(OSSClient client, String bucketName)
			throws OSSException, ClientException {
		init();
		//创建bucket
		client.createBucket(bucketName);
		//设置bucket的访问权限，public-pulish-write权限
		client.setBucketAcl(bucketName, CannedAccessControlList.PublicRead);
	}

	/**
	 * 记录上传日志
	 * @param ossBucketName
	 * @param objectMeta
	 * @param r_key
	 * @param originalFilename
	 */
	private void logInfo(String ossBucketName,ObjectMetadata objectMeta,String r_key,String originalFilename){
		logger.debug("上传，获得文件格式等 filename:{},r_key:{}", originalFilename, r_key);
		logger.debug("contentType:{}--contentLength:{}", objectMeta.getContentType(), objectMeta.getContentLength());
	}


	/**
	 * base64转码上传 用于前端JS（localResizeIMG）压缩图片上传
     * @param filedata 上传的文件BASE64编码
     * @param key 上传到阿里云服务器的文件路径公共部分
     * @param basePath 临时文件保存的上传路径公共部分
     * @param file_name 上传图片的名称集合
     * @throws IOException
	 * @throws NotSupportedImgException 
	 */
	@SuppressWarnings("all")
	public void saveBase64File(String filedata, String key, String basePath, List<String> file_name) throws IOException, NotSupportedImgException {
		init();
		BASE64Decoder decoder = new BASE64Decoder();
		FileOutputStream outputStream = null;
		
		FileInputStream inputStream = null;
		// 获得一个图片文件流，我这里是从flex中传过来的
		String[] filescode = filedata.split(",");
		int k = 0;
		for (int j = 0; j < filescode.length; j++) {
			if(j%2 > 0) {
				String imgPath = basePath+ UUID.randomUUID()+".jpg";
				// new一个文件对象用来保存图片，默认保存当前工程根目录
				File imageFile = new File(imgPath);
				if(!new File(basePath).isDirectory()) new File(basePath).mkdirs();
				
				// 创建输出流
				outputStream = new FileOutputStream(imageFile);
				
				byte[] result = decoder.decodeBuffer(filescode[j]);//解码
				for (int i = 0; i < result.length; ++i) {
					if (result[i] < 0) {// 调整异常数据
						result[i] += 256;
					}
				}
				outputStream.write(result);
				outputStream.flush(); 
				outputStream.close();
				
				inputStream = new FileInputStream(imageFile);
				logger.debug("上传，获得文件格式等 filename:{},r_key:{}", imageFile.getName(), key + file_name.get(k));
				client.putObject(IMG_OSS_BUCKET_NAME, key + file_name.get(k), inputStream);
//				File imageFile = new File(imgPath);
				//saveImgFile(IMG_OSS_BUCKET_NAME, key + file_name.get(k), imageFile);
//				inputStream.close();
				imageFile.delete();
				k++;
			}
			if(inputStream != null) {
				inputStream.close();
			}
			if(outputStream != null) {
				outputStream.flush(); 
				outputStream.close();
			}
		}
		if(inputStream != null) {
			inputStream.close();
		}
		if(outputStream != null) {
			outputStream.flush(); 
			outputStream.close();
		}
	}
}

