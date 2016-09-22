package com.tianrui.test.redis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import redis.clients.jedis.Jedis;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class RedisTest {
	
	public static Logger logger =LoggerFactory.getLogger(RedisTest.class);
	
	@Autowired
	RedisTemplate redisTemplate;
	
	@Test
	public void saveTest(){
		
		redisTemplate.opsForValue().set("key", ""+System.currentTimeMillis());
	}
	
	
}
