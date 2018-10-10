package com.hwt.domain.entity.mg.information;

import java.io.Serializable;


/**
 * 资讯
 * @author Administrator
 *
 */
public class HwInformation implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 资讯 id
	 */
	private Long information_id;
	
	/**
	 * 标题
	 */
	private String tilte;
	
	/**
	 * 作者
	 */
	private String author;
	
	/**
	 * 资讯分类  0-为默认分类
	 */
	private Long information_type;
	
	/**
	 * 内容
	 */
	private String content;
	
	/**
	 * 列表图片展示
	 */
	private String images;
	
	/**
	 * 来源
	 */
	private String source;
	
	/**
	 * 评论数
	 */
	private Long comment_num;
	
	/**
	 * 获赞数
	 */
	private Long good_num;
	
	/**
	 * 阅读数
	 */
	private Long look_num;
	
	/**
	 * 是否删除  1-是 0-否
	 */
	private Integer is_hide;
	
	/**
	 * 是否显示 1-是 0-否
	 */
	private Integer is_display;
	
	/**
	 * 创建时间
	 */
	private Long create_time;

	public String getTilte() {
		return tilte;
	}

	public void setTilte(String tilte) {
		this.tilte = tilte;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Long getInformation_type() {
		return information_type;
	}

	public void setInformation_type(Long information_type) {
		this.information_type = information_type;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImages() {
		return images;
	}

	public void setImages(String images) {
		this.images = images;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Long getComment_num() {
		return comment_num;
	}

	public void setComment_num(Long comment_num) {
		this.comment_num = comment_num;
	}

	public Long getGood_num() {
		return good_num;
	}

	public void setGood_num(Long good_num) {
		this.good_num = good_num;
	}

	public Long getLook_num() {
		return look_num;
	}

	public void setLook_num(Long look_num) {
		this.look_num = look_num;
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

	public Integer getIs_display() {
		return is_display;
	}

	public void setIs_display(Integer is_display) {
		this.is_display = is_display;
	}

	public Long getInformation_id() {
		return information_id;
	}

	public void setInformation_id(Long information_id) {
		this.information_id = information_id;
	}
}
