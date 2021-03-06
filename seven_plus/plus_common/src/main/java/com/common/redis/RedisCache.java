package com.common.redis;

import com.common.util.ObjectHelper;
import com.common.util.PropertiesUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Component
public class RedisCache {
	
	private final static Logger log = LoggerFactory.getLogger(RedisCache.class);
	
	private static JedisPool jedisPool;// 非切片连接池

    // 测试用
    public static String ip = "127.0.0.1";
    public static Integer port = 6379;
    public static String pwd = "123456";
    
    
    public final static int db0 = 0;//redis区  --- ，会员 缓存数据
    public final static int db1 = 1;//redis区 ---- 配置数据信息，缓存基础配置
    public final static int db2 = 2;//redis区 ---- 
    public final static int db3 = 3;//redis区 ---- 
    public final static int db4 = 4;//redis区 ---- 
    public final static int db5 = 5;//redis区 ----
    public final static int db6 = 6;//redis区
    public final static int db7 = 7;//redis区
    public final static int db8 = 8;//redis区
    public final static int db9 = 9;//redis区
    public final static int db10 = 10;//redis区
    public final static int db11 = 11;//redis区
    public final static int db12 = 12;//redis区
    public final static int db13 = 13;//redis区
    public final static int db14 = 14;//redis区
    public final static int db15 = 15;//redis区

	public RedisCache() throws IOException {
		log.info("redis start connection");
		if (jedisPool == null) {
			Properties properties = PropertiesUtils.getProperties("application.properties");
			if(ObjectHelper.isNotEmpty(properties.getProperty("xx.redis.ip")) && ObjectHelper.isNotEmpty(properties.getProperty("xx.redis.port"))
					&& ObjectHelper.isNotEmpty(properties.getProperty("xx.redis.pwd"))){
				ip = properties.getProperty("xx.redis.ip");
				port = Integer.valueOf(properties.getProperty("xx.redis.port"));
				pwd = properties.getProperty("xx.redis.pwd");
				
				properties.clear();
				initialPool();
			}
		}
		log.info(" redis ip : {}, redis port : {}, redis pwd : {}" , RedisCache.ip,RedisCache.port, RedisCache.pwd);
	}

	public Jedis getJedisResource() {
		return jedisPool.getResource();
	}
	
	public JedisPool getJedisPool() {
		return jedisPool;
	}


