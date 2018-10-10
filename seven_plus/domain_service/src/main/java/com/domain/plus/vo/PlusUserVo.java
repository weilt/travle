package com.domain.plus.vo;

import com.domain.plus.entity.PlusUser;
import lombok.Data;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/8 19:48
 * @Description:
 */

@Data
public class PlusUserVo extends PlusUser {


    /**
     * 是否办理划痕
     */
    private Integer isNick;

    /**
     *
     * 是否办理天天洗车
     */
    private Integer isWash;

    /**
     * 洗车次数
     */
    private Integer washCount;

    /**
     * 划痕次数
     */
    private Integer nickCount;

    /**
     * 车牌号
     */
    private String carNo;

}
