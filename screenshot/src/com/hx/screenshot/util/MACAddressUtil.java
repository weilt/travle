package com.hx.screenshot.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/3 15:33
 * @Description:
 */
public class MACAddressUtil {


    /**
     * 获取MAC地址的方法
     * @return
     */
    public static String getMACAddress() {
        InetAddress inetAddress = null;
        byte[] mac = new byte[0];
        try {
            inetAddress = InetAddress.getLocalHost();
            //获得网络接口对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。
            mac = NetworkInterface.getByInetAddress(inetAddress).getHardwareAddress();
        } catch (UnknownHostException e){
            e.printStackTrace();
        }catch (SocketException e) {
            e.printStackTrace();
        }
        //下面代码是把mac地址拼装成String
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<mac.length;i++){
            if(i!=0){
                sb.append("-");
            }
            //mac[i] & 0xFF 是为了把byte转化为正整数
            String s = Integer.toHexString(mac[i] & 0xFF);
            sb.append(s.length()==1?0+s:s);
        }
        //把字符串所有小写字母改为大写成为正规的mac地址并返回
        return sb.toString().toUpperCase();
    }

}
