package com.tianrui.web.action.new_;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.new_.IMemberVehicleNewService;
import com.tianrui.api.req.new_.MemberVehicleNewReq;
import com.tianrui.api.req.new_.V_vehicle_driverReq;
import com.tianrui.api.resp.new_.V_vehicle_driverResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.SessionManager;

/***
 * new_我的运力
 * @author jh
 *
 */
@Controller
@RequestMapping("trwuliu/member_vehicle/new")
public class MemberVehicleNewAction {

	@Autowired
	IMemberVehicleNewService memberVehicleNewService;
	
	
	/** 我的运力*/
	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/new/vehicle/myCapa_new");
		return view;
	}
	/** 添加运力*/
	@RequestMapping("addpage")
	public ModelAndView pageAdd(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/new/vehicle/addCapa_new");
		return view;
	}
	
	/**查询我的运力
	 * @throws Exception */
	@RequestMapping("find")
	@ResponseBody
	public Result find(V_vehicle_driverReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<V_vehicle_driverResp> page = memberVehicleNewService.select(req);
		rs.setData(page);
		return rs;
	}
	
	/** 查找运力
	 * @throws Exception */
	@RequestMapping("selectVehicle")
	@ResponseBody
	public Result selectVehicle(String vheicleno) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = memberVehicleNewService.selectVehicle(vheicleno);
		return rs;
	}
	
	/** 添加我的运力
	 * @throws Exception */
	@RequestMapping("save")
	@ResponseBody
	public Result save(MemberVehicleNewReq req,HttpServletRequest request) throws Exception{
		Result rs = null;
		MemberVo vo =SessionManager.getSessionMember(request);
		req.setMemberid(vo.getId());
		rs = memberVehicleNewService.saveMyVehicle(req);
		return rs;
	}
	@RequestMapping("delect")
	@ResponseBody
	public Result delect(String id) throws Exception{
		Result rs = null;
	 	rs = memberVehicleNewService.delect(id);
		return rs;
	}
}
