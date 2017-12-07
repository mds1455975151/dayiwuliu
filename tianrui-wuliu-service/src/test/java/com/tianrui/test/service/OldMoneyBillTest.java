package com.tianrui.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.intf.IMoenyDisposeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class OldMoneyBillTest {
	public static Logger logger =LoggerFactory.getLogger(OldMoneyBillTest.class);
	@Autowired
	IMoenyDisposeService moenyDisposeService;
	
	
	public void saveWithDrawFail() throws Exception{
		logger.info("执行开始");
		System.out.println("开始");
		moenyDisposeService.saveWithDrawFail();
		logger.info("执行结束");
		System.out.println("执行结束");
	}
	
	public void saveWithDrawSuccess() throws Exception{
		logger.info("执行开始");
		System.out.println("开始");
		moenyDisposeService.saveWithDrawSuccess();
		logger.info("执行结束");
		System.out.println("执行结束");
	}
	
	public void saveWithDraw() throws Exception{
		logger.info("执行开始");
		System.out.println("开始");
		moenyDisposeService.saveWithDraw();
		logger.info("执行结束");
		System.out.println("执行结束");
	}
	
	public void uptBillMoney() throws Exception{
		System.out.println("开始");
		moenyDisposeService.uptPandMoney();
		System.out.println("执行结束");
	}
	@Test
	public void saveOldALBill() throws Exception{
		System.out.println("开始");
		moenyDisposeService.oldALSaveBillMoney();
		System.out.println("执行结束");
	}
	
	
	public void saveOldDYBill() throws Exception{
		logger.info("执行开始");
		System.out.println("开始");
		moenyDisposeService.oldDYSaveBillMoney();
		logger.info("执行结束");
		System.out.println("执行结束");
	}
}
