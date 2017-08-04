package com.tianrui.trwl.admin.web.pay;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IPayInvoiceDetail1Service;
import com.tianrui.api.req.admin.pay.PayInvoiceDetail1FindReq;
import com.tianrui.api.req.front.bill.BillConfirmPriceReq;
import com.tianrui.api.resp.admin.pay.PayInvoiceDetail1Resp;
import com.tianrui.api.resp.pay.PayAndBillDateilResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("/pay/InviceDetail1")
public class PayInviceDetail1Action {

	@Autowired
	IPayInvoiceDetail1Service payInvoiceDetail1Service;
	
	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/file/payInvoice/pay/file_paydetail");
		return view;
	}
	/** 查看账单详情
	 * @throws Exception */
	@RequestMapping("payInviceDetail")
	public ModelAndView payInviceDetail(String id) throws Exception{
		ModelAndView view = new ModelAndView();
		
		PayAndBillDateilResp resp = payInvoiceDetail1Service.payInviuceDetail(id);
		view.setViewName("/file/payInvoice/pay/payInvoice_detail");
		view.addObject("pay", resp);
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(PayInvoiceDetail1FindReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<PayInvoiceDetail1Resp> page = payInvoiceDetail1Service.select(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("findById")
	@ResponseBody
	public Result findById(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		PayInvoiceDetail1Resp resp = payInvoiceDetail1Service.selectById(id);
		rs.setData(resp);
		return rs;
	}
	
	//账单备注
	@RequestMapping("payMemo")
	@ResponseBody
	public Result payMemo(String id,String memo) throws Exception{
		return payInvoiceDetail1Service.payMemo(id, memo);
	}
	
	//后台运价确认
	@RequestMapping("uptPrice")
	@ResponseBody
	public Result uptPrice(BillConfirmPriceReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		rs = payInvoiceDetail1Service.uptPrice(req,user.getAccount());
		return rs;
	}
	
}
