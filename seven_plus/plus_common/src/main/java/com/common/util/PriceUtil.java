package com.common.util;

import java.math.BigDecimal;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/15 17:03
 * @Description:
 */
public class PriceUtil {

    /**
     * 基础费用key
     */
    public static final String SCRATCH_BASE_PRICE = "scratchBasePrice";

    /**
     * 运营费率kdy
     */
    public static final String SCRATCH_OPERATION_RATE = "scratchOperationRate";

    /**
     * 车龄费率key
     */
    public static final String SCRATCH_AGE_RATE = "scratchAgeRate";

    /**
     * 附加费率key
     */
    public static final String SCRATCH_ADDITIONA_RATE = "scratchAdditionaRate";

    /**
     *  计算划痕无忧价格  计算公式 基础费用 + 基础费用 * （用途费率 + 车龄费率 + 附加费率）
     * @param scratchBasePrice 基础费用
     * @param scratchOperationRate 用途费率
     * @param scratchAgeRate 车龄费率
     * @param scratchAdditionaRate 附加费率
     * @return 返回分
     */
    public static Long getPrice(Integer scratchBasePrice, Integer scratchOperationRate, Integer scratchAgeRate,Integer scratchAdditionaRate){
        BigDecimal basePrice = new BigDecimal(scratchBasePrice);
        BigDecimal b1 = new BigDecimal(scratchOperationRate);
        BigDecimal b2 = new BigDecimal(scratchAgeRate);
        BigDecimal b3 = new BigDecimal(scratchAdditionaRate);
        BigDecimal b4 = b1.add(b2).add(b3);
        return b4.divide(new BigDecimal(100), 3, BigDecimal.ROUND_HALF_UP).multiply(basePrice).add(basePrice).multiply(new BigDecimal(100)).longValue();
    }



    /**
     * 加法
     *
     * @param var1
     * @param var2
     * @return
     */
    public static double add(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.add(b2).doubleValue();

    }

    /**
     * 减法
     *
     * @param var1
     * @param var2
     * @return
     */

    public static double sub(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 乘法
     *
     * @param var1
     * @param var2
     * @return
     */
    public static double mul(double var1, double var2) {
        BigDecimal b1 = new BigDecimal(Double.toString(var1));
        BigDecimal b2 = new BigDecimal(Double.toString(var2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 除法
     *
     * @param v1
     * @param v2
     * @param scale 精度，到小数点后几位
     * @return
     */

    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or ");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();

    }

    /**
     * 四舍五入
     * @param v
     * @param scale 精确位数
     * @return
     */
    public static double round(double v, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException("The scale must be a positive integer or zero");
        }
        BigDecimal b = new BigDecimal(Double.toString(v));
        BigDecimal one = new BigDecimal("1");
        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

}
