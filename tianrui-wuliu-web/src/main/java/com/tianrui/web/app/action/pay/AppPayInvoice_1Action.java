package com.tianrui.web.app.action.pay;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.admin.intf.IPayInvoiceService;
import com.tianrui.api.req.admin.PayInvoiceReq;
import com.tianrui.api.resp.admin.PayInvoiceVo;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/payInvoice_1")
public class AppPayInvoice_1Action {

	private Logger logger = Logger.getLogger(AppPayInvoice_1Action.class);
	
	@Autowired
	private IPayInvoiceService payInvoiceService;
	
	@RequestMapping("/page")
	@ApiParamRawType(PayInvoiceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult page(AppParam<PayInvoiceReq> appParam){
		Result result = Result.getSuccessResult();
		try {
			Head head = appParam.getHead();
			PayInvoiceReq req = appParam.getBody();
//			req.setPayeeIdentity(Constant.PAY_INVOICE_VENDER);
			req.setPayeeId(head.getId());
			PaginationVO<PayInvoiceVo> page = payInvoiceService.page(req);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return AppResult.valueOf(result);
	}
	
	/**账单详情*/
	@RequestMapping("payDetail")
	@ApiParamRawType(PayInvoiceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult payDetail(AppParam<PayInvoiceReq> appParam){
		Result rs = Result.getSuccessResult();
		PayInvoiceReq req = appParam.getBody();
		rs = payInvoiceService.payDetail(req.getId());
		return AppResult.valueOf(rs);
	}
	
	/**账单自审*/
	@RequestMapping("payAudit")
	@ApiParamRawType(PayInvoiceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult payAudit(AppParam<PayInvoiceReq> appParam){
		Result rs = Result.getSuccessResult();
		PayInvoiceReq req = appParam.getBody();
		rs = payInvoiceService.payAudit(req.getId());
		return AppResult.valueOf(rs);
	}
	
	/**账单推送后台*/
	@RequestMapping("payPush")
	@ApiParamRawType(PayInvoiceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult payPush(AppParam<PayInvoiceReq> appParam){
		Result rs = Result.getSuccessResult();
		PayInvoiceReq req = appParam.getBody();
		rs = payInvoiceService.payPush(req.getId());
		return AppResult.valueOf(rs);
	}
	
	/**账单推送收回*/
	@RequestMapping("pushBack")
	@ApiParamRawType(PayInvoiceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult pushBack(AppParam<PayInvoiceReq> appParam){
		Result rs = Result.getSuccessResult();
		PayInvoiceReq req = appParam.getBody();
		rs = payInvoiceService.pushBack(req.getId());
		return AppResult.valueOf(rs);
	}
	
	/**账单删除*/
	@RequestMapping("payDelete")
	@ApiParamRawType(PayInvoiceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult payDelete(AppParam<PayInvoiceReq> appParam){
		Result rs = Result.getSuccessResult();
		PayInvoiceReq req = appParam.getBody();
		rs = payInvoiceService.payDelete(req.getId());
		return AppResult.valueOf(rs);
	}
	
	/**账单修改银行卡*/
	@RequestMapping("updateBankCard")
	@ApiParamRawType(PayInvoiceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult updateBankCard(AppParam<PayInvoiceReq> appParam){
		Result rs = Result.getSuccessResult();
		PayInvoiceReq req = appParam.getBody();
		rs = payInvoiceService.updateBankCard(req.getId(), req.getBankCardId());
		return AppResult.valueOf(rs);
	}
	
}
	