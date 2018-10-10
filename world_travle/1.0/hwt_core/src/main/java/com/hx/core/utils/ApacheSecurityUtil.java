package com.hx.core.utils;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.SecureRandom;

/**
 * 依赖  commons.lang3
 * commons.codec
 * 
 * 修改必须与 SecurityUtil 一致
 * 
 * 
 * 
 * 编码工具类 1.将byte[]转为各种进制的字符串 2.base 64 encode 3.base 64 decode 4.获取byte[]的md5值
 * 5.获取字符串md5值 6.结合base64实现md5加密 7.AES加密 8.AES加密为base 64 code 9.AES解密 10.将base
 * 64 code AES解密
 */
public class ApacheSecurityUtil {


	/**
	 * 将byte[]转为各种进制的字符串
	 * 
	 * @param bytes
	 *            byte[]
	 * @param radix
	 *            可以转换进制的范围，从Character.MIN_RADIX到Character.MAX_RADIX，超出范围后变为10进制
	 * @return 转换后的字符串
	 */
	public static String binary(byte[] bytes, int radix) {
		return new BigInteger(1, bytes).toString(radix);// 这里的1代表正数
	}

	/**
	 * base 64 encode
	 * 
	 * @param bytes
	 *            待编码的byte[]
	 * @return 编码后的base 64 code
	 */
	 static String base64Encode(byte[] bytes) {
		 return Base64.encodeBase64String(bytes);
		//return new BASE64Encoder().encode();
	}

	/**
	 * base 64 decode
	 * 
	 * @param base64Code
	 *            待解码的base 64 code
	 * @return 解码后的byte[]
	 * @throws Exception
	 */
	public static byte[] base64Decode(String base64Code) throws Exception {
		return StringUtils.isEmpty(base64Code) ? null : Base64.decodeBase64(base64Code);
	}

	public static String md5(byte[] bytes) throws Exception {
		StringBuffer sb = new StringBuffer(32);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(bytes);
		byte[] array = md.digest();
		for (int i = 0; i < array.length; i++) {
			sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3));
		}
		return sb.toString();
	}

	/**
	 * 获取字符串md5值
	 * 
	 * @param msg
	 * @return md5
	 * @throws Exception
	 */
	public static String md5(String msg) throws Exception {
		return StringUtils.isEmpty(msg) ? null : md5(msg.getBytes());
	}


	/**
	 * AES加密
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的byte[]
	 * @throws Exception
	 */
	public static byte[] aesEncryptToBytes(String content, String encryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(encryptKey.getBytes()));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));

		return cipher.doFinal(content.getBytes("utf-8"));
	}

	/**
	 * AES加密为base 64 code
	 * 
	 * @param content
	 *            待加密的内容
	 * @param encryptKey
	 *            加密密钥
	 * @return 加密后的base 64 code
	 * @throws Exception
	 */
	public static byte[] aesEncrypt(String content, String encryptKey) throws Exception {

		KeyGenerator kgen = KeyGenerator.getInstance("AES");

        SecureRandom random=SecureRandom.getInstance("SHA1PRNG");//需要自己手动设置
        random.setSeed(encryptKey.getBytes());
        kgen.init(128, random);

		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		byte[] byteContent = content.getBytes("utf-8");
		cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(byteContent);
		return result; // 加密

	}

	/**
	 * AES解密
	 * 
	 * @param encryptBytes
	 *            待解密的byte[]
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的String
	 * @throws Exception
	 */
	public static String aesDecryptByBytes(byte[] encryptBytes, String decryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
		kgen.init(128, new SecureRandom(decryptKey.getBytes()));

		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(kgen.generateKey().getEncoded(), "AES"));
		byte[] decryptBytes = cipher.doFinal(encryptBytes);

		return new String(decryptBytes);
	}

	/**
	 * 将base 64 code AES解密
	 * 
	 * @param encryptStr
	 *            待解密的base 64 code
	 * @param decryptKey
	 *            解密密钥
	 * @return 解密后的string
	 * @throws Exception
	 */
	public static byte[] aesDecrypt(byte[] encryptStr, String decryptKey) throws Exception {
		KeyGenerator kgen = KeyGenerator.getInstance("AES");
        SecureRandom random=SecureRandom.getInstance("SHA1PRNG");//需要自己手动设置
        random.setSeed(decryptKey.getBytes());
        kgen.init(128, random);
		SecretKey secretKey = kgen.generateKey();
		byte[] enCodeFormat = secretKey.getEncoded();
		SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
		Cipher cipher = Cipher.getInstance("AES");// 创建密码器
		cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
		byte[] result = cipher.doFinal(encryptStr);
		return result; // 加密
	}

	/**将二进制转换成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */
	public static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length()/2];
		for (int i = 0;i< hexStr.length()/2; i++) {
			int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
			int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}

	/**
	 * jiami
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String aes(String content, String key) throws Exception {
		byte[] encryptResult = aesEncrypt(content, key);
		return parseByte2HexStr(encryptResult);
	}
	/**
	 * jiemi
	 * @param content
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String dec(String content, String key) throws Exception {
		byte[] decryptFrom = parseHexStr2Byte(content);
		return new String(aesDecrypt(decryptFrom,key));
	}

//    public static void main(String[] args) {
//        String a = "dajskldjkasjkdlajskldjalsk;djlkasjdkjaslkdja;lsjdlasjkldjSDKLasjdkl;JSKLDJSDJKLasjdklAJSLDK;Ja;lsdjk;lASJDLK;jaskld" +
//				"AKSJDK;Lajsldk;jAKLSDJLK;asjdkl;JASKLDJkla;sjdkl;ASJDKLajskl;djAL;KSDJlk;asdjl;kASJDLK;ajsdkl;JASK;LD" +
//				"AJSKDjask;ldj;LKASJDL;ajsd;lkASJDLK;jas;ldjLK;ASJDLK;ajsdk;lJASDKL;JaskldASJDKLasjd;lkASJDLK;ajslk;dj;LKASJD" +
//				"aklsjd;lkJSADKLjask;ldj;LKASDJKLasjdlkAJSDjasdjk;ASKDJaskljdA;LKSJHFsdkfhDFHKJ;LS";
//        String aa = "zDL6LAW6KzcM4eFR";
//        try {
//
//            System.out.println(aesEncryptToBytes(a,aa));
//            System.out.println(aesDecryptByBytes(aesEncryptToBytes(a,aa),aa));
//            System.out.println(aes(a, aa));
//            System.out.println(dec(aes(a, aa), aa));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
