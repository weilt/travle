package com.hwt.domain.entity.order;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 常用用户
 * @author 
 */
@ApiModel(value=" 常用用户")
public class HwOrderUsuallyUser implements Serializable {
    /**
     * 常用id  
     */
	@ApiModelProperty(value = "常用id" ,hidden=true)
    private Long usually_id;

    /**
     * 用户id
     */
	@ApiModelProperty(value = "用户id" ,hidden=true)
    private Long user_id;

    /**
     * 姓名
     */
	@ApiModelProperty(value = " 姓名")
    private String name;

    /**
     * 英文姓
     */
	@ApiModelProperty(value = " 英文姓")
    private String eng_surname;

    /**
     * 英文名
     */
	@ApiModelProperty(value = " 英文名")
    private String eng_name;

    /**
     * 证件类型 默认身份证
     */
	@ApiModelProperty(value = "证件类型 默认身份证")
    private String identity_type;

    /**
     * 证件号码
     */
	@ApiModelProperty(value = "证件号码")
    private String identity_num;

    /**
     * 证件有效期
     */
	@ApiModelProperty(value = "证件有效期")
    private Long identity_effective;

    /**
     * 生日
     */
	@ApiModelProperty(value = "生日")
    private Long birthday;

    /**
     * 性别  1-男 2- 女   默认1
     */
	@ApiModelProperty(value = "性别  1-男 2- 女   默认1")
    private Integer sex;

    /**
     * 手机号码
     */
	@ApiModelProperty(value = " 手机号码")
    private String phone;

    /**
     * 是否是自己  0-否 1-是  默认0
     */
	@ApiModelProperty(value = "是否是自己  0-否 1-是  默认0")
    private Integer is_own;

    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间" ,hidden=true)
    private Long create_time;

    private static final long serialVersionUID = 1L;

    public Long getUsually_id() {
        return usually_id;
    }

    public void setUsually_id(Long usually_id) {
        this.usually_id = usually_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEng_surname() {
        return eng_surname;
    }

    public void setEng_surname(String eng_surname) {
        this.eng_surname = eng_surname;
    }

    public String getEng_name() {
        return eng_name;
    }

    public void setEng_name(String eng_name) {
        this.eng_name = eng_name;
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

    public Long getIdentity_effective() {
        return identity_effective;
    }

    public void setIdentity_effective(Long identity_effective) {
        this.identity_effective = identity_effective;
    }

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIs_own() {
        return is_own;
    }

    public void setIs_own(Integer is_own) {
        this.is_own = is_own;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}