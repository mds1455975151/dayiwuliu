package com.tianrui.service.admin.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianrui.api.intf.IReportAllService;
import com.tianrui.api.req.report.ReportBillAllReq;
import com.tianrui.api.req.report.ReportPayAllReq;
import com.tianrui.api.req.report.ReportPlanAllReq;
import com.tianrui.api.resp.report.ReportBillAllResp;
import com.tianrui.api.resp.report.ReportPayAllResp;
import com.tianrui.api.resp.report.ReportPlanAllResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.ReportBillAll;
import com.tianrui.service.bean.ReportPayAll;
import com.tianrui.service.bean.ReportPlanAll;
import com.tianrui.service.mapper.ReportBillAllMapper;
import com.tianrui.service.mapper.ReportPayAllMapper;
import com.tianrui.service.mapper.ReportPlanAllMapper;
@Service
public class ReportAllService implements IReportAllService{

	Logger logger = LoggerFactory.getLogger(ReportAllService.class);
	
	@Autowired
	ReportBillAllMapper reportBillAllMapper;
	@Autowired
	ReportPayAllMapper reportPayAllMapper;
	@Autowired
	ReportPlanAllMapper reportPlanAllMapper;
	
	@Override
	public PaginationVO<ReportPlanAllResp> selectPlan(ReportPlanAllReq req) throws Exception {
		PaginationVO<ReportPlanAllResp> resp = new PaginationVO<ReportPlanAllResp>();
		ReportPlanAll record = new ReportPlanAll();
		PropertyUtils.copyProperties(record, req);
		if(req.getPageNo()!=null){
			record.setPageNo(req.getPageNo()*req.getPageSize());
			record.setPageSize(req.getPageSize());
			resp.setPageNo(req.getPageNo());
			resp.setPageSize(req.getPageSize());
		}
		List<ReportPlanAll> list = reportPlanAllMapper.selectByCondition(record);
		long a = reportPlanAllMapper.selectByCount(record);
		resp.setTotal(a);
		List<ReportPlanAllResp> respList = new ArrayList<ReportPlanAllResp>();
		for(ReportPlanAll  plan:list){
			ReportPlanAllResp sp =new ReportPlanAllResp();
			PropertyUtils.copyProperties(sp, plan);
			respList.add(sp);
		}
		resp.setList(respList);
		return resp;
	}

	@Override
	public Result savePlan() throws Exception {
		// TODO Auto-generated method stub
		logger.info("查询计划开始");
		ReportPlanAll record = new ReportPlanAll();
		reportPlanAllMapper.selectByAllPlan(record);
		return null;
	}

	@Override
	public PaginationVO<ReportBillAllResp> selectBill(ReportBillAllReq req) throws Exception {
		// TODO Auto-generated method stub
		PaginationVO<ReportBillAllResp> resp = new PaginationVO<ReportBillAllResp>();
		ReportBillAll bill = new ReportBillAll();
		PropertyUtils.copyProperties(bill, req);
		if(req.getPageNo()!=null){
			bill.setPageNo(req.getPageNo()*req.getPageSize());
			bill.setPageSize(req.getPageSize());
			resp.setPageNo(req.getPageNo());
			resp.setPageSize(req.getPageSize());
		}
		List<ReportBillAll> list = reportBillAllMapper.selectByCondition(bill);
		long a = reportBillAllMapper.selectByCount(bill);
		resp.setTotal(a);
		List<ReportBillAllResp> resplist = new ArrayList<ReportBillAllResp>();
		for(ReportBillAll bi : list){
			ReportBillAllResp sp =new ReportBillAllResp();
			PropertyUtils.copyProperties(sp, bi);
			resplist.add(sp);
		}
		resp.setList(resplist);
		return resp;
	}

	@Override
	public Result saveBill() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PaginationVO<ReportPayAllResp> selectPay(ReportPayAllReq req) throws Exception {
		PaginationVO<ReportPayAllResp> resp = new PaginationVO<ReportPayAllResp>();
		ReportPayAll pay = new ReportPayAll();
		PropertyUtils.copyProperties(pay, req);
		if(req.getPageNo()!=null){
			pay.setPageNo(req.getPageNo()*req.getPageSize());
			pay.setPageSize(req.getPageSize());
			resp.setPageNo(req.getPageNo());
			resp.setPageSize(req.getPageSize());
		}
		List<ReportPayAll> list = reportPayAllMapper.selectByCondition(pay);
		long a = reportPayAllMapper.selectByCount(pay);
		resp.setTotal(a);
		List<ReportPayAllResp> respList = new ArrayList<ReportPayAllResp>();
		for(ReportPayAll all : list){
			ReportPayAllResp sp = new ReportPayAllResp();
			PropertyUtils.copyProperties(sp, all);
			respList.add(sp);
		}
		resp.setList(respList);
		return resp;
	}

	@Override
	public Result savePay() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
