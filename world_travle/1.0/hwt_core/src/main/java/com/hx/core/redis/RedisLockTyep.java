package com.hx.core.redis;

/**
 * Created by Ro on 2018/4/12.
 * redis锁枚举类型
 */
public enum  RedisLockTyep {
    
	/**
	 * 下单导游
	 */
	orderCicerone("HXJLOrderCicerone_",5, 5);

    RedisLockTyep(String redisLockTypePrefix, int timeout, int expireSecs) {
        this.redisLockTypePrefix = redisLockTypePrefix;
        this.timeout = timeout;
        this.expireSecs = expireSecs;
    }

    /**
     * 锁缓存前缀
     */
    public String redisLockTypePrefix;
   

    /**
     * 获取锁的等待时间
     */
    public int timeout;
    
    /**
     * 锁的有效时间，必须大于0
     */
    public int expireSecs;

}
