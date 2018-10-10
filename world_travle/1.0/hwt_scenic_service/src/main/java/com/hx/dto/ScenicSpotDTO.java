package com.hx.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 景点DTO
 */
public class ScenicSpotDTO implements Serializable {

    private String spotId;
    private String spotName;
    private String country;
    private String city;
    private String description;
    private String brief;
    private String subtitle;
    private String rating;
    private String telephones;
    private String geoPoint;
    private String tags;
    private String imageUrls;
    private String rank;
    private String openingHours;
    private String location;
    private String ticketInfo;
    private String dataUrl;
    private String dataSources;
    private Long dataTime;
    private Integer isHide;
    private Integer visitsNum;


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

    public Long getDataTime() {
        return dataTime;
    }

    public void setDataTime(Long dataTime) {
        this.dataTime = dataTime;
    }

    public Integer getIsHide() {
        return isHide;
    }

    public void setIsHide(Integer isHide) {
        this.isHide = isHide;
    }

    public Integer getVisitsNum() {
        return visitsNum;
    }

    public void setVisitsNum(Integer visitsNum) {
        this.visitsNum = visitsNum;
    }
}
