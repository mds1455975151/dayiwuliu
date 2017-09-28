package com.tianrui.api.intf;

import com.tianrui.api.req.report.ReportBillAllReq;
import com.tianrui.api.req.report.ReportPayAllReq;
import com.tianrui.api.req.report.ReportPlanAllReq;
import com.tianrui.api.resp.report.ReportBillAllResp;
import com.tianrui.api.resp.report.ReportPayAllResp;
import com.tianrui.api.resp.report.ReportPlanAllResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

/**
 * 计划统计报表接口
 * @author jh
 *
 */
public interface IReportAllService {

	/** 查询计划*/
	public PaginationVO<ReportPlanAllResp> selectPlan(ReportPlanAllReq req)throws Exception;
	/** 添加计划*/
	public Result savePlan()throws Exception;
	/** 查询运单*/
	public PaginationVO<ReportBillAllResp> selectBill(ReportBillAllReq req)throws Exception;
	/** 添加运单*/
	public Result saveBill()throws Exception;
	/** 查询账单*/
	public PaginationVO<ReportPayAllResp> selectPay(ReportPayAllReq req)throws Exception;
	/** 添加账单*/
	public Result savePay()throws Exception;
	
	public Result findPlanCode(String planCode)throws Exception;
	
}
