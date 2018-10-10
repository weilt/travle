package com.hx.core.wyim.ntes;




import java.io.Serializable;
import java.util.Date;

import com.hx.core.utils.RandomUtils;

/**
 * Created by RO on 2018/3/7.
 * 网易云信统一Header 设置
 */
public class NtesHeader implements Serializable {

    /**
     * 开发者平台分配的appkey
     */
    private String appKey;
    /**
     * 随机数（最大长度128个字符）
     */
    private String nonce;
    /**
     * 当前UTC时间戳，从1970年1月1日0点0 分0 秒开始到现在的秒数(String)
     */
    private String curTime;
    /**
     * SHA1(AppSecret + Nonce + CurTime),三个参数拼接的字符串，进行SHA1哈希计算，转化成16进制字符(String，小写)
     * CheckSum有效期：出于安全性考虑，每个checkSum的有效期为5分钟(用CurTime计算)，建议每次请求都生成新的checkSum，
     * 同时请确认发起请求的服务器是与标准时间同步的，比如有NTP服务。
     * CheckSum检验失败时会返回414错误码，具体参看code状态表。
     */
    private String checkSum;

    public NtesHeader() {
        this.appKey = NtesCommon.APP_KEY;
        this.nonce = RandomUtils.randomString(16);
        this.curTime = String.valueOf((new Date()).getTime() / 1000L);
        this.checkSum = NtesCheckSumBuilder.getCheckSum(NtesCommon.APP_SECRET,nonce,curTime);
    }

    public String getAppKey() {
        return appKey;
    }

    public String getNonce() {
        return nonce;
    }

    public String getCurTime() {
        return curTime;
    }

    public String getCheckSum() {
        return checkSum;
    }
}
