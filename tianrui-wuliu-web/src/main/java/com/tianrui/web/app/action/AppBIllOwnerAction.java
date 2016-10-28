package com.tianrui.web.app.action;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.IMemberCapaService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.bill.WaybillConfirmReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 货主我发布的运单APP接口
 * @author zhanggaohao
 * @createtime 2016年10月28日 上午9:38:47
 * @classname AppBIllOwnerAction.java
 */
@Controller
@RequestMapping("/app/billowner")
public class AppBIllOwnerAction {
	
	public Logger logger = LoggerFactory.getLogger(AppBIllOwnerAction.class);
	
	
	@Autowired
	protected IBillService billService;
	@Autowired
	IVehicleDriverService vehicleDriverService;
	@Autowired
	IMemberCapaService memberCapaService;

	
	//获取我发布的运单列表
	@RequestMapping(value="/page",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult page(AppParam<WaybillQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
	
		//拼装查询条件
		WaybillQueryReq query =appParam.getBody();
		query.setQueryType(1);
		query.setCurrId(uId);
		PaginationVO<WaybillResp> page =billService.page(query);
		
		appResult.setCode("000000");
		appResult.setTotal(page.getTotalInt());
		appResult.setReturnData(page.getList());
		return appResult;
	}
	
	
	//获取运单详情
	@RequestMapping(value="/detail",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult detail(AppParam<WaybillQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillQueryReq query =appParam.getBody();
		query.setCurrId(uId);
		WaybillResp bill  =billService.queryWayBill(query);
		
		appResult.setCode("000000");
		appResult.setReturnData(bill);
		return appResult;
	}
	
	//删除运单
	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ApiParamRawType(WaybillConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult delete(AppParam<WaybillConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs=billService.ownerdelete(req);
		return AppResult.valueOf(rs);
	}
	
	//确认签收运单
	@RequestMapping(value="/signConfirm",method=RequestMethod.POST)
	@ApiParamRawType(WaybillConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult signConfirm(AppParam<WaybillConfirmReq> appParam) throws Exception{
		///获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		return AppResult.valueOf(billService.signConfirm(req));
	}
	
}
