package com.tianrui.trwl.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianrui.api.intf.ICrossVehicleService;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("autherMain")
public class AutherMainAction {

	@Autowired
	ICrossVehicleService crossVehicleService;
	
	//@RequestMapping("main")
	public Result main(){
		Result rs = Result.getSuccessResult();
		rs = crossVehicleService.allVehicleconf();
		return rs;
	}
}
