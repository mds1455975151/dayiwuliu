package com.tianrui.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.ICountIndexService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class CountIndexServiceTest {
	public static Logger logger =LoggerFactory.getLogger(CountIndexServiceTest.class);
	@Autowired
	private  ICountIndexService  countIndexService;
	
	@Test
	public void everyDay()throws Exception{
		
//		for( int i=1;i<=31;i++){
//			countIndexService.everyDay("2017-05-"+i);
//		}
		
		for( int i=1;i<=14;i++){
			countIndexService.everyDay("2017-06-"+i);
		}
	}
	
}
