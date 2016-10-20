package com.tianrui.api.intf;

import com.tianrui.api.req.front.report.ReportReq;
import com.tianrui.api.resp.front.report.ReportResp;
import com.tianrui.common.vo.PaginationVO;
/**
 * @author zhanggaohao
 * @createtime 2016年10月18日 下午4:48:43
 * @classname 运单报表接口
 */
public interface IBillReportService {
	
	//查询运单报表
	public PaginationVO<ReportResp> queryReport(ReportReq req) throws Exception;
	//查询运单报表详情
	public ReportResp querReportDetail(ReportReq req, boolean isOwner) throws Exception;
	
}
