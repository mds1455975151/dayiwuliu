package com.tianrui.trwl.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.ILEDCountService;
import com.tianrui.api.req.LED.LEDCountReq;
import com.tianrui.api.resp.LED.LEDCountResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("/admin/LED")
public class LEDCountAction {

	@Autowired
	ILEDCountService lEDCountService;
	
	@RequestMapping("index")
	public ModelAndView index() throws Exception{
		ModelAndView view = new ModelAndView();
		view.setViewName("/LED/index");
		Result rs = lEDCountService.selectByKey("0000000_data_upt");
		view.addObject("data",rs.getData());
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(LEDCountReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<LEDCountResp> page = lEDCountService.findCount(req);
		rs.setData(page);
		return rs;
	}
	@RequestMapping("setConfig")
	@ResponseBody
	public Result setConfig() throws Exception{
		Result rs = Result.getSuccessResult();
		rs = lEDCountService.setConfig();
		return rs;
	}
	
	@RequestMapping("update")
	@ResponseBody
	public Result update(LEDCountReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = lEDCountService.uptData(req);
		return rs;
	}
	
	
	
}
