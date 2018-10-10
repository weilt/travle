package com.admin.service.plus.impl;

import com.admin.service.plus.PlusCommentService;
import com.common.util.PageUtil;
import com.domain.plus.mapper.PlusCommentMapper;
import com.domain.plus.vo.CommentVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/13 10:40
 * @Description:
 */
@Service
public class PlusCommentServiceImpl implements PlusCommentService {

    @Autowired
    private PlusCommentMapper commentMapper;

    @Override
    public Integer countComment(String phone, String storeName, String storeAddress) {
        return commentMapper.countComment(phone,storeName,storeAddress);
    }

    @Override
    public List<CommentVo> findComment(String phone, String storeName, String storeAddress, int pageNumber, int pageSize) {
        Integer index = PageUtil.init(pageNumber,pageSize).getIndex();
        Integer last = PageUtil.init(pageNumber,pageSize).getPageSize();
        return commentMapper.queryCommentList(phone,storeName,storeAddress,index,last);
    }


    @Override
    public Integer deleteComment(Long... ids) {
        return Arrays.stream(ids).mapToInt(l -> commentMapper.deleteComment(l)).sum();
    }

    @Override
    public CommentVo commentInfo(Long id) {
        return commentMapper.queryCommentById(id);
    }
}
