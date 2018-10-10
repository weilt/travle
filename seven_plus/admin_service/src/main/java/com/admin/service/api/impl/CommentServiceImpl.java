package com.admin.service.api.impl;

import com.admin.service.api.CommentService;
import com.common.excption.AuthExceptionConstants;
import com.common.excption.BaseException;
import com.common.util.PageUtil;
import com.domain.plus.entity.PlusComment;
import com.domain.plus.mapper.PlusCommentMapper;
import com.domain.plus.param.AddCommentParam;
import com.domain.plus.vo.CommentStoreVo;
import io.swagger.annotations.ApiModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/22 16:59
 * @Description:
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private PlusCommentMapper commentMapper;


    @Override
    public Boolean commentUnique(Long recordId) {
        if (null == recordId)
            return Boolean.FALSE;
        int count = commentMapper.countCommentByRecordId(recordId);
        return count <= 0;
    }

    @Override
    public Integer addStoreComment(Long userId, AddCommentParam pa) {
        int count = commentMapper.countCommentByRecordId(pa.getRecordId());
        if (count > 0){
            throw BaseException.build(AuthExceptionConstants.COMMENT_UNIQUE);
        }
        PlusComment plusComment = new PlusComment();
        plusComment.setUserId(userId);
        plusComment.setStoreId(pa.getStoreId());
        plusComment.setType(pa.getType());
        plusComment.setComment(pa.getComment());
        plusComment.setRecordId(pa.getRecordId());
        plusComment.setCreateTime(System.currentTimeMillis());
        return commentMapper.insertPlusComment(plusComment);
    }

    @Override
    public List<CommentStoreVo> getStoreComment(Long storeId,Integer type, Integer pageNo, Integer pageSize) {
        Integer index = PageUtil.init(pageNo,pageSize).getIndex();
        Integer last = PageUtil.init(pageSize,pageSize).getPageSize();
        List<CommentStoreVo> list = commentMapper.queryCommentByStoreId(storeId,type,index,last);
        List<CommentStoreVo> result = new ArrayList<>();
        if (null != list && !list.isEmpty()){
            list.stream().forEach(l -> {
                if (StringUtils.isEmpty(l.getPhone())){
                    l.setPhone("135****4569");
                } else {
                    l.setPhone( l.getPhone().replaceAll("(\\d{3})\\d{4}(\\d{4})","$1****$2"));
                }
                result.add(l);
            });
        }
        return result;
    }
}
