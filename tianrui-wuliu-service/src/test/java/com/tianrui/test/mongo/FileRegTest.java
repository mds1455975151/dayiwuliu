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
import com.tianrui.service.mongo.ContactDao;
import com.tianrui.service.mongo.FileRegDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class FileRegTest {
	
	public static Logger logger =LoggerFactory.getLogger(FileRegTest.class);
	@Autowired
	private  FileRegDao fileRegDao;
	@Autowired
	private  ContactDao contacksDao;

	@Test
	public void saveTest(){
		 FileReg  fileReg =new FileReg();
		 fileReg.setFileName("fileName1");
		 fileReg.setFileType("jpg2");
		 fileReg.setTimeStamp(System.currentTimeMillis());
		 fileRegDao.save(fileReg);
	}
	
	@Test
	public void saveConTest(){
		Contacts bean =new Contacts();
		bean.setCreatime("123456");
		bean.setCreator("zhangsan");
		contacksDao.save(bean);
	}
	
	
}
