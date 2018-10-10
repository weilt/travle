package com.admin.service.plus.impl;

import com.admin.service.admin.AdminSystemConfigService;
import com.admin.service.plus.PlusStoreService;
import com.common.excption.BaseAdminException;
import com.common.excption.BaseException;
import com.common.tencent.map.TencentMapUtil;
import com.common.util.PageUtil;
import com.domain.plus.entity.PlusStore;
import com.domain.plus.mapper.PlusStoreMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/13 18:01
 * @Description:
 */
@Service
public class PlusStoreServiceImpl implements PlusStoreService {

    @Autowired
    private AdminSystemConfigService systemConfig;

    @Autowired
    private PlusStoreMapper storeMapper;

    @Override
    public Integer countStore(String name, String address, Integer count) {
        return storeMapper.countStore(name,address,count);
    }

    @Override
    public List<PlusStore> findStore(String name, String address, Integer count, int pageNumber, int pageSize) {
        Integer index = PageUtil.init(pageNumber,pageSize).getIndex();
        Integer last = PageUtil.init(pageNumber,pageSize).getPageSize();
        return storeMapper.queryStore(name,address,count,index,last);
    }

    /**
     * 添加网点
     * 调用腾讯地图API获取经纬度
     * @param store
     * @return
     */
    @Override
    public Integer storeAdd(PlusStore store) {
        String key = systemConfig.getValue("tencentMapKey");
        // 调用腾讯地图API获取经纬度
        Map<String,String> location = TencentMapUtil.getLocation(store.getStoreAddress(),key);
        if (null == location || location.isEmpty()){
            throw new BaseAdminException("获取地址经纬度失败！");
        }
        String lng = location.get("lng");
        String lat = location.get("lat");
        String district = location.get("district");
        store.setStoreLon(lng);
        store.setStoreLat(lat);
        store.setStoreDistrict(district);
        return storeMapper.insertPlusStore(store);
    }

    @Override
    public PlusStore findById(Long id) {
        return storeMapper.queryStoreById(id);
    }

    @Override
    public Integer countStoreByName(Integer storeType, String storeName) {
        return storeMapper.countStoreByNameAndType(storeType,storeName);
    }

    @Override
    public List<PlusStore> getStoreByName(Integer storeType, String storeName, int pageNumber, int pageSize) {
        Integer index = PageUtil.init(pageNumber,pageSize).getIndex();
        Integer last = PageUtil.init(pageSize,pageSize).getPageSize();
        return storeMapper.queryStoreByNameAndType(storeType,storeName,index,last);
    }

    @Override
    public Integer storeUpdate(PlusStore plusStore) {
        String key = systemConfig.getValue("tencentMapKey");
        // 调用腾讯地图API获取经纬度
        Map<String,String> location = TencentMapUtil.getLocation(plusStore.getStoreAddress(),key);
        if (null == location || location.isEmpty()){
            throw new BaseAdminException("获取地址经纬度失败！");
        }
        String lng = location.get("lng");
        String lat = location.get("lat");
        String district = location.get("district");
        plusStore.setStoreLon(lng);
        plusStore.setStoreLat(lat);
        plusStore.setStoreDistrict(district);
        return storeMapper.updatePlusStore(plusStore);
    }
}
