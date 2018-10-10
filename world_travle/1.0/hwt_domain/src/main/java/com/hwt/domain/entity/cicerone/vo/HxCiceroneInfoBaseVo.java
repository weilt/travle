package com.hwt.domain.entity.cicerone.vo;

import java.io.Serializable;
import java.math.BigDecimal;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value="导游基本信息")
public class HxCiceroneInfoBaseVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	@ApiModelProperty(value="用户id")
	private Long user_id;
   
	@ApiModelProperty(value=" 名称")
    private String real_name;
    
    @ApiModelProperty(value=" 封面")
    private String cover;
 
    @ApiModelProperty(value=" 年龄")
    private Long birthday;
   
    @ApiModelProperty(value=" 个性签名")
    private String autograph;
    
    @ApiModelProperty(value=" 服务次数")
    private Long service_count;
   
    @ApiModelProperty(value=" 点评分数")
    private Long score;
    
    @ApiModelProperty(value=" 点评次数")
    private Long score_num;
    
    
   
    @ApiModelProperty(value="审核状态 默认1 未审核 2-通过 3-未通过")
    private Integer status;
    @ApiModelProperty(value="用户性别 0-未知 1-男 2-女")
    private Integer sex;
    
    /**
     * 可被预约时间
     */
    @ApiModelProperty(value="工作时间（0101,0102,0103,0129）")
    private String work_time;

    /**
     * 工作状态；默认0 空闲，1工作中
     */
    @ApiModelProperty(value="工作状态；默认 0空闲，1工作中")
    private Integer work_status;
    
    /**
     * 他的故事
     */
    @ApiModelProperty(value="他的故事")
    private String about;
    
    /**
     * 年龄
     */
    @ApiModelProperty(value="年龄")
    private String tag;
    
    /**
     * 电话
     */
    @ApiModelProperty(value="电话")
    private String phone;
    
    /**
     * 每日价格
     */
    @ApiModelProperty(value="每日价格")
    private BigDecimal everyday_price;
    
    

    /**
     * 是否公开  0-公开   1- 不公开
     */
    @ApiModelProperty(value="是否公开  0-公开   1- 不公开")
    private Integer is_open;
    
    /**
     * 是否被禁用  0-否  1-是 2-永久被封 
     */
    @ApiModelProperty(value="是否被禁用  0-否  1-是 2-永久被封")
	private Integer is_hide;
	
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public String getCover() {
		return cover;
	}
	public void setCover(String cover) {
		this.cover = cover;
	}
	public Long getBirthday() {
		return birthday;
	}
	public void setBirthday(Long birthday) {
		this.birthday = birthday;
	}
	public String getAutograph() {
		return autograph;
	}
	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}
	public Long getService_count() {
		return service_count;
	}
	public void setService_count(Long service_count) {
		this.service_count = service_count;
	}
	public Long getScore() {
		return score;
	}
	public void setScore(Long score) {
		this.score = score;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getWork_time() {
		return work_time;
	}
	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}
	public Integer getWork_status() {
		return work_status;
	}
	public void setWork_status(Integer work_status) {
		this.work_status = work_status;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Long getUser_id() {
		return user_id;
	}
	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	public BigDecimal getEveryday_price() {
		return everyday_price;
	}
	public void setEveryday_price(BigDecimal everyday_price) {
		this.everyday_price = everyday_price;
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
	public Long getScore_num() {
		return score_num;
	}
	public void setScore_num(Long score_num) {
		this.score_num = score_num;
	}
	
	
}
