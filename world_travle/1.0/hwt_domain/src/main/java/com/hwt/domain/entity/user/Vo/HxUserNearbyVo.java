package com.hwt.domain.entity.user.Vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 附近的人
 * @author Administrator
 *
 */
@ApiModel(value="附近的人信息返回")
public class HxUserNearbyVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="用户id")
	private Long user_id;
	
	@ApiModelProperty(value="用户im_id")
	private String im_id;
	
	@ApiModelProperty(value="用户头像")
	private String user_icon;
	
	@ApiModelProperty(value="用户昵称")
	private String  nickname;
	
	@ApiModelProperty(value="用户性别 0-人妖 1-男 2-女")
	private Integer  user_sex;
	
	@ApiModelProperty(value="用户个性签名")
	private String user_autograph;
	
	@ApiModelProperty(value="用户经度")
	private String last_longitude;
	
	@ApiModelProperty(value="用户纬度")
	private String last_latitude;
	
	@ApiModelProperty(value="距离  单位 米")
	private Long distance;

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getIm_id() {
		return im_id;
	}

	public void setIm_id(String im_id) {
		this.im_id = im_id;
	}

	public String getUser_icon() {
		return user_icon;
	}

	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Integer getUser_sex() {
		return user_sex;
	}

	public void setUser_sex(Integer user_sex) {
		this.user_sex = user_sex;
	}

	public String getUser_autograph() {
		return user_autograph;
	}

	public void setUser_autograph(String user_autograph) {
		this.user_autograph = user_autograph;
	}

	public String getLast_longitude() {
		return last_longitude;
	}

	public void setLast_longitude(String last_longitude) {
		this.last_longitude = last_longitude;
	}

	public String getLast_latitude() {
		return last_latitude;
	}

	public void setLast_latitude(String last_latitude) {
		this.last_latitude = last_latitude;
	}

	public Long getDistance() {
		return distance;
	}

	public void setDistance(Long distance) {
		this.distance = distance;
	}
	
	
}
