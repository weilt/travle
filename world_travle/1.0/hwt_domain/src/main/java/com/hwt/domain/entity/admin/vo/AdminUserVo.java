package com.hwt.domain.entity.admin.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户返回信息
 * @author 
 */
public class AdminUserVo implements Serializable {
    /**
     * id
     */
    private Long user_id;

    /**
     * 用户名 用于登陆后台系统
     */
    private String user_account;

 

    /**
     * 用户头像 地址
     */
    private String user_image;

    /**
     * 工作职位
     */
    private String job_position;

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
     * 角色名称
     */
    private String roleName;
 
    /**
     * 电话号码
     */
    private String telephone;

    /**
     * 创建时间
     */
    private Date create_time;

    
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

    

    public Integer getIsenable() {
        return isenable;
    }

    public void setIsenable(Integer isenable) {
        this.isenable = isenable;
    }

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
    
}