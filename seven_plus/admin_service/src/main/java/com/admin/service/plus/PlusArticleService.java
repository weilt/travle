package com.admin.service.plus;

import com.domain.plus.entity.PlusArticle;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/8 10:56
 * @Description: 文章
 */
public interface PlusArticleService {



    /**
     * 获取所有的文章
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<PlusArticle> getType(Integer type,Integer pageNo, Integer pageSize);

    /**
     * 查询总条数
     * @param type
     * @return
     */
    Integer countType(Integer type);


    /**
     *  添加
     * @param plusArticle
     * @return
     */
    Integer insert(PlusArticle plusArticle);


    /**
     * findById
     * @param id
     * @return
     */
    PlusArticle findById(Long id);

    /**
     *  更新
     * @param plusArticle
     * @return
     */
    int updateArticle(PlusArticle plusArticle);


    /**
     * 该类型是否不存在
     * @param type
     * @return
     */
    Boolean notExist(Integer type);
}
