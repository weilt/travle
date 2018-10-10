package com.hx.core.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件工具类
 * 针对文件操作提供公共接口
 * @author WJ
 *
 */
@SuppressWarnings("all")
public class FileUtils {

	// 文件大小设置为500kB
	public final static Long FILE_MAX_SIZE = 512000L;
	
	private static Logger log = LoggerFactory.getLogger(FileUtils.class);

    
    /**
     * 上传文件
     * @param path 上传文件地址
     * @param file 文件
     * @throws ApplicationBusinessException 
     * @throws IOException 
     * @throws IllegalStateException 
     */
    public static void uploadFile(String path,MultipartFile file,String filename){
    	
    	// 上传文件名称
        if(!new File(path).exists()) {
            new File(path).mkdirs();
        };
    	log.info("file:{} upload path: {}",file.getOriginalFilename(),path);
    	File uploadFile = new File(path,filename);
    	try {
			file.transferTo(uploadFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }





    /**
     * 上传文件
     * @param path 上传文件地址
     * @param file 文件
     * @throws ApplicationBusinessException 
     * @throws IOException 
     * @throws IllegalStateException 
     */
    public static void uploadFile(String path,File file,String filename)
    		throws IllegalStateException, IOException {
    	// 上传文件名称
    	log.debug("file:{} upload path: {}",file.getName(),path);
    	File uploadFile = new File(path,filename);
    	if(!new File(path).exists() && !new File(path).isDirectory()) {
    		uploadFile.mkdirs();
    	};
    	FileInputStream in = new FileInputStream(file);
    	
    	FileOutputStream out = new FileOutputStream(uploadFile);
    	
    	byte buffer[] = new byte[1024];
    	//判断输入流中的数据是否已经读完的标识
    	int len = 0;
    	//循环将输入流读入到缓冲区当中，(len=in.read(buffer))>0就表示in里面还有数据
    	while((len=in.read(buffer))>0){
    	//使用FileOutputStream输出流将缓冲区的数据写入到指定的目录(savePath + "\\" + filename)当中
    		out.write(buffer, 0, len);
    	}
    	//关闭输入流
    	in.close();
    	//关闭输出流
    	out.flush();
    	out.close();
    	
    }



    
    /**
     * 上传文件
     * @param path 上传文件地址
     * @param file 文件
     * @throws ApplicationBusinessException 
     * @throws IOException 
     * @throws IllegalStateException 
     */
    public static List<String> uploadFiles(String path,MultipartFile[] files) 
    		throws IllegalStateException, IOException {
    	List<String> imagePath = null;
    	if(files != null && files.length > 0 ) {
    		imagePath = new ArrayList<>();
    		for (MultipartFile file : files) {
    			// 上传文件名称
    	    	String filename = System.currentTimeMillis() + ".jpg";
    	    	log.debug("file:{} upload path: {}",filename,path);
    	    	File uploadFile = new File(path+filename);
    	    	// 创件文件夹
    	    	if(uploadFile.exists() && uploadFile.isDirectory()) {uploadFile.mkdirs();};
    	    	file.transferTo(uploadFile);
    	    	log.debug("上传成功..........");
    	    	imagePath.add(filename);
			}
    	}
    	return imagePath;
    }
    
    /**
     * base64转码上传 用于前端JS（localResizeIMG）压缩图片上传
     * @param filedata 上传的文件BASE64编码
     * @param basePath 上传路径
     * @throws IOException
     * @return 返回图片完整的保存路径
     */
    public static List<String> base64UpFile(String filedata, String basePath) throws IOException {
    	log.debug("[文件上传（fileUploadPicture）][filedata=" + filedata + "]");
		BASE64Decoder decoder = new BASE64Decoder();
		FileOutputStream outputStream = null;
		List<String> imagePath = new ArrayList<>();
		try {
			// 获得一个图片文件流，我这里是从flex中传过来的
			String[] filescode = filedata.split(",");
			for (int j = 0; j < filescode.length; j++) {
				if(j%2 > 0) {
					String real_name = System.currentTimeMillis() + ".jpg";
					String imgPath = basePath+real_name;
					// new一个文件对象用来保存图片，默认保存当前工程根目录
					File imageFile = new File(imgPath);
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
					imagePath.add(real_name);
				}
			}
		} catch (Exception e) {
			log.error("[文件上传（fileUpload）][errors:" + e + "]");
		}finally{
			if(outputStream != null) {
				outputStream.flush(); 
				outputStream.close();
			}
			return imagePath;
		}
    }
    
    /**
     * 上传文件检查 
     * 检查文件大小及是否为空
     * @param files
     * @param fileMaxSize 文件最大值 单位为b
     * @return
     */
    public static boolean checkFiles(MultipartFile[] files, Long fileMaxSize) {
    	boolean flag = false;
    	if(files != null && files.length > 0) {
    		for (int i = 0; i < files.length; i++) {
				long size = files[i].getSize();
				log.debug("文件:{},的大小为:{}",files[i].getOriginalFilename(),size);
				if(size > fileMaxSize) {
					return flag;
				}
			}
    		flag = true;
    	}
    	return flag;
    }
    
    /**
     * 上传文件检查 
     * 检查文件大小及是否为空
     * @param files
     * @param fileMaxSize 文件最大值 单位为b
     * @return
     */
    public static boolean checkFile(MultipartFile file, Long fileMaxSize) {
    	if(file == null || file.getSize() > fileMaxSize) {
    		log.debug("文件:{},的大小为:{}",file.getOriginalFilename(),file.getSize());
    		return false;
    	}
    	return true;
    }
    
    
    /** 
     * 删除单个文件 
     * @param   sPath    被删除文件 
     * @return 单个文件删除成功返回true，否则返回false 
     */  
    public static boolean deleteFile(String sPath) {  
        boolean flag = false;  
        File file = new File(sPath);  
        // 路径为文件且不为空则进行删除  
        if (file.isFile() && file.exists()) {  
            file.delete();  
            flag = true;  
        }  
        return flag;  
    }

    /**
     * 删除文件夹下所有文件
     * @param file
     */
    public static void doDeleteEmptyDir(File file) {
		if (file.isDirectory()) {
			File[] files = file.listFiles();
			for (File f : files) {
				doDeleteEmptyDir(f);
			}
		} else {
			file.delete();
		}
    }

}
