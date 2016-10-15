package com.tianrui.web.action.pay;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.front.pay.PayInvoiceDetailQueryReq;
import com.tianrui.api.req.front.pay.PayInvoiceGenalReq;
import com.tianrui.api.resp.pay.PayInvoiceDetailResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.PayInvoiceDetailService;
import com.tianrui.service.mongo.CodeGenDao;
import com.tianrui.service.mongo.impl.CodeGenDaoImpl;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/payInvoiceItem")
public class PayInvoiceDetailAction {

	private Logger logger = Logger.getLogger(PayInvoiceDetailAction.class);
	
	@Autowired
	PayInvoiceDetailService payInvoiceDetailService; 
	
	@RequestMapping("main")
	public ModelAndView main(HttpServletRequest request) throws Exception{
		ModelAndView model = new ModelAndView("pay/payInvoiceItem/yunfei_page");
		MemberVo vo = SessionManager.getSessionMember(request);
		Result rs = payInvoiceDetailService.getCargoTypeName();
		model.addObject("currId", vo.getId());
		model.addObject("paytype", rs.getData());
		return model;
	}
	
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(HttpServletRequest request,PayInvoiceDetailQueryReq req){
		Result rs = Result.getSuccessResult();
		try {
			MemberVo vo = SessionManager.getSessionMember(request);
			req.setCurruId(vo.getId());
			PaginationVO<PayInvoiceDetailResp> page =payInvoiceDetailService.page(req);
			rs.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return rs;
	}
	
	
	@RequestMapping("/detail")
	public ModelAndView detail(PayInvoiceDetailQueryReq req,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("pay/detail");
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			view.addObject("bill",payInvoiceDetailService.queryPayInvoice(req));
		}
		return view;
	}
	/** 生成发票单
	 * @throws Exception */
	@RequestMapping(value="/calculated",method = RequestMethod.POST)
	@ResponseBody
	public Result calculated (PayInvoiceGenalReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCurruId(vo.getId());
		rs = payInvoiceDetailService.generalPayInvoice(req);
		return rs;
	}
	
	/** 查询多选数据
	 * @throws Exception */
	@RequestMapping(value="/selectIds",method = RequestMethod.POST)
	@ResponseBody
	public Result selectIds(PayInvoiceDetailQueryReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCurruId(vo.getId());
		if(StringUtils.isBlank(req.getIds())){
			rs.setCode("1");
			rs.setError("请选择数据");
			return rs;
		}
		PaginationVO<PayInvoiceDetailResp> page =payInvoiceDetailService.page(req);
		rs.setData(page);
		return rs;
	}
}
	