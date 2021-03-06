package com.domain.admin.entity;

import java.io.Serializable;
import java.util.Date;

import com.common.util.GsonUtil;
import com.fasterxml.jackson.annotation.JsonFormat;


/**
 * 后台管理用户表
 * @author LiuGang
 */
public class AdminUser implements Serializable{
	/**
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * id
	 */
    private Long id;
    /**
     * 用户名 用于登陆后台系统
     */
    private String userName;
    /**
     * 密码 MD5加密处理
     */
    private String passWord;
    /**
     * 工作职位
     */
    private String jobPosition;
    /**
     * 角色Id
     */
    private Integer roleId;
    /**
     * 真实姓名
     */
    private String realName;
    /**
     * 性别 2女，1男，0保密
     */
    private Byte sex;
    /**
     * 联系地址
     */
    private String address;
    /**
     * 关于我（简介）
     */
    private String aboutMe;
    /**
     * 电话号码
     */
    private String telephone;
    /**
     * 创建时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createTime;
    /**
     * 创建人 ID
     */
    private Integer userId;
    /**
     * 是否删除 默认 1正常 2-禁用
     */
    private Byte isDelete;
    /**
     * 微信
     */
    private String wchat;
    /**
     * 用户头像 地址
     */
    private String userImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord == null ? null : passWord.trim();
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition == null ? null : jobPosition.trim();
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName == null ? null : realName.trim();
    }

    public Byte getSex() {
        return sex;
    }

    public void setSex(Byte sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getAboutMe() {
        return aboutMe;
    }

    public void setAboutMe(String aboutMe) {
        this.aboutMe = aboutMe == null ? null : aboutMe.trim();
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Byte getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Byte isDelete) {
        this.isDelete = isDelete;
    }

    public String getWchat() {
        return wchat;
    }

    public void setWchat(String wchat) {
        this.wchat = wchat == null ? null : wchat.trim();
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage == null ? null : userImage.trim();
    }
    
    public String toString() {
		return GsonUtil.toJson(this, AdminUser.class);
	}
}