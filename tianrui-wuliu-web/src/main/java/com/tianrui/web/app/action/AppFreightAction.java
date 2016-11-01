package com.tianrui.web.app.action;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.admin.intf.IFileOrgCargoService;
import com.tianrui.api.intf.IFreightService;
import com.tianrui.api.intf.IMemberVoService;
import com.tianrui.api.intf.IRouteService;
import com.tianrui.api.req.admin.FileOrgCargoReq;
import com.tianrui.api.req.front.cargoplan.FreightReq;
import com.tianrui.api.req.front.cargoplan.RouteReq;
import com.tianrui.api.resp.admin.FileOrgCargoResp;
import com.tianrui.api.resp.front.cargoplan.FreightCRResp;
import com.tianrui.api.resp.front.cargoplan.FreightResp;
import com.tianrui.api.resp.front.cargoplan.RouteResp;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.MemberVo;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 运价策略APP接口
 * @author zhanggaohao
 * @createtime 2016年10月26日 上午9:27:05
 * @classname AppFreightAction.java
 */
@Controller
@RequestMapping("/app/freight")
public class AppFreightAction {
	
	public Logger logger = LoggerFactory.getLogger(AppFreightAction.class);
	
	@Autowired
	protected IMemberVoService memberVoService;
	
	@Autowired
	protected IFileOrgCargoService fileOrgCargoService;
	
	@Autowired
	protected IRouteService routeService;
	
	@Autowired
	protected IFreightService freightService;
	
	//查询运价策略货物路线信息
	@RequestMapping(value="/findFCR",method=RequestMethod.POST)
	@ApiParamRawType(FreightReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult findFCR(AppParam<FreightReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		FreightCRResp resp = freightService.findFCR(appParam.getBody());
		appResult.setCode("000000");
		appResult.setReturnData(resp);
		return appResult;
	}
	
	//查询货物信息
	@RequestMapping(value="/cargo",method=RequestMethod.POST)
	@ApiParamRawType(FileOrgCargoReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult cargo(AppParam<FileOrgCargoReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		//拼装查询条件
		FileOrgCargoReq req =appParam.getBody();
		MemberVo vo = memberVoService.get(uId);
		req.setOrgId(vo.getOrgid());
		List<FileOrgCargoResp> list = fileOrgCargoService.queryMyCargoInfo(req);
		appResult.setCode("000000");
		appResult.setReturnData(list);
		return appResult;
	}
	
	//查询路线信息
	@RequestMapping(value="/route",method=RequestMethod.POST)
	@ApiParamRawType(RouteReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult route(AppParam<RouteReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		MemberVo vo = memberVoService.get(uId);
		//拼装查询条件
		RouteReq req =appParam.getBody();
		req.setOrganizationid(vo.getOrgid());
		List<RouteResp> list = routeService.findRouteByEntity(req);
		appResult.setCode("000000");
		appResult.setReturnData(list);
		return appResult;
	}
	
	//查询运价策略
	@RequestMapping(value="/freight",method=RequestMethod.POST)
	@ApiParamRawType(FreightReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult freight(AppParam<FreightReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		MemberVo vo = memberVoService.get(uId);
		//拼装查询条件
		FreightReq req =appParam.getBody();
		req.setOrganizationid(vo.getOrgid());
		req.setcStatus("1");
		req.setrStatus("1");
		req.setStatus("0");
		req.setAuditstatus("1");//审核通过
		List<FreightResp> list = freightService.findByEntity(req);
		appResult.setCode("000000");
		appResult.setReturnData(list);
		return appResult;
	}
	
	//查询运价策略有效性
	@RequestMapping(value="/validForFreight",method=RequestMethod.POST)
	@ApiParamRawType(FreightReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult validForFreight(AppParam<FreightReq> appParam) throws Exception{
		AppResult appResult = new AppResult();
		//获取当前用户
		String uId =appParam.getHead().getId();
		MemberVo vo = memberVoService.get(uId);
		//拼装查询条件
		FreightReq req =appParam.getBody();
		req.setOrganizationid(vo.getOrgid());
		List<FreightResp> list = freightService.findByEntity(req);
		FreightResp resp = list.get(0);
		if(!"1".equals(resp.getrStatus())){
			appResult.setCode("1");
			appResult.setMessage("路径已被禁用");
		}
		if(!"0".equals(resp.getStatus())){
			appResult.setCode("1");
			appResult.setMessage("运价策略已被禁用");
		}
		if(!"1".equals(resp.getcStatus())){
			appResult.setCode("1");
			appResult.setMessage("货物已被禁用");
		}
		return appResult;
	}
	
}
