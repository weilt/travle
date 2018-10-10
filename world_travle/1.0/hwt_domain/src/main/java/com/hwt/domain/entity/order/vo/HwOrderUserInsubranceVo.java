package com.hwt.domain.entity.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="用户保险信息")
public class HwOrderUserInsubranceVo implements Serializable {
    /**
     * id
     */
	@ApiModelProperty(value = "id" )
    private Long user_insubrance_id;

    /**
     * 订单用户id
     */
	@ApiModelProperty(value = "订单用户id" )
    private Long order_user_id;

    /**
     * 保险id
     */
	@ApiModelProperty(value = " 保险id" )
    private Long insubrance_id;

    /**
     * 保险价格
     */
	@ApiModelProperty(value = "保险价格" )
    private BigDecimal insurance_price;

    /**
     * 保险名称
     */
	@ApiModelProperty(value = "保险名称" )
    private String insurance_name;

    /**
     * 保险链接
     */
	@ApiModelProperty(value = "保险链接" )
    private String insurance_url;

    /**
     * 保险公司
     */
	@ApiModelProperty(value = "保险公司" )
    private String insurance_company;

    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间" )
    private Long create_tiem;

    /**
     * 保险描述
     */
	@ApiModelProperty(value = "保险描述" )
    private String insurance_dec;

    private static final long serialVersionUID = 1L;

    public Long getUser_insubrance_id() {
        return user_insubrance_id;
    }

    public void setUser_insubrance_id(Long user_insubrance_id) {
        this.user_insubrance_id = user_insubrance_id;
    }

    public Long getOrder_user_id() {
        return order_user_id;
    }

    public void setOrder_user_id(Long order_user_id) {
        this.order_user_id = order_user_id;
    }

    public Long getInsubrance_id() {
        return insubrance_id;
    }

    public void setInsubrance_id(Long insubrance_id) {
        this.insubrance_id = insubrance_id;
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

    public Long getCreate_tiem() {
        return create_tiem;
    }

    public void setCreate_tiem(Long create_tiem) {
        this.create_tiem = create_tiem;
    }

    public String getInsurance_dec() {
        return insurance_dec;
    }

    public void setInsurance_dec(String insurance_dec) {
        this.insurance_dec = insurance_dec;
    }
}