package com.tianrui.trwl.admin.web.ticket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.ISystemMemberInfoService;
import com.tianrui.api.req.front.member.AdminMenberInfoReq;
import com.tianrui.common.vo.Result;

/**
 * 
 * @author jh
 *
 */
@Controller
@RequestMapping("admin/driverTicket")
public class DriverTicketAction {

	@Autowired
	ISystemMemberInfoService systemMemberInfoService;
	
	@RequestMapping("upt")
	@ResponseBody
	public Result upt(AdminMenberInfoReq req) throws Exception{
		Result rs = Result.getSuccessResult();
		rs = systemMemberInfoService.uptDrvierAnlian(req);
		return rs;
	}
}
