package com.tianrui.trwl.admin.web;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.api.req.admin.ZJXLVehicleReq;
import com.tianrui.api.resp.admin.PageResp;
import com.tianrui.api.resp.admin.ZJXLVehicleResp;
import com.tianrui.common.vo.Result;


@Controller
@RequestMapping("fileCross")
public class CrossAction {
	public static Logger logger = LoggerFactory.getLogger(CrossAction.class);
	@Autowired
	ICrossVehicleService crossVehicleService;
	
	@RequestMapping("index")
	public ModelAndView index(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/adminMember/cross_vehicle");
		return view;
	}
	@RequestMapping("crossVehicle")
	@ResponseBody
	public Result crossVehicle(ZJXLVehicleReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PageResp<ZJXLVehicleResp> list = crossVehicleService.find(req);
		rs.setData(list);
		return rs;
	}
	public Result saveAdd(ZJXLVehicleReq req) throws Exception{
		Result rs = crossVehicleService.insert(req);
		return rs;
		
	}
	
	
}
