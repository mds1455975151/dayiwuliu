package com.tianrui.web.action.bill;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.ISignerBillService;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.req.front.bill.AnlianBillSignerReq;
import com.tianrui.api.req.front.bill.BillConfirmPriceReq;
import com.tianrui.api.req.front.bill.SignerBillFindReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.resp.front.bill.SignerBillResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/billSigner")
public class BillSignerAction {

	@Autowired
	ISignerBillService signerService;
	@Autowired
	IBillService billService;
	@Autowired
	IAnlianBillService anlianBillService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/signer/signer_owner_page");
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(SignerBillFindReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo =SessionManager.getSessionMember(request);
		req.setReceiveMemberid(vo.getId());
		PaginationVO<SignerBillResp> resp =signerService.select(req);
		rs.setData(resp);
		return rs;
	}
	
	//查询大易运单详情
	@RequestMapping("findDybillDetail")
	@ResponseBody
	public Result findDybillDetail(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		WaybillQueryReq req = new WaybillQueryReq();
		req.setId(id);
		WaybillResp resp = billService.queryWayBill(req);
		rs.setData(resp);
		return rs;
	}
	
	//查询安联运单详情
	@RequestMapping("findAlbillDetail")
	@ResponseBody
	public Result findAlbillDetail(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		AnlianBillFindReq req = new AnlianBillFindReq();
		req.setId(id);
		rs = anlianBillService.findByid(req);
		return rs;
	}
	
	//安联运单签收 上传磅单
	@RequestMapping("alBillsigner")
	@ResponseBody
	public Result alBillsigner(AnlianBillSignerReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = anlianBillService.billSigner(req);
		return rs;
	}
	//前台运价确认
	@RequestMapping("confirmTotalPrice")
	@ResponseBody
	public Result confirmTotalPrice(BillConfirmPriceReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setCreater(vo.getId());
		rs = signerService.BillConfirmPrice(req);
		return rs;
	}
	
}
