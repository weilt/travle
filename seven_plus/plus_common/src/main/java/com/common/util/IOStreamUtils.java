package com.common.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * IO流转换工具
 * created by zengyh on 2018/3/17
 */
public class IOStreamUtils {

    /**
     * 转换为字符串-流只能被read一次，请勿重复读取
     * @param inputStream
     * @return
     */
    public static String toString(InputStream inputStream) throws IOException {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            int i;
            while ((i = inputStream.read()) != -1) {
                baos.write(i);
            }
            return baos.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }
        return null;
    }

    /**
     * 读取流为字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            int i;
            while((i = inputStream.read()) != -1){
                baos.write(i);
                return baos.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            inputStream.close();
        }

        return null;
    }
}
