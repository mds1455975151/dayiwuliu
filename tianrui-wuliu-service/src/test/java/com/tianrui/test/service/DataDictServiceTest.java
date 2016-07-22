package com.tianrui.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.tianrui.api.intf.IDataDictService;
import com.tianrui.api.req.front.system.DataDictReq;
import com.tianrui.api.resp.common.DataDictResp;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DataDictServiceTest {
	public static Logger logger =LoggerFactory.getLogger(DataDictServiceTest.class);
	@Autowired
	private  IDataDictService  dataDictService;
	
	@Test
	public void findDictListTest()throws Exception{
		DataDictReq bean = new  DataDictReq();

		bean.setSubCode("subCode");
		List<DataDictResp> rs =dataDictService.findDictList(bean);
		logger.info("{}",JSON.toJSON(rs));
	}
	
}
