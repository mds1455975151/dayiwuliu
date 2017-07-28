package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.api.req.count.AuditReportReq;
import com.tianrui.api.req.front.adminReport.StatReportReq;
import com.tianrui.api.resp.count.AuditReportResp;
import com.tianrui.api.resp.front.adminReport.StatReportOfPlanResp;
import com.tianrui.service.bean.AuditReport;
import com.tianrui.service.bean.MemberBankCard;
import com.tianrui.service.bean.MemberFind;
import com.tianrui.service.bean.MemberVehicle;

public interface AuditReportMapper {
	 
		int deleteByPrimaryKey(String id);

	    int insert(AuditReport record);

	    int insertSelective(AuditReport record);

	    AuditReport selectByPrimaryKey(String id);

	    int updateByPrimaryKeySelective(AuditReport record);

	    int updateByPrimaryKey(AuditReport record);
	    
	    List<AuditReport> selectByCondition(AuditReport record);
	    /**
	     * @Title: findsAuditReportListCount 
	     * @Description: TODO查询审核总条数
	     * @param @param record
	     * @param @return   
	     * @return long    
	     * @throws
	     */
	    long findsAuditReportListCount(AuditReport record);
	    void delete(String type);
	   
	    int queryAuditReportCount(AuditReportReq req);
		
		List<AuditReportResp> queryAuditReport(AuditReportReq req);
}
