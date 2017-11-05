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
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("LEDCount")
public class LEDCountAction {
	
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
		PaginationVO<LEDCountResp> page = lEDCountService.findCount(req);
		rs.setData(page);
		return rs;
	}
}
