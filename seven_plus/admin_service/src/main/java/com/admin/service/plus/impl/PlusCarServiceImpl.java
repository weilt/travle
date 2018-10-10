package com.admin.service.plus.impl;

import com.admin.service.plus.PlusCarService;
import com.common.util.PageUtil;
import com.domain.plus.mapper.PlusCarMapper;
import com.domain.plus.vo.PlusCarVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/14 15:30
 * @Description:
 */
@Service
public class PlusCarServiceImpl implements PlusCarService {

    @Autowired
    private PlusCarMapper carMapper;


    @Override
    public Integer countCarByPhone(String phone) {
        return carMapper.countCarByPhone(phone);
    }

    @Override
    public PlusCarVo findCarById(Long id) {
        return carMapper.queryCarById(id);
    }

    @Override
    public List<PlusCarVo> findCarByPhone(String phone, Integer pageNumber, Integer pageSize) {
        Integer index = PageUtil.init(pageNumber,pageSize).getIndex();
        Integer last = PageUtil.init(pageNumber,pageSize).getPageSize();
        return carMapper.findCarByPhone(phone,index,last);
    }
}
