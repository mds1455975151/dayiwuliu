package com.tianrui.web.action.pay;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IPayInvoiceService;
import com.tianrui.api.req.admin.PayInvoiceReq;
import com.tianrui.api.resp.admin.PayInvoiceVo;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/payInvoice_1")
public class PayInvoice_1Action {

	private Logger logger = Logger.getLogger(PayInvoice_1Action.class);
	
	@Autowired
	private IPayInvoiceService payInvoiceService;
	
	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView model = new ModelAndView("pay/payInvoice/pay_page_new");
		MemberVo vo = SessionManager.getSessionMember(request);
		model.addObject("currId", vo.getId());
		return model;
	}
	
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(HttpServletRequest request,PayInvoiceReq req){
		Result result = Result.getSuccessResult();
		try {
			req.setPayeeIdentity(Constant.PAY_INVOICE_VENDER);
			PaginationVO<PayInvoiceVo> page = payInvoiceService.page(req);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
}
	