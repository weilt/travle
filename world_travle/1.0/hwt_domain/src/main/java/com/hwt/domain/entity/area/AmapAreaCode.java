package com.hwt.domain.entity.area;

import java.io.Serializable;

/**
 * @author 
 */
public class AmapAreaCode implements Serializable {
    /**
     * id
     */
    private Long id;

    /**
     * 行政区划级别
     */
    private String level;

    /**
     * 省级编码
     */
    private String province_code;

    /**
     * 省级名称
     */
    private String province_name;

    /**
     * 市级编码
     */
    private String city_code;

    /**
     * 市级名称
     */
    private String city_name;

    /**
     * 区级编码
     */
    private String district_code;

    /**
     * 区级名称
     */
    private String district_name;

    /**
     * 区号
     */
    private String area_code;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getProvince_code() {
        return province_code;
    }

    public void setProvince_code(String province_code) {
        this.province_code = province_code;
    }

    public String getProvince_name() {
        return province_name;
    }

    public void setProvince_name(String province_name) {
        this.province_name = province_name;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getDistrict_code() {
        return district_code;
    }

    public void setDistrict_code(String district_code) {
        this.district_code = district_code;
    }

    public String getDistrict_name() {
        return district_name;
    }

    public void setDistrict_name(String district_name) {
        this.district_name = district_name;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }
}