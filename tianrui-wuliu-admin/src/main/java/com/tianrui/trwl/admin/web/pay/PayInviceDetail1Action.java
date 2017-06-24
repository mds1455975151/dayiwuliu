package com.tianrui.trwl.admin.web.pay;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IPayInvoiceDetail1Service;
import com.tianrui.api.req.admin.pay.PayInvoiceDetail1Req;
import com.tianrui.api.resp.admin.pay.PayInvoiceDetail1Resp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

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
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(PayInvoiceDetail1Req req) throws Exception{
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
	
	//后台运价确认
	@RequestMapping("uptPrice")
	@ResponseBody
	public Result uptPrice(PayInvoiceDetail1Req req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = payInvoiceDetail1Service.uptPrice(req);
		return rs;
	}
	
}
