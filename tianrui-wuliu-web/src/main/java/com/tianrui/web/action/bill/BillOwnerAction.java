package com.tianrui.web.action.bill;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.front.bill.WaybillConfirmReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.BillService;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

/**
 * 
  * @ClassName: BillOwnerAction 
  * @Description: 货主订单控制器
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月1日 上午9:43:04 
  *
 */
@Controller
@RequestMapping("/trwuliu/billowner")
public class BillOwnerAction {
	
	public static Logger loger =LoggerFactory.getLogger(BillOwnerAction.class);
	
	@Autowired
	BillService billService;
	
	@RequestMapping("/main")
	@AuthValidation(autyType=Constant.AUTHCHECK_OWNER)
	public ModelAndView main(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/owner/bill_page");
		return view;
	}
	
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(WaybillQueryReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurrId(currUser.getId());
			req.setQueryType(1);
			rs.setData(billService.page(req));
		}
		return rs;
	}
	
	//运单详情
	@RequestMapping("/detail")
	public ModelAndView detail(WaybillQueryReq req,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/owner/bill_detail");
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurrId(currUser.getId());
			view.addObject("bill",billService.queryWayBill(req));
			view.addObject("pageNo",req.getPageNo());
		}
		return view;
	}
	
	//签收运单
	@RequestMapping("/signConfirm")
	@ResponseBody
	public Result signConfirm(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.signConfirm(req);
		}
		return rs;
	}
	//货主删除运单
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.ownerdelete(req);
		}
		return rs;
	}
	
}
