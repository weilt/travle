package com.admin.service.api.impl;

import com.admin.service.api.AddressService;
import com.domain.plus.entity.PlusAddress;
import com.domain.plus.mapper.PlusAddressMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/21 19:34
 * @Description:
 */
@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private PlusAddressMapper addressMapper;


    @Override
    public Integer delAddress(Long userId, Long id) {
        return addressMapper.delete(userId,id);
    }

    @Override
    public Integer addAddress(Long id, String address) {
        PlusAddress plusAddress = new PlusAddress();
        plusAddress.setAddress(address);
        plusAddress.setUserId(id);
        plusAddress.setCraetaTime(System.currentTimeMillis());
        return addressMapper.insertPlusAddress(plusAddress);
    }

    @Override
    public List<PlusAddress> getAddress(Long userId) {
        return addressMapper.queryAddrByUserId(userId);
    }

    @Override
    public Integer updateAddress(Long userId, Long id, String address) {
        PlusAddress plusAddress = addressMapper.queryAddressById(id);
        plusAddress.setAddress(address);
        plusAddress.setUpdateTime(System.currentTimeMillis());
        return addressMapper.updatePlusAddress(plusAddress);
    }
}
