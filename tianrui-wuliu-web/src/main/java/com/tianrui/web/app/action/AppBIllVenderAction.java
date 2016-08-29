package com.tianrui.web.app.action;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IBillService;
import com.tianrui.api.intf.IVehicleDriverService;
import com.tianrui.api.req.front.bill.WaybillConfirmReq;
import com.tianrui.api.req.front.bill.WaybillEditReq;
import com.tianrui.api.req.front.bill.WaybillQueryReq;
import com.tianrui.api.req.front.bill.WaybillSaveReq;
import com.tianrui.api.resp.front.bill.BillGpsResp;
import com.tianrui.api.resp.front.bill.BillVehicleResp;
import com.tianrui.api.resp.front.bill.WaybillResp;
import com.tianrui.api.resp.front.position.PositionResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 
  * @ClassName: AppBIllVenderAction 
  * @Description: 我承运的运单 app接口
  * @author lixp(lixinpengbj@163.com)
  * @date 2016年6月22日 下午3:06:49 
  *
 */
@Controller
@RequestMapping("/app/billvender")
public class AppBIllVenderAction {
	
	public Logger logger = LoggerFactory.getLogger(AppBIllVenderAction.class);
	
	
	@Autowired
	protected IBillService billService;
	@Autowired
	IVehicleDriverService vehicleDriverService;

	
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
		query.setQueryType(2);
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
		if(bill == null){
			appResult.setReturnData("");
		}else{
			appResult.setReturnData(bill);
		}
		return appResult;
	}
	//获取承运计划轨迹
	@RequestMapping(value="/track",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult track(AppParam<WaybillQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillQueryReq query =appParam.getBody();
		query.setQueryType(2);
		query.setCurrId(uId);
		WaybillResp bill  =billService.queryWayBillWithTrack(query);
		
		appResult.setCode("000000");
		appResult.setReturnData(bill.getBillTrackList());
		return appResult;
	}
	//获取运单经纬度
	@RequestMapping(value="/gps",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult gps(AppParam<WaybillQueryReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillQueryReq query =appParam.getBody();
		query.setQueryType(2);
		query.setCurrId(uId);
		BillGpsResp bill  =billService.gps(query);
		
		appResult.setCode("000000");
		appResult.setReturnData(bill);
		return appResult;
	}
	
	
	//新建计划
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ApiParamRawType(WaybillSaveReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult save(AppParam<WaybillSaveReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillSaveReq query =appParam.getBody();
		query.setCurruId(uId);
		Result rs=billService.saveWayBill(query);
		
		return AppResult.valueOf(rs);
	}
	
	//编辑
	@RequestMapping(value="/edit",method=RequestMethod.POST)
	@ApiParamRawType(WaybillEditReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult edit(AppParam<WaybillEditReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillEditReq query =appParam.getBody();
		query.setCurruId(uId);
		Result rs=billService.editWayBill(query);
		return AppResult.valueOf(rs);
	}
	
	//收回
	@RequestMapping(value="/cancle",method=RequestMethod.POST)
	@ApiParamRawType(WaybillConfirmReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult cancle(AppParam<WaybillConfirmReq> appParam) throws Exception{
		//获取当前用户
		String uId =appParam.getHead().getId();
		
		//拼装查询条件
		WaybillConfirmReq req =appParam.getBody();
		req.setCurruId(uId);
		Result rs=billService.cancleConfirm(req);
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
		Result rs=billService.verderdelete(req);
		return AppResult.valueOf(rs);
	}
	//查询车辆
	@RequestMapping(value="/queryVehicle",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult queryVehicle(AppParam<WaybillQueryReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		//获取当前用户
		
		List<BillVehicleResp> list =billService.queryVehicle(appParam.getBody().getPlanId());
		rs.setData(list);
		return AppResult.valueOf(rs);
		
	}
	//查询已完成运单轨迹
	@RequestMapping(value="/queryBillAllTrack",method=RequestMethod.POST)
	@ApiParamRawType(WaybillQueryReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult queryBillTrack(AppParam<WaybillQueryReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		//获取当前用户
		WaybillQueryReq req =appParam.getBody();
		List<PositionResp> list =billService.getBIllTrack(req);
		rs.setData(list);
		return AppResult.valueOf(rs);
		
	}
	
	
	
}
