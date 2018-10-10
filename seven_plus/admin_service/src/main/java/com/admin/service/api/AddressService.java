package com.admin.service.api;

import com.domain.plus.entity.PlusAddress;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/21 19:33
 * @Description:
 */
public interface AddressService {

    /**
     * 添加地址
     * @param id
     * @param address
     * @return
     */
    Integer addAddress(Long id, String address);

    /**
     * 删除地址
     * @param id
     * @param userId
     * @return
     */
    Integer delAddress(Long userId, Long id);


    /**
     * 更新地址
     * @param id
     * @param userId
     * @param address
     * @return
     */
    Integer updateAddress(Long userId, Long id, String address);


    /**
     * 获取用户地址
     * @param userId
     * @return
     */
    List<PlusAddress> getAddress(Long userId);
}
