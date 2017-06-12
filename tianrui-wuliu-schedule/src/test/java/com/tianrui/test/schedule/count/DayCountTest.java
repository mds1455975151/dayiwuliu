package com.tianrui.test.schedule.count;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.ICountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DayCountTest {

	@Autowired
	ICountService countService; 
	
	//车辆总数
	@Test
	public void vehicleCountSumTest(){
		//countService.
		
	} 
	//货运总数
	//交易总数
	//运费总额
}
