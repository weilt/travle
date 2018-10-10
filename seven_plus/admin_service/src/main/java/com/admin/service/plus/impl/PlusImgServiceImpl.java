package com.admin.service.plus.impl;

import com.admin.service.plus.PlusImgService;
import com.domain.plus.entity.PlusImg;
import com.domain.plus.mapper.PlusImgMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/10 14:11
 * @Description:
 */
@Service
public class PlusImgServiceImpl implements PlusImgService {

    @Autowired
    private PlusImgMapper plusImgMapper;


    @Override
    public List<PlusImg> findByTypes(Integer ... types) {
        return plusImgMapper.queryImgByTypes(types);
    }

    @Override
    public Integer save(PlusImg plusImg) {
        return plusImgMapper.insertPlusImg(plusImg);
    }


    @Override
    public Boolean restrict(Integer type, Integer count) {
        int total = plusImgMapper.countType(type);

        return total >= count;
    }

    @Override
    public PlusImg findById(Long id) {
        return plusImgMapper.findById(id);
    }

    @Override
    public Integer update(PlusImg plusImg) {
        return plusImgMapper.updatePlusImg(plusImg);
    }

    @Override
    public Integer delete(PlusImg plusImg) {
        return plusImgMapper.deleteImg(plusImg);
    }
}
