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
	/**车主账单*/
	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView model = new ModelAndView("pay/payInvoice/pay_page_new");
		MemberVo vo = SessionManager.getSessionMember(request);
		model.addObject("currId", vo.getId());
		return model;
	}

	//查询账单详情
	@RequestMapping("payDetail")
	public ModelAndView payDetail(String id){
		ModelAndView view = new ModelAndView();
		view.addObject("pay", payInvoiceService.payDetail(id).getData());
		view.setViewName("pay/payInvoice/pay_detail");
		return view;
	}
	
	/**司机账单*/
	@RequestMapping("mainDriver")
	public ModelAndView mainDriver(HttpServletRequest request){
		ModelAndView model = new ModelAndView("pay/driverpay/pay_page_new");
		MemberVo vo = SessionManager.getSessionMember(request);
		model.addObject("currId", vo.getId());
		return model;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(HttpServletRequest request,PayInvoiceReq req){
		Result result = Result.getSuccessResult();
		try {
			MemberVo vo = SessionManager.getSessionMember(request);
			req.setPayeeId(vo.getId());
			PaginationVO<PayInvoiceVo> page = payInvoiceService.page(req);
			result.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("payDateil_page")
	public ModelAndView payDateil_page(){
		ModelAndView view = new ModelAndView();
		
		return view;
	}
	
	/**账单自审*/
	@RequestMapping("payAudit")
	@ResponseBody
	public Result payAudit(String id){
		Result rs = Result.getSuccessResult();
		rs = payInvoiceService.payAudit(id);
		return rs;
	}
	
	/**账单推送后台*/
	@RequestMapping("payPush")
	@ResponseBody
	public Result payPush(String id){
		Result rs = Result.getSuccessResult();
		rs = payInvoiceService.payPush(id);
		return rs;
	}
	
	/**账单推送收回*/
	@RequestMapping("pushBack")
	@ResponseBody
	public Result pushBack(String id){
		Result rs = Result.getSuccessResult();
		rs = payInvoiceService.pushBack(id);
		return rs;
	}
	
	/**账单删除*/
	@RequestMapping("payDelete")
	@ResponseBody
	public Result payDelete(String id){
		Result rs = Result.getSuccessResult();
		rs = payInvoiceService.payDelete(id);
		return rs;
	}
	
	/**更换银行卡*/
	@RequestMapping("updateBankCard")
	@ResponseBody
	public Result updateBankCard(String id, String bankCardId){
		return payInvoiceService.updateBankCard(id, bankCardId);
	}
	
}
	