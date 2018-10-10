package com.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;


public class Des {   
  
    //解密数据   
    public static String decrypt(String message,String key) throws Exception {   
            
            byte[] bytesrc =convertHexString(message);      
            Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");       
            DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));      
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");      
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);      
            IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
                   
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);         
             
            byte[] retByte = cipher.doFinal(bytesrc);        
            return new String(retByte);    
    }   
  
    
    //加密
    public static byte[] encrypt(String message, String key)   
            throws Exception {   
        Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");   
  
        DESKeySpec desKeySpec = new DESKeySpec(key.getBytes("UTF-8"));   
  
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");   
        SecretKey secretKey = keyFactory.generateSecret(desKeySpec);   
        IvParameterSpec iv = new IvParameterSpec(key.getBytes("UTF-8"));   
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);   
        return cipher.doFinal(message.getBytes("UTF-8"));   
    }  
       
    public static byte[] convertHexString(String ss)    
	    {    
	    byte digest[] = new byte[ss.length() / 2];    
	    for(int i = 0; i < digest.length; i++)    
	    {    
	    String byteString = ss.substring(2 * i, 2 * i + 2);    
	    int byteValue = Integer.parseInt(byteString, 16);    
	    digest[i] = (byte)byteValue;    
	    }    
	  
	    return digest;    
    }    
    public static String toHexString(byte b[]) {   
        StringBuffer hexString = new StringBuffer();   
        for (int i = 0; i < b.length; i++) {   
            String plainText = Integer.toHexString(0xff & b[i]);   
            if (plainText.length() < 2)   
                plainText = "0" + plainText;   
            hexString.append(plainText);   
        }   
        return hexString.toString();   
    }   
    
    
    
    
    
    
    
    public static void main(String[] args) throws Exception {   
       /* String key = "12345678";   
        String value="test1234 ";   
        String jiami=java.net.URLEncoder.encode(value, "utf-8").toLowerCase();   
           
        System.out.println("加密数据:"+jiami);   
        String a=toHexString(encrypt(jiami, key)).toUpperCase();   
        
        System.out.println("加密后的数据为:"+a);   
        String b=java.net.URLDecoder.decode(decrypt(a,key), "utf-8") ;   
        System.out.println("解密后的数据:"+b);*/   
        
        
        
      //-----------------密码开始--------------
    	String mima = "456789aaa";//密码
    	String passmiyao = "123456aaa";//秘钥
    	
    	String md5miyao = MessageUtil.MD5(passmiyao, 16).toLowerCase(); //秘钥MD5加密
    	System.out.println("秘钥MD5加密:"+md5miyao);
    	//1d598a453606e890
    	//md5
    	String key = MessageUtil.MD5(md5miyao, 32);
    	byte[] bs = encrypt(mima, key.substring(0, 8));
    	System.out.println(toHexString(bs));
    	
    	String dierbu2=toHexString(bs).toLowerCase();
    	System.out.println("DES+密码:"+dierbu2);
    	
    	String MD5mima = MessageUtil.MD5(dierbu2, 32).toLowerCase(); //密码MD5加密
    	System.out.println("密码MD5加密:"+MD5mima);
    	//b16df5e069f3641435c9ce5dc282748e
    }   
  
}