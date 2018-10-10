package com.common.consts;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/9 18:17
 * @Description:
 */
public interface Consts {


    /**
     * 一年的毫秒数
     */
    Long YEAR_MILLISECOND = 365 * 24 * 60 * 60 * 1000L;

    /**
     * 洗车价格配置key
     */
    String CAR_WASH_PRICE_KEY = "carWashPrice";

    /**
     * 划痕使用次数
     */
    String CAR_SCRATCH_COUNT = "carScratchCount";

    /**
     * 天天洗车
     */
    String CAR_WASH_COUNT = "carWashCount";

    /**
     * Header - key
     */
    String USER_CONTEXT_TOKEN = "UserContext";

    /**
     *  文章类型
     */
    enum PlusArticleType  {
        //1： 划痕无忧  2：天天洗车 3：会员协议 4：车友群介绍 5:道路抢险 6：代办车贷
        TYPE_1(1,"划痕无忧"),
        TYPE_2(2,"天天洗车"),
        TYPE_3(3,"会员协议"),
        TYPE_4(4,"车友群介绍"),
        TYPE_5(5,"道路抢险"),
        TYPE_6(6,"代办车贷"),

        ;

        private int code;

        private String type;

        PlusArticleType(int code, String type) {
            this.code = code;
            this.type = type;
        }

        public String getType() {
            return type;
        }

        public int getCode() {
            return code;
        }

        public static String getType(int code) {
            for (PlusArticleType articleType : PlusArticleType.values()) {
                if (articleType.getCode() == code) {
                    return articleType.getType();
                }
            }
            return null;
        }
    }

    /**
     * 图片类型
     */
    enum PlusImgType {
        //图片类型 默认0：广告位 1：划痕无忧宣传图 2：天天洗车宣传图 3：二维码  4：车辆正面示意图  5:左前面示意图
        // 6:右前面示意图  7:左侧面示意图  8:右侧面示意图  9:正后面示意图  10:划痕示意图  11:天天洗车示意图
        IMG_TYPE_1(1,"划痕无忧宣传图"),
        IMG_TYPE_2(2,"天天洗车宣传图"),
        IMG_TYPE_3(3,"二维码"),
        IMG_TYPE_4(4,"车辆正面示意图"),
        IMG_TYPE_5(5,"左前面示意图"),
        IMG_TYPE_6(6,"右前面示意图"),
        IMG_TYPE_7(7,"左侧面示意图"),
        IMG_TYPE_8(8,"右侧面示意图"),
        IMG_TYPE_9(9,"正后面示意图"),
        IMG_TYPE_0(0,"广告轮播图"),
        IMG_TYPE_10(10,"划痕示意图"),
        IMG_TYPE_11(11,"天天洗车示意图"),
        ;

        private int code;

        private String msg;

        PlusImgType(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        /**
         * 通过code获取类型名称
         * @param code
         * @return
         */
        public static String getImgType(int code) {
            for (PlusImgType articleType : PlusImgType.values()) {
                if (articleType.getCode() == code) {
                    return articleType.getMsg();
                }
            }
            return null;
        }
    }

    /**
     * 订单类型
     */
    enum PlusOrderType {
        TYPE_1(1,"天天洗车"),
        TYPE_2(2,"划痕无忧"),
        ;
        private int code;
        private String describe;

        PlusOrderType(int code, String describe) {
            this.code = code;
            this.describe = describe;
        }

        public int getCode() {
            return code;
        }

        public String getDescribe() {
            return describe;
        }
    }


    /**
     * 订单状态
     */
    enum OrderIsExpire {
        normal(0,"正常"),
        expire(1,"过期"),
        paying(2,"支付中"),
        payfail(3,"支付失败"),
        ;
        private int code;
        private String msg;

        OrderIsExpire(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    enum RenewType {
        NEW(0,"新开通"),
        RENEW(1,"续费"),
        ;

        private int code;
        private String msg;

        RenewType(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
    }

    /**
     * 0：支付中，1：支付成功，2：支付失败
     */
    enum RenewState {
        paying,
        success,
        fail,
        ;
    }

    /**
     * 状态 默认0 未审核 1：审核通过 2：审核失败
     */
    enum RecordState {
        UNAUDITED,
        AUDIT_PASS,
        AUDIT_FAILURE,
        ;
    }

    /**
     * 网点类型
     */
    enum StoreType {
        wash_type,
        nike_type,
        ;
    }

}
