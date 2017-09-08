package com.tianrui.api.intf;

import com.tianrui.api.req.front.bill.AnReportReq;
import com.tianrui.api.req.front.bill.WuReportReq;
import com.tianrui.api.resp.admin.AnReportResp;
import com.tianrui.api.resp.admin.WuReportResp;
import com.tianrui.common.vo.PaginationVO;

public interface IReportService {
	//开票运单
	public PaginationVO<AnReportResp> selectAnReceiver(AnReportReq req)throws Exception;
	//普通运单
	public PaginationVO<WuReportResp> selectWuReceiver(WuReportReq req)throws Exception;
}
