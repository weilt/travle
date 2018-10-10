package com.domain.plus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.PlusArticle;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface PlusArticleMapper {

    int insertPlusArticle(PlusArticle object);

    int updatePlusArticle(PlusArticle object);

    int update(PlusArticle.UpdateBuilder object);

    List<PlusArticle> queryPlusArticle(PlusArticle object);

    /**
     * 类型查询分页
     * @param type
     * @param index
     * @param last
     * @return
     */
    List<PlusArticle> queryPlusArticleType(@Param("type")Integer type, @Param("index")Integer index,@Param("last")Integer last );

    /**
     * 类型查询总条数
     * @param type
     * @return
     */
    Integer countPlusArticleType(@Param("type")Integer type);

    PlusArticle queryPlusArticleLimit1(PlusArticle object);


    PlusArticle findById(@Param("id") Long id);

    Integer exist(@Param("type")Integer type);

    /**
     *  通过类型查询一个
     * @return
     */
    String queryCommentByTypeLimit1(@Param("type")Integer type);

    PlusArticle queryArticleByType(@Param("type")Integer type);
}