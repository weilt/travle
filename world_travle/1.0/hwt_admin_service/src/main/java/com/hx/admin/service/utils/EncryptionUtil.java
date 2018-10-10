package com.hx.admin.service.utils;

import com.hx.core.utils.Md5Utils;

/**
 * Created by Ro on 2018/4/23.
 * 后台管理加密工具类
 */
public class EncryptionUtil {

    /**
     * 加密秘钥
     */
    private static final String encryptSecretKey = "HXKJADMIN!@#";

    /**
     * 后台用户登陆密码加密方式一
     * @param password
     * @return
     */
    public static String userPasswordEncrypt(String password) {
       return Md5Utils.encodeMD5(encryptSecretKey + password);
    }
    
    public static void main(String[] args) {
		System.out.println(userPasswordEncrypt("123456"));
	}
    /**
     * 后台用户登陆密码加密方式二(暂时不用)
     * @param password
     * @return
     */
    @Deprecated
    public static String userPasswordEncrypt(String account, String password) {
        return Md5Utils.encodeMD5(encryptSecretKey + account + password);
    }

}
