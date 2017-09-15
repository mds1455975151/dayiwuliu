package com.tianrui.api.intf;

import java.util.List;

import com.tianrui.api.req.front.bill.AnReportReq;
import com.tianrui.api.resp.admin.AnReportResp;
import com.tianrui.api.resp.admin.WuReportResp;
import com.tianrui.common.vo.PaginationVO;

public interface IReportService {
	//开票运单
	public PaginationVO<AnReportResp> selectAnReceiver(AnReportReq req)throws Exception;
	//普通运单
	public PaginationVO<WuReportResp> selectWuReceiver(AnReportReq req)throws Exception;
	
	//安联路线名称查询
	List <AnReportResp> findAnRoutename(AnReportReq req)throws Exception;
	//大易路线名称查询
	List <WuReportResp> findWuRoutename(AnReportReq req)throws Exception;
}
