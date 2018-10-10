package com.hwt.domain.entity.version.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 淮信版本返回对象
 * @author Administrator
 *
 */
@ApiModel(value="淮信版本返回对象")
public class HxVersionVo implements Serializable {
	 /**
     * 主键Id
     */
	@ApiModelProperty(value = "主键Id")
    private Long id;

    /**
     * 版本名称
     */
	@ApiModelProperty(value = "版本名称")
    private String version_name;

    /**
     * 版本号
     */
	@ApiModelProperty(value = "版本号")
    private Long version_number;

    /**
     * 1-安卓 2-ios
     */
	@ApiModelProperty(value = "1-安卓 2-ios")
    private Integer android_or_ios;

    /**
     * 最低版本
     */
	@ApiModelProperty(value = "最低版本")
    private Long minimun;

    /**
     * 是否最低版本 0-否 1-是
     */
	@ApiModelProperty(value = "是否最低版本 0-否 1-是")
    private Integer is_not_minimum;

    /**
     * 版本下载路径
     */
	@ApiModelProperty(value = "版本下载路径")
    private String versions_url;

    /**
     * 备注
     */
	@ApiModelProperty(value = "备注")
    private String remarks;

    /**
     * 版本添加日期
     */
	@ApiModelProperty(value = "版本添加日期")
    private Date create_time;

    /**
     * 版本说明
     */
	@ApiModelProperty(value = "版本说明")
    private String version_instructions;

    private static final long serialVersionUID = 1L;
    
   

	public Long getId() {
		return id;
	}



	public void setId(Long id) {
		this.id = id;
	}



	public String getVersion_name() {
		return version_name;
	}



	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}



	public Long getVersion_number() {
		return version_number;
	}



	public void setVersion_number(Long version_number) {
		this.version_number = version_number;
	}



	public Integer getAndroid_or_ios() {
		return android_or_ios;
	}



	public void setAndroid_or_ios(Integer android_or_ios) {
		this.android_or_ios = android_or_ios;
	}



	public Long getMinimun() {
		return minimun;
	}



	public void setMinimun(Long minimun) {
		this.minimun = minimun;
	}



	public Integer getIs_not_minimum() {
		return is_not_minimum;
	}



	public void setIs_not_minimum(Integer is_not_minimum) {
		this.is_not_minimum = is_not_minimum;
	}



	public String getVersions_url() {
		return versions_url;
	}



	public void setVersions_url(String versions_url) {
		this.versions_url = versions_url;
	}



	public String getRemarks() {
		return remarks;
	}



	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}



	public Date getCreate_time() {
		return create_time;
	}



	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}



	public String getVersion_instructions() {
		return version_instructions;
	}



	public void setVersion_instructions(String version_instructions) {
		this.version_instructions = version_instructions;
	}




}
