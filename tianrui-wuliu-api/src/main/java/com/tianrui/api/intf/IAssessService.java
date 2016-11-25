package com.tianrui.api.intf;

import com.tianrui.api.req.front.bill.BillAssessReq;
import com.tianrui.api.resp.front.bill.BillAssessResp;

public interface IAssessService {
	
	public int saveAssess(BillAssessReq req) throws Exception;
	
	public BillAssessResp queryAssess(BillAssessReq req) throws Exception;
	
}