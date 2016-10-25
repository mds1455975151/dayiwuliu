package com.tianrui.test.service;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.IDataDictService;
import com.tianrui.api.intf.IFileService;
import com.tianrui.api.req.front.system.DataDictReq;
import com.tianrui.api.resp.common.DataDictResp;
import com.tianrui.common.vo.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class FileServiceTest {
	public static Logger logger =LoggerFactory.getLogger(FileServiceTest.class);
	@Autowired
	private  IFileService fileService ;
	
	@Test
	public void saveTest()throws Exception{
		File file =new File("D:\\陆交平台接入标准接口.pdf");
		Result rs =fileService.uploadByteFile(file);
		System.out.println(JSON.toJSONString(rs));
	}
	
}
