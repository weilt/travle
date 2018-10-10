package com.admin.service.scheduled.impl;

import com.admin.service.scheduled.TaskService;
import com.common.util.PageUtil;
import com.domain.plus.entity.OrderRenew;
import com.domain.plus.entity.PlusOrder;
import com.domain.plus.mapper.OrderRenewMapper;
import com.domain.plus.mapper.PlusCarMapper;
import com.domain.plus.mapper.PlusOrderMapper;
import com.domain.plus.vo.PlusCarTaskVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/9/3 09:39
 * @Description:
 */
@Service
public class TaskServiceImpl implements TaskService {

    /**
     * 日志
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);

    /**
     * 一天的时间
     */
    private static final long DAY_LONG = 24 * 60 * 60 * 1000;

    @Autowired
    private PlusOrderMapper orderMapper;

    @Autowired
    private OrderRenewMapper renewMapper;

    @Autowired
    private PlusCarMapper carMapper;

    /**
     * 获取未支付订单
     * @param index 开始页
     * @param size 页面大小
     * @return
     */
    private List<PlusOrder> getOrderByInvalid(Integer index,Integer size) {
        Integer begin = PageUtil.init(index,size).getIndex();
        Integer pageSize = PageUtil.init(index,size).getPageSize();
        return orderMapper.queryOrderByUnPay(begin,pageSize);
    }

    /**
     *  获取车辆
     * @param index 开始页
     * @param size 页面大小
     * @return
     */
    private List<PlusCarTaskVo> getCarTask(Integer index, Integer size) {
        Integer begin = PageUtil.init(index,size).getIndex();
        Integer pageSize = PageUtil.init(index,size).getPageSize();
        return carMapper.queryCarTask(begin,pageSize);
    }

    @Transactional
    @Override
    public void carTask() {
        int index = 0;
        LOGGER.info("定时任务清理车辆数据开始....");
        while (true) {
            List<PlusCarTaskVo> list = getCarTask(index, 100);
            if (null == list || list.isEmpty()){
                break;
            }
            index ++;
            list.stream().forEach(l -> {
                if (null == l.getOrderId() && l.getCreateTime() - System.currentTimeMillis() > DAY_LONG){
                    //删除
                    carMapper.deleteCarById(l.getId());
                }
            });
        }
        LOGGER.info("定时任务清理车辆数据结束！");
    }

    @Transactional
    @Override
    public void orderTask() {
        int index = 0;
        LOGGER.info("定时任务清理订单数据开始！");
        while (true) {
            List<PlusOrder> list = getOrderByInvalid(index,100);
            if (null == list || list.isEmpty()){
                break;
            }
            index ++;
            list.stream().forEach(l -> {
                if (l.getCreateTime() - System.currentTimeMillis() > DAY_LONG ){
                    //删除订单
                    //删除 订单支付记录
                    renewMapper.deleteByOrderId(l.getId());
                    orderMapper.deleteOrderById(l.getId());
                }
            });
        }
        LOGGER.info("定时任务清理订单数据结束！");
    }
}
