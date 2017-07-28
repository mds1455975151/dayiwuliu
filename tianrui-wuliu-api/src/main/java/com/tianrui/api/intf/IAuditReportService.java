package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.count.AuditReportReq;
import com.tianrui.api.req.front.adminReport.StatReportReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.count.AuditReportResp;
import com.tianrui.api.resp.front.adminReport.StatReportOfPlanResp;
import com.tianrui.common.vo.Result;

public interface IAuditReportService {
	PageResp<AuditReportResp> find(AuditReportReq req)throws Exception;
	List<AuditReportResp> findWithType(AuditReportReq req)throws Exception;
	
	AuditReportResp findId(String id)throws Exception;
	
	boolean save(AuditReportReq req)throws Exception;
	
	boolean update(AuditReportReq req)throws Exception;
	public Result updateWithId(String id,String val)throws Exception;
	/**
	 * 定时任务方法
	 * @Title: timingTask 
	 * @Description: TODO
	 * @param @throws Exception   
	 * @return void    
	 * @throws
	 */
	void timingTask()throws Exception;
	boolean delete(String id)throws Exception;
	//后台审核导出计划数据
	List<AuditReportResp> queryAuditReport(AuditReportReq req);
	//后台审核导出计划数据条数
	int queryAuditReportCount(AuditReportReq req);
}
