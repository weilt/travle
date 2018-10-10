package com.hx.core.consts;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/9 18:02
 * @Description:
 */
public interface HxSystemConst {

    /**
     *  年代
     */
    String HX_YEAS_KEY = "HX_YEAS_KEY";

    /**
     *  年代有效时间 2 天
     */
    int EFFECTIVE_SECONDS = 2 * 24 * 60 * 60;


    /**
     *  用户性别
     */
    enum Sex {
        UNKNOWN(0,"未知"),
        MALE(1,"男"),
        FEMALE(2,"女"),;
        private int code;
        private String msg;
        Sex(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }

    /**
     * 导游审核状态
     */
    enum CiceroneStatus {
        STATUS_WAIT(1,"未审核"),
        STATUS_PASS(2,"通过"),
        STATUS_FAIL(3,"未通过"),;
        private int code;
        private String msg;

        CiceroneStatus(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }


}
