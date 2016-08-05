package com.tianrui.web.action.bill;

import java.util.List;

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
import com.tianrui.api.req.front.transfer.TransferReq;
import com.tianrui.api.req.front.vehicle.OwnerDriverReq;
import com.tianrui.api.resp.front.vehicle.OwnerDriverResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.BillService;
import com.tianrui.service.impl.OwnerDriverService;
import com.tianrui.service.impl.SystemMemberInfoService;
import com.tianrui.service.impl.TransferService;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

/**
  * @ClassName: BillDriverAction 
  * @Description: 司机运单控制器
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月1日 上午9:43:45 
  *
 */
@Controller
@RequestMapping("/trwuliu/billdriver")
public class BillDriverAction {
	
	public static Logger loger =LoggerFactory.getLogger(BillDriverAction.class);
	
	@Autowired
	BillService billService;
	
	@Autowired
	TransferService transferService;
	
	@Autowired
	OwnerDriverService ownerDriverService;
	
	@Autowired
	SystemMemberInfoService systemMemberInfoService;
	
	
	@RequestMapping("/main")
	@AuthValidation(autyType=Constant.AUTHCHECK_DRIVBR)
	public ModelAndView main(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/driver/bill_page");
		return view;
	}
	
	//运单详情
	@RequestMapping("/detail")
	public ModelAndView detail(WaybillQueryReq req,HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/driver/bill_detail");
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurrId(currUser.getId());
			view.addObject("bill",billService.queryWayBill(req));
		}
		return view;
	}
	
	@RequestMapping("/page")
	@ResponseBody
	public Result page(WaybillQueryReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurrId(currUser.getId());
			req.setQueryType(3);
			rs.setData(billService.page(req));
		}
		return rs;
	}

	
	//确认接受运单
	@RequestMapping("/acceptConfirm")
	@ResponseBody
	public Result acceptConfirm(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.acceptConfirm(req);
		}
		return rs;
	}
	
	//发车确认
	@RequestMapping("/departureConfirm")
	@ResponseBody
	public Result departureConfirm(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.departureConfirm(req);
		}
		return rs;
	}
	//提货确认
	@RequestMapping("/pickupConfirm")
	@ResponseBody
	public Result pickupConfirm(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.pickupConfirm(req);
		}
		return rs;
	}
	
	//司机已装车，等待运送目的地，请提前安排接收事宜。
	//司机到达目的地确认
	@RequestMapping("/arrivedConfirm")
	@ResponseBody
	public Result arrivedConfirm(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.arrivedConfirm(req);
		}
		return rs;
	}
	
	//司机已卸货完成，等待签收
	//司机卸货完成确认
	@RequestMapping("/dischargeConfirm")
	@ResponseBody
	public Result dischargeConfirm(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.dischargeConfirm(req);
		}
		return rs;
	}
	

	
	//取消运单
	@RequestMapping("/refuseConfirm")
	@ResponseBody
	public Result refuseConfirm(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.refuseConfirm(req);
		}
		return rs;
	}
	
	//删除运单
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(WaybillConfirmReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setCurruId(currUser.getId());
			rs=billService.driverdelete(req);
		}
		return rs;
	}
	
	//初始化司机交班页面
	@RequestMapping("/handView")
	@AuthValidation(autyType=Constant.AUTHCHECK_DRIVBR)
	public ModelAndView handView(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("bill/driver/bill_hand");
		MemberVo currUser =SessionManager.getSessionMember(request);
		view.addObject("list",systemMemberInfoService.handView(currUser.getId()).getData());
		view.addObject("isHand", transferService.isHand(currUser.getId()));
		return view;
	}
	//初始化司机交班页面
	@RequestMapping("/queryDriver")
	@ResponseBody
	public Result queryDriver(String memberId, HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		OwnerDriverReq driverReq = new OwnerDriverReq();
		// 用户主键
		driverReq.setVehicleownerid(memberId);
		List<OwnerDriverResp> driverRespList = ownerDriverService.queryMyDriverOutsideByCondition(driverReq);
		rs.setData(driverRespList);
		return rs;
	}
	
	//申请交班
	@RequestMapping("/applyHand")
	@ResponseBody
	public Result applyHand(TransferReq req,HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		if( req !=null ){
			MemberVo currUser =SessionManager.getSessionMember(request);
			req.setStatus("0");
			req.setStartid(currUser.getId());
			req.setStarter(currUser.getRealName());
			req.setStarttele(currUser.getCellphone());
			req.setStarttime(System.currentTimeMillis());
			req.setIsvalid("1");
			rs = transferService.save(req);
		}
		return rs;
	}
	//收回交班
	@RequestMapping("/recover")
	@ResponseBody
	public Result recover(HttpServletRequest request) throws Exception{
		Result rs =Result.getSuccessResult();
		MemberVo currUser =SessionManager.getSessionMember(request);
		rs = transferService.delete(currUser.getId());
		return rs;
	}
}
