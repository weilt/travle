package com.hwt.domain.entity.bureau;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class HwTravelBureau implements Serializable {
    /**
     *  主键
     */
    private Long bureau_id;

    /**
     * 旅行社登录账号
     */
    private String bureau_account;

    /**
     * 密码
     */
    private String password;

    /**
     * 密码盐 
     */
    private String pwd_salt;

    /**
     *  企业名称
     */
    private String company_name;

    /**
     * 工商营业执照注册号码
     */
    private String license_no;

    /**
     * 法人
     */
    private String legal_person;

    /**
     * 企业法人手机号
     */
    private String legal_person_phome;

    /**
     * 注册资本
     */
    private BigDecimal registered_capital;

    /**
     * 成立时间
     */
    private Long company_time;

    /**
     * 营业执照有效时期开始时间
     */
    private Long licen_validity_begin;

    /**
     * 营业执照有效时期到期时间
     */
    private Long licen_validity_end;

    /**
     * 是否一般纳税人 默认： 0 否  1 是 
     */
    private Integer taxpayer_state;

    /**
     * 组织机构代码
     */
    private String org_no;

    /**
     * 质量保证金(单位分)
     */
    private BigDecimal quality_deposit;

    /**
     * 总部地址
     */
    private String address;

    /**
     * 简介描述
     */
    private String description;

    /**
     * 联系人
     */
    private String contacts_name;

    /**
     * 联系人手机号
     */
    private String contacts_phome;

    /**
     * 联系人邮箱
     */
    private String contacts_emil;

    /**
     * 营业执照
     */
    private String license_url;

    /**
     * 经营许可证
     */
    private String busine_licen_url;

    /**
     * 旅行社责任险保单
     */
    private String duty_policy_url;

    /**
     * 法人身份证正面
     */
    private String identity_positive;

    /**
     * 法人身份证反面
     */
    private String identity_negative;

    /**
     * 未通过原因
     */
    private String reason;

    /**
     * 状态 默认 0 待处理，1 已完成，2 已拒绝 
     */
    private Integer state;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间
     */
    private Long create_time;

    /**
     * 处理时间
     */
    private Long examine_time;

    /**
     * 旅行社银行账户
     */
    private String bureau_bank_account;

    /**
     * 旅行社银行名称
     */
    private String bank_name;

    private static final long serialVersionUID = 1L;

    public Long getBureau_id() {
        return bureau_id;
    }

    public void setBureau_id(Long bureau_id) {
        this.bureau_id = bureau_id;
    }

    public String getBureau_account() {
        return bureau_account;
    }

    public void setBureau_account(String bureau_account) {
        this.bureau_account = bureau_account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPwd_salt() {
        return pwd_salt;
    }

    public void setPwd_salt(String pwd_salt) {
        this.pwd_salt = pwd_salt;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getLicense_no() {
        return license_no;
    }

    public void setLicense_no(String license_no) {
        this.license_no = license_no;
    }

    public String getLegal_person() {
        return legal_person;
    }

    public void setLegal_person(String legal_person) {
        this.legal_person = legal_person;
    }

    public String getLegal_person_phome() {
        return legal_person_phome;
    }

    public void setLegal_person_phome(String legal_person_phome) {
        this.legal_person_phome = legal_person_phome;
    }

    public BigDecimal getRegistered_capital() {
        return registered_capital;
    }

    public void setRegistered_capital(BigDecimal registered_capital) {
        this.registered_capital = registered_capital;
    }

    public Long getCompany_time() {
        return company_time;
    }

    public void setCompany_time(Long company_time) {
        this.company_time = company_time;
    }

    public Long getLicen_validity_begin() {
        return licen_validity_begin;
    }

    public void setLicen_validity_begin(Long licen_validity_begin) {
        this.licen_validity_begin = licen_validity_begin;
    }

    public Long getLicen_validity_end() {
        return licen_validity_end;
    }

    public void setLicen_validity_end(Long licen_validity_end) {
        this.licen_validity_end = licen_validity_end;
    }

    public Integer getTaxpayer_state() {
        return taxpayer_state;
    }

    public void setTaxpayer_state(Integer taxpayer_state) {
        this.taxpayer_state = taxpayer_state;
    }

    public String getOrg_no() {
        return org_no;
    }

    public void setOrg_no(String org_no) {
        this.org_no = org_no;
    }

    public BigDecimal getQuality_deposit() {
        return quality_deposit;
    }

    public void setQuality_deposit(BigDecimal quality_deposit) {
        this.quality_deposit = quality_deposit;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContacts_name() {
        return contacts_name;
    }

    public void setContacts_name(String contacts_name) {
        this.contacts_name = contacts_name;
    }

    public String getContacts_phome() {
        return contacts_phome;
    }

    public void setContacts_phome(String contacts_phome) {
        this.contacts_phome = contacts_phome;
    }

    public String getContacts_emil() {
        return contacts_emil;
    }

    public void setContacts_emil(String contacts_emil) {
        this.contacts_emil = contacts_emil;
    }

    public String getLicense_url() {
        return license_url;
    }

    public void setLicense_url(String license_url) {
        this.license_url = license_url;
    }

    public String getBusine_licen_url() {
        return busine_licen_url;
    }

    public void setBusine_licen_url(String busine_licen_url) {
        this.busine_licen_url = busine_licen_url;
    }

    public String getDuty_policy_url() {
        return duty_policy_url;
    }

    public void setDuty_policy_url(String duty_policy_url) {
        this.duty_policy_url = duty_policy_url;
    }

    public String getIdentity_positive() {
        return identity_positive;
    }

    public void setIdentity_positive(String identity_positive) {
        this.identity_positive = identity_positive;
    }

    public String getIdentity_negative() {
        return identity_negative;
    }

    public void setIdentity_negative(String identity_negative) {
        this.identity_negative = identity_negative;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public Long getExamine_time() {
        return examine_time;
    }

    public void setExamine_time(Long examine_time) {
        this.examine_time = examine_time;
    }

    public String getBureau_bank_account() {
        return bureau_bank_account;
    }

    public void setBureau_bank_account(String bureau_bank_account) {
        this.bureau_bank_account = bureau_bank_account;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

	public String getBureauPhone(Long bureau_id2) {
		// TODO Auto-generated method stub
		return null;
	}
}