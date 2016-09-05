package com.tianrui.web.action.Capa;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IMemberCapaService;
import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.api.resp.front.capa.MemberCapaListResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.SessionManager;

@Controller
@RequestMapping("/trwuliu/Member/capa")
public class MemberCapaAction {

	@Autowired
	IMemberCapaService memberCapaService;
	@RequestMapping("capa")
	public ModelAndView capa(){
		ModelAndView view = new ModelAndView();
		view.setViewName("member/vehicle/addCapa");
		return view;
	}
	
	@RequestMapping("indexPage")
	public ModelAndView indexPage(){
		ModelAndView view = new ModelAndView();
		view.setViewName("member/vehicle/myCapa");
		return view;
	}
	
	/** 我的运力查询*/
	@RequestMapping("index")
	@ResponseBody
	public Result index(CapaReq req ,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setMemberid(vo.getId());
		PaginationVO<MemberCapaListResp> vg = memberCapaService.index(req);
		rs.setData(vg);
		return rs;
	}
	
	/** 车主运力查询*/
	@RequestMapping("venderCapa")
	@ResponseBody
	public Result venderCapa(CapaReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		//查询类型，车主运力查询时，后台筛选未绑定车辆
		req.setType("capa");
		PaginationVO<MemberCapaListResp> vg = memberCapaService.index(req);
		rs.setData(vg);
		return rs;
	}
	/** 查询车辆*/
	@RequestMapping("selectVehicle")
	@ResponseBody
	public Result selectVehicle(CapaReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = memberCapaService.selectVehicle(req);
		return rs;
	}
	/** 添加车辆*/
	@RequestMapping("save")
	@ResponseBody
	public Result save(CapaReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setMemberid(vo.getId());
		req.setCellphone(vo.getCellphone());
		rs = memberCapaService.save(req);
		return rs;
	}
}
