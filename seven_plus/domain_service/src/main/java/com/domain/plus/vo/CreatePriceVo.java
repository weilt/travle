package com.domain.plus.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/20 15:05
 * @Description:
 */
@ApiModel(value="订单信息")
public class CreatePriceVo implements Serializable {

    @ApiModelProperty(value="办理业务 1：天天洗车 2：划痕无忧")
    private Integer orderType;

    @ApiModelProperty(value="车牌号码")
    private String carNO;

    @ApiModelProperty(value="品牌型号")
    private String carBrand;

    @ApiModelProperty(value="车辆性质 默认0 非运营 1：运营")
    private Integer carNature;

    @ApiModelProperty(value="车龄年限 0：三年以下 1：三年到五年以下 2：五年以上")
    private Integer drivingAge;

    @ApiModelProperty(value="车辆类型 默认0：五座以下 1：五座到七座")
    private Integer carType;

    @ApiModelProperty(value="到期时间 (时间戳)")
    private Long expireTime;

    @ApiModelProperty(value="使用次数")
    private Integer count;

    @ApiModelProperty(value="价格（分）")
    private Long price;

    public Integer getOrderType() {
        return orderType;
    }

    public void setOrderType(Integer orderType) {
        this.orderType = orderType;
    }

    public String getCarNO() {
        return carNO;
    }

    public void setCarNO(String carNO) {
        this.carNO = carNO;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public Integer getCarNature() {
        return carNature;
    }

    public void setCarNature(Integer carNature) {
        this.carNature = carNature;
    }

    public Integer getDrivingAge() {
        return drivingAge;
    }

    public void setDrivingAge(Integer drivingAge) {
        this.drivingAge = drivingAge;
    }

    public Integer getCarType() {
        return carType;
    }

    public void setCarType(Integer carType) {
        this.carType = carType;
    }

    public Long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Long expireTime) {
        this.expireTime = expireTime;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }
}
