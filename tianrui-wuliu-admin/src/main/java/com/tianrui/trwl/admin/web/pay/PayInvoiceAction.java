package com.tianrui.trwl.admin.web.pay;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IPayInvoiceService;
import com.tianrui.api.req.admin.PayInvoiceAuditUpdate;
import com.tianrui.api.req.admin.PayInvoiceReq;
import com.tianrui.api.resp.admin.PayInvoiceVo;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("pay/invoice")
public class PayInvoiceAction {

	private Logger logger = LoggerFactory.getLogger(PayInvoiceAction.class);
	
	@Autowired
	private IPayInvoiceService payInvoiceService;
	
	@RequestMapping("driver/main")
	public ModelAndView driverMain(){
		ModelAndView view = new ModelAndView("file/payInvoice/driver/payInvoice");
		return view;
	}

	@RequestMapping("driver/page")
	@ResponseBody
	public Result driverPage(PayInvoiceReq req){
		logger.info("into action: driver pay invoice page.");
		Result result = Result.getSuccessResult();
		try {
			req.setPayeeIdentity(Constant.PAY_INVOICE_DRIVER);
			PaginationVO<PayInvoiceVo> page = payInvoiceService.page(req);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("out action: driver pay invoice page.");
		return result;
	}
	
	@RequestMapping("driver/auditData")
	@ResponseBody
	public Result driverAuditData(String id){
		Result result = Result.getSuccessResult();
		logger.info("into action: driver pay invoice auditData.");
		try {
			result = payInvoiceService.payInvoiceUpdateData(id, true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("out action: driver pay invoice auditData.");
		return result;
	}
	
	@RequestMapping("driver/audit")
	@ResponseBody
	public Result driverAudit(PayInvoiceAuditUpdate auditUpdate){
		Result result = Result.getSuccessResult();
		logger.info("into action: driver pay invoice audit.");
		try {
			result = payInvoiceService.payInvoiceUpdate(auditUpdate, true);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("out action: driver pay invoice audit.");
		return result;
	}
	
	@RequestMapping("driver/updateData")
	@ResponseBody
	public Result driverUpdateData(String id){
		Result result = Result.getSuccessResult();
		logger.info("into action: driver pay invoice updateData.");
		try {
			result = payInvoiceService.payInvoiceUpdateData(id, false);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("out action: driver pay invoice updateData.");
		return result;
	}
	
	@RequestMapping("driver/update")
	@ResponseBody
	public Result driverUpdate(PayInvoiceAuditUpdate auditUpdate){
		Result result = Result.getSuccessResult();
		logger.info("into action: driver pay invoice update.");
		try {
			result = payInvoiceService.payInvoiceUpdate(auditUpdate, false);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("out action: driver pay invoice update.");
		return result;
	}
	
	@RequestMapping("driver/push")
	@ResponseBody
	public Result driverPush(String id){
		Result result = Result.getSuccessResult();
		logger.info("into action: driver pay invoice driver push.");
		try {
			result = payInvoiceService.driverPush(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("out action: driver pay invoice driver push.");
		return result;
	}
	
	@RequestMapping("vender/main")
	public ModelAndView venderMain(){
		ModelAndView view = new ModelAndView("file/payInvoice/vender/payInvoice");
		return view;
	}

	@RequestMapping("vender/page")
	@ResponseBody
	public Result venderPage(PayInvoiceReq req){
		logger.info("into action: vender pay invoice page.");
		Result result = Result.getSuccessResult();
		try {
			req.setPayeeIdentity(Constant.PAY_INVOICE_VENDER);
			PaginationVO<PayInvoiceVo> page = payInvoiceService.page(req);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("out action: vender pay invoice page.");
		return result;
	}
	
	@RequestMapping("vender/push")
	@ResponseBody
	public Result venderPush(String id){
		Result result = Result.getSuccessResult();
		logger.info("into action: vender pay invoice vender push.");
		try {
			result = payInvoiceService.venderPush(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("out action: vender pay invoice vender push.");
		return result;
	}
	
}
