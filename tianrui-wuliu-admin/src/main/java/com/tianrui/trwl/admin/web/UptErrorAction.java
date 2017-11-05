package com.tianrui.trwl.admin.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.tianrui.api.intf.IUptErrorBillImgService;
import com.tianrui.common.vo.Result;

/** 修改错误信息*/

@Controller
@RequestMapping("uptError")
public class UptErrorAction {

	@Autowired
	IUptErrorBillImgService uptErrorBillImgService;
	
	@RequestMapping("upt")
	public Result upt() throws Exception{
		Result rs = Result.getSuccessResult();
		uptErrorBillImgService.uptBillImg();
		return rs;
	}
}
