package com.tianrui.service.cache;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import com.alibaba.fastjson.JSON;


public class CacheClient {



	private int localTTL = 2* 60 * 60;//默认生命周期 2 小时

	private static Logger logger = Logger.getLogger(CacheClient.class);

	public void setLocalTTL(int localTTL) {
		this.localTTL = localTTL;
	}

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 
	 * @描述:根据key值获取cache封装后的对象
	 * @param key
	 * @return key对应的Object 
	 * @返回类型 Object
	 * @创建人 tank
	 * @创建时间 2016年1月16日下午2:36:51
	 */
	public <T> T getObj(String key,Class<?> clazz) {
		T rs =null;
		try {
			String  str=redisTemplate.opsForValue().get(key).toString();
			rs= (T)JSON.parseObject(str,clazz);
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} 
		return rs;
	}
	
	public String getString(String key) {
		String rs=null;
		try {
			rs=redisTemplate.opsForValue().get(key).toString();
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} 
		return rs;
		
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
		boolean rs =false;
		try {
			String value_str = JSON.toJSONString(value);
			if( expiredTime==null ){
				redisTemplate.opsForValue().set(key,value_str,localTTL,TimeUnit.SECONDS);
			}else if(expiredTime ==0 ){
				redisTemplate.opsForValue().set(key,value_str);
			}else{
				redisTemplate.opsForValue().set(key,value_str,expiredTime,TimeUnit.SECONDS);
			}
			rs=true;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} 
		return rs;
	}
	
	
	public boolean saveString(String key, String value, Integer expiredTime) {
		boolean rs =false;
		try {
			redisTemplate.opsForValue().set(key, value, expiredTime,TimeUnit.SECONDS);
			rs=true;
		} catch (Exception e) {
			logger.error(e.getLocalizedMessage());
		} 
		return rs;
	}


	public void remove(String key){
		redisTemplate.opsForValue().set(key, "",1L, TimeUnit.MICROSECONDS );
	}

	public boolean saveObject(String key, Object value) {
		return saveObject(key, value, localTTL);
	}


	public int getLocalTTL() {
		return localTTL;
	}

}
