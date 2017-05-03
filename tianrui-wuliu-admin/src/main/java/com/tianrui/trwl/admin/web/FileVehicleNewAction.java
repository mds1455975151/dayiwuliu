package com.tianrui.trwl.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.admin.intf.IFileVehicleNewService;
import com.tianrui.api.req.admin.vehicle_new.FileVehicleNewReq;
import com.tianrui.api.resp.admin.vehicle_new.FileVehicleNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("/admin/fileVehicle")
public class FileVehicleNewAction {

	@Autowired
	IFileVehicleNewService fileVehicleNewService;
	
	@RequestMapping("/main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminVehicle/vehicle_new");
		return view;
	}

	@RequestMapping("/page")
	@ResponseBody
	public Result page(FileVehicleNewReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<FileVehicleNewResp> resp = fileVehicleNewService.find(req);
		rs.setData(resp);
		return rs;
	}
	
}


