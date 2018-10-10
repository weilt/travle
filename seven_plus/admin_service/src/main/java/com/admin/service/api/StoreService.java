package com.admin.service.api;

import com.domain.plus.vo.StoreVo;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 15:36
 * @Description:
 */
public interface StoreService {

    /**
     *  首页获取网点信息
     * @param storeType
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<StoreVo> getStoreByType(Integer storeType, Integer pageNo, Integer pageSize);

    /**
     *  首页获取网点信息
     * @param storeType
     * @param storeName
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<StoreVo> getStoreByType(Integer storeType, String storeName, Integer pageNo, Integer pageSize);
}
