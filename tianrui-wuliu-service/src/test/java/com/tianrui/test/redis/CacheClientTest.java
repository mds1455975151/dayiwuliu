package com.tianrui.test.redis;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.service.bean.Bill;
import com.tianrui.service.cache.CacheClient;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class CacheClientTest {
	
	public static Logger logger =LoggerFactory.getLogger(CacheClientTest.class);
	
	@Autowired
	CacheClient cache;
	
	@Test
	public void stringTest(){
		
		cache.saveString("key1", System.currentTimeMillis()+"",600 );
		cache.remove("key1");
		
		System.out.println("@@"+cache.getString("key1"));
		
	}
	@Test
	public void objTest(){
		
		List<Bill> list =new ArrayList<Bill>();
		Bill bill1 =new Bill();
		bill1.setCargoname("111");
		Bill bill2 =new Bill();
		bill1.setCargoname("2");
		Bill bill3 =new Bill();
		bill1.setCargoname("3");
		list.add(bill1);
		list.add(bill2);
		list.add(bill3);
		
		//cache.saveObject("testObj", bill1);
		
		Bill rs =cache.getObj("testObj", Bill.class);
		System.out.println(rs.getCargoname());
		
	}
	
	
}
