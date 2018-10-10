package com.domain.plus.vo;

import com.domain.plus.entity.OrderRecord;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/15 09:42
 * @Description:
 */
public class OrderRecordVo extends OrderRecord {

    private String phone;
    private String city;
    private String storeName;
    private String storeAddress;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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
}
