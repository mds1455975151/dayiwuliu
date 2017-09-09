package com.tianrui.web.app.action.report;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IReportService;
import com.tianrui.api.req.front.bill.AnReportReq;
import com.tianrui.api.req.front.bill.WuReportReq;
import com.tianrui.api.resp.admin.AnReportResp;
import com.tianrui.api.resp.admin.WuReportResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/report")
public class AppReportAction {

	public Logger logger = LoggerFactory.getLogger(AppReportAction.class);
	@Autowired
	IReportService reportService;

	/**
	 * 
	 * @Title: selectAnReceiver @Description: 收货人开票运单报表查询 @param @param
	 * appParam @param @return @param @throws Exception @return
	 * AppResult @throws
	 */
	@RequestMapping(value = "/selectAnReceiver", method = RequestMethod.POST)
	@ApiParamRawType(AnReportReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult selectAnReceiver(AppParam<AnReportReq> appParam) throws Exception {
		String id = appParam.getHead().getId();
		AnReportReq req = appParam.getBody();
		if (req.getType().equals("1")) {
			// 收货人id
			req.setReceiveMemberid(id);
		}
		if (req.getType().equals("2")) {
			// 货主id
			req.setOwnerid(id);
		}
		if (req.getType().equals("3")) {
			// 车主id
			req.setVenderid(id);
		}
		if (req.getType().equals("4")) {
			// 司机id
			req.setDriverid(id);
		}
		PaginationVO<AnReportResp> page = reportService.selectAnReceiver(req);
		AppResult app = new AppResult();
		app.setCode("000000");
		app.setTotal(page.getTotalInt());
		app.setReturnData(page.getList());
		return app;
	}

	/**
	 * 
	 * @Title: selectReceiver @Description: 收货人普通运单查询 @param @param
	 * appParam @param @return @param @throws Exception @return
	 * AppResult @throws
	 */
	@RequestMapping(value = "/selectWuReceiver", method = RequestMethod.POST)
	@ApiParamRawType(WuReportReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult selectWuReceiver(AppParam<WuReportReq> appParam) throws Exception {
		AppResult app = new AppResult();
		String id = appParam.getHead().getId();
		WuReportReq req = appParam.getBody();
		if (req.getType().equals("1")) {
			// 收货人id
			req.setReceiveMemberid(id);
		}
		if (req.getType().equals("2")) {
			// 货主id
			req.setOwnerid(id);
		}
		if (req.getType().equals("3")) {
			// 车主id
			req.setVenderid(id);
		}
		if (req.getType().equals("4")) {
			// 司机id
			req.setDriverid(id);
		}
		PaginationVO<WuReportResp> page = reportService.selectWuReceiver(req);
		app.setCode("000000");
		app.setTotal(page.getTotalInt());
		app.setReturnData(page.getList());
		return app;
	}
}
