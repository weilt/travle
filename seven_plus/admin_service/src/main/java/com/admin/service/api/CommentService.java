package com.admin.service.api;

import com.domain.plus.param.AddCommentParam;
import com.domain.plus.vo.CommentStoreVo;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 16:59
 * @Description:
 */
public interface CommentService {
    /**
     * 通过网点id获取评价
     * @param storeId
     * @param pageNo
     * @param pageSize
     * @return
     */
    List<CommentStoreVo> getStoreComment(Long storeId,Integer type,Integer pageNo, Integer pageSize);

    /**
     * 添加评价
     * @param userId
     * @param pa
     * @return
     */
    Integer addStoreComment(Long userId,AddCommentParam pa);

    /**
     * 判断
     * @param recordId
     * @return
     */
    Boolean commentUnique(Long recordId);
}
