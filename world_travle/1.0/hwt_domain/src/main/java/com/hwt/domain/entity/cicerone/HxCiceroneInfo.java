package com.hwt.domain.entity.cicerone;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author 
 */
public class HxCiceroneInfo implements Serializable {
    /**
     * 用户ID
     */
    private Long user_id;

    /**
     * 真实姓名
     */
    private String real_name;

    /**
     * 用户性别 0-未知 1-男 2-女
     */
    private Integer sex;

    /**
     * 生日
     */
    private Long birthday;

    /**
     * 封面
     */
    private String cover;

    /**
     * 身份证号码
     */
    private String identity_no;

    /**
     * 身份证正面
     */
    private String identity_positive;

    /**
     * 身份证反面
     */
    private String identity_negative;

    /**
     * 手持证件照
     */
    private String identity_hold;

    /**
     * 服务区域
     */
    private String service_area_city;

    /**
     * 服务区域名称
     */
    private String service_area_city_name;

    /**
     * 工作状态；默认0 空闲，1工作中
     */
    private Byte work_status;

    /**
     * 个性签名
     */
    private String autograph;

    /**
     * 电话
     */
    private String phone;

    /**
     * 年龄   如1980
     */
    private String tag;

    /**
     * 服务次数(接单次数)
     */
    private Long service_count;

    /**
     * 点评次数
     */
    private Integer score_num;

    /**
     * 点评总分数
     */
    private BigDecimal score;

    /**
     * 收藏数
     */
    private Long collect;

    /**
     * 确认率
     */
    private BigDecimal confirm;

    /**
     * 审核状态 默认1 未审核 2-通过 3-未通过
     */
    private Integer status;

    /**
     * 未通过原因
     */
    private String reason;

    /**
     * 每日价格
     */
    private BigDecimal everyday_price;

    /**
     * 申请时间
     */
    private Long create_time;

    /**
     * 审核时间
     */
    private Long examine_time;

    /**
     * 被预约次数
     */
    private Integer is_reserved_num;

    /**
     * 拒绝次数
     */
    private Integer refuse_num;

    /**
     * 被取消次数
     */
    private Integer is_canceled_num;

    /**
     * 导游类型
     */
    private String cicerone_type;

    /**
     * 是否公开  0-公开   1- 不公开
     */
    private Integer is_open;

    /**
     * 是否被禁用  0-否  1-是 2-永久被封 默认0
     */
    private Integer is_hide;

    private static final long serialVersionUID = 1L;

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
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

    public Long getBirthday() {
        return birthday;
    }

    public void setBirthday(Long birthday) {
        this.birthday = birthday;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getIdentity_no() {
        return identity_no;
    }

    public void setIdentity_no(String identity_no) {
        this.identity_no = identity_no;
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

    public String getIdentity_hold() {
        return identity_hold;
    }

    public void setIdentity_hold(String identity_hold) {
        this.identity_hold = identity_hold;
    }

    public String getService_area_city() {
        return service_area_city;
    }

    public void setService_area_city(String service_area_city) {
        this.service_area_city = service_area_city;
    }

    public String getService_area_city_name() {
        return service_area_city_name;
    }

    public void setService_area_city_name(String service_area_city_name) {
        this.service_area_city_name = service_area_city_name;
    }

    public Byte getWork_status() {
        return work_status;
    }

    public void setWork_status(Byte work_status) {
        this.work_status = work_status;
    }

    public String getAutograph() {
        return autograph;
    }

    public void setAutograph(String autograph) {
        this.autograph = autograph;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Long getService_count() {
        return service_count;
    }

    public void setService_count(Long service_count) {
        this.service_count = service_count;
    }

    public Integer getScore_num() {
        return score_num;
    }

    public void setScore_num(Integer score_num) {
        this.score_num = score_num;
    }

    public BigDecimal getScore() {
        return score;
    }

    public void setScore(BigDecimal score) {
        this.score = score;
    }

    public Long getCollect() {
        return collect;
    }

    public void setCollect(Long collect) {
        this.collect = collect;
    }

    public BigDecimal getConfirm() {
        return confirm;
    }

    public void setConfirm(BigDecimal confirm) {
        this.confirm = confirm;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public BigDecimal getEveryday_price() {
        return everyday_price;
    }

    public void setEveryday_price(BigDecimal everyday_price) {
        this.everyday_price = everyday_price;
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

    public Integer getIs_reserved_num() {
        return is_reserved_num;
    }

    public void setIs_reserved_num(Integer is_reserved_num) {
        this.is_reserved_num = is_reserved_num;
    }

    public Integer getRefuse_num() {
        return refuse_num;
    }

    public void setRefuse_num(Integer refuse_num) {
        this.refuse_num = refuse_num;
    }

    public Integer getIs_canceled_num() {
        return is_canceled_num;
    }

    public void setIs_canceled_num(Integer is_canceled_num) {
        this.is_canceled_num = is_canceled_num;
    }

    public String getCicerone_type() {
        return cicerone_type;
    }

    public void setCicerone_type(String cicerone_type) {
        this.cicerone_type = cicerone_type;
    }

    public Integer getIs_open() {
        return is_open;
    }

    public void setIs_open(Integer is_open) {
        this.is_open = is_open;
    }

    public Integer getIs_hide() {
        return is_hide;
    }

    public void setIs_hide(Integer is_hide) {
        this.is_hide = is_hide;
    }
}