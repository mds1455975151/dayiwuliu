package com.tianrui.web.app.action;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.req.front.pay.PayInvoiceAdviceReq;
import com.tianrui.api.req.front.pay.PayInvoiceDetailQueryReq;
import com.tianrui.api.req.front.pay.PayInvoiceGenalReq;
import com.tianrui.api.req.front.pay.PayInvoiceQueryReq;
import com.tianrui.api.req.front.pay.PayInvoiceReq;
import com.tianrui.api.resp.pay.PayInvoiceDetailResp;
import com.tianrui.api.resp.pay.PayInvoiceResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.PayInvoiceDetailService;
import com.tianrui.service.impl.PayInvoiceService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
 * @类描述：移动端支付接口
 * @创建人：lsj
 * @创建时间：2016年9月26日下午4:48:05
 *
 * @修改人：lsj
 * @修改时间：2016年9月26日下午4:48:05
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/app/appPayAction")
public class AppPayAction {

	@Autowired
	PayInvoiceDetailService payInvoiceDetailService; 
	@Autowired
	PayInvoiceService payInvoiceService; 
	
	/** 查询运费结算单*/
	@RequestMapping(value="/pageDetail",method=RequestMethod.POST)
	@ApiParamRawType(PayInvoiceDetailQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult pageDetail(AppParam<PayInvoiceDetailQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		Head head = appParam.getHead();
		PayInvoiceDetailQueryReq req = appParam.getBody();
		req.setCurruId(head.getId());
		PaginationVO<PayInvoiceDetailResp> page =payInvoiceDetailService.page(req);
		appResult.setCode("000000");
		appResult.setTotal(page.getTotalInt());
		appResult.setReturnData(page.getList());
		return appResult;
	}
	
	/** 生成运费发票单*/
	@RequestMapping(value="/calculated",method=RequestMethod.POST)
	@ApiParamRawType(PayInvoiceGenalReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult calculated(AppParam<PayInvoiceGenalReq> appParam) throws Exception{
		Head head = appParam.getHead();
		PayInvoiceGenalReq req = appParam.getBody();
		Result rs = Result.getSuccessResult();
		req.setCurruId(head.getId());
		rs = payInvoiceDetailService.generalPayInvoice(req);
		return AppResult.valueOf(rs);
	}
	
	/** 发票账单查询*/
	@RequestMapping(value="/pageInvoice",method=RequestMethod.POST)
	@ApiParamRawType(PayInvoiceQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult pageInvoice(AppParam<PayInvoiceQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		Head head = appParam.getHead();
		PayInvoiceQueryReq req = appParam.getBody();
		req.setCurruId(head.getId());
		PaginationVO<PayInvoiceResp> page =payInvoiceService.page(req);
		appResult.setCode("000000");
		appResult.setTotal(page.getTotalInt());
		appResult.setReturnData(page.getList());
		return appResult;
	}
	
	/** 发票账单自审*/
	@RequestMapping(value="/payAudit",method=RequestMethod.POST)
	@ApiParamRawType(PayInvoiceAdviceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult payAudit(AppParam<PayInvoiceAdviceReq> appParam) throws Exception{
		Head head = appParam.getHead();
		PayInvoiceAdviceReq req = appParam.getBody();
		req.setCurruId(head.getId());
		Result rs =payInvoiceService.advice(req);
		return AppResult.valueOf(rs);
	}
	
	/** 运费单据提交
	 * @throws Exception */
	@RequestMapping(value="payInvoiceSave",method=RequestMethod.POST)
	@ApiParamRawType(PayInvoiceReq.class)
	@ApiTokenValidation
	@ResponseBody
	public Result payInvoiceSave(AppParam<PayInvoiceReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		Head head = appParam.getHead();
		PayInvoiceReq req = appParam.getBody();
		req.setCurruId(head.getId());
		rs = payInvoiceService.PayNcSave(req);
		return rs;
	}
	
}
