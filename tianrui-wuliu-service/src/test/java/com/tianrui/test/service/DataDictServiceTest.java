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
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.front.system.DataDictReq;
import com.tianrui.api.req.money.SaveBillMoneyReq;
import com.tianrui.api.req.money.SaveWithdrawReq;
import com.tianrui.api.req.money.UpdateBillMoneyReq;
import com.tianrui.api.req.money.updateWithdrawReq;
import com.tianrui.api.resp.common.DataDictResp;
import com.tianrui.common.vo.Result;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class DataDictServiceTest {
	public static Logger logger =LoggerFactory.getLogger(DataDictServiceTest.class);
	@Autowired
	private  IDataDictService  dataDictService;
	@Autowired
	private IPendingBillMoneyService billMoneyService;
	
	@Test
	public void saveBill()throws Exception{
		SaveBillMoneyReq req = new SaveBillMoneyReq();
		req.setCellphone("18039330360");
		req.setCreatetime(new Date().getTime());
		req.setPendingmoney(900000000L);
		req.setUsername("谭明克");
		req.setUseryhno("410482198702206011");
		req.setWaybillno("4155556666666544144");
		Result rs = billMoneyService.save(req);
		System.out.println(JSON.toJSON(rs));
		logger.info("{}",JSON.toJSON(rs));
	}
	
}
