package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 群信息返回
 * @author Administrator
 *
 */
@ApiModel(value="群信息返回")
public class HxUserTeamVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="群id")
	private String team_id;
	
	@ApiModelProperty(value="群名称")
	private String name;

	public String getTeam_id() {
		return team_id;
	}

	public void setTeam_id(String team_id) {
		this.team_id = team_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

}
