package com.domain.plus.mapper;

import java.util.List;

import com.domain.plus.vo.CommentStoreVo;
import com.domain.plus.vo.CommentVo;
import io.swagger.models.auth.In;
import org.apache.ibatis.annotations.Mapper;
import com.domain.plus.entity.PlusComment;
import org.apache.ibatis.annotations.Param;

/**
*  @author zhoudu
*/
@Mapper
public interface PlusCommentMapper {

    int insertPlusComment(PlusComment object);

    int updatePlusComment(PlusComment object);

    int update(PlusComment.UpdateBuilder object);

    List<PlusComment> queryPlusComment(PlusComment object);

    PlusComment queryPlusCommentLimit1(PlusComment object);

    int deleteComment(@Param("id")Long id);

    List<CommentVo> queryCommentList(@Param("phone")String phone, @Param("storeName")String storeName,
                                     @Param("storeAddress")String storeAddress,
                                     @Param("index") Integer index, @Param("last") Integer last);

    Integer countComment(@Param("phone")String phone, @Param("storeName")String storeName,
                         @Param("storeAddress")String storeAddress);

    CommentVo queryCommentById(@Param("id") Long id);

    List<CommentStoreVo> queryCommentByStoreId(@Param("storeId")Long storeId, @Param("type")Integer type,
                                               @Param("index") Integer index, @Param("last") Integer last);

    Integer countCommentByRecordId(@Param("recordId")Long recordId);
}