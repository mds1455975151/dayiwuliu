package com.tianrui.web.action.report;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.tianrui.api.req.front.report.ReportReq;
import com.tianrui.api.resp.front.report.ReportResp;
import com.tianrui.common.enums.BillStatusEnum;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.BillReportService;
import com.tianrui.web.util.SessionManager;
/**
 * @author zhanggaohao
 * @createtime 2016年10月19日 上午10:19:19
 * @classname 司机报表
 */
@Controller
@RequestMapping("trwuliu/venderreport")
public class VenderReportAction {
	
	private static Logger logger = LoggerFactory.getLogger(VenderReportAction.class); 
	
	@Autowired
	private BillReportService billReportService;
	
	@RequestMapping("main")
	public ModelAndView main(){
		ModelAndView view = new ModelAndView("report/vender/main");
		return view;
	}
	
	@RequestMapping("queryReport")
	@ResponseBody
	public Result queryReport(ReportReq req, HttpServletRequest request){
		Result result = Result.getSuccessResult();
		try {
			MemberVo vo = SessionManager.getSessionMember(request);
			req.setVenderid(vo.getId());
			if(StringUtils.isNotBlank(req.getBillStatus())){
				if(Integer.parseInt(req.getBillStatus()) > BillStatusEnum.COMPLETE.getStatus()){
					String payStatus = "";
					switch (req.getBillStatus()) {
					case "7":
						payStatus = "2";
						break;
					case "8":
						payStatus = "3";
						break;
					default:
						break;
					}
					req.setPayStatus(payStatus);
					req.setBillStatus("");
				}
			}
			PaginationVO<ReportResp> page = billReportService.queryReport(req);
			result.setData(page);
		} catch (Exception e) {
			result.setCode("000001");
			result.setError("系统异常!");
			logger.error(e.getMessage(), e);
		}
		return result;
	}
	
	@RequestMapping("detail")
	public ModelAndView detail(ReportReq req){
		ModelAndView view = new ModelAndView("report/vender/detail");
		try {
			ReportResp resp = billReportService.querReportDetail(req, false);
			view.addObject("v_bill", resp);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return view;
	}
}
