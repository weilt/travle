package com.hwt.domain.entity.order.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="订单评论")
public class HwOrderCommentVo implements Serializable {
	
    /**
     * id
     */
	@ApiModelProperty(value = "评论id")
    private Long order_comment_id;

	 /**
     * 订单ID
     */
	@ApiModelProperty(value = "订单ID")
    private Long order_id;
	
	 /**
     * 评论人
     */
	@ApiModelProperty(value="评论人")
    private CommentUserVo comment_user;

    /**
     * 导游id 或者线路id
     */
	@ApiModelProperty(value = "导游id 或者线路id")
    private Long name_id;

    /**
     * 2-导游 3-线路
     */
	@ApiModelProperty(value = "2-导游 3-线路")
    private Integer name_type;
	
   
	
	 /**
     * 恢复的哪一条  0不是回复
     */
	@ApiModelProperty(value = "恢复的哪一条  0不是回复")
    private Long parent_comment_id;

	/**
     * 回复的谁 
     */
	@ApiModelProperty(value="回复的谁")
	private CommentUserVo parent_comment_user;

	/**
     * 是否有图  0-无  2-有 默认0
     */
	@ApiModelProperty(value = "是否有图  0-无  2-有 默认0")
    private Integer is_image;

    /**
     * 评价描述
     */
	@ApiModelProperty(value = "评价描述" )
    private String comment_dec;

    /**
     * 图片组
     */
	@ApiModelProperty(value = "图片组" )
    private String image;

    /**
     * 相符度  1-1星 2-2星 3-3星 4-4星 5-5星 
     */
	@ApiModelProperty(value = "相符度  1-1星 2-2星 3-3星 4-4星 5-5星 " )
    private Integer match_score;

    /**
     * 行程安排  1-1星 2-2星 3-3星 4-4星 5-5星 
     */
	@ApiModelProperty(value = "行程安排  1-1星 2-2星 3-3星 4-4星 5-5星 " )
    private Integer trip_score;

    /**
     * 服务  1-1星 2-2星 3-3星 4-4星 5-5星 
     */
	@ApiModelProperty(value = "服务  1-1星 2-2星 3-3星 4-4星 5-5星 " )
    private Integer service_score;

    /**
     * 综合评分  1-1星 2-2星 3-3星 4-4星 5-5星 
     */
	@ApiModelProperty(value = "综合评分  1-1星 2-2星 3-3星 4-4星 5-5星 ")
    private Float score;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Long create_time;

    private static final long serialVersionUID = 1L;

	public Long getOrder_comment_id() {
		return order_comment_id;
	}

	public void setOrder_comment_id(Long order_comment_id) {
		this.order_comment_id = order_comment_id;
	}

	public Long getOrder_id() {
		return order_id;
	}

	public void setOrder_id(Long order_id) {
		this.order_id = order_id;
	}

	public CommentUserVo getComment_user() {
		return comment_user;
	}

	public void setComment_user(CommentUserVo comment_user) {
		this.comment_user = comment_user;
	}

	public Long getName_id() {
		return name_id;
	}

	public void setName_id(Long name_id) {
		this.name_id = name_id;
	}

	public Integer getName_type() {
		return name_type;
	}

	public void setName_type(Integer name_type) {
		this.name_type = name_type;
	}

	public Long getParent_comment_id() {
		return parent_comment_id;
	}

	public void setParent_comment_id(Long parent_comment_id) {
		this.parent_comment_id = parent_comment_id;
	}

	public CommentUserVo getParent_comment_user() {
		return parent_comment_user;
	}

	public void setParent_comment_user(CommentUserVo parent_comment_user) {
		this.parent_comment_user = parent_comment_user;
	}

	public Integer getIs_image() {
		return is_image;
	}

	public void setIs_image(Integer is_image) {
		this.is_image = is_image;
	}

	public String getComment_dec() {
		return comment_dec;
	}

	public void setComment_dec(String comment_dec) {
		this.comment_dec = comment_dec;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getMatch_score() {
		return match_score;
	}

	public void setMatch_score(Integer match_score) {
		this.match_score = match_score;
	}

	public Integer getTrip_score() {
		return trip_score;
	}

	public void setTrip_score(Integer trip_score) {
		this.trip_score = trip_score;
	}

	public Integer getService_score() {
		return service_score;
	}

	public void setService_score(Integer service_score) {
		this.service_score = service_score;
	}

	public Float getScore() {
		return score;
	}

	public void setScore(Float score) {
		this.score = score;
	}

	public Long getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Long create_time) {
		this.create_time = create_time;
	}

	
}