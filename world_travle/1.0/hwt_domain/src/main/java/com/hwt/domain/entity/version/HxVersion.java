package com.hwt.domain.entity.version;

import java.io.Serializable;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 
 */
public class HxVersion implements Serializable {
	 /**
     * 主键Id
     */
    private Long id;

    /**
     * 版本名称
     */
    private String version_name;

    /**
     * 版本号
     */
    private Long version_number;

    /**
     * 1-安卓 2-ios
     */
    private Integer android_or_ios;

    /**
     * 最低版本
     */
    private Long minimun;

    /**
     * 是否最低版本 0-否 1-是
     */
    private Integer is_not_minimum;

    /**
     * 版本下载路径
     */
    private String versions_url;

    /**
     * 备注
     */
    private String remarks;

    /**
     * 版本添加日期
     */
    private Date create_time;

    /**
     * 版本说明
     */
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



	public static void main(String[] args) {
		
		String pattern  ="^[a-zA-Z][a-zA-Z0-9_-]{2,19}$";
		Pattern r = Pattern.compile(pattern);
		Matcher matcher = r.matcher("在z1111");
		boolean matches = matcher.matches();
		System.out.println(matches);
		if (!matches){
			throw new RuntimeException("账号只能6—20个字母、数字、下划线和减号，必须以字母开头（不区分大小写），不支持设置中文");
		}
		System.out.println(new Date().getTime());
	}
}
