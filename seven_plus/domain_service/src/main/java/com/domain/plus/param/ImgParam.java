package com.domain.plus.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/9/7 16:18
 * @Description:
 */
@Data
@ApiModel(value="获取示意图")
public class ImgParam implements Serializable {
    @ApiModelProperty(value="图片类型 默认0：广告位 1：划痕无忧宣传图 2：天天洗车宣传图 3：二维码  4：车辆正面示意图  5:左前面示意图   6:右前面示意图  7:左侧面示意图  8:右侧面示意图  9:正后面示意图  10:划痕示意图  11:天天洗车示意图")
    private Integer type;
}
