package com.admin.service.plus;

import com.domain.plus.entity.PlusStore;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/13 18:00
 * @Description:
 */
public interface PlusStoreService {

    /**
     *  网点条数查询
     * @param name 名称
     * @param address 地址
     * @param count 订单数
     * @return
     */
    Integer countStore(String name, String address, Integer count);

    /**
     * 网点列表查询
     * @param name
     * @param address
     * @param count
     * @param pageNumber
     * @param pageSize
     * @return
     */
    List<PlusStore> findStore(String name, String address, Integer count, int pageNumber, int pageSize);

    /**
     * 添加网点
     * @param store
     * @return
     */
    Integer storeAdd(PlusStore store);

    PlusStore findById(Long id);

    Integer storeUpdate(PlusStore plusStore);

    Integer countStoreByName(Integer storeType, String storeName);

    List<PlusStore> getStoreByName(Integer storeType, String storeName, int pageNumber, int pageSize);
}
