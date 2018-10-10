package com.admin.service.plus;

import com.domain.plus.entity.OrderRecord;
import com.domain.plus.vo.OrderRecordVo;
import com.domain.plus.vo.OrderVo;

import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/14 17:10
 * @Description:
 */
public interface PlusOrderService {
    Integer countOrder(String phone, Integer count, String beginTime, String endTime);

    List<OrderVo> findOrder(String phone, Integer count, String beginTime, String endTime, Integer pageNumber, Integer pageSize);

    /**
     * 订单详情
     * @param id
     * @param storeId
     * @return
     */
    Map<String,Object> findById(Long id,Long storeId);

    Integer countWashOrder(String phone, String storeName, String storeAddress);

    List<OrderRecordVo> findWashOrder(String phone, String storeName, String storeAddress, Integer pageNumber, Integer pageSize);

    Integer countScratchOrder(String phone, String beginTime, String endTime, Integer state);

    List<OrderRecordVo> findScratchOrder(String phone, String beginTime, String endTime, Integer state, Integer pageNumber, Integer pageSize);

    /**
     * 获取订单使用记录
     * @param id
     * @return
     */
    OrderRecord findRecordById(Long id);

    /**
     * 审核
     * @param record
     * @return
     */
    Integer checkScratch(OrderRecord record);
}
