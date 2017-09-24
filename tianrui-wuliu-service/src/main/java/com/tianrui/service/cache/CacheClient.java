package com.tianrui.service.cache;

import java.util.Set;
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
			//logger.warn(e.getLocalizedMessage());
		} 
		return rs;
	}
	
	public Set<String>  getList(String keys){
		Set<String> rs = null;
		rs = redisTemplate.keys(keys);
		return rs;
	}
	
	public String getString(String key) {
		String rs=null;
		try {
			rs=redisTemplate.opsForValue().get(key).toString();
		} catch (Exception e) {
			logger.warn(e.getLocalizedMessage());
		} 
		return rs;
		
	}

	/**
	 * 
	 * @描述: 创建redis存储并设置缓存的生存时间
	 * @param key
	 * @param value
	 * @param expiredTime  expiredTime=-1时 永久保存   为null或者0时默认2小时 
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
			if( expiredTime==null || expiredTime==0 ){
				redisTemplate.opsForValue().set(key,value_str,localTTL,TimeUnit.SECONDS);
			}else if(expiredTime ==-1 ){
				redisTemplate.opsForValue().set(key,value_str);
			}else{
				redisTemplate.opsForValue().set(key,value_str,expiredTime,TimeUnit.SECONDS);
			}
			rs=true;
		} catch (Exception e) {
			logger.warn(e.getLocalizedMessage());
		} 
		return rs;
	}
	
	/**
	 * @param key
	 * @param value
	 * @param expiredTime expiredTime=-1时 永久保存   为null或者0时默认2小时 
	 * @return
	 */
	public boolean saveString(String key, String value, Integer expiredTime) {
		boolean rs =false;
		try {
			if( expiredTime == -1 ){
				redisTemplate.opsForValue().set(key, value);
			}else if(expiredTime==null || expiredTime==0){
				redisTemplate.opsForValue().set(key, value, localTTL,TimeUnit.SECONDS);
			}else{
				redisTemplate.opsForValue().set(key, value, expiredTime,TimeUnit.SECONDS);
			}
			rs=true;
		} catch (Exception e) {
			logger.warn(e.getLocalizedMessage());
		} 
		return rs;
	}

	//设置过期时间 单位为秒
	public void setExpire(String key,Long second){
		redisTemplate.expire(key, second, TimeUnit.SECONDS);
	}
	//删除key
	public void remove(String key){
		redisTemplate.delete(key);
	}

	public boolean saveObject(String key, Object value) {
		return saveObject(key, value, localTTL);
	}


	public int getLocalTTL() {
		return localTTL;
	}

}
