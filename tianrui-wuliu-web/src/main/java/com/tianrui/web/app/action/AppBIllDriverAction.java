package com.tianrui.web.app.action;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IBillService;
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
 * 
  * @ClassName: AppBIllDriverAction 
  * @Description: 我运输的运单 app接口
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月22日 下午3:06:49 
  *
 */
@Controller
@RequestMapping("/app/billdriver")
public class AppBIllDriverAction {
	
	public Logger logger = LoggerFactory.getLogger(AppBIllDriverAction.class);
	
	
	@Autowired
	protected IBillService billService;

	
	//获取承运计划列表
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
		query.setQueryType(3);
		query.setCurrId(uId);
		PaginationVO<WaybillResp> page =billService.page(query);
		
		appResult.setCode("000000");
		appResult.setTotal(page.getTotalInt());
		appResult.setReturnData(page.getList());
		return appResult;
	}
	
	
	//获取承运计划详情
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
		query.setQueryType(2);
		query.setCurrId(uId);
		WaybillResp bill  =billService.queryWayBill(query);
		
		appResult.setCode("000000");
		appResult.setReturnData(bill);
		return appResult;
	}
	
	

	
	//拒绝
	@RequestMapping(value="/refuse",method=RequestMethod.POST)
	@ApiParamRawType(WaybillConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult refuse(AppParam<WaybillConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs=billService.refuseConfirm(req);
		return AppResult.valueOf(rs);
	}
	
	//接受
	@RequestMapping(value="/accept",method=RequestMethod.POST)
	@ApiParamRawType(WaybillConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult accept(AppParam<WaybillConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs=billService.acceptConfirm(req);
		return AppResult.valueOf(rs);
	}
	//删除
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
		Result rs=billService.driverdelete(req);
		return AppResult.valueOf(rs);
	}
	
	
	//到达发货地
	@RequestMapping(value="/pickup",method=RequestMethod.POST)
	@ApiParamRawType(WaybillConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult pickupConfirm(AppParam<WaybillConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs=billService.pickupConfirm(req);
		return AppResult.valueOf(rs);
	}
	
	//装货完毕
	@RequestMapping(value="/departure",method=RequestMethod.POST)
	@ApiParamRawType(WaybillConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult departureConfirm(AppParam<WaybillConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs=billService.departureConfirm(req);
		return AppResult.valueOf(rs);
	}
	//到达收获地
	@RequestMapping(value="/arrived",method=RequestMethod.POST)
	@ApiParamRawType(WaybillConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult arrivedConfirm(AppParam<WaybillConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs=billService.arrivedConfirm(req);
		return AppResult.valueOf(rs);
	}
	//卸货完毕
	@RequestMapping(value="/discharge",method=RequestMethod.POST)
	@ApiParamRawType(WaybillConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult dischargeConfirm(AppParam<WaybillConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs=billService.dischargeConfirm(req);
		return AppResult.valueOf(rs);
	}
	
	
	
}
