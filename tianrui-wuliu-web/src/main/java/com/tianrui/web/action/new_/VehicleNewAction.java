package com.tianrui.web.action.new_;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFileVehicleNewService;
import com.tianrui.api.intf.IDataService;
import com.tianrui.api.intf.new_.IFileVehicleRecordNewService;
import com.tianrui.api.intf.new_.IMemberVehicleNewService;
import com.tianrui.api.intf.new_.IVehicleReg4VehicleService;
import com.tianrui.api.req.data.WebDictReq;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverQueryReq;
import com.tianrui.api.req.front.vehicle.VechicleRegDriverSaveReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleAuthReq;
import com.tianrui.api.req.front.vehicle.VechicleRegVehicleTicketAuthReq;
import com.tianrui.api.resp.front.vehicle.VehicleRegVehicleDetailResp;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.web.util.SessionManager;

/***
 * 我是车辆
 * @author jh
 *
 */
@Controller
@RequestMapping("trwuliu/vehicle/new")
public class VehicleNewAction {
	
	@Autowired
	IVehicleReg4VehicleService  vehicleReg4VehicleService;

	@Autowired
	IMemberVehicleNewService memberVehicleNewService;
	@Autowired
	IFileVehicleRecordNewService fileVehicleRecordNewService;
	
	@Autowired
	IFileVehicleNewService fileVehicleNewService;
	
	@Autowired
	IDataService dataService;
	
	/**车辆完全认证
	 * @throws Exception */
	@RequestMapping("vheicle_w")
	public ModelAndView vheicle_w(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
		Result rs = fileVehicleNewService.selectByid(vo.getId());
		WebDictReq req = new WebDictReq();
		req.setType("vehicle");
		view.addObject("vt", dataService.find(req));
		view.addObject("vehicle", rs.getData());
		view.setViewName("/new/vehicle/vehicleAuth");
		return view;
	}
	/** 添加车辆完全认证信息*/
	@RequestMapping("vehicle_w_save")
	@ResponseBody
	public Result vehicle_w_save(VechicleRegVehicleAuthReq req,HttpServletRequest request){
		Result rs = Result.getSuccessResult();
		MemberVo vo = SessionManager.getSessionMember(request);
		req.setId(vo.getId());
		vehicleReg4VehicleService.vehicleAuth(req);
		return rs;
	}
	
	/** 我的驾驶员*/
	@RequestMapping("driverpage")
	public ModelAndView page(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		//驾驶员列表
		MemberVo vo =SessionManager.getSessionMember(request);
		VechicleRegDriverQueryReq req =new VechicleRegDriverQueryReq();
		req.setCurrVId(vo.getId());
		Result rs =vehicleReg4VehicleService.driverPage(req);
		view.addObject("driverPage", rs.getData());
		
		view.setViewName("/new/vehicle/driverpage");
		return view;
	}
	
	
	/** TODO 添加驾驶员page
	 * @throws Exception */
	@RequestMapping("driverAddView")
	public ModelAndView driveraddView(HttpServletRequest request,String vehicleId) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo = SessionManager.getSessionMember(request);
		view.addObject("vehicleId", vo.getId());
		view.setViewName("/new/vehicle/driverAddView");
		return view;
	}
	/** 添加驾驶员*/
	@RequestMapping("driverAdd")
	@ResponseBody
	public Result driverAdd(VechicleRegDriverSaveReq req){
		Result rs = Result.getSuccessResult();
		rs = vehicleReg4VehicleService.driverSave(req);
		return rs;
	}
	
	//选中驾驶员
	@RequestMapping("driverCheck")
	@ResponseBody
	public Result driverCheck(HttpServletRequest request,VechicleRegDriverQueryReq req) throws Exception{
		Result rs =Result.getErrorResult();
		MemberVo vo =SessionManager.getSessionMember(request);
		if( req !=null &&  vo!=null && StringUtils.isNotBlank(req.getId())){
			req.setCurrVId(vo.getId());
			rs =vehicleReg4VehicleService.driverCheck(req);
		}
		return rs;
	}
	//删除驾驶员
	@RequestMapping("driverDel")
	@ResponseBody
	public Result driverDel(HttpServletRequest request,VechicleRegDriverQueryReq req) throws Exception{
		Result rs =Result.getErrorResult();
		MemberVo vo =SessionManager.getSessionMember(request);
		if( req !=null &&  vo!=null && StringUtils.isNotBlank(req.getId())){
			req.setCurrVId(vo.getId());
			rs =vehicleReg4VehicleService.driverDel(req);
		}
		return rs;
	}
	/** 我的车辆*/
	@RequestMapping("vehicledetail")
	public ModelAndView pageAdd(HttpServletRequest request){
		ModelAndView view = new ModelAndView();
		//车辆信息
		MemberVo vo =SessionManager.getSessionMember(request);
		VechicleRegDriverQueryReq req =new VechicleRegDriverQueryReq();
		req.setCurrVId(vo.getId());
		Result rs =vehicleReg4VehicleService.vehicleDetil(req);
		if( rs.getData() !=null){
			view.addObject("vehicle", rs.getData());
		}else{
			view.addObject("vehicle", new VehicleRegVehicleDetailResp());
		}

		view.setViewName("/new/vehicle/vehicledetail");
		return view;
	}
	
	/** TODO 开票认证页面
	 * @throws Exception */
	@RequestMapping("ticketAuthView")
	public ModelAndView ticketAuthView(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		MemberVo vo =SessionManager.getSessionMember(request);
		view.addObject("id", vo.getId());
		view.setViewName("/new/vehicle/vehicleTicketAuth");
		return view;
	}
	
	/** TODO 开票认证动作
	 * @throws Exception */
	@RequestMapping("ticketAtuh")
	@ResponseBody
	public Result ticketAuth(HttpServletRequest request,VechicleRegVehicleTicketAuthReq req) throws Exception{
		Result rs =Result.getErrorResult();
		MemberVo vo =SessionManager.getSessionMember(request);
		if( req !=null &&  vo!=null && StringUtils.isNotBlank(req.getMotor()) && StringUtils.isNotBlank(req.getQuality())){
			req.setCurrVId(vo.getId());
			rs =vehicleReg4VehicleService.vehicleAuthTicket(req);
		}
		return rs;
	}
}