	/**
	 * 初始化非切片池
	 */
	private void initialPool() {
		try {
            JedisPoolConfig config = new JedisPoolConfig();
            // 连接耗尽时是否阻塞, false报异常,ture阻塞直到超时, 默认true
            config.setBlockWhenExhausted(true);
            // 设置的逐出策略类名, 默认DefaultEvictionPolicy(当连接超过最大空闲时间,或连接数超过最大空闲连接数)
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy");
            // 是否启用pool的jmx管理功能, 默认true
            config.setJmxEnabled(true);
            // 最大空闲连接数, 默认8个 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
            config.setMaxIdle(10);
            // 最大连接数, 默认8个
            config.setMaxTotal(500);
            // 表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            config.setMaxWaitMillis(1000 * 100);
            // 在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            config.setTestOnBorrow(true);
            jedisPool = new JedisPool(config, ip, port, 3000, pwd);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
	}
	
	 /**
     * 获取Jedis实例
     * 
     * @return
     */
    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
            	new RedisCache();
            	if (jedisPool != null) {
                    Jedis resource = jedisPool.getResource();
                    return resource;
                }
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	
	/**
	 * 设置值
	 * 
	 * @Title: set
	 * @Description: TODO
	 * @param key 键
	 * @param value 值
	 * @param experice 失效时间
	 * @return
	 */
	public static boolean set(String key, String value, int experice) {
		Jedis jedis = getJedis();
		try {
			if ("OK".equals(jedis.set(key, value))) {
				jedis.expire(key, experice);
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		} finally {
			if(jedis != null)
				jedis.close();
		}
	}

	
	 
	
	/**
	 * 设置值
	 * @Title: set
	 * @Description: TODO
	 * @param key 键
	 * @param value 值
	 * @return
	 */
	public static boolean set(String key, String value) {
		Jedis jedis = getJedis();
		try {
			if ("OK".equals(jedis.set(key, value))) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(jedis != null)
				jedis.close();
		}
	}
	/**
	 * 设置值
	 * 分区
	 * @Title: set
	 * @Description: TODO
	 * @param key 键
	 * @param value 值
	 * @param redisIndex 区
	 * @return
	 */
	public  static boolean setpar(String key, String value,Integer redisIndex) {
		Jedis jedis = getJedis();
		try {
			jedis.select(redisIndex == null ? 0 : redisIndex);
			if ("OK".equals(jedis.set(key, value))) {
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(jedis != null)
				jedis.close();
		}
	}
	/**
	 * 设置值
	 * 分区
	 * @Title: set
	 * @Description: TODO
	 * @param key 键
	 * @param value 值
	 * @param experice 失效时间
	 * @param redisIndex 区
	 * @return
	 */
	public  static boolean setpar(String key, String value,int experice,Integer redisIndex) {
		Jedis jedis = getJedis();
		try {
			jedis.select(redisIndex == null ? 0 : redisIndex);
			if ("OK".equals(jedis.set(key, value))) {
				jedis.expire(key, experice);
				return true;
			}
			return false;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		} finally {
			if(jedis != null)
				jedis.close();
		}
	}
	
	/**
	* 获取值
	* @Title: get 
	* @Description: TODO
	*  @param key
	*  @return    
	*/
	public static String get(String key) {
		Jedis jedis = getJedis();
		try {
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(jedis != null)
				jedis.close();
		}
	}
	
	
	/**
	 *  获取值
	 *  @param key
	 *  @param redisIndex 区
	 *  @return    
	 */
	public static String get(String key,Integer redisIndex) {
		Jedis jedis = getJedis();
		try {
			jedis.select(redisIndex == null ? 0 : redisIndex);
			String value = jedis.get(key);
			return value;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			if(jedis != null)
				jedis.close();
		}
	}

    /**
     * 删除值
     * @Title: get
     * @Description: TODO
     *  @param key
     *  @return
     */
    public static void del(String key) {
    	Jedis jedis = getJedis();
        try {
            jedis.del(key);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			if(jedis != null)
				jedis.close();
		}
    }

	/**
	 *  删除值
	 *  @param key
	 *  @param redisIndex 区
	 *  @return    
	 */
	public static void del(String key,Integer redisIndex) {
		Jedis jedis = getJedis();
		try {
            jedis.select(redisIndex == null ? 0 : redisIndex);
			jedis.del(key);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if(jedis != null)
				jedis.close();
		}
	}

    /**
     * 添加map到redis
     * @param key
     * @param map
     * @param redisIndex    数据库索引
     */
	public static void putMap(String key, Map<String, String> map, Integer redisIndex) {
		Jedis jedis = getJedis();
        try {
            jedis.select(redisIndex == null ? 0 : redisIndex);
            jedis.hmset(key, map);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			if(jedis != null)
				jedis.close();
		}
    }

    /**
     * 追加到map
     * @param key key值
     * @param field map键值
     * @param value 值
     * @param redisIndex 区
     */
    public static void appendMap(String key, String field, String value, Integer redisIndex) {
    	Jedis jedis = getJedis();
        try {
            jedis.select(redisIndex == null ? 0 : redisIndex);
            jedis.hset(key,field,value);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			if(jedis != null)
				jedis.close();
		}
    }

    /**
     * 删除map指定的值
     * @param key
     * @param redisIndex - 区
     * @param field 1-N个map的key
     */
    public static void removeMap(String key,Integer redisIndex, String... field) {
    	Jedis jedis = getJedis();
        try {
            jedis.select(redisIndex == null ? 0 : redisIndex);
            jedis.hdel(key, field);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
			if(jedis != null)
				jedis.close();
		}
    }

    /**
     * 获取值
     * @param key
     * @param field 键值
     * @param redisIndex 区
     * @return
     */
    public static String getMap(String key, String field,Integer redisIndex) {
    	Jedis jedis = getJedis();
        try {
            jedis.select(redisIndex == null ? 0 : redisIndex);
            String value = jedis.hget(key,field);
            return value;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
			if(jedis != null)
				jedis.close();
		}
    }

    /**
     * 获取map
     * @param key
     * @param redisIndex 键值
     * @return
     */
    public static Map<String,String> getMap(String key, Integer redisIndex) {
    	Jedis jedis = getJedis();
        try {
            jedis.select(redisIndex == null ? 0 : redisIndex);
            Map<String,String> map = jedis.hgetAll(key);
            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
			if(jedis != null)
				jedis.close();
		}
    }
}

