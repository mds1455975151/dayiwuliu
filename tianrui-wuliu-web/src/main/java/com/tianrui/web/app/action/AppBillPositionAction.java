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
	
	@RequestMapping(value="/index",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult index(AppParam<WaybillQueryReq> appParam) throws Exception{
		AppResult rs = new AppResult();
		rs.setCode("000000");
		String bid = appParam.getBody().getId();
		List<BillPositionResp> list = billService.getBillPosition(bid);
		rs.setReturnData(list);
		return rs;
	}
}
