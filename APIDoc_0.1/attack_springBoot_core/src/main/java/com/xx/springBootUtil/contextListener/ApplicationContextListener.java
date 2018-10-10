package com.xx.springBootUtil.contextListener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.mysql.cj.jdbc.AbandonedConnectionCleanupThread;
import com.xx.springBootUtil.redis.RedisCache;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Enumeration;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * 容器启动 销毁时的控制
 * 可以解决部分启动的业务线程或者线程池在容器结束时不能及时销毁的情况
 */
@WebListener
public class ApplicationContextListener implements ServletContextListener {
	
	private static final Logger LOGGER =  LoggerFactory.getLogger(ApplicationContextListener.class);
	
    private ServletContext servletContext;
    private RedisCache redis;
    @Autowired
    @Qualifier("backExecutor")
    private ThreadPoolTaskExecutor backExecutorTo;
    /**
     * 初始化
     * @param sce
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	if(servletContext == null){
    		servletContext = sce.getServletContext();
    	}
        WebApplicationContext  ac2 = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        redis = ac2.getBean(RedisCache.class);
        //线程池
    }

    /**
     * 销毁
     * @param sce
     */
	@Override
    public void contextDestroyed(ServletContextEvent sce) {
		redis.getJedisPool().close();
		/*try {
			DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		destroy(); //关闭数据库
    	if(backExecutorTo != null){
			LOGGER.debug("backExecutorTo -- :线程池不为空：正在关闭线程池");
        	shutdownAndAwaitTermination(backExecutorTo.getThreadPoolExecutor());
        }else{
        	LOGGER.debug("backExecutorTo -- :线程池可能未关闭");
        }
    }


	private void shutdownAndAwaitTermination(ExecutorService pool) {
        pool.shutdown(); // Disable new tasks from being submitted
        try {
            // Wait a while for existing tasks to terminate
            if (!pool.awaitTermination(30, TimeUnit.SECONDS)) {
                pool.shutdownNow(); // Cancel currently executing tasks
                // Wait a while for tasks to respond to being cancelled
                if (!pool.awaitTermination(30, TimeUnit.SECONDS))
                    System.err.println("Pool did not terminate");
            }
        } catch (InterruptedException ie) {
            // (Re-)Cancel if current thread also interrupted
            pool.shutdownNow();
            // Preserve interrupt status
            Thread.currentThread().interrupt();
        }
    }
	
	/** 
     * 在项目结束的时候，应释放所有资源，应调用本方法。建议阅读C3P0官网。 
     */  
    @SuppressWarnings("deprecation")
	public static void destroy(){  
//        AbandonedConnectionCleanupThread.checkedShutdown();  
    	try {
	    	AbandonedConnectionCleanupThread.shutdown();
	        Enumeration<Driver> drivers = DriverManager.getDrivers();  
	        while(drivers.hasMoreElements()) {  
	                Driver driver = drivers.nextElement();  
	                DriverManager.deregisterDriver(driver);  
	        }
	        Set<Thread> threadSet = Thread.getAllStackTraces().keySet();
	        Thread[] threadArray = threadSet.toArray(new Thread[threadSet.size()]);
	        for(Thread t:threadArray) {
	            if(t.getName().contains("Abandoned connection cleanup thread")) {
	                synchronized(t) {
	                    t.stop(); //don't complain, it works
	                }
	            }
	        }
    	} catch (Exception e) {  
        }
    }  
    
    public void setRedis(RedisCache redis) {
        this.redis = redis;
    }
}