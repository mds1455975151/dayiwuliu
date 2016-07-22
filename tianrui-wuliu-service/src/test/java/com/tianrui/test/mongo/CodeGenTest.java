package com.tianrui.test.mongo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mysql.fabric.xmlrpc.base.Data;
import com.mysql.jdbc.ConnectionPropertiesTransform;
import com.tianrui.service.bean.Contacts;
import com.tianrui.service.bean.FileReg;
import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.mongo.ContactDao;
import com.tianrui.service.mongo.FileRegDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class CodeGenTest {
	
	public static Logger logger =LoggerFactory.getLogger(CodeGenTest.class);
	@Autowired
	private  CodeGenDao codeGenTest;

	@Test
	public void saveTest(){

		 System.out.println(codeGenTest.codeGen(2));
	}
	
	
	
	
}
