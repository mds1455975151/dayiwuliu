package com.tianrui.api.intf;

import com.tianrui.api.req.front.adminReport.AnlianBillReportReq;
import com.tianrui.api.resp.admin.AnlianBillReportResp;
import com.tianrui.common.vo.PaginationVO;

public interface IAnlianBillReportService {

	/** 后台统计功能查询
	 * @throws Exception */
	PaginationVO<AnlianBillReportResp> select(AnlianBillReportReq req) throws Exception;
	/** 计算历史数据中起运地 ，目的地位置偏差*/
	void uptDistance()throws Exception;
}
