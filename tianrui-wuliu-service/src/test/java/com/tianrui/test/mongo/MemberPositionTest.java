package com.tianrui.test.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.mongo.MemberPositionRecordDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class MemberPositionTest {
	
	public static Logger logger =LoggerFactory.getLogger(MemberPositionTest.class);
	@Autowired
	private  MemberPositionRecordDao memberPositionRecordDao;

	@Test
	public void saveTest(){

		// System.out.println(codeGenTest.codeGen(2));
	}
	
	
	
	
}
