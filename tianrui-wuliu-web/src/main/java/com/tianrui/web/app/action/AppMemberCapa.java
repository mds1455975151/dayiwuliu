package com.tianrui.web.app.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianrui.api.intf.IMemberCapaService;
import com.tianrui.api.req.front.capa.CapaReq;
import com.tianrui.common.vo.AppParam;
import com.tianrui.common.vo.AppResult;
import com.tianrui.common.vo.Head;
import com.tianrui.common.vo.Result;
import com.tianrui.web.smvc.ApiParamRawType;
import com.tianrui.web.smvc.ApiTokenValidation;

/**
 * 移动端运力共享
 * @类描述：
 * @创建人：lsj
 * @创建时间：2016年8月31日上午9:35:49
 *
 * @修改人：lsj
 * @修改时间：2016年8月31日上午9:35:49
 * @修改备注：
 *
 */
@Controller
@RequestMapping("/app/capa")
public class AppMemberCapa {

	@Autowired
	IMemberCapaService memberCapa;
	
	/** 查询我的运力
	 * @throws Exception */
	@RequestMapping(value="/index",method=RequestMethod.POST)
	@ApiParamRawType(CapaReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult index(AppParam<CapaReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		Head head = appParam.getHead();
		CapaReq req = appParam.getBody();
		req.setMemberid(head.getId());
		rs = memberCapa.index(req);
		return AppResult.valueOf(rs);
	}
	/**查询车辆*/
	@RequestMapping(value="/selectVhicle",method=RequestMethod.POST)
	@ApiParamRawType(CapaReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult selectVhicle(AppParam<CapaReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		CapaReq req = appParam.getBody();
		rs = memberCapa.selectVehicle(req);
		return AppResult.valueOf(rs);
	}
	/**添加车辆*/
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@ApiParamRawType(CapaReq.class)
	@ApiTokenValidation
	@ResponseBody
	public AppResult save(AppParam<CapaReq> appParam) throws Exception{
		Result rs = Result.getSuccessResult();
		Head head = appParam.getHead();
		CapaReq req = appParam.getBody();
		req.setMemberid(head.getId());
		req.setCellphone(head.getAccount());
		rs = memberCapa.save(req);
		return AppResult.valueOf(rs);
	}
}
