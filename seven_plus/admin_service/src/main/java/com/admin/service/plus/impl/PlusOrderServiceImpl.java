package com.admin.service.plus.impl;

import com.admin.service.plus.PlusOrderService;
import com.common.consts.Consts;
import com.common.util.DateUtil;
import com.common.util.PageUtil;
import com.domain.plus.entity.*;
import com.domain.plus.mapper.*;
import com.domain.plus.vo.OrderRecordVo;
import com.domain.plus.vo.OrderRenewVo;
import com.domain.plus.vo.OrderVo;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/14 17:10
 * @Description:
 */
@Service
public class PlusOrderServiceImpl implements PlusOrderService {

    @Autowired
    private PlusOrderMapper orderMapper;

    @Autowired
    private OrderRenewMapper renewMapper;

    @Autowired
    private OrderRecordMapper recordMapper;

    @Autowired
    private PlusUserMapper userMapper;

    @Autowired
    private PlusStoreMapper storeMapper;

    @Autowired
    private PlusCarMapper carMapper;

    @Override
    public Integer countOrder(String phone, Integer count, String beginTime, String endTime) {
        Long begin = DateUtil.getDayBeginDateTime(beginTime);
        Long end = DateUtil.getDayEndDateTime(endTime);
        return orderMapper.countOrder(phone,count,begin,end);
    }

    @Override
    public List<OrderVo> findOrder(String phone, Integer count, String beginTime, String endTime, Integer pageNumber, Integer pageSize) {
        Integer index = PageUtil.init(pageNumber,pageSize).getIndex();
        Integer last = PageUtil.init(pageNumber,pageSize).getPageSize();
        Long begin = DateUtil.getDayBeginDateTime(beginTime);
        Long end = DateUtil.getDayEndDateTime(endTime);
        List<OrderVo> list = orderMapper.queryOrder(phone,count,begin,end,index,last);
        List<OrderVo> result = new ArrayList<>();
        if (null != list && !list.isEmpty()){
            list.forEach(l -> {
                l.setOrderRenew(renewMapper.queryOrderRenewByOrder(l.getId()));
                result.add(l);
            });
        }
        return result;
    }

    @Override
    public Map<String,Object> findById(Long id,Long storeId) {
        PlusOrder order = orderMapper.queryOrderById(id);
        //用户信息
        PlusUser user = userMapper.queryById(order.getUserId());
        PlusUser parent = null;
        if (user.getParentId() > 0) {
            parent = userMapper.queryById(user.getParentId());
        }
        //订单信息
        OrderRenew renew = renewMapper.queryRenewByOrderId(order.getId());
        //车辆信息
        PlusCar plusCar = carMapper.findById(order.getCarId());
        PlusStore store = null;
        if (null != storeId){
            store = storeMapper.queryStoreById(storeId);
        }
        Map<String,Object> map = new HashMap<>();
        map.put("order",order);
        map.put("user",user);
        map.put("renew",renew);
        map.put("car",plusCar);
        map.put("store",store);
        map.put("parent",parent);
        return map;
    }

    @Override
    public Integer countWashOrder(String phone, String storeName, String storeAddress) {
        return recordMapper.countOrderRecord(phone,storeName,storeAddress,1);
    }

    @Override
    public List<OrderRecordVo> findWashOrder(String phone, String storeName, String storeAddress, Integer pageNumber, Integer pageSize) {
        Integer index = PageUtil.init(pageNumber,pageSize).getIndex();
        Integer last = PageUtil.init(pageNumber,pageSize).getPageSize();
        return recordMapper.queryOrderRecordVo(phone,storeName,storeAddress,1,index,last);
    }

    @Override
    public Integer countScratchOrder(String phone, String beginTime, String endTime, Integer state) {
        Long begin = DateUtil.getDayBeginDateTime(beginTime);
        Long end = DateUtil.getDayEndDateTime(endTime);
        return recordMapper.countScratchOrder(phone,begin,end,Consts.PlusOrderType.TYPE_2.getCode(),state);
    }

    @Override
    public List<OrderRecordVo> findScratchOrder(String phone, String beginTime, String endTime, Integer state, Integer pageNumber, Integer pageSize) {
        Integer index = PageUtil.init(pageNumber,pageSize).getIndex();
        Integer last = PageUtil.init(pageNumber,pageSize).getPageSize();
        Long begin = DateUtil.getDayBeginDateTime(beginTime);
        Long end = DateUtil.getDayEndDateTime(endTime);
        return recordMapper.queryScratchOrder(phone,begin,end,Consts.PlusOrderType.TYPE_2.getCode(),state,index,last);
    }

    @Transactional
    @Override
    public Integer checkScratch(OrderRecord record) {
        if (Consts.RecordState.AUDIT_PASS.ordinal() == record.getState()){
            PlusStore store = storeMapper.queryStoreById(record.getStoreId());
            store.setOrderCount(store.getOrderCount() == null? 1 : store.getOrderCount() + 1);
            store.setUpdateTime(System.currentTimeMillis());
            storeMapper.updatePlusStore(store);
            PlusOrder order = orderMapper.queryOrderById(record.getOrderId());
            order.setUpdateTime(System.currentTimeMillis());
            order.setUseCount(order.getUseCount()== null ? 1 : order.getUseCount() + 1);
            order.setTotalCount(order.getTotalCount() - 1);
            orderMapper.updatePlusOrder(order);
        }
        return recordMapper.updateOrderRecord(record);
    }

    @Override
    public OrderRecord findRecordById(Long id) {
        return recordMapper.queryRecordById(id);
    }
}
