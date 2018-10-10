package com.hwt.domain.entity.admin;

import java.io.Serializable;
import java.util.Date;

/**
 * @author 
 */
public class AdminUser implements Serializable {
    /**
     * id
     */
    private Long user_id;

    /**
     * 用户名 用于登陆后台系统
     */
    private String user_account;

    /**
     * 密码 MD5加密处理
     */
    private String password;

    /**
     * 用户头像 地址
     */
    private String user_image;

    /**
     * 工作职位
     */
    private String job_position;

    /**
     * 角色id
     */
    private Integer role_id;

    /**
     * 真实姓名
     */
    private String real_name;

    /**
     * 2女，1男，0保密
     */
    private Integer sex;

    /**
     * 联系地址
     */
    private String address;

    /**
     * 关于我（简介）
     */
    private String about_me;

    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 创建人 ID
     */
    private Long opt_user_id;

    /**
     * 是否删除 默认 1正常 2-禁用
     */
    private Integer isenable;

    private static final long serialVersionUID = 1L;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public String getUser_account() {
        return user_account;
    }

    public void setUser_account(String user_account) {
        this.user_account = user_account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser_image() {
        return user_image;
    }

    public void setUser_image(String user_image) {
        this.user_image = user_image;
    }

    public String getJob_position() {
        return job_position;
    }

    public void setJob_position(String job_position) {
        this.job_position = job_position;
    }

    public Integer getRole_id() {
        return role_id;
    }

    public void setRole_id(Integer role_id) {
        this.role_id = role_id;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAbout_me() {
        return about_me;
    }

    public void setAbout_me(String about_me) {
        this.about_me = about_me;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Long getOpt_user_id() {
        return opt_user_id;
    }

    public void setOpt_user_id(Long opt_user_id) {
        this.opt_user_id = opt_user_id;
    }

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }
}