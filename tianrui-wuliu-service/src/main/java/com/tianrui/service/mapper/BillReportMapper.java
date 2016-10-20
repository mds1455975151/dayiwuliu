package com.tianrui.service.mapper;

import java.util.List;

import com.tianrui.api.req.front.report.ReportReq;
import com.tianrui.service.bean.BillReport;
/**
 * @author zhanggaohao
 * @createtime 2016年10月18日 下午4:50:06
 * @classname 运单报表mapper
 */
public interface BillReportMapper {
	
	List<BillReport> queryBillReport(ReportReq req);
	
	long queryBillReportCount(ReportReq req);

	BillReport querReportDetail(ReportReq req);
	
}