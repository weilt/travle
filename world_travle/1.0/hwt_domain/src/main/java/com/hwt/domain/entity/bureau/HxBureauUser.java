package com.hwt.domain.entity.bureau;

import java.io.Serializable;

/**
 * @author 
 */
public class HxBureauUser implements Serializable {
    /**
     * id
     */
    private Long bureau_user_id;

    /**
     * 账号
     */
    private String bureau_user_account;

    /**
     * 密码盐
     */
    private String pwd_salt;

    /**
     * 密码
     */
    private String password;

    /**
     * 旅行社id
     */
    private Long bureau_id;

    /**
     * 旅行社角色id
     */
    private Long bureau_role_id;

    /**
     * 创建时间
     */
    private Long create_time;

    /**
     * 网易云信id
     */
    private String im_id;

    /**
     * 第三方通信token
     */
    private String im_token;

    /**
     * 通信token更新时间
     */
    private String im_token_time;

    /**
     * 是否是法人  0-不是 1-是
     */
    private Integer is_legal;

    /**
     * 是否禁用 1-否 2-是
     */
    private Integer is_delete;

    /**
     * 联系方式
     */
    private String phone;

    /**
     * 真实姓名
     */
    private String real_name;

    /**
     * 描述
     */
    private String description;

    /**
     * 备注
     */
    private String remarks;

    private static final long serialVersionUID = 1L;

    public Long getBureau_user_id() {
        return bureau_user_id;
    }

    public void setBureau_user_id(Long bureau_user_id) {
        this.bureau_user_id = bureau_user_id;
    }

    public String getBureau_user_account() {
        return bureau_user_account;
    }

    public void setBureau_user_account(String bureau_user_account) {
        this.bureau_user_account = bureau_user_account;
    }

    public String getPwd_salt() {
        return pwd_salt;
    }

    public void setPwd_salt(String pwd_salt) {
        this.pwd_salt = pwd_salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getBureau_id() {
        return bureau_id;
    }

    public void setBureau_id(Long bureau_id) {
        this.bureau_id = bureau_id;
    }

    public Long getBureau_role_id() {
        return bureau_role_id;
    }

    public void setBureau_role_id(Long bureau_role_id) {
        this.bureau_role_id = bureau_role_id;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

    public String getIm_id() {
        return im_id;
    }

    public void setIm_id(String im_id) {
        this.im_id = im_id;
    }

    public String getIm_token() {
        return im_token;
    }

    public void setIm_token(String im_token) {
        this.im_token = im_token;
    }

    public String getIm_token_time() {
        return im_token_time;
    }

    public void setIm_token_time(String im_token_time) {
        this.im_token_time = im_token_time;
    }

    public Integer getIs_legal() {
        return is_legal;
    }

    public void setIs_legal(Integer is_legal) {
        this.is_legal = is_legal;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}