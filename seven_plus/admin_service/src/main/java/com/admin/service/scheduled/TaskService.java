package com.admin.service.scheduled;


/**
 * @Auther: Zhoudu
 * @Date: 2018/9/3 09:39
 * @Description:
 */
public interface TaskService {

    /**
     * 清理订单数据
     */
    void orderTask();


    /**
     * 清理车辆数据
     */
    void carTask();
}
