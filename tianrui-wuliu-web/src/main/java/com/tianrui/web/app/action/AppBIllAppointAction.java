package com.tianrui.web.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.service.impl.BillService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * @author zhanggaohao
 * @createtime 2016年9月12日 下午3:18:02
 * @classname AppBIllAppointAction.java
 */
@Controller
@RequestMapping("/app/billAppoint")
public class AppBIllAppointAction {
	
	public Logger logger = LoggerFactory.getLogger(AppBIllAppointAction.class);
	
	@Autowired
	private BillService billService;
	
	//获取承运计划列表
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult page(AppParam<WaybillQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		try {
			//获取当前用户
			String uId =appParam.getHead().getId();
			//拼装查询条件
			WaybillQueryReq query =appParam.getBody();
			query.setCurrId(uId);
			PaginationVO<WaybillResp> page = billService.queryAppointBillPage(query);
			appResult.setCode("000000");
			appResult.setTotal(page.getTotalInt());
			appResult.setReturnData(page.getList());
		} catch (Exception e) {
			appResult.setCode("000001");
			appResult.setMessage("页面初始化失败，请稍后重试！");
			logger.error(e.getMessage(), e);
			return appResult;
		}
		return appResult;
	}
}
