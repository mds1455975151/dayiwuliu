package com.tianrui.web.app.action.report;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IReportService;
import com.tianrui.api.req.front.bill.AnReportReq;
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
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String dstr=req.getSigntime();
		Date date = sdf.parse(dstr); 
		AppResult app = new AppResult();
		if(req.getBillType().equals("al")){
			req.setSigntimes(date.getTime());
			PaginationVO<AnReportResp> page = reportService.selectAnReceiver(req);
			app.setCode("000000");
			app.setTotal(page.getTotalInt());
			app.setReturnData(page.getList());
		}
		if(req.getBillType().equals("dy")){
			req.setSigntimes(date.getTime());
			PaginationVO<WuReportResp> page = reportService.selectWuReceiver(req);
			app.setCode("000000");
			app.setTotal(page.getTotalInt());
			app.setReturnData(page.getList());
		}
		return app;
	}
	
	
	
	/**
	 * 查询路线名称
	 * @Title: selectRoutename 
	 * @Description: TODO
	 * @param @param appParam
	 * @param @return
	 * @param @throws Exception   
	 * @return AppResult    
	 * @throws
	 */
	@RequestMapping(value = "/selectRoutename", method = RequestMethod.POST)
	@ApiParamRawType(AnReportReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult selectRoutename(AppParam<AnReportReq> appParam) throws Exception {
		AppResult app = new AppResult();
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
 
		//判断运单类型
		if(req.getBillType().equals("al")){
			List <AnReportResp> list =reportService.findAnRoutename(req);
			app.setCode("000000");
			app.setReturnData(list);
		}
		if(req.getBillType().equals("dy")){
			List <WuReportResp> list =reportService.findWuRoutename(req);
			app.setCode("000000");
			app.setReturnData(list);
		}
		return app;
	}
	

//	/**
//	 * 
//	 * @Title: selectReceiver @Description: 收货人普通运单查询 @param @param
//	 * appParam @param @return @param @throws Exception @return
//	 * AppResult @throws
//	 */
//	@RequestMapping(value = "/selectWuReceiver", method = RequestMethod.POST)
//	@ApiParamRawType(WuReportReq.class)
//	@ApiTokenValidation
//	@ResponseBody
//	public AppResult selectWuReceiver(AppParam<WuReportReq> appParam) throws Exception {
//		AppResult app = new AppResult();
//		String id = appParam.getHead().getId();
//		WuReportReq req = appParam.getBody();
//		if (req.getType().equals("1")) {
//			// 收货人id
//			req.setReceiveMemberid(id);
//		}
//		if (req.getType().equals("2")) {
//			// 货主id
//			req.setOwnerid(id);
//		}
//		if (req.getType().equals("3")) {
//			// 车主id
//			req.setVenderid(id);
//		}
//		if (req.getType().equals("4")) {
//			// 司机id
//			req.setDriverid(id);
//		}
//		PaginationVO<WuReportResp> page = reportService.selectWuReceiver(req);
//		app.setCode("000000");
//		app.setTotal(page.getTotalInt());
//		app.setReturnData(page.getList());
//		return app;
//	}
}
