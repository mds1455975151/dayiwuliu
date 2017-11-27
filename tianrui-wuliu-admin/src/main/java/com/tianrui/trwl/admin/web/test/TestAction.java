package com.tianrui.trwl.admin.web.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.money.intf.IWithdrawRecordService;
import com.tianrui.api.req.money.FindWithdrawRecordReq;
import com.tianrui.api.resp.money.FindWithdrawRecordResp;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;

@Controller
@RequestMapping("/admin/test")
public class TestAction {

	@Autowired
	IWithdrawRecordService withdrawRecordService;
	
	@RequestMapping("view")//接口名称--跳转页面
	public ModelAndView view (){
		ModelAndView view = new ModelAndView();
		view.setViewName("/accessLog/access_log");
		return view;
	}
	
	/**
	 * 用户提现记录
	 * */
	@RequestMapping(value="/withdrawRecordSelect")
	@ResponseBody//返回数据
	public Result withdrawRecordSelect(FindWithdrawRecordReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		PaginationVO<FindWithdrawRecordResp> page = withdrawRecordService.select(req);
		rs.setData(page);
		return rs;
	}
}
