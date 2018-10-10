package com.xx.springBootUtil.util;

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
	
	
	

	public static void main(String[] args) {
		
		String sss = "data:image/jpg;base64,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";
		
		String sss1 = "I:/LiuGang/a1.jpg";
		System.out.println(encodeMD5("123456"));
		
		
		
	}

}