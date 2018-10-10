package com.hwt.domain.entity.es;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * es 查询
 * @author Administrator
 *
 */
@ApiModel(value="es 查询")
public class ESQuery {
	
	/**
	 * id
	 */
	@ApiModelProperty(value=" id")
	private Long id;
	
	/**
	 * 数据的实际id
	 */
	@ApiModelProperty(value="数据的实际id")
	private Long name_id;
	
	/**
	 * 名称
	 */
	@ApiModelProperty(value="名称")
	private String name;
	
	/**
	 * 类型   1-景点 2-导游  3- 线路
	 */
	@ApiModelProperty(value="类型   1-景点 2-导游  3- 线路")
	private Integer type;
	
	/**
	 * 区号
	 */
	@ApiModelProperty(value=" 区号")
	private String area_code;
	
	/**
	 * 区号名称
	 */
	@ApiModelProperty(value="区号名称")
	private String area_code_name;
	
	/**
	 * 封面图
	 */
	@ApiModelProperty(value="封面图")
	private String image;
	
	/**
	 * 是否热门  0-否   1-是
	 */
	@ApiModelProperty(value="是否热门  0-否   1-是")
	private Integer is_hot;
	
	/**
	 * 销售数量
	 */
	@ApiModelProperty(value="销售数量")
	private Integer sales;
	
	/**
	 * 
	 * 展示用价格
	 * 
	 */
	@ApiModelProperty(value=" 展示用价格")
	private String price;
	
	/**
	 * 评论次数
	 */
	@ApiModelProperty(value="评论次数")
	private Integer comment_count;
	
	/**
	 * 评分
	 */
	@ApiModelProperty(value="评分")
	private Float comment_score;
	
	/**
	 * 是否删除  0-否   1-是
	 * 
	 */
	@ApiModelProperty(value="是否删除  0-否   1-是")
	private Integer is_hide;
	
	/**
	 * 地址
	 */
	@ApiModelProperty(value="地址")
	private String location;
	
	/**
	 * 标签    
	 */
	@ApiModelProperty(value="标签    ")
	private String tar;
	
	/**
	 * 坐标  {经度 ,纬度}
	 */
	@ApiModelProperty(value="坐标  {经度 ,纬度}")
	private String geo_point;
	
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间")
	private Long create_time;
	
	/**
	 * 头像地址
	 */
	@ApiModelProperty(value="头像地址")
	private String user_icon;
	
	/**
	 * 个新签名
	 */
	@ApiModelProperty(value="个新签名")
	private String autograph;
	
	
	public ESQuery() {
		super();
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Integer getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(Integer is_hot) {
		this.is_hot = is_hot;
	}

	public Integer getSales() {
		return sales;
	}

	public void setSales(Integer sales) {
		this.sales = sales;
	}

	public Integer getComment_count() {
		return comment_count;
	}

	public void setComment_count(Integer comment_count) {
		this.comment_count = comment_count;
	}

	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getTar() {
		return tar;
	}

	public void setTar(String tar) {
		this.tar = tar;
	}

	public String getGeo_point() {
		return geo_point;
	}

	public void setGeo_point(String geo_point) {
		this.geo_point = geo_point;
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


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}


	public Float getComment_score() {
		return comment_score;
	}

	public void setComment_score(Float comment_score) {
		this.comment_score = comment_score;
	}


	public Integer getIs_hide() {
		return is_hide;
	}


	public void setIs_hide(Integer is_hide) {
		this.is_hide = is_hide;
	}


	public String getUser_icon() {
		return user_icon;
	}


	public void setUser_icon(String user_icon) {
		this.user_icon = user_icon;
	}


	public String getAutograph() {
		return autograph;
	}


	public void setAutograph(String autograph) {
		this.autograph = autograph;
	}


	public String getArea_code_name() {
		return area_code_name;
	}


	public void setArea_code_name(String area_code_name) {
		this.area_code_name = area_code_name;
	}
	
	
}
