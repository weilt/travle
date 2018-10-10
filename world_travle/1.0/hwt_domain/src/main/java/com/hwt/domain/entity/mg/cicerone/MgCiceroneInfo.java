package com.hwt.domain.entity.mg.cicerone;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 导游详情
 * @author Administrator
 *
 */
@ApiModel(value="导游详情")
public class MgCiceroneInfo {
	
	
	@ApiModelProperty(value="用户id")
	private Long user_id;

    /**
     * 他的故事
     */
    @ApiModelProperty(value="他的故事")
    private String about;
    
    
    /**
     * 可被预约时间
     */
    @ApiModelProperty(value="工作时间（0101,0102,0103,0129）")
    private String work_time;


	public Long getUser_id() {
		return user_id;
	}


	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}


	public String getAbout() {
		return about;
	}


	public void setAbout(String about) {
		this.about = about;
	}


	public String getWork_time() {
		return work_time;
	}


	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}
    
    
}
