package com.tianrui.trwl.admin.web.new_;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFileVehicleNewService;
import com.tianrui.api.req.admin.vehicle_new.FileVehicleNewReq;
import com.tianrui.api.req.admin.vehicle_new.VehicleMGoReq;
import com.tianrui.api.resp.admin.vehicle_new.FileVehicleNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.VehicleReg;
import com.tianrui.service.mongo.VehicleRegDao;

@Controller
@RequestMapping("/admin/fileVehicle")
public class FileVehicleNewAction {

	@Autowired
	IFileVehicleNewService fileVehicleNewService;
	@Autowired
	VehicleRegDao vehicleRegDao;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminVehicle/vehicle_new");
		return view;
	}
	@RequestMapping("/mgmain")
	public ModelAndView mgmain(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminVehicle/vehicle_new_mg");
		return view;
	}
	/** mysql 司机信息查询*/
	@RequestMapping("/page")
	@ResponseBody
	public Result page(FileVehicleNewReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<FileVehicleNewResp> resp = fileVehicleNewService.find(req);
		rs.setData(resp);
		return rs;
	}
	/** mongodb 司机信息查询*/
	@RequestMapping("/find")
	@ResponseBody
	public Result find(VehicleMGoReq req){
		Result rs = Result.getSuccessResult();
		PaginationVO<VehicleReg> page = vehicleRegDao.findVehicleReq(req);
		rs.setData(page);
		return rs;
	}
	/** mongodb 司机信息详情*/
	@RequestMapping("/findId")
	@ResponseBody
	public Result findId(String id){
		Result rs = Result.getSuccessResult();
		VehicleReg resp = vehicleRegDao.findVehicleById(id);
		rs.setData(resp);
		return rs;
	}
	
	/** 创建司机账户和车辆--后台审核
	 * @throws Exception */
	@RequestMapping("saveDriverAndVehicle")
	@ResponseBody
	public Result saveDriverAndVehicle(String id) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = fileVehicleNewService.saveVehicleAndDriver(id);
		return rs;
	}
	
}


