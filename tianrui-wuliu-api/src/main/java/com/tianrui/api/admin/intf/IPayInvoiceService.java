package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.PayInvoiceAuditUpdate;
import com.tianrui.api.req.admin.PayInvoiceReq;
import com.tianrui.api.resp.admin.PayInvoiceVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IPayInvoiceService {

	PaginationVO<PayInvoiceVo> page(PayInvoiceReq req);
	
	Result payInvoiceUpdateData(String id, boolean isAudit);

	Result payInvoiceUpdate(PayInvoiceAuditUpdate auditUpdate, boolean isAudit);

	Result driverPush(String id);

	Result venderPush(String id);

}
