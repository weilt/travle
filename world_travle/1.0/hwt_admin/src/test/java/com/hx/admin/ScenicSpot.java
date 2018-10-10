package com.hx.admin;

import java.io.Serializable;

/**
 * Created by Ro on 2018/5/3.
 */
public class ScenicSpot implements Serializable{

 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long spotId;      //景点ID
    private String spotName;    //景点名称
    private String country;     //所在国家
    private String province;   //省
    private String district;   //区
    private String city;        //城市
    private String city_code;	//行政编码
    private String area_code;   //区号
    private String description; //景点描述
    private String brief;       //景点简介
    private String subtitle;    //小标题
    private String rating;      //景点评分
    private String telephones;  //联系电话
    private String geoPoint;    //定位坐标
    private String tags;        //标签
    private String imageUrls;   //景点图片组
    private String rank;        //景点等级
    private String openingHours;//开发时间
    private String location;    //地址
    private String ticketInfo;  //门票信息
    private String dataUrl;     //来源景点URL
    private String dataSources; //数据来源
    private Integer isHide;		//是否删除  0-删除  1-不删除
    private Integer isHot;		//是否热门   0-否  1-是
    private Integer isRecommend;		//是否推荐  0-否  1-是
    private String remarks;		//备注
    private Long visitsNum;		//访问次数
    private long dataTime;      //添加时间

   

    public Long getSpotId() {
		return spotId;
	}

	public void setSpotId(Long spotId) {
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
    
    public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
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

    public String getDataSources() {
        return dataSources;
    }

    public void setDataSources(String dataSources) {
        this.dataSources = dataSources;
    }

    public long getDataTime() {
        return dataTime;
    }

    public void setDataTime(long dataTime) {
        this.dataTime = dataTime;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }

	public Integer getIsHide() {
		return isHide;
	}

	public void setIsHide(Integer isHide) {
		this.isHide = isHide;
	}

	public Long getVisitsNum() {
		return visitsNum;
	}

	public void setVisitsNum(Long visitsNum) {
		this.visitsNum = visitsNum;
	}

	public Integer getIsHot() {
		return isHot;
	}

	public void setIsHot(Integer isHot) {
		this.isHot = isHot;
	}

	public Integer getIsRecommend() {
		return isRecommend;
	}

	public void setIsRecommend(Integer isRecommend) {
		this.isRecommend = isRecommend;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getArea_code() {
		return area_code;
	}

	public void setArea_code(String area_code) {
		this.area_code = area_code;
	}
    
	
}
