package com.tianrui.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.money.intf.ICapitalAccountService;
import com.tianrui.api.req.money.CheckPasswordReq;
import com.tianrui.api.req.money.SavePasswordReq;
import com.tianrui.common.vo.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class CheckMoenyTestTest {
	public static Logger logger =LoggerFactory.getLogger(CheckMoenyTestTest.class);
	@Autowired
	ICapitalAccountService capitalAccountService;
	
	@Test
	public void checkPassword() throws Exception{
		logger.info("执行开始");
		System.out.println("开始");
		CheckPasswordReq req = new CheckPasswordReq();
		req.setId("xxxxxxx33");
		req.setCellphone("18337129805");
		req.setCheckType("2");
		req.setPassword("111111113");
		req.setGesturepass("2222222");
		Result rs = capitalAccountService.checkPassword(req);
		System.out.println(rs.getCode()+rs.getError());
		System.out.println("执行结束");
	}
	
	public void saveOrUptAcountPassword() throws Exception{
		logger.info("执行开始");
		System.out.println("开始");
		SavePasswordReq req = new SavePasswordReq();
		req.setId("xxxxxxx33");
		req.setCellphone("18337129805");
		req.setCheckType("2");
//		req.setPassword("11111111");
//		req.setOldPassword("12345633");
		req.setGesturepass("2222222");
		req.setOldGesturepass("123456897");
		Result rs = capitalAccountService.saveOrUptAcountPassword(req);
		System.out.println(rs.getCode()+rs.getError());
		System.out.println("执行结束");
	}
	
}
