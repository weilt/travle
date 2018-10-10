package com.hwt.domain.entity.order.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="订单人员")
public class HwOrderUserVo implements Serializable {
	
	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
    private Long order_user_id;
	
	 /**
     * 订单id
     */
	@ApiModelProperty(value = "订单id")
    private Long order_id;


    /**
     * 姓名
     */
	@ApiModelProperty(value = "姓名")
    private String name;

    /**
     * 证件类型 默认身份证
     */
	@ApiModelProperty(value = " 证件类型 默认身份证")
    private String identity_type;

    /**
     * 证件号码
     */
	@ApiModelProperty(value = "证件号码")
    private String identity_num;

    

    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
    private Long create_time;

	@ApiModelProperty(value = "用户保险信息")
	private List<HwOrderUserInsubranceVo> hwOrderUserInsubranceVos;

    private static final long serialVersionUID = 1L;

    public Long getOrder_user_id() {
        return order_user_id;
    }

    public void setOrder_user_id(Long order_user_id) {
        this.order_user_id = order_user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentity_type() {
        return identity_type;
    }

    public void setIdentity_type(String identity_type) {
        this.identity_type = identity_type;
    }

    public String getIdentity_num() {
        return identity_num;
    }

    public void setIdentity_num(String identity_num) {
        this.identity_num = identity_num;
    }


    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

	public List<HwOrderUserInsubranceVo> getHwOrderUserInsubranceVos() {
		return hwOrderUserInsubranceVos;
	}

	public void setHwOrderUserInsubranceVos(List<HwOrderUserInsubranceVo> hwOrderUserInsubranceVos) {
		this.hwOrderUserInsubranceVos = hwOrderUserInsubranceVos;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}
	
}