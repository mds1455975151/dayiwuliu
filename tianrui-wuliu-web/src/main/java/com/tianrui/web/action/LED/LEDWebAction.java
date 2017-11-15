package com.tianrui.web.action.LED;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ILEDCountService;
import com.tianrui.api.req.LED.LEDCountReq;
import com.tianrui.api.resp.LED.LEDCountResp;
import com.tianrui.api.resp.LED.LEDRouteResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.bean.LEDCountData;

@Controller
@RequestMapping("LEDCount")
public class LEDWebAction {
	
	@Autowired
	ILEDCountService lEDCountService;

	@RequestMapping("index")
	public ModelAndView index(HttpServletRequest request) throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("LED/LED_index");
		return view;
	}
	
	@RequestMapping("queryData")
	@ResponseBody
	public Result queryData(LEDCountReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = lEDCountService.selectConfig();
		if(rs.getCode().equals("000000")){
			rs = lEDCountService.selectByKey("0000000_data_upt");
			LEDCountData data = (LEDCountData) rs.getData();
			req.setDataType(data.getStimestr());
			PaginationVO<LEDCountResp> page = lEDCountService.findCount(req);
			rs.setData(page);
		}
		return rs;
	}
	/** 查询路线信息*/
	@RequestMapping("queryRouteData")
	@ResponseBody
	public Result queryRouteData(LEDCountReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = lEDCountService.selectConfig();
		if(rs.getCode().equals("000000")){
			LEDRouteResp page = lEDCountService.findRoute(req);
			rs.setData(page);
		}
		return rs;
	}
}
