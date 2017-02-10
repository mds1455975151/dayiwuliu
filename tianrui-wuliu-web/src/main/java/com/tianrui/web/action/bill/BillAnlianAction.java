package com.tianrui.web.action.bill;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IAnlianService;
import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.intf.ISystemMemberService;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.resp.front.bill.AnlianBillResp;
import com.tianrui.api.resp.front.member.MemberResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.SystemMember;
import com.tianrui.service.bean.anlian.AnlianBill;
import com.tianrui.service.impl.SystemMemberService;
import com.tianrui.web.util.SessionManager;

/***
 * 安联运单
 * @author jh
 *
 */
@Controller
@RequestMapping("/trwuliu/billAnlian")
public class BillAnlianAction {
	@Autowired
	IAnlianBillService anlianBillService;
	@Autowired
	IAnlianService anlianService;
	@Autowired
	ISystemMemberService systemMemberService;
	
	@RequestMapping("driver")
	public ModelAndView driver(){
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/anlian/driver_page");
		return view;
	}
	@RequestMapping("vender")
	public ModelAndView vender(){
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/anlian/vender_page");
		return view;
	}
	@RequestMapping("owner")
	public ModelAndView owner(){
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/anlian/owner_page");
		return view;
	}
	
	@RequestMapping("detail")
	public ModelAndView detail(AnlianBillFindReq req) throws Exception{
		ModelAndView view = new ModelAndView();
		Result rs = anlianBillService.findByid(req);
		AnlianBillResp bill = (AnlianBillResp) rs.getData();
		rs = anlianService.detail(bill.getBillno());
		
		MemberResp resp = systemMemberService.findById(bill.getDriverid());
		bill.setDrivertel(resp.getCellPhone());
		if(!rs.getCode().equals("000000")){
			bill.setStatus(rs.getError());
		}
		view.addObject("bill", bill);
		view.setViewName("bill/anlian/bill_detail");
		return view;
	}
	
	/**查询列表
	 * @throws Exception */
	@RequestMapping("page")
	@ResponseBody
	public Result page(AnlianBillFindReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		if("driver".equals(req.getType())){
			req.setDriverid(vo.getId());
		}else if("vender".equals(req.getType())){
			req.setVenderid(vo.getId());
		}else if("owner".equals(req.getType())){
			req.setOwnerid(vo.getId());
		}else{
			rs.setCode("1");
			rs.setError("请选择身份");
		}
		PaginationVO<AnlianBillResp> page = anlianBillService.find(req);
		rs.setData(page);
		return rs;
	}
	/** 查询运单轨迹
	 * @throws Exception */
	@RequestMapping("position")
	@ResponseBody
	public Result position(AnlianBillFindReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = anlianBillService.findPosition(req);
		return rs;
	}
}
