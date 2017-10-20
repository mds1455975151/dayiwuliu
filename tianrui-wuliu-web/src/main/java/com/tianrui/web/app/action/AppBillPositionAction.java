package com.tianrui.web.app.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IBillService;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.resp.front.bill.BillPositionResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 获取运单轨迹
 * @author jh
 *
 */
@Controller
@RequestMapping("/app/billPosition")
public class AppBillPositionAction {
	@Autowired
	IBillService billService;
	
	/** old获取轨迹*/
	@RequestMapping(value="/index",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult index(AppParam<WaybillQueryReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		rs.setCode("000000");
		String bid = appParam.getBody().getId();
		List<BillPositionResp> list = billService.getBillPositionOld(bid);
		rs.setReturnData(list);
		return rs;
	}
	
	/** 获取大易轨迹*/
	@RequestMapping(value="/dyindex",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult dyindex(AppParam<WaybillQueryReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		rs.setCode("000000");
		String bid = appParam.getBody().getId();
		List<BillPositionResp> list = billService.getBillPosition(bid);
		rs.setReturnData(list);
		return rs;
	}
	
	/** 获取中交轨迹*/
	@RequestMapping(value="/zjindex",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult zjindex(AppParam<WaybillQueryReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		rs.setCode("000000");
		String bid = appParam.getBody().getId();
		Result list = billService.getPosition(bid);
		rs.setReturnData(list.getData());
		return rs;
	}
}
