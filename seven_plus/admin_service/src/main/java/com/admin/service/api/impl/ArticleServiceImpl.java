package com.admin.service.api.impl;

import com.admin.service.api.ArticleService;
import com.domain.plus.entity.PlusArticle;
import com.domain.plus.mapper.PlusArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 09:57
 * @Description:
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private PlusArticleMapper mapper;

    @Override
    public String getComment(Integer type) {
        return mapper.queryCommentByTypeLimit1(type);
    }

    @Override
    public PlusArticle getArticleByType(Integer type) {
        return mapper.queryArticleByType(type);
    }
}
