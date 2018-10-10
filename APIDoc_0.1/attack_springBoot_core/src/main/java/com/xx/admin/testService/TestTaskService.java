package com.xx.admin.testService;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class TestTaskService {

	
	//测试线程池注解
	@Async
	public void test(){
		try {
			Thread.sleep(5000);
			System.out.println("进入线程池信息中");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
