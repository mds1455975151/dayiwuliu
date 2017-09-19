package com.tianrui.service.admin.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IReportPlanAllService;
import com.tianrui.api.req.report.ReportBillAllReq;
import com.tianrui.api.req.report.ReportPayAllReq;
import com.tianrui.api.req.report.ReportPlanAllReq;
import com.tianrui.api.resp.report.ReportBillAllResp;
import com.tianrui.api.resp.report.ReportPayAllResp;
import com.tianrui.api.resp.report.ReportPlanAllResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.mapper.ReportBillAllMapper;
import com.tianrui.service.mapper.ReportPayAllMapper;
import com.tianrui.service.mapper.ReportPlanAllMapper;
@Service
public class ReportPlanAllService implements IReportPlanAllService{

	@Autowired
	ReportBillAllMapper reportBillAllMapper;
	@Autowired
	ReportPayAllMapper reportPayAllMapper;
	@Autowired
	ReportPlanAllMapper reportPlanAllMapper;
	
	@Override
	public PaginationVO<ReportPlanAllResp> selectPlan(ReportPlanAllReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result savePlan(ReportPlanAllReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationVO<ReportBillAllResp> selectBill(ReportBillAllReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result saveBill(ReportBillAllReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationVO<ReportPayAllResp> selectPay(ReportPayAllReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Result savePay(ReportPayAllReq req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
