package com.tianrui.web.action.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IMemberVehicleService;
import com.tianrui.api.intf.IPositionService;
import com.tianrui.common.vo.Result;
import com.tianrui.web.util.ExcilUtil;
import com.tianrui.web.util.ReadExcel;

@Controller
@RequestMapping("excel")
public class ExcelTest {

	@Autowired
	IPositionService positionService;
	@Autowired
	IMemberVehicleService memberVehicleService;
	@RequestMapping("main")
	public ModelAndView index() throws Exception{
		Result rs = memberVehicleService.delectVehicle(ReadExcel.readXls());
		Map map = new HashMap();
    	map.put("list", rs.getData());
    	
    	ExcilUtil excilUtil = new ExcilUtil(); 
	   return new ModelAndView(excilUtil, map);   
	}
}
