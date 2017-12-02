package com.tianrui.test.service;

import java.util.Date;
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
import com.tianrui.api.message.intf.IMessagePushService;
import com.tianrui.api.message.intf.IMessageRollingService;
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.front.system.DataDictReq;
import com.tianrui.api.req.money.AppMessageReq;
import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.api.req.money.SaveWithdrawReq;
import com.tianrui.api.req.money.UpdateBillMoneyReq;
import com.tianrui.api.req.money.updateWithdrawReq;
import com.tianrui.api.resp.common.DataDictResp;
import com.tianrui.api.resp.front.message.MessageAppResp;
import com.tianrui.api.resp.money.MessageRollingResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.mapper.MessageRollingMapper;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DataDictServiceTest {
	public static Logger logger =LoggerFactory.getLogger(DataDictServiceTest.class);
	@Autowired
	private  IDataDictService  dataDictService;
	@Autowired
	private IMessagePushService billMoneyService;
	@Autowired 
	private IMessageRollingService messageRollingMapper;
	@Test
	public void saveBill()throws Exception{
		AppMessageReq req = new AppMessageReq();
		req.setPageNo(0);
		req.setPageSize(3);
		List<MessageRollingResp>  ls = messageRollingMapper.findRollingMessage(1);
		Result rs = Result.getSuccessResult();
		rs.setData(ls);
		System.out.println(JSON.toJSON(rs));
		logger.info("{}",JSON.toJSON(rs));
	}
	
}
