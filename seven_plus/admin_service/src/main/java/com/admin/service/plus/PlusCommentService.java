package com.admin.service.plus;

import com.domain.plus.vo.CommentVo;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/13 10:40
 * @Description:
 */
public interface PlusCommentService {
    Integer countComment(String phone, String storeName, String storeAddress);

    List<CommentVo> findComment(String phone, String storeName, String storeAddress, int pageNumber, int pageSize);

    /**
     * 批量删除
     * @param ids
     * @return
     */
    Integer deleteComment(Long ... ids);

    CommentVo commentInfo(Long id);
}
