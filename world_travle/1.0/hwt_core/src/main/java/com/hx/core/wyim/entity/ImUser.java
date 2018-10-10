package com.hx.core.wyim.entity;

import java.io.Serializable;

/**
 * Created by RO on 2018/3/7.
 * 通信用户信息对象
 * 注：当前版本默认使用网易云信的用户信息创建对象，如有其它第三方可公用字段
 */
public class ImUser implements Serializable{

    private String accid;   //网易云通信ID，最大长度32字符，必须保证一个APP内唯一（只允许字母、数字、半角下划线_、@、半角点以及半角-组成，不区分大小写，会统一小写处理，请注意以此接口返回结果中的accid为准）。
    private String name;    //网易云通信ID昵称，最大长度64字符，用来PUSH推送时显示的昵称
    private String props;   //json属性，第三方可选填，最大长度1024字符
    private String icon;    //网易云通信ID头像URL，第三方可选填，最大长度1024
    private String token;   //网易云通信ID可以指定登录token值，最大长度128字符，并更新，如果未指定，会自动生成token，并在创建成功后返回
    private String sign;    //用户签名，最大长度256字符
    private String email;   //用户email，最大长度64字符
    private String birth;   //用户生日，最大长度16字符
    private String mobile;  //用户mobile，最大长度32字符
    private int gender;     //用户性别，0表示未知，1表示男，2女表示女，其它会报参数错误
    private String ex;      //用户名片扩展字段，最大长度1024字符，用户可自行扩展，建议封装成JSON字符串

    public ImUser(String accid) {
        this.accid = accid;
    }

    public ImUser(String accid, String name) {
        this.accid = accid;
        this.name = name;
    }

    public ImUser(String accid, String name, String icon) {
        this.accid = accid;
        this.name = name;
        this.icon = icon;
    }

    public String getAccid() {
        return accid;
    }

    public void setAccid(String accid) {
        this.accid = accid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public String getEx() {
        return ex;
    }

    public void setEx(String ex) {
        this.ex = ex;
    }

    @Override
    public String toString() {
        return "ImUser{" +
                "accid='" + accid + '\'' +
                ", name='" + name + '\'' +
                ", props='" + props + '\'' +
                ", icon='" + icon + '\'' +
                ", token='" + token + '\'' +
                ", sign='" + sign + '\'' +
                ", email='" + email + '\'' +
                ", birth='" + birth + '\'' +
                ", mobile='" + mobile + '\'' +
                ", gender=" + gender +
                ", ex='" + ex + '\'' +
                '}';
    }
}
