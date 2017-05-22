package com.tianrui.trwl.admin.web.new_;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IVehicleDriverNewService;
import com.tianrui.api.req.admin.vehicle_new.DriverNewAuthReq;
import com.tianrui.api.req.admin.vehicle_new.VehicleDriverNewReq;
import com.tianrui.api.resp.admin.vehicle_new.VehicleDriverNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

/** 后台司机认证_new*/
@Controller
@RequestMapping("admin/vehicleDriver/new")
public class VehicleDriverNewAction {
	@Autowired
	IVehicleDriverNewService vehicleDriverNewService;
	
	/** 司机管理页面*/
	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminVehicle/vehicle_driver_new");
		return view;
	}
	
	/**查询司机信息*/
	@RequestMapping("find")
	@ResponseBody
	public Result find(VehicleDriverNewReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<VehicleDriverNewResp> page = vehicleDriverNewService.find(req);
		rs.setData(page);
		return rs;
	}
	/** 查询详情
	 * @throws Exception */
	@RequestMapping("selectBykey")
	@ResponseBody
	public Result selectBykey(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		VehicleDriverNewResp resp = vehicleDriverNewService.selectBykey(id);
		rs.setData(resp);
		return rs;
	}
	/** 司机审核
	 * @throws Exception */
	@RequestMapping("authCheckType")
	@ResponseBody
	public Result authCheckType(DriverNewAuthReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setAuthuser(user.getAccount());
		rs = vehicleDriverNewService.authCheckType(req);
		return rs;
	}
}
