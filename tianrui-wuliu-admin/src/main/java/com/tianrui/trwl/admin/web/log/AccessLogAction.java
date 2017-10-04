package com.tianrui.trwl.admin.web.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.intf.IAccessLogService;
import com.tianrui.api.req.accessLog.AccessLogReq;
import com.tianrui.api.resp.accessLog.AccessLogResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("access/log")
public class AccessLogAction {

	@Autowired
	IAccessLogService accessLogService;
	
	@RequestMapping("page")
	public ModelAndView page(){
		ModelAndView view = new ModelAndView();
		view.setViewName("/accessLog/access_log");
		return view;
	}
	
	@RequestMapping("find")
	@ResponseBody
	public Result find(AccessLogReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<AccessLogResp> page = accessLogService.select(req);
		rs.setData(page);
		return rs;
	}
}
