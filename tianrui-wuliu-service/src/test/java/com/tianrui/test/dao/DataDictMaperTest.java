package com.tianrui.test.dao;

import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSON;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.service.admin.mapper.RoleMenuMapper;
import com.tianrui.service.bean.DataDict;
import com.tianrui.service.mapper.DataDictMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
//@Ignore
public class DataDictMaperTest {
	
	public static Logger logger =LoggerFactory.getLogger(DataDictMaperTest.class);
	@Autowired
	private  DataDictMapper  dataDictMapper;
	@Autowired
	private RoleMenuMapper roleMenuMapper;
	
	@Test
	public void saveTest(){
		DataDict bean = new  DataDict();
		bean.setId(UUIDUtil.getId());
		bean.setItemcode("itemcode");
		bean.setItemname("itemname");
		bean.setSubcode("subcode");
		bean.setSubname("subname");
		bean.setTimestamp(new Date());
		dataDictMapper.insert(bean);
	}
	@Test
	public void findTest(){
		DataDict bean = new  DataDict();
		bean.setItemcode("itemcode");
		List<DataDict> rs=dataDictMapper.selectByCondition(bean);
		logger.info(JSON.toJSONString(rs));
	}
	@Test
	public void delTest(){
		//roleMenuMapper.deleteByPrimaryKey(3);
		roleMenuMapper.deleteWithrId(3);
	}
	
	
	
}
