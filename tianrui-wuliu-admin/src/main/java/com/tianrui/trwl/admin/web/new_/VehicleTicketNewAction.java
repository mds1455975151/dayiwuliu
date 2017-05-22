package com.tianrui.trwl.admin.web.new_;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.new_.IFileVehicleRecordNewService;
import com.tianrui.api.req.new_.FileVehicleRecordNewReq;
import com.tianrui.api.resp.new_.FileVehicleRecordNewResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.admin.bean.Users;
import com.tianrui.trwl.admin.util.SessionManager;

@Controller
@RequestMapping("/admin/fileVehicle/new")
public class VehicleTicketNewAction {

	@Autowired
	IFileVehicleRecordNewService fileVehicleRecordNewService;
	
	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminVehicle/ticket_new");
		///admin/fileVehicle/new/page
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(FileVehicleRecordNewReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<FileVehicleRecordNewResp> page = fileVehicleRecordNewService.select(req);
		rs.setData(page);
		return rs;
	}
	
	@RequestMapping("vehicleTrickCheck")
	@ResponseBody
	public Result vehicleTrickCheck(FileVehicleRecordNewReq req,HttpServletRequest request) throws Exception{
		Result rs = Result.getSuccessResult();
		Users user = SessionManager.getSessionMember(request);
		req.setAuthuser(user.getAccount());
		rs = fileVehicleRecordNewService.vehicleTrickCheck(req);
		return rs;
	}
}
