package com.hx.order.service.hx;

import java.util.List;
import java.util.Map;

import com.hwt.domain.entity.order.HwOrderComment;
import com.hwt.domain.entity.order.vo.HwOrderCommentListVo;
import com.hwt.domain.entity.order.vo.HwOrderCommentVo;

public interface OrderCommentService {

	
	/**
     * 评论
     * @param token
     * @param order_id				订单id
     * @param parent_comment_id		恢复的哪一条  0不是回复
     * @param parent_user_id		回复的谁 0-不是回复的谁 
     * @param comment_dec			评价描述
     * @param image					图片组 最多6张
     * @param match_score			相符度  1-1星 2-2星 3-3星 4-4星 5-5星  线路的订单必填 
     * @param trip_score			行程安排  1-1星 2-2星 3-3星 4-4星 5-5星  线路的订单必填 
     * @param service_score			服务  1-1星 2-2星 3-3星 4-4星 5-5星    线路的订单必填 
     * @param score					综合评分  1-1星 2-2星 3-3星 4-4星 5-5星    导游的订单必填
     * @return
     * @throws Exception
     */
	HwOrderComment insert(Long user_id, Long order_id, Long parent_comment_id, Long parent_user_id, String comment_dec,
			String image, Integer match_score, Integer trip_score, Integer service_score, Float score);

	 /**
     * 评论查询
     */
	HwOrderCommentListVo query(Map<String, Object> map);

}
