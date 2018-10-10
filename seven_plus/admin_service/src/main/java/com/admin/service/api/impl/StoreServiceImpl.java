package com.admin.service.api.impl;

import com.admin.service.api.StoreService;
import com.common.util.PageUtil;
import com.domain.plus.mapper.PlusStoreMapper;
import com.domain.plus.vo.StoreVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 15:36
 * @Description:
 */
@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private PlusStoreMapper storeMapper;

    @Override
    public List<StoreVo> getStoreByType(Integer storeType, Integer pageNo, Integer pageSize) {
        Integer index = PageUtil.init(pageNo,pageSize).getIndex();
        Integer last = PageUtil.init(pageSize,pageSize).getPageSize();
        if (null != storeType){
            storeType --;
        }
        return storeMapper.queryStoreByType(storeType,index,last);
    }

    @Override
    public List<StoreVo> getStoreByType(Integer storeType, String storeName, Integer pageNo, Integer pageSize) {
        Integer index = PageUtil.init(pageNo,pageSize).getIndex();
        Integer last = PageUtil.init(pageSize,pageSize).getPageSize();
        if (null != storeType){
            storeType --;
        }
        return storeMapper.queryStoreByTypeAndName(storeType,storeName,index,last);
    }
}
