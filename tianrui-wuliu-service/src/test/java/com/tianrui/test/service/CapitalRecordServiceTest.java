package com.tianrui.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.tianrui.api.money.intf.ICapitalRecordService;
import com.tianrui.api.money.intf.IPendingBillMoneyService;
import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.money.FindCapitalRecordReq;
import com.tianrui.api.req.money.FindPendingBillMoneyReq;
import com.tianrui.api.req.money.FindWithdrawRecordReq;
import com.tianrui.api.resp.money.FindCapitalRecordResp;
import com.tianrui.api.resp.money.FindPendingBillMoneyResp;
import com.tianrui.api.resp.money.FindWithdrawRecordResp;
import com.tianrui.common.vo.PaginationVO;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath*:spring/appliactionContext-service.xml" })
public class CapitalRecordServiceTest {
	
	@Autowired
	ICapitalRecordService capitalRecordService;
	@Autowired
	IPendingBillMoneyService pendingBillMoneyService;
	@Autowired
	IWithdrawRecordService withdrawRecordService;
	
	@Test
	public void withdrawRecordService() throws Exception{
		System.out.println("开始");
		FindWithdrawRecordReq req = new FindWithdrawRecordReq();
		req.setPageNo(1);
		req.setPageSize(10);
		PaginationVO<FindWithdrawRecordResp> page = withdrawRecordService.select(req);
		System.out.println("执行结束");
	}
	
	public void pendingBillMoneyService() throws Exception{
		System.out.println("开始");
		FindPendingBillMoneyReq req = new FindPendingBillMoneyReq();
		req.setPageNo(0);
		req.setPageSize(10);
		PaginationVO<FindPendingBillMoneyResp> page = pendingBillMoneyService.select(req);
		System.out.println("执行结束");
	}
	
	public void capitalRecordService() throws Exception{
		System.out.println("开始");
		FindCapitalRecordReq req = new FindCapitalRecordReq();
		req.setPageNo(0);
		req.setPageSize(10);
		PaginationVO<FindCapitalRecordResp> page = capitalRecordService.select(req);
		System.out.println("执行结束");
	}
}
