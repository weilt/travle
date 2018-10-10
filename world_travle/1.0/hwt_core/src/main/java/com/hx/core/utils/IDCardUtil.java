package com.hx.core.utils;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/19 09:26
 * @Description: 身份证验证工具
 */
public class IDCardUtil {
    // 正则格式
    private static Pattern pattern = Pattern.compile("^\\d{17}[\\d|X]$");

    /**
     * 省、直辖市代码表，身份证号的前6位为地址信息，我们只验证前两位
     */
    private static final String CITY_CODE[] = {
            "11", "12", "13", "14", "15", "21", "22", "23", "31", "32", "33", "34", "35", "36", "37", "41",
            "42", "43", "44", "45", "46", "50", "51", "52", "53", "54", "61", "62", "63", "64", "65", "71",
            "81", "82", "91"
    };

    /**
     * 每位加权因子
     */
    private static final int POWER[] = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    /**
     * 第18位校检码
     **/
    private static final String VERIFY_CODE[] = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};

    /**
     * 验证身份证
     *
     * @param IDCardNo
     * @return
     */
    public static boolean checkNo(String IDCardNo) {
        //1.格式验证
        if (null == IDCardNo || !pattern.matcher(IDCardNo = IDCardNo.toUpperCase()).matches()) {
            return false;
        }
        //2.验证省、直辖市代码。市、区不作验证，没有规则限制，数字即可
        if (Arrays.binarySearch(CITY_CODE, IDCardNo.substring(0, 2)) == -1) {
            return false;
        }
        //3.顺序码不作验证，没有规则限制，数字即可
        //4.验证位验证，计算规则为：身份证前17位数字，对应乘以每位的权重因子，然后相加得到数值X，与11取模获得余数，得到数值Y,通过Y得到校验码。
        String verifyCode = VERIFY_CODE[getPowerSum(IDCardNo) % 11];
        if (!verifyCode.equals(IDCardNo.substring(17, 18))) {
            return false;
        }
        return true;
    }

    /**
     * 取得身份证号前17位与对应的权重值相乘的和
     *
     * @return
     */
    private static int getPowerSum(String IDCardNo) {
        int sum = 0;
        // 身份证前17位
        char[] fix17 = IDCardNo.substring(0, 17).toCharArray();
        for (int i = 0; i <= 16; i++) {
            sum += (Integer.parseInt(fix17[i] + "") * POWER[i]);
        }
        return sum;
    }



    /**
     * 转换成日期
     *
     * @param IDCardNo
     * @return
     */
    public static Long getBirthDay(String IDCardNo) {
        if (!checkNo(IDCardNo)){
            return null;
        }
        char[] idCardNo = IDCardNo.toCharArray();
        String year = String.copyValueOf(idCardNo, 6, 4);
        String month = String.copyValueOf(idCardNo, 10, 2);
        String day = String.copyValueOf(idCardNo, 12, 2);
        LocalDateTime localDateTime = LocalDateTime.of(Integer.parseInt(year), Month.of(Integer.parseInt(month)), Integer.parseInt(day), 12, 12, 12);
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     * 从身份证号中获取性别
     *
     * @param IDCardNo
     * @return 0:获取失败，1:男，2:女
     */
    public static int getGender(String IDCardNo) {
        if (!checkNo(IDCardNo)) {
            return 0;
        }
        // 奇男，偶女
        return (Integer.parseInt(IDCardNo.substring(16, 17)) % 2) == 0 ? 2 : 1;
    }


}
