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

import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.front.position.PositionResp;
import com.tianrui.common.constants.Constant;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.BillService;
import com.tianrui.web.smvc.AuthValidation;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/billAppoint")
public class BillAppointAction {
	
	private Logger logger = Logger.getLogger(BillAppointAction.class);
	
	@Autowired
	private BillService billService;
	
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
	public ModelAndView track(HttpServletRequest request,String id){
		ModelAndView model = new ModelAndView("bill/maps/tarck");
		model.addObject("bid", id);
		if( StringUtils.isBlank(id) ){
			model.setViewName("/error/msg");
		}
		return model;
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
	
}