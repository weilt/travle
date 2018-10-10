package com.admin.service.api;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 09:52
 * @Description:
 */
public interface ImgService {

    /**
     * 通过类型获取图片地址集合
     * @param code
     * @return
     */
    List<String> getAdvertisement(Integer code);

    /**
     * 通过类型获取图片地址
     * @param code
     * @return
     */
    String getImg(Integer code);
}
