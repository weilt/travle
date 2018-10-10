package com.hwt.domain.entity.admin.vo.scenic;

import java.io.Serializable;

/**
 * 后台景区返回对象
 * @author Administrator
 *
 */
public class AdminScenicSpotVo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String spotId;      //景点ID
    private String spotName;    //景点名称
    private String country;     //所在国家
    private String city;        //城市
    private String description; //景点描述
    private String brief;       //景点简介
    private String subtitle;    //小标题
    private String rating;      //景点评分
    private String telephones;  //联系电话
    private String geoPoint;    //定位坐标
    private String tags;        //标签
    private String imageUrls;   //景点图片组
    private String rank;        //景点等级
    private String openingHours;//开放时间
    private String location;    //地址
    private String ticketInfo;  //门票信息
    private String dataUrl;     //来源景点URL
    private String dataSources; //数据来源
    private Integer isHide;		//是否删除  0-删除  1-不删除
    private Long visitsNum;		//访问次数
    private Long dataTime;      //添加时间
    
	public String getSpotId() {
		return spotId;
	}
	public void setSpotId(String spotId) {
		this.spotId = spotId;
	}
	public String getSpotName() {
		return spotName;
	}
	public void setSpotName(String spotName) {
		this.spotName = spotName;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getBrief() {
		return brief;
	}
	public void setBrief(String brief) {
		this.brief = brief;
	}
	public String getSubtitle() {
		return subtitle;
	}
	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}
	public String getRating() {
		return rating;
	}
	public void setRating(String rating) {
		this.rating = rating;
	}
	public String getTelephones() {
		return telephones;
	}
	public void setTelephones(String telephones) {
		this.telephones = telephones;
	}
	public String getGeoPoint() {
		return geoPoint;
	}
	public void setGeoPoint(String geoPoint) {
		this.geoPoint = geoPoint;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	public String getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(String imageUrls) {
		this.imageUrls = imageUrls;
	}
	public String getRank() {
		return rank;
	}
	public void setRank(String rank) {
		this.rank = rank;
	}
	public String getOpeningHours() {
		return openingHours;
	}
	public void setOpeningHours(String openingHours) {
		this.openingHours = openingHours;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getTicketInfo() {
		return ticketInfo;
	}
	public void setTicketInfo(String ticketInfo) {
		this.ticketInfo = ticketInfo;
	}
	public String getDataUrl() {
		return dataUrl;
	}
	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}
	public String getDataSources() {
		return dataSources;
	}
	public void setDataSources(String dataSources) {
		this.dataSources = dataSources;
	}
	public Integer getIsHide() {
		return isHide;
	}
	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}
	public Long getDataTime() {
		return dataTime;
	}
	public void setDataTime(Long dataTime) {
		this.dataTime = dataTime;
	}
	public Long getVisitsNum() {
		return visitsNum;
	}
	public void setVisitsNum(Long visitsNum) {
		this.visitsNum = visitsNum;
	}
    
    
}

