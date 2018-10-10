package com.hx.admin.file.type;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import com.hx.admin.file.type.type.FileType;
import com.hx.admin.file.type.type.ImgType;





public class FileTypeJudge {
	/**
	 * Constructor
	 */
	private FileTypeJudge() {
	}

	/**
	 * 将文件头转换成16进制字符串
	 * 
	 * @param src    文件
	 * @return 16进制字符串
	 */
	private static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder();
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * 得到文件头
	 * 
	 * @param inputStream 文件流
	 * @return 文件头
	 * @throws IOException
	 */
	private static String getFileContent(InputStream inputStream) throws IOException {
		byte[] b = new byte[28];
		inputStream.read(b, 0, 28);
		return bytesToHexString(b);
	}


	
	/**
	 * 判定图片类型
	 * @param file
	 * @return
	 * @throws IOException
	 */
	public static FileType getImgFileType(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		return getImgFileType(inputStream);
	}
	
	public static ImgType getImgType(File file) throws IOException {
		InputStream inputStream = new FileInputStream(file);
		return getImgType(inputStream);
	}
	/**
	 * 判断图片类型
	 * @param inputStream
	 * @return
	 * @throws IOException
	 */
	public static FileType getImgFileType(InputStream inputStream) throws IOException {
		// 包装成FileType类型
		ImgType imgType = getImgType(inputStream);
		if(imgType == null){
			return null;
		}
		return FileType.valueOf(imgType.name());
	}
	
	public static ImgType getImgType(InputStream inputStream) throws IOException {
		String fileHead = getFileContent(inputStream);
		if (fileHead == null || fileHead.length() == 0) {
			return null;
		}
		fileHead = fileHead.toUpperCase();
		
		//System.out.println("fileHead:"+fileHead);
		ImgType[] fileTypes = ImgType.values();
		// 迭代得到具体的枚举类型
		for (ImgType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				// 包装成FileType类型
				return type;
			}
		}
		
		inputStream.close();
		return null;
	}

	
	public static FileType getFileType(InputStream inputStream) throws IOException {
		String fileHead = getFileContent(inputStream);
		if (fileHead == null || fileHead.length() == 0) {
			return null;
		}
		fileHead = fileHead.toUpperCase();
		FileType[] fileTypes = FileType.values();
		// 迭代得到具体的枚举类型
		for (FileType type : fileTypes) {
			if (fileHead.startsWith(type.getValue())) {
				return type;
			}
		}
		return null;
	}

}
