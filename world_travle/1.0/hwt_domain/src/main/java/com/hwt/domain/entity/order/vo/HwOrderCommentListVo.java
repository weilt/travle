package com.hwt.domain.entity.order.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="订单评论列表返回")
public class HwOrderCommentListVo implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 好评数
	 */
	@ApiModelProperty(value="好评数")
	private Long good_comment;
	
	/**
	 * 中等数
	 */
	@ApiModelProperty(value=" 中等数")
	private Long secondary_comment;
	
	/**
	 * 差评数
	 */
	@ApiModelProperty(value="差评数")
	private Long bad_comment;
	
	/**
	 * 有图数
	 */
	@ApiModelProperty(value="有图数")
	private Long have_image;
	
	/**
    * 评论数据
    */
	@ApiModelProperty(value="评论数据")
	private List<HwOrderCommentVo> hwOrderCommentVos;
	
	public Long getGood_comment() {
		return good_comment;
	}
	public void setGood_comment(Long good_comment) {
		this.good_comment = good_comment;
	}
	public Long getSecondary_comment() {
		return secondary_comment;
	}
	public void setSecondary_comment(Long secondary_comment) {
		this.secondary_comment = secondary_comment;
	}
	public Long getBad_comment() {
		return bad_comment;
	}
	public void setBad_comment(Long bad_comment) {
		this.bad_comment = bad_comment;
	}
	
	public Long getHave_image() {
		return have_image;
	}
	public void setHave_image(Long have_image) {
		this.have_image = have_image;
	}
	public List<HwOrderCommentVo> getHwOrderCommentVos() {
		return hwOrderCommentVos;
	}
	public void setHwOrderCommentVos(List<HwOrderCommentVo> hwOrderCommentVos) {
		this.hwOrderCommentVos = hwOrderCommentVos;
	}

	
}