package com.tianrui.service.cache;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.SafeEncoder;


public class CacheClient {

	private ShardedJedisPool jedisPool;
	
	private final CacheHelper cacheHelper = new CacheHelper();

	private long shutdownTimeout = 1000;

	private ShardedJedis jedis;

	private int localTTL = 60 * 60;//默认生命周期

	private static Logger logger = Logger.getLogger(CacheClient.class);

	public void setLocalTTL(int localTTL) {
		this.localTTL = localTTL;
	}

	public ShardedJedis getJedis() {
		jedis = jedisPool.getResource();
		return jedis;
	}

	public void returnJedis(ShardedJedis jedis) {
		jedisPool.returnResource(jedis);
	}

	/**
	 * 
	 * @描述:根据key值获取cache封装后的对象
	 * @param key
	 * @return key对应的Object 
	 * @返回类型 Object
	 * @创建人 tank
	 * @创建时间 2016年1月16日下午2:36:51
	 */
	public <T> T getObj(String key,Class clazz) {
		ShardedJedis jedis = jedisPool.getResource();
		T rs = null;
		try {
			String jsonStr =jedis.get(key);
			rs= (T)JSON.parseObject(jsonStr,clazz);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
		    if(jedis != null)
		        jedisPool.returnResource(jedis);
		}
		return rs;
	}
	
	public String getString(String key) {
		ShardedJedis jedis = jedisPool.getResource();
		String obj = null;
		try {
			obj =jedis.get(key);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
			if(jedis != null)
				jedisPool.returnResource(jedis);
		}
		return obj;
		
	}

	/**
	 * 
	 * @描述: 创建redis存储并设置缓存的生存时间
	 * @param key
	 * @param value
	 * @param expiredTime
	 * 单位秒
	 * @return 成功或者失败
	 * @返回类型 boolean
	 * @创建人 tank
	 * @创建时间 2016年1月16日下午2:47:24
	 */
	public boolean saveObject(String key, Object value, Integer expiredTime) {
		ShardedJedis jedis = jedisPool.getResource();
		boolean tag =true;
		try {
			jedis.set(key, JSON.toJSONString(value));
			if(expiredTime>0){
			    jedis.expire(key, expiredTime);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			tag =false;
		} finally {
		    if(jedis != null)
                jedisPool.returnResource(jedis);
		}
		return tag;
	}
	
	
	public boolean saveString(String key, String value, Integer expiredTime) {
		ShardedJedis jedis = jedisPool.getResource();
		boolean tag =true;
		try {
			jedis.set(key, value);
			if(expiredTime>0){
				jedis.expire(key, expiredTime);
			}
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
			tag =false;
		} finally {
			if(jedis != null)
				jedisPool.returnResource(jedis);
		}
		return tag;
	}

	/**
	 * 
	 * @描述: 根据key值删除redis中的数据
	 * @param key
	 * @return 成功或者失败
	 * @返回类型 boolean
	 * @创建人 tank
	 * @创建时间 2016年1月16日下午2:45:58
	 */
	public boolean remove(String key) {
			ShardedJedis jedis = jedisPool.getResource();
			boolean tag = true;
			try {
				jedis.del(key);
			} catch (Exception e) {
				logger.error(e.getLocalizedMessage());
				tag = false;
			} finally {
				jedisPool.returnResource(jedis);
			}
			return tag;
	}
	

	/**
	 * 
	 * @描述: 判断对象是否存在
	 * @param key
	 * @返回类型 boolean
	 * @创建人 tank
	 * @创建时间 2016年1月16日下午3:39:15
	 */
	public boolean isExist(String key) {
        ShardedJedis jedis = jedisPool.getResource();
        try {
            return jedis.exists(key);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            return false;
        } finally {
            jedisPool.returnResource(jedis);
        }
    }
	/**
	 * 
	 * @描述: 获取key的有效时间,返回有效时间单位是秒
	 * @param key
	 * @return key的有效时间
	 * @返回类型 long
	 * @创建人 tank
	 * @创建时间 2016年1月16日下午3:35:07
	 */
	public long getTTL(String key){
		long ttl=0;
		ShardedJedis jedis = jedisPool.getResource();
		try {
			ttl = jedis.ttl(SafeEncoder.encode(key));
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} finally {
		    if(jedis != null)
		        jedisPool.returnResource(jedis);
		}
		return ttl;
	}

	public boolean saveObject(String key, Object value) {
		return saveObject(key, value, localTTL);
	}

	public boolean saveString(String key,String val) {
		return saveString(key, val, localTTL);
	}
	
	public ShardedJedisPool getJedisPool() {
		return jedisPool;
	}

	public void setJedisPool(ShardedJedisPool jedisPool) {
		this.jedisPool = jedisPool;
	}

	public CacheHelper getCacheHelper() {
		return cacheHelper;
	}

	public long getShutdownTimeout() {
		return shutdownTimeout;
	}

	public void setShutdownTimeout(long shutdownTimeout) {
		this.shutdownTimeout = shutdownTimeout;
	}

	public int getLocalTTL() {
		return localTTL;
	}

	public void setJedis(ShardedJedis jedis) {
		this.jedis = jedis;
	}

}
