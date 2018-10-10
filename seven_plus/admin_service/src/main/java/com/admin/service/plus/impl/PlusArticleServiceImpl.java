package com.admin.service.plus.impl;

import com.admin.service.plus.PlusArticleService;
import com.common.util.PageUtil;
import com.domain.plus.entity.PlusArticle;
import com.domain.plus.mapper.PlusArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/8 10:57
 * @Description:
 */
@Service
public class PlusArticleServiceImpl implements PlusArticleService {


    @Autowired
    private PlusArticleMapper plusArticleMapper;

    @Override
    public List<PlusArticle> getType(Integer type,Integer pageNo, Integer pageSize) {
        Integer index = PageUtil.init(pageNo,pageSize).getIndex();
        Integer last = PageUtil.init(pageNo,pageSize).getPageSize();
        List<PlusArticle> list = plusArticleMapper.queryPlusArticleType(type,index,last);
        return list;
    }

    @Override
    public Integer countType(Integer type) {
        return plusArticleMapper.countPlusArticleType(type);
    }


    @Override
    public Integer insert(PlusArticle plusArticle) {
        return plusArticleMapper.insertPlusArticle(plusArticle);
    }

    @Override
    public PlusArticle findById(Long id) {
        return plusArticleMapper.findById(id);
    }

    @Override
    public int updateArticle(PlusArticle plusArticle) {
        return plusArticleMapper.updatePlusArticle(plusArticle);
    }

    @Override
    public Boolean notExist(Integer type) {
        return plusArticleMapper.exist(type) <= 0;
    }
}
