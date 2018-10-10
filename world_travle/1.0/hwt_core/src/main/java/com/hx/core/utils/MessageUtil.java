package com.hx.core.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;
@SuppressWarnings("restriction")
public class MessageUtil {
	
	/**
	 * 方法说明：对字符串进行编码转换
	 * @param src要转换的源字符串
	 * @param from原编码
	 * @param to 要转换成的编码
	 * @return
	 */
	public static String trans(String src, String from, String to) {
		String value = src;
		try {
			value = new String(value.getBytes(from), to);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * 
	 * 方法说明：对字符串进行加密 base64
	 * @param value 要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String encodeBas64(String value) {
		return new BASE64Encoder().encode(value.getBytes());
	}

	/**
	 * 
	 * 方法说明：对加密后的字符串进行解密 base64
	 * @param value 要解密的字符串
	 * @return 解密后的字符串
	 */
	public static String decodeBase64(String value) {
		String result = null;
		try {
			result = new String(new BASE64Decoder().decodeBuffer(value));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 
	 * 方法说明：使用MD5加密字符串
	 * @param value
	 * @return
	 */
	public static String encodeMD5(String value) {
		String s = null;
		char hexDigits[] = { // 用来将字节转换成 16 进制表示的字符
		'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
		try {
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			md.update(value.getBytes());
			byte tmp[] = md.digest(); // MD5 的计算结果是一个 128 位的长整数，
			// 用字节表示就是 16 个字节
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
			// 所以表示成 16 进制需要 32 个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
				// 转换成 16 进制字符的转换
				byte byte0 = tmp[i]; // 取第 i 个字节
				str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
				// >>> 为逻辑右移，将符号位一起右移
				str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			s = new String(str); // 换后的结果转换为字符串
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}
	
	/**
	 * MD5加密
	 * @param str - 字符串
	 * @param code - 加密位数16/32
	 * @return
	 */
	public static String MD5(String str, int code){
         String strEncrypt = null;
         if (code == 16){
             strEncrypt = encodeMD5(str).substring(8, 24);
         }
         if (code == 32){
             strEncrypt = encodeMD5(str);
         }
         return strEncrypt;
     }
	
	/**
	 * 加密字符串
	 * @param str 加密字符串
	 * @param type -'MD5','SHA','SHA1'
	 * @return
	 */
	public static String getMD5(String str,String type) {
	    try {
	        // 生成一个MD5加密计算摘要
	        MessageDigest md = MessageDigest.getInstance(type);
	        // 计算md5函数
	        md.update(str.getBytes());
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	        throw new RuntimeException("MD5加密出现错误");
	    }
	}
	
	public static String imageToBase64(String path) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
	    byte[] data = null;
	    // 读取图片字节数组
	    try {
	        InputStream in = new FileInputStream(path);
	        data = new byte[in.available()];
	        in.read(data);
	        in.close();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	    // 对字节数组Base64编码
	    BASE64Encoder encoder = new BASE64Encoder();
	    return encoder.encode(data);// 返回Base64编码过的字节数组字符串
	}
}