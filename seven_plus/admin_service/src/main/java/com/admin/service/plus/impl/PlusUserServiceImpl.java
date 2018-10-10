package com.admin.service.plus.impl;

import com.admin.service.plus.PlusUserService;
import com.common.util.PageUtil;
import com.domain.plus.entity.*;
import com.domain.plus.mapper.*;
import com.domain.plus.vo.BrokerageVo;
import com.domain.plus.vo.PlusCarVo;
import com.domain.plus.vo.PlusUserVo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/8 19:22
 * @Description:
 */
@Service
public class PlusUserServiceImpl implements PlusUserService {

    @Autowired
    private PlusUserMapper plusUserMapper;

    @Autowired
    private PlusCarMapper plusCarMapper;

    @Autowired
    private PlusAddressMapper plusAddressMapper;

    @Autowired
    private PlusBrokerageMapper brokerageMapper;

    @Autowired
    private ExtractCashMapper cashMapper;



    @Override
    public Integer countUser(String phone, Integer washCount, Integer nickCount) {
        return plusUserMapper.countUser(phone,washCount,nickCount);
    }

    @Override
    public List<PlusUserVo> queryUser(String phone, Integer washCount, Integer nickCount, Integer pageNo, Integer pageSize) {
        Integer index = PageUtil.init(pageNo,pageSize).getIndex();
        Integer last = PageUtil.init(pageNo,pageSize).getPageSize();
        List<PlusUserVo> list = plusUserMapper.queryUserVoLimit(phone,washCount,nickCount,index,last);
        List<PlusUserVo> result = Lists.newArrayList();
        if (null == list || list.isEmpty()){
            return list;
        }
        list.stream().forEach(l -> {
            PlusCar car = plusCarMapper.findOneCarByUserId(l.getId());
            if (null != car)
                l.setCarNo(car.getCarNo());
            result.add(l);
        });
        return result;
    }

    @Override
    public Integer save(PlusUser user) {
        return plusUserMapper.insertPlusUser(user);
    }

    @Override
    public PlusUser findById(Long id) {
        return plusUserMapper.queryById(id);
    }

    @Override
    public List<PlusAddress> findAddrByUserId(Long id) {
        return plusAddressMapper.queryAddrByUserId(id);
    }

    @Override
    public List<PlusCarVo> findCarByUserId(Long id) {
        return plusCarMapper.findCarByUserId(id);
    }

    @Override
    public List<BrokerageVo> findBrokerageByUserId(Long id) {
        return brokerageMapper.queryBrokerageByUserId(id);
    }


    @Transactional
    @Override
    public Boolean setBrokerage(PlusUser plusUser, Integer money) {
        plusUser.setBrokerage(plusUser.getBrokerage() - money * 100);
        plusUser.setUpdateTime(System.currentTimeMillis());
        plusUserMapper.updatePlusUser(plusUser);
        ExtractCash cash = new ExtractCash();
        cash.setUserId(plusUser.getId());
        cash.setCreateTime(System.currentTimeMillis());
        cash.setMoney(money * 100L);
        cashMapper.insertExtractCash(cash);
        return Boolean.TRUE;
    }

    @Override
    public List<ExtractCash> findExtractCash(Long id) {
        return cashMapper.queryExtractCashByUserId(id);
    }
}
