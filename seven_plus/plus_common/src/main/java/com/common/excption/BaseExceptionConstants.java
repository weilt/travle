/*
 *  Copyright (c) 2015.  meicanyun.com Corporation Limited.
 *  All rights reserved.
 *
 *  This software is the confidential and proprietary information of
 *  meicanyun Company. ("Confidential Information").  You shall not
 *  disclose such Confidential Information and shall use it only in
 *  accordance with the terms of the license agreement you entered into
 *  with meicanyun.com.
 */

package com.common.excption;

/**
 * @author xiong
 */
public interface BaseExceptionConstants {
    interface StartCode{
        /**
         * 微信api相关异常
         */
        long WX_CODE =3000;


        /**
         * 公共错误code开始10000结束于11999
         */
        long COMMON_CODE = 10000;
    }

    /**
     * 获取错误code
     * @return
     */
    long getCode();

    /**
     * 获取错误信息
     * @return
     */
    String getMessage();

    /**
     * 构建一个异常
     * @return
     */
    default BaseException build(){
        return BaseException.build(this);
    }

    /**
     * 构建一个异常 带异常链
     * @param cause
     * @return
     */
    default BaseException build(Throwable cause){
        return BaseException.build(this,cause);
    }

    /**
     * 比较
     * @param code
     * @return
     */
    default boolean equals(Long code){
        return code!=null && code==this.getCode()? Boolean.TRUE:Boolean.FALSE;
    }
}
