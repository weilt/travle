package com.xx.springBootUtil.threadPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Administrator on 2017/8/18.
 */
@Configuration
@EnableAsync
public class AsyncTaskExecutePool{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
	private TaskThreadPoolConfig config;
    
    @Bean
    public ThreadPoolTaskExecutor backExecutor() {
    	ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize((config.getCorePoolSize() == 0) ? 5 : config.getCorePoolSize());
		executor.setMaxPoolSize((config.getMaxPoolSize() == 0) ? 50 : config.getMaxPoolSize());
		executor.setQueueCapacity((config.getQueueCapacity() == 0) ? 30 :config.getQueueCapacity());
		executor.setKeepAliveSeconds((config.getKeepAliveSeconds() == 0) ? 3000 : config.getKeepAliveSeconds());
		executor.setThreadNamePrefix("taskExecutor-");
		// rejection-policy：当pool已经达到max size的时候，如何处理新任务
		// CALLER_RUNS：不在新线程中执行任务，而是由调用者所在的线程来执行
		executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
		executor.initialize();
		return executor;
    }
    
}