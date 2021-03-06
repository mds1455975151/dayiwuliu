package com.tianrui.web.action.bill;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IAnlianBillService;
import com.tianrui.api.req.front.bill.AnlianBillFindReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.resp.front.bill.AnlianBillResp;
import com.tianrui.api.resp.front.bill.BillPositionResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.front.position.PositionResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.VehicleGpsZjxl;
import com.tianrui.service.impl.BillService;
import com.tianrui.service.mongo.VehicleGpsZjxlDao;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/billAppoint")
public class BillAppointAction {
	
	private Logger logger = Logger.getLogger(BillAppointAction.class);
	
	@Autowired
	private BillService billService;
	@Autowired
	private IAnlianBillService anlianBillService;
	@Autowired
	VehicleGpsZjxlDao vehicleGpsZjxlDao;
	
	@RequestMapping("main")
	@AuthValidation(autyType=Constant.AUTHCHECK_VEHICLE_OWNER)
	public ModelAndView main(HttpServletRequest request){
		ModelAndView model = new ModelAndView("bill/appoint/main");
		MemberVo vo = SessionManager.getSessionMember(request);
		model.addObject("currId", vo.getId());
		return model;
	}
	
	@RequestMapping("page")
	@ResponseBody
	public Result page(WaybillQueryReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			MemberVo vo = SessionManager.getSessionMember(request);
			req.setCurrId(vo.getId());
			PaginationVO<WaybillResp> page = billService.queryAppointBillPage(req);
			rs.setData(page);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage(), e);
		}
		return rs;
	}
	
	@RequestMapping("track")
	public ModelAndView track(HttpServletRequest request,String id) throws Exception{
		ModelAndView model = new ModelAndView("bill/maps/tarck");
		model.addObject("bid", id);
		WaybillQueryReq billId = new WaybillQueryReq();
		billId.setId(id);
		WaybillResp resp = billService.queryWayBill(billId);
		model.addObject("vehicle", resp.getVehicleno());
		model.addObject("driver", resp.getDrivername());
		model.addObject("drivertel", resp.getDrivertel());
		if( StringUtils.isBlank(id) ){
			model.setViewName("/error/msg");
		}
		return model;
	}
	
	@RequestMapping("tarckAnlian")
	public ModelAndView mainAnlian(HttpServletRequest request,String id) throws Exception{
		ModelAndView model = new ModelAndView("bill/maps/tarckAnlian");
		model.addObject("bid", id);
		AnlianBillFindReq bill = new AnlianBillFindReq();
		bill.setId(id);
		Result rs = anlianBillService.findByid(bill);
		AnlianBillResp resp = (AnlianBillResp) rs.getData();
		model.addObject("vehicle", resp.getCph());
		model.addObject("driver", resp.getDrivername());
		model.addObject("drivertel", resp.getDrivertel());
		if( StringUtils.isBlank(id) ){
			model.setViewName("/error/msg");
		}
		return model;
	}
	
	@RequestMapping("tarckTest")
	public ModelAndView mainTest(HttpServletRequest request){
		ModelAndView model = new ModelAndView("bill/maps/tarckTest");
		return model;
	}

	@RequestMapping("zjxltrackData")
	@ResponseBody
	public Result zjxltrackData(String vehicleNo){
		Result rs = Result.getSuccessResult();
		List<VehicleGpsZjxl> list = vehicleGpsZjxlDao.getVehicleTrack(vehicleNo, System.currentTimeMillis()-3*24*60*60*1000, System.currentTimeMillis());
		rs.setData(list);
		return rs;
	}
	
	@RequestMapping("trackdata")
	@ResponseBody
	public Result trackdata(WaybillQueryReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			List<PositionResp> list=billService.getBIllTrackAll(req);
			rs.setData(list);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage(), e);
		}
		return rs;
	}
	
	@RequestMapping("zjPositiondata")
	@ResponseBody
	public Result billPositiondata(WaybillQueryReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			rs = billService.getPosition(req.getId());
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage(), e);
		}
		return rs;
	}
	
	@RequestMapping("dyPositiondata")
	@ResponseBody
	public Result dyPositiondata(WaybillQueryReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			List<BillPositionResp> list =billService.getBillPosition(req.getId());
			rs.setData(list);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage(), e);
		}
		return rs;
	}
	
	@RequestMapping("zjPositiondataAnlian")
	@ResponseBody
	public Result billPositiondataAnlian(AnlianBillFindReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			rs = anlianBillService.zjfindPosition(req);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage(), e);
		}
		return rs;
	}
	
	@RequestMapping("dyPositiondataAnlian")
	@ResponseBody
	public Result dyPositiondataAnlian(AnlianBillFindReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		try {
			rs = anlianBillService.dyfindPosition(req);
		} catch (Exception e) {
			rs.setCode("000001");
			rs.setError("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage(), e);
		}
		return rs;
	}
	
}