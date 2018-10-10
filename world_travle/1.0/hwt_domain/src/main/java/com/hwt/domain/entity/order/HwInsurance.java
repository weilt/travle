package com.hwt.domain.entity.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class HwInsurance implements Serializable {
    /**
     * 保险id
     */
    private Long insurance_id;

    /**
     * 保险价格
     */
    private BigDecimal insurance_price;

    /**
     * 保险名称
     */
    private String insurance_name;

    /**
     * 保险链接
     */
    private String insurance_url;

    /**
     * 保险公司
     */
    private String insurance_company;

    /**
     * 0-无效  1-有效
     */
    private Integer is_hide;

    /**
     * 旅行社id  0-为系统自己的保险
     */
    private Long bureau_id;

    /**
     * 是否是默认 0-不是  1-是 注意默认只能有一个
     */
    private Integer is_default;

    /**
     * 创建时间
     */
    private Long create_time;

    /**
     * 保险描述
     */
    private String insurance_dec;

    private static final long serialVersionUID = 1L;

    public Long getInsurance_id() {
        return insurance_id;
    }

    public void setInsurance_id(Long insurance_id) {
        this.insurance_id = insurance_id;
    }

    public BigDecimal getInsurance_price() {
        return insurance_price;
    }

    public void setInsurance_price(BigDecimal insurance_price) {
        this.insurance_price = insurance_price;
    }

    public String getInsurance_name() {
        return insurance_name;
    }

    public void setInsurance_name(String insurance_name) {
        this.insurance_name = insurance_name;
    }

    public String getInsurance_url() {
        return insurance_url;
    }

    public void setInsurance_url(String insurance_url) {
        this.insurance_url = insurance_url;
    }

    public String getInsurance_company() {
        return insurance_company;
    }

    public void setInsurance_company(String insurance_company) {
        this.insurance_company = insurance_company;
    }

    public Integer getIs_hide() {
        return is_hide;
    }

    public void setIs_hide(Integer is_hide) {
        this.is_hide = is_hide;
    }

    public Long getBureau_id() {
        return bureau_id;
    }

    public void setBureau_id(Long bureau_id) {
        this.bureau_id = bureau_id;
    }

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public String getInsurance_dec() {
        return insurance_dec;
    }

    public void setInsurance_dec(String insurance_dec) {
        this.insurance_dec = insurance_dec;
    }
}