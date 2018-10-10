package com.plus.admin.scheduled;

import com.admin.service.scheduled.TaskService;
import com.domain.plus.entity.PlusOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Auther: Zhoudu
 * @Date: 2018/8/31 17:00
 * @Description: 定时任务
 */
@Component
@EnableScheduling
public class ScheduledTask {


    @Autowired
    private TaskService taskService;


    /**
     * 订单定时任务 （每天23：59：59执行）
     */
    @Scheduled(cron = "59 59 23 * * ?")
    public void orderTask(){
        taskService.orderTask();
    }

    /**
     * 车辆定时任务 （每天23：59：59执行）
     */
    @Scheduled(cron = "59 59 23 * * ?")
    public void carTask(){
        taskService.carTask();
    }
}
