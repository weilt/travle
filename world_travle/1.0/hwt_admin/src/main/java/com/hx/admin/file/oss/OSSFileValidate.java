package com.hx.admin.file.oss;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hx.admin.file.type.type.FileType;

import java.io.IOException;

/**
 * Created by lyj on 2016/10/10.
 * OSS上传文件的验证
 */
public class OSSFileValidate {

    //文件最大量
    private static final long MAX_SIZE = 104857600;

    //文件最小量
    private static final long MIN_SIZE = 0;


    /**
     * 判断单个上传的文件是否格式，大小匹配
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean validateFile(MultipartFile file) throws IOException {
        String fileName = file.getName();

        long size = file.getSize();

        if(StringUtils.isNotBlank(fileName) && size > 0){
            //大小的判断
            if(size > OSSFileValidate.MAX_SIZE || size < OSSFileValidate.MIN_SIZE){
                return  false;
            }

            //类型的判断:
            String suffix = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf(".") + 1);
            System.out.println("getOriginalFilename ==="  + file.getOriginalFilename());
            System.out.println("getName ==="  + file.getName());
            System.out.println("getContentType ==="  + file.getContentType());
            
            suffix = suffix.toUpperCase();
            FileType[] fileTypes = FileType.values();
            FileType fileType = null;
            for (FileType type : fileTypes) {
            	if(type.name().equals(suffix)) {
            		fileType = type;
            	}
			}
            		
//            FileType fileType = FileTypeJudge.getFileType(file.getInputStream());

            if(fileType == null){
                return false;
            }
        }else{
            return false;
        }
        return true;
    }

    
    public static void main(String[] args) {
    	FileType[] fileTypes = FileType.values();
        for (FileType fileType : fileTypes) {
        	System.out.println(fileType.name());
		}
	}
    
    
    /**
     * 判断单个上传的文件是否格式，大小匹配
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static boolean validateFileList(MultipartFile[] multipartFile) throws IOException {
        if(multipartFile == null || multipartFile.length <= 0){
            return false;
        }else{
            for(MultipartFile file : multipartFile){

                if(!OSSFileValidate.validateFile(file)){
                    return false;
                }
            }
        }
        return true;
    }

}
