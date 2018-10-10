package com.hwt.domain.entity.user.video;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @author 
 */
@ApiModel(value="小视频评论")
public class HxUserVideoComment implements Serializable {
    /**
     * 评论id
     */
	@ApiModelProperty(value="小视频评论id")
    private Long video_comment_id;

    /**
     * 评论人
     */
	@ApiModelProperty(value="小视频评论人id")
    private Long user_id;

    /**
     * 小视频   id
     */
	@ApiModelProperty(value="小视频   id")
    private Long video_id;

	/**
	 * 评论内容
	 */
	@ApiModelProperty(value="评论内容")
    private String content;

    /**
     * 1-评论 2-点赞
     */
	@ApiModelProperty(value="1-评论 2-点赞")
    private Integer type;

    /**
     * 回复的哪一条  0-不是回复
     */
	@ApiModelProperty(value="回复的哪一条  0-不是回复")
    private Long parent_id;

    /**
     * 回复的谁  0-不是回复
     */
	@ApiModelProperty(value="回复的谁  0-不是回复")
    private Long parent_user_id;

    /**
     * 是否删除  0-否 1-是
     */
	@ApiModelProperty(value="是否删除  0-否 1-是")
    private Integer is_hide;

    /**
     * 创建时间
     */
	@ApiModelProperty(value="创建时间")
    private Long create_time;

    private static final long serialVersionUID = 1L;

    public Long getVideo_comment_id() {
        return video_comment_id;
    }

    public void setVideo_comment_id(Long video_comment_id) {
        this.video_comment_id = video_comment_id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getVideo_id() {
        return video_id;
    }

    public void setVideo_id(Long video_id) {
        this.video_id = video_id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getParent_id() {
        return parent_id;
    }

    public void setParent_id(Long parent_id) {
        this.parent_id = parent_id;
    }

    public Long getParent_user_id() {
        return parent_user_id;
    }

    public void setParent_user_id(Long parent_user_id) {
        this.parent_user_id = parent_user_id;
    }

    public Integer getIs_hide() {
        return is_hide;
    }

    public void setIs_hide(Integer is_hide) {
        this.is_hide = is_hide;
    }

    public Long getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Long create_time) {
        this.create_time = create_time;
    }
}