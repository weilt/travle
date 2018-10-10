package com.hwt.domain.entity.user.collect.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="收藏夹")
public class HwUserCollectVo implements Serializable {
    /**
     * id
     */
	@ApiModelProperty(value = "id")
    private Long collect_id;
    
    /**
     * 用户id
     */
	@ApiModelProperty(value = "用户id")
    private Long user_id;

    /**
     * 类型 1-景点 2-导游 3-线路 4-咨询
     */
	@ApiModelProperty(value = "类型 1-景点 2-导游 3-线路 4-咨询")
    private Integer type;

    /**
     * 内容的实际id
     */
	@ApiModelProperty(value = "内容的实际id")
    private Long name_id;

    /**
     * 创建时间
     */
	@ApiModelProperty(value = "创建时间")
    private Long create_time;

    private static final long serialVersionUID = 1L;

    public Long getCollect_id() {
        return collect_id;
    }

    public void setCollect_id(Long collect_id) {
        this.collect_id = collect_id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getName_id() {
        return name_id;
    }

    public void setName_id(Long name_id) {
        this.name_id = name_id;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
    
}