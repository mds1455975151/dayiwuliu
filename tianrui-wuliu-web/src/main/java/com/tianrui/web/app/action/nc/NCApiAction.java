package com.tianrui.web.app.action.nc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.admin.intf.IPayInvoiceService;
import com.tianrui.api.req.admin.PayInvoiceNcCheckParams;
import com.tianrui.common.vo.ApiResult;

@Controller
@RequestMapping("/nc/api")
public class NCApiAction {
	
	private Logger logger = LoggerFactory.getLogger(NCApiAction.class);
	@Autowired
	private IPayInvoiceService payInvoiceService;
	
	@RequestMapping("/checkPayInvoice")
	@ResponseBody
	public ApiResult checkPayInvoice(PayInvoiceNcCheckParams apiParam) {
		ApiResult apiResult = ApiResult.getErrorResult();
		try {
			logger.info("into checkPayInvoice action.");
			apiResult = payInvoiceService.checkPayInvoice(apiParam);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		logger.info("end checkPayInvoice action.");
		return apiResult;
	}
}
