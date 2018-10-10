package com.hwt.domain.entity.user.Vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/7/17 15:10
 * @Description:
 */
@ApiModel(value="年代信息返回")
public class HxYearsVo implements Serializable {
    @ApiModelProperty(value= "年")
    private Integer year;
    @ApiModelProperty(value= "年代")
    private String yearValue;

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getYearValue() {
        return yearValue;
    }

    public void setYearValue(String yearValue) {
        this.yearValue = yearValue;
    }
}
