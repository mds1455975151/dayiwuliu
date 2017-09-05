package com.tianrui.web.app.action.bankcard;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.tianrui.api.intf.bankcard.IMemberBankCardService;
import com.tianrui.api.req.bankcard.MemberBankCardReq;
import com.tianrui.api.req.front.vehicle.AddVehicleBankCardReq;
import com.tianrui.api.resp.bankcard.MemberBankCardResp;
import com.tianrui.common.utils.UUIDUtil;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.PaginationVO;
import com.tianrui.common.vo.Result;
import com.tianrui.service.impl.OwnerDriverService;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

@Controller
@RequestMapping("/app/bankcard")
public class AppBankCardAction {
	
	public Logger logger = LoggerFactory.getLogger(AppBankCardAction.class);
	
	@Autowired
	private OwnerDriverService ownerDriverService;
	@Autowired
	private IMemberBankCardService memberBankCardService;
	/**
	 * @Title: saveOwnerPage 
	 * @Description: 添加车主银行卡页面
	 * @param @return
	 * @param @throws Exception   
	 * @return ModelAndView    
	 * @throws
	 */
	@RequestMapping(value="/findOwnerVehicleBankcard",method=RequestMethod.POST)
	@ApiParamRawType(MemberBankCardReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult findOwnerVehicleBankcard(AppParam<MemberBankCardReq> appParam)throws Exception{
		String id = appParam.getHead().getId();
		List <MemberBankCardResp> list = memberBankCardService.findOwnerById(id);
		AppResult result = new AppResult();
		result.setCode("000000");
		result.setReturnData(list);
		return result;
		
	}
	/**
	 * 
	 * @Title: addVehicleownerBankcard 
	 * @Description: TODO添加车主银行卡id
	 * @param @return
	 * @param @throws Exception   
	 * @return Result    
	 * @throws
	 */
	
	@RequestMapping(value="/addVehicleownerBankcard",method=RequestMethod.POST)
	@ApiParamRawType(AddVehicleBankCardReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult addVehicleownerBankcard(AppParam<AddVehicleBankCardReq> appParam)throws Exception {
		AddVehicleBankCardReq addVehicle = appParam.getBody();
		String id = appParam.getHead().getId();
		AddVehicleBankCardReq req = appParam.getBody();
		addVehicle.setDriverid(id);
		addVehicle.setVehicleownerid(req.getVehicleownerid());
		addVehicle.setId(UUIDUtil.getId());
		Result rs = ownerDriverService.addVehicleownerBankcard(addVehicle);
		return  AppResult.valueOf(rs);
	}
	
	/** 查询*/
	@RequestMapping(value="/find",method=RequestMethod.POST)
	@ApiParamRawType(MemberBankCardReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult find(AppParam<MemberBankCardReq> appParam) throws Exception{
		AppResult app = new AppResult();
		Head head = appParam.getHead();
		MemberBankCardReq req = appParam.getBody();
		req.setCreater(head.getId());
		//PaginationVO<MemberCapaListResp> vo = memberCapa.index(req);
		PaginationVO<MemberBankCardResp> page = memberBankCardService.selectBankCard(req);
		app.setCode("000000");
		app.setReturnData(page);
		return app;
	}
}
