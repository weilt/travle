package com.domain.plus.vo;

import java.io.Serializable;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/13 10:20
 * @Description: 评价列表
 */
public class CommentVo implements Serializable {
    /**
     * com.domain.plus.vo.CommentVo
     * c.id,
     * 	u.phone,
     * 	s.store_name,
     * 	s.store_address,
     * 	c.create_time
     */

    private Long id;
    private String phone;
    private String storeName;
    private String storeAddress;
    private String comment;
    private Long createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getStoreAddress() {
        return storeAddress;
    }

    public void setStoreAddress(String storeAddress) {
        this.storeAddress = storeAddress;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
