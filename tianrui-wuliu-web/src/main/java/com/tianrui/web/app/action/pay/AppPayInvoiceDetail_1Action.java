package com.tianrui.web.app.action.pay;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.admin.intf.IPayInvoiceDetail1Service;
import com.tianrui.api.req.admin.pay.PayInviceSave1Req;
import com.tianrui.api.req.admin.pay.PayInvoiceDetail1FindReq;
import com.tianrui.api.req.admin.pay.PayInvoiceDetail1Req;
import com.tianrui.api.resp.admin.pay.PayInvoiceDetail1Resp;
import com.tianrui.api.resp.pay.PayVenderGroupResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.PayInvoiceDetailService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/payInvoiceItem_1")
public class AppPayInvoiceDetail_1Action {

	private Logger logger = Logger.getLogger(AppPayInvoiceDetail_1Action.class);
	
	@Autowired
	PayInvoiceDetailService payInvoiceDetailService; 
	
	@Autowired
	IPayInvoiceDetail1Service payInvoiceDetail1Service;
	
	
	//车主结算单查询
	@RequestMapping("/page")
	@ApiParamRawType(PayInvoiceDetail1FindReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult page(AppParam<PayInvoiceDetail1FindReq> appParam){
		Result rs = Result.getSuccessResult();
		try {
			PayInvoiceDetail1FindReq req = appParam.getBody();
			Head vo = appParam.getHead();
			if(req.getBillType()==null){
				rs.setCode("1");
				rs.setError("请选择账单身份");
				return AppResult.valueOf(rs);
			}
			if(req.getBillType()==1){
				req.setDriverId(vo.getId());
			}else if(req.getBillType()==2){
				req.setVenderId(vo.getId());
			}else if(req.getBillType()==3){
				req.setCreator(vo.getId());
				req.setBillType(2);
			}else {
				rs.setCode("1");
				rs.setError("请选择账单身份");
				return AppResult.valueOf(rs);
			}
			PaginationVO<PayInvoiceDetail1Resp> page = payInvoiceDetail1Service.select(req);
			rs.setData(page);
		} catch (Exception e) {
			logger.error(e.getMessage(),e);
		}
		return AppResult.valueOf(rs);
	}
	
	/** 生成发票单
	 * @throws Exception */
	@RequestMapping(value="/calculated",method = RequestMethod.POST)
	@ApiParamRawType(PayInviceSave1Req.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult calculated (AppParam<PayInviceSave1Req> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		PayInviceSave1Req req = appParam.getBody();
		Head vo = appParam.getHead();
		req.setCreater(vo.getId());
		rs = payInvoiceDetail1Service.savePayInvoice(req);
		return AppResult.valueOf(rs);
	}
	
	/**billId  运单id 查询运价确认信息*/
	@RequestMapping("billSelectPrice")
	@ApiParamRawType(PayInvoiceDetail1Req.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult billSelectPrice(AppParam<PayInvoiceDetail1Req> appParam) throws Exception{
		Result rs =Result.getSuccessResult();
		PayInvoiceDetail1Req req = appParam.getBody();
		rs = payInvoiceDetail1Service.billSelectPrice(req);
		return AppResult.valueOf(rs);
	}
	
	/** 查询多选数据
	 * @throws Exception */
	@RequestMapping(value="/selectIds",method = RequestMethod.POST)
	@ApiParamRawType(PayInvoiceDetail1Req.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult selectIds(AppParam<PayInvoiceDetail1FindReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		PayInvoiceDetail1FindReq req = appParam.getBody();
		if(StringUtils.isBlank(req.getIdStr())){
			rs.setCode("1");
			rs.setError("请选择数据");
			return AppResult.valueOf(rs);
		}
		PaginationVO<PayInvoiceDetail1Resp> page =payInvoiceDetail1Service.select(req);
		rs.setData(page);
		return AppResult.valueOf(rs);
	}
	/** 获取车主列表
	 * @throws Exception */
	@RequestMapping(value="/groupVender",method = RequestMethod.POST)
	@ApiParamRawType(PayInvoiceDetail1FindReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult groupVender(AppParam<PayInvoiceDetail1FindReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		Head vo = appParam.getHead();
		PayInvoiceDetail1FindReq req = appParam.getBody();
		req.setCreator(vo.getId());
		req.setBillType(2);
		List<PayVenderGroupResp> list = payInvoiceDetail1Service.groupByVender(req);
		rs.setData(list);
		return AppResult.valueOf(rs);
	}
}
	