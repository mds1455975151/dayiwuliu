package com.tianrui.web.action.pay;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tianrui.api.req.front.pay.PayInvoiceQueryReq;
import com.tianrui.api.resp.pay.PayInvoiceResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.PayInvoiceService;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/payInvoice")
public class PayInvoiceAction {

	private Logger logger = Logger.getLogger(PayInvoiceAction.class);
	
	@Autowired
	PayInvoiceService payInvoiceService; 
	
	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request){
		ModelAndView model = new ModelAndView("pay/main");
		MemberVo vo = SessionManager.getSessionMember(request);
		model.addObject("currId", vo.getId());
		return model;
	}
	
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(HttpServletRequest request,PayInvoiceQueryReq req){
		Result rs = Result.getSuccessResult();
		try {
			MemberVo vo = SessionManager.getSessionMember(request);
			req.setCurruId(vo.getId());
			PaginationVO<PayInvoiceResp> page =payInvoiceService.page(req);
			rs.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return rs;
	}
	
	
	@RequestMapping("/detail")
	public ModelAndView detail(PayInvoiceQueryReq req,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("pay/detail");
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			view.addObject("bill",payInvoiceService.queryPayInvoice(req));
		}
		return view;
	}
}
	