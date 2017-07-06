package com.tianrui.api.admin.intf;

import com.tianrui.api.req.admin.PayInvoiceAuditUpdate;
import com.tianrui.api.req.admin.PayInvoiceReq;
import com.tianrui.api.resp.admin.PayInvoiceVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

public interface IPayInvoiceService {

	PaginationVO<PayInvoiceVo> page(PayInvoiceReq req);
	
	Result payInvoiceUpdateData(String id, boolean isAudit);
	/**
	 * @annotation 账单修改
	 * @param auditUpdate
	 * @param isAudit
	 * @return
	 */
	Result payInvoiceUpdate(PayInvoiceAuditUpdate auditUpdate, boolean isAudit);
	/**
	 * @annotation 司机账单推送
	 * @param id
	 * @return
	 */
	Result driverPush(String id);
	/**
	 * @annotation 车主账单推送
	 * @param id
	 * @return
	 */
	Result venderPush(String id);
	/**
	 * @Description 定时查询NC接口获取账单id及对应的应付金额并回写
	 * @exception 
	 * @author zhanggaohao
	 * @version 2017年6月25日 上午9:16:22
	 */
	void callBackPayInvoicePaidAmount();
	/**前台用户账单自审*/
	Result payAudit(String id);
	/**前台用户账单推送后台*/
	Result payPush(String id);
	/**前台推送收回*/
	Result pushBack(String id);
	/**账单删除*/
	Result payDelete(String id);
	/** 账单详情*/
	Result payDetail(String id);
	/**
	 * @annotation 修改银行卡
	 * @param id
	 * @param bankCardId
	 * @return
	 */
	Result updateBankCard(String id, String bankCardId);

}
