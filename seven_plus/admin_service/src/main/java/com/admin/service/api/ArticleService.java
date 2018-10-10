package com.admin.service.api;

import com.domain.plus.entity.PlusArticle;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/16 09:56
 * @Description:
 */
public interface ArticleService {

    /**
     * 通过类型获取文章内容
     * @param type
     * @return
     */
    String getComment(Integer type);

    /**
     * 通过类型查文章
     * @param type
     * @return
     */
    PlusArticle getArticleByType(Integer type);
}
